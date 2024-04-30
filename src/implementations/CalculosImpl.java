package implementations;

import clases.Carta;
import interfaces.Calculos;

import java.util.Random;

public class CalculosImpl implements Calculos {
    private final Random r = new Random();
    private boolean golpeCritico;

    @Override
    public int calculoDanio(Carta carta1, int poderDefensa) { // metodo para calcular el daño
        double danioSR;
        int enteroED = r.nextInt(100) + 1;
        double efectividadDisparo = enteroED * 0.01;
        double valorAtaque = carta1.getPoderDisparo() + (carta1.getPoderDisparo() * efectividadDisparo );

        if (esGolpeCritico()) {
            if (valorAtaque * efectividadDisparo - poderDefensa == 0) {
                return 0;
            }
            double v = (((valorAtaque + (carta1.getPoderDisparo() * efectividadDisparo )) - poderDefensa) / 500) * 100;
            danioSR = v * 2;
        } else {
            if (valorAtaque * efectividadDisparo - poderDefensa == 0) {
                return 0;
            }
            danioSR = ((((valorAtaque+ (carta1.getPoderDisparo() * efectividadDisparo )) - poderDefensa) / 500) * 100);
        }

        if (danioSR <= 0) { // sin este condicional, hace daño positivo al jugador, agregandole mas vida
            return 0;
        }

        if (carta1.getRaza().equals("elfo") || carta1.getRaza().equals("enano")) {
            danioSR = danioSR * 1.05;
        } else if (carta1.getRaza().equals("orco")) {
            danioSR = danioSR * 1.10;
        }

        return (int) danioSR;
    }

    @Override
    public boolean golpeCritico() {
        return this.golpeCritico;
    }

    @Override
    public boolean esGolpeCritico() { // este metodo sortea si es golpe critico o no
        int probabilidadCritico = r.nextInt(100)+1;
        if (probabilidadCritico < 11) {
            this.golpeCritico = true;
            return true;
        } else {
            this.golpeCritico = false;
            return false;
        }
    }

    @Override
    public int checkArrays(Carta[] cartasjugador, Carta[] cartasenemigo) { // este metodo checkea que no queden mazos en la mano del jugador/enemigo, clave para checkear la victoria o derrota.
        int conteoJugador = 0, conteoEnemigo = 0;

        for (int i = 0; i < cartasenemigo.length; i++) {
            if (cartasjugador[i] == null) {
                conteoJugador += 1;
            }
            if (cartasenemigo[i] == null) {
                conteoEnemigo += 1;
            }
        }

        if (conteoJugador == 3) {
            return 1;
        } else if (conteoEnemigo == 3) {
            return 2;
        } else {
            return 3;
        }
    }
}
