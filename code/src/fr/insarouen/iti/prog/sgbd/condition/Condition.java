package fr.insarouen.iti.prog.sgbd.condition;

import fr.insarouen.iti.prog.sgbd.table.Ligne;
import fr.insarouen.iti.prog.sgbd.exception.TypeMismatchException;

/**
 * Interface représentant une condition de la base de données.
 * Une condition peut être vérifiée.
 * 
 * @author Groupe_14
 * @version 1.0
 */

public interface Condition{
    /**
     * Indique si la condition est vérifiée.
     * 
     * @param l la ligne où la condition doit être vérifiée.
     * @return true si la condition est vérifiée, false sinon.
     * @throws TypeMismatchException type incompatible entre deux éléments.
     */
    boolean estVerifiee(Ligne l) throws TypeMismatchException ;
}