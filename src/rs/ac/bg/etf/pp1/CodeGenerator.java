package rs.ac.bg.etf.pp1;

import java.util.*;

import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.mj.runtime.Code;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Struct;

public class CodeGenerator extends VisitorAdaptor {
	private int mainPC;
	private Stack<String> operators = new Stack<String>();
	private LinkedList<String> output = new LinkedList<String>();
	private Stack<Obj> objects = new Stack<Obj>();
	
	private List<String> operatorTokens = Arrays.asList(new String[]{"+", "-", "*", "/", "%", "(", ")", "-u", "+=", "-=", "=", "*=", "/=", "%="});
	private List<String> rightAssociativeOperators = Arrays.asList(new String[]{"-u" ,"+=", "-=", "=", "*=", "/=", "%="});
	
	public int getMainPC() {
		return mainPC;
	}
	
	public void visit(NUMBER num) {
		output.add(num.getN1().toString());
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
	
	public void visit(ExprAddopRight var) {
//		var.getExpr().childrenAccept(new VisitorAdaptor() {
//			@Override
//			public void visit(DesignatorIdent DesignatorIdent) {
//				objects.push(DesignatorIdent.obj);
//			}
//		});
	}

	
	public void visit(Print printStmt) {
		generateCode();
		if (printStmt.getExpr().struct.getKind() == Tab.charType.getKind()) {
			Code.loadConst(1);
			Code.put(Code.bprint);
		} else {
			Code.loadConst(5);
			Code.put(Code.print);
		}
	}
	
	public void visit(Negative op) {
		addOperatorToOutput("-u");
	}
	
	private boolean isLeftAssociative(String op) {
		if(rightAssociativeOperators.indexOf(op) != -1)
			return false;
		return true;
	}
	
	//todo: add other operators
	private int getPrecendence(String op) {
		if(op.equals("++") || op.equals("--"))
			return 1;
		if(op.equals("(") || op.equals(")"))
			return 1;
		if(op.equals("-u"))
			return 2;
		if(op.equals("*") || op.equals("/") || op.equals("%"))
			return 3;
		if(op.equals("+") || op.equals("-"))
			return 4;
		if(op.equals("*=") || op.equals("/=") || op.equals("%=") || op.equals("+=") || op.equals("-=") || op.equals("="))
			return 14;
		return -1;
		
	}
	
	private void addOperatorToOutput(String op) {
		
		if(op.equals("(")) {
			operators.push(op);
			return;
		}
		
		if(op.equals(")")) {
			while(!operators.lastElement().equals("(")) {
				String currentOp = operators.pop();
				output.add(currentOp);
			}
			
			if(operators.lastElement().equals("(")) {
				operators.pop();
			}
			
			return;
		}
		
		while(!operators.isEmpty() 
				&& !operators.lastElement().equals("(")
				&& (getPrecendence(operators.lastElement()) < getPrecendence(op)
						|| (getPrecendence(operators.lastElement()) == getPrecendence(op)) && isLeftAssociative(op))) {
			output.add(operators.pop());
		}
		
		operators.push(op);
		
	}
	
	public void visit(AddopPls plus) {
		addOperatorToOutput("+");
	}
	
	public void visit(Equal var) {
		addOperatorToOutput("=");
	}
	
	public void visit(AddopMin min) {
		addOperatorToOutput("-");
	}
	
	public void visit(MUL mul) {
		addOperatorToOutput("*");
	}
	
	public void visit(DIV div) {
		addOperatorToOutput("/");
	}
	
	public void visit(MOD mod) {
		addOperatorToOutput("%");
	}
	
	public void visit(PLUSEQ var) {
		addOperatorToOutput("+=");
	}
	
	public void visit(MINUSEQ var) {
		addOperatorToOutput("-=");
	}
	
	public void visit(DIVEQ var) {
		addOperatorToOutput("/=");
	}
	
	public void visit(MODEQ var) {
		addOperatorToOutput("%=");
	}
	
	public void visit(StatementDesignator var) {
		System.out.println(var);
		var.getDesignatorStatement().childrenAccept(new VisitorAdaptor() {
			@Override
			public void visit(DesignatorIdent DesignatorIdent) {
				objects.push(DesignatorIdent.obj);
			}
		});
		generateCode();
	}
	
	public void visit(DesignatorIdent var) {
		//objects.add(var.obj);
		output.add(var.obj.getName());
	}
	
	public void visit(MethodDecl var) {
		Code.put(Code.exit);
		Code.put(Code.return_);
	}
	
	private Obj getObject(String in) {
		try {
			int val = Integer.parseInt(in);
			Obj obj = new Obj(Obj.Con, "", Tab.intType, val, Obj.NO_VALUE);
			return obj;
		}catch(NumberFormatException er) {
			return Tab.find(in);
		}
	}
	
	private boolean isOperator(String in) {
		if(operatorTokens.indexOf(in) != -1) {
			return true;
		}
		return false;
	}
	
	public void visit(Lparent var) {
		addOperatorToOutput("(");
	}
	
	public void visit(Rparent var) {
		addOperatorToOutput(")");
	}
	
	public void generateCode() {
		Stack<Obj> vars = new Stack<Obj>();
		while(!operators.isEmpty()) {
			output.add(operators.pop());
		}
		
		while(!output.isEmpty()) {
			String in = output.poll();
			System.out.println("In je: " + in);
			
			if(!isOperator(in)) {
				Obj obj = getObject(in);
//				Code.load(obj);
				vars.push(obj);
//				if(operators.isEmpty()) {
//					Code.load(obj);
//					return;
//				}
			}
			else {
				if(in.equals("+")) {
					Obj var1 = vars.pop();
					Obj var2 = vars.pop();
					Code.load(var1);
					Code.load(var2);
					Code.put(Code.add);
					int result = var1.getAdr() + var2.getAdr();
					Obj res = new Obj(Obj.Var, "", Tab.intType, result, 1);
					Code.store(res);
					vars.push(res);
				}
				if(in.equals("-u")) {
					Obj var1 = vars.pop();
					Code.load(var1);
					Code.put(Code.neg);
					Obj res = new Obj(Obj.Var, "", Tab.intType);
					Code.store(res);
					vars.push(res);
				}
				if(in.equals("*")) {
					Obj var1 = vars.pop();
					Obj var2 = vars.pop();
					Code.load(var1);
					Code.load(var2);
					Code.put(Code.mul);
					int res = var1.getAdr() * var2.getAdr();
					Obj ress = new Obj(Obj.Var, "", Tab.intType, res, 1);
					Code.store(ress);
					vars.push(ress);
				}
				if(in.equals("/")) {
					Obj var1 = vars.pop();
					Obj var2 = vars.pop();
					Code.load(var1);
					Code.load(var2);
					Code.put(Code.div);
					int result = var1.getAdr() / var2.getAdr();
					Obj res = new Obj(Obj.Var, "", Tab.intType, result, 1);
					Code.store(res);
					vars.push(res);
				}
				if(in.equals("%")) {
					Obj var1 = vars.pop();
					Obj var2 = vars.pop();
					Code.load(var1);
					Code.load(var2);
					Code.put(Code.rem);
					int result = var1.getAdr() % var2.getAdr();
					Obj res = new Obj(Obj.Var, "", Tab.intType, result, 1);
					Code.store(res);
					vars.push(res);
				}
				if(in.equals("-")) {
					Obj var1 = vars.pop();
					Obj var2 = vars.pop();
					Code.load(var2);
					Code.load(var1);
					Code.put(Code.sub);
					int result = var2.getAdr() - var1.getAdr();
					Obj res = new Obj(Obj.Var, "", Tab.intType, result, 1);
					Code.store(res);
					vars.push(res);
				}
				if(in.equals("=")) {
					Obj var1 = vars.pop();
					Obj var2 = vars.pop();
//					Obj obj = Code.
//					Obj obj = objects.pop();
//					Code.put(Code.astore);
				//	Code.put(Code.pop);
					Code.load(var2);
					Code.store(var1);
				}
				if(in.equals("+=")) {
					Obj var1 = vars.pop();
					Obj var2 = vars.pop();
					Code.load(var1);
					Code.load(var2);
					Code.put(Code.add);
					Code.store(var2);
					vars.push(var2);
				}
				if(in.equals("-=")) {
					Obj var1 = vars.pop();
					Obj var2 = vars.pop();
					Code.load(var1);
					Code.load(var2);
					Code.put(Code.sub);
					Code.store(var2);
					vars.push(var2);
				}
				if(in.equals("*=")) {
					Obj var1 = vars.pop();
					Obj var2 = vars.pop();
					Code.load(var1);
					Code.load(var2);
					Code.put(Code.mul);
					Code.store(var2);
					vars.push(var2);
				}
				if(in.equals("/=")) {
					Obj var1 = vars.pop();
					Obj var2 = vars.pop();
					Code.load(var1);
					Code.load(var2);
					Code.put(Code.div);
					Code.store(var2);
					vars.push(var2);
				}
				if(in.equals("%=")) {
					Obj var1 = vars.pop();
					Obj var2 = vars.pop();
					Code.load(var1);
					Code.load(var2);
					Code.put(Code.rem);
					Code.store(var2);
					vars.push(var2);
				}
					
			}
			
		}
		
//		Code.load(vars.pop());
		
	}

}
