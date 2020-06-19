// generated with ast extension for cup
// version 0.8
// 19/5/2020 23:21:54


package rs.ac.bg.etf.pp1.ast;

public class Inc extends DesignatorStatement {

    private Designator Designator;
    private PlusPlus PlusPlus;

    public Inc (Designator Designator, PlusPlus PlusPlus) {
        this.Designator=Designator;
        if(Designator!=null) Designator.setParent(this);
        this.PlusPlus=PlusPlus;
        if(PlusPlus!=null) PlusPlus.setParent(this);
    }

    public Designator getDesignator() {
        return Designator;
    }

    public void setDesignator(Designator Designator) {
        this.Designator=Designator;
    }

    public PlusPlus getPlusPlus() {
        return PlusPlus;
    }

    public void setPlusPlus(PlusPlus PlusPlus) {
        this.PlusPlus=PlusPlus;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Designator!=null) Designator.accept(visitor);
        if(PlusPlus!=null) PlusPlus.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Designator!=null) Designator.traverseTopDown(visitor);
        if(PlusPlus!=null) PlusPlus.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Designator!=null) Designator.traverseBottomUp(visitor);
        if(PlusPlus!=null) PlusPlus.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Inc(\n");

        if(Designator!=null)
            buffer.append(Designator.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(PlusPlus!=null)
            buffer.append(PlusPlus.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Inc]");
        return buffer.toString();
    }
}
