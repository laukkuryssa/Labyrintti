/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labyrintti;

import java.util.Random;

/**
 * Luokassa Taulukko luodaan Solmuista 2-ulotteinen taulukko,
 * luokkamuttujina taulukon pituus ja korkeus, sekä taulukko.
 * 
 */
public class Taulukko {
    
    public Solmu[][] taulukko;
    private int kokox;
    private int kokoy;

    public Taulukko(int x, int y) {
        this.taulukko = new Solmu[x][y];
        this.kokox = x;
        this.kokoy = y;
    }
    /**
     * Metodissa luoTaulukko annetaan parametrina muodostettavaan labyrinttiin
     * tulevien seinien prosentuaalinen todennäköisyys (prosentuaalinen osuus) 
     * prosenttikertoimena. Metodi taulukon verran Solmuja ja lisää ne
     * taulukkoon ja määrittää samalla, onko Solmu labyrintin seinää vaiko ei.
     * 
     */
    
    
    public Solmu[][] luoTaulukko(double SeinanTodnak) {       
        Random arpoja = new Random();       
        for (int i = 0; i < kokox; i++) {
            for (int j = 0; j < kokoy; j++) {
                Solmu solmu = new Solmu(i, j);
                if (arpoja.nextDouble() < SeinanTodnak) {
                    solmu.setPaasyFalse();
                    solmu.setArvo('E');
                    solmu.setPaino(kokox*kokoy+3);
                }
                taulukko[i][j] = solmu;
            }
        }
        return taulukko;
    }
    
    /**
 * Metodi haeNaapurit saa parametrina solmun, jonka naapurit se käy läpi.
 * Myös solmun ollessa taulukon reunalla, tämä otetaan huomioon.
 * 
 */
    
    public void haeNaapurit(Solmu solmu) {
        if (solmu.getX() != 0) {
            solmu.muokkaaVieruslistaa(taulukko[solmu.getX()-1][solmu.getY()], 0);
        }
        if (solmu.getY() != 0) {
            solmu.muokkaaVieruslistaa(taulukko[solmu.getX()][solmu.getY()-1], 1);
        }
        if (solmu.getX() != kokox-1) {
            solmu.muokkaaVieruslistaa(taulukko[solmu.getX()+1][solmu.getY()], 2);
        }
        if (solmu.getY() != kokoy-1) {
            solmu.muokkaaVieruslistaa(taulukko[solmu.getX()][solmu.getY()+1], 3);
        }         
    }

    public Solmu[][] getTaulukko() {
        return taulukko;
    }
    
    public int getKokox() {
        return kokox;
    }

    public int getKokoy() {
        return kokoy;
    }
    
    
    
}