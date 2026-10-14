package fr.insarouen.iti.prog.sgbd.condition;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@Suite.SuiteClasses({
    TestConditionLogique.class,
    TestConditionSimple.class
})

public class AllTestsCondition {}