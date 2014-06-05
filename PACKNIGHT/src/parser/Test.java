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
	static void afficheTableTransition()
	{
	   //On crée une List contenant tous les noeuds "tec" "tt" et "tea" de l'Element racine
	   List<Element> listTEC = racine.getChildren("tec");
	   List<Element> listTT  = racine.getChildren("tt");
	   List<Element> listTEA = racine.getChildren("tea");
	   
	   //On crée un Iterator sur nos listes
	   Iterator<Element> itr_tec = listTEC.iterator();
	   Iterator<Element> itr_tt  = listTT.iterator();
	   Iterator<Element> itr_tea = listTEA.iterator();
	   
	   while(i.hasNext())
	   {
	      //On recupere les trois elements voulue
	      Element value_tec = (Element)itr_tec.next();
	      Element value_tt  = (Element)itr_tt.next();
	      Element value_tea = (Element)itr_tea.next();
	      
	      //On affiche le nom de l’élément courant
	      System.out.println(value_tec. //getChild("nom"));
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
         document = sxb.build(new File("Exercice2.xml"));
      }
      catch(Exception e){}

      //On initialise un nouvel élément racine avec l'élément racine du document.
      racine = document.getRootElement();

      //Méthode définie dans la partie 3.2. de cet article
      //afficheALL();
   }
}