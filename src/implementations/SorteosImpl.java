package implementations;

import clases.Carta;
import interfaces.Sorteos;

import java.util.Random;

public class SorteosImpl implements Sorteos {
    private final Random r = new Random();
    @Override
    public Carta sorteoCartas(Carta[] cartas) { // este metodo sortea que cartas del mazo va a salir a combatir
        int sorteoCartas;
        Carta carta = null;
        while (carta == null) {
            for (int i = 0; i < cartas.length; i++) {
                if (cartas[i] != null) {
                    sorteoCartas = r.nextInt(2);
                    if (sorteoCartas == 0) {
                        carta = cartas[i];
                        cartas[i] = null;
                        carta.setCartaNro(i+1);
                        return carta;
                    }
                }
            }
        }
        return carta;
    }

    @Override
    public int sorteoPrimerTurno() {
        return r.nextInt(2);
    }

    @Override
    public int sorteoDesempate() {
        return r.nextInt(100) + 1;
    }
}
