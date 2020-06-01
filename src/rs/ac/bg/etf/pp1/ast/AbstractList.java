// generated with ast extension for cup
// version 0.8
// 1/5/2020 22:45:30


package rs.ac.bg.etf.pp1.ast;

public class AbstractList extends MethodOrAbstractMethodDeclList {

    private MethodOrAbstractMethodDeclList MethodOrAbstractMethodDeclList;
    private AbstractMethodDecl AbstractMethodDecl;

    public AbstractList (MethodOrAbstractMethodDeclList MethodOrAbstractMethodDeclList, AbstractMethodDecl AbstractMethodDecl) {
        this.MethodOrAbstractMethodDeclList=MethodOrAbstractMethodDeclList;
        if(MethodOrAbstractMethodDeclList!=null) MethodOrAbstractMethodDeclList.setParent(this);
        this.AbstractMethodDecl=AbstractMethodDecl;
        if(AbstractMethodDecl!=null) AbstractMethodDecl.setParent(this);
    }

    public MethodOrAbstractMethodDeclList getMethodOrAbstractMethodDeclList() {
        return MethodOrAbstractMethodDeclList;
    }

    public void setMethodOrAbstractMethodDeclList(MethodOrAbstractMethodDeclList MethodOrAbstractMethodDeclList) {
        this.MethodOrAbstractMethodDeclList=MethodOrAbstractMethodDeclList;
    }

    public AbstractMethodDecl getAbstractMethodDecl() {
        return AbstractMethodDecl;
    }

    public void setAbstractMethodDecl(AbstractMethodDecl AbstractMethodDecl) {
        this.AbstractMethodDecl=AbstractMethodDecl;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(MethodOrAbstractMethodDeclList!=null) MethodOrAbstractMethodDeclList.accept(visitor);
        if(AbstractMethodDecl!=null) AbstractMethodDecl.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(MethodOrAbstractMethodDeclList!=null) MethodOrAbstractMethodDeclList.traverseTopDown(visitor);
        if(AbstractMethodDecl!=null) AbstractMethodDecl.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(MethodOrAbstractMethodDeclList!=null) MethodOrAbstractMethodDeclList.traverseBottomUp(visitor);
        if(AbstractMethodDecl!=null) AbstractMethodDecl.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("AbstractList(\n");

        if(MethodOrAbstractMethodDeclList!=null)
            buffer.append(MethodOrAbstractMethodDeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(AbstractMethodDecl!=null)
            buffer.append(AbstractMethodDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [AbstractList]");
        return buffer.toString();
    }
}
