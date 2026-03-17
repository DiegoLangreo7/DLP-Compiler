package symboltable;

import java.util.*;
import ast.definition.Definition;

public class SymbolTable {
	
	private int scope=0;
	private List<Map<String,Definition>> table = new ArrayList<>();
	public SymbolTable()  {
		table.add(new HashMap<>());
	}

	public void set() {
		table.add(new HashMap<>());
		this.scope++;
	}
	
	public void reset() {
		table.remove(this.scope);
		this.scope--;
	}
	
	public boolean insert(Definition definition) {
		if(findInCurrentScope(definition.getName())){
			return false;
		}
		else{
			table.get(this.scope).put(definition.getName(),definition);
			definition.setScope(this.scope);
			return true;
		}
	}
	
	public Definition find(String id) {
		for (int i = table.size() - 1; i >= 0; i--) {
			if(table.get(i).containsKey(id)){
				return table.get(i).get(id);
			}
		}
		return null;
	}

	//package-protected for testing pourposes
	boolean findInCurrentScope(String id) {
		Map<String,Definition> actualScope = table.get(this.scope);
		return actualScope.containsKey(id);
	}
}
