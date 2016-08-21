/*
 * Pääluokka. Täällä annetaan syötteitä toistaiseksi.
 */
package main;

import labyrintti.Solmu;
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
        Taulukko labyrintti = new Taulukko(5,5);
        labyrintti.luoTaulukko(0);
        labyrintti.getSolmu(2, 1).setPaasy(false);
        labyrintti.getSolmu(2, 1).setArvo('E');
        labyrintti.getSolmu(3, 1).setPaasy(false);
        labyrintti.getSolmu(3, 1).setArvo('E');
        labyrintti.getSolmu(1, 1).setPaasy(false);
        labyrintti.getSolmu(1, 1).setArvo('E');
        labyrintti.getSolmu(4, 1).setPaasy(false);
        labyrintti.getSolmu(4, 1).setArvo('E');
        labyrintti.getSolmu(1, 2).setPaasy(false);
        labyrintti.getSolmu(1, 2).setArvo('E');
        labyrintti.getSolmu(1, 3).setPaasy(false);
        labyrintti.getSolmu(1, 3).setArvo('E');




        for (int i = 0; i < labyrintti.getKokox(); i++) {
            for (int j = 0; j < labyrintti.getKokoy(); j++) {
                labyrintti.haeNaapurit(labyrintti.getSolmu(i,j));
            }
        }

        System.out.println(labyrintti.toString());
        BellmanFord hakija = new BellmanFord(5, 5, labyrintti, 0, 0, 4, 4);
        hakija.suoritaBellmanFord();
        hakija.lyhinPolku();
        System.out.println(labyrintti.toString());
        
    }
    
}
