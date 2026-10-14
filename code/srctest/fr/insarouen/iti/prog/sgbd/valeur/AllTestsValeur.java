package fr.insarouen.iti.prog.sgbd.valeur;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@Suite.SuiteClasses({
    TestValeurINT.class,
    TestValeurSERIAL.class,
    TestValeurVARCHAR.class
})

public class AllTestsValeur {}