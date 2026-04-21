package ast.type;

import ast.Locatable;
import ast.definition.*;
import visitor.Visitor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    public RecordField getField(String ID) {
        for(RecordField field : fields){
            if(Objects.equals(field.getFieldName(), ID)){
                return field;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("RecordType[fields: ");

        for (int i = 0; i < fields.size(); i++) {
            sb.append(fields.get(i).toString());
            if (i < fields.size() - 1) {
                sb.append(", ");
            }
        }

        sb.append("]");
        return sb.toString();
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
