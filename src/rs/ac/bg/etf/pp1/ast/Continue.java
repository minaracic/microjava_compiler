// generated with ast extension for cup
// version 0.8
// 23/5/2020 11:23:51


package rs.ac.bg.etf.pp1.ast;

public class Continue extends Statement {

    public Continue () {
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
        buffer.append("Continue(\n");

        buffer.append(tab);
        buffer.append(") [Continue]");
        return buffer.toString();
    }
}
