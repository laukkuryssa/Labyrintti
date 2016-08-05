/*
 * Täällä on toteutettu Dijkstra kekoa käyttäen.
 */
package tietorakenteet;

import labyrintti.Solmu;
import labyrintti.Taulukko;

/**
 *
 * @author timohaut
 */
public class Dijkstra {
    
    private Minimikeko keko;
    private int[][] distance;
    private Solmu[][] path;
    private int kokox;
    private int kokoy;

    /**
 *
 *  @param keko on Minimikeko, jota käytetään apuna Dijkstrassa.
 *  @param distance on taulukko, joka sisältää jokaisen taulukon alkion
 * tämänhetkisen etäisyysarvion lähtösolmusta s.
 *  @param path sisältää solmua edeltävän solmun.
 *  @param kokox on taulukon pituus.
 *  @param kokoy on taulukon korkeus.
 */
     
    public Dijkstra(int x, int y) {
        keko = new Minimikeko();
        distance = new int[x][y];
        path = new Solmu[x][y];
        kokox = x;
        kokoy = y;
    }
    
    /**
 * initialiseSingleSource on Dijkstrassa käytetty alustusoperaatio, jossa
 * distance-taulukkoon asetetaan kaikki etäisyydet äärettömiksi (tässä tarpeeksi)
 * iso luku riittää ja path-taulukkoon kaikkien solmujen arvoksi null.
 * Lopuksi aloitussolmun etäisyydeksi aloitussolmusta asetetaan 0.
 * @param alkusolmu on solmu, josta dijkstra aloittaa.
 */
    
    public void initialiseSingleSource(int kokox, int kokoy, Solmu alkusolmu) {
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
    
    /**
 * Varsinainen Dijkstran suoritus tapahtuu tässä. Tämä metodi on vielä kesken.
 */
    
    public void suoritaDijkstra(Solmu[][] taulukko, Solmu alkusolmu) {
        for (int i = 0; i < kokox; i++) {
            for (int j = 0; j < kokoy; j++) {
                Solmu alkio = taulukko[i][j];
                alkio.setPrioriteetti(distance[i][j]);
                keko.lisaa(alkio);
            }
        }
        while (keko.getKoko() > 0) {
            Solmu u = keko.poppaa();
            for (int i = 0; i < 4; i++) {
                Solmu v = u.getVieruslista()[i];
                if (v!=null) {
                    relax(u, v);
                    keko.heapDecKey(v, distance[v.getX()][v.getY()]);
                }
            }
        }
        
    }

    
}
