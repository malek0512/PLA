package parser;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

public class Parser {
	
   private org.jdom2.Document document;
   private Element racine;
   
   /**
    * Parse le fichier donnée en argument
    * Prend en argument les infos de l'automate a remplir
    */
   public void parse(String file, int tabTrans[][],String tabSort[][],int etatInit,List<Integer> etatFin)
   {
	
    //On crée une instance de SAXBuilder
    SAXBuilder sxb = new SAXBuilder();
    try
    {
       //On crée un nouveau document JDOM avec en argument le fichier XML
       document = sxb.build(new File(file));
    }
    catch(Exception e){}

    //On initialise un nouvel élément racine avec l'élément racine du document.
    racine = document.getRootElement();

    //Méthode définie dans la partie 3.2. de cet article
    parserAll(tabTrans,tabSort,etatInit,etatFin);
   }
   
   
	private void parserAll(int tabTrans[][],String tabSort[][],int etatInit,List<Integer> etatFin)
	{
	   //On crée fichierXML la liste des etats
	   List<Element> listEtat = racine.getChildren("Etat");
	   
	   //On crée un Iterator sur la liste d'etat
	   Iterator<Element> i = listEtat.iterator();
	   
	   while(i.hasNext())
	   {
		   //Balise du prochain etat a traité
		   Element etat = (Element)i.next();
		   
		   //Balise des infos de transition
		   List<Element> listTransition = etat.getChildren("Transition");
		   Iterator<Element> j = listTransition.iterator(); 
		   Element transition = (Element)j.next();
	      
	      //On affiche le nom de l’élément courant
	      Integer Ientre = Integer.getInteger(etat.getAttributeValue("Nom"));
	      Integer Ietat = Integer.getInteger(etat.getAttributeValue("Etat"));
	      String Iaction = etat.getAttributeValue("Etat");
	      
	      tabSort
	      
	      while(j.hasNext())
	      {
	    	//On recupere les trois elements voulue
		    Element transition = (Element)j.next();
		    
		    //On affiche les infos de transition
		    System.out.println("Entree : " + transition.getAttributeValue("Entree"));
		    System.out.println("Etat : " + transition.getAttributeValue("Etat"));
	      }
	      
	   }
	}
}
