/**
 * Fonctions static, inutile d'instancier un parseur !
 * author : Alex
 */
package src.parser;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

public class Parser {
	
   static private org.jdom2.Document document;
   static private Element racine;
   
   /**
    * Parse le fichier donnée en argument
    * Prend en argument les infos de l'automate a remplir
    */
   static public void parse(String file, int tabTrans[][],String tabSort[][],int etatInit,List<Integer> etatFin)
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
   
   
	static private void parserAll(int tabTrans[][],String tabSort[][],int etatInit,List<Integer> etatFin)
	{
	   //On crée fichierXML la liste des etats
	   List<Element> listEtat = racine.getChildren("Etat");
	   
	   //On crée un Iterator sur la liste d'etat
	   Iterator<Element> i = listEtat.iterator();
	   
	   while(i.hasNext())
	   {
		   //Balise du prochain etat a traité
		   Element etat = (Element)i.next();
		   
		   //Recup l'indice de l'état pour le tableau Transition
		   Integer Ietat = Integer.getInteger(etat.getAttributeValue("Nom"));
		   
		   //Meme action mais pour transition
		   List<Element> listTransition = etat.getChildren("Transition");
		   Iterator<Element> j = listTransition.iterator(); 
		  
		   
		   while(j.hasNext())
		   {
			   Element transition = (Element)j.next(); 
			   Integer Itest = Integer.getInteger(transition.getAttributeValue("Entree"));
			   Integer Iarriver = Integer.getInteger(transition.getAttributeValue("Etat"));
			   String Iaction = transition.getAttributeValue("Action");
			   
			   tabSort[Ietat][Itest] = Iaction;
			   tabTrans[Ietat][Itest] = Iarriver;
			   
		   }
	   }
	}
}
