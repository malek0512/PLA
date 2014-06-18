package structure_terrain;

public class Terrain1 extends Terrain {

	public Terrain1(int hauteur, int largeur) {
		super(hauteur, largeur,0);
		for(int i=0; i<hauteur;i++)
		{
			for(int j=0;j<largeur;j++)
			{
				terrain[i][j] = new Case(1);
			}
		}
//		terrain[1][1] = new Case(1);
//		terrain[1][2] = new Case(1);
//		terrain[1][3] = new Case(1);
//		terrain[2][1] = new Case(1);
//		terrain[2][3] = new Case(1);
//		terrain[3][1] = new Case(1);
//		terrain[3][3] = new Case(1);
//		terrain[3][4] = new Case(1);
//		terrain[3][6] = new Case(1);
//		terrain[4][1] = new Case(1);
//		terrain[4][1] = new Case(1);
//		terrain[4][1] = new Case(1);
//		terrain[4][2] = new Case(1);
//		terrain[4][3] = new Case(1);
//		terrain[5][1] = new Case(1);
//		terrain[3][5] = new Case(1);
//		terrain[4][5] = new Case(1);
//		terrain[2][5] = new Case(1);
//		terrain[6][1] = new Case(1);
//		terrain[7][1] = new Case(1);
//		terrain[6][3] = new Case(1);
//		terrain[7][2] = new Case(1);
//		terrain[7][3] = new Case(1);
//		terrain[7][4] = new Case(1);
//		terrain[8][3] = new Case(1);
//		terrain[2][3] = new Case(0);
//		terrain[4][2] = new Case(0);
//		terrain[5][3] = new Case(1);
	}

}
