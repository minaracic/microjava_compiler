// generated with ast extension for cup
// version 0.8
// 31/4/2020 17:15:43


package rs.ac.bg.etf.pp1.ast;

public class For extends Statement {

    private OptionalDesignatorStatement OptionalDesignatorStatement;
    private OptionalCondition OptionalCondition;
    private OptionalDesignatorStatement OptionalDesignatorStatement1;
    private Statement Statement;

    public For (OptionalDesignatorStatement OptionalDesignatorStatement, OptionalCondition OptionalCondition, OptionalDesignatorStatement OptionalDesignatorStatement1, Statement Statement) {
        this.OptionalDesignatorStatement=OptionalDesignatorStatement;
        if(OptionalDesignatorStatement!=null) OptionalDesignatorStatement.setParent(this);
        this.OptionalCondition=OptionalCondition;
        if(OptionalCondition!=null) OptionalCondition.setParent(this);
        this.OptionalDesignatorStatement1=OptionalDesignatorStatement1;
        if(OptionalDesignatorStatement1!=null) OptionalDesignatorStatement1.setParent(this);
        this.Statement=Statement;
        if(Statement!=null) Statement.setParent(this);
    }

    public OptionalDesignatorStatement getOptionalDesignatorStatement() {
        return OptionalDesignatorStatement;
    }

    public void setOptionalDesignatorStatement(OptionalDesignatorStatement OptionalDesignatorStatement) {
        this.OptionalDesignatorStatement=OptionalDesignatorStatement;
    }

    public OptionalCondition getOptionalCondition() {
        return OptionalCondition;
    }

    public void setOptionalCondition(OptionalCondition OptionalCondition) {
        this.OptionalCondition=OptionalCondition;
    }

    public OptionalDesignatorStatement getOptionalDesignatorStatement1() {
        return OptionalDesignatorStatement1;
    }

    public void setOptionalDesignatorStatement1(OptionalDesignatorStatement OptionalDesignatorStatement1) {
        this.OptionalDesignatorStatement1=OptionalDesignatorStatement1;
    }

    public Statement getStatement() {
        return Statement;
    }

    public void setStatement(Statement Statement) {
        this.Statement=Statement;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(OptionalDesignatorStatement!=null) OptionalDesignatorStatement.accept(visitor);
        if(OptionalCondition!=null) OptionalCondition.accept(visitor);
        if(OptionalDesignatorStatement1!=null) OptionalDesignatorStatement1.accept(visitor);
        if(Statement!=null) Statement.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(OptionalDesignatorStatement!=null) OptionalDesignatorStatement.traverseTopDown(visitor);
        if(OptionalCondition!=null) OptionalCondition.traverseTopDown(visitor);
        if(OptionalDesignatorStatement1!=null) OptionalDesignatorStatement1.traverseTopDown(visitor);
        if(Statement!=null) Statement.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(OptionalDesignatorStatement!=null) OptionalDesignatorStatement.traverseBottomUp(visitor);
        if(OptionalCondition!=null) OptionalCondition.traverseBottomUp(visitor);
        if(OptionalDesignatorStatement1!=null) OptionalDesignatorStatement1.traverseBottomUp(visitor);
        if(Statement!=null) Statement.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("For(\n");

        if(OptionalDesignatorStatement!=null)
            buffer.append(OptionalDesignatorStatement.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(OptionalCondition!=null)
            buffer.append(OptionalCondition.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(OptionalDesignatorStatement1!=null)
            buffer.append(OptionalDesignatorStatement1.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Statement!=null)
            buffer.append(Statement.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [For]");
        return buffer.toString();
    }
}
