package codegen;

public class ValueCGVisitor extends AbstractCGVisitor<Void,Void> {

    private AddressCGVisitor addressCGVisitor;

    public ValueCGVisitor(CodeGenerator codeGenerator, AddressCGVisitor addressCGVisitor) {
        this.cg = codeGenerator;
        this.addressCGVisitor = addressCGVisitor;
    }
}
