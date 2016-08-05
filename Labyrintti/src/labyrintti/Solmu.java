/*
 * Tämä on alkio, joista sekä labyrintti että keko koostuvat. Siksi Solmu-
luokassa on kekoa varten prioriteettiarvo, x- ja y-arvot sijainnille taulukossa,
parametri arvo on taas labyrintin tulostuksessa käytettävä arvo, painoa käytetään
dijkstrassa, boolean kertoo solmun merkityksen labyrintissä ja vieruslista on
4-paikkainen taulukko, jossa on solmun naapurisolmut.
 */
package labyrintti;

public class Solmu {
    private int prioriteetti;
    private int x;
    private int y;
    private char arvo;
    private boolean paasy;
    private int paino;
    private Solmu[] vieruslista;

    public Solmu(int x, int y) {
        this.prioriteetti = 0;
        this.x = x;
        this.y = y;
        this.arvo = 'P';
        this.paasy = true;
        this.paino = 1;
        vieruslista = new Solmu[4];
        for (int i = 0; i < 4; i++) {
            vieruslista[i] = null;
        }
    }
    
    public char getArvo() {
        return arvo;
    }
    
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getPrioriteetti() {
        return prioriteetti;
    }
    
    public int getPaino() {
        return this.paino;
    }
    
    public Solmu[] getVieruslista() {
        return vieruslista;
    }

    public void setVieruslista(Solmu[] vieruslista) {
        this.vieruslista = vieruslista;
    }
    
    public void muokkaaVieruslistaa(Solmu s, int paikka) {
        this.vieruslista[paikka] = s;
    }

    public void setArvo(char arvo) {
        this.arvo = arvo;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    public void setPaasyFalse() {
        this.paasy = false;
    }
    
    public void setPaasyTrue() {
        this.paasy = true;
    }
    
    public void setPaino(int i) {
        this.paino = i;
    }

    public void setPrioriteetti(int prioriteetti) {
        this.prioriteetti = prioriteetti;
    }
    
    
}
