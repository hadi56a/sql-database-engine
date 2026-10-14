package fr.insarouen.iti.prog.sgbd.condition;

import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import org.hamcrest.Matchers;

import java.util.Map;
import java.util.HashMap;

import fr.insarouen.iti.prog.sgbd.table.Attribut;
import fr.insarouen.iti.prog.sgbd.table.Ligne;
import fr.insarouen.iti.prog.sgbd.table.TypeAttribut;
import fr.insarouen.iti.prog.sgbd.valeur.Valeur;
import fr.insarouen.iti.prog.sgbd.valeur.ValeurINT;
import fr.insarouen.iti.prog.sgbd.valeur.ValeurVARCHAR;
import fr.insarouen.iti.prog.sgbd.exception.TypeMismatchException;

public class TestConditionSimple {

    @Test
    public void test_comparaisonEgalINT() throws TypeMismatchException {
        Attribut attr = new Attribut("attr", TypeAttribut.INT);
        Map<Attribut, Valeur<?>> valeurs = new HashMap<>();
        valeurs.put(attr, new ValeurINT(10));
        Ligne ligne = new Ligne(valeurs);
        ConditionSimple conditionVraie = new ConditionSimple(attr, new ValeurINT(10), Operateur.EGAL);
        ConditionSimple conditionFausse = new ConditionSimple(attr, new ValeurINT(15), Operateur.EGAL);
        assertTrue("La condition 10 == 10  devrait être vraie", conditionVraie.estVerifiee(ligne));
        assertFalse("La condition 10 == 15 devrait être fausse", conditionFausse.estVerifiee(ligne));
    }

    @Test
    public void test_comparaisonEgalVARCHAR() throws TypeMismatchException {
        Attribut attr = new Attribut("attr", TypeAttribut.VARCHAR);
        Map<Attribut, Valeur<?>> valeurs = new HashMap<>();
        valeurs.put(attr, new ValeurVARCHAR("test"));
        Ligne ligne = new Ligne(valeurs);
        ConditionSimple conditionVraie = new ConditionSimple(attr, new ValeurVARCHAR("test"), Operateur.EGAL);
        ConditionSimple conditionFausse = new ConditionSimple(attr, new ValeurVARCHAR("passePas"), Operateur.EGAL);
        assertTrue("La condition \"test\" == \"test\"  devrait être vraie", conditionVraie.estVerifiee(ligne));
        assertFalse("La condition \"test\" == \"passePas\" devrait être fausse", conditionFausse.estVerifiee(ligne));
    }

    @Test
    public void test_comparaisonSuperieurINT() throws TypeMismatchException {
        Attribut attr = new Attribut("attr", TypeAttribut.INT);
        Map<Attribut, Valeur<?>> valeurs = new HashMap<>();
        valeurs.put(attr, new ValeurINT(15));
        Ligne ligne = new Ligne(valeurs);
        ConditionSimple conditionVraie = new ConditionSimple(attr, new ValeurINT(10), Operateur.SUPERIEUR);
        ConditionSimple conditionFausse1 = new ConditionSimple(attr, new ValeurINT(15), Operateur.SUPERIEUR);
        ConditionSimple conditionFausse2 = new ConditionSimple(attr, new ValeurINT(20), Operateur.SUPERIEUR);
        assertTrue("La condition 15 > 10  devrait être vraie", conditionVraie.estVerifiee(ligne));
        assertFalse("La condition 15 > 15 devrait être fausse", conditionFausse1.estVerifiee(ligne));
        assertFalse("La condition 15 > 20 devrait être fausse", conditionFausse2.estVerifiee(ligne));
    }

    @Test
    public void test_comparaisonSuperieurVARCHAR() throws TypeMismatchException {
        Attribut attr = new Attribut("attr", TypeAttribut.VARCHAR);
        Map<Attribut, Valeur<?>> valeurs = new HashMap<>();
        valeurs.put(attr, new ValeurVARCHAR("def"));
        Ligne ligne = new Ligne(valeurs);
        ConditionSimple conditionVraie = new ConditionSimple(attr, new ValeurVARCHAR("abc"), Operateur.SUPERIEUR);
        ConditionSimple conditionFausse1 = new ConditionSimple(attr, new ValeurVARCHAR("def"), Operateur.SUPERIEUR);
        ConditionSimple conditionFausse2 = new ConditionSimple(attr, new ValeurVARCHAR("ghi"), Operateur.SUPERIEUR);
        assertTrue("La condition \"def\" > \"abc\"  devrait être vraie", conditionVraie.estVerifiee(ligne));
        assertFalse("La condition \"def\" > \"def\" devrait être fausse", conditionFausse1.estVerifiee(ligne));
        assertFalse("La condition \"def\" > \"ghi\" devrait être fausse", conditionFausse2.estVerifiee(ligne));
    }

    @Test
    public void test_comparaisoInferieurINT() throws TypeMismatchException {
        Attribut attr = new Attribut("attr", TypeAttribut.INT);
        Map<Attribut, Valeur<?>> valeurs = new HashMap<>();
        valeurs.put(attr, new ValeurINT(15));
        Ligne ligne = new Ligne(valeurs);
        ConditionSimple conditionVraie = new ConditionSimple(attr, new ValeurINT(20), Operateur.INFERIEUR);
        ConditionSimple conditionFausse1 = new ConditionSimple(attr, new ValeurINT(15), Operateur.INFERIEUR);
        ConditionSimple conditionFausse2 = new ConditionSimple(attr, new ValeurINT(10), Operateur.INFERIEUR);
        assertTrue("La condition 15 < 20  devrait être vraie", conditionVraie.estVerifiee(ligne));
        assertFalse("La condition 15 < 15 devrait être fausse", conditionFausse1.estVerifiee(ligne));
        assertFalse("La condition 15 < 10 devrait être fausse", conditionFausse2.estVerifiee(ligne));
    }

    @Test
    public void test_comparaisonInferieurVARCHAR() throws TypeMismatchException {
        Attribut attr = new Attribut("attr", TypeAttribut.VARCHAR);
        Map<Attribut, Valeur<?>> valeurs = new HashMap<>();
        valeurs.put(attr, new ValeurVARCHAR("def"));
        Ligne ligne = new Ligne(valeurs);
        ConditionSimple conditionVraie = new ConditionSimple(attr, new ValeurVARCHAR("ghi"), Operateur.INFERIEUR);
        ConditionSimple conditionFausse1 = new ConditionSimple(attr, new ValeurVARCHAR("def"), Operateur.INFERIEUR);
        ConditionSimple conditionFausse2 = new ConditionSimple(attr, new ValeurVARCHAR("abc"), Operateur.INFERIEUR);
        assertTrue("La condition \"def\" < \"ghi\"  devrait être vraie", conditionVraie.estVerifiee(ligne));
        assertFalse("La condition \"def\" < \"def\" devrait être fausse", conditionFausse1.estVerifiee(ligne));
        assertFalse("La condition \"def\" < \"abc\" devrait être fausse", conditionFausse2.estVerifiee(ligne));
    }

    @Test
    public void test_comparaisonEntreDeuxAttributs() throws TypeMismatchException {
        Attribut attr1 = new Attribut("attr1", TypeAttribut.INT);
        Attribut attr2 = new Attribut("attr2", TypeAttribut.INT);
        Map<Attribut, Valeur<?>> valeurs = new HashMap<>();
        valeurs.put(attr1, new ValeurINT(10));
        valeurs.put(attr2, new ValeurINT(5));
        Ligne ligne = new Ligne(valeurs);
        ConditionSimple conditionVraie = new ConditionSimple(attr1, attr2, Operateur.SUPERIEUR);
        ConditionSimple conditionFausse = new ConditionSimple(attr1, attr2, Operateur.INFERIEUR);
        assertTrue("La conditon 10 > 5  devrait être vrai", conditionVraie.estVerifiee(ligne));
        assertFalse("La conditon 15 < 5 devrait être fausse", conditionFausse.estVerifiee(ligne));
    }

    @Test(expected = TypeMismatchException.class)
    public void test_TypeMismatchException() throws TypeMismatchException {
        Attribut attr = new Attribut("attr", TypeAttribut.INT);
        ConditionSimple conditionErreur = new ConditionSimple(attr, new ValeurVARCHAR("ghi"), Operateur.INFERIEUR);
    }
}