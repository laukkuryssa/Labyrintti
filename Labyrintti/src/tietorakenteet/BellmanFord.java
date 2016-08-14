/*
 * Täällä on toteutettu Bellman-Ford.
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

    /**
 *
 * Metodi suorittaa Bellman-Ford -algoritmin annetuilla syötteillä. Taulukon
 * jokainen alkio käydään läpi for-loopilla ja jokaisen kohdalla haetaan
 * naapurit ja näiden välinen yhteys relaxoidaan.
 */
    
    public void suoritaBellmanFord() {
        initialiseSingleSource();
        for (int j = 0; j < kokoy; j++) {
            for (int i = 0; i < kokox; i++) {
                Solmu alkio = taulukko.getSolmu(i, j);
                for (int k = 0; k < 4; k++) {
                    Solmu v = alkio.getVieruslista()[k];
                    if (v != null) {
                        relax(alkio, v);
                    }
                }
            }
        }
    }

}
