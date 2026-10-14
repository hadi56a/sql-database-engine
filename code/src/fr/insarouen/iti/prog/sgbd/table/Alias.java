package fr.insarouen.iti.prog.sgbd.table;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

import fr.insarouen.iti.prog.sgbd.valeur.Valeur;

/** 
 * Représente une source dans une clause FROM avec un alias optionnel.
 * Par exemple : FROM employes AS E → Alias("E", tableEmployes)
 * Permet de renommer les attributs d'une table avec un préfixe
 * pour gérer les jointures et auto-jointures.
 * 
 * @author Groupe 14
 * @version 1.0
 * 
 */
public class Alias{
    /** Table de reference  */
    private Table table;
    /** Le nom de l'alias  */
    private String alias;

    /**
     * Constructeur de l'Alias
     * @param table la table originale
     * @param alias le nom de l'alias
     */
    public Alias(Table table,String alias){
        this.table = table;
        this.alias = alias;
    }
    /**
     * Retourne le nom de l'alias.
     * @return le nom de l'alias
     */
    public String getAlias() { 
        return this.alias; 
    }

    /**
     * Retourne la table originale sans modification.
     * @return la table originale
     */
    public Table getTable() {
        return table; 
    }

    /**
     * Crée une copie de la table où chaque attribut est préfixé
     * par le nom de l'alias. 
     * Utilisé pour les jointures et auto-jointures afin de distinguer
     * les colonnes de même nom provenant de tables différentes.
     * @return une nouvelle table avec les attributs renommés(préfixé)
     */
    public Table getTableAvecPrefixe() {
        LinkedHashSet<Attribut> attributsRenommes = new LinkedHashSet<>();
        for (Attribut attr : table.getAttributs()) {
            attributsRenommes.add(new Attribut(alias, attr.nom(), attr.type()));
        }
        Table temp = new Table(alias, attributsRenommes);
        for (Ligne ligne : table.getLignes()) {
            Map<Attribut, Valeur<?>> valeursRenommees = new HashMap<>();
            for (Attribut attr : table.getAttributs()) {
                Attribut attrRenomme = new Attribut(alias, attr.nom(), attr.type());
                valeursRenommees.put(attrRenomme, ligne.getValeur(attr));
            }
            temp.insertLigne(new Ligne(valeursRenommees));
        }
        return temp;
    }
}