package Tests;
import controleur.automate.Automate;
import personnages.Direction;
import personnages.Pacman;
import personnages.Personnage;
import personnages.RobotIdiot;
import structure_terrain.Case;
import structure_terrain.Terrain;


public class TestRobot {

	static RobotIdiot PM;
	
	public static void main(String[] args) throws Exception {
		PM = new RobotIdiot(0,0,Direction.bas);
		Automate a = new Automate("Fichier.xml");
		PM.insererAutomate(a);
		PM.initTerrain(5, 5);
		
//		while ( !(PM.getControleur().isEtatFinal()) && !(PM.estSortie())){
//			
//			System.out.println(PM.toString());
//			System.out.println(PM.getControleur().infoAutomate());
//			PM.suivant();
//			Thread.sleep(3000); //Attend 3 secondes
//		}
		System.out.println(PM.toString());
		System.out.println(PM.getControleur().infoAutomate());
	}

}
