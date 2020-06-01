// generated with ast extension for cup
// version 0.8
// 1/5/2020 22:45:30


package rs.ac.bg.etf.pp1.ast;

public class RelopSml extends Relop {

    public RelopSml () {
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
        buffer.append("RelopSml(\n");

        buffer.append(tab);
        buffer.append(") [RelopSml]");
        return buffer.toString();
    }
}
