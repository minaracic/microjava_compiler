// generated with ast extension for cup
// version 0.8
// 20/5/2020 13:14:11


package rs.ac.bg.etf.pp1.ast;

public interface Visitor { 

    public void visit(Mulop Mulop);
    public void visit(Relop Relop);
    public void visit(Assignop Assignop);
    public void visit(ExprInParentRPARENT ExprInParentRPARENT);
    public void visit(ExprInParentLPARENT ExprInParentLPARENT);
    public void visit(Equal Equal);
    public void visit(StatementList StatementList);
    public void visit(MethodOrAbstractMethodDeclList MethodOrAbstractMethodDeclList);
    public void visit(Addop Addop);
    public void visit(Factor Factor);
    public void visit(CondTerm CondTerm);
    public void visit(PlusPlus PlusPlus);
    public void visit(DeclList DeclList);
    public void visit(AddopLeft AddopLeft);
    public void visit(Designator Designator);
    public void visit(DesignatorRBRACKET DesignatorRBRACKET);
    public void visit(OptionalExpr OptionalExpr);
    public void visit(Term Term);
    public void visit(FormParsList FormParsList);
    public void visit(Condition Condition);
    public void visit(ConstValue ConstValue);
    public void visit(OptionalBracket OptionalBracket);
    public void visit(ExtendsDecl ExtendsDecl);
    public void visit(AddopRight AddopRight);
    public void visit(OptionalFormPars OptionalFormPars);
    public void visit(ArrayDecl ArrayDecl);
    public void visit(FunctionIdent FunctionIdent);
    public void visit(VarDeclList VarDeclList);
    public void visit(Expr Expr);
    public void visit(OptionalActPars OptionalActPars);
    public void visit(VarDeclListList VarDeclListList);
    public void visit(ActPars ActPars);
    public void visit(TypeOrVoid TypeOrVoid);
    public void visit(AbstractClassDecl AbstractClassDecl);
    public void visit(DesignatorStatement DesignatorStatement);
    public void visit(OptionalCondition OptionalCondition);
    public void visit(ConstValueList ConstValueList);
    public void visit(OptionalDesignatorStatement OptionalDesignatorStatement);
    public void visit(Statement Statement);
    public void visit(VarDecl VarDecl);
    public void visit(MulopLeft MulopLeft);
    public void visit(MinusMinus MinusMinus);
    public void visit(CondFact CondFact);
    public void visit(MethodDeclList MethodDeclList);
    public void visit(Neg Neg);
    public void visit(MulopRight MulopRight);
    public void visit(DesignatorLBRACKET DesignatorLBRACKET);
    public void visit(ClassMethods ClassMethods);
    public void visit(MODEQ MODEQ);
    public void visit(DIVEQ DIVEQ);
    public void visit(MULEQ MULEQ);
    public void visit(MOD MOD);
    public void visit(DIV DIV);
    public void visit(MUL MUL);
    public void visit(RightMulop RightMulop);
    public void visit(LeftMulop LeftMulop);
    public void visit(NoActPars NoActPars);
    public void visit(WithActPars WithActPars);
    public void visit(TermMulop TermMulop);
    public void visit(TermFactor TermFactor);
    public void visit(Negative Negative);
    public void visit(ExprMin ExprMin);
    public void visit(ExprTerm ExprTerm);
    public void visit(ExprAddopLeft ExprAddopLeft);
    public void visit(ExprAddopRight ExprAddopRight);
    public void visit(RBRACKET RBRACKET);
    public void visit(LBRACKET LBRACKET);
    public void visit(DesignatorArr DesignatorArr);
    public void visit(DesignatorAtrib DesignatorAtrib);
    public void visit(DesignatorIdent DesignatorIdent);
    public void visit(Rparent Rparent);
    public void visit(Lparent Lparent);
    public void visit(ExprInParent ExprInParent);
    public void visit(FactorNewType FactorNewType);
    public void visit(FactorNewExpr FactorNewExpr);
    public void visit(FactorConst FactorConst);
    public void visit(FuncCall FuncCall);
    public void visit(FactorDesignator FactorDesignator);
    public void visit(ActParsExpr ActParsExpr);
    public void visit(ActParsList ActParsList);
    public void visit(AssignMulopR AssignMulopR);
    public void visit(AssignAddopR AssignAddopR);
    public void visit(AEqual AEqual);
    public void visit(AddopPls AddopPls);
    public void visit(AddopMin AddopMin);
    public void visit(MINUSEQ MINUSEQ);
    public void visit(PLUSEQ PLUSEQ);
    public void visit(RightAddop RightAddop);
    public void visit(LeftAddop LeftAddop);
    public void visit(RelopSmlEql RelopSmlEql);
    public void visit(RelopSml RelopSml);
    public void visit(RelopGreEql RelopGreEql);
    public void visit(RelopGre RelopGre);
    public void visit(RelopNotEql RelopNotEql);
    public void visit(RelopEql RelopEql);
    public void visit(CondFactRelop CondFactRelop);
    public void visit(CondFactExpr CondFactExpr);
    public void visit(CondTermAnd CondTermAnd);
    public void visit(CondTermFact CondTermFact);
    public void visit(NoExpr NoExpr);
    public void visit(WithExpr WithExpr);
    public void visit(NoCondition NoCondition);
    public void visit(WithCondition WithCondition);
    public void visit(NoDesignatorStatement NoDesignatorStatement);
    public void visit(WithDesignatorStatement WithDesignatorStatement);
    public void visit(ConditionOR ConditionOR);
    public void visit(ConditionA ConditionA);
    public void visit(MinMin MinMin);
    public void visit(PlsPls PlsPls);
    public void visit(Dec Dec);
    public void visit(Inc Inc);
    public void visit(DesignatorFuncCall DesignatorFuncCall);
    public void visit(DesignatorAssign DesignatorAssign);
    public void visit(AbstractMethodDecl AbstractMethodDecl);
    public void visit(Statements Statements);
    public void visit(Print Print);
    public void visit(PrintWithConst PrintWithConst);
    public void visit(Read Read);
    public void visit(Return Return);
    public void visit(Continue Continue);
    public void visit(Break Break);
    public void visit(For For);
    public void visit(Else Else);
    public void visit(IF IF);
    public void visit(StatementDesignator StatementDesignator);
    public void visit(NoStatementList NoStatementList);
    public void visit(WithStatementList WithStatementList);
    public void visit(Type Type);
    public void visit(NoFormPars NoFormPars);
    public void visit(WithFormPars WithFormPars);
    public void visit(TypeOrVoidVoid TypeOrVoidVoid);
    public void visit(TypeOrVoidType TypeOrVoidType);
    public void visit(NoMethodDeclList NoMethodDeclList);
    public void visit(WithMethodDeclList WithMethodDeclList);
    public void visit(MethodsOfClass MethodsOfClass);
    public void visit(ClassDecl ClassDecl);
    public void visit(NoFormParsList NoFormParsList);
    public void visit(WithFormParsList WithFormParsList);
    public void visit(FormPars FormPars);
    public void visit(FunctionVoid FunctionVoid);
    public void visit(FunctionType FunctionType);
    public void visit(MethodDecl MethodDecl);
    public void visit(NoExtendsDecl NoExtendsDecl);
    public void visit(WithExtendsDecl WithExtendsDecl);
    public void visit(NoMethodOrAbstractMethodDeclList NoMethodOrAbstractMethodDeclList);
    public void visit(AbstractList AbstractList);
    public void visit(MethodList MethodList);
    public void visit(AbstractClassWithMethods AbstractClassWithMethods);
    public void visit(AbstractClass AbstractClass);
    public void visit(NoParent NoParent);
    public void visit(WithBracket WithBracket);
    public void visit(NoVarDeclList NoVarDeclList);
    public void visit(WithVarDeclList WithVarDeclList);
    public void visit(ArrayInit ArrayInit);
    public void visit(NoVarDeclListList NoVarDeclListList);
    public void visit(VarDeclListListDerived2 VarDeclListListDerived2);
    public void visit(VarDeclListListDerived1 VarDeclListListDerived1);
    public void visit(VarDeclSingle VarDeclSingle);
    public void visit(VarArray VarArray);
    public void visit(VarDeclDerived2 VarDeclDerived2);
    public void visit(VarDeclDerived1 VarDeclDerived1);
    public void visit(VarDeclArray VarDeclArray);
    public void visit(VarList VarList);
    public void visit(BOOL BOOL);
    public void visit(CHAR CHAR);
    public void visit(NUMBER NUMBER);
    public void visit(ConstVarDecl ConstVarDecl);
    public void visit(NoConstValueList NoConstValueList);
    public void visit(WithConstValueList WithConstValueList);
    public void visit(ConstType ConstType);
    public void visit(ConstDecl ConstDecl);
    public void visit(EQUALOP EQUALOP);
    public void visit(NoDeclList NoDeclList);
    public void visit(DeclListClass DeclListClass);
    public void visit(DeclListAbstractClass DeclListAbstractClass);
    public void visit(DeclListVarDecl DeclListVarDecl);
    public void visit(DeclListConstDecl DeclListConstDecl);
    public void visit(ProgName ProgName);
    public void visit(Program Program);

}
