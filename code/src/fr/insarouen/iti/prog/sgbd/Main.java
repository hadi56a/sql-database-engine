package fr.insarouen.iti.prog.sgbd;

import fr.insarouen.iti.prog.sgbd.analyseur.AnalyseurSyntaxique;
import fr.insarouen.iti.prog.sgbd.data.Enregistreur;
import fr.insarouen.iti.prog.sgbd.data.Lecteur;
import fr.insarouen.iti.prog.sgbd.exception.ExitException;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileNotFoundException;

public class Main {

    private static Lecteur lecteur;
    private static Enregistreur enregistreur;
    private static BaseDeDonnees bdd = null;

    private static void aide() {
        System.out.println("Utilisation : java fr.insarouen.iti.prog.sgbd.Main [< nomDuFichierSQL]");
    }

    public static void main(String[] args) {

        AnalyseurSyntaxique analyseurSyntaxique;

        if (args.length == 0) {
            charger();
            if(bdd == null){
                bdd = new BaseDeDonnees();
            }
            try {
                analyseurSyntaxique = new AnalyseurSyntaxique(System.in);
                analyseurSyntaxique.programme(bdd);
            } catch (ExitException e) {
                // Sortie normale
            } catch (Throwable e) {
                System.err.println("> Erreur: Syntaxe SQL invalide");
            }
            sauvegarder();
            System.out.println("> Au revoir !");
        } else {
            aide();
        }
    }

    private static void charger(){
        try{
            lecteur = new Lecteur(new ObjectInputStream(new FileInputStream("data")));
            bdd = lecteur.getBaseDeDonnees();
        }catch(FileNotFoundException e){
            System.err.println("Pas de sauvegarde trouvée");
        }catch(Exception e){
            System.err.println("erreur de chargement");
        }
    }

    public static void sauvegarder(){
        try{
            enregistreur = new Enregistreur(new ObjectOutputStream(new FileOutputStream("data")));
            enregistreur.enregistrer(bdd);
        }catch(Throwable e){
            System.err.println("erreur de sauvegarde");
        }
    }
}