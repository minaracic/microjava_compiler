// generated with ast extension for cup
// version 0.8
// 31/4/2020 17:15:43


package rs.ac.bg.etf.pp1.ast;

public class AbstractClassWithMethods extends AbstractClassDecl {

    private String I1;
    private ExtendsDecl ExtendsDecl;
    private VarDeclList VarDeclList;
    private MethodOrAbstractMethodDeclList MethodOrAbstractMethodDeclList;

    public AbstractClassWithMethods (String I1, ExtendsDecl ExtendsDecl, VarDeclList VarDeclList, MethodOrAbstractMethodDeclList MethodOrAbstractMethodDeclList) {
        this.I1=I1;
        this.ExtendsDecl=ExtendsDecl;
        if(ExtendsDecl!=null) ExtendsDecl.setParent(this);
        this.VarDeclList=VarDeclList;
        if(VarDeclList!=null) VarDeclList.setParent(this);
        this.MethodOrAbstractMethodDeclList=MethodOrAbstractMethodDeclList;
        if(MethodOrAbstractMethodDeclList!=null) MethodOrAbstractMethodDeclList.setParent(this);
    }

    public String getI1() {
        return I1;
    }

    public void setI1(String I1) {
        this.I1=I1;
    }

    public ExtendsDecl getExtendsDecl() {
        return ExtendsDecl;
    }

    public void setExtendsDecl(ExtendsDecl ExtendsDecl) {
        this.ExtendsDecl=ExtendsDecl;
    }

    public VarDeclList getVarDeclList() {
        return VarDeclList;
    }

    public void setVarDeclList(VarDeclList VarDeclList) {
        this.VarDeclList=VarDeclList;
    }

    public MethodOrAbstractMethodDeclList getMethodOrAbstractMethodDeclList() {
        return MethodOrAbstractMethodDeclList;
    }

    public void setMethodOrAbstractMethodDeclList(MethodOrAbstractMethodDeclList MethodOrAbstractMethodDeclList) {
        this.MethodOrAbstractMethodDeclList=MethodOrAbstractMethodDeclList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ExtendsDecl!=null) ExtendsDecl.accept(visitor);
        if(VarDeclList!=null) VarDeclList.accept(visitor);
        if(MethodOrAbstractMethodDeclList!=null) MethodOrAbstractMethodDeclList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ExtendsDecl!=null) ExtendsDecl.traverseTopDown(visitor);
        if(VarDeclList!=null) VarDeclList.traverseTopDown(visitor);
        if(MethodOrAbstractMethodDeclList!=null) MethodOrAbstractMethodDeclList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ExtendsDecl!=null) ExtendsDecl.traverseBottomUp(visitor);
        if(VarDeclList!=null) VarDeclList.traverseBottomUp(visitor);
        if(MethodOrAbstractMethodDeclList!=null) MethodOrAbstractMethodDeclList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("AbstractClassWithMethods(\n");

        buffer.append(" "+tab+I1);
        buffer.append("\n");

        if(ExtendsDecl!=null)
            buffer.append(ExtendsDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(VarDeclList!=null)
            buffer.append(VarDeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(MethodOrAbstractMethodDeclList!=null)
            buffer.append(MethodOrAbstractMethodDeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [AbstractClassWithMethods]");
        return buffer.toString();
    }
}
