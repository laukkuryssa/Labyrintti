/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import labyrintti.Taulukko;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import tietorakenteet.BellmanFord;

/**
 *
 * @author timohaut
 */
public class BellmanFordTest {
    
    public BellmanFordTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        Taulukko labyrintti2x2 = new Taulukko(5,5);
        labyrintti2x2.luoTaulukko(0);
    }
    
    @After
    public void tearDown() {
    }

     @Test
     public void KulmastaKeskelle() {
        Taulukko labyrintti5x5 = new Taulukko(5,5);
        labyrintti5x5.luoTaulukko(0);
        labyrintti5x5.getSolmu(2, 1).setPaasy(false);
        labyrintti5x5.getSolmu(2, 1).setArvo('E');
        labyrintti5x5.getSolmu(1, 1).setPaasy(false);
        labyrintti5x5.getSolmu(1, 1).setArvo('E');
        labyrintti5x5.getSolmu(0, 1).setPaasy(false);
        labyrintti5x5.getSolmu(0, 1).setArvo('E');
        for (int i = 0; i < labyrintti5x5.getKokox(); i++) {
            for (int j = 0; j < labyrintti5x5.getKokoy(); j++) {
                labyrintti5x5.haeNaapurit(labyrintti5x5.getSolmu(i,j));
            }
        }

        BellmanFord hakija = new BellmanFord(5, 5, labyrintti5x5, 0, 0, 2, 2);
        hakija.suoritaBellmanFord();
        hakija.lyhinPolku();
        String tavoite = "\nRRRRP\nEEERP\nPPRRP\nPPPPP\nPPPPP";
        assertEquals(tavoite, labyrintti5x5.toString());
        
        labyrintti5x5.getSolmu(1, 1).setPaasy(true);
        labyrintti5x5.getSolmu(1, 1).setArvo('P');
        for (int i = 0; i < labyrintti5x5.getKokox(); i++) {
            for (int j = 0; j < labyrintti5x5.getKokoy(); j++) {
                labyrintti5x5.haeNaapurit(labyrintti5x5.getSolmu(i,j));
            }
        }
        hakija.suoritaBellmanFord();
        hakija.lyhinPolku();
        String tavoite2 = "\nRRRRP\nERERP\nPRRRP\nPPPPP\nPPPPP";
        assertEquals(tavoite2, labyrintti5x5.toString());
     }
     
     @Test
     public void KulmastaKulmaan() {
        Taulukko labyrintti5x5 = new Taulukko(5,5);
        labyrintti5x5.luoTaulukko(0);
        labyrintti5x5.getSolmu(2, 1).setPaasy(false);
        labyrintti5x5.getSolmu(2, 1).setArvo('E');
        labyrintti5x5.getSolmu(3, 1).setPaasy(false);
        labyrintti5x5.getSolmu(3, 1).setArvo('E');
        labyrintti5x5.getSolmu(4, 1).setPaasy(false);
        labyrintti5x5.getSolmu(4, 1).setArvo('E');
        labyrintti5x5.getSolmu(0, 1).setPaasy(false);
        labyrintti5x5.getSolmu(0, 1).setArvo('E');
        for (int i = 0; i < labyrintti5x5.getKokox(); i++) {
            for (int j = 0; j < labyrintti5x5.getKokoy(); j++) {
                labyrintti5x5.haeNaapurit(labyrintti5x5.getSolmu(i,j));
            }
        }

        BellmanFord hakija = new BellmanFord(5, 5, labyrintti5x5, 0, 0, 4, 4);
        hakija.suoritaBellmanFord();
        hakija.lyhinPolku();
        String tavoite = "\nRRPPP\nEREEE\nPRRRR\nPPPPR\nPPPPR";
         assertEquals(tavoite, labyrintti5x5.toString());
     }
}
