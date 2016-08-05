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
        A = new Solmu[1000000000];
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
        return A[alkio.getPrioriteetti()/2];
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
        return A[alkio.getPrioriteetti()*2];
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
        return A[alkio.getPrioriteetti()*2+1];
    }
    
     /**
 * Metodi vaihda vaihtaa kahden solmun paikkaa taulukossa eli keossamme.
 * 
 */
    
    private void vaihda(Solmu eka, Solmu toka){
    Solmu muisti = eka;
    A[eka.getPrioriteetti()] = toka;
    A[toka.getPrioriteetti()] = muisti;
    }
    
    /**
 * Metodi onkoLehti tarkistaa, onko kyseisellä solmulla lapsia.
 * 
 */

    public boolean onkoLehti(Solmu i) {
        if (i.getPrioriteetti() > koko/2) {
            return true;
        }
        return false;
    }
    
    /**
 * Metodi lisaa lisää uuden solmun kekoon ja sijoittaa sen oikealle paikalleen.
 * Tarkalleen tämä tapahtuu siten, että ensin tietoa keon koosta muokataan.
 * Sitten taulukkoon lisätään viimeiselle paikalle uusi solmu. Sen jälkeen
 * vertaillaan vanhempien prioriteetteja juuri lisätyn solmun prioriteetin 
 * kanssa ja niin kauan kuin solmun prioriteetti on vanhemman prioriteettiä
 * pienempi (= minimikeossa suurempi), se siirtyy ylöspäin vaihtaen paikkaa
 * vanhempansa kanssa.
 * 
 */
    
    public void lisaa(Solmu alkio) {
        
        A[++koko] = alkio;
        Solmu tamanHetkinen = A[koko];
        while (A[vanhempi(tamanHetkinen).getPrioriteetti()].getPrioriteetti() > A[tamanHetkinen.getPrioriteetti()].getPrioriteetti()) {
            vaihda(vanhempi(tamanHetkinen),tamanHetkinen);
        }
    }
    
    /**
 * Metodi poppaa poistaa prioriteetiltaan ensimmäisen solmun keosta ja asettaa keon
 * tämän jälkeen järjestykseen.
 * Tarkalleen tämä tapahtuu siten, että ensin kyseinen solmu otetaan muistiin.
 * Sitten keon viimeisimpänä oleva alkio siirretään keon huipulle ja samalla 
 * keon kokoa pienennetään yhdellä.
 * Sitten kutsutaan heapify-metodia tuolle alkiolle, jolloin se siirtyy omalle
 * paikalleen keossa eli tuon solmun kohdalta rikkoutunut kekoehto korjaantuu.
 * Lopuksi palautetaan poistettu solmu. Tämä vastaa teorian heap-del-min
 * -metodia.
 * 
 */
    
    public Solmu poppaa() {
        Solmu popattava = A[1];
        A[1] = A[koko--];
        heapify(A[1]);
        return popattava;
    }
    
 /**
 * Metodi heapify korjaa kekoehdon parametrina annetun solmun osalta.
 * Heapifytä käytetään siis keon pohjalta huipulle siirretyn lehden siirtämi-
 * seen omalle paikalleen keossa.
 * Tarkalleen tämä tapahtuu siten, että ensin tarkastetaan, onko kyseinen solmu
   lehti. Mikäli on, sitä ei enää voi siirtää alaspäin, joten homma jää siihen.
 * Mikäli solmu ei ole lehti, katsotaan, onko jomman kumman lapsen 
 * prioriteetti pienempi (minimikeossa suurempi) kuin solmun. Jos ei, solmun ei
 * tarvitse mennä alemmas. Mikäli jommalla kummalla lapsella on kovempi priori-
 * teetti, katsotaan, kummalla on kovempi ja solmu vaihtaa tuon lapsen kanssa
 * paikkaa. Sitten kyseistä metodia kutsutaan rekursiivisesti keossa tuon
 * lapsen entisellä ja itse solmun nykyisellä paikalla olevalle solmulle eli
 * solmulle itselleen, jolloin solmu pääsee taas etenemään alemmas, mikäli
 * kekoehto niin vaatii.
 * 
 */
    
    public void heapify(Solmu i) {
        if (onkoLehti(i)) {
            return;
        }
        
        if ( A[i.getPrioriteetti()].getPrioriteetti() > A[vasen(i).getPrioriteetti()].getPrioriteetti() || A[i.getPrioriteetti()].getPrioriteetti() > A[oikea(i).getPrioriteetti()].getPrioriteetti()) {
            if (A[vasen(i).getPrioriteetti()].getPrioriteetti() < A[oikea(i).getPrioriteetti()].getPrioriteetti()) {
                vaihda(i, vasen(i));
                heapify(A[vasen(i).getPrioriteetti()]);
            }
            else {
                vaihda(i, oikea(i));
                heapify(A[oikea(i).getPrioriteetti()]);
            }
        }              
    }
    
    /**
 * Metodi palauttaa keon kärjessä olevan alkion.
 * 
 */
    
    public Solmu heapMin() {
        return A[1];
    }
    
    /**
 * Metodi antaa parametrina annetulle keon alkiolle uuden prioriteettiarvon,
 * mikäli se on kovempi, kuin entinen. Ensin siis tarkistetaan, onko kovempi.
 * 
 * 
 * 
 * 
 */
    
    public void heapDecKey(Solmu i, int uusiPrioriteetti) {
        if (uusiPrioriteetti < A[i.getPrioriteetti()].getPrioriteetti()) {
            A[i.getPrioriteetti()].setPrioriteetti(uusiPrioriteetti);
            while (i.getPrioriteetti()>1 && A[vanhempi(i).getPrioriteetti()].getPrioriteetti() > A[i.getPrioriteetti()].getPrioriteetti()) {
                vaihda(A[i.getPrioriteetti()], A[vanhempi(i).getPrioriteetti()]);
                i = vanhempi(i);
            }
        }
    }
    
    
    
}
