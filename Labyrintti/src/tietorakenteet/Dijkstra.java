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
public class Dijkstra extends Algoritmi {

    private Minimikeko keko;

    /**
     * @param keko on Minimikeko, jota käytetään apuna Dijkstrassa.
     */
    public Dijkstra(int x, int y, Taulukko taulukko, int alkux, int alkuy, int loppux, int loppuy) {
        super(x, y, taulukko, alkux, alkuy, loppux, loppuy);
        keko = new Minimikeko();
    }

    /**
     * Varsinainen Dijkstran suoritus tapahtuu tässä. Kaikki taulukon solmut
     * käydään aluksi läpi ja ne lisätään kekoon. Kekoon jää päällimmäiseksi
     * alkusolmu, jonka paino on alustettu nollaksi. Sitten keosta poistetaan
     * alkioita ja aina haetaan poistetun alkion vierussolmut ja relaxoidaan
     * poistetun solmun ja sen naapurisolmujen etäisyys. Samalla päivitetään
     * naapurisolmujen (jotka siis vielä ovat keossa) paikka keossa.
     */
    public void suoritaDijkstra() {
        int s = 0;
        initialiseSingleSource();
        for (int i = 0; i < kokox; i++) {
            for (int j = 0; j < kokoy; j++) {
                Solmu alkio = taulukko.getSolmu(i, j);
                if (alkio.isPaasy() == true) {
                    alkio.setKayty(false);
                    keko.lisaa(alkio);
                }
            }
        }
        while (keko.getKoko() > 0) {
            Solmu u = keko.poppaa();
            System.out.println(u.getX() + ", " + u.getY() + " " + u.getPaino());
            if (!u.getKayty()) {
                u.setKayty(true);
                for (int i = 0; i < 4; i++) {
                    Solmu v = u.getVieruslista()[i];
                    if (v != null) {
                        relax(u, v);
                        keko.heapDecKey(v);
                    }
                }
                u = null;
            }
        }
    }

    private int vertaa(Solmu a, Solmu b) {
        if (a.getX() == b.getX() && a.getY() == b.getY()) {
            return 1;
        }
        return 0;
    }

}
