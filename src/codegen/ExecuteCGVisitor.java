package codegen;

import ast.Program;
import ast.definition.Definition;
import ast.definition.FunctionDefinition;
import ast.definition.VariableDefinition;
import ast.expression.Invocation;
import ast.statement.*;
import ast.type.FunctionType;
import ast.type.IntType;
import ast.type.VoidType;

import java.util.List;

public class ExecuteCGVisitor extends AbstractCGVisitor<FunctionDefinition,Void> {

    private AddressCGVisitor addressCGVisitor;
    private ValueCGVisitor valueCGVisitor;

    public ExecuteCGVisitor(CodeGenerator codeGenerator) {
        this.cg = codeGenerator;
        //---
        this.addressCGVisitor = new AddressCGVisitor(this.cg);
        this.valueCGVisitor = new ValueCGVisitor(this.cg);
        //---
        this.addressCGVisitor.setValueCGVisitor(this.valueCGVisitor);
        this.valueCGVisitor.setAddressCGVisitor(this.addressCGVisitor);
    }

    /**
     * void execute[[Program: program -> definitions*]]() =
     *      for(Definition d : definitions*)
     *          if(d instanceOf VariableDefinition)
     *              execute[[d]]()
     *      <call main>
     *      <halt>
     *      for(Definition d : definitions*)
     *                if(d instanceOf FunctionDefinition)
     *                    execute[[d]]()
     */
    @Override
    public Void visit(Program e, FunctionDefinition param) {
        for(Definition d : e.getDefinitions()){
            if(d instanceof VariableDefinition){
                d.accept(this,null);
            }
        }
        cg.mainInvocation();
        for(Definition d : e.getDefinitions()){
            if(d instanceof FunctionDefinition){
                d.accept(this,null);
            }
        }
        return null;
    }

    /**
     * void execute[[FunctionDefinition: definition -> type ID varDef* statement*]]() =
     *     ID <:>
     *     for(VarDef param : type.params)
     *         execute[[param]]()
     *     for(VarDef local : varDef*)
     *         execute[[local]]()
     *     <enter> definition.localBytesSum
     *     for(VarDef s : statement*)
     *         execute[[s]]()
     *     <ret> type.returnValue.getNumberOfBytes, definition.localBytesSum, definition.paramBytesSum
     */
    @Override
    public Void visit(FunctionDefinition e, FunctionDefinition param) {
        cg.line(e.getLine());
        FunctionType functionType = (FunctionType) e.getType();
        cg.functionID(e.getName()); // etiqueta
        cg.comment("* Parameters");
        List<VariableDefinition> params = functionType.getParameters();
        for (int i = params.size() - 1; i >= 0; i--) {
            params.get(i).accept(this, null);
        }
        cg.comment("* Local variables");
        for(Statement funcLine : e.getFuncBody()){
            if(funcLine instanceof VariableDefinition)
                funcLine.accept(this,null);
        }
        cg.enter(e.getLocalBytesSum());
        for(Statement funcLine : e.getFuncBody()){
            if(!(funcLine instanceof VariableDefinition))
                funcLine.accept(this,e);
        }
        if(functionType.getReturnType() instanceof VoidType){
            cg.ret(0, e.getLocalBytesSum(), e.getParamBytesSum());
        }
        return null;
    }

    /**
     * void execute[[Return: statement -> expression]](FunctionDefinition def)=
     *              value[[expression]]()
     *              cg.convertTo(expression.type,def.type.returnType)
     *              <ret> def.type.returnType.numberOfBytes, def.localBytesSum, def.paramBytesSum
     */
    @Override
    public Void visit(Return e, FunctionDefinition param){
        cg.line(e.getLine());
        cg.comment("* Return");
        e.getReturnValue().accept(this.valueCGVisitor,null);
        FunctionType functionType = (FunctionType) param.getType();
        cg.convertTo(e.getReturnValue().getType(), functionType.getReturnType());
        cg.ret(functionType.getReturnType().getNumberOfBytes(), param.getLocalBytesSum(), param.getParamBytesSum());
        return null;
    }

    /**
     * void execute[[VariableDefinition: definition -> type ID]]() =
     *     ' <*> type.toString ID <(offset> definition.offset <)>
     */
    @Override
    public Void visit(VariableDefinition e, FunctionDefinition param){
        cg.comment("* " + e.getType() + " " + e.getName() + " (offset " + e.getOffset() + ")");
        return null;
    }

    /**
     * void execute[[Assignment: statement -> expr1 expr2]]() =
     *      address[[expr1]]()
     *      value[[expr2]]()
     *      cg.convertTo(expr2.type,expr1.type)
     *      <store> expr1.type.suffix
     */
    @Override
    public Void visit(Assignment e, FunctionDefinition param) {
        cg.line(e.getLine());
        cg.comment("* Assignment");
        e.getLeft().accept(this.addressCGVisitor,null);
        e.getRigth().accept(this.valueCGVisitor,null);
        cg.convertTo(e.getRigth().getType(),e.getLeft().getType());
        cg.store(e.getLeft().getType());
        return null;
    }

    /**
     * void execute[[Input: statement -> expression]]() =
     *     address[[expression]]()
     *     <in> expression.type.suffix
     *     <store> expression.type.suffix
     */
    @Override
    public Void visit(Input e,  FunctionDefinition param) {
        cg.line(e.getLine());
        cg.comment("* Read");
        e.getParameter().accept(this.addressCGVisitor,null);
        cg.in(e.getParameter().getType());
        cg.store(e.getParameter().getType());
        return null;
    }

    /**
     * void execute[[Log: statement -> expression]]() =
     *   value[[expression]]()
     *   <out> expression.type.suffix
     */
    @Override
    public Void visit(Log e, FunctionDefinition param) {
        cg.line(e.getLine());
        cg.comment("* Write");
        e.getParameter().accept(this.valueCGVisitor,null);
        cg.out(e.getParameter().getType());
        return null;
    }

    /**
     * execute[[While: statement1 -> expression statement2*]]() =
     *      String cond = cg.getLabel()
     *      String end = cg.getLabel()
     *      cond <:>
     *      value[[expression]]()
     *      cg.convertTo(expression.type,IntType)
     *      <jz> end
     *      statement2*.forEach(statement -> execute[[statement]]())
     *      <jmp> cond
     *      end <:>
     */
    @Override
    public Void visit(While e, FunctionDefinition param){
        cg.line(e.getLine());
        cg.comment("* While");
        String cond = cg.getLabel();
        String end = cg.getLabel();
        cg.line(e.getLine());
        cg.labelID(cond);
        e.getCondition().accept(this.valueCGVisitor,null);
        cg.convertTo(e.getCondition().getType(), IntType.getInstance());
        cg.jz(end);
        cg.comment("* While body");
        for(Statement statement : e.getWhileBody()){
            statement.accept(this,param);
        }
        cg.jmp(cond);
        cg.line(e.getLine());
        cg.labelID(end);
        return null;
    }

    /**
     * execute[[DoWhile: statement1 -> statement2* expression]]()=
     *      String body = cg.getLabel()
     *      body <:>
     *      statement2*.forEach(statement -> execute[[statement]]())
     *      value[[expression]]()
     *      cg.convertTo(expression.type,IntType)
     *      <jnz> body
     */
    @Override
    public Void visit(DoWhile e, FunctionDefinition param){
        cg.line(e.getLine());
        cg.comment("* Do While");
        String body = cg.getLabel();
        cg.line(e.getLine());
        cg.labelID(body);
        cg.comment("* Do While body");
        e.getBody().forEach(statement->statement.accept(this,param));
        e.getCondition().accept(this.valueCGVisitor,null);
        cg.convertTo(e.getCondition().getType(), IntType.getInstance());
        cg.jnz(body);
        return null;
    }

    /**
     *  execute[[IfElse: statement1 -> expression statement2* statement3*^]]() =
     *      String else = cg.getLabel()
     *      String end = cg.getLabel()
     *      value[[expression]]()
     *      cg.convertTo(expression.type, IntType)
     *      <jz> else
     *      statement2*.forEach(statement -> execute[[statement]]())
     *      <jmp> end
     *      else <:>
     *      statement3*.forEach(statement -> execute[[statement]]())
     *      end <:>
     */
    @Override
    public Void visit(IfElse e, FunctionDefinition param){
        cg.line(e.getLine());
        cg.comment("* If");
        String elsePart = cg.getLabel();
        String end = cg.getLabel();
        e.getCondition().accept(this.valueCGVisitor,null);
        cg.convertTo(e.getCondition().getType(), IntType.getInstance());
        cg.jz(elsePart);
        cg.comment("* if body");
        for(Statement statement : e.getThenBranch()){
            statement.accept(this,param);
        }
        cg.jmp(end);
        cg.line(e.getLine());
        cg.labelID(elsePart);
        cg.comment("* else body");
        for(Statement statement : e.getElseBranch()){
            statement.accept(this,param);
        }
        cg.line(e.getLine());
        cg.labelID(end);
        return null;
    }

    /**
     * void execute[[Invocation: statement -> expression1 expression2*]]() =
     *              value[[(Expression) statement]]()
     *              if( expression1.type.returnType != VoidType){
     *                  <pop> expression1.type.returnType.suffix()
     *              }
     */
    @Override
    public Void visit(Invocation e, FunctionDefinition param){
        e.accept(this.valueCGVisitor,null);
        FunctionType functionType = (FunctionType) e.getFuncName().getType();
        if (!(functionType.getReturnType() instanceof VoidType)) {
            cg.pop(functionType.getReturnType());
        }
        return null;
    }

}