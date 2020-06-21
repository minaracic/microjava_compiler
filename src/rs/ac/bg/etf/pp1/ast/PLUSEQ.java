// generated with ast extension for cup
// version 0.8
// 21/5/2020 14:8:38


package rs.ac.bg.etf.pp1.ast;

public class PLUSEQ extends AddopRight {

    public PLUSEQ () {
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
        buffer.append("PLUSEQ(\n");

        buffer.append(tab);
        buffer.append(") [PLUSEQ]");
        return buffer.toString();
    }
}
