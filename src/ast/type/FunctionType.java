package ast.type;

import ast.definition.VariableDefinition;
import ast.expression.Variable;

import java.util.List;

public class FunctionType implements Type {

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
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        if (parameters != null && !parameters.isEmpty()) {
            for (int i = 0; i < parameters.size(); i++) {
                sb.append(parameters.get(i));
                if (i < parameters.size() - 1) {
                    sb.append(", ");
                }
            }
        }
        sb.append(") -> ").append(returnType);
        return sb.toString();
    }

}
