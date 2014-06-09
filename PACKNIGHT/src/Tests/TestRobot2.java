package Tests;
import controleur.automate.Automate;
import personnages.Direction;
import personnages.Pacman;
import personnages.Personnage;
import personnages.RobotIdiot;
import structure_terrain.Case;
import structure_terrain.Terrain;


public class TestRobot2 {

	static RobotIdiot PM;
	
	public static void main(String[] args) throws Exception {
		PM = new RobotIdiot(0,0,Direction.bas);
		//Automate a = new Automate(); //"Fichier.xml");
		Automate a = new Automate("src/Tests/Test2.xml", PM);
		//PM.insererAutomate(a);
		PM.initTerrain(5, 5);
		//a.setPersonnage(PM);
		while ( a.getEntree() != Automate.SORTIE_TERRAIN || !a.isEtatFinal()){
			
			System.out.println(PM.toString());
			System.out.println(a.infoAutomate());
			//PM.suivant();
			a.suivant();
			Thread.sleep(1000); //Attend 1 secondes
		}
		System.out.println(PM.toString());
		System.out.println(a.infoAutomate());
//		System.out.println(PM.getControleur().infoAutomate());
		
	}

}
