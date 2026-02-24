package ast.type;

import ast.definition.VariableDefinition;

import java.util.List;

public class RecordType implements Type{

    private List<RecordField> fields;

    public RecordType( List<VariableDefinition> fields) {
        for(VariableDefinition vD : fields){
            this.fields.add(toField(vD));
        }
    }

    private RecordField toField(VariableDefinition vD) {
        return new RecordField( vD.getName(), vD.getType());
    }

    public List<RecordField> getFields() {
        return fields;
    }

}
