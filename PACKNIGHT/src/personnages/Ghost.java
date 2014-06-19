package personnages;
import graph.Aetoile;
import graph.Graph;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Ghost extends Personnage {
	
	/**
	 * liste des fantomes du jeux
	 * cette liste est utiliser pour gerer les collisions, et pour
	 * que les fantomes puissent communiquer entre eux
	 * author : alex
	 */
	public static List<Ghost> liste = new LinkedList<Ghost>();


	
		//info divers
	private final int vision = 5;
	private CoordonneesFloat pointDeRespawn;
	private int nbInterChercher = 4; //nombre d'inter calculer par fantome lord pour les ganks
	
	
	static public Graph g;
	
	
	//boolean des animations
	private boolean prisonner = false; //le fantome est dans la prison
	private boolean stun = false;
	private boolean entendEtObei = false;
	
	//timeur des animation
	final static private int tempsPasserEnPrison = 1; 
	final static private int tempsStun = 15;
	final static private int tempsPrisonner = 10;
	
	//attribut pour les fantomes qui recoivent des ordres
	private CoordonneesFloat caseDOrdre;
	private List<CoordonneesFloat> ordre;
	
	public Ghost(String nom, int x, int y, Direction d,CoordonneesFloat spawn) {
		super(nom, x, y, d);
		this.agonise = false;
		this.pointDeRespawn = new CoordonneesFloat(spawn.x * 32, spawn.y * 32);
		Ghost.liste.add(this);
		
		
	}
	//getter de base
	public boolean getisAlive(){
		return !(agonise);
	}
	/**
	 * Met à jour l'état vivant ou mort du fantome*/
	public void setIsAlive(boolean a){
		agonise=a;
		
	}
	
	public boolean getEntendEtObei(){
		
		return entendEtObei;
	}
	
	/**
	 * Structure qui repertorie l'ensemble des information d'un PM en fuite
	 * */
	public class AvisDeRecherche {
		boolean Mort;
		public CoordonneesFloat coord;
		public int timer;

		public AvisDeRecherche(CoordonneesFloat c) {
			coord = new CoordonneesFloat(c);
			timer=300;
		}
		
		public void majAvisDeRecherche(CoordonneesFloat c){
			timer=300;
			coord=c;
			
			
		}
	}

	/**
	 * Le central repertorie l'ensemble des information des PM en fuite
	 */
	public static Map<Pacman, AvisDeRecherche> central=new HashMap<Pacman, AvisDeRecherche>();
	
	/**
	 * Supprime le Pacman de la centrale si le timer est à 0*/
	public static void disparitionPacman(){
		for(Iterator<Pacman> i = Pacman.liste.iterator();i.hasNext();){
			Pacman pac = i.next();
			if(central.containsKey(pac)){
				if(central.get(pac).timer==0)
					central.remove(pac);
				else 
					central.get(pac).timer--;
			}
	
		}
	}
	/**
	 * Gère la collision avec les pacmans*/
	public void gererCollision() {
		Iterator<PacKnight> i = PacKnight.liste.iterator();
		while(i.hasNext() && this.hitting())
		{
			PacKnight g = i.next();
			if(g.hitting() && hitBoxManager.HitBoxManager.personnageHittingPersonnage(this.coord, g.coord))
			{
				this.meurtDansDatroceSouffrance();
				g.meurtDansDatroceSouffrance();
				break;
			}
		}		
		Iterator<PacPrincess> j = PacPrincess.liste.iterator();
		while(i.hasNext() && this.hitting() )
		{
			PacPrincess g = j.next();
			if(hitBoxManager.HitBoxManager.personnageHittingPersonnage(this.coord, g.coord))
			{
				g.meurtDansDatroceSouffrance();
				break;
			}
		}
	}
	
	/**
	 * etourdi le fantome
	 */
	public void stun()
	{
		this.stun = true;
	}

	public int getVision() {
		return vision;
	}

	/**
	 * Pourquoi ne pas la mettre dans personnage car elle est commune aux pacman et aux Ghost
	 * */
	public void respawn()
	{
		this.agonise = true;
	}
	
	/**
	 * parametre le pac-man pour qu'il fasse un respawn sans animation
	 * effectuer une fois que l'animation est fini
	 */
	protected void respawnWOA() {
		this.coord = new CoordonneesFloat(this.pointDeRespawn);
		this.prisonner = true;
	}
	
	/**
	 * fait mourrir le ghost pour un moment restreint :D
	 */
	public void meurtDansDatroceSouffrance() {
		this.agonise = true;
	}

	/**
	 * renvoie vraie si le ghost est parametrable par un automate a un l'instant courant
	 */
	public boolean parametrable() {
		return !(agonise || prisonner || stun || entendEtObei);
	}

	/**
	 * fait avancer d'un cran les animations en cours
	 */
	public void avancerAnimation() {
		if(agonise)
		{
			if(this.timerAnimation < Pacman.tempsPasserMort)
			{
				this.timerAnimation++;
				//faire avancer d'un cran l'animation
			}
			else
			{
				this.timerAnimation=0;
				this.agonise=false;
				this.respawnWOA();
			}
		}
		if(prisonner)
		{
			if(this.timerAnimation < Ghost.tempsPasserEnPrison)
			{
				this.timerAnimation++;
			}
			else
			{
				this.timerAnimation = 0;
				this.prisonner = false;
			}
		}
		if(stun)
		{
			if(this.timerAnimation < Ghost.tempsStun)
			{
				this.timerAnimation++;
			}
			else
			{
				this.timerAnimation = 0;
				this.stun = false;
			}
		}
		if(entendEtObei)
		{
				executerOrdre();
		}
	}

	/**
	 * fonction misterieuse qui renvoie la direction a prendre pour aller
	 * de la case src vers la case dest
	 * @param src : case source
	 * @param dest : case destination
	 * @return la direction a suivre pour aller a dest
	 */
	public Direction mysteriousFunction(CoordonneesFloat src, CoordonneesFloat dest)
	{
		int x = src.x - dest.x;
		int y = src.y - dest.y;
		
		if(x==0)
		{
			if(x==-1)
				return Direction.droite; 
			else
				return Direction.gauche;
		}
		else//y == 0
		{
			if(y==-1)
				return Direction.bas;
			else
				return Direction.haut;
		}
	}
	
	/**
	 * fait recevoir au fantome un ordre
	 * @param l : la liste des case a parcourir
	 */
	public void recoitOrdre(List<CoordonneesFloat> l)
	{
		//HA HA
		//l.remove(0); //on retire la tete, on y est déja !
		//HA HA
		if(!l.isEmpty())
		{
			this.ordre = l;
			this.entendEtObei = true;
			this.caseDOrdre = l.get(0);
			this.direction = mysteriousFunction(this.coord.CasCentre(), caseDOrdre);
		}
	}
	
	/**
	 * avance a la prochaine case de l'ordre
	 */
	private void executerOrdre()
	{
		if(this.coord == this.caseDOrdre)
		{
			ordre.remove(0);
			if(!ordre.isEmpty())
			{
				caseDOrdre = ordre.get(0);
				direction = mysteriousFunction(this.coord, caseDOrdre);
				avancer();
			}
			else
				entendEtObei = false;
		}
		else
			avancer();
	}

	/**
	 * renvoie vrai si la gestion de collision inter perso est autorisé pour le personnage
	 */
	public boolean hitting() {
		return !(agonise);
	}
	
	/**
	 * donne des ordre au fantomes pour coincé un pacman donné
	 */
	public void donnerDesOrdres(Pacman ref)
	{
		int xref = ref.coord.x;
		int yref = ref.coord.y;
		//reboot du graph
		Ghost.g.reset();
		// calcule des intersection a occuper
		List<CoordonneesFloat> listeDesInter = Ghost.g.visiterLargeur(ref.coord,nbInterChercher);
		
		//copie de la liste des fantomes

		List<Ghost> listeDesGhost = new LinkedList<Ghost>(Ghost.liste);


		
		// pour chaque inter
		Iterator<CoordonneesFloat> i = listeDesInter.iterator();
		while(i.hasNext())
		{
			CoordonneesFloat interEnTraitement = i.next();
			// variable temporaire
			Ghost meilleurCandidat = null;
			int distanceMeilleurCandidat = Integer.MAX_VALUE;
			
			// calcul de la distance max entre le fantome et l'inter
			int dmax = Math.abs(xref - interEnTraitement.x) + Math.abs(yref - interEnTraitement.y);
			dmax += 2; //parceque je suis sadic :3
			
			// calcul du fantome qui doit y aller
			Iterator<Ghost> ig = listeDesGhost.iterator();
			
			int indice = 0;
			int cpt = 0;
			while(ig.hasNext())
			{	//creation du candidat
				Ghost actuelCandidat = ig.next();
				//test si le candidat peut obtenir des ordres
				if(actuelCandidat.parametrable())
				{
					int dactuelCandidat = Math.abs(actuelCandidat.coord.x - interEnTraitement.x) 
										+ Math.abs(actuelCandidat.coord.y - interEnTraitement.y);
					
					if(dactuelCandidat < dmax && dactuelCandidat < distanceMeilleurCandidat)
					{	//maj du candidat
						meilleurCandidat = actuelCandidat;
						distanceMeilleurCandidat = dactuelCandidat;
						indice = cpt;
					}
				}
				//on incremente notre compteur dans tout les cas !
				cpt++;
			}
			if(meilleurCandidat != null)
			{
				//supprime le fantome de la liste
				listeDesGhost.remove(indice);
				
				//calcul de l'itinéraire
				Aetoile ga = new Aetoile(meilleurCandidat.coord);
				List<CoordonneesFloat> ordre = ga.algo(interEnTraitement);
				meilleurCandidat.recoitOrdre(ordre);
			}
		}	
	}
}