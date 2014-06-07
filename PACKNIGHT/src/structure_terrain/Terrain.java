package structure_terrain;

import structure_terrain.Case; 

public class Terrain {
	
	private Case[][] terrain;
	
	public Terrain(int largeur, int longueur){
		terrain=new Case[largeur][longueur];
	}
	
	public void param(int x, int y, int xf, int yf)
	{
		terrain[x][y].setAcessCase(true);
		terrain[xf][yf].setAcessCase(false);
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
