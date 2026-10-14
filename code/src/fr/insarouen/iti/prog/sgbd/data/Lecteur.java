package fr.insarouen.iti.prog.sgbd.data;

import fr.insarouen.iti.prog.sgbd.BaseDeDonnees;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Collection;

/**
 * Classe représentant un lecteur de base de données sérialisé.
 * Un lecteur de sérialisation reconstruit une base de données préalablement sérialisé
 * en le désérialisant depuis un flux d'entrée binaire.
 *
 * @author Groupe 14
 * @version 1.0
 */

public class Lecteur{

    /**
     * La base de données.
     */
    private BaseDeDonnees bdd;

    /**
     * Constructeur d'un lecteur de sérialisation.
     * Lit et désérialise immédiatement le monde depuis le flux fourni.
     *
     * @param ois le flux d'entrée binaire contenant la base de données sérialisé
     * @throws ClassNotFoundException si la classe de la base de données sérialisé est introuvable
     * @throws IOException si une erreur de lecture survient
     */
    @SuppressWarnings("unchecked")
    public Lecteur(ObjectInputStream ois) throws ClassNotFoundException, IOException{
        this.bdd = (BaseDeDonnees)ois.readObject();
    }

    /**
     * Retourne la base de données reconstruit par désérialisation.
     *
     * @return la base de données désérialisé
     */
    public BaseDeDonnees getBaseDeDonnees(){
        return this.bdd;
    }

}