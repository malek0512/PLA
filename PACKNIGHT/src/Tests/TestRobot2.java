package Tests;
import controleur.automate.Automate;
import personnages.CoordonneesFloat;
import personnages.Direction;
import personnages.PacKnight;
import personnages.PacPrincess;
import personnages.Personnage;
import structure_terrain.Terrain;
import structure_terrain.Terrain1;


public class TestRobot2 {

	static PacKnight PK;
	static PacPrincess PR;
	
	public static void main(String[] args) throws Exception {
		PK = new PacKnight("", 1, 1, Direction.droite, new CoordonneesFloat(1,1));
		PR = new PacPrincess("", 1, 1, Direction.droite, new CoordonneesFloat(1,1));
		Automate k = new Automate("Automate/Packnight.xml",PK); //"Fichier.xml");
		Automate p = new Automate("Automate/Princesse.xml", PR);
		
		//PM.insererAutomate(a);
		Terrain terrain = new Terrain1(6, 6);
		Personnage.initTerrain(terrain);
		//a.setPersonnage(PM);
		while ( /*a.getEntree() != Automate.SORTIE_TERRAIN || */!p.isEtatFinal()){
			
			System.out.println(PK.toString());
			System.out.println(PR.toString());
			System.out.println(k.infoAutomate());
			System.out.println(p.infoAutomate());
			
			p.suivant();
			k.suivant();
			Thread.sleep(1000); //Attend 1 secondes
		}
		
		System.out.println(PK.toString());
		System.out.println(PR.toString());
		System.out.println(k.infoAutomate());
		System.out.println(p.infoAutomate());
//		System.out.println(PM.getControleur().infoAutomate());
		
	}

}
