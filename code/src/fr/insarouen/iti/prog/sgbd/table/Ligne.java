package fr.insarouen.iti.prog.sgbd.table;

import java.util.Map;
import java.util.Set;
import java.util.List;
import java.util.HashMap;
import java.util.Collection;
import java.util.Collections;
import fr.insarouen.iti.prog.sgbd.valeur.Valeur;
import java.io.Serializable;

/**
 * Représente une ligne (tuple) dans une table du SGBD.
 */
public class Ligne implements Serializable{
    /**
     * Association entre les attributs et leurs valeurs.
     */
    private Map<Attribut, Valeur<?>> valeurs;

    /**
     * Crée une ligne à partir d'une map attribut-valeur.
     * @param valeurs la map associant chaque attribut à sa valeur
     */
    public Ligne(Map<Attribut, Valeur<?>> valeurs){
        this.valeurs = new HashMap<>(valeurs);
    }

    /**
     * Retourne la valeur associée à un attribut.
     * @param attr l'attribut recherché
     * @return la valeur associée, ou null si l'attribut n'existe pas
     */
    public Valeur<?> getValeur(Attribut attr) {
        return this.valeurs.get(attr);
    }

    /**
     * Retourne toutes les valeurs de la ligne en lecture seule.
     * @return une collection non modifiable des valeurs
     */
    public Collection<Valeur<?>> getValeurs() {
        return Collections.unmodifiableCollection(this.valeurs.values());
    }

    /**
     * Retourne la map interne attribut-valeur.
     * @return la map attribut-valeur
     */
    public Map<Attribut, Valeur<?>> getLigne() {
        return Collections.unmodifiableMap(valeurs);
    }

    /**
     * Représentation textuelle de la ligne.
     * @return la représentation textuelle de la ligne
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        boolean premier = true;
        for (Map.Entry<Attribut, Valeur<?>> entry : valeurs.entrySet()) {
            if (!premier) {
                sb.append(", ");
            }
            sb.append(entry.getKey()).append("=").append(entry.getValue());
            premier = false;
        }
        sb.append(")");
        return sb.toString();
    }
}