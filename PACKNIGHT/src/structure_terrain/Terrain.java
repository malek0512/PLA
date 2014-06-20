/**
 * author alex
 * Classe abstract, on ne peut instancier que des terrains dont on connais tout
 */
package structure_terrain;

import game.WindowGame;
import structure_terrain.Case;
import personnages.Coordonnees;
import personnages.CoordonneesFloat;
import personnages.Direction;
import personnages.Personnage;


public class Terrain {

	public Case[][] terrain;
	public int hauteur;
	public int largeur;
	static public int nb_pacgum;
	
	/**
	 * Alloue la mémoire pour un terrain de haut largeur donnée
	 * @param hauteur
	 * @param largeur
	 * @require les paramètre sont >= a 1
	 * author : alex
	 */
	public Terrain(int largeur, int hauteur, int nb_pacgum){
		terrain=new Case[largeur][hauteur];
		this.hauteur = hauteur;
		this.largeur = largeur;
		Terrain.nb_pacgum = nb_pacgum;
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
		return caseAcessible(x,y,1,direction);
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
			switch(direction)
			{
			case haut :
				if(estDansLeTerrain(x,y-distance))
					return terrain[x][y-distance].isAccessable();
			case bas :
				if(estDansLeTerrain(x,y+distance))
					return terrain[x][y+distance].isAccessable();
			case droite :
				if(estDansLeTerrain(x+distance,y))
					return terrain[(x+distance)][y].isAccessable();
			case gauche : 
				if(estDansLeTerrain(x-distance,y))
					return terrain[(x-distance)][y].isAccessable();
			default:
				break; 
			}
		return false;
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
	 * renvoie vrai si les coordonné (x,y) et dans la direction d est dans un core
	 * @param x
	 * @param y
	 * @param d
	 * @return
	 */
	public boolean estCore(int x,int y, Direction d)
	{
		int tmpX = x;
		int tmpY = y;
		switch (d)
		{
		case haut : tmpY-= 1; break;
		case bas : tmpY+= 1; break;
		case droite : tmpX+=1; break;
		case gauche : tmpX-=1; break;
		default : break;
		}
		return !(estDansLeTerrain(tmpX, tmpY));
	}
	
	/**
	 * renvoie la valeur du pixel du bord droit 
	 * @return
	 */
	public int pixelBordDroit()
	{
		return WindowGame.tuile_size * this.largeur;
	}
	
	/**
	 * renvoie la valeur du pixel du bord bas
	 * @return
	 */
	public int pixelBordBas()
	{
		return WindowGame.tuile_size * this.hauteur;
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
	
	public int ValueCase(CoordonneesFloat cord)
	{
		return terrain[cord.x][cord.y].caseValeur(); 
	}
	
	public int ValueCase(int x, int y)
	{
		return terrain[x][y].caseValeur(); 
	}
	
	
	public void SetCase(CoordonneesFloat c, int v)
	{
		terrain[c.x][c.y].setAcessCase(v);
	}

	/**Pas merci ! :)
	 * @return Vrai si la case est une intersection
	 */
	public boolean estIntersection(int x, int y){
		int n=0;

		for(Direction d : Direction.values())
			if(this.caseAcessible(x,y,d))
				n++;
		
		return n>2;
	}
}