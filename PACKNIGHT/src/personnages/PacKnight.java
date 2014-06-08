/**
 * author : Alex
 */
package personnages;

import java.util.LinkedList;
import java.util.List;

public class PacKnight extends Pacman{

	/**
	 * liste des PacKnight sur le terrain
	 */
	static List<PacKnight> liste = new LinkedList<PacKnight>();
	static int vie = 10;
	
	public PacKnight(String name, int x, int y, Direction d) {
		super(name,x,y,d);
		PacKnight.liste.add(this);
	}

	public boolean canRespawn() {
		return vie != 0;
	}

	public void meurtDansDatroceSouffrance() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void respawn() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void gererCollision() {
		// TODO Auto-generated method stub
		
	}

	
}
