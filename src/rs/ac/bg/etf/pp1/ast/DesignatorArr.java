// generated with ast extension for cup
// version 0.8
// 20/5/2020 13:14:11


package rs.ac.bg.etf.pp1.ast;

public class DesignatorArr extends Designator {

    private Designator Designator;
    private DesignatorLBRACKET DesignatorLBRACKET;
    private Expr Expr;
    private DesignatorRBRACKET DesignatorRBRACKET;

    public DesignatorArr (Designator Designator, DesignatorLBRACKET DesignatorLBRACKET, Expr Expr, DesignatorRBRACKET DesignatorRBRACKET) {
        this.Designator=Designator;
        if(Designator!=null) Designator.setParent(this);
        this.DesignatorLBRACKET=DesignatorLBRACKET;
        if(DesignatorLBRACKET!=null) DesignatorLBRACKET.setParent(this);
        this.Expr=Expr;
        if(Expr!=null) Expr.setParent(this);
        this.DesignatorRBRACKET=DesignatorRBRACKET;
        if(DesignatorRBRACKET!=null) DesignatorRBRACKET.setParent(this);
    }

    public Designator getDesignator() {
        return Designator;
    }

    public void setDesignator(Designator Designator) {
        this.Designator=Designator;
    }

    public DesignatorLBRACKET getDesignatorLBRACKET() {
        return DesignatorLBRACKET;
    }

    public void setDesignatorLBRACKET(DesignatorLBRACKET DesignatorLBRACKET) {
        this.DesignatorLBRACKET=DesignatorLBRACKET;
    }

    public Expr getExpr() {
        return Expr;
    }

    public void setExpr(Expr Expr) {
        this.Expr=Expr;
    }

    public DesignatorRBRACKET getDesignatorRBRACKET() {
        return DesignatorRBRACKET;
    }

    public void setDesignatorRBRACKET(DesignatorRBRACKET DesignatorRBRACKET) {
        this.DesignatorRBRACKET=DesignatorRBRACKET;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Designator!=null) Designator.accept(visitor);
        if(DesignatorLBRACKET!=null) DesignatorLBRACKET.accept(visitor);
        if(Expr!=null) Expr.accept(visitor);
        if(DesignatorRBRACKET!=null) DesignatorRBRACKET.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Designator!=null) Designator.traverseTopDown(visitor);
        if(DesignatorLBRACKET!=null) DesignatorLBRACKET.traverseTopDown(visitor);
        if(Expr!=null) Expr.traverseTopDown(visitor);
        if(DesignatorRBRACKET!=null) DesignatorRBRACKET.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Designator!=null) Designator.traverseBottomUp(visitor);
        if(DesignatorLBRACKET!=null) DesignatorLBRACKET.traverseBottomUp(visitor);
        if(Expr!=null) Expr.traverseBottomUp(visitor);
        if(DesignatorRBRACKET!=null) DesignatorRBRACKET.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DesignatorArr(\n");

        if(Designator!=null)
            buffer.append(Designator.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(DesignatorLBRACKET!=null)
            buffer.append(DesignatorLBRACKET.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Expr!=null)
            buffer.append(Expr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(DesignatorRBRACKET!=null)
            buffer.append(DesignatorRBRACKET.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesignatorArr]");
        return buffer.toString();
    }
}
