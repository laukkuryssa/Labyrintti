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
 *  @param keko on Minimikeko, jota käytetään apuna Dijkstrassa.
 */
     
    public Dijkstra(int x, int y, Taulukko taulukko, int alkux, int alkuy, int loppux, int loppuy) {
        super(x, y, taulukko, alkux, alkuy, loppux, loppuy);
        keko = new Minimikeko();
    }
    
    /**
 * Varsinainen Dijkstran suoritus tapahtuu tässä. Kaikki taulukon solmut käydään
 * aluksi läpi ja ne lisätään kekoon. Kekoon jää päällimmäiseksi alkusolmu, jonka
 * paino on alustettu nollaksi. Sitten keosta poistetaan alkioita ja aina
 * haetaan poistetun alkion vierussolmut ja relaxoidaan poistetun solmun ja
 * sen naapurisolmujen etäisyys. Samalla päivitetään naapurisolmujen (jotka siis
 * vielä ovat keossa) paikka keossa.
 */
    
    public void suoritaDijkstra() {
        for (int i = 0; i < kokox; i++) {
            for (int j = 0; j < kokoy; j++) {
                Solmu alkio = taulukko.getSolmu(i, j);
                keko.lisaa(alkio);
            }
        }
        while (keko.getKoko() > 0) {
            Solmu u = keko.poppaa();
            for (int i = 0; i < 4; i++) {
                Solmu v = u.getVieruslista()[i];
                if (v!=null) {
                    relax(u, v);
                    keko.heapDecKey(v, v.getPaino());
                }
            }
        }
        
    }
    
}
