/**
 * author alex
 * Classe abstract, on ne peut instancier que des terrains dont on connais tout
 */
package structure_terrain;

import structure_terrain.Case;
import personnages.Coordonnees;
import personnages.Direction;
import personnages.Personnage;


public class Terrain {

	public Case[][] terrain;
	protected int hauteur;
	protected int largeur;
	
	/**
	 * Alloue la mémoire pour un terrain de haut largeur donnée
	 * @param hauteur
	 * @param largeur
	 * @require les paramètre sont >= a 1
	 * author : alex
	 */
	public Terrain(int largeur, int hauteur){
		terrain=new Case[largeur][hauteur];
		this.hauteur = hauteur;
		this.largeur = largeur;
		for(int i=0; i<largeur; i++){
			for(int j=0; j<hauteur; j++){
				this.terrain[i][j] = new Case(1) ;
			}
		}
	}

	/**
	 * @return la hauteur du terrain
	 */
	public int getHauteur(){
		return hauteur;
	}

	/**
	 * @return la largeur du terrain
	 */
	public int getLargeur(){
		return largeur;
	}

	
	public Case getCase(int ligne, int colonne){
		return terrain[ligne][colonne];
	}
	
	/**
	 * ajoute l'objet au coordonée donnée
	 * @param ligne
	 * @param colonne
	 * @param elt l'objet a mettre
	 * @require : les coordonée sont juste et l'objet est initialiser
	 */
	public void setCase(int ligne, int colonne, int elt){
		terrain[ligne][colonne]=new Case(elt);
	}

	/**
	 * affiche le terrain
	 * fonction a ameliorer pour pouvoir voir quelque chose
	 */
	public void afficher(){
		int i,j;

		for(i=0; i < this.hauteur;i++){
			System.out.print(i + " ");
			for(j=0; j < this.largeur;j++){
				System.out.print(terrain[j][i].toString());
			}
			System.out.print("\n");
		}
	}
	
	/**
	 * renvoie vrai si la case dans la direction est accessible
	 * @param x : cord.x de la case
	 * @param y : cord.y de la case
	 * @param direction : direction ou doit etre tester la case
	 * @return vraie si la case dans la direction de la case (x,y) est accessible
	 */
	public boolean caseAcessible(int x, int y,Direction direction)
	{
		if(estDansLeTerrain(x,y))
			switch(direction)
			{
			case haut : return terrain[x][y-1].isAccessable();
			case bas : return terrain[x][y+1].isAccessable();
			case droite : return terrain[x+1][y].isAccessable();
			case gauche : return terrain[x-1][y].isAccessable();
			default:
				break; 
			}
		return false;
	}
	
	/**
	 * Renvoie si la case d'une distance distance dans la direction donnée est accessible
	 * @param x : cord.x de la case de reference
	 * @param y : cord.y de la case de reference
	 * @param distance : distance de la case chercher par rapport a la case de depart
	 * si distance = 0, on renvoie si la case (x,y) est accessible
	 * @param direction : direction vers ou on veut connaitre la case
	 * @return vraie si la case de distance distance et dans la direction donné est accessible
	 */
	public boolean caseAcessible(int x, int y, int distance, Direction direction)
	{
		if(estDansLeTerrain(x,y))
			switch(direction)
			{
			case haut : return terrain[x][y-distance].isAccessable();
			case bas : return terrain[x][y+distance].isAccessable();
			case droite : return terrain[x+distance][y].isAccessable();
			case gauche : return terrain[x-distance][y].isAccessable();
			default:
				break; 
			}
		return false;
	}
	
	/**
	 * ATTENTION : ceci ne fonctionne que pour des coordonnées de TERRAIN et non de PERSONNAGE
	 * @param coord : coordonnée de la case a regarder 
	 * @param direction : direction de la case que l'on veut retourner
	 * @return case si avancer dans direction
	 * @author alex
	 * 
	 * Pas de test si les coordonnées sont dans le terrain ? (malek)
	 * @throws Exception 
	 */

	public Case getCase(int x, int y,Direction direction)
	{
	//	if(estDansLeTerrain(new Coordonnees((int) (x/WindowGame.tuile_size),(int) (y/WindowGame.tuile_size))))

			switch(direction)
			{
			case haut : return terrain[x][y-1];
			case bas : return terrain[x][y+1];
			case droite :return terrain[x+1][y];
			case gauche : return terrain[x-1][y];
			default:

				break; 
			}
		return null;

	}

	/**
	 * TODO : a faire plus tard...
	 * @author malek
	 * @param coord
	 * @return vraie si les coordonnée sont hors du terrain, faux sinon
	 *
	 */
	protected boolean estDansLeTerrain(int x, int y){
		return (!(x < 0
		|| x > Personnage.getTerrain().getLargeur() - 1
		|| y < 0
		|| y > Personnage.getTerrain().getHauteur() - 1));
	}
	
	/**
	 * @require : les coordonnes sont dans le terrain
	 * @param coord : coordonée de la case a regarder 
	 * @param direction : direction de la case que l'on veut retourner
	 * @return coordonné si avancer dans direction
	 * @author alex
	 */
	public Coordonnees getCoordonnees(Coordonnees coord,Direction direction)
	{
		switch(direction)
		{
		case haut : return new Coordonnees(coord.x, coord.y-1);
		case bas : return new Coordonnees(coord.x, coord.y+1);
		case droite : return new Coordonnees(coord.x+1, coord.y);
		case gauche : return new Coordonnees(coord.x-1, coord.y);
		default:
			break; 
		}
		return null;
	}
}