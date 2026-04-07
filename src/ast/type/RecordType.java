package ast.type;

import ast.Locatable;
import ast.definition.*;
import visitor.Visitor;

import java.util.ArrayList;
import java.util.List;

public class RecordType extends AbstractType {

    private List<RecordField> fields;

    public RecordType( List<VariableDefinition> fields) {
        this.fields = new ArrayList<>();
        for(VariableDefinition vD : fields){
            this.fields.add(toField(vD));
        }
    }

    private RecordField toField(VariableDefinition vD) {
        return new RecordField(vD.getLine(), vD.getColumn(), vD.getName(), vD.getType());
    }

    public List<RecordField> getFields() {
        return fields;
    }

        public String toString() {
        	return "record";
        }

    @Override
    public <TP, TR> TR accept(Visitor<TP, TR> visitor, TP param) {
        return visitor.visit(this, param);
    }

    @Override
    public Type dot(String field, Locatable locatable) {
        for (RecordField f : fields) {
            if (f.getFieldName().equals(field)) {
                return f.getFieldType();
            }
        }
        return new ErrorType("Field " + field + " does not exist in record type", locatable);
    }

    @Override
    public int getNumberOfBytes() {
        int fieldsBytesSum = 0;
        for(RecordField field : fields){
            fieldsBytesSum += field.getFieldType().getNumberOfBytes();
        }
        return fieldsBytesSum;
    }

}
