/*
 * Tämä luokka sisältää Dijkstran ja Bellman-Fordin apumetodit sekä yhteiset
muuttujat.
 */
package tietorakenteet;

import labyrintti.Solmu;
import labyrintti.Taulukko;

/**
 *  @param alkusolmu on alkusolmu, jonka parametrit syötetään luodessa
 *  algoritmi.
 *  @param maalisolmu on maalisolmu, jonka parametrit syötetään luodessa
 *  algoritmi.
 *  @param kokox on taulukon pituus.
 *  @param kokoy on taulukon korkeus.
 *  @param taulukko on labyrintti.
 */
public class Algoritmi {
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
        kokox = x;
        kokoy = y;
        this.alkusolmu = taulukko.getSolmu(alkux, alkuy);
        this.maalisolmu = taulukko.getSolmu(loppux, loppuy);
    }
    
    /**
 * initialiseSingleSource on Dijkstrassa käytetty alustusoperaatio, jossa
 * kaikkien solmujen painot asetetaan äärettömiksi (tässä tarpeeksi)
 * iso luku riittää ja path-solmuiksi null, sillä ne löytyvät haun myötä.
 */
    
    public void initialiseSingleSource() {
        for (int i = 0; i < kokox; i++) {
            for (int j = 0; j < kokoy; j++) {
                taulukko.getSolmu(i,j).setPaino(1000000000);
            }
        }
        alkusolmu.setPaino(0);
    }
    
    /**
 * Relaxointi on operaatio, jossa metodi saa parametrina solmut u ja v ja metodi
 * vertaa, onko lähtösolmusta u:hen ja siitä v:hen lyhyempi matka kuin tämän-
 * hetkinen tiedossa oleva matka suoraan lähtösolmusta v:hen. Mikäli näin
 * on, solmun painoksi muutetaan  muutetaan summan
 * ja v:n path-solmuksi u.
 */
    
    public void relax(Solmu u, Solmu v) {
        if (v.getPaino() > u.getPaino() + 1) {
            v.setPaino(u.getPaino() + 1);
            v.setPath(u);
        }
    }
    
    /**
 * Lyhin polku on linkitetty lista maalisolmusta lähtösolmuun, joka on
 * muodostunut relaxointien yhteydessä. Liikkeelle lähdetään maalisolmusta
 * ja edetään siitä aina eteenpäin kohti lähtösolmua path-solmun avulla.
 * Samalla muokataan solmujen kirjain-arvoa R (reitti), jolloin polku näkyy
 * tulostuksessa R-kirjaimista muodostuvana jonona.
 */
    
    public void lyhinPolku() {
        maalisolmu.setArvo('R');
        alkusolmu.setArvo('R');
        Solmu u = maalisolmu.getPath();
        while (u.getX() != alkusolmu.getX() || u.getY() != alkusolmu.getY()) {
            u.setArvo('R');
            u = u.getPath();
        }
    }

    
}
    
