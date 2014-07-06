package highscores;

import game.WindowGame;

import java.io.Serializable;

import personnages.PacKnight;
import structure_terrain.Terrain;

public class Score implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private int score;
    private String name;

    public int getScore() {
        return score;
    }

    public String getName() {
        return name;
    }

    public Score(String name, int score) {
        this.score = score;
        this.name = name;
    }
    
    public static int CalculateScoreFinal(){
    	
    	int res = (Terrain.nb_pacgum_init-Terrain.nb_pacgum)*10;
    	res+=(100*PacKnight.vie)-WindowGame.time/100;
    	
    	return res;
    	
    }
    
    public static String ScoreToString(){
    	int res=(Terrain.nb_pacgum_init-Terrain.nb_pacgum)*10;
       	String score="";
       	String valeur=String.valueOf(res);
       	String tmp=valeur;
    	while(tmp.length()<4){
    		score+="0";
    		tmp=score+valeur;
    	}
    	score+=valeur;
    	return score;
    	
    }
}
