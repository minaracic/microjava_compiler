// generated with ast extension for cup
// version 0.8
// 21/5/2020 14:8:38


package rs.ac.bg.etf.pp1.ast;

public class ConstVarDecl implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private ConstVarDeclIdent ConstVarDeclIdent;
    private Equal Equal;
    private ConstValue ConstValue;

    public ConstVarDecl (ConstVarDeclIdent ConstVarDeclIdent, Equal Equal, ConstValue ConstValue) {
        this.ConstVarDeclIdent=ConstVarDeclIdent;
        if(ConstVarDeclIdent!=null) ConstVarDeclIdent.setParent(this);
        this.Equal=Equal;
        if(Equal!=null) Equal.setParent(this);
        this.ConstValue=ConstValue;
        if(ConstValue!=null) ConstValue.setParent(this);
    }

    public ConstVarDeclIdent getConstVarDeclIdent() {
        return ConstVarDeclIdent;
    }

    public void setConstVarDeclIdent(ConstVarDeclIdent ConstVarDeclIdent) {
        this.ConstVarDeclIdent=ConstVarDeclIdent;
    }

    public Equal getEqual() {
        return Equal;
    }

    public void setEqual(Equal Equal) {
        this.Equal=Equal;
    }

    public ConstValue getConstValue() {
        return ConstValue;
    }

    public void setConstValue(ConstValue ConstValue) {
        this.ConstValue=ConstValue;
    }

    public SyntaxNode getParent() {
        return parent;
    }

    public void setParent(SyntaxNode parent) {
        this.parent=parent;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line=line;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ConstVarDeclIdent!=null) ConstVarDeclIdent.accept(visitor);
        if(Equal!=null) Equal.accept(visitor);
        if(ConstValue!=null) ConstValue.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ConstVarDeclIdent!=null) ConstVarDeclIdent.traverseTopDown(visitor);
        if(Equal!=null) Equal.traverseTopDown(visitor);
        if(ConstValue!=null) ConstValue.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ConstVarDeclIdent!=null) ConstVarDeclIdent.traverseBottomUp(visitor);
        if(Equal!=null) Equal.traverseBottomUp(visitor);
        if(ConstValue!=null) ConstValue.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ConstVarDecl(\n");

        if(ConstVarDeclIdent!=null)
            buffer.append(ConstVarDeclIdent.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Equal!=null)
            buffer.append(Equal.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ConstValue!=null)
            buffer.append(ConstValue.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConstVarDecl]");
        return buffer.toString();
    }
}
