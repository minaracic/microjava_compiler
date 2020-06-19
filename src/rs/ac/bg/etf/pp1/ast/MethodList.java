// generated with ast extension for cup
// version 0.8
// 19/5/2020 23:21:54


package rs.ac.bg.etf.pp1.ast;

public class MethodList extends MethodOrAbstractMethodDeclList {

    private MethodOrAbstractMethodDeclList MethodOrAbstractMethodDeclList;
    private MethodDecl MethodDecl;

    public MethodList (MethodOrAbstractMethodDeclList MethodOrAbstractMethodDeclList, MethodDecl MethodDecl) {
        this.MethodOrAbstractMethodDeclList=MethodOrAbstractMethodDeclList;
        if(MethodOrAbstractMethodDeclList!=null) MethodOrAbstractMethodDeclList.setParent(this);
        this.MethodDecl=MethodDecl;
        if(MethodDecl!=null) MethodDecl.setParent(this);
    }

    public MethodOrAbstractMethodDeclList getMethodOrAbstractMethodDeclList() {
        return MethodOrAbstractMethodDeclList;
    }

    public void setMethodOrAbstractMethodDeclList(MethodOrAbstractMethodDeclList MethodOrAbstractMethodDeclList) {
        this.MethodOrAbstractMethodDeclList=MethodOrAbstractMethodDeclList;
    }

    public MethodDecl getMethodDecl() {
        return MethodDecl;
    }

    public void setMethodDecl(MethodDecl MethodDecl) {
        this.MethodDecl=MethodDecl;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(MethodOrAbstractMethodDeclList!=null) MethodOrAbstractMethodDeclList.accept(visitor);
        if(MethodDecl!=null) MethodDecl.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(MethodOrAbstractMethodDeclList!=null) MethodOrAbstractMethodDeclList.traverseTopDown(visitor);
        if(MethodDecl!=null) MethodDecl.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(MethodOrAbstractMethodDeclList!=null) MethodOrAbstractMethodDeclList.traverseBottomUp(visitor);
        if(MethodDecl!=null) MethodDecl.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MethodList(\n");

        if(MethodOrAbstractMethodDeclList!=null)
            buffer.append(MethodOrAbstractMethodDeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(MethodDecl!=null)
            buffer.append(MethodDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MethodList]");
        return buffer.toString();
    }
}
