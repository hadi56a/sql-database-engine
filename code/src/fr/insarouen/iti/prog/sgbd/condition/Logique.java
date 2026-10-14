package fr.insarouen.iti.prog.sgbd.condition;

/**
 * Représente les différents types de comparateur logique.
 * Cette énumération est utilisée pour déterminer le comparateur 
 * logique entre deux conditions.
 * 
 * @author Groupe_14
 * @version 1.0
 */

public enum Logique {

    /**
     * Comparateur logique ET entre deux conditions.
     * Les deux conditions doivent être vérifiées.
     */
    ET,

    /**
     * Comparateur logique OU entre deux conditions.
     * Au moins une des deux conditions doit être vérifiée.
     */
    OU
}