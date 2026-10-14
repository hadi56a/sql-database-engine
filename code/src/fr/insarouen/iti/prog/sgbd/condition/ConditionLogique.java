package fr.insarouen.iti.prog.sgbd.condition;

import fr.insarouen.iti.prog.sgbd.table.Ligne;
import fr.insarouen.iti.prog.sgbd.exception.TypeMismatchException;

/**
 * Classe représentant l'expression d'une condition logique.
 * Une condition logique est une expression entre deux conditions 
 * séparé par un comparateur logique.
 * 
 * @author Groupe_14
 * @version 1.0
 */

public class ConditionLogique implements Condition{
    
    /**
     * La première condition de l'expression.
     */
    private final Condition condition1;

    /**
     * La deuxième condition de l'expression.
     */
    private final Condition condition2;

    /**
     * Le comparateur logique entre les deux expression.
     */
    private final Logique logique;

    /**
     * Constructeur d'une condition logique.
     * 
     * @param c1 la première condition à vérifiée
     * @param c2 la deuxième condition à vérifiée
     * @param l le comparateur logique entre les conditions
     */
    public ConditionLogique(Condition c1, Condition c2, Logique l) {
        if (c1 == null || c2 == null || l == null){
            throw new IllegalArgumentException("Les conditons et l'opérateur ne peuvent pas être vides");
        }
        this.condition1 = c1;
        this.condition2 = c2;
        this.logique = l;
    }

    /**
     * Indique si la condition logique est vérifiée
     * 
     * @param l la ligne où tester la condition
     * @return true si la condition logique est vérifiée, false sinon
     * @throws TypeMismatchException type incompatible entre deux éléments
     */
    @Override
    public boolean estVerifiee(Ligne l) throws TypeMismatchException {
        if (this.logique == Logique.ET){
            return this.condition1.estVerifiee(l) && this.condition2.estVerifiee(l);
        } else{
            return this.condition1.estVerifiee(l) || this.condition2.estVerifiee(l);
        }
    }
}