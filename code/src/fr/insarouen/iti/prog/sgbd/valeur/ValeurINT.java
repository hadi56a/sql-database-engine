package fr.insarouen.iti.prog.sgbd.valeur;

import fr.insarouen.iti.prog.sgbd.table.TypeAttribut;

/**
 * Classe représentant une valeur entière de type INT.
 * @author Groupe_14
 * @version 1.0 
 */
public class ValeurINT extends Valeur<Integer>{
    
    protected Integer donnee;

    /**
     * Construit une valeur entière.
     *
     * @param donnee la valeur entière à stocker
     */
    public ValeurINT(Integer donnee) {
        super(TypeAttribut.INT);
        this.donnee = donnee;
    }

    /**
     * Retourne le type INT.
     *
     * @return le type INT
     */
    public TypeAttribut getType() {
        return TypeAttribut.INT;
    }

    /**
     * Retourne la valeur entière stockée.
     *
     * @return la valeur entière
     */
    @Override
    public Integer getValeur() {
        return this.donnee;
    }

    /**
     * Retourne une représentation textuelle de la valeur.
     *
     * @return la valeur sous forme de chaîne de caractères
     */
    @Override
    public String toString(){
        return Integer.toString(this.getValeur());
    }
}