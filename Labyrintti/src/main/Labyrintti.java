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
        Kayttoliittyma tekstikalli = new Kayttoliittyma();
        tekstikalli.kaynnista();
    }
    
}
