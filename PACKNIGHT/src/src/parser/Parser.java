/**
 * Fonctions static, inutile d'instancier un parseur !
 * author : Alex
 * 
 */
package src.parser;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import org.jdom2.*;
import org.jdom2.input.*;
public class Parser {
	
   static private org.jdom2.Document document;
   static private Element racine;
   
   /**
    * Parse le fichier donnée en argument
    * Prend en argument les infos de l'automate a remplir
    * author : Alex
    */
   static public void parse(String file, int tabTrans[][],int tabSort[][],int etatInit,List<Integer> etatFin)
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
   
   
	static private void parserAll(int tabTrans[][],int tabSort[][],int etatInit,List<Integer> etatFin)
	{
	/*****************************
	 * Recuperation des tableaux
	 *****************************/
	   //On recup la liste des etats
	   List<Element> listEtat = racine.getChildren("Etat");
	   
	   //On crée un Iterator sur la liste d'etat
	   Iterator<Element> i = listEtat.iterator();
	   while(i.hasNext())
	   {
		   //Recup le prochain etat a traiter
		   Element etat = (Element)i.next();
		   
		   //Recup l'indice de l'état pour le tableau Transition
		   Integer Ietat = Integer.getInteger(etat.getAttributeValue("Nom"));
		   
		   //Meme action mais pour transition
		   List<Element> listTransition = etat.getChildren("Transition");
		   Iterator<Element> j = listTransition.iterator(); 
		  
		   
		   while(j.hasNext())
		   {
			   Element transition = (Element)j.next(); 
			   Integer Ientree = Integer.getInteger(transition.getAttributeValue("Entree"));
			   Integer Iarriver = Integer.getInteger(transition.getAttributeValue("Etat"));
			   Integer Isortie = Integer.getInteger(transition.getAttributeValue("Action"));
			   
			   tabSort[Ietat][Ientree] = Isortie;
			   tabTrans[Ietat][Ientree] = Iarriver;
		   }
	   }

		/*****************************
		 * Recuperation de l'état init
		 *****************************/

	   	//On recup la liste d'état initial mis dans la balise "Etats_Initiaux"
	   List<Element> listEtatInit = racine.getChildren("Etats_Initiaux");
	   
	   Iterator<Element> i3 = listEtatInit.iterator();
	   
	   while (i3.hasNext())
	   {
		   Element etat_initiale = (Element)i3.next();
		   Integer Ientree = Integer.getInteger(etat_initiale.getAttributeValue("Nom"));
		   etatInit = Ientree;
	   }
	   
		/*****************************
		 * Recuperation des etats final
		 *****************************/
		   //On recup la liste des etats finaux
		   List<Element> listEtatFinal = racine.getChildren("Etats_Finals");
		   
		   //On crée un Iterator sur cette liste
		   Iterator<Element> i2 = listEtatFinal.iterator();
		   
		   while (i2.hasNext())
		   {
			   Element etat_finals = (Element)i2.next();
			   
			   //on recupere la liste des etats final mis dans la balise "Etats_Finals"
			   List<Element> listeEtat = etat_finals.getChildren("Etat");
			   
			   Iterator<Element> j = listeEtat.iterator();
			   
			   while(j.hasNext())
			   {
				   //Recup le prochain etat a traiter
				   Element etat = (Element)j.next();
				   
				   //Recup l'indice de l'état pour le tableau Transition
				   Integer Ietat = Integer.getInteger(etat.getAttributeValue("Nom"));
				   
				   //on ajoute a la liste d'état final létat trouver
				   etatFin.add(Ietat);
			   }
		   	}
	}
}
