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
public class Algoritmi {
    
    protected int[][] distance;
    protected Solmu[][] path;
    protected int kokox;
    protected int kokoy;
    protected Solmu alkusolmu;
    protected Solmu maalisolmu;
    protected Taulukko taulukko;

    /**
 *
 *  @param keko on Minimikeko, jota käytetään apuna Dijkstrassa.
 *  @param distance on taulukko, joka sisältää jokaisen taulukon alkion
 * tämänhetkisen etäisyysarvion lähtösolmusta s.
 *  @param path sisältää solmua edeltävän solmun.
 *  @param kokox on taulukon pituus.
 *  @param kokoy on taulukon korkeus.
 */
     
    public Algoritmi(int x, int y, Taulukko taulukko, int alkux, int alkuy, int loppux, int loppuy) {
        this.taulukko = taulukko;
        distance = new int[x][y];
        path = new Solmu[x][y];
        kokox = x;
        kokoy = y;
        this.alkusolmu = taulukko.getSolmu(alkux, alkuy);
        this.maalisolmu = taulukko.getSolmu(loppux, loppuy);
    }
    
    /**
 * initialiseSingleSource on Dijkstrassa käytetty alustusoperaatio, jossa
 * distance-taulukkoon asetetaan kaikki etäisyydet äärettömiksi (tässä tarpeeksi)
 * iso luku riittää ja path-taulukkoon kaikkien solmujen arvoksi null.
 */
    
    public void initialiseSingleSource() {
        for (int i = 0; i < kokox; i++) {
            for (int j = 0; j < kokoy; j++) {
                distance[i][j] = 1000000000;
                path[i][j] = null;
            }
        }
        distance[alkusolmu.getX()][alkusolmu.getY()]=0;
    }
    
    /**
 * Relaxointi on operaatio, jossa metodi saa parametrina solmut u ja v ja metodi
 * vertaa, onko lähtösolmusta u:hen ja siitä v:hen lyhyempi matka kuin tämän-
 * hetkinen tiedossa oleva matka suoraan lähtösolmusta v:hen. Mikäli näin
 * on, distance-taulukkoon muutetaan v:n arvoksi edellämainitun summan pituus
 * ja path-taulukkoon v:n arvoksi u.
 */
    
    public void relax(Solmu u, Solmu v) {
        if (distance[v.getX()][v.getY()] > distance[u.getX()][u.getY()] + v.getPaino()) {
            distance[v.getX()][v.getY()] = distance[u.getX()][u.getY()] + v.getPaino();
            path[v.getX()][v.getY()] = u;
        }
    }
    
    
    public void lyhinPolku() {
        maalisolmu.setArvo('R');
        alkusolmu.setArvo('R');
        Solmu u = path[maalisolmu.getX()][maalisolmu.getY()];
        while (u.getX() != alkusolmu.getX() || u.getY() != alkusolmu.getY()) {
            u.setArvo('R');
            u = path[u.getX()][u.getY()];
        }
    }

    
}
    
