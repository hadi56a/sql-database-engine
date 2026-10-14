package fr.insarouen.iti.prog.sgbd;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import fr.insarouen.iti.prog.sgbd.condition.AllTestsCondition;
import fr.insarouen.iti.prog.sgbd.table.AllTestsTable;
import fr.insarouen.iti.prog.sgbd.valeur.AllTestsValeur;

@RunWith(Suite.class)
@Suite.SuiteClasses({
    AllTestsCondition.class,
    AllTestsTable.class,
    AllTestsValeur.class,
    TestBaseDeDonnees.class
})

public class AllTests {}