package controleur.automate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import src.parser.Quad;


public class TableTransitionSortie {
	//Classe intermediaire de triplet
	public class Triplet {
		public String EtatSuiv; int Sortie;
		public Triplet(String etatSuiv, int sortie) {
			EtatSuiv = etatSuiv;
			Sortie = sortie;
		}
	}
	
	Map<String, Map<Integer,Triplet>> table;
	public TableTransitionSortie(){
		table = new HashMap<String, Map<Integer,Triplet>>();
	}

	/**
	 * Initialise la table de transition avec ArrayList de liste chainée contenant le type 
	 * 		Quad = {int Entree, int EtatSuiv, int Sortie }
	 * @param TransitionSortie
	 */
	public void initTransitionSortie(Map<String,List<Quad>>TransitionSortie ){
		for(Entry<String,List<Quad>> i : TransitionSortie.entrySet()){
			table.put(i.getKey(), new HashMap<Integer,Triplet>());
			for(Quad q: i.getValue()){
				table.get(i.getKey()).put(q.Entree, new Triplet(q.EtatSuiv,q.Sortie));
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
		for(Entry<String, Map<Integer, Triplet>>  s: table.entrySet()){
//			for(Map<Integer, Triplet> i : s.getValue())
				OK = OK || s.getValue().containsKey(Entree);
		}
		return OK;
	}
	
	/**
	 * Renvoie True, si l'automate possède le numero de l'état passé en parametre
	 * @param Etat
	 * @return
	 */
	public boolean isEtatExisteDansAutomate(int Etat){
		return table.containsKey(Etat);
	}
	
	/**
	 * Renvoie la table de transition associé a l'etat donné en paramtre
	 * @param Etat
	 * @throws Exception Si l'etat n'existe pas dans l'automate
	 */
	public Map<Integer, Triplet> getEtatAll(String Etat) throws Exception{
		if (!table.containsKey(Etat)) throw new Exception("L'etat renseigner n'existe pas dans la table");
		return (Map<Integer, Triplet>) table.get(Etat);
	}

	public String getEtatSuiv(String Etat, int Entree) throws Exception{
		if (!table.containsKey(Etat)) throw new Exception("L'etat renseigner n'existe pas dans la table");
		if (!table.get(Etat).containsKey(Entree)) throw new Exception("L'entree renseignée n'existe pas dans la table");
		return table.get(Etat).get(Entree).EtatSuiv;
	}
	
	public int getSortie(String Etat, int Entree) throws Exception{
		if (!table.containsKey(Etat)) throw new Exception("L'etat renseigner n'existe pas dans la table");
		if (!table.get(Etat).containsKey(Entree)) throw new Exception("L'entree renseignée n'existe pas dans la table");
		return table.get(Etat).get(Entree).Sortie;
	}
	
	public String toString(){
		String res = "Table De Transition et Sortie \n";
		for(Entry<String, Map<Integer, Triplet>> i : table.entrySet()){
			res += "ETAT " + i.getKey() + " : ";
			for(Map.Entry<Integer,Triplet> e : i.getValue().entrySet()){
				res += " [ENTREE " + e.getKey() + " : etatSuiv " + e.getValue().EtatSuiv + ", SORTIE " + 
						e.getValue().Sortie + "], ";
			}
			res += "\n";
		}
		return res;
	}
}
