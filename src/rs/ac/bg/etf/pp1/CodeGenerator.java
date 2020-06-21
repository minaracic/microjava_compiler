package rs.ac.bg.etf.pp1;

import java.util.*;

import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.mj.runtime.Code;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Scope;
import rs.etf.pp1.symboltable.concepts.Struct;
import rs.etf.pp1.symboltable.visitors.SymbolTableVisitor;

public class CodeGenerator extends VisitorAdaptor {
	private int mainPC;
	private Stack<SyntaxNode> operators = new Stack<SyntaxNode>();
	private LinkedList<SyntaxNode> output = new LinkedList<SyntaxNode>();
//	private Set<Obj> objects = new HashSet<Obj>();
	
	private List<Class> operatorTokens = Arrays.asList(new Class[]{AddopPls.class, AddopMin.class, EQUALOP.class, 
			MUL.class, DIV.class, MOD.class, 
			Lparent.class, Rparent.class,
			PLUSEQ.class, MINUSEQ.class, MULEQ.class, DIVEQ.class, MODEQ.class,
			LBRACKET.class, RBRACKET.class, PlsPls.class, MinMin.class, Negative.class});
			//- kao unarni operator, kako da razlikujem
	
	//dodaj - kao unarni operator
	private List<Class> rightAssociativeOperators = Arrays.asList(new Class[]{PLUSEQ.class, MINUSEQ.class, MULEQ.class, 
			DIVEQ.class, MODEQ.class, EQUALOP.class, Negative.class});
	
	public int getMainPC() {
		return mainPC;
	}
	
	public void visit(NUMBER num) {
		output.add(num);
	}
	
	public void visit(CHAR ch) {
		output.add(ch);
	}
	
	public void visit(BOOL bool) {
		output.add(bool);
	}
	
	public void visit(Dec dec) {
		generateCode();
//		Code.put(Code.const_1);
//		Code.put(Code.sub);
//		Code.put(Code.dup);
//		Code.store(dec.getDesignator().obj);
	}
	
	public void visit(Inc dec) {
		generateCode();
//		Obj var = dec.getDesignator().obj;
//		loadObject(var, vars);
//		Code.put(Code.const_1);
//		Code.put(Code.add);
//		Code.put(Code.dup);
//		Code.store(dec.getDesignator().obj);
	}
	
	public void visit(FunctionVoid name) {
		if ("main".equalsIgnoreCase(name.getFuncName())) {
			mainPC = Code.pc;
		}
		name.obj.setAdr(Code.pc);
		Code.put(Code.enter);
		Code.put(0);
		Code.put(SemanticAnalyzer.mainMethodLocals);
	}

	
	public void visit(Print printStmt) {
		Stack<Obj> o = generateCode();
		loadObject(o.pop(), o);
		
		if (printStmt.getExpr().struct.getKind() == Tab.charType.getKind()) {
			Code.loadConst(1);
			Code.put(Code.bprint);
		} else {
			Code.loadConst(5);
			Code.put(Code.print);
		}
	}
	
	public void visit(Negative op) {
		addOperatorToOutput(op);
	}
	
	private boolean isLeftAssociative(SyntaxNode op) {
		if(rightAssociativeOperators.indexOf(op.getClass()) != -1)
			return false;
		return true;
	}
	
	//todo: add other operators ++, --, []
	private int getPrecendence(SyntaxNode op) {
		if(op instanceof Inc || op instanceof Dec)
			return 1;
		if(op instanceof Lparent  || op instanceof Rparent)
			return 1;
		if(op instanceof LBRACKET || op instanceof RBRACKET)
			return 1;
		if(op instanceof MinMin || op instanceof PlsPls)
			return 1;
		if(op instanceof Negative)
			return 2;
		if(op instanceof MUL || op instanceof DIV || op instanceof MOD)
			return 3;
		if(op instanceof AddopPls || op instanceof AddopMin)
			return 4;
		if(op instanceof MULEQ || op instanceof DIVEQ || op instanceof MODEQ || op instanceof PLUSEQ || op instanceof MINUSEQ || op instanceof EQUALOP)
			return 14;
		return -1;
		
	}
	
	private void addOperatorToOutput(SyntaxNode op) {
		if(op instanceof LBRACKET) {
			operators.push(op);
			output.add(op);
			return;
		}
		
		if(op instanceof RBRACKET) {
			while(!(operators.lastElement() instanceof LBRACKET)) {
				SyntaxNode currentOp = operators.pop();
				output.add(currentOp);
			}
			output.add(op);
			
			if(operators.lastElement() instanceof LBRACKET) {
				
				operators.pop();
			}
			
			return;
		}
		
		if(op instanceof Lparent) {
			operators.push(op);
			return;
		}
		
		if(op instanceof Rparent) {
			while(!(operators.lastElement() instanceof Lparent)) {
				SyntaxNode currentOp = operators.pop();
				output.add(currentOp);
			}
			
			if(operators.lastElement() instanceof Lparent) {
				operators.pop();
			}
			
			return;
		}
		
		while(!operators.isEmpty() 
				&& !(operators.lastElement() instanceof Lparent)
				&& !(operators.lastElement() instanceof LBRACKET)
				&& (getPrecendence(operators.lastElement()) < getPrecendence(op)
						|| ((getPrecendence(operators.lastElement()) == getPrecendence(op)) && isLeftAssociative(op)))) {
			output.add(operators.pop());
		}
		
		operators.push(op);
		
	}
	
	//todo: dodaj ++ i --
	public void visit(PlsPls var) {
		addOperatorToOutput(var);
	}
	
	public void visit(MinMin var) {
		addOperatorToOutput(var);
	}
	
	public void visit(LBRACKET var) {
		addOperatorToOutput(var);
	}
	
	public void visit(RBRACKET var) {
		addOperatorToOutput(var);
	}
	
	public void visit(AddopPls plus) {
		addOperatorToOutput(plus);
	}
	
	public void visit(EQUALOP var) {
		addOperatorToOutput(var);
	}
	
	public void visit(AddopMin min) {
		addOperatorToOutput(min);
	}
	
	public void visit(MULEQ min) {
		addOperatorToOutput(min);
	}
	
	public void visit(MUL mul) {
		addOperatorToOutput(mul);
	}
	
	public void visit(DIV div) {
		addOperatorToOutput(div);
	}
	
	public void visit(MOD mod) {
		addOperatorToOutput(mod);
	}
	
	public void visit(PLUSEQ var) {
		addOperatorToOutput(var);
	}
	
	public void visit(MINUSEQ var) {
		addOperatorToOutput(var);
	}
	
	public void visit(DIVEQ var) {
		addOperatorToOutput(var);
	}
	
	public void visit(MODEQ var) {
		addOperatorToOutput(var);
	}
	
	public void visit(StatementDesignator var) {
		if(output.size() == 0)return;
		generateCode();
	}
	
	public void visit(DesignatorIdent var) {
		output.add(var);
	}
	
	public void visit(Lparent var) {
		addOperatorToOutput(var);
	}
	
	public void visit(Rparent var) {
		addOperatorToOutput(var);
	}
	
	public void visit(FactorNewExpr var) {
		SyntaxNode arr = output.pop();
		SyntaxNode cnt = output.pop();
		Obj arrObj = getObject(arr);
		Obj cntObj = getObject(cnt);
		
		Code.load(cntObj);
		if(arrObj.getType().getElemType().getKind() == Struct.Char) {
			//Code.put(Code.baload);
			Code.put(Code.newarray);
			Code.put(0);
		}
		else {
			//Code.put(Code.aload);
			Code.put(Code.newarray);
			Code.put(1);
		}
		Code.store(arrObj);
		
		output.clear();
		operators.clear();
	}
	
	public void visit(MethodDecl var) {
		Code.put(Code.exit);
		Code.put(Code.return_);
	}
	
	private Obj getObject(SyntaxNode in) {
		//if(in instanceof ConstVarDeclIdent)return ((ConstVarIdent) in).obj;
		if(in instanceof DesignatorIdent)return ((DesignatorIdent) in).obj;
		if(in instanceof NUMBER)return ((NUMBER) in).obj;
		if(in instanceof CHAR)return ((CHAR) in).obj;
		return null;
	}
	
	public void visit(ConstVarDecl var) {
		//Stack<Obj> o = generateCode();
		output.clear();
		operators.clear();
		
	}
	
	private boolean isOperator(Class in) {
		if(operatorTokens.indexOf(in) != -1) {
			return true;
		}
		return false;
	}
	
	public boolean isArrayElement(Obj obj) {
		if(obj.getKind() == Obj.Elem) 
			return true;
		return false;
	}
	
	public void loadElementOfArray(Obj obj) {
		
	}
	
	public void storeInElementOfArray(Obj obj) {
		
	}
	
	public void visit(ConstVarIdent var) {
		//output.add(var);
	}
	
	public boolean loadObject2(Stack<Obj> vars) {
		Obj curr = vars.pop();
		if(curr.getType().getKind() == Tab.noType.getKind())return true;
		
		if(isArrayElement(curr)) {
			Obj index = vars.pop();
			boolean swap = loadObject2(vars);
			if(swap) {
				Code.put(Code.dup_x1);
				Code.put(Code.pop);
			}
			
			Obj arr = vars.pop();
			
			
		}
		else {
			Code.load(curr);
			return false;
		}
	}
	
	//load object on stack
	public boolean loadObject(Obj obj, Stack<Obj> vars) {
		if(obj.getType().getKind() == Tab.noType.getKind())return true;
		
		if(isArrayElement(obj)) {
			//staro
			Obj index = vars.pop();
			boolean swap = loadObject(index, vars);
			if(swap) {
				Code.put(Code.dup_x1);
				Code.put(Code.pop);
			}
		
			Obj arr = vars.pop();
			Code.load(arr);
			Code.put(Code.dup_x1);
			Code.put(Code.pop);
			
			if(arr.getType().getElemType().getKind() == Struct.Char) 
				Code.put(Code.baload);
			else 
				Code.put(Code.aload);
			
//			Obj tmp = new Obj(Obj.Var, "$", Tab.noType);
//			vars.push(tmp);
			return false;
		}
		else {
			Code.load(obj);
			return false;
		}
	}
	
	public void putObjectOnStack(Obj obj, Stack<Obj> vars) {
		if(obj.getType().getKind() == Tab.noType.getKind())return;
		if(isArrayElement(obj)) {
			Obj index = vars.pop();
			loadObject(index, vars);
			Obj arr = vars.pop();
			Code.load(arr);
			
			Code.put(Code.dup_x1);
			Code.put(Code.pop);
		}
		else {
			Code.load(obj);
		}
	}
	
	public void storeInObject(Obj obj, Stack<Obj> vars) {
		
		if(isArrayElement(obj)) {
			if(obj.getType().getElemType().getKind() == Struct.Char) {
				Code.put(Code.bastore);
			}
			else {
				Code.put(Code.astore);
			}
			
		}
		else {
			Code.store(obj);
		}
	}
	
	public void visit(Read read) {
		Obj var = read.getDesignator().obj;
		Stack<Obj> vars = generateCode();
		
		if(var.getType().getKind() == Struct.Array) {
			if(var.getType().getElemType().getKind() == Struct.Char) {
				Code.put(Code.bread);
			}
			else {
				Code.put(Code.read);
			}
		}
		else if(var.getType().getKind() == Struct.Char) {
			Code.put(Code.bread);
		}
		else {
			Code.put(Code.read);
		}
		
		Obj tmp = new Obj(Obj.Var, "$", Tab.noType);
		Code.store(tmp);
		
		putObjectOnStack(vars.pop(), vars);
		Code.load(tmp);
		storeInObject(var, vars);
		
	}
	
	private boolean isNoType(Obj obj) {
		if(obj.getType().getKind() == Tab.noType.getKind())return true;
		return false;
	}
	
	public void generateCodeForOp(SyntaxNode in, Stack<Obj> vars) {
		if(in instanceof AddopPls) {
			Obj var1 = vars.pop();
			loadObject(var1, vars);
			
			Obj var2 = vars.pop();
			loadObject(var2, vars);
			
			Code.put(Code.add);
			Obj tmp = new Obj(Obj.Var, "$", Tab.noType);
			vars.push(tmp);
			
		}
		if(in instanceof Negative) {
			Obj var1 = vars.pop();
			loadObject(var1, vars);
			Code.put(Code.neg);
			Obj tmp = new Obj(Obj.Var, "$", Tab.noType);
			vars.push(tmp);
		}
		if(in instanceof MUL) {
			Obj var1 = vars.pop();
			loadObject(var1, vars);
			Obj var2 = vars.pop();
			loadObject(var2, vars);
			
			Code.put(Code.mul);
			Obj tmp = new Obj(Obj.Var, "$", Tab.noType);
			vars.push(tmp);
		}
		if(in instanceof DIV) {
			Obj var1 = vars.pop();
			loadObject(var1, vars);
			
			Obj tmp = new Obj(Obj.Var, "$", Tab.noType);
			Code.store(tmp);
			
			Obj var2 = vars.pop();
			loadObject(var2, vars);
			Code.load(tmp);
			Code.put(Code.div);
	
			tmp = new Obj(Obj.Var, "$", Tab.noType);
			vars.push(tmp);
			
		}
		if(in instanceof MOD) {
			Obj var1 = vars.pop();
			loadObject(var1, vars);
			Obj tmp = new Obj(Obj.Var, "$", Tab.noType);
			Code.store(tmp);
			Obj var2 = vars.pop();
			loadObject(var2, vars);
			Code.load(tmp);
			
			Code.put(Code.rem);
			tmp = new Obj(Obj.Var, "$", Tab.noType);
			vars.push(tmp);
		}
		if(in instanceof AddopMin) {
			Obj var1 = vars.pop();
			loadObject(var1, vars);
			Obj tmp = new Obj(Obj.Var, "$", Tab.noType);
			Code.store(tmp);
			
			Obj var2 = vars.pop();
			loadObject(var2, vars);
			Code.load(tmp);
			
			Code.put(Code.sub);
			tmp = new Obj(Obj.Var, "$", Tab.noType);
			vars.push(tmp);
		}
		if(in instanceof MinusMinus) {
			Obj var1 = vars.pop();
			putObjectOnStack(var1, vars);
			if(isArrayElement(var1)) {
				Code.put(Code.dup2);
				Code.put(Code.aload);
			}
			Code.put(Code.const_1);
			Code.put(Code.sub);
			
//			Code.put(Code.dup);
//			Obj tmp = new Obj(Obj.Var, "$", Tab.noType);
//			vars.push(tmp);
			storeInObject(var1, vars);
		}
		if(in instanceof PlusPlus) {
			Obj var1 = vars.pop();
			putObjectOnStack(var1, vars);
			if(isArrayElement(var1)) {
				Code.put(Code.dup2);
				Code.put(Code.aload);
			}
			Code.put(Code.const_1);
			Code.put(Code.add);
			
//			Code.put(Code.dup);
//			Obj tmp = new Obj(Obj.Var, "$", Tab.noType);
//			vars.push(tmp);
			storeInObject(var1, vars);
		}
		if(in instanceof EQUALOP) {
			
			Obj var1 = vars.pop(); 
			loadObject(var1, vars);
		
			Obj var2 = vars.pop();
			if(isArrayElement(var2)) {
				
				Obj ind = vars.pop();
				loadObject(ind, vars);
				if(!isNoType(ind)) {		
					Code.put(Code.dup_x1);
					Code.put(Code.pop);
				}
			
				Obj arr = vars.pop();
				Code.load(arr);
				Code.put(Code.dup_x2);
				Code.put(Code.pop);

				if(arr.getType().getElemType().getKind() == Struct.Char)Code.put(Code.bastore);
				else Code.put(Code.astore);
			}
			else {
//				Code.load(val);
				Code.store(var2);
				
			}
//			Obj val = new Obj(Obj.Var, "val", Tab.noType);
			
		}
		if(in instanceof PLUSEQ) {
			Obj var1 = vars.pop();
			loadObject(var1, vars);
			Obj tmp = new Obj(Obj.Var,"$", Tab.noType);
			Code.store(tmp);
			
			Obj var2 = vars.pop();
			if(isArrayElement(var2)) {	
				putObjectOnStack(var2, vars);
				Code.put(Code.dup2);
				Code.put(Code.aload);
				Code.load(tmp);
				Code.put(Code.add);
				Code.put(Code.dup_x2);
			}
			else {
				loadObject(var2, vars);
				Code.load(tmp);
				Code.put(Code.add);
				Code.put(Code.dup);
			}
			
			
			storeInObject(var2, vars);
			Obj tmp1 = new Obj(Obj.Var, "$", Tab.noType);
			vars.push(tmp1);					
		}
		if(in instanceof MINUSEQ) {
			Obj var1 = vars.pop();
			loadObject(var1, vars);
			Obj tmp = new Obj(Obj.Var,"$", Tab.noType);
			Code.store(tmp);
			
			Obj var2 = vars.pop();
			if(isArrayElement(var2)) {	
				putObjectOnStack(var2, vars);
				Code.put(Code.dup2);
				Code.put(Code.aload);
				Code.load(tmp);
				Code.put(Code.sub);
				Code.put(Code.dup_x2);
			}
			else {
				loadObject(var2, vars);
				Code.load(tmp);
				Code.put(Code.sub);
				Code.put(Code.dup);
			}
			
			storeInObject(var2, vars);
			Obj tmp1 = new Obj(Obj.Var, "$", Tab.noType);
			vars.push(tmp1);					
		}
		if(in instanceof MULEQ) {
			Obj var1 = vars.pop();
			loadObject(var1, vars);
			Obj tmp = new Obj(Obj.Var,"$", Tab.noType);
			Code.store(tmp);
			
			Obj var2 = vars.pop();
			if(isArrayElement(var2)) {	
				putObjectOnStack(var2, vars);
				Code.put(Code.dup2);
				Code.put(Code.aload);
				Code.load(tmp);
				Code.put(Code.mul);
				Code.put(Code.dup_x2);
			}
			else {
				loadObject(var2, vars);
				Code.load(tmp);
				Code.put(Code.mul);
				Code.put(Code.dup);
			}
			
			storeInObject(var2, vars);
			Obj tmp1 = new Obj(Obj.Var, "$", Tab.noType);
			vars.push(tmp1);					
		}
		if(in instanceof DIVEQ) {
			Obj var1 = vars.pop();
			loadObject(var1, vars);
			Obj tmp = new Obj(Obj.Var,"$", Tab.noType);
			Code.store(tmp);
			
			Obj var2 = vars.pop();
			if(isArrayElement(var2)) {	
				putObjectOnStack(var2, vars);
				Code.put(Code.dup2);
				Code.put(Code.aload);
				Code.load(tmp);
				Code.put(Code.div);
				Code.put(Code.dup_x2);
			}
			else {
				loadObject(var2, vars);
				Code.load(tmp);
				Code.put(Code.div);
				Code.put(Code.dup);
			}
			
			
			storeInObject(var2, vars);
			Obj tmp1 = new Obj(Obj.Var, "$", Tab.noType);
			vars.push(tmp1);		
		}
		if(in instanceof MODEQ) {
			Obj var1 = vars.pop();
			loadObject(var1, vars);
			Obj tmp = new Obj(Obj.Var,"$", Tab.noType);
			Code.store(tmp);
			
			Obj var2 = vars.pop();
			if(isArrayElement(var2)) {	
				putObjectOnStack(var2, vars);
				Code.put(Code.dup2);
				Code.put(Code.aload);
				Code.load(tmp);
				Code.put(Code.rem);
				Code.put(Code.dup_x2);
			}
			else {
				loadObject(var2, vars);
				Code.load(tmp);
				Code.put(Code.rem);
				Code.put(Code.dup);
			}
			
			
			storeInObject(var2, vars);
			Obj tmp1 = new Obj(Obj.Var, "$", Tab.noType);
			vars.push(tmp1);		
		}	
	}
	
	public Stack<Obj> generateCode() {
		Stack<Obj> vars = new Stack<Obj>();
	
		//add rest of operators
		while(!operators.isEmpty()) {
			output.add(operators.pop());
		}
		
	//	System.out.println(output);
		
		while(!output.isEmpty()) {
			SyntaxNode in = output.poll();
			System.out.println("In je: " + in.toString());
			
			if(in instanceof LBRACKET)continue;
			if(in instanceof RBRACKET) {
				//moze da bude i char
				Obj tmp = new Obj(Obj.Elem, "$", Tab.intType);
				vars.push(tmp);
				
			}
			
			else {
				if(isOperator(in.getClass()) == false) {
					Obj o = getObject(in);
					vars.push(o);
				}
				else {
					generateCodeForOp(in, vars);
				}
			}
			
		}
		
		if(vars.size() != 0)
			return vars;
		return null;
	}

}
