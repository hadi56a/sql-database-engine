package fr.insarouen.iti.prog.sgbd.table;

import java.util.Collection;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Set;
import java.util.LinkedHashSet;
import java.util.Iterator;
import fr.insarouen.iti.prog.sgbd.valeur.Valeur;
import fr.insarouen.iti.prog.sgbd.exception.TypeMismatchException;
import fr.insarouen.iti.prog.sgbd.exception.AttributNotFoundException;
import fr.insarouen.iti.prog.sgbd.condition.Condition;
import java.io.Serializable;

/**
 * Représente une table dans le SGBD.
 */
public class Table implements Serializable{

    /** Nom de la table */
    private String nom;

    /** Liste des lignes de la table */
    private List<Ligne> lignes;

    /** Ensemble ordonné des attributs de la table */
    private Set<Attribut> attributs;
    
    /**
     * Constructeur de table avec le nom et les attributs.
     * @param nom le nom de la table
     * @param attributs les attributs de la table
     */
    public Table(String nom, Set<Attribut> attributs ){
        this.nom = nom;
        this.attributs = attributs;
        this.lignes = new ArrayList<>();
    }

    /**
     * Ajoute un suffixe au nom de la table.
     * @param suffixe le suffixe à ajouter
     */
    public void ajouterSuffixe(String suffixe){
        this.nom +=suffixe; 
    }
    
    /**
     * @return le nom de la table
     */
    public String getNom(){
        return this.nom;
    }

    /**
     * Retourne les attributs de la table en lecture seule.
     * @return une collection non modifiable des attributs
     */
    public Collection<Attribut> getAttributs(){
        return Collections.unmodifiableCollection(this.attributs);
    }

    /**
     * Recherche un attribut par son nom.
     * @param nomRecherche le nom de l'attribut recherché
     * @return l'attribut trouvé OU NULL
     */
    public Attribut getAttribut(String nomRecherche) {
        for (Attribut attr : this.attributs) {
            if (attr.toString().equalsIgnoreCase(nomRecherche)) {
                return attr;
            }
            if (attr.nom().equalsIgnoreCase(nomRecherche)) {
                return attr;
            }
        }
        return null; 
    }

    // SELECT * → pas d'attributs spécifiés, on prend tous
    /**
     * Sélectionne toutes les colonnes de la table selon une condition.
     * @param condition la condition de filtrage
     * @return une nouvelle table contenant les lignes sélectionnées
     * @throws TypeMismatchException si un attribut est inconnu
     */
    public Table select(Condition condition) throws TypeMismatchException{
        return select(this.attributs, condition);
    }

    /**
     * Sélectionne un sous-ensemble de colonnes selon une condition.
     * @param condition la condition de filtrage
     * @return une nouvelle table contenant les lignes et colonnes sélectionnées
     * @throws TypeMismatchException si un attribut est inconnu
     */
    public Table select(Set<Attribut> attributs, Condition condition, boolean multiSource, boolean projection) throws TypeMismatchException{
        Table resultat = select(attributs, condition);
        if (multiSource || (projection && condition != null)) {
            resultat.ajouterSuffixe("_selection_projection");
        } else if (condition != null) {
            resultat.ajouterSuffixe("_selection");
        } else if (projection) {
            resultat.ajouterSuffixe("_projection");
        }
        return resultat;
    }

    public Table select(Set<Attribut> attributs, Condition condition) throws TypeMismatchException{
        if (!(this.attributs.containsAll(attributs))) {
            throw new AttributNotFoundException("Tous les attributs ne sont pas dans la table");
        }
        Table temp = new Table(this.getNom(), attributs);
        for (Ligne tempLigne : this.lignes) {
            if (condition == null || condition.estVerifiee(tempLigne)) {
                Map<Attribut, Valeur<?>> valeursFiltrees = new HashMap<>();
                for (Attribut attribut : attributs) {
                    valeursFiltrees.put(attribut, tempLigne.getValeur(attribut));
                }
                temp.insertLigne(new Ligne(valeursFiltrees));
            }
        }
        return temp;
    }

    /**
     * Vérifie qu'un attribut existe dans la table,
     * en comparant le nom (insensible à la casse) et le type.
     * @param attr l'attribut à rechercher
     * @return true si un attribut de même nom et de même type existe, false sinon
     */
    private boolean attributExiste(Attribut attr) {
        boolean trouve = false;
        for (Attribut a : this.attributs) {
            if (a.nom().equalsIgnoreCase(attr.nom()) && a.type() == attr.type()) {
                trouve = true;
            }
        }
        return trouve;
    }

    /**
     * Insère une ligne déjà construite dans la table.
     * Utilisé en interne par select et produitCartesien.
     * @param l la ligne à insérer
     */
    public void insertLigne(Ligne l) {
        this.lignes.add(l);
    }

    /**
     * Insère une ligne dans la table à partir d'une map attribut-valeur.
     * Vérifie que les attributs existent et que les types correspondent.
     * @param valeursUtilisateur la map associant chaque attribut à sa valeur
     * @throws TypeMismatchException si un attribut est inconnu ou si le type ne correspond pas
     */
    public void insert(Map<Attribut, Valeur<?>> valeursUtilisateur) throws TypeMismatchException {
        for (Attribut attr : valeursUtilisateur.keySet()) {
            if (!attributExiste(attr)) {
                throw new TypeMismatchException("Attribut inconnu : " + attr.nom());
            }
            if (!(attr.type() == valeursUtilisateur.get(attr).getType())) {
                throw new TypeMismatchException(String.format("Le type de %s ne correspond pas au type de l'attribut %s", valeursUtilisateur.get(attr), attr));
            }
        }
        this.lignes.add(new Ligne(valeursUtilisateur));
    }
    
    /**
     * Supprime toutes les lignes de la table.
     * Équivalent de DELETE FROM table sans WHERE.
     */
    public int delete() {
        int count = this.lignes.size();
        this.lignes.clear();
        return count;
    }

    /**
     * Supprime les lignes vérifiant une condition.
     * Équivalent de DELETE FROM table WHERE condition.
     * @param condition la condition de suppression
     * @throws TypeMismatchException si un attribut est inconnu
     */
    public int delete(Condition condition) throws TypeMismatchException{
        int count = 0;
        for (Iterator<Ligne> i = this.lignes.iterator(); i.hasNext();) {
            Ligne l = i.next();
            if (condition.estVerifiee(l)) {
                i.remove();
                count++;
            }
        }
        return count;
    }

    /**
     * Retourne les lignes de la table en lecture seule.
     * @return une liste non modifiable des lignes
     */
    public List<Ligne> getLignes() {
        return Collections.unmodifiableList(lignes);
    }
    
    /**
     * Calcule le produit cartésien de cette table avec une autre.
     * Utilisé pour les jointures — chaque ligne de cette table
     * est combinée avec chaque ligne de l'autre table.
     * @param table la table à combiner
     * @return une nouvelle table contenant toutes les combinaisons de lignes des deux tables 
     */
    public Table produitCartesien(Table table){
        Set<Attribut> attributs = new LinkedHashSet<>(this.getAttributs());
        attributs.addAll(table.getAttributs());

        Table temp = new Table(this.getNom() + "_" + table.getNom(), attributs);

        for(Ligne ligneGauche : this.lignes){
            for (Ligne ligneDroite : table.getLignes()) {
                Map<Attribut, Valeur<?>> valeurs = new HashMap<>(ligneGauche.getLigne());
                valeurs.putAll(ligneDroite.getLigne());
                temp.insertLigne(new Ligne(valeurs));
            }
        }
        return temp;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        
        Table autreTable = (Table) obj;
        return this.nom.equals(autreTable.nom) && this.attributs.equals(autreTable.attributs);
    }

    @Override
    public int hashCode() {
        return 11*this.nom.hashCode() + 13*this.attributs.hashCode();
    }

    /**
     * Retourne une représentation textuelle de la table sous forme de tableau,
     * @return la table formatée en chaîne de caractères
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
        // Calcul de la plus grande largeur pour chaque colone 
        Map<Attribut, Integer> largeurs = new LinkedHashMap<>();
        for (Attribut attr : attributs) {
            int max = attr.toString().length();
            for (Ligne ligne : lignes) {
                Object val = ligne.getValeur(attr);
                int len;
                if (val == null) {
                    len = 4;
                } else {
                    len = val.toString().length();
                }
                if (len > max) {
                    max = len;
                }
            }
            largeurs.put(attr, max);
        }
        
        //affichage du nom de la table
        sb.append("> Table: ").append(nom).append("\n");
        
        // aAfficher l'entete
        sb.append("| ");
        for (Attribut attr : attributs) {
            sb.append(String.format("%-" + largeurs.get(attr) + "s", attr.toString()));
            sb.append(" | ");
        }
        sb.append("\n");
        
        // Affichage des Separateur
        sb.append("|");
        for (Attribut attr : attributs) {
            sb.append("-".repeat(largeurs.get(attr) + 2)).append("|");
        }
        sb.append("\n");
        
        // Affichage des ligne
        for (Ligne ligne : lignes) {
            sb.append("| ");
            for (Attribut attr : attributs) {
                Object val = ligne.getValeur(attr);
                String affichage;
                if (val == null) {
                    affichage = "null";
                } else {
                    affichage = val.toString();
                }
                sb.append(String.format("%-" + largeurs.get(attr) + "s", affichage));
                sb.append(" | ");
            }
            sb.append("\n");
        }
        
        return sb.toString();
    }
}