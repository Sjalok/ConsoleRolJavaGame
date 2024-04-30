package interfaces;

import clases.Carta;

public interface Sorteos {
    public Carta sorteoCartas(Carta[] cartas);

    public int sorteoPrimerTurno();

    public int sorteoDesempate();
}
