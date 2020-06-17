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
	
	private List<Class> operatorTokens = Arrays.asList(new Class[]{AddopPls.class, AddopMin.class, Equal.class, 
			MUL.class, DIV.class, MOD.class, 
			Lparent.class, Rparent.class,
			PLUSEQ.class, MINUSEQ.class, MULEQ.class, DIVEQ.class, MODEQ.class,
			LBRACKET.class, RBRACKET.class});
			//- kao unarni operator, kako da razlikujem
	
	//dodaj - kao unarni operator
	private List<Class> rightAssociativeOperators = Arrays.asList(new Class[]{PLUSEQ.class, MINUSEQ.class, MULEQ.class, 
			DIVEQ.class, MODEQ.class, Equal.class});
	
	public int getMainPC() {
		return mainPC;
	}
	
	public void visit(NUMBER num) {
		output.add(num);
	}
	
	public void visit(CHAR ch) {
		ch.obj.setLevel(0);
		Code.load(ch.obj);
	}
	
	public void visit(BOOL bool) {
		bool.obj.setLevel(0);
		Code.load(bool.obj);
	}
	
	public void visit(Dec dec) {
		Code.put(Code.const_1);
		Code.put(Code.sub);
		Code.put(Code.dup);
		Code.store(dec.getDesignator().obj);
	}
	
	public void visit(Inc dec) {
		Code.put(Code.const_1);
		Code.put(Code.add);
		Code.put(Code.dup);
		Code.store(dec.getDesignator().obj);
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
//		Obj o = getObject(generateCode());
		Obj o =generateCode();
		Code.load(o);
		if (printStmt.getExpr().struct.getKind() == Tab.charType.getKind()) {
			Code.loadConst(1);
			Code.put(Code.bprint);
		} else {
			Code.loadConst(5);
			Code.put(Code.print);
		}
	}
	
//	public void visit(Negative op) {
//		addOperatorToOutput("-u");
//	}
//	
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
//		if(op.equals("-u"))
//			return 2;
		if(op instanceof MUL || op instanceof DIV || op instanceof MOD)
			return 3;
		if(op instanceof AddopPls || op instanceof AddopMin)
			return 4;
		if(op instanceof MULEQ || op instanceof DIVEQ || op instanceof MODEQ || op instanceof PLUSEQ || op instanceof MINUSEQ || op instanceof Equal)
			return 14;
		return -1;
		
	}
	
	private void addOperatorToOutput(SyntaxNode op) {
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
				&& (getPrecendence(operators.lastElement()) < getPrecendence(op)
						|| ((getPrecendence(operators.lastElement()) == getPrecendence(op)) && isLeftAssociative(op)))) {
			output.add(operators.pop());
		}
		
		operators.push(op);
		
	}
	
	//todo: dodaj ++ i --
	
	public void visit(LBRACKET var) {
		addOperatorToOutput(var);
	}
	
	public void visit(RBRACKET var) {
		addOperatorToOutput(var);
	}
	
	public void visit(AddopPls plus) {
		addOperatorToOutput(plus);
	}
	
	public void visit(Equal var) {
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
		System.out.println(output.size());
		generateCode();
	}
	
	public void visit(DesignatorIdent var) {
		output.add(var);
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
		
	}
	
	public void visit(MethodDecl var) {
		Code.put(Code.exit);
		Code.put(Code.return_);
	}
	
	private Obj getObject(SyntaxNode in) {
		if(in instanceof DesignatorIdent)return ((DesignatorIdent) in).obj;
		if(in instanceof NUMBER)return ((NUMBER) in).obj;
		return null;
	}
	
	private boolean isOperator(Class in) {
		if(operatorTokens.indexOf(in) != -1) {
			return true;
		}
		return false;
	}
	
	public void visit(Lparent var) {
		addOperatorToOutput(var);
	}
	
	public void visit(Rparent var) {
		addOperatorToOutput(var);
	}
	
	public Obj generateCode() {
		Stack<Obj> vars = new Stack<Obj>();
	
		//add rest of operators
		while(!operators.isEmpty()) {
			output.add(operators.pop());
		}
		
		System.out.println(output);
		
		while(!output.isEmpty()) {
			SyntaxNode in = output.poll();
//			System.out.println("In je: " + in.toString());
			
			
			if(isOperator(in.getClass()) == false) {
				Obj o = getObject(in);
				vars.push(o);
			}
			else {
				
				if(in instanceof AddopPls) {
					Obj var1 = vars.pop();
					Obj var2 = vars.pop();
					
					Code.load(var1);
					Code.load(var2);
					Code.put(Code.add);
					Obj tmp = new Obj(Obj.Var, "$", Tab.intType);
					tmp.setAdr(var1.getAdr()+var2.getAdr());
					Code.store(tmp);
					vars.push(tmp);
				}
				if(in.equals("-u")) {
					
				}
				if(in instanceof MUL) {
					Obj var1 = vars.pop();
					Obj var2 = vars.pop();
					Code.load(var1);
					Code.load(var2);
					Code.put(Code.mul);
					Obj tmp = new Obj(Obj.Var, "$", Tab.intType);
					tmp.setAdr(var1.getAdr()*var2.getAdr());
					Code.store(tmp);
					vars.push(tmp);
				}
				if(in instanceof DIV) {
					Obj var1 = vars.pop();
					Obj var2 = vars.pop();
					Code.load(var2);
					Code.load(var1);
					Code.put(Code.div);
					Obj tmp = new Obj(Obj.Var, "$", Tab.intType);
					tmp.setAdr(var2.getAdr()/var1.getAdr());
					Code.store(tmp);
					vars.push(tmp);
				}
				if(in instanceof MOD) {
					Obj var1 = vars.pop();
					Obj var2 = vars.pop();
					Code.load(var2);
					Code.load(var1);
					Code.put(Code.rem);
					Obj tmp = new Obj(Obj.Var, "$", Tab.intType);
					tmp.setAdr(var2.getAdr()%var1.getAdr());
					Code.store(tmp);
					vars.push(tmp);
				}
				if(in instanceof AddopMin) {
					Obj var1 = vars.pop();
					Obj var2 = vars.pop();
					
					Code.load(var1);
					Code.load(var2);
					Code.put(Code.sub);
					Obj tmp = new Obj(Obj.Var, "$", Tab.intType);
					tmp.setAdr(var1.getAdr()-var2.getAdr());
					Code.store(tmp);
					vars.push(tmp);
				}
				if(in instanceof Equal) {
					Obj var1 = vars.pop();
					Obj var2 = vars.pop();
					Code.load(var1);
					Code.store(var2);
				}
				if(in instanceof PLUSEQ) {
					Obj var1 = vars.pop();
					Obj var2 = vars.pop();
					Code.load(var1);
					Code.load(var2);
					Code.put(Code.add);
					Code.store(var2);
					vars.push(var2);				
				}
				if(in instanceof MINUSEQ) {
					Obj var1 = vars.pop();
					Obj var2 = vars.pop();
					Code.load(var2);
					Code.load(var1);
					Code.put(Code.sub);
					Code.store(var2);
					vars.push(var2);				
				}
				if(in instanceof MULEQ) {
					Obj var1 = vars.pop();
					Obj var2 = vars.pop();
					Code.load(var2);
					Code.load(var1);
					Code.put(Code.mul);
					Code.store(var2);
					vars.push(var2);	
				}
				if(in instanceof DIVEQ) {
					Obj var1 = vars.pop();
					Obj var2 = vars.pop();
					Code.load(var2);
					Code.load(var1);
					Code.put(Code.div);
					Code.store(var2);
					vars.push(var2);	
				}
				if(in instanceof MODEQ) {
					Obj var1 = vars.pop();
					Obj var2 = vars.pop();
					Code.load(var2);
					Code.load(var1);
					Code.put(Code.rem);
					Code.store(var2);
					vars.push(var2);	
				}	
			}
			
		}
		
		if(vars.size() != 0)
			return vars.pop();
		return null;
	}

}
