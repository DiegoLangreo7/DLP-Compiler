package codegen;

public class AddressCGVisitor extends AbstractCGVisitor<Void,Void> {

    private ValueCGVisitor valueCGVisitor;

    public AddressCGVisitor(CodeGenerator codeGenerator, ValueCGVisitor valueCGVisitor) {
        this.cg = codeGenerator;
        this.valueCGVisitor = valueCGVisitor;
    }
}
