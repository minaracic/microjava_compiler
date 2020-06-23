package rs.ac.bg.etf.pp1;

import javax.smartcardio.TerminalFactorySpi;

import org.apache.log4j.Logger;

import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.symboltable.*;
import rs.etf.pp1.symboltable.concepts.*;

public class SemanticAnalyzer extends VisitorAdaptor{
	public static int mainMethodLocals = 0;

	int printCallCount = 0;
	boolean returnFound = false;
	boolean errorDetected = false;
	boolean mainFound = false;
	
	int totalGlobalVar = 0;
	int totalGlobalConstVar = 0;
	int totalGlobalArray = 0;
	int nVars = 0;
	
	Struct currentType = Tab.noType;
	Obj currentMethod = Tab.noObj;
	
	public void report_error(String message, SyntaxNode info) {
		errorDetected = true;
		StringBuilder msg = new StringBuilder(message);
		int line = (info == null) ? 0: info.getLine();
		if (line != 0)
			msg.append (" na liniji ").append(line);
		log.error(msg.toString());
	}

	public void report_info(String message, SyntaxNode info) {
		StringBuilder msg = new StringBuilder(message); 
		int line = (info == null) ? 0: info.getLine();
		if (line != 0)
			msg.append (" na liniji ").append(line);
		log.info(msg.toString());
	}
	
	public boolean passed() {
		return !errorDetected && mainFound;
	}
	
	Logger log = Logger.getLogger(getClass());

    public int getTotalGlobalVar() {
		return totalGlobalVar;
	}
    
    public int getMainMethodLocals() {
		return mainMethodLocals;
	}

	public int getTotalGlobalConstVar() {
		return totalGlobalConstVar;
	}

	public int getTotalGlobalArray() {
		return totalGlobalArray;
	}

	public void visit(ProgName progName){
    	progName.obj = Tab.insert(Obj.Prog, progName.getProgName(), Tab.noType);
    	Tab.insert(Obj.Type, "bool", new Struct(Struct.Bool));
    	Tab.insert(Obj.Meth, "len", Tab.intType);
    	Tab.insert(Obj.Meth, "chr", Tab.charType);
    	Tab.insert(Obj.Meth, "odr", Tab.intType);
    	Tab.openScope();
    }
    
    public void visit(Program program){
    	nVars = Tab.currentScope().getnVars();
    	Tab.chainLocalSymbols(program.getProgName().obj);
    	Tab.closeScope();
    } 

    public void visit(Print var) {
    	Struct bool = Tab.find("bool").getType();
    	if(var.getExpr().struct.getKind() != Struct.Int
    		&& var.getExpr().struct.getKind() != Struct.Char
    		&& var.getExpr().struct.getKind() != bool.getKind()) {
    		report_error("Izraz u print naredbi nije dobrog tipa", var);
    	}
    }
    
    public void visit(ConstVarDecl constVar) {
    	int line = constVar.getLine();
    	String name = ((ConstVarDeclIdent)constVar.getConstVarDeclIdent()).obj.getName();
    	Obj constValue = constVar.getConstValue().obj;
    	Obj var = Tab.find(name);
    	
		if(constValue.getType() == currentType) {
			var.setAdr(constValue.getAdr());
		//	constVar.obj = var;
			totalGlobalConstVar++;
		}
		else {
			report_error("Greska na liniji " + line + " nekompatibilni tipovi podataka", null);
		}

    }
    
    public void visit(ActParsExpr var) {
		var.struct = ((ActParsExpr) var).getExpr().struct;
    }
    
    public void visit(WithActPars var) {
		var.struct = ((WithActPars) var).getActPars().struct;
    }
    
    public void visit(FactorNewExpr var) {
    	var.struct = new Struct(Struct.Array, var.getType().struct);
    	if(var.getExpr().struct.getKind() != Struct.Int) {
    		report_info("Izraz u [] nije int tipa ", var);
    	}
    }
    
    public void visit(DesignatorFuncCall var) {
		if(((DesignatorIdent)var.getDesignator()).getI1().equalsIgnoreCase("len")){
    		if(((DesignatorFuncCall) var).getOptionalActPars().struct.getKind() != Struct.Array) {
    			report_error("Izraz u len funkciji nije tipa niz", var);
    		}
    	}
		if(((DesignatorIdent)var.getDesignator()).getI1().equalsIgnoreCase("ord")){
			if(((DesignatorFuncCall) var).getOptionalActPars().struct.getKind() == Struct.Array) {
				if(((DesignatorFuncCall) var).getOptionalActPars().struct.getElemType().getKind() != Struct.Char) {
	    			report_error("Izraz u ord funkciji nije tipa char", var);
				}
			}
			else if(((DesignatorFuncCall) var).getOptionalActPars().struct.getKind() != Struct.Char) {
    			report_error("Izraz u ord funkciji nije tipa char", var);
    		}
    	}
		if(((DesignatorIdent)var.getDesignator()).getI1().equalsIgnoreCase("chr")){
			if(((DesignatorFuncCall) var).getOptionalActPars().struct.getKind() == Struct.Array) {
				if(((DesignatorFuncCall) var).getOptionalActPars().struct.getElemType().getKind() != Struct.Int) {
	    			report_error("Izraz u chr funkciji nije tipa int", var);
				}
			}
			else if(((DesignatorFuncCall) var).getOptionalActPars().struct.getKind() != Struct.Int) {
    			report_error("Izraz u chr funkciji nije tipa int", var);
    		}
    	}
    }	
   
    public void visit(ArrayInit arr) {
    	int line = arr.getLine();
    	String name = arr.getArrayName();
    	
    	if(currentMethod.getName().equals("main")) {
    		mainMethodLocals++;
    	}
    	
    	if(Tab.currentScope().findSymbol(name) == null) {
    		Obj constVal = Tab.insert(Obj.Var, name, new Struct(Struct.Array, currentType));
    		
    		
    		if(mainFound) {
    			constVal.setLevel(1);
    		}
    		totalGlobalArray++;
    		report_info("Deklarisana promenljiva "+ name, arr);
    		//arr.obj = constVal

    	}
    	else {
			report_error("Greska na liniji " + line + " (" + name + ") vec deklarisano", null);
    	}
    }
    
    public void visit(VarDeclSingle var) {
    	int line = var.getLine();
    	String name = var.getVarName();
    	
    	if(currentMethod.getName().equals("main")) {
    		mainMethodLocals++;
    	}
    	
    	if(Tab.currentScope().findSymbol(name) == null) {
    		Obj constVal = Tab.insert(Obj.Var, name, currentType);
    		constVal.setLevel(1);
    		totalGlobalVar++;
    		report_info("Deklarisana promenljiva "+ name, var);

    	}
    	else {
			report_error("Greska na liniji " + line + " (" + name + ") vec deklarisano", null);
    	}
    }
    
    public void visit(VarList var) {
    	int line = var.getLine();
    	String name = var.getVarName();
    	
    	if(currentMethod.getName().equals("main")) {
    		mainMethodLocals++;
    	}
    	
    	if(Tab.currentScope().findSymbol(name) == null) {
    		Obj constVal = Tab.insert(Obj.Var, name, currentType);
    		if(mainFound) {
    			constVal.setLevel(1);
    		}
    		totalGlobalVar++;
    		report_info("Deklarisana promenljiva "+ name, var);

    	}
    	else {
			report_error("Greska na liniji " + line + " (" + name + ") vec deklarisano", null);
    	}
    }
    
    public void visit(NUMBER var) {
		var.obj = new Obj(Obj.Con, "", Tab.intType, var.getN1(), Obj.NO_VALUE);
    }
    
    public void visit(CHAR var) {
    	var.obj = new Obj(Obj.Con, "", Tab.charType, var.getC1(), Obj.NO_VALUE);
    }
    
    public void visit(BOOL var) {
    	Boolean value = var.getB1();
    	
    	int adr = 0;
    	if(value.equals(new Boolean("true"))) {
    		adr = 1;
    	}
    	
    	var.obj = new Obj(Obj.Con, "", Tab.find("bool").getType(), adr, Obj.NO_VALUE);
    }
 
    public void visit(ConstDecl constDecl) {
    	currentType = Tab.noType;
    }
    
    public void visit(ConstType constType){
    	constType.struct = constType.getType().struct;
    	currentType = constType.struct;
    }
    
    public void visit(Type type){
    	Obj typeNode = Tab.find(type.getTypeName());
    	if(typeNode == Tab.noObj){
    		report_error("Nije pronadjen tip " + type.getTypeName() + " u tabeli simbola", null);
    		type.struct = Tab.noType;
    	}else{
    		if(Obj.Type == typeNode.getKind()){
    			type.struct = typeNode.getType();
    		}else{
    			report_error("Ime " + type.getTypeName() + " ne predstavlja tip", type);
    			type.struct = Tab.noType;
    		}
    	}
    }
    
    public void visit(DesignatorIdent var) {
    	Obj obj = Tab.find(var.getI1());
    	if(obj == Tab.noObj) {
			report_error("Identifikator ne postoji", var);
			obj = new Obj(obj.getKind(), var.getI1(), obj.getType());
    	}
    	
    	var.obj = obj;
    }
    
    public void visit(ConstVarIdent var) {
    	String name = var.getI1();
    	int line = var.getLine();
    	Obj constVal;
    	
    	if(Tab.currentScope().findSymbol(name) == null) {
			constVal = Tab.insert(Obj.Con, name, currentType);
			constVal.setLevel(0);
			totalGlobalConstVar++;
			report_info("Deklarisana promenljiva "+ name, var);
		}
   
    	else {
			report_error("Greska na liniji " + line + " (" + name + ") vec deklarisano", null);
			constVal = new Obj(Obj.Var, name, Tab.noType);
    	}
    	
    	var.obj = constVal;
    }
    
    public void visit(DesignatorArr var) {
    	Obj obj = var.getDesignator().obj;
    	var.obj = Tab.noObj;
    	
    	if(obj.getType().getKind() != Struct.Array) {
    		report_error("Promenljiva nije tipa niz!", var);
    	}
    	else {
    		if(var.getExpr().struct.getKind() != Struct.Int) {
        		report_error("Izraz u [] nije tipa int!", var);
        	}
    		var.obj = new Obj(Obj.Elem, obj.getName(), obj.getType().getElemType());
    	}
    
    	if(var.obj.equals(Tab.noObj)) {
    		var.obj = new Obj(Tab.noObj.getKind(), var.getDesignator().obj.getName(), Tab.noType);
    	}
    	
    }
    
    public void visit(DesignatorAssign var) {
    	if(var.getDesignator().obj.getType().getKind() == 3) {
    		if(var.getExpr().struct.getKind() == Struct.Array) {
    			if(var.getExpr().struct.getElemType().getKind() != var.getDesignator().obj.getType().getElemType().getKind()) {
    				report_error("Nekompatibilni tipovi u inicijalizaciji niza", var);
    			}
    		}
    		else {
    			if(var.getExpr().struct.getKind() != var.getDesignator().obj.getType().getElemType().getKind()) {
    				report_error("Nekompatibilni tipovi u inicijalizaciji niza", var);
    			}
    		}
    	}
    	else if(var.getExpr().struct.getKind() != var.getDesignator().obj.getType().getKind())
    		report_error("Nekompatibilni tipovi u dodeli vrednosti", var);
    }
    
    public void visit(ExprMin var) {
    	if(!var.getTerm().struct.equals(Tab.intType)) {
    		report_error("Promenljiva iza minusa ne predstavlja int tip!", var);
    	}
    	
    	var.struct = var.getTerm().struct;
    }
    
    public void visit(ExprTerm var) {
    	var.struct = var.getTerm().struct;
    }
    
    public void visit(FactorConst var) {
    	var.struct = var.getConstValue().obj.getType();
    }
 
    public void visit(FunctionType var) {
    	if(var.getFuncName().equals("main")) {
    		report_error("Povratni tip main metode nije dobar!", var);
    	}
    	
		Struct retType = var.getType().struct;
		Obj obj = Tab.insert(Obj.Meth, var.getFuncName(), retType);
		currentMethod = obj;
		var.obj = obj;
		report_info("Deklarisana metoda "+ var.getFuncName(), var);
    	
    }
    
    public void visit(FunctionVoid var) {
    	if(var.getFuncName().equals("main") && mainFound) {
    		report_error("Main metoda vec deklarisana!", var);
    	}
    	
    	else if(var.getFuncName().equals("main") && !mainFound) {
			mainFound = true;
			Obj obj = Tab.insert(Obj.Meth, var.getFuncName(), Tab.noType);
			currentMethod = obj;
			var.obj = obj;
			report_info("Deklarisana metoda "+ var.getFuncName(), var);
		}
    	else var.obj = Tab.insert(Obj.Meth, var.getFuncName(), Tab.noType);
    }
    
    public void visit(WithFormPars var) {
    	if(currentMethod.getName().equals("main")) {
    		report_error("Main metoda ne sme da ima formalne parametre", var);
    	}
    }
    
    //todo: dodaj za addopRight
//    public void visit(ExprAddopLeft var) {	
//    	if(var.getExpr().struct.getKind() == var.getTerm().struct.getKind()) {
//    		if(var.getExpr().struct.getKind() != Struct.Int) {
//    			report_error("Izraz nije tipa int!", var);
//    		}
//    	}
//    	else {
//    		report_error("Tipovi u izrazu nisu kompatibilni!", var);
//    	}
//
//		var.struct = var.getExpr().struct;
//    }
    
    public void visit(ExprAddopLeft var) {	
		if(var.getExpr().struct.getKind() == var.getTerm().struct.getKind()) {
			if(var.getExpr().struct.getKind() != Struct.Int) {
				report_error("Izraz nije tipa int!", var);
			}
		}
		else {
			report_error("Tipovi u izrazu nisu kompatibilni!", var);
		}
    	var.struct = var.getExpr().struct;
    }
    
    public void visit(ExprAddopRight var) {	
    	if(!var.getExpr().struct.compatibleWith(var.getTerm().struct)) {
//    		if(var.getExpr().struct.getKind() != Struct.Int) {
//    			report_error("Izraz nije tipa int!", var);
//    		}
    		report_error("Tipovi u izrazu nisu kompatibilni!", var);
    	}
		if(var.getExpr() instanceof ExprTerm) {
			if((((ExprTerm)var.getExpr()).getTerm() instanceof TermFactor)) {
				TermFactor t = (TermFactor) ((ExprTerm)var.getExpr()).getTerm();
				if(t.getFactor() instanceof FactorDesignator == false)
					report_error("Nepravilan izraz na levoj strani dodele", var);
			}
			
		}
		var.struct = var.getExpr().struct;
    }
    
    public void visit(FactorDesignator var) {
    	Obj obj = var.getDesignator().obj;
    	int objKind = obj.getKind();
    	if(objKind == Obj.Meth || objKind == Obj.Prog || objKind == Obj.Fld || objKind == Obj.Type) {
    		report_error("Identifikator nije promenljiva", var);
    	}
    	
		var.struct = obj.getType();
    }
    
    public void visit(FuncCall var) {
    	int designatorKind = var.getDesignator().obj.getKind();
    	
    	if(designatorKind != Obj.Meth) {
    		report_error("Identifikator nije funkcija", var);
    	}
    	else {
    		if(((DesignatorIdent)var.getDesignator()).getI1().equalsIgnoreCase("len")){
        		if(((FuncCall) var).getOptionalActPars().struct.getKind() != Struct.Array) {
        			report_error("Izraz u len funkciji nije tipa niz", var);
        		}
        	}
    		if(((DesignatorIdent)var.getDesignator()).getI1().equalsIgnoreCase("ord")){
    			if(((FuncCall) var).getOptionalActPars().struct.getKind() == Struct.Array) {
    				if(((FuncCall) var).getOptionalActPars().struct.getElemType().getKind() != Struct.Char) {
    	    			report_error("Izraz u ord funkciji nije tipa char", var);
    				}
    			}
    			else if(((FuncCall) var).getOptionalActPars().struct.getKind() != Struct.Char) {
        			report_error("Izraz u ord funkciji nije tipa char", var);
        		}
        	}
    		if(((DesignatorIdent)var.getDesignator()).getI1().equalsIgnoreCase("chr")){
    			if(((FuncCall) var).getOptionalActPars().struct.getKind() == Struct.Array) {
    				if(((FuncCall) var).getOptionalActPars().struct.getElemType().getKind() != Struct.Int) {
    	    			report_error("Izraz u chr funkciji nije tipa int", var);
    				}
    			}
    			else if(((FuncCall) var).getOptionalActPars().struct.getKind() != Struct.Int) {
        			report_error("Izraz u chr funkciji nije tipa int", var);
        		}
        	}
    	}
    	
    	
    	var.struct = var.getDesignator().obj.getType();
    	
    }
    
    public void visit(ExprInParent var) {
    	var.struct = var.getExpr().struct;
    }
    
    public void visit(TermFactor var) {
    //	System.out.println("TermFactor " + var.getFactor().struct.getKind());
//    	if(var.getFactor().struct.getKind() == 3) {
//    		
//    	}
//    	if(var.getParent() instanceof FactorNewExpr) {
//    		var.struct = ((FactorNewExpr)var.getParent()).struct.getElemType();
//    	}
//    	else{
//    		var.struct = var.getFactor().struct;
//    	}
//    	System.out.println("TermFactor " + var.struct.getKind());
    	var.struct = var.getFactor().struct;
    }

    public void visit(TermMulop var) {
    	if(!var.getTerm().struct.compatibleWith(var.getFactor().struct)) {
//    		if(var.getTerm().struct.getKind() != Struct.Int) {
//    			report_error("Izraz nije tipa int!", var);
//    		}
    		report_error("Tipovi u izrazu nisu kompatibilni! ", var);
    	}
    	
    	
    	if(var.getMulop() instanceof RightMulop) {
    		if(var.getTerm() instanceof TermFactor) {
    			if((((TermFactor)var.getTerm()).getFactor() instanceof FactorDesignator) == false) {
    				report_error("Nepravilan izraz na levoj strani dodele", var);
    			}
    			
    		}
    	}
    
		var.struct = var.getTerm().struct;
    }
   
    public void visit(Inc var) {
    	if(var.getDesignator().obj.getType().getKind() != Struct.Int) {
    		report_error("Izraz nije tipa int!", var);
    	}
    }
    
    public void visit(Dec var) {
    	if(var.getDesignator().obj.getType().getKind() != Struct.Int) {
    		report_error("Izraz nije tipa int!", var);
    	}
    }
}
