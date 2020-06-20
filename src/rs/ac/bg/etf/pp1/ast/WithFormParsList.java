// generated with ast extension for cup
// version 0.8
// 20/5/2020 13:14:11


package rs.ac.bg.etf.pp1.ast;

public class WithFormParsList extends FormParsList {

    private FormParsList FormParsList;
    private Type Type;
    private String I3;
    private OptionalBracket OptionalBracket;

    public WithFormParsList (FormParsList FormParsList, Type Type, String I3, OptionalBracket OptionalBracket) {
        this.FormParsList=FormParsList;
        if(FormParsList!=null) FormParsList.setParent(this);
        this.Type=Type;
        if(Type!=null) Type.setParent(this);
        this.I3=I3;
        this.OptionalBracket=OptionalBracket;
        if(OptionalBracket!=null) OptionalBracket.setParent(this);
    }

    public FormParsList getFormParsList() {
        return FormParsList;
    }

    public void setFormParsList(FormParsList FormParsList) {
        this.FormParsList=FormParsList;
    }

    public Type getType() {
        return Type;
    }

    public void setType(Type Type) {
        this.Type=Type;
    }

    public String getI3() {
        return I3;
    }

    public void setI3(String I3) {
        this.I3=I3;
    }

    public OptionalBracket getOptionalBracket() {
        return OptionalBracket;
    }

    public void setOptionalBracket(OptionalBracket OptionalBracket) {
        this.OptionalBracket=OptionalBracket;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(FormParsList!=null) FormParsList.accept(visitor);
        if(Type!=null) Type.accept(visitor);
        if(OptionalBracket!=null) OptionalBracket.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(FormParsList!=null) FormParsList.traverseTopDown(visitor);
        if(Type!=null) Type.traverseTopDown(visitor);
        if(OptionalBracket!=null) OptionalBracket.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(FormParsList!=null) FormParsList.traverseBottomUp(visitor);
        if(Type!=null) Type.traverseBottomUp(visitor);
        if(OptionalBracket!=null) OptionalBracket.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("WithFormParsList(\n");

        if(FormParsList!=null)
            buffer.append(FormParsList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Type!=null)
            buffer.append(Type.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(" "+tab+I3);
        buffer.append("\n");

        if(OptionalBracket!=null)
            buffer.append(OptionalBracket.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [WithFormParsList]");
        return buffer.toString();
    }
}
