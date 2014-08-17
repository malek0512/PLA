package view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import model.personnages.Ghost;
import model.personnages.Personnage;

public abstract class Equipage {

	public static HashMap<String, String> automate = new HashMap<String, String>() {
		{put("Berserk", "fm_berserk.xml");}
		{put("Aleatoire", "fm_aleatoire.xml");}
		{put("Intercepteur", "fm_intersepteur.xml");}
		{put("Spectrum", "fm_lord.xml");}
		{put("Suiveur", "fm_suiveur.xml");}
		{put("None", null);}
	};
	
	public static Personnage joueurCamera;
	public static Personnage joueurFleche;
	public static Personnage joueurZQSD;
	public static Personnage joueurIJKL;
	public static Personnage joueur8456;

	public static List<String> getListOfPersonnage () {
		ArrayList<String> res = new ArrayList<>();
		for(String k : Equipage.automate.keySet()){
			res.add(k);
		}
		return res;
	}
	
	public void render() {
		for(Joueur j:Joueur.liste)
//			if (j.p.hitting() || j.p instanceof Ghost)
				j.render();
	}
	
	public abstract void create();
}
