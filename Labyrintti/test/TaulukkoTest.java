/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import labyrintti.Solmu;
import labyrintti.Taulukko;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import sun.font.SunFontManager;

/**
 *
 * @author timohaut
 */
public class TaulukkoTest {
    
    Taulukko taulukko3x3;
    
    public TaulukkoTest() {
        taulukko3x3 = new Taulukko(3,3);
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

    @Test
    public void taulukonLuontiToimii() {
       taulukko3x3.luoTaulukko(0);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                assertEquals('P', taulukko3x3.getTaulukko()[i][j].getArvo());
            }
        }
       taulukko3x3.luoTaulukko(1);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                assertEquals('E', taulukko3x3.getTaulukko()[i][j].getArvo());
            }
        } 
    }
    
    @Test
    public void haeNaapuritToimii() {
        taulukko3x3.luoTaulukko(0.12);
        taulukko3x3.haeNaapurit(taulukko3x3.getTaulukko()[0][2]);
        assertEquals(null, taulukko3x3.getTaulukko()[0][2].getVieruslista()[0]);
        assertEquals(taulukko3x3.getTaulukko()[0][1], taulukko3x3.getTaulukko()[0][2].getVieruslista()[1]);
        assertEquals(taulukko3x3.getTaulukko()[1][2], taulukko3x3.getTaulukko()[0][2].getVieruslista()[2]);
        assertEquals(null, taulukko3x3.getTaulukko()[0][2].getVieruslista()[3]);
    }
}
