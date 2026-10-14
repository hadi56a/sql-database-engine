package fr.insarouen.iti.prog.sgbd.valeur;

import org.junit.BeforeClass;
import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;

import fr.insarouen.iti.prog.sgbd.table.TypeAttribut;

public class TestValeurSERIAL{

    private static ValeurSERIAL val1;
    private static ValeurSERIAL val2;

    @BeforeClass
    public static void avantTest(){
        val1 = new ValeurSERIAL(3);
        val2 = new ValeurSERIAL(7);
    }

    @Test
    public void test_getType(){
        assertThat(val1.getType(), equalTo(TypeAttribut.SERIAL));
        assertThat(val2.getType(), equalTo(TypeAttribut.SERIAL));
    }

    @Test
    public void test_getValeur(){
        assertThat(val1.getValeur(), equalTo(3));
        assertThat(val2.getValeur(), equalTo(7));        
    }
}