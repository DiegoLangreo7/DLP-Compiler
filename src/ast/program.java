package ast;

import ast.Definition.Definition;

import java.util.List;

public class program implements ASTNode {

    private List<Definition> definitions;

    public program(List<Definition> definitions) {
        this.definitions = definitions;
    }

    public List<Definition> getDefinitions() {
        return definitions;
    }
}
