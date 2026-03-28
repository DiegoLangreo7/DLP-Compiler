package ast.type;

import ast.Locatable;
import ast.definition.VariableDefinition;
import ast.expression.Variable;
import visitor.Visitor;

import java.util.List;

public class FunctionType extends AbstractType {

    private Type returnType;
    private List<VariableDefinition> parameters;

    public FunctionType(Type returnType, List<VariableDefinition> parameters) {
        this.returnType = returnType;
        this.parameters = parameters;
    }

    public Type getReturnType() {
        return returnType;
    }

    public List<VariableDefinition> getParameters() {
        return parameters;
    }

    @Override
    public String toString() {
        return "function";
    }

    @Override
    public <TP, TR> TR accept(Visitor<TP, TR> visitor, TP param) {
        return visitor.visit(this, param);
    }

    @Override
    public Type parenthesis(List<Type> parameters, Locatable locatable) {
        if (this.parameters.size() != parameters.size()) {
            return new ErrorType("The function expected " + this.parameters.size() + " arguments but received " + parameters.size(), locatable);
        }
        for (int i = 0; i < this.parameters.size(); i++) {
            parameters.get(i).mustPromotesTo(this.parameters.get(i).getType(), locatable);
        }
        return returnType;
    }

}
