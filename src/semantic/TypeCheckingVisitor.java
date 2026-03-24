package semantic;

import ast.expression.CharLiteral;
import ast.expression.IntLiteral;
import ast.expression.NumberLiteral;
import ast.type.CharType;
import ast.type.IntType;
import ast.type.NumberType;
import ast.type.Type;
import visitor.AbstractVisitor;

public class TypeCheckingVisitor extends AbstractVisitor<Type, Void> {

    @Override
    public Void visit(IntLiteral e, Type param) {
        e.setType(IntType.getInstance());
        return null;
    }

    @Override
    public Void visit(CharLiteral e, Type param) {
        e.setType(CharType.getInstance());
        return null;
    }

    @Override
    public Void visit(NumberLiteral e, Type param) {
        e.setType(NumberType.getInstance());
        return null;
    }


}
