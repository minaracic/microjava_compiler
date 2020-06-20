// generated with ast extension for cup
// version 0.8
// 20/5/2020 13:14:11


package rs.ac.bg.etf.pp1.ast;

public class AddopPls extends AddopLeft {

    public AddopPls () {
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
        buffer.append("AddopPls(\n");

        buffer.append(tab);
        buffer.append(") [AddopPls]");
        return buffer.toString();
    }
}
