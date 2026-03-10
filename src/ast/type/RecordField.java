package ast.type;

import visitor.Visitor;

public class RecordField {

    private String fieldName;
    private Type fieldType;

    public RecordField(String fieldName, Type fieldType) {
        this.fieldName = fieldName;
        this.fieldType = fieldType;
    }

    public String getFieldName() {
        return fieldName;
    }

    public Type getFieldType() {
        return fieldType;
    }

    public String toString() {
        	return fieldName+": "+fieldType;
        }

    public <TP, TR> TR accept(Visitor<TP, TR> visitor, TP param) {
        return visitor.visit(this, param);
    }
}
