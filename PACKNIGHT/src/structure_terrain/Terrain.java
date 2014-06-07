package structure_terrain;
import structure_terrain.Case; 

public class Terrain {
	
	private Case[][] terrain;
	
	public Terrain(int largeur, int longueur){
		terrain=new Case[largeur][longueur];
	}
	
	public Case[][] getTerrain(){
		return terrain;
	}
	
	public int getHauteur(){
		return terrain.length;
	}
	
	public int getLargeur(){
		if (terrain.length == 0) return 0;
		else return terrain[0].length;
	}
	
	public Case getCase(int ligne, int colonne){
		return terrain[ligne][colonne];
	}
	
	public void setCase(int ligne, int colonne, Case ca){
		terrain[ligne][colonne]=ca;
	}

	
	
	public void afficher(){
		int i,j;
		
		for(i=0; i < terrain.length;i++){
			for(j=0; j < terrain[0].length;j++){
				System.out.print(terrain[i][j].toString());
			}
			System.out.print("\n");
		}
		
	}

}
