package structure_terrain;

public class Case {
	
	private Boolean accessible;
	//private Bonus bonus;
	public Case(Boolean a){
		this.accessible=a;
	}
	
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
