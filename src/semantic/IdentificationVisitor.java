package semantic;

import ast.definition.FunctionDefinition;
import ast.definition.VariableDefinition;
import ast.expression.Variable;
import ast.statement.Statement;
import ast.type.ErrorType;
import ast.type.RecordField;
import ast.type.RecordType;
import errorhandler.ErrorHandler;
import symboltable.SymbolTable;
import visitor.AbstractVisitor;

import java.util.ArrayList;
import java.util.List;

public class IdentificationVisitor extends AbstractVisitor<Void,Void>{

    private final SymbolTable st = new SymbolTable();

    @Override
    public Void visit(FunctionDefinition e, Void param) {
        if(!st.insert(e)){
            new ErrorType("Function '" + e.getName() + "' is already defined", e);
        }
        st.set();
        e.getType().accept(this,param);
        for(Statement statement : e.getFuncBody())
            statement.accept(this,param);
        st.reset();
        return null;
    }

    @Override
    public Void visit(VariableDefinition e, Void param) {
        e.getType().accept(this,param);
        if(!st.insert(e)){
            new ErrorType("Variable '" + e.getName() + "' is already defined", e);
        }
        return null;
    }

    @Override
    public Void visit(Variable e, Void param) {
        e.setLValue(true);
        if(st.find(e.getName())!=null){
            e.setDefinition(st.find(e.getName()));
        }else{
            ErrorType error = new ErrorType("Variable '" + e.getName() + "' isn't defined", e);
            e.setDefinition(new VariableDefinition(e.getLine(),e.getColumn(),e.getName(),error));
        }
        return null;
    }

    @Override
    public Void visit(RecordType e, Void param) {
        List<String> local = new ArrayList<>();
        for(RecordField field : e.getFields()){
            field.accept(this,param);
            if(local.contains(field.getFieldName())){
                new ErrorType("Field '" + field.getFieldName()+ "' is already defined",field);
            }
            local.add(field.getFieldName());
        }
        return null;
    }



}
