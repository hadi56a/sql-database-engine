package fr.insarouen.iti.prog.sgbd;

import java.util.Set;
import java.util.Map;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

import fr.insarouen.iti.prog.sgbd.table.Table;
import fr.insarouen.iti.prog.sgbd.table.Attribut;
import fr.insarouen.iti.prog.sgbd.valeur.Valeur;
import fr.insarouen.iti.prog.sgbd.valeur.ValeurSERIAL;
import fr.insarouen.iti.prog.sgbd.table.TypeAttribut;
import fr.insarouen.iti.prog.sgbd.exception.TypeMismatchException;
import fr.insarouen.iti.prog.sgbd.exception.TableAlreadyExistsException;
import fr.insarouen.iti.prog.sgbd.exception.TableNotFoundException;
import java.io.Serializable;

/**
 * Classe représentant la base de données.
 * Gère la création et la suppression de table ainsi que le serial.
 * @author Groupe_14
 * @version 1.0
 */


public class BaseDeDonnees implements Serializable{
    
    /**
     * Ensemble des tables de la base
     */
    private Map<String,Table> tables ;

    /**
     * Compteur serial
     */
    private int serialCounter = 1;

    /**
     * Crée la base de données
     */
    public BaseDeDonnees() {
        this.tables = new HashMap<>();
    }

    /**
     * Crée une table et l'ajoute dans la base
     * @param nom Nom de la table
     * @param attributs Ensemble des attributs de la table
     * @throws TableAlreadyExistsException La table existe déjà
     */
    public void createTable(String nom, Set<Attribut> attributs) throws TableAlreadyExistsException {
        if(this.tables.containsKey(nom.toUpperCase())) {
            throw new TableAlreadyExistsException(String.format("Impossible de créer la table, %s existe déjà", nom));
        }
        Table table = new Table(nom, attributs);
        this.tables.put(nom.toUpperCase(),table);
    }

    /**
     * Supprime une table
     * @param nom Nom de la table à supprimer
     * @throws TableNotFoundException la table à supprimer n'existe pas
     */
    public void dropTable(String nom) throws TableNotFoundException {
        if(!this.tables.containsKey(nom.toUpperCase())){
            throw new TableNotFoundException(String.format("Impossible de supprimer la table, %s n'existe pas", nom));
        }
        else {
            this.tables.remove(nom.toUpperCase());
        }
    }

    /**
     * Getter sur une table de la base
     * @param nom Nom de la table
     * @return La table demandée
     */
    public Table getTable(String nom) {
        return this.tables.get(nom.toUpperCase());
    }

    /**
     * Getter sur l'ensemble des tables de la base
     * @return Ensemble des tables de la base
     */
    public Set<Table> getTables() {
        return new HashSet<>(this.tables.values());
    }

    /**
     * Insert une ligne dans une table en ajoutant et incrémentant le serialCounter si présent dans les attributs de la table
     * @param valeursUtilisateur Ligne à insérer
     * @param table Table dans laquelle insérer la ligne
     * @throws TypeMismatchException les types ne correspondent pas
     */
    public void insert(Map<Attribut, Valeur<?>> valeursUtilisateur, Table table) throws TypeMismatchException{
        for (Attribut attr : table.getAttributs()){
            if (attr.type() == TypeAttribut.SERIAL){
                valeursUtilisateur.put(attr, new ValeurSERIAL(serialCounter++));
            }
        }
        table.insert(valeursUtilisateur);
    }

}