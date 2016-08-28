/*
 * Luokassa Minimikeko on toteutettu minimikeko, jonka tulisi toimia apuna
 * Dijkstra-algoritmille. Keko on toteutettu taulukkona, joka sisältää KeonAlki-
 * oita.
 */
package tietorakenteet;

import labyrintti.Solmu;

/**
 * Keossa on myös koko omana luokkamuttujanaan, joka helpottaa Dijkstran
 * toimintaa.
 *
 */
public class Minimikeko {

    private Solmu[] A;
    private int koko;

    public Minimikeko() {
        A = new Solmu[100000];
        koko = 0;
    }

    public int getKoko() {
        return koko;
    }

    /**
     * Metodi vanhempi kertoo keon puurakenteessa kyseisen solmun vanhemman.
     * Tämä metodi saa syötteenä yhden solmun ja palauttaa toisen.
     *
     *
     * @param alkio on solmu, jonka vanhempaa selvitetään.
     * @return Solmu-tyyppinen vanhempi.
     *
     */
    public Solmu vanhempi(Solmu alkio) {
        return A[alkio.getJnro() / 2];
    }

    /**
     * Metodi vasen kertoo keon puurakenteessa kyseisen solmun vasemman lapsen.
     * Tämä metodi saa syötteenä yhden solmun ja palauttaa toisen.
     *
     * @param alkio on solmu, jonka vasenta lasta selvitetään.
     * @return Solmu-tyyppinen vasen lapsi.
     *
     */
    public Solmu vasen(Solmu alkio) {
        if (onkoLehti(alkio)) {
            return null;
        }
        return A[alkio.getJnro() * 2];
    }

    /**
     * Metodi oikea kertoo keon puurakenteessa kyseisen solmun oikean lapsen.
     * Tämä metodi saa syötteenä yhden solmun ja palauttaa toisen.
     *
     *
     * @param alkio on solmu, jonka oikeaa lasta selvitetään.
     * @return Solmu-tyyppinen oikea lapsi.
     *
     */
    public Solmu oikea(Solmu alkio) {
        if (onkoLehti(alkio)) {
            return null;
        }
        return A[alkio.getJnro() * 2 + 1];
    }

    /**
     * Metodi vaihda vaihtaa kahden solmun paikkaa taulukossa eli keossamme.
     * Ennen sitä vaihdetaan vielä näiden järjestysnumerot päittäin joten
     * lopulta kun järjestys numerot vaihtavat sijaintia kaksi kertaa, ne
     * pysyvätkin paikallaan ja kaikki muu vaihtuu.
     *
     */
    private void vaihda(Solmu eka, Solmu toka) {
        int jnroeka = eka.getJnro();
        int jnrotoka = toka.getJnro();
        eka.setJnro(jnrotoka);
        toka.setJnro(jnroeka);
        A[jnroeka] = toka;
        A[jnrotoka] = eka;
    }

    /**
     * Metodi onkoLehti tarkistaa, onko kyseisellä solmulla lapsia.
     *
     */
    public boolean onkoLehti(Solmu i) {
        return (i.getJnro() <= koko / 2);
    }

    /**
     * Metodi lisaa lisää uuden solmun kekoon ja sijoittaa sen oikealle
     * paikalleen. Tarkalleen tämä tapahtuu siten, että ensin tietoa keon koosta
     * muokataan. Sitten taulukkoon lisätään viimeiselle paikalle uusi solmu.
     * Sen jälkeen vertaillaan vanhempien painoja juuri lisätyn solmun painon
     * kanssa ja niin kauan kuin solmun paino on vanhemman painoa pienempi, se
     * siirtyy ylöspäin vaihtaen paikkaa vanhempansa kanssa.
     *
     */
    public void lisaa(Solmu alkio) {

        A[++koko] = alkio;
        alkio.setJnro(koko);
        if (alkio.getJnro() != 1) {
            while (alkio.getJnro() != 1 && vanhempi(alkio).getPaino() > alkio.getPaino()) {
                if (vanhempi(alkio).getJnro() == 1) {
                    System.out.println("HÄLYTYS" + alkio.getX() + " " + alkio.getY());
                }
                vaihda(vanhempi(alkio), alkio);
            }
        }
    }

    /**
     * Metodi poppaa poistaa prioriteetiltaan ensimmäisen solmun keosta ja
     * asettaa keon tämän jälkeen järjestykseen. Tarkalleen tämä tapahtuu siten,
     * että ensin kyseinen solmu otetaan muistiin. Sitten keon viimeisimpänä
     * oleva alkio siirretään keon huipulle ja samalla keon kokoa pienennetään
     * yhdellä. Sitten kutsutaan heapify-metodia tuolle alkiolle, jolloin se
     * siirtyy omalle paikalleen keossa eli tuon solmun kohdalta rikkoutunut
     * kekoehto korjaantuu. Lopuksi palautetaan poistettu solmu. Tämä vastaa
     * teorian heap-del-min -metodia.
     *
     */
    public Solmu poppaa() {
        Solmu popattava = A[1];
        Solmu viimeinen = A[koko];
        A[koko] = null;
        koko--;
        viimeinen.setJnro(1);
        A[1] = viimeinen;
        heapify(A[1]);
        return popattava;
    }

    /**
     * Metodi heapify korjaa kekoehdon parametrina annetun solmun osalta.
     * Heapifytä käytetään siis keon pohjalta huipulle siirretyn lehden
     * siirtämi- seen omalle paikalleen keossa. Tarkalleen tämä tapahtuu siten,
     * että ensin tarkastetaan, onko kyseinen solmu lehti. Mikäli on, sitä ei
     * enää voi siirtää alaspäin, joten homma jää siihen. Mikäli solmu ei ole
     * lehti, katsotaan, onko jomman kumman lapsen paino pienempi kuin solmun.
     * Jos ei, solmun ei tarvitse mennä alemmas. Mikäli jommalla kummalla
     * lapsella on kovempi priori- teetti, katsotaan, kummalla on kovempi ja
     * solmu vaihtaa tuon lapsen kanssa paikkaa. Sitten kyseistä metodia
     * kutsutaan rekursiivisesti keossa tuon lapsen entisellä ja itse solmun
     * nykyisellä paikalla olevalle solmulle eli solmulle itselleen, jolloin
     * solmu pääsee taas etenemään alemmas, mikäli kekoehto niin vaatii.
     *
     */
    public void heapify(Solmu i) {
        if (onkoLehti(i)) {
            return;
        }
        if (vasen(i) != null && oikea(i) != null) {
            if (i.getPaino() > vasen(i).getPaino() || i.getPaino() > oikea(i).getPaino()) {
                if (vasen(i).getPaino() < oikea(i).getPaino()) {
                    vaihda(i, vasen(i));
                    heapify(i);
                } else {
                    vaihda(i, oikea(i));
                    heapify(i);
                }
            }
        } else if (vasen(i) != null && i.getPaino() > vasen(i).getPaino()) {
            vaihda(i, vasen(i));
            heapify(i);
        } else if (oikea(i) != null && i.getPaino() > oikea(i).getPaino()) {
            vaihda(i, oikea(i));
            heapify(i);
        }

    }

    /**
     * Metodi palauttaa keon kärjessä olevan alkion.
     */
    public Solmu heapMin() {
        return A[1];
    }

    /**
     * Metodi antaa parametrina annetulle keon alkiolle uuden prioriteettiarvon,
     * mikäli se on kovempi, kuin entinen. Ensin siis tarkistetaan, onko
     * kovempi.
     */
    public void heapDecKey(Solmu i) {
        if (!i.getKayty()) {
            while (i.getJnro() != 1 && i.getPaino() < vanhempi(i).getPaino()) {
                vaihda(i, vanhempi(i));
            }
        }
    }
    
}
