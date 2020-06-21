// generated with ast extension for cup
// version 0.8
// 21/5/2020 14:8:38


package rs.ac.bg.etf.pp1.ast;

public class WithConstValueList extends ConstValueList {

    private ConstValueList ConstValueList;
    private ConstVarDecl ConstVarDecl;

    public WithConstValueList (ConstValueList ConstValueList, ConstVarDecl ConstVarDecl) {
        this.ConstValueList=ConstValueList;
        if(ConstValueList!=null) ConstValueList.setParent(this);
        this.ConstVarDecl=ConstVarDecl;
        if(ConstVarDecl!=null) ConstVarDecl.setParent(this);
    }

    public ConstValueList getConstValueList() {
        return ConstValueList;
    }

    public void setConstValueList(ConstValueList ConstValueList) {
        this.ConstValueList=ConstValueList;
    }

    public ConstVarDecl getConstVarDecl() {
        return ConstVarDecl;
    }

    public void setConstVarDecl(ConstVarDecl ConstVarDecl) {
        this.ConstVarDecl=ConstVarDecl;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ConstValueList!=null) ConstValueList.accept(visitor);
        if(ConstVarDecl!=null) ConstVarDecl.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ConstValueList!=null) ConstValueList.traverseTopDown(visitor);
        if(ConstVarDecl!=null) ConstVarDecl.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ConstValueList!=null) ConstValueList.traverseBottomUp(visitor);
        if(ConstVarDecl!=null) ConstVarDecl.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("WithConstValueList(\n");

        if(ConstValueList!=null)
            buffer.append(ConstValueList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ConstVarDecl!=null)
            buffer.append(ConstVarDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [WithConstValueList]");
        return buffer.toString();
    }
}
