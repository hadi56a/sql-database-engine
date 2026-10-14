package fr.insarouen.iti.prog.sgbd.condition;

import fr.insarouen.iti.prog.sgbd.table.Attribut;
import fr.insarouen.iti.prog.sgbd.table.TypeAttribut;
import fr.insarouen.iti.prog.sgbd.table.Ligne;
import fr.insarouen.iti.prog.sgbd.valeur.Valeur;
import fr.insarouen.iti.prog.sgbd.exception.TypeMismatchException;

/**
 * Classe représentant l'expression d'une condition simple.
 * Une condition simple est une expression comparant deux éléments à 
 * l'aide d'un opérateur.
 * 
 * @author Groupe_14
 * @version 1.0
 */

public class ConditionSimple implements Condition {
    
    /**
     * L'attribut gauche de la condition.
     */
    private Attribut partieGauche;

    /**
     * L'attribut droit de la condition, null si la partie droite est une valeur.
     */
    private Attribut partieDroiteAttribut;
    
    /**
     * La valeur droite de la condition, null si la partie droite est un attribut.
     */
    private Valeur<?> partieDroiteValeur;

    /**
     * L'opérateur entre les deux parties.
     */
    private Operateur operateur;

    /**
     * Constructeur d'une condition simple avec un attribut pour la partie droite.
     * 
     * @param partieGauche la partie gauche de la condition
     * @param partieDroite la partie droite de la condition
     * @param operateur l'opérateur entre les deux parties
     * @throws TypeMismatchException type incompatible entre deux éléments
     */
    public ConditionSimple(Attribut partieGauche, Attribut partieDroite, Operateur operateur) throws TypeMismatchException {
        if (partieGauche.type() == TypeAttribut.INT || partieGauche.type() == TypeAttribut.SERIAL){
            if (partieDroite.type() == TypeAttribut.VARCHAR) {
                throw new TypeMismatchException(String.format("La comparaison entre le type %s et le type %s n'est pas possible",partieGauche.type(), partieDroite.type()));
            }
        }
        else{
            if (partieDroite.type() != TypeAttribut.VARCHAR) {
                throw new TypeMismatchException(String.format("La comparaison entre le type %s et le type %s n'est pas possible",partieGauche.type(), partieDroite.type()));
            }
        }
        this.partieGauche = partieGauche;
        this.partieDroiteAttribut = partieDroite;
        this.partieDroiteValeur = null;
        this.operateur = operateur;
    }

    /**
     * Constructeur d'une condition simple avec une valeur pour la partie droite.
     * 
     * @param partieGauche la partie gauche de la condition
     * @param partieDroite la partie droite de la condition
     * @param operateur l'opérateur entre les deux parties
     * @throws TypeMismatchException type incompatible entre deux éléments
     */
    public ConditionSimple(Attribut partieGauche, Valeur<?> partieDroite, Operateur operateur) throws TypeMismatchException {
        if (partieGauche.type() == TypeAttribut.INT || partieGauche.type() == TypeAttribut.SERIAL){
            if (partieDroite.getType() == TypeAttribut.VARCHAR) {
                throw new TypeMismatchException(String.format("La comparaison entre le type %s et le type %s n'est pas possible",partieGauche.type(), partieDroite.getType()));
            }
        }
        else{
            if (partieDroite.getType() != TypeAttribut.VARCHAR) {
                throw new TypeMismatchException(String.format("La comparaison entre le type %s et le type %s n'est pas possible",partieGauche.type(), partieDroite.getType()));
            }
        }
        this.partieGauche = partieGauche;
        this.partieDroiteAttribut = null;
        this.partieDroiteValeur = partieDroite;
        this.operateur = operateur;
    }

    /**
     * Indique si la condition simple est vérifiée
     * 
     * @param l la ligne où tester la condition
     * @return true si la condition simple est vérifiée, false sinon
     */
    @Override
    @SuppressWarnings({"unchecked", "rawtypes"})
    public boolean estVerifiee(Ligne l) {
        Valeur<?> val1 = l.getValeur(this.partieGauche);
        Valeur<?> val2;
        if(this.partieDroiteValeur == null){
            val2 = l.getValeur(this.partieDroiteAttribut);
        }
        else{
            val2 = this.partieDroiteValeur;
        }

        if (val1 == null || val2 == null || val1.getValeur() == null || val2.getValeur() == null) {
            return false;
        }
        Comparable valComparable1 = (Comparable) val1.getValeur();
        Comparable valComparable2 = (Comparable) val2.getValeur();
        int resComparaison = valComparable1.compareTo(valComparable2);

        boolean res;
        switch(this.operateur){
            case Operateur.EGAL:
                res = (resComparaison == 0);
                break;
            case Operateur.SUPERIEUR:
                res = (resComparaison > 0);
                break;
            default:
                res = (resComparaison < 0);
                break;
            }
        return res;
    }

}