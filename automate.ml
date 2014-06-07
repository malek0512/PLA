

(* Types *)

type entree = int;;
type etat_name = int;;
type action = string;;

type etat = {
  nom : etat_name;
  transitions : (entree * etat_name * action) list
};;


type automate = {
  nom1 : string;
  etatsInitiaux : etat list;
  etatsFinals : etat list;
  etatsAutres : etat list
};;

(* Fonctions *)
let rec getTransitions (trans : (entree * etat_name * action) list): string =
  let getTransition transition =
  let (entry, nom_etat, act) = transition in
  " <Transition Entree= \""^ string_of_int (entry) ^ "\" Etat= \"" ^ string_of_int (nom_etat) ^ "\" Action = \"" ^ act ^ "\" > </Transition> \n" in

  match trans with
    |[] -> ""
    |a::tail -> getTransition a ^ getTransitions tail;;

let rec getEtats (e :etat list) : string =
  let getEtat (e : etat) : string =
  let {
    nom = name;
    transitions = trans
  }=e in
  "<Etat Nom= \""^ string_of_int name ^ "\" > \n" ^  getTransitions trans ^ "</Etat> \n"
  in
  match e with
    |[] -> ""
    |head::tail -> getEtat head ^ getEtats tail;;

let getAutomate (a : automate) : string =
  let  {
  nom1 = name;
  etatsInitiaux = etatInit;
  etatsFinals = etatEnd;
  etatsAutres = etatlist
  } = a in
  "<Automate ID= \""^ name ^"\" > \n" ^
    "<Etats_Initiaux> \n" ^
    getEtats etatInit ^
    "</Etats_Initiaux> \n" ^

    getEtats etatlist ^

    "<Etats_Finals> \n" ^
    getEtats etatEnd ^
    "<Etats_Finals> \n" ^
 "</Automate> \n";;

let entete = "<?xml version = \"1.0\" ?>";;
let outChannel = open_out"Fichier.xml";;
let writeAutomate (a:automate) =
  let string = getAutomate a in
  output outChannel (entete) 0 (String.length(entete));
  output outChannel (string) 0 (String.length(string))
  ;;

(********************************************************************)


let etat0 = {
  nom = 0;
  transitions = [(0,0,"Boire")]
};;

let etat1 = {
  nom = 0;
  transitions = [(0,0,"Boire")]
};;


let automata = {
  nom1="Fontome";
  etatsInitiaux = [etat0];
  etatsFinals = [etat3];
  etatsAutres = [etat1;etat2]
};;


let _= writeAutomate automata;;

close_out outChannel;;


