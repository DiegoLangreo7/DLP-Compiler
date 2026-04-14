package codegen;

import ast.definition.FunctionDefinition;
import ast.definition.VariableDefinition;
import ast.statement.Statement;
import ast.type.FunctionType;
import ast.type.RecordField;
import ast.type.RecordType;
import visitor.AbstractVisitor;

import java.util.Collections;

public class OffsetVisitor extends AbstractVisitor<Boolean,Void> {

    private int globalBytesSum = 0;
    private int localBytesSum = 0;
    private int paramBytesSum = 0;

    private final int CONTROL_INFORMATION_BYTES = 4;

    /**
     * Calcula el offset de las definiciones de variable
     * Pueden ser:
     *              - Variables globales
     *              - Variables locales
     *              - Parámetros
     */
    @Override
    public Void visit(VariableDefinition e, Boolean isLocal) {

        if(e.getScope()==0){ // Variables globales
            e.setOffset(globalBytesSum);
            globalBytesSum += e.getType().getNumberOfBytes();
        }
        else if(isLocal){ // Variables locales
            localBytesSum += e.getType().getNumberOfBytes();
            e.setOffset(-localBytesSum);
        }
        else{ // Parámetros
            e.setOffset(CONTROL_INFORMATION_BYTES + paramBytesSum);
            paramBytesSum += e.getType().getNumberOfBytes();
        }

        return null;
    }

    /**
     * Calcula el offset de los RecordField
     */
    @Override
    public Void visit(RecordType e, Boolean param) {

        int recordBytesSum = 0;

        for(RecordField field : e.getFields()){
            field.setOffset(recordBytesSum);
            recordBytesSum += field.getFieldType().getNumberOfBytes();
            field.getFieldType().accept(this,param);
        }

        return null;
    }

    // Necesario para recorrer los parámetros al revés
    @Override
    public Void visit(FunctionType e, Boolean param) {

        Collections.reverse(e.getParameters());

        for(VariableDefinition variableDefinition  : e.getParameters())
            variableDefinition.accept(this,param);
        return null;
    }

    // Necesario para resetear los contadores de variables locales y parámetros
    @Override
    public Void visit(FunctionDefinition e, Boolean param) {

        this.paramBytesSum = 0;

        e.getType().accept(this,false);

        this.localBytesSum = 0;

        for (Statement statement : e.getFuncBody()) {
            statement.accept(this, true);
        }

        return null;
    }

}
