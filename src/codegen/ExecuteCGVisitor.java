package codegen;

import ast.Program;
import ast.definition.Definition;
import ast.definition.VariableDefinition;
import ast.statement.Assignment;
import ast.type.ErrorType;

public class ExecuteCGVisitor extends AbstractCGVisitor<Void,Void> {

    private AddressCGVisitor addressCGVisitor;
    private ValueCGVisitor valueCGVisitor;

    public ExecuteCGVisitor(CodeGenerator codeGenerator) {
        this.cg = codeGenerator;
        this.addressCGVisitor = new AddressCGVisitor(this.cg,this.valueCGVisitor);
        this.valueCGVisitor = new ValueCGVisitor(this.cg,this.addressCGVisitor);
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
            if(d instanceof VariableDefinition){
                d.accept(this,param);
            }
        }
        return null;
    }

    /**
     * void execute[[Assignment: statement -> expr1 expr2]]() =
     *      address[[expr1]]
     *      value[[expr2]]
     *      cg.convertTo(expr2.type,expr1.type)
     *      <store> expr1.type.suffix
     */
    @Override
    public Void visit(Assignment e, Void param) {
        e.getLeft().accept(this.addressCGVisitor,param);
        e.getRigth().accept(this.valueCGVisitor,param);
        cg.convertTo(e.getRigth().getType(),e.getLeft().getType());
        cg.store(e.getLeft().getType());
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
     *     <ret> type.returnValue.getNumberOfBytes, definition.localBytesSum. definition.paramBytesSum
     */

}
