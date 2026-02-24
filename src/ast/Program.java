package ast;

import ast.definition.Definition;
import ast.definition.FunctionDefinition;
import ast.definition.VariableDefinition;

import java.util.List;

public class Program implements ASTNode {

    private List<Definition> definitions;

    public Program(){
    }

    public List<Definition> getDefinitions() {
        return definitions;
    }

    public void addDefinition(FunctionDefinition definition){
        this.definitions.add(definition);
    }

    public void addDefinition(List<VariableDefinition> definitions){
        this.definitions.addAll(definitions);
    }
}
