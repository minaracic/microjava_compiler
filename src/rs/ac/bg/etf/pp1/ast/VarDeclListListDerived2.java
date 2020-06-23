// generated with ast extension for cup
// version 0.8
// 23/5/2020 11:23:51


package rs.ac.bg.etf.pp1.ast;

public class VarDeclListListDerived2 extends VarDeclListList {

    public VarDeclListListDerived2 () {
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
        buffer.append("VarDeclListListDerived2(\n");

        buffer.append(tab);
        buffer.append(") [VarDeclListListDerived2]");
        return buffer.toString();
    }
}
