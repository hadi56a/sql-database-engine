package fr.insarouen.iti.prog.sgbd.table;

import org.junit.BeforeClass;
import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;
import java.util.HashMap;
import java.util.Map;
import java.util.HashSet;
import java.util.Set;



import fr.insarouen.iti.prog.sgbd.valeur.ValeurINT;
import fr.insarouen.iti.prog.sgbd.valeur.ValeurVARCHAR;
import fr.insarouen.iti.prog.sgbd.valeur.Valeur;

public class TestLigne {

    private static Ligne ligne;
    private static Attribut attr1;
    private static Attribut attr2;
    private static ValeurVARCHAR val1;
    private static ValeurINT val2;
    private static Map<Attribut, Valeur<?>> ligneMap;

    @BeforeClass
    public static void avantTest(){
        attr1 = new Attribut("nom", TypeAttribut.VARCHAR);
        attr2 = new Attribut("age", TypeAttribut.INT);
        val1 = new ValeurVARCHAR("malandain");
        val2 = new ValeurINT(20);
        ligneMap = new HashMap<>();
        ligneMap.put(attr1, val1);
        ligneMap.put(attr2, val2);
        ligne = new Ligne(ligneMap);
    }

    @Test
    public void test_getValeur() {
        assertThat(ligne.getValeur(attr1), equalTo(val1));
        assertThat(ligne.getValeur(attr2), equalTo(val2));
    }

    @Test
    public void test_getValeurs() {
        Set<Valeur<?>> valeursAttendues = Set.of(val1, val2);
        Set<Valeur<?>> valeursReelles = new HashSet<>(ligne.getValeurs());
        assertThat(valeursReelles, equalTo(valeursAttendues));
    }

    @Test
    public void test_getLigne() {
        assertThat(ligne.getLigne(), equalTo(ligneMap));
    }
}