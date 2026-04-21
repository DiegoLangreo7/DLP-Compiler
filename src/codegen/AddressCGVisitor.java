package codegen;

import ast.definition.VariableDefinition;
import ast.expression.ArrayAccess;
import ast.expression.FieldAccess;
import ast.expression.Variable;
import ast.type.IntType;
import ast.type.RecordType;

public class AddressCGVisitor extends AbstractCGVisitor<Void,Void> {

    private ValueCGVisitor valueCGVisitor;

    public AddressCGVisitor(CodeGenerator codeGenerator) {
        this.cg = codeGenerator;
    }

    public void setValueCGVisitor(ValueCGVisitor valueCGVisitor) {
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
            cg.push(IntType.getInstance(),String.valueOf(((VariableDefinition) e.getDefinition()).getOffset()));
            cg.add(IntType.getInstance());
        }
        return null;
    }

    /**
     * void address[[ArrayAccess: expression1 -> expression2 expression3]]() =
     *      address[[expression2]]()
     *      value[[expression3]]()
     *      cg.convertTo(expression3.type, IntType)
     *      <pushi> expression1.type.numberOfBytes
     *      <muli>
     *      <addi>
     */
    @Override
    public Void visit(ArrayAccess e, Void param) {
        e.getArray().accept(this,param);
        e.getIndex().accept(this.valueCGVisitor,param);
        cg.convertTo(e.getIndex().getType(), IntType.getInstance());
        cg.push(IntType.getInstance(), String.valueOf(e.getType().getNumberOfBytes()));
        cg.mul(IntType.getInstance());
        cg.add(IntType.getInstance());
        return null;
    }

    /**
     * void address[[FieldAccess: expression1 -> expression2 ID]]() =
     *      address[[expression2]]()
     *      <pushi> expression2.type.getField(ID).offset
     *      <addi>
     */
    @Override
    public Void visit(FieldAccess e, Void param) {
        e.getStructureExpression().accept(this,param);
        RecordType recordType = (RecordType) e.getStructureExpression().getType();
        int recordFieldOffset = recordType.getField(e.getFieldAccess()).getOffset();
        cg.push(IntType.getInstance(), String.valueOf(recordFieldOffset));
        cg.add(IntType.getInstance());
        return null;
    }
}
