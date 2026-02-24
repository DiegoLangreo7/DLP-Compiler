package ast;

import ast.definition.Definition;
import ast.definition.VariableDefinition;

import java.util.ArrayList;
import java.util.List;

public class Program implements ASTNode {

    private List<Definition> definitions = new ArrayList<Definition>();

    public Program(){
    }

    public void addDefinitions(List<Definition> definitions){
        this.definitions.addAll(definitions);
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
}
