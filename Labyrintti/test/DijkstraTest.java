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
import tietorakenteet.Dijkstra;

/**
 *
 * @author timohaut
 */
public class DijkstraTest {
    
    public DijkstraTest() {
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
    public void kulmastaKeskelle() {
        Taulukko labyrintti5x5 = new Taulukko(5, 5);
        labyrintti5x5.luoTaulukko(0);
        labyrintti5x5.getSolmu(1, 3).setPaasy(false);
        labyrintti5x5.getSolmu(1, 3).setArvo('█');
        labyrintti5x5.getSolmu(2, 3).setPaasy(false);
        labyrintti5x5.getSolmu(2, 3).setArvo('█');
        labyrintti5x5.getSolmu(3, 3).setPaasy(false);
        labyrintti5x5.getSolmu(3, 3).setArvo('█');
        labyrintti5x5.getSolmu(3, 2).setPaasy(false);
        labyrintti5x5.getSolmu(3, 2).setArvo('█');
        labyrintti5x5.getSolmu(3, 1).setPaasy(false);
        labyrintti5x5.getSolmu(3, 1).setArvo('█');
        labyrintti5x5.getSolmu(2, 1).setPaasy(false);
        labyrintti5x5.getSolmu(2, 1).setArvo('█');
        labyrintti5x5.getSolmu(1, 1).setPaasy(false);
        labyrintti5x5.getSolmu(1, 1).setArvo('█');
        labyrintti5x5.getSolmu(0, 1).setPaasy(false);
        labyrintti5x5.getSolmu(0, 1).setArvo('█');
        for (int i = 0; i < labyrintti5x5.getKokox(); i++) {
            for (int j = 0; j < labyrintti5x5.getKokoy(); j++) {
                labyrintti5x5.haeNaapurit(labyrintti5x5.getSolmu(i, j));
            }
        }

        Dijkstra hakija = new Dijkstra(5, 5, labyrintti5x5, 0, 0, 2, 2);
        hakija.suoritaDijkstra();
        hakija.lyhinPolku();
        String tavoite = "\nRRRRR\n████R\nRRR█R\nR███R\nRRRRR";
        assertEquals(tavoite, labyrintti5x5.toString());
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (labyrintti5x5.getSolmu(i, j).isPaasy()) {
                    labyrintti5x5.getSolmu(i, j).setArvo(' ');
                }
            }
        }
        labyrintti5x5.getSolmu(1, 1).setPaasy(true);
        labyrintti5x5.getSolmu(1, 1).setArvo(' ');
        for (int i = 0; i < labyrintti5x5.getKokox(); i++) {
            for (int j = 0; j < labyrintti5x5.getKokoy(); j++) {
                labyrintti5x5.haeNaapurit(labyrintti5x5.getSolmu(i, j));
            }
        }
        hakija.suoritaDijkstra();
        hakija.lyhinPolku();
        String tavoite2 = "\nRR   \n█R██ \n RR█ \n ███ \n     ";
        assertEquals(tavoite2, labyrintti5x5.toString());
    }

}