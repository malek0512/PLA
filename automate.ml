

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
  etats : etat list;
  etatsFinals : etat list;
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

let rec getEtatsFinals (e :etat list) : string =
  let getEtat (e : etat) : string =
    let {nom = name;
	 transitions = trans
	} = e in
  "<Etat Nom= \""^ string_of_int name ^ "\" > \n" ^ "</Etat> \n"
  in
  match e with
    |[] -> ""
    |head::tail -> getEtat head ^ getEtatsFinals tail;;

let getAutomate (a : automate) : string =
  let  {
  nom1 = name;
  etats = etat;
  etatsFinals = etatEnd;
  } = a in
  "<Automate ID= \""^ name ^"\" > \n" ^
    getEtats etat ^

    "<Etats_Finals> \n" ^
       getEtatsFinals etatEnd ^
    "</Etats_Finals> \n" ^
 "</Automate> \n";;

let entete = "<?xml version = \"1.0\" ?>\n";;
let outChannel = open_out"Fichier.xml";;
let writeAutomate (a:automate) =
  let string = getAutomate a in
  output outChannel (entete) 0 (String.length(entete));
  output outChannel (string) 0 (String.length(string))
  ;;

(********************************************************************)
(*
type entree = int;;
type etat_name = int;;
type action = string;;

type etat = {
  nom : etat_name;
  transitions : (entree * etat_name * action) list
};;


type automate = {
  nom1 : string;
  etats : etat list;
  etatsFinals : int list;
};;
*)
(*******************************************************************)

let etat0 = {
  nom = 0;
  transitions = [(0,0,"0"); (2,1,"2")]
};;

let etat1 = {
  nom = 1;
  transitions = [(2,1,"2")]
};;


let automata = {
  nom1="Fontome";
  etats = [etat0; etat1];
  etatsFinals = [etat1];
};;


let _= writeAutomate automata;;

close_out outChannel;;


