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
import fr.insarouen.iti.prog.sgbd.exception.TypeMismatchException;

public class TestConditionLogique {

    ConditionSimple conditionVraie;
    ConditionSimple conditionFausse;
    Ligne ligne;

    @Before
    public void avantTest() throws TypeMismatchException {
        Attribut attr1 = new Attribut("attr1", TypeAttribut.INT);
        Attribut attr2 = new Attribut("attr2", TypeAttribut.INT);
        Map<Attribut, Valeur<?>> valeurs = new HashMap<>();
        valeurs.put(attr1, new ValeurINT(10));
        valeurs.put(attr2, new ValeurINT(5));
        this.ligne = new Ligne(valeurs);
        this.conditionVraie = new ConditionSimple(attr1, attr2, Operateur.SUPERIEUR);
        this.conditionFausse = new ConditionSimple(attr1, attr2, Operateur.INFERIEUR);
    }

    @Test
    public void test_conditionET() throws TypeMismatchException {
        ConditionLogique conditionVraieVrai = new ConditionLogique(conditionVraie, conditionVraie, Logique.ET);
        ConditionLogique conditionVraieFausse = new ConditionLogique(conditionVraie, conditionFausse, Logique.ET);
        ConditionLogique conditionFausseVraie = new ConditionLogique(conditionFausse, conditionVraie, Logique.ET);
        ConditionLogique conditionFausseFausse = new ConditionLogique(conditionFausse, conditionFausse, Logique.ET);

        assertTrue("La conditon logique ET entre VRAI et VRAI devrait être vraie", conditionVraieVrai.estVerifiee(ligne));
        assertFalse("La conditon logique ET entre VRAI et FAUX devrait être fausse", conditionVraieFausse.estVerifiee(ligne));
        assertFalse("La conditon logique ET entre FAUSSE et VRAI devrait être fausse", conditionFausseVraie.estVerifiee(ligne));
        assertFalse("La conditon logique ET entre FAUX et FAUX devrait être fausse", conditionFausseFausse.estVerifiee(ligne));
    }

    @Test
    public void test_conditionOU() throws TypeMismatchException {
        ConditionLogique conditionVraieVrai = new ConditionLogique(conditionVraie, conditionVraie, Logique.OU);
        ConditionLogique conditionVraieFausse = new ConditionLogique(conditionVraie, conditionFausse, Logique.OU);
        ConditionLogique conditionFausseVraie = new ConditionLogique(conditionFausse, conditionVraie, Logique.OU);
        ConditionLogique conditionFausseFausse = new ConditionLogique(conditionFausse, conditionFausse, Logique.OU);

        assertTrue("La conditon logique OU entre VRAI et VRAI devrait être vraie", conditionVraieVrai.estVerifiee(ligne));
        assertTrue("La conditon logique OU entre VRAI et FAUX devrait être vraie", conditionVraieFausse.estVerifiee(ligne));
        assertTrue("La conditon logique OU entre FAUSSE et VRAI devrait être vraie", conditionFausseVraie.estVerifiee(ligne));
        assertFalse("La conditon logique OU entre FAUX et FAUX devrait être fausse", conditionFausseFausse.estVerifiee(ligne));
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_IllegalArgumentException() {
        ConditionLogique conditionVraieVrai = new ConditionLogique(null, conditionVraie, Logique.OU);
    }
}