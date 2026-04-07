package ast.type;

import ast.AbstractLocatable;
import visitor.Visitor;

public class RecordField extends AbstractLocatable {

    private String fieldName;
    private Type fieldType;

    private int offset;

    public RecordField(int line, int column, String fieldName, Type fieldType) {
        super(line,column);
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

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }
}
