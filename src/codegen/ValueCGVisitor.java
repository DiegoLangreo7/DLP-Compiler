package codegen;

import ast.expression.Variable;

public class ValueCGVisitor extends AbstractCGVisitor<Void,Void> {

    private AddressCGVisitor addressCGVisitor;

    public ValueCGVisitor(CodeGenerator codeGenerator, AddressCGVisitor addressCGVisitor) {
        this.cg = codeGenerator;
        this.addressCGVisitor = addressCGVisitor;
    }

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
}
