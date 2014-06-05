/**
 * Author : Alex
 * Utiliter privé.
 * Sert a faire des tests sur xml :D
 */

package parser;

import java.io.*;

import org.jdom2.*;
import org.jdom2.input.*;

//import org.jdom2.filter.*; //Pour les filtres
import java.util.List; // pour les listes
import java.util.Iterator;

public class Test
{
	/**
	 * affiche a l'ecran les informations lu, infos sel
	 */
	static void afficheALL()
	{
	   //On crée la liste des etats
	   List<Element> listEtat = racine.getChildren("Etat");
	   
	   //On crée un Iterator sur la liste d'etat
	   Iterator<Element> i = listEtat.iterator();
	   
	   while(i.hasNext())
	   {
	      //On recupere les trois elements voulue
	      Element etat = (Element)i.next();
	      
	      //On crée la liste des transition de l'état
	      List<Element> listTransition = etat.getChildren("Transition");
	      
	      //On crée un Iterator sur la liste de transition
	      Iterator<Element> j = listTransition.iterator();
	      
	      //On affiche le nom de l’élément courant
	      System.out.println("Nom : " + etat.getAttributeValue("Nom"));
	      System.out.println("Action : " + etat.getAttributeValue("Action"));
	      System.out.println("Nom : " + etat.getAttributeValue("Nom"));
	      
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
   static org.jdom2.Document document;
   static Element racine;

   public static void main(String[] args)
   {
      //On crée une instance de SAXBuilder
      SAXBuilder sxb = new SAXBuilder();
      try
      {
         //On crée un nouveau document JDOM avec en argument le fichier XML
         //Le parsing est terminé ;)
         document = sxb.build(new File("Test.xml"));
      }
      catch(Exception e){}

      //On initialise un nouvel élément racine avec l'élément racine du document.
      racine = document.getRootElement();

      //Méthode définie dans la partie 3.2. de cet article
      afficheALL();
   }
}