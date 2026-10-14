package fr.insarouen.iti.prog.sgbd.condition;

/**
 * Représente les différents types d'opérateur entre deux éléments.
 * Cette énumération est utilisée pour déterminer l'opérateur
 * entre deux éléments.
 * 
 * @author Groupe_14
 * @version 1.0
 */

public enum Operateur {

    /**
     * Opérateur EGAL entre deux éléments.
     * Les deux éléments doivent être égales.
     */
    EGAL,

    /**
     * Opérateur SUPERIEUR entre deux éléments.
     * L'élément de droite doit être supérieur (numériquement ou alphabétiquement)
     * à la partie gauche.
     */
    SUPERIEUR,

    /**
     * Opérateur INFERIEUR entre deux éléments.
     * L'élément de droite doit être inférieur (numériquement ou alphabétiquement)
     * à l'élément de gauche.
     */
    INFERIEUR
}