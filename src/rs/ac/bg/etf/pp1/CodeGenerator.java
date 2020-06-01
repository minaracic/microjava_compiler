package rs.ac.bg.etf.pp1;

import java.util.Stack;

import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.mj.runtime.Code;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;

public class CodeGenerator extends VisitorAdaptor {
	private int mainPC;
	private Stack<Obj> variables = new Stack<Obj>(); 
	private Stack<String> operands = new Stack<String>();
	
	public int getMainPC() {
		return mainPC;
	}
	
	public void visit(NUMBER num) {
		num.obj.setLevel(0);
//		Code.load(num.obj);
		variables.push(num.obj);
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
	
	public void visit(ExprMin var) {
		Code.put(Code.neg);
	}
	
//	public void visit(ExprAdd var) {
//		Addop op = var.getAddop();
//		if(op instanceof LeftAddop) {
//			if(((LeftAddop) op).getAddopLeft() instanceof AddopPls){
//				Code.put(Code.add);
//			}
//			if(((LeftAddop) op).getAddopLeft() instanceof AddopMin){
//				Code.put(Code.sub);
//			}
//		}
//		else {
//			if(((RightAddop) op).getAddopRight() instanceof PLUSEQ){
//				Code.put(Code.add);
//				Code.put(Code.dup);
//				Code.store(variables.pop());
//			}
//			if(((RightAddop) op).getAddopRight() instanceof MINUSEQ){
//				Code.put(Code.sub);
//				Code.put(Code.dup);
//				Code.store(variables.pop());
//			}
//		}
//	}
	
	public void visit(TermMulop var) {
		Code.put(Code.mul);
	}
	
	public void visit(FactorDesignator var) {
	//	variables.push(var.getDesignator().obj);
//		Code.load(var.getDesignator().obj);
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
	
	public void visit(Equal var) {
		operands.push("=");
	}
	
	public void visit(PLUSEQ var) {
		operands.push("+=");
	}
	
	public void visit(MINUSEQ var) {
		operands.push("-=");
	}

	
	public void visit(DesignatorAssign var) {
		Obj desig = var.getDesignator().obj;
		Assignop op = var.getAssignop();
		
//		if(op instanceof Equal) {
//			operands.push("=");
//			//generateCode();
//		}
//		if(op instanceof AssignAddopR) {
//			AddopRight right = ((AssignAddopR) op).getAddopRight();
//			if(right instanceof PLUSEQ) {
//				operands.push("+=");
//			}
//			if(right instanceof MINUSEQ) {
//				operands.push("-=");
//			}
//		}
//		if(op instanceof AssignMulopR) {
//			MulopRight mul = ((AssignMulopR) op).getMulopRight();
//			if(mul instanceof MULEQ) {
//				operands.push("*=");
//			}
//			if(mul instanceof DIVEQ) {
//				operands.push("/=");
//			}
//			if(mul instanceof MODEQ) {
//				operands.push("/=");
//			}
//		}
	
	}
	
	public void visit(StatementDesignator var) {
		generateCode();
	}
	
	public void visit(DesignatorIdent var) {
		variables.push(var.obj);
		
	}
	
	public void visit(MethodDecl var) {
		Code.put(Code.exit);
		Code.put(Code.return_);
	}
	
	public void visit(ExprAddopLeft var) {
//		if(var.getAddopLeft() instanceof AddopPls) {
//			operands.push("+");
//		}
//		if(var.getAddopLeft() instanceof AddopMin) {
//			operands.push("-");
//		}
	}
	
	public void visit(ExprAddopRight var) {
//		if(var.getAddopRight() instanceof PLUSEQ) {
//			operands.push("+=");
//		}
//		if(var.getAddopRight() instanceof MINUSEQ) {
//			operands.push("-=");
//		}
	}
	
	private void codeForEqual() {
		Obj val = variables.pop();
		Obj var = variables.pop();
		Code.load(val);
		Code.store(var);
	}
	
	private void codeForPlusEq() {
		Obj val = variables.pop();
		Obj var = variables.pop();
		Code.load(val);
		Code.load(var);
		Code.put(Code.add);
		Code.store(var);
		variables.push(var);
//		Code.load(var);
	}
	
	private void codeForMinEq() {
		Obj val = variables.pop();
		Obj var = variables.pop();
		Code.load(val);
		Code.load(var);
	}
	
	private void codeForPlus() {
		
	}
	
	private boolean isAssignement(String op) {
		if(op.equals("+") || op.equals("-") || op.equals("*") || op.equals("/") || op.equals("%"))
			return false;
		return true;
	}
	
	public void generateCode() {
		if(operands.empty() && !variables.empty()) {
			Obj obj = variables.pop();
			Code.load(obj);
		}
		while(!operands.isEmpty()) {
			String op = operands.pop();
			
			switch(op) {
				case "=":
					codeForEqual();
				case "+=":
					codeForPlusEq();
				case "-=":
					codeForMinEq();
				case "+":
					codeForPlus();
					
			}
			
			if(operands.empty() && !isAssignement(op)) {
				Obj val = variables.pop();
				Code.load(val);
			}
		}
	}

}
