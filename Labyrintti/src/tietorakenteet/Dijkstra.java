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
public class Dijkstra extends Algoritmi{
    
    private Minimikeko keko;
    
    /**
 *
 *  @param keko on Minimikeko, jota käytetään apuna Dijkstrassa.
 *  @param distance on taulukko, joka sisältää jokaisen taulukon alkion
 * tämänhetkisen etäisyysarvion lähtösolmusta s.
 *  @param path sisältää solmua edeltävän solmun.
 *  @param kokox on taulukon pituus.
 *  @param kokoy on taulukon korkeus.
 */
     
    public Dijkstra(int x, int y, Taulukko taulukko, int alkux, int alkuy, int loppux, int loppuy) {
        super(x, y, taulukko, alkux, alkuy, loppux, loppuy);
        keko = new Minimikeko();
    }
    
    /**
 * Varsinainen Dijkstran suoritus tapahtuu tässä. Tämä metodi on vielä kesken.
 */
    
    public void suoritaDijkstra() {
        for (int i = 0; i < kokox; i++) {
            for (int j = 0; j < kokoy; j++) {
                Solmu alkio = taulukko.getSolmu(i, j);
                alkio.setPaino(distance[i][j]);
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
