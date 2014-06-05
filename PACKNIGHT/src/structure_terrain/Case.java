package structure_terrain;

public abstract class Case {
	
	protected Boolean accessible;
	
	public Case(Boolean a){
		this.accessible=a;
	}
	
	protected abstract void informer();
	
	protected void setacessCase(Boolean a){
	accessible=a;
	}
	
	public Boolean getaccessCase(){
		return accessible;
	}
	
	public String toString(){
		String s;
		
		if (getaccessCase()){
			s="-";
		}
		else 
			s="*";
		return s;
	}
}
