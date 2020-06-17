// generated with ast extension for cup
// version 0.8
// 17/5/2020 13:34:48


package rs.ac.bg.etf.pp1.ast;

public class DIV extends MulopLeft {

    public DIV () {
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
        buffer.append("DIV(\n");

        buffer.append(tab);
        buffer.append(") [DIV]");
        return buffer.toString();
    }
}
