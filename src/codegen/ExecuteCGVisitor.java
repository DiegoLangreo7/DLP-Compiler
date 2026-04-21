package codegen;

import ast.Program;
import ast.definition.Definition;
import ast.definition.FunctionDefinition;
import ast.definition.VariableDefinition;
import ast.statement.*;
import ast.type.FunctionType;
import ast.type.IntType;
import ast.type.VoidType;

public class ExecuteCGVisitor extends AbstractCGVisitor<Void,Void> {

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
    public Void visit(Program e, Void param) {
        for(Definition d : e.getDefinitions()){
            if(d instanceof VariableDefinition){
                d.accept(this,param);
            }
        }
        cg.mainInvocation();
        for(Definition d : e.getDefinitions()){
            if(d instanceof FunctionDefinition){
                d.accept(this,param);
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
    public Void visit(FunctionDefinition e, Void param) {
        cg.line(e.getLine());
        FunctionType functionType = (FunctionType) e.getType();
        cg.functionID(e.getName());
        cg.comment("* Parameters");
        for(VariableDefinition parameter : functionType.getParameters()){
            parameter.accept(this,param);
        }
        cg.comment("* Local variables");
        for(Statement funcLine : e.getFuncBody()){
            if(funcLine instanceof VariableDefinition)
                funcLine.accept(this,param);
        }
        cg.enter(e.getLocalBytesSum());
        for(Statement funcLine : e.getFuncBody()){
            if(!(funcLine instanceof VariableDefinition))
                funcLine.accept(this,param);
        }
        int returnBytes = functionType.getReturnType() instanceof VoidType
                ? 0
                : functionType.getReturnType().getNumberOfBytes();
        cg.ret(returnBytes, e.getLocalBytesSum(), e.getParamBytesSum());
        return null;
    }

    /**
     * void execute[[VariableDefinition: definition -> type ID]]() =
     *     ' <*> type.toString ID <(offset> definition.offset <)>
     */
    @Override
    public Void visit(VariableDefinition e, Void param){
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
    public Void visit(Assignment e, Void param) {
        cg.line(e.getLine());
        cg.comment("* Assignment");
        e.getLeft().accept(this.addressCGVisitor,param);
        e.getRigth().accept(this.valueCGVisitor,param);
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
    public Void visit(Input e,  Void param) {
        cg.line(e.getLine());
        cg.comment("* Read");
        e.getParameter().accept(this.addressCGVisitor,param);
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
    public Void visit(Log e, Void param) {
        cg.line(e.getLine());
        cg.comment("* Write");
        e.getParameter().accept(this.valueCGVisitor,param);
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
    public Void visit(While e, Void param){
        cg.line(e.getLine());
        cg.comment("* While");
        String cond = cg.getLabel();
        String end = cg.getLabel();
        cg.line(e.getLine());
        cg.labelID(cond);
        e.getCondition().accept(this.valueCGVisitor,param);
        cg.convertTo(e.getCondition().getType(), IntType.getInstance());
        cg.jz(end);
        for(Statement statement : e.getWhileBody()){
            statement.accept(this,param);
        }
        cg.jmp(cond);
        cg.line(e.getLine());
        cg.labelID(end);
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
    public Void visit(IfElse e, Void param){
        cg.line(e.getLine());
        cg.comment("* While");
        String elsePart = cg.getLabel();
        String end = cg.getLabel();
        e.getCondition().accept(this.valueCGVisitor,param);
        cg.convertTo(e.getCondition().getType(), IntType.getInstance());
        cg.jz(elsePart);
        for(Statement statement : e.getThenBranch()){
            statement.accept(this,param);
        }
        cg.jmp(end);
        cg.line(e.getLine());
        cg.labelID(elsePart);
        for(Statement statement : e.getElseBranch()){
            statement.accept(this,param);
        }
        cg.line(e.getLine());
        cg.labelID(end);
        return null;
    }

}
