package controleur.automate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class TableTransitionSortie {
	//Classe intermediaire de triplet
	public class Triplet {
		public boolean ok; public int EtatSuiv, Sortie;
		public Triplet(boolean ok, int etatSuiv, int sortie) {
			super();
			this.ok = ok;
			EtatSuiv = etatSuiv;
			Sortie = sortie;
		}
	}

	List<Map<Integer,Triplet>> table;
	private int nbEtat;
	public TableTransitionSortie(int nbEtat){
		table = new ArrayList<Map<Integer,Triplet>>(nbEtat);
		this.nbEtat = nbEtat;
		for(int i=0; i<nbEtat; i++){
			table.add(i, new HashMap<Integer,Triplet>());
		}
	}
	/**
	 * 
	 * @param transition
	 * @param sortie
	 * @param bool, bool[i][j] = true, si a l'etat i, l'entree j, et utilisé
	 */
	public void initTransitionSortie(int[][] transition, int[][] sortie, boolean[][] bool ){
		for(int i=0; i<nbEtat; i++){
			for(int j=0; j<transition[0].length; j++){
				table.get(i).put(j, new Triplet(bool[i][j], transition[i][j], sortie[i][j]));
			}
		}
	}
	
	public boolean getValide(int Etat, int Entree){
		assert (table.size()>Etat) : "L'etat renseigner n'existe pas dans la table";
		assert (!table.get(Etat).containsKey(Entree)) : "L'entree renseignée n'existe pas dans la table";
		return table.get(Etat).get(Entree).ok;
	}
	
	
	public Map<Integer, Triplet> getEtatAll(int Etat){
		assert (table.size()>Etat) : "L'etat renseigner n'existe pas dans la table";
		return (Map<Integer, Triplet>) table.get(Etat);
	}

	public int getEtatSuiv(int Etat, int Entree){
		assert (table.size()>Etat) : "L'etat renseigner n'existe pas dans la table";
		assert (!table.get(Etat).containsKey(Entree)) : "L'entree renseignée n'existe pas dans la table";
		return table.get(Etat).get(Entree).EtatSuiv;
	}
	
	public int getSortie(int Etat, int Entree){
		assert (table.size()>Etat) : "L'etat renseigner n'existe pas dans la table";
		assert (!table.get(Etat).containsKey(Entree)) : "L'entree renseignée n'existe pas dans la table";
		return table.get(Etat).get(Entree).Sortie;
	}
}
