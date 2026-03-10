package ast;

import ast.definition.Definition;
import ast.definition.VariableDefinition;
import visitor.Visitor;

import java.util.ArrayList;
import java.util.List;

public class Program implements ASTNode {

    private List<Definition> definitions = new ArrayList<Definition>();

    public Program(){
    }

    public void addDefinitions(List<Definition> definitions){
        this.definitions.addAll(definitions);
    }

    public List<Definition> getDefinitions() {
        return definitions;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Program:\n");
        if (definitions != null) {
            for (Definition def : definitions) {
                sb.append(def).append("\n");
            }
        }
        return sb.toString();
    }

    @Override
    public <TP, TR> TR accept(Visitor<TP, TR> visitor, TP param) {
        return visitor.visit(this,param);
    }
}
