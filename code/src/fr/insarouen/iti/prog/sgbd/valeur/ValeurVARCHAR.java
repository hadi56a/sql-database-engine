package fr.insarouen.iti.prog.sgbd.valeur;

import fr.insarouen.iti.prog.sgbd.table.TypeAttribut;

/**
 * Classe représentant une valeur textuelle de type VARCHAR.
 * @author Groupe_14
 * @version 1.0
 */
public class ValeurVARCHAR extends Valeur<String>{
    
    private String donnee;

    /**
     * Construit une valeur VARCHAR
     * @param donnee la chaine de caractères à stocker
     */
    public ValeurVARCHAR(String donnee) {
        super(TypeAttribut.VARCHAR);
        this.donnee = donnee;
    }

    /**
     * Retourne le type VARCHAR
     * @return le type VARCHAR
     */
    public TypeAttribut getType() {
        return TypeAttribut.VARCHAR;
    }

    /**
     * Retourne la chaine de caractères stockée.
     * @return la chaine stockée
     */
    @Override
    public String getValeur() {
        return this.donnee;
    }

    /**
     * Retourne une représentation textuelle de la valeur.
     * @return la valeur sous forme de chaine de caractères
     */
    @Override
    public String toString(){
        return this.getValeur();
    }
}