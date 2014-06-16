package controleur.iu;


import controleur.Controleur;

public class InterfaceUser extends Controleur {
	
	private InterfaceUser[] iu; 
	
	public InterfaceUser(int nb_joueur){
		setIu(new InterfaceUser[nb_joueur]);
	}
	/**
	 * Renvoie le tableau de tous les joueurs présents*/
	public InterfaceUser[] getIu() {
		return iu;
	}
	/**
	 * Remplace un tableau de joueurs par un autre
	 * Utilisation : Mort d'un des joueurs donc maj de iu*/
	public void setIu(InterfaceUser[] iu) {
		this.iu = iu;
	}
	
	

}
