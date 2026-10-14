package fr.insarouen.iti.prog.sgbd;

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

import java.util.Set;
import java.util.Map;
import java.util.HashSet;
import java.util.HashMap;
import java.util.List;

import fr.insarouen.iti.prog.sgbd.BaseDeDonnees;
import fr.insarouen.iti.prog.sgbd.table.Table;
import fr.insarouen.iti.prog.sgbd.table.Attribut;
import fr.insarouen.iti.prog.sgbd.table.Ligne;
import fr.insarouen.iti.prog.sgbd.table.TypeAttribut;
import fr.insarouen.iti.prog.sgbd.valeur.Valeur;
import fr.insarouen.iti.prog.sgbd.valeur.ValeurINT;
import fr.insarouen.iti.prog.sgbd.valeur.ValeurVARCHAR;
import fr.insarouen.iti.prog.sgbd.exception.TypeMismatchException;
import fr.insarouen.iti.prog.sgbd.exception.TableAlreadyExistsException;
import fr.insarouen.iti.prog.sgbd.exception.TableNotFoundException;

public class TestBaseDeDonnees {
    
    private BaseDeDonnees bdd;
    private Set<Attribut> attributs;
    private Attribut attr1, attr2;

    @Before
    public void avantTest() {
        this.bdd = new BaseDeDonnees();
        this.attr1 = new Attribut("attribut1", TypeAttribut.INT);
        this.attr2 = new Attribut("attribut2", TypeAttribut.INT);
        this.attributs = new HashSet<>();
        this.attributs.add(attr1);
        this.attributs.add(attr2);
    }

    @Test
    public void test_createTable() throws TableAlreadyExistsException{
        this.bdd.createTable("table1", this.attributs);
        Table table1 = new Table("table1", this.attributs);
        assertThat(this.bdd.getTable("table1"), equalTo(table1));
        assertThat(this.bdd.getTable("table2"), equalTo(null));
    }

    @Test
    public void test_dropTable() throws TableAlreadyExistsException, TableNotFoundException {
        this.bdd.createTable("table1", this.attributs);
        Table table1 = new Table("table1", this.attributs);
        assertThat(this.bdd.getTable("table1"), equalTo(table1));
        this.bdd.dropTable("table1");
        assertThat(this.bdd.getTable("table1"), equalTo(null));
    }

    @Test
    public void test_getTables() throws TableAlreadyExistsException {
        this.bdd.createTable("table1", this.attributs);
        Attribut attr3 = new Attribut("attribut3", TypeAttribut.VARCHAR);
        Set<Attribut> attributs2 = new HashSet<>();
        attributs2.add(attr3);
        this.bdd.createTable("table2", attributs2);
        Set<Table> tables = new HashSet<>();
        tables.add(this.bdd.getTable("table1"));
        tables.add(this.bdd.getTable("table2"));
        assertThat(this.bdd.getTables(), equalTo(tables));
    }

    @Test
    public void test_insert() throws TypeMismatchException , TableAlreadyExistsException{
        this.bdd.createTable("table1", this.attributs);
        Table table1 = this.bdd.getTable("table1");
        Map<Attribut, Valeur<?>> ligne = new HashMap<>();
        Valeur<Integer> val1 = new ValeurINT(1);
        Valeur<Integer> val2 = new ValeurINT(2);
        ligne.put(this.attr1, val1);
        ligne.put(this.attr2, val2);
        this.bdd.insert(ligne, table1);
        List<Ligne> lignesTable = this.bdd.getTable("table1").getLignes();
        assertThat(lignesTable.size(), equalTo(1));
        Ligne ligneInseree = lignesTable.get(0);
        assertThat(ligneInseree.getValeur(this.attr1), equalTo(val1));
        assertThat(ligneInseree.getValeur(this.attr1), not(equalTo(val2)));
        assertThat(ligneInseree.getValeur(this.attr2), equalTo(val2));
        assertThat(ligneInseree.getValeur(this.attr2), not(equalTo(val1)));
    }

    @Test
    public void test_serialCounter() throws TypeMismatchException, TableAlreadyExistsException {
        Attribut attrSerial = new Attribut("attributSerial", TypeAttribut.SERIAL);
        this.attributs.add(attrSerial);
        this.bdd.createTable("table1", this.attributs);
        Table table1 = this.bdd.getTable("table1");
        Map<Attribut, Valeur<?>> ligne1 = new HashMap<>();
        Map<Attribut, Valeur<?>> ligne2 = new HashMap<>();
        Valeur<Integer> val1 = new ValeurINT(1);
        Valeur<Integer> val2 = new ValeurINT(2);
        ligne1.put(this.attr1, val1);
        ligne1.put(this.attr2, val2);
        ligne2.put(this.attr2, val1);
        ligne2.put(this.attr1, val2);
        this.bdd.insert(ligne1, table1);
        this.bdd.insert(ligne2, table1);
        List<Ligne> lignesTable = this.bdd.getTable("table1").getLignes();
        assertThat(lignesTable.size(), equalTo(2));
        Ligne ligneInseree1 = lignesTable.get(0);
        Ligne ligneInseree2 = lignesTable.get(1);
        assertThat(ligneInseree1.getValeur(attrSerial).getValeur(), equalTo(1));
        assertThat(ligneInseree2.getValeur(attrSerial).getValeur(), equalTo(2));

        this.bdd.createTable("table2", this.attributs);
        Table table2 = this.bdd.getTable("table2");
        this.bdd.insert(ligne1, table2);
        List<Ligne> lignesTable2 = this.bdd.getTable("table2").getLignes();
        Ligne ligneInseree3 = lignesTable2.get(0);
        assertThat(ligneInseree3.getValeur(attrSerial).getValeur(), equalTo(3));
    }

    @Test(expected = TypeMismatchException.class)
    public void test_TypeMismatchException()throws TypeMismatchException, TableAlreadyExistsException {
        this.bdd.createTable("table1", this.attributs);
        Table table1 = this.bdd.getTable("table1");
        Map<Attribut, Valeur<?>> ligneFausse = new HashMap<>();
        Valeur<Integer> val1 = new ValeurINT(1);
        Valeur<String> val2 = new ValeurVARCHAR("faux");
        ligneFausse.put(this.attr1, val1);
        ligneFausse.put(this.attr2, val2);
        this.bdd.insert(ligneFausse, table1);
    }

    @Test(expected = TableAlreadyExistsException.class)
    public void test_TableAlreadyExistsException() throws TableAlreadyExistsException {
        this.bdd.createTable("table1", this.attributs);
        this.bdd.createTable("table1", this.attributs);
    }

    @Test(expected = TableNotFoundException.class)
    public void test_TableNotFoundException() throws TableNotFoundException {
        this.bdd.dropTable("table1");
    }
}