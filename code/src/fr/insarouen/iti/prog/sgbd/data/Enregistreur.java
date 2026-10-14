package fr.insarouen.iti.prog.sgbd.data;

import fr.insarouen.iti.prog.sgbd.BaseDeDonnees;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Collection;

/**
 * Classe représentant un type particulier d'enregistreur d'objets.
 * Il permet d'enregistrer des objets sérialisés dans le flux passé au constructeur.
 * 
 * @author Groupe 14
 * @version 1.0
 */

public class Enregistreur{

    /**
     * Enregistreur serialisé
     */
    private ObjectOutputStream oos;

    /**
     * Créer une instance de notre classe
     * 
     * @param oos fichier sur lequel on va enregistrer
     */
    public Enregistreur(ObjectOutputStream oos){
        this.oos = oos;
    }

    /**
     * Permet d'enregistrer une base de données
     * 
     * @param bdd la base de données
     * @throws IOException si l'enregistrement échoue
     */
    public void enregistrer(BaseDeDonnees bdd) throws IOException{
        this.oos.writeObject(bdd);
    }

}
