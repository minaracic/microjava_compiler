// generated with ast extension for cup
// version 0.8
// 1/5/2020 22:50:17


package rs.ac.bg.etf.pp1.ast;

public class ExprMin extends Expr {

    private Neg Neg;
    private Term Term;

    public ExprMin (Neg Neg, Term Term) {
        this.Neg=Neg;
        if(Neg!=null) Neg.setParent(this);
        this.Term=Term;
        if(Term!=null) Term.setParent(this);
    }

    public Neg getNeg() {
        return Neg;
    }

    public void setNeg(Neg Neg) {
        this.Neg=Neg;
    }

    public Term getTerm() {
        return Term;
    }

    public void setTerm(Term Term) {
        this.Term=Term;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Neg!=null) Neg.accept(visitor);
        if(Term!=null) Term.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Neg!=null) Neg.traverseTopDown(visitor);
        if(Term!=null) Term.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Neg!=null) Neg.traverseBottomUp(visitor);
        if(Term!=null) Term.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ExprMin(\n");

        if(Neg!=null)
            buffer.append(Neg.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Term!=null)
            buffer.append(Term.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ExprMin]");
        return buffer.toString();
    }
}
