package fr.insarouen.iti.prog.sgbd.table;

import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import java.util.Map;
import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import fr.insarouen.iti.prog.sgbd.valeur.ValeurINT;
import fr.insarouen.iti.prog.sgbd.valeur.ValeurVARCHAR;
import fr.insarouen.iti.prog.sgbd.valeur.Valeur;
import fr.insarouen.iti.prog.sgbd.exception.TypeMismatchException;
import fr.insarouen.iti.prog.sgbd.condition.Condition;
import fr.insarouen.iti.prog.sgbd.condition.ConditionSimple;
import fr.insarouen.iti.prog.sgbd.condition.Operateur;

public class TestTable {

    private Table table;
    private Attribut attr1;
    private Attribut attr2;
    private Set<Attribut> attributs;
    private ValeurVARCHAR nom1;
    private ValeurVARCHAR nom2;
    private ValeurINT age1;
    private ValeurINT age2;
    private Map<Attribut, Valeur<?>> ligne1;
    private Map<Attribut, Valeur<?>> ligne2;

    @Before
    public void avantTest() throws TypeMismatchException {
        attr1 = new Attribut("nom", TypeAttribut.VARCHAR);
        attr2 = new Attribut("age", TypeAttribut.INT);
        nom1 = new ValeurVARCHAR("malandain");
        age1 = new ValeurINT(20);
        nom2 = new ValeurVARCHAR("delestre");
        age2 = new ValeurINT(22);
        ligne1 = new HashMap<>();
        ligne1.put(attr1, nom1);
        ligne1.put(attr2, age1);
        ligne2 = new HashMap<>();
        ligne2.put(attr1, nom2);
        ligne2.put(attr2, age2);
        attributs = new HashSet<>();
        attributs.add(attr1);
        attributs.add(attr2);
        table = new Table("les nicolas", attributs);
    }

    @Test
    public void test_getNom() {
        assertThat(table.getNom(), equalTo("les nicolas"));
    }

    @Test
    public void test_getAttributs(){
        Set<Attribut> attributsReels = new HashSet<>(table.getAttributs());
        assertThat(attributsReels, equalTo(attributs));
    }

    @Test
    public void test_insert_getLignes() throws TypeMismatchException{
        table.insert(ligne1);
        table.insert(ligne2);
        List<Ligne> lignes = table.getLignes();
        assertThat(lignes.size(), equalTo(2));
        assertThat(lignes.get(0).getLigne(), equalTo(ligne1));
        assertThat(lignes.get(1).getLigne(), equalTo(ligne2));
    }

    @Test
    public void test_delete() throws TypeMismatchException{
        table.insert(ligne1);
        table.delete();
        assertThat(table.getLignes().isEmpty(), is(true));
    }

    @Test
    public void test_delete_condition() throws TypeMismatchException{
        table.insert(ligne1);
        table.insert(ligne2);
        Condition condition = new ConditionSimple(attr2, age2, Operateur.EGAL);
        table.delete(condition);
        List<Ligne> lignes = table.getLignes();
        assertThat(lignes.size(), equalTo(1));
        assertThat(lignes.get(0).getLigne(), equalTo(ligne1));
    }

    @Test
    public void test_select() throws TypeMismatchException{
        table.insert(ligne1);
        table.insert(ligne2);
        Condition condition = new ConditionSimple(attr2, age2, Operateur.EGAL);
        Table table2 = table.select(condition);
        List<Ligne> lignes = table2.getLignes();
        assertThat(lignes.size(), equalTo(1));
        assertThat(lignes.get(0).getLigne(), equalTo(ligne2));
    }

    @Test
    public void test_select_attributs() throws TypeMismatchException{
        table.insert(ligne1);
        table.insert(ligne2);
        Condition condition = new ConditionSimple(attr2, age2, Operateur.EGAL);
        Set<Attribut> attributsSelect = new HashSet<>();
        attributsSelect.add(attr1);
        Table table2 = table.select(attributsSelect, condition);
        List<Ligne> lignes = table2.getLignes();
        ligne2.remove(attr2);
        assertThat(lignes.size(), equalTo(1));
        assertThat(lignes.get(0).getLigne(), equalTo(ligne2));
    }

    @Test
    public void test_produit_cartesien() throws TypeMismatchException {
    table.insert(ligne1);
    table.insert(ligne2);
    Attribut attr3 = new Attribut("ec", TypeAttribut.VARCHAR);
    Set<Attribut> attributs2 = new HashSet<>();
    attributs2.add(attr3);
    Table table2 = new Table("les ec", attributs2);
    Map<Attribut, Valeur<?>> ligne3 = new HashMap<>();
    ValeurVARCHAR ec1 = new ValeurVARCHAR("Java");
    ligne3.put(attr3, ec1);
    Map<Attribut, Valeur<?>> ligne4 = new HashMap<>();
    ValeurVARCHAR ec2 = new ValeurVARCHAR("Python");
    ligne4.put(attr3, ec2);
    table2.insert(ligne3);
    table2.insert(ligne4);
    Table produit = table.produitCartesien(table2);
    List<Ligne> lignes = produit.getLignes();
    assertThat(lignes.size(), equalTo(4));
    Map<Attribut, Valeur<?>> attendu1 = new HashMap<>();
    attendu1.put(attr1, nom1);
    attendu1.put(attr2, age1);
    attendu1.put(attr3, ec1);
    Map<Attribut, Valeur<?>> attendu2 = new HashMap<>();
    attendu2.put(attr1, nom1);
    attendu2.put(attr2, age1);
    attendu2.put(attr3, ec2);
    Map<Attribut, Valeur<?>> attendu3 = new HashMap<>();
    attendu3.put(attr1, nom2);
    attendu3.put(attr2, age2);
    attendu3.put(attr3, ec1);
    Map<Attribut, Valeur<?>> attendu4 = new HashMap<>();
    attendu4.put(attr1, nom2);
    attendu4.put(attr2, age2);
    attendu4.put(attr3, ec2);
    assertThat(lignes.get(0).getLigne(), equalTo(attendu1));
    assertThat(lignes.get(1).getLigne(), equalTo(attendu2));
    assertThat(lignes.get(2).getLigne(), equalTo(attendu3));
    assertThat(lignes.get(3).getLigne(), equalTo(attendu4));
}


    @Test
    public void test_equals() {

        Set<Attribut> attributs = new HashSet<>();
        attributs.add(attr1);
        attributs.add(attr2);
        Set<Attribut> mauvaisAttributs = new HashSet<>();
        Attribut attr3 = new Attribut("age", TypeAttribut.VARCHAR);
        mauvaisAttributs.add(attr1);
        mauvaisAttributs.add(attr3);

        Table autreTable = new Table("les nicolas", attributs);
        Table tableMauvaisNom = new Table("mauvais nom", attributs);
        Table tableMauvaisAttributs = new Table("les nicolas", mauvaisAttributs);

        assertThat(table.equals(autreTable), is(true));
        assertThat(table.equals(tableMauvaisNom), is(false));
        assertThat(table.equals(tableMauvaisAttributs), is(false));
    }

}