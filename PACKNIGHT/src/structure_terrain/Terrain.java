/**
 * author alex
 * Classe abstract, on ne peut instancier que des terrains dont on connais tout
 */

package structure_terrain;
import structure_terrain.Case; 

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
		terrain=new Case[hauteur][largeur];
		this.hauteur = hauteur;
		this.largeur = largeur;
		for(int i=0; i<hauteur; i++){
			for(int j=0; j<largeur; j++){
				this.terrain[i][j] = new Case(true) ;
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
	public void setCase(int ligne, int colonne, Case elt){
		terrain[ligne][colonne]= elt;
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

}