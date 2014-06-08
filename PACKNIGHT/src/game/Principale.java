/**
 * Cette objet est tout simplement le main, qui apelle game
 */
package game;

public class Principale {
static Game g;
static long duree = 10;

// prog principal
public static void main(String[] args) throws InterruptedException {
	// creation du jeu
	g = new Game();
	// appel à la boucle
	boucle();
}
// boucle de jeu
static void boucle() throws InterruptedException {
	// duree de la boucle

	while (g.fini() == false) {
	// recupere temps avant
	long avant = System.currentTimeMillis();
	g.update();
	g.render();
	// recupere temps aprés
	long apres = System.currentTimeMillis();
	// rendre la main
	Thread.sleep(duree - (apres - avant));
	}
	}
}
