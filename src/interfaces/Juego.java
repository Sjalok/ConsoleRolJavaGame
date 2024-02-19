package interfaces;

public interface Juego {
    public void cartasAlAzar ();

    public void crearCartas ();

    public void ordenarCartas ();

    public void jugarTurno (Carta carta1, Carta carta2);

    public void mensajeWin ();

    public void mensajeLoose ();
}
