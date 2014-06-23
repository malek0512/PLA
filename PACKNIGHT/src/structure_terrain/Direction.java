package structure_terrain;

public enum Direction {
	haut,
	gauche,
	bas,
	droite;
	
	public Direction opposer(){
		Direction opp = null;
		switch(this){
		case haut: opp=bas;break;
		case bas: opp=haut;break;
		case gauche: opp=droite;break;
		case droite: opp=gauche;break;
		}
		return opp;
	}

}
