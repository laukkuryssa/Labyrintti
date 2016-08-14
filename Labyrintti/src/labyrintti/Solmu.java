/*
 * Tämä on alkio, joista sekä labyrintti että keko koostuvat. Siksi Solmu-
luokassa on kekoa varten jnro, joka kertoo Solmun paikkanumeron kekotaulukossa
ja toisaalta paino (etäisyysarvio lähtösolmuun), path (naapuri, jonka kautta
kulkee lyhin matka lähtösolmuun).
Solmulla on myös x- ja y-arvot sijainnille taulukossa,
parametri arvo on taas labyrintin tulostuksessa käytettävä arvo, 
boolean kertoo pääseekö kyseiseen kohtaan ja vieruslista on
4-paikkainen taulukko, jossa on solmun naapurisolmut.
 */
package labyrintti;

public class Solmu {
    private int x;
    private int y;
    private char arvo;
    private boolean paasy;
    private int paino;
    private Solmu path;
    private int jnro;
    private Solmu[] vieruslista;

    public Solmu(int x, int y) {
        this.path = null;
        this.x = x;
        this.jnro = 1;
        this.y = y;
        this.arvo = 'P';
        this.paasy = true;
        this.paino = 1;
        vieruslista = new Solmu[4];
        for (int i = 0; i < 4; i++) {
            vieruslista[i] = null;
        }
    }
    
    /**
 * Tämä on apumetodi, jota haeNaapurit-metodi käyttää.
 */
    
    public void muokkaaVieruslistaa(Solmu s, int paikka) {
        this.vieruslista[paikka] = s;
    }

    public char getArvo() {
        return arvo;
    }

    public void setArvo(char arvo) {
        this.arvo = arvo;
    }

    public int getJnro() {
        return jnro;
    }

    public void setJnro(int jnro) {
        this.jnro = jnro;
    }

    public int getPaino() {
        return paino;
    }

    public void setPaino(int paino) {
        this.paino = paino;
    }

    public boolean isPaasy() {
        return paasy;
    }

    public void setPaasy(boolean paasy) {
        this.paasy = paasy;
    }

    public Solmu[] getVieruslista() {
        return vieruslista;
    }

    public void setVieruslista(Solmu[] vieruslista) {
        this.vieruslista = vieruslista;
    }

    public Solmu getPath() {
        return path;
    }

    public void setPath(Solmu path) {
        this.path = path;
    }
    
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    
}
