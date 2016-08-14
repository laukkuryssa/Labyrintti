/*
 * Pääluokka. Täällä annetaan syötteitä toistaiseksi.
 */
package main;

import labyrintti.Taulukko;
import tietorakenteet.BellmanFord;
import tietorakenteet.Dijkstra;

/**
 *
 * @author timohaut
 */
public class Labyrintti {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Taulukko labyrintti = new Taulukko(20,20);
        labyrintti.luoTaulukko(0.15);
        labyrintti.tulosta();
        BellmanFord dijkstra = new BellmanFord(20,20, labyrintti, 10, 10, 1, 1);
        dijkstra.suoritaBellmanFord();
        dijkstra.lyhinPolku();
        labyrintti.tulosta();
        
    }
    
}
