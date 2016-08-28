/*
 * Pääluokka. Täällä annetaan syötteitä toistaiseksi.
 */
package main;

import java.util.Scanner;
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
        while (true) {
                        Taulukko labyrintti = new Taulukko(20, 20);
                        labyrintti.luoTaulukko(0);
                        for (int i = 0; i < labyrintti.getKokox(); i++) {
                            for (int j = 0; j < labyrintti.getKokoy(); j++) {
                                labyrintti.haeNaapurit(labyrintti.getSolmu(i, j));
                            }
                        }
                        try {
                            String s = labyrintti.toString();
                            Dijkstra hakija = new Dijkstra(20, 20, labyrintti, 0, 0, 19, 19);
                            hakija.suoritaDijkstra();
                            hakija.lyhinPolku();
                            System.out.println(s);
                            System.out.println(labyrintti.toString());
                            break;
                        } catch (NullPointerException exception) {
                        }
                    }
    }
    
}
