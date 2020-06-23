// generated with ast extension for cup
// version 0.8
// 23/5/2020 11:23:51


package rs.ac.bg.etf.pp1.ast;

public class VarList extends VarDecl {

    private ConstType ConstType;
    private String varName;
    private VarDeclListList VarDeclListList;

    public VarList (ConstType ConstType, String varName, VarDeclListList VarDeclListList) {
        this.ConstType=ConstType;
        if(ConstType!=null) ConstType.setParent(this);
        this.varName=varName;
        this.VarDeclListList=VarDeclListList;
        if(VarDeclListList!=null) VarDeclListList.setParent(this);
    }

    public ConstType getConstType() {
        return ConstType;
    }

    public void setConstType(ConstType ConstType) {
        this.ConstType=ConstType;
    }

    public String getVarName() {
        return varName;
    }

    public void setVarName(String varName) {
        this.varName=varName;
    }

    public VarDeclListList getVarDeclListList() {
        return VarDeclListList;
    }

    public void setVarDeclListList(VarDeclListList VarDeclListList) {
        this.VarDeclListList=VarDeclListList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ConstType!=null) ConstType.accept(visitor);
        if(VarDeclListList!=null) VarDeclListList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ConstType!=null) ConstType.traverseTopDown(visitor);
        if(VarDeclListList!=null) VarDeclListList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ConstType!=null) ConstType.traverseBottomUp(visitor);
        if(VarDeclListList!=null) VarDeclListList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("VarList(\n");

        if(ConstType!=null)
            buffer.append(ConstType.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(" "+tab+varName);
        buffer.append("\n");

        if(VarDeclListList!=null)
            buffer.append(VarDeclListList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [VarList]");
        return buffer.toString();
    }
}
