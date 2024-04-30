package interfaces;

import clases.Carta;

public interface Calculos {
    public int calculoDanio (Carta carta1, int poderdefensa);

    public boolean golpeCritico ();

    public boolean esGolpeCritico ();

    public int checkArrays (Carta[] cartasjugador, Carta[] cartasenemigo);
}
