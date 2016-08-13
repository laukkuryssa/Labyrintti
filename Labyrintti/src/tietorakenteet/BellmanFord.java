/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tietorakenteet;

import labyrintti.Solmu;
import labyrintti.Taulukko;

/**
 *
 * @author timohaut
 */
public class BellmanFord extends Algoritmi {
    
    public BellmanFord(int x, int y, Taulukko taulukko, int alkux, int alkuy, int loppux, int loppuy) {
        super(x, y, taulukko, alkux, alkuy, loppux, loppuy);
    }
    
    public void suoritaBellmanFord() {
        initialiseSingleSource();
        for (int i = 0; i < kokox*kokoy; i++) {
            
        }
    }
   
    
    
}
