/**
 * author alex
 * Classe abstract, on ne peut instancier que des terrains dont on connais tout
 */
package structure_terrain;

import structure_terrain.Case;
import personnages.Coordonnees;
import personnages.Direction;


public abstract class Terrain {

	protected Case[][] terrain;
	protected int hauteur;
	protected int largeur;
	
	/**
	 * Alloue la mémoire pour un terrain de haut largeur donnée
	 * @param hauteur
	 * @param largeur
	 * @require les paramètre sont >= a 1
	 * author : alex
	 */
	public Terrain(int hauteur, int largeur){
		terrain=new Case[largeur][hauteur];
		this.hauteur = hauteur;
		this.largeur = largeur;
		for(int i=0; i<hauteur; i++){
			for(int j=0; j<largeur; j++){
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

		for(i=0; i < terrain.length;i++){
			for(j=0; j < terrain[0].length;j++){
				System.out.print(terrain[i][j].toString());
			}
			System.out.print("\n");
		}
	}
	
	/**
	 * @param coord : coordonée de la case a regarder 
	 * @param direction : direction de la case que l'on veut retourner
	 * @return case si avancer dans direction
	 * @author alex
	 */
	public Case getCase(Coordonnees coord,Direction direction)
	{
		System.out.println("valeur donne bis :" + coord.x +" "+coord.y);
		switch(direction)
		{
		case haut : return terrain[coord.x+1][coord.y];
		case bas : return terrain[coord.x-1][coord.y];
		case droite : return terrain[coord.x][coord.y+1];
		case gauche : return terrain[coord.x][coord.y-1];
		default:
			break; 
		}
		return null;
	}
	
	
	/**
	 * @param coord : coordonée de la case a regarder 
	 * @param direction : direction de la case que l'on veut retourner
	 * @return coordoné si avancer dans direction
	 * @author alex
	 */
	public Coordonnees getCoordone(Coordonnees coord,Direction direction)
	{
		System.out.println("valeur donne :" + coord.x +" "+coord.y);
		switch(direction)
		{
		case haut : return new Coordonnees(coord.x+1, coord.y);
		case bas : return new Coordonnees(coord.x-1, coord.y);
		case droite : return new Coordonnees(coord.x, coord.y+1);
		case gauche : return new Coordonnees(coord.x, coord.y-1);
		default:
			break; 
		}
		return null;
	}
	
}