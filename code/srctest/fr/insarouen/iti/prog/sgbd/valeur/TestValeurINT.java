package fr.insarouen.iti.prog.sgbd.valeur;

import org.junit.BeforeClass;
import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;

import fr.insarouen.iti.prog.sgbd.table.TypeAttribut;

public class TestValeurINT{

    private static ValeurINT val1;
    private static ValeurINT val2;

    @BeforeClass
    public static void avantTest(){
        val1 = new ValeurINT(3);
        val2 = new ValeurINT(7);
    }

    @Test
    public void test_getType(){
        assertThat(val1.getType(), equalTo(TypeAttribut.INT));
        assertThat(val2.getType(), equalTo(TypeAttribut.INT));
    }

    @Test
    public void test_getValeur(){
        assertThat(val1.getValeur(), equalTo(3));
        assertThat(val2.getValeur(), equalTo(7));        
    }
}