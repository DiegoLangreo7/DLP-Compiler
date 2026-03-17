package ast.type;

import ast.definition.*;
import visitor.Visitor;

import java.util.ArrayList;
import java.util.List;

public class RecordType implements Type{

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
        	StringBuilder sb = new StringBuilder();
        	sb.append("record {");
        	for (int i = 0; i < fields.size(); i++) {
        		sb.append(fields.get(i));
        		if (i < fields.size() - 1) {
        			sb.append(", ");
        		}
        	}
        	sb.append("}");
        	return sb.toString();
        }

    @Override
    public <TP, TR> TR accept(Visitor<TP, TR> visitor, TP param) {
        return visitor.visit(this, param);
    }
}
