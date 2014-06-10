package controleur.automate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import src.parser.Quad;


public class TableTransitionSortie {
	//Classe intermediaire de triplet
	public class Triplet {
//		public boolean ok;
		public int EtatSuiv, Sortie;
		public Triplet(int etatSuiv, int sortie) {
			super();
//			this.ok = ok;
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
	public void initTransitionSortie(int[][] transition, int[][] sortie ){
		for(int i=0; i<nbEtat; i++){
			for(int j=0; j<transition[0].length; j++){
				table.get(i).put(j, new Triplet(transition[i][j], sortie[i][j]));
			}
		}
	}

	/**
	 * Initialise la table de transition avec ArrayList de liste chainée contenant le type 
	 * 		Quad = {int Entree, int EtatSuiv, int Sortie }
	 * @param TransitionSortie
	 */
	public void initTransitionSortie(ArrayList<List<Quad>>TransitionSortie ){
		for(int i=0; i<nbEtat; i++){
			for(Quad q: TransitionSortie.get(i)){
				table.get(i).put(q.Entree, new Triplet(q.EtatSuiv,q.Sortie));
			}
		}
	}
		
	/**
	 * Renvoie True, si l'Entree Existe Dans l'Automate donc si l'une des Hashmap contient l'entree. 
	 * @param Entree
	 * @return
	 */
	public boolean isEntreeExisteDansAutomate(int Entree){
		boolean OK = false;
		for(Map<Integer, Triplet> i : table)
			OK = OK || i.containsKey(Entree);
		return OK;
	}
	
	/**
	 * Renvoie True, si l'automate possède le numero de l'état passé en parametre
	 * @param Etat
	 * @return
	 */
	public boolean isEtatExisteDansAutomate(int Etat){
		return table.size()>Etat;
	}
	
	/**
	 * Renvoie la table de transition associé a l'etat donné en paramtre
	 * @param Etat
	 * @throws Exception Si l'etat n'existe pas dans l'automate
	 */
	public Map<Integer, Triplet> getEtatAll(int Etat) throws Exception{
		if (table.size()<=Etat) throw new Exception("L'etat renseigner n'existe pas dans la table");
		return (Map<Integer, Triplet>) table.get(Etat);
	}

	public int getEtatSuiv(int Etat, int Entree) throws Exception{
		if (table.size()<=Etat) throw new Exception("L'etat renseigner n'existe pas dans la table");
		if (!table.get(Etat).containsKey(Entree)) throw new Exception("L'entree renseignée n'existe pas dans la table");
		return table.get(Etat).get(Entree).EtatSuiv;
	}
	
	public int getSortie(int Etat, int Entree) throws Exception{
		if (table.size()<=Etat) throw new Exception("L'etat renseigner n'existe pas dans la table");
		if (!table.get(Etat).containsKey(Entree)) throw new Exception("L'entree renseignée n'existe pas dans la table");
		return table.get(Etat).get(Entree).Sortie;
	}
	
	public String toString(){
		String res = "Table De Transition et Sortie \n";
		for(int i=0; i<table.size(); i++){
			res += "ETAT " + i + " : ";
			for(Map.Entry<Integer,Triplet> e : table.get(i).entrySet()){
				res += " [ENTREE " + e.getKey() + " : etatSuiv " + e.getValue().EtatSuiv + ", SORTIE " + 
						e.getValue().Sortie + "], ";
			}
			res += "\n";
		}
		return res;
	}
}