// generated with ast extension for cup
// version 0.8
// 23/5/2020 11:23:51


package rs.ac.bg.etf.pp1.ast;

public class NoDesignatorStatement extends OptionalDesignatorStatement {

    public NoDesignatorStatement () {
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
        buffer.append("NoDesignatorStatement(\n");

        buffer.append(tab);
        buffer.append(") [NoDesignatorStatement]");
        return buffer.toString();
    }
}
