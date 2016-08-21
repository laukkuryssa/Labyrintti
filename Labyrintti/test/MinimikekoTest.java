/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import labyrintti.Solmu;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import tietorakenteet.Minimikeko;

/**
 *
 * @author timohaut
 */
public class MinimikekoTest {
    
    public MinimikekoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {

    }
    
    @After
    public void tearDown() {
    }

     @Test
     public void kekoonLisaysToimii() {
        Minimikeko keko = new Minimikeko();
        Solmu solmu1 = new Solmu(1,1);
        Solmu solmu2 = new Solmu(2,2);
        Solmu solmu3 = new Solmu(3,3);   
        solmu1.setPaino(2);
        solmu2.setPaino(3);
        solmu3.setPaino(1);
        keko.lisaa(solmu1);
        keko.lisaa(solmu2);
        keko.lisaa(solmu3);
        Solmu poppi1 = keko.poppaa();
        assertEquals(1, poppi1.getJnro());
//        assertEquals(3, poppi1.getX());

     }
}
