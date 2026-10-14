package fr.insarouen.iti.prog.sgbd.valeur;

import fr.insarouen.iti.prog.sgbd.table.TypeAttribut;

/**
 * Classe représentant une valeur de type SERIAL.
 * Hérite de ValeurINT — seul le type retourné diffère.
 * @author Groupe_14
 * @version 1.0
 */
public class ValeurSERIAL extends ValeurINT {

    /**
     * Construit une valeur SERIAL.
     *
     * @param donnee la valeur entière associée
     */
    public ValeurSERIAL(Integer donnee) {
        super(donnee);
    }

    /**
     * Retourne le type SERIAL.
     *
     * @return le type SERIAL
     */
    @Override
    public TypeAttribut getType() {
        return TypeAttribut.SERIAL;
    }
}
