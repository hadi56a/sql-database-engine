package fr.insarouen.iti.prog.sgbd.valeur;
import java.io.Serializable;

import fr.insarouen.iti.prog.sgbd.table.TypeAttribut;

/**
 * Classe abstraite représentant une valeur typée dans le SGBD.
 *
 * @param <T> le type de la donnée stockée
 * @author Groupe_14
 * @version 1.0 
 */
public abstract class Valeur<T> implements Serializable{
    
    private TypeAttribut type;

    /**
     * Construit une valeur avec un type d'attribut donné.
     *
     * @param type le type de l'attribut associé
     */
    public Valeur(TypeAttribut type) {
        this.type = type;
    }
    /**
     * Retourne le type de l'attribut.
     *
     * @return le type de l'attribut
     */
    public abstract TypeAttribut getType();

    /**
     * Retourne la donnée stockée.
     *
     * @return la valeur stockée
     */
    public abstract T getValeur();
}