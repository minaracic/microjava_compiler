// generated with ast extension for cup
// version 0.8
// 31/4/2020 17:15:43


package rs.ac.bg.etf.pp1.ast;

public class Return extends Statement {

    private OptionalExpr OptionalExpr;

    public Return (OptionalExpr OptionalExpr) {
        this.OptionalExpr=OptionalExpr;
        if(OptionalExpr!=null) OptionalExpr.setParent(this);
    }

    public OptionalExpr getOptionalExpr() {
        return OptionalExpr;
    }

    public void setOptionalExpr(OptionalExpr OptionalExpr) {
        this.OptionalExpr=OptionalExpr;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(OptionalExpr!=null) OptionalExpr.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(OptionalExpr!=null) OptionalExpr.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(OptionalExpr!=null) OptionalExpr.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Return(\n");

        if(OptionalExpr!=null)
            buffer.append(OptionalExpr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Return]");
        return buffer.toString();
    }
}