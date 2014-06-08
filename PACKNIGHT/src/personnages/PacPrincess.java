/**
 * author alex
 */

package personnages;

import java.util.LinkedList;
import java.util.List;

public class PacPrincess extends Pacman{
	
	/**
	 * liste des PacPrincess sur le terrain
	 */
	static List<PacPrincess> liste = new LinkedList<PacPrincess>();
	private int vie = 2;
	
	public PacPrincess(String name, int x, int y, Direction d) {
		super(name,x,y,d);
		PacPrincess.liste.add(this);
	}

	public boolean canRespawn() {
		return vie !=0;
	}

	@Override
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
