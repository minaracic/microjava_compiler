// generated with ast extension for cup
// version 0.8
// 23/5/2020 11:23:51


package rs.ac.bg.etf.pp1.ast;

public class Dec extends DesignatorStatement {

    private Designator Designator;
    private MinusMinus MinusMinus;

    public Dec (Designator Designator, MinusMinus MinusMinus) {
        this.Designator=Designator;
        if(Designator!=null) Designator.setParent(this);
        this.MinusMinus=MinusMinus;
        if(MinusMinus!=null) MinusMinus.setParent(this);
    }

    public Designator getDesignator() {
        return Designator;
    }

    public void setDesignator(Designator Designator) {
        this.Designator=Designator;
    }

    public MinusMinus getMinusMinus() {
        return MinusMinus;
    }

    public void setMinusMinus(MinusMinus MinusMinus) {
        this.MinusMinus=MinusMinus;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Designator!=null) Designator.accept(visitor);
        if(MinusMinus!=null) MinusMinus.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Designator!=null) Designator.traverseTopDown(visitor);
        if(MinusMinus!=null) MinusMinus.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Designator!=null) Designator.traverseBottomUp(visitor);
        if(MinusMinus!=null) MinusMinus.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Dec(\n");

        if(Designator!=null)
            buffer.append(Designator.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(MinusMinus!=null)
            buffer.append(MinusMinus.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Dec]");
        return buffer.toString();
    }
}
