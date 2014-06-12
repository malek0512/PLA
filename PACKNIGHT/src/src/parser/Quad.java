package src.parser;

public class Quad {
	//Classe intermediaire de Quad
		public int Entree, Sortie; public String EtatSuiv;
		public Quad(int entree, String etatSuiv, int sortie) {
			super();
			this.Entree = entree;
			EtatSuiv = etatSuiv;
			Sortie = sortie;
		}
}
