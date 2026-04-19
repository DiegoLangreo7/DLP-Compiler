package codegen;

import ast.definition.VariableDefinition;
import ast.expression.ArrayAccess;
import ast.expression.FieldAccess;
import ast.expression.Variable;
import ast.type.IntType;

public class AddressCGVisitor extends AbstractCGVisitor<Void,Void> {

    private ValueCGVisitor valueCGVisitor;

    public AddressCGVisitor(CodeGenerator codeGenerator, ValueCGVisitor valueCGVisitor) {
        this.cg = codeGenerator;
        this.valueCGVisitor = valueCGVisitor;
    }

    /**
     * void address[[Variable: expression -> ID]]() =
     *    if(expression.definition.scope == 0)
     *       <pusha> expression.definition.offset
     *    else{
     *        <push bp>
     *        <pusha> expression.definition.offset
     *        <addi>
     *    }
     */
    @Override
    public Void visit(Variable e, Void param) {
        if(e.getDefinition().getScope() == 0) {
            cg.pusha(((VariableDefinition) e.getDefinition()).getOffset());
        }
        else{
            cg.pushbp();
            cg.pusha(((VariableDefinition) e.getDefinition()).getOffset());
            cg.add(IntType.getInstance());
        }
        return null;
    }

    @Override
    public Void visit(ArrayAccess e, Void param) {
        return null;
    }

    @Override
    public Void visit(FieldAccess e, Void param) {
        return null;
    }
}
