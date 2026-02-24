package ast.type;

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
}
