package fr.insarouen.iti.prog.sgbd.valeur;

import org.junit.BeforeClass;
import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;

import fr.insarouen.iti.prog.sgbd.table.TypeAttribut;

public class TestValeurVARCHAR{

    private static ValeurVARCHAR val1;
    private static ValeurVARCHAR val2;

    @BeforeClass
    public static void avantTest(){
        val1 = new ValeurVARCHAR("abcd");
        val2 = new ValeurVARCHAR("efgh");
    }

    @Test
    public void test_getType(){
        assertThat(val1.getType(), equalTo(TypeAttribut.VARCHAR));
        assertThat(val2.getType(), equalTo(TypeAttribut.VARCHAR));
    }

    @Test
    public void test_getValeur(){
        assertThat(val1.getValeur(), equalTo("abcd"));
        assertThat(val2.getValeur(), equalTo("efgh"));        
    }
}