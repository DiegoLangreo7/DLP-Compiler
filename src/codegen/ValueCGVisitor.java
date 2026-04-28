package codegen;

import ast.expression.*;
import ast.expression.binaryOperation.Arithmetic;
import ast.expression.binaryOperation.Comparison;
import ast.expression.binaryOperation.Logic;
import ast.expression.unaryOperation.UnaryNot;
import ast.type.FunctionType;
import ast.type.RecordType;

public class ValueCGVisitor extends AbstractCGVisitor<Void,Void> {

    private AddressCGVisitor addressCGVisitor;

    public ValueCGVisitor(CodeGenerator codeGenerator) {
        this.cg = codeGenerator;
    }

    public void setAddressCGVisitor(AddressCGVisitor addressCGVisitor) {
        this.addressCGVisitor = addressCGVisitor;
    }

    /**
     * void value[[IntLiteral: expression -> INT_CONSTANT]]() =
     *   <pushi> INT_CONSTANT
     */
    @Override
    public Void visit(IntLiteral e, Void param) {
        cg.push(e.getType(),String.valueOf(e.getValue()));
        return null;
    }

    /**
     * void value[[CharLiteral: expression -> CHAR_CONSTANT]]() =
     *   <pushb> CHAR_CONSTANT
     */
    @Override
    public Void visit(CharLiteral e, Void param) {
        cg.push(e.getType(), String.valueOf((int) e.getValue()));
        return null;
    }

    /**
     * void value[[NumberLiteral: expression -> REAL_CONSTANT]]() =
     *   <pushf> REAL_CONSTANT
     */
    @Override
    public Void visit(NumberLiteral e, Void param) {
        cg.push(e.getType(),String.valueOf(e.getValue()));
        return null;
    }

    /**
     * void value[[Arithmetic: expression1 -> expression2 (+ | - | * | / | %) expression3]]() =
     *    value[[expression2]]()
     *    cg.convertTo(expression2.type,expression1.type)
     *    value[[expression3]]()
     *    cg.convertTo(expression3.type,expression1.type)
     *    cg.arithmetic(expression1.type, expression1.operator)
     */
    @Override
    public Void visit(Arithmetic e, Void param) {
        e.getLeft().accept(this, null);
        cg.convertTo(e.getLeft().getType(), e.getType());
        e.getRight().accept(this, null);
        cg.convertTo(e.getRight().getType(), e.getType());
        cg.arithmetic(e.getType(), e.getOperator());
        return null;
    }

    /**
     * void value[[Logical: expression1 -> expression2 (&& | ||) expression3]]() =
     *   value[[expression2]]()
     *   cg.convertTo(expression2.type,expression1.type)
     *   value[[expression3]]()
     *   cg.convertTo(expression3.type,expression1.type)
     *   cg.logical(expression1.type, expression1.operator)
     */
    @Override
    public Void visit(Logic e, Void param) {
        e.getLeft().accept(this, null);
        cg.convertTo(e.getLeft().getType(), e.getType());
        e.getRight().accept(this, null);
        cg.convertTo(e.getRight().getType(), e.getType());
        cg.logical(e.getOperator());
        return null;
    }

    /**
     * void value[[Comparison: expression1 -> expression2 (> | < | >= | <= | == | !=) expression3]]() =
     *      value[[expression2]]()
     *      cg.convertTo(expression2.type,expression1.type)
     *      value[[expression3]]()
     *      cg.convertTo(expression3.type,expression1.type)
     *      cg.comparison(expression1.type, expression1.operator)
     */
    @Override
    public Void visit(Comparison e, Void param){
        e.getLeft().accept(this, param);
        cg.convertTo(e.getLeft().getType(), e.getType());
        e.getRight().accept(this, param);
        cg.convertTo(e.getRight().getType(), e.getType());
        cg.comparison(e.getType(), e.getOperator());
        return null;
    }

    /**
     * void value[[UnaryNot: expression1 -> expression2))() =
     *     value[[expression2]]()
     *     cg.logical("!")
     */
    @Override
    public Void visit(UnaryNot e, Void param) {
        e.getOperand().accept(this, param);
        cg.logical("!");
        return null;
    }

    /**
     * void value[[Cast: statement -> type expression]]() =
     *      value[[expression]]()
     *      cg.convert(expression.type,type)
     */
    @Override
    public Void visit(Cast e, Void param) {
        e.getOperand().accept(this,param);
        cg.convertTo(e.getOperand().getType(),e.getCastType());
        return null;
    }

    /**
     * void value[[Invocation: expression1 -> expression2 expression3*]]() =
     *      for(int i = 0; i < expression3*.size; i++){
     *          value[expression3*.get(i)]()
     *          cg.convertTo(expression3*.get(i).type, expression2.type.parameters.get(i).type)
     *      }
     *      <call> expression2.name
     */
    @Override
    public Void visit(Invocation e, Void param){
        for(int i=0; i<e.getArguments().size();i++){
            e.getArguments().get(i).accept(this,param);
            FunctionType functionType = (FunctionType) e.getFuncName().getType();
            cg.convertTo(e.getArguments().get(i).getType(), functionType.getReturnType());
        }
        cg.call(e.getFuncName().getName());
        return null;
    }

    // L-VALUE's

    /**
     * void value[[Variable: expression -> ID]]() =
     *    address[[expression]]()
     *    <load> expression.type.suffix
     */
    @Override
    public Void visit(Variable e, Void param) {
        e.accept(addressCGVisitor, null);
        cg.load(e.getDefinition().getType());
        return null;
    }

    /**
     * void value[[ArrayAccess: expression1 -> expression2 expression3]]() =
     *      address[[expression2]]()
     *      <load> expression1.type.suffix
     *
     */
    @Override
    public Void visit(ArrayAccess e, Void param) {
        e.accept(addressCGVisitor, null);
        cg.load(e.getType());
        return null;
    }

    /**
     * void value[[FieldAccess: expression1 -> expression2 ID]]() =
     *      address[[expression2]]()
     *      <load> expression2.getField(ID).getType.ID
     */
    @Override
    public Void visit(FieldAccess e, Void param) {
        e.accept(addressCGVisitor, null);
        RecordType recordType = (RecordType) e.getStructureExpression().getType();
        cg.load(recordType.getField(e.getFieldAccess()).getFieldType());
        return null;
    }
}