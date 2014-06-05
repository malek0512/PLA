package structure_terrain;

public abstract class Case {
	
	protected Boolean accessible;
	
	protected void deplacer(int ligne, int colonne){
		
			
	}
	
	protected abstract void informer();
	
	protected void setacessCase(Boolean a){
	accessible=a;
	}
	
	public Boolean getaccessCase(){
		return accessible;
	}
}
