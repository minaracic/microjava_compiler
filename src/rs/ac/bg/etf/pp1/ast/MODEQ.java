// generated with ast extension for cup
// version 0.8
// 31/4/2020 17:15:43


package rs.ac.bg.etf.pp1.ast;

public class MODEQ extends MulopRight {

    public MODEQ () {
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
        buffer.append("MODEQ(\n");

        buffer.append(tab);
        buffer.append(") [MODEQ]");
        return buffer.toString();
    }
}
