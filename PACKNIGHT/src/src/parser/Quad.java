package src.parser;

public class Quad {
	//Classe intermediaire de Quad
		public boolean ok; public int Entree, EtatSuiv, Sortie;
		public Quad(boolean ok, int entree, int etatSuiv, int sortie) {
			super();
			this.ok = ok;
			this.Entree = entree;
			EtatSuiv = etatSuiv;
			Sortie = sortie;
		}
}
