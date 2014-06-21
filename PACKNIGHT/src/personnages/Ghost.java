package personnages;
import game.Accueil;
import graph.Aetoile;
import graph.Graph;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import music.MusicManager;
import structure_terrain.Terrain;

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
	
		//info pour le pouvoir de controle
	static private int nbInterChercher = 4; //nombre d'inter calculer par fantome lord pour les ganks
	static private int fantomeUp=0; //fantome up pour le pouvoir
	static public Graph g; //graph pour le pouvoir
	//attribut pour les fantomes qui recoivent des ordres
	private CoordonneesFloat caseDOrdre = null; //case en cours
	private List<CoordonneesFloat> ordre = null; //liste des case de l'ordre en cours
	
		//boolean des animations
	private boolean prisonner = false; //le fantome est dans la prison
	private boolean stun = false;
	private boolean entendEtObei = false;
	private boolean sortiePrison = false;
	
		//timeur des animation
	static private int tempsPasserEnPrison = 230; //pair
	final static private int tempsStun = 15;
	
	static public boolean modeMulti;

	
	/**
	 * @param position ou on veut savoir si un personnage si trouve
	 * @return renvoie vrai si un objet Personnage se trouve sur la position indiquer
	 */
	static public boolean personnagePresent(CoordonneesFloat position)
	{
		Iterator<Ghost> i= Ghost.liste.iterator();
		while (i.hasNext()) {
			if (i.next().coord.CasCentre().equals(position))
				return true;
		}
		return false;
	}
	
	static public boolean personnagePresentCas(CoordonneesFloat position)
	{
		Iterator<Ghost> i= Ghost.liste.iterator();
		while(i.hasNext())
		{
			if(position.equals(i.next().coord.CasCentre()))
				return true;
		}
		return false;
	}
	
	static public List<CoordonneesFloat> listCoord()
	{
		List<CoordonneesFloat> listRes = new LinkedList<CoordonneesFloat>();
		Iterator<Ghost> i= Ghost.liste.iterator();
		while (i.hasNext()) {
				listRes.add(i.next().coord.CasCentre());
		}
		return listRes;
	}

	static public int distance(CoordonneesFloat c)
	{
		int min = Integer.MAX_VALUE;
		Iterator<Ghost> i = Ghost.liste.iterator();
		while(i.hasNext())
		{
			int aux = i.next().coord.CasCentre().distance(c);
			if( aux < min)
				min = aux;
		}
		return min;
	}
	
	static public boolean powerUp()
	{
		return fantomeUp>=nbInterChercher;
	}
	
	static public void init()
	{
		if(modeMulti)
			Ghost.initAlea();
		else
			Ghost.initMap0();
	}
	
	static public void initAlea()
	{
		Iterator<Ghost> i= Ghost.liste.iterator();
		while (i.hasNext()) {
			i.next().respawnWOA();
		}
		Ghost.tempsPasserEnPrison=0;
	}
	
	static public void initMap0()
	{
		Iterator<Ghost> i= Ghost.liste.iterator();
		int timer = 0;
		int x = 12*32;
		int y = 14*32;
		while (i.hasNext()) {
			Ghost g = i.next();
			g.prisonner=true;
			g.timerAnimation -= timer*55;
			g.pointDeRespawn = new CoordonneesFloat(12*32,14*32);
			g.coord.x = x;
			g.coord.y = y;
			timer++;
			x+= 32;
			g.direction=Direction.droite;
		}
	}
	
	public Ghost(String nom) {
		super(nom, 1, 1, Direction.bas);
		this.agonise=false;
		Ghost.liste.add(this);
		Ghost.fantomeUp++;
	}
	
	public Ghost(String nom, int x, int y, Direction d) {
		super(nom, x, y, d);
		this.agonise = false;
		this.pointDeRespawn = new CoordonneesFloat(x * 32,y * 32);
		Ghost.liste.add(this);
		Ghost.fantomeUp++;
		tempsPasserEnPrison = 0;
	}
	
	public Ghost(String nom, int x, int y, Direction d, int timer) {
		super(nom, x, y, d);
		this.agonise = false;
		this.pointDeRespawn = new CoordonneesFloat(x * 32, y * 32);
		Ghost.liste.add(this);
		Ghost.fantomeUp++;
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
		if(Ghost.central.isEmpty())
		{
			MusicManager.reperer.stop();
			Accueil.Music_WindowGame.resume();
			System.out.println("changement de music 2 -> 1");
		}
	}
	/**
	 * Gère la collision avec les pacmans*/
	public void gererCollision() {
		Iterator<PacKnight> i = PacKnight.liste.iterator();
		while(this.hitting() && i.hasNext() )
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
		while(this.hitting() && i.hasNext())
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
		if(!Ghost.modeMulti)
		{
			this.coord = new CoordonneesFloat(this.pointDeRespawn);
			this.direction = Direction.droite;
			this.prisonner = true;
		}
		else
		{
			Random r = new Random();
			Terrain t = Personnage.getTerrain();
			int x;
			int y;
			do
			{
			x = r.nextInt(t.largeur);
			y = r.nextInt(t.hauteur);
			}while(!(t.caseAcessible(x, y) && Pacman.distance(new CoordonneesFloat(x, y))>(vision+2)));
			this.coord = new CoordonneesFloat(x*32, y*32);
		}
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
		return !(agonise || prisonner || stun || entendEtObei || sortiePrison);
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
				if(!this.caseDevantDisponible())
					this.direction = this.direction.opposer();
				this.avancer();
			}
			else
			{
				this.timerAnimation = 0;
				this.prisonner = false;
				this.sortiePrison = true;
				this.direction = Direction.haut;
			}
		}
		if(sortiePrison)
		{
			if(this.timerAnimation < 24)
			{
				this.timerAnimation++;
				this.avancerAux();
			}
			else
			{
				this.timerAnimation = 0;
				this.sortiePrison = false;
				this.direction = Direction.droite;
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
				this.executerOrdre();
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

		if (y == 0) {
			if (x == -1)
				return Direction.droite;
			else
				return Direction.gauche;
		} else// y != 0
		{
			if (y == -1)
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
		if(!l.isEmpty())
		{
			this.ordre = l;
			this.entendEtObei = true;
			fantomeUp--;
			this.caseDOrdre = l.get(0);
			this.coord = new CoordonneesFloat(this.caseDOrdre.x*32,this.caseDOrdre.y*32);
			ordre.remove(0);
			this.caseDOrdre = l.get(0);
			this.direction = mysteriousFunction(coord.CasCentre(), caseDOrdre);
			this.avancer();
		}
	}
	
	/**
	 * avance a la prochaine case de l'ordre
	 * cette case doit se situé dans l'index 1 de la liste
	 * si il n'y a pas cette case, on arrete d'executer des ordres
	 */
	private void executerOrdre()
	{
		if(this.coord.CasBG().equals(this.coord.CasHD()))
		{
			if(!ordre.isEmpty())
			{
				ordre.remove(0);
				if(!ordre.isEmpty())
				{
					caseDOrdre = ordre.get(0);
					direction = mysteriousFunction(coord.CasCentre(), caseDOrdre);
					avancer();
				}
			}
			else
			{
				entendEtObei = false;
				fantomeUp++;
				if(!this.caseDevantDisponible())
				{
					for(Direction d : Direction.values())
					{
						if(this.caseDisponible(d))
						{
							this.direction = d;
							break;
						}
					}
				}
			}
		}
		else
			this.avancer();
		
	}


	public boolean hitting() {
		return !(agonise) && !(prisonner) && !(sortiePrison);
	}
	
	
	/**
	 * donne des ordre au fantomes pour coincé un pacman donné
	 */
	public void donnerDesOrdres(Pacman ref)
	{
		if(Ghost.powerUp())
		{
			System.out.println("Fantomes dispos");
			CoordonneesFloat refCasCentre = ref.coord.CasCentre();
			
			//reboot du graph
			Ghost.g.reset();
			
			// calcule des intersection a occuper
			List<CoordonneesFloat> listeDesInter = Ghost.g.visiterLargeur(ref.coord.CasCentre(),nbInterChercher);
			
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
				int dmax = interEnTraitement.distance(refCasCentre);
				
				dmax += 25; //parceque je suis sadic :3
				//des fantomes se deplaceront meme si ils ne sont pas sur de le coincé, ca fiche le stress
				
				// calcul du fantome qui doit y aller
				Iterator<Ghost> ig = listeDesGhost.iterator();
				
				int indice = 0; //index de la liste fantome
				int cpt = 0; //cpt pour savoir l'index en cours de test
				while(ig.hasNext())
				{
					//creation du candidat
					Ghost actuelCandidat = ig.next();
					//test si le candidat peut obtenir des ordres
					if(actuelCandidat.parametrable())
					{
						int dactuelCandidat = interEnTraitement.distance(actuelCandidat.coord.CasCentre());
						
						if(dactuelCandidat < dmax && dactuelCandidat < distanceMeilleurCandidat)
						{
							//maj du candidat
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
					Aetoile ga = new Aetoile(meilleurCandidat.coord.CasCentre());
					List<CoordonneesFloat> ordre = ga.algo(interEnTraitement);
					meilleurCandidat.recoitOrdre(ordre);
				}
			}	
			System.out.println("sortie");
		}
	}
}