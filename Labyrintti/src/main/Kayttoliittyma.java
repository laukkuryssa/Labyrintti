/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.Scanner;
import labyrintti.Taulukko;
import tietorakenteet.BellmanFord;
import tietorakenteet.Dijkstra;

public class Kayttoliittyma {

    Scanner lukija = new Scanner(System.in);

    public void kaynnista() {
        System.out.println("Tervetuloa!");
        while (true) {
            System.out.println("Labyrinttihaku, paina 1");
            System.out.println("Poistu, paina 2");
            String numero = lukija.nextLine();
            if (numero.equals("1")) {
                System.out.println("Kuinka leveän labyrintin haluat? (Kokonaisluku väliltä 3-1000)");
                int x = kysyKokonaisluku(3, 1000);
                System.out.println("Kuinka korkean labyrintin haluat? (Kokonaisluku väliltä 3-1000)");
                int y = kysyKokonaisluku(3, 1000);
                System.out.println("Syötä seinän todennäköisyys (0.00 - 0.40)");
                double tn = kysyLuku(0, 0.40);
                System.out.println("Anna lähtöpisteen x-korodinaatti (1 - taulukon leveys)");
                int lx = kysyKokonaisluku(1, x);
                System.out.println("Anna lähtöpisteen y-korodinaatti (1 - taulukon korkeus)");
                int ly = kysyKokonaisluku(1, y);
                System.out.println("Anna maalipisteen x-korodinaatti (1 - taulukon leveys)");
                int mx = kysyKokonaisluku(1, x);
                System.out.println("Anna maalipisteen y-korodinaatti (1 - taulukon korkeus)");
                int my = kysyKokonaisluku(1, y);
                System.out.println("Haluatko verrata Dijkstraa ja Bellman-Fordia? (k/e)");
                String vertailuhalu = lukija.nextLine();
                if (!vertailuhalu.equals("k") && !vertailuhalu.equals("e")) {
                        System.out.println("Syötä k tai e.");
                        vertailuhalu = lukija.nextLine();
                    }
                if (vertailuhalu.equals("e")) {
                    System.out.println("Jos haluat suorittaa reitinhaun Bellman-Fordilla,\n syötä b ja jos Dijkstralla, syötä d.");
                    String algoritmi = lukija.nextLine();
                    System.out.println("Tulostetaanko labyrintti? (k/e)");
                    String tulostus = lukija.nextLine();
                    if (!tulostus.equals("k") && !tulostus.equals("e")) {
                        System.out.println("Syötä k tai e.");
                        tulostus = lukija.nextLine();
                    }
                    while (!algoritmi.equals("b") && !algoritmi.equals("d")) {
                        System.out.println("Syötä b tai d.");
                        algoritmi = lukija.nextLine();
                    }
                    if (algoritmi.equals("d")) {
                        d(x, y, tn, lx, ly, mx, my, tulostus);
                        kysyUusinta(x, y, tn, lx, ly, mx, my, tulostus);
                    } else if (algoritmi.equals("b")) {
                        b(x, y, tn, lx, ly, mx, my, tulostus);
                        kysyUusinta(x, y, tn, lx, ly, mx, my, tulostus);
                    }
                } if (vertailuhalu.equals("k")) {
                    vertaa(x, y, tn, lx, ly, mx, my);
                }
            } else if (numero.equals("2")) {
                System.out.println("Näkemiin.");
                break;
            } else {
                System.out.println("Syötä 1 tai 2.");
            }
        }
    }

    public double kysyLuku(double a, double b) {
        while (true) {
            try {
                double luku = Double.parseDouble(lukija.nextLine().replace(",", "."));
                if (luku >= a && luku <= b) {
                    return luku;
                } else {
                    System.out.println("\nSyötä luku, joka on välillä [" + a + ","
                            + b + "].");
                }
            } catch (Exception poikkeus) {
                System.out.println("\nLue mitä pyydetään syöttämään!");
            }
        }
    }

    public int kysyKokonaisluku(int a, int b) {
        while (true) {
            try {
                int luku = Integer.parseInt(lukija.nextLine());
                if (luku >= a && luku <= b) {
                    return luku;
                } else {
                    System.out.println("\nSyötä kokonaisluku, joka on "
                            + "välillä [" + a + ","
                            + b + "].");
                }
            } catch (Exception poikkeus) {
                System.out.println("\nLue mitä pyydetään syöttämään!");
            }
        }
    }

    public long d(int x, int y, double tn, int lx, int ly, int mx, int my, String tulostus) {
        long aikaAlussa = 0;
        long aikaLopussa = 0;
        while (true) {
            Taulukko labyrintti = new Taulukko(x, y);
            labyrintti.luoTaulukko(tn);
            for (int i = 0; i < labyrintti.getKokox(); i++) {
                for (int j = 0; j < labyrintti.getKokoy(); j++) {
                    labyrintti.haeNaapurit(labyrintti.getSolmu(i, j));
                }
            }
            try {
                String s = labyrintti.toString();
                Dijkstra hakija = new Dijkstra(x, y, labyrintti, lx - 1, ly - 1, mx - 1, my - 1);
                aikaAlussa = System.currentTimeMillis();
                hakija.suoritaDijkstra();
                hakija.lyhinPolku();
                aikaLopussa = System.currentTimeMillis();
                if (tulostus.equals("k")) {
                    System.out.println(s);
                    System.out.println(labyrintti.toString());
                }
                System.out.println("Operaatioon kului aikaa: " + (aikaLopussa - aikaAlussa) + "ms.");
                return aikaLopussa - aikaAlussa;
            } catch (NullPointerException exception) {
            }
        }
    }

    public long b(int x, int y, double tn, int lx, int ly, int mx, int my, String tulostus) {
        long aikaAlussa = 0;
        long aikaLopussa = 0;
        while (true) {
            Taulukko labyrintti = new Taulukko(x, y);
            labyrintti.luoTaulukko(tn);
            for (int i = 0; i < labyrintti.getKokox(); i++) {
                for (int j = 0; j < labyrintti.getKokoy(); j++) {
                    labyrintti.haeNaapurit(labyrintti.getSolmu(i, j));
                }
            }
            try {
                String s = labyrintti.toString();
                BellmanFord hakija = new BellmanFord(x, y, labyrintti, lx - 1, ly - 1, mx - 1, my - 1);
                aikaAlussa = System.currentTimeMillis();
                hakija.suoritaBellmanFord();
                hakija.lyhinPolku();
                aikaLopussa = System.currentTimeMillis();
                if (tulostus.equals("k")) {
                    System.out.println(s);
                    System.out.println(labyrintti.toString());
                }
                System.out.println("Operaatioon kului aikaa: " + (aikaLopussa - aikaAlussa) + "ms.");
                return aikaLopussa - aikaAlussa;
            } catch (NullPointerException exception) {
            }
        }
    }

    public void kysyUusinta(int x, int y, double tn, int lx, int ly, int mx, int my, String tulostus) {
        System.out.println("Jos haluat uuden haun samoilla hakuehdoilla, \n"
                + "syötä d (Dijkstra) tai b (Bellman-Ford). Mikäli haluat \n"
                + "lopettaa, syötä jotain muuta.");
        String syote = lukija.nextLine();
        if (syote.equals("d")) {
            d(x, y, tn, lx, ly, mx, my, tulostus);
            kysyUusinta(x, y, tn, lx, ly, mx, my, tulostus);
        }
        if (syote.equals("b")) {
            b(x, y, tn, lx, ly, mx, my, tulostus);
            kysyUusinta(x, y, tn, lx, ly, mx, my, tulostus);
        }
    }

    public void vertaa(int x, int y, double tn, int lx, int ly, int mx, int my) {
        System.out.println("Montako toista tehdään? (1-1000)");
        int kerrat = kysyKokonaisluku(1, 1000);
        long aikaBellman = 0;
        long aikaDijkstra = 0;
        for (int i = 0; i < kerrat; i++) {
            aikaBellman += b(x, y, tn, lx, ly, mx, my, "e");
            aikaDijkstra += d(x, y, tn, lx, ly, mx, my, "e");
        }
        aikaBellman = aikaBellman / kerrat;
        aikaDijkstra = aikaDijkstra / kerrat;

        System.out.println("\nBellman-Fordin keskiarvo: " + aikaBellman + " ms.");
        System.out.println("Dijkstran keskiarvo: " + aikaDijkstra + " ms.");
    }

}
