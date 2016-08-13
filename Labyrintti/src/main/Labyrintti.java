/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import labyrintti.Taulukko;
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
        Dijkstra dijkstra = new Dijkstra(20,20, labyrintti, 10, 10, 1, 1);
        dijkstra.initialiseSingleSource();
        dijkstra.suoritaDijkstra();
        dijkstra.lyhinPolku();
        labyrintti.tulosta();
        
    }
    
}
