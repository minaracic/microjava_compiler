// generated with ast extension for cup
// version 0.8
// 1/5/2020 22:45:30


package rs.ac.bg.etf.pp1.ast;

public class ExprInParent extends Factor {

    private ExprInParentLPARENT ExprInParentLPARENT;
    private Expr Expr;
    private ExprInParentRPARENT ExprInParentRPARENT;

    public ExprInParent (ExprInParentLPARENT ExprInParentLPARENT, Expr Expr, ExprInParentRPARENT ExprInParentRPARENT) {
        this.ExprInParentLPARENT=ExprInParentLPARENT;
        if(ExprInParentLPARENT!=null) ExprInParentLPARENT.setParent(this);
        this.Expr=Expr;
        if(Expr!=null) Expr.setParent(this);
        this.ExprInParentRPARENT=ExprInParentRPARENT;
        if(ExprInParentRPARENT!=null) ExprInParentRPARENT.setParent(this);
    }

    public ExprInParentLPARENT getExprInParentLPARENT() {
        return ExprInParentLPARENT;
    }

    public void setExprInParentLPARENT(ExprInParentLPARENT ExprInParentLPARENT) {
        this.ExprInParentLPARENT=ExprInParentLPARENT;
    }

    public Expr getExpr() {
        return Expr;
    }

    public void setExpr(Expr Expr) {
        this.Expr=Expr;
    }

    public ExprInParentRPARENT getExprInParentRPARENT() {
        return ExprInParentRPARENT;
    }

    public void setExprInParentRPARENT(ExprInParentRPARENT ExprInParentRPARENT) {
        this.ExprInParentRPARENT=ExprInParentRPARENT;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ExprInParentLPARENT!=null) ExprInParentLPARENT.accept(visitor);
        if(Expr!=null) Expr.accept(visitor);
        if(ExprInParentRPARENT!=null) ExprInParentRPARENT.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ExprInParentLPARENT!=null) ExprInParentLPARENT.traverseTopDown(visitor);
        if(Expr!=null) Expr.traverseTopDown(visitor);
        if(ExprInParentRPARENT!=null) ExprInParentRPARENT.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ExprInParentLPARENT!=null) ExprInParentLPARENT.traverseBottomUp(visitor);
        if(Expr!=null) Expr.traverseBottomUp(visitor);
        if(ExprInParentRPARENT!=null) ExprInParentRPARENT.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ExprInParent(\n");

        if(ExprInParentLPARENT!=null)
            buffer.append(ExprInParentLPARENT.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Expr!=null)
            buffer.append(Expr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ExprInParentRPARENT!=null)
            buffer.append(ExprInParentRPARENT.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ExprInParent]");
        return buffer.toString();
    }
}
