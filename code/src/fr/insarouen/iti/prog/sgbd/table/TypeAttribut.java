package fr.insarouen.iti.prog.sgbd.table;

/**
 * Représente les différents types d'attributs possibles
 * Cette énumération est utilisée pour définir le type d'un
 * attribut.
 * 
 * @author Groupe_14
 * @version 1.0
 */

public enum TypeAttribut {
    /**
     * L'attribut est un entier qui s'auto-incrémente lors de 
     * l'ajout d'une ligne dans une table
     */
    SERIAL,

    /**
     * L'attribut est un entier
     */
    INT,

    /**
     * L'attribut est une chaîne de caractères
     */
    VARCHAR
}