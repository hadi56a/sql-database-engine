package fr.insarouen.iti.prog.sgbd.table;
import java.io.Serializable;


/**
 * Représente un attribut (colonne) d'une table dans le SGBD.
 * Un attribut est défini par un nom, un type et un préfixe optionnel.
 * Le préfixe est utilisé pour les jointures et auto-jointures
 * afin de distinguer les colonnes de même nom provenant de tables différentes.
 * 
 * @param prefixe alias de l'attribut
 * @param nom nom de l'attribut
 * @param type type de l'attribut
 */
public record Attribut(String prefixe, String nom, TypeAttribut type) implements Serializable{
    /**
     * Constructeur cas simple sans alias.
     * @param nom le nom de l'attribut
     * @param type le type de données de l'attribut
     */
    public Attribut(String nom, TypeAttribut type) {
        this(null, nom, type);
    }

    /**
     * Retourne la représentation textuelle de l'attribut.
     * Retourne "prefixe.nom" si un préfixe est défini, "nom" sinon.
     * @return la représentation textuelle de l'attribut
     */
   @Override
    public String toString() {
        if (prefixe != null) {
            return prefixe + "." + nom;
        } else {
            return nom;
        }
    }
}
