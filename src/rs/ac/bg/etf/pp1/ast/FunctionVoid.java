// generated with ast extension for cup
// version 0.8
// 1/5/2020 22:50:17


package rs.ac.bg.etf.pp1.ast;

public class FunctionVoid extends FunctionIdent {

    private String funcName;

    public FunctionVoid (String funcName) {
        this.funcName=funcName;
    }

    public String getFuncName() {
        return funcName;
    }

    public void setFuncName(String funcName) {
        this.funcName=funcName;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("FunctionVoid(\n");

        buffer.append(" "+tab+funcName);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [FunctionVoid]");
        return buffer.toString();
    }
}
