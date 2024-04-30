package interfaces;

import clases.Carta;

import java.io.IOException;
import java.util.ArrayList;

public interface Juego {

    public void preJuego () throws IOException;

    public void registro();
    public void cartasAlAzar (ArrayList<Integer> numeros);

    public void crearEnemigo (ArrayList<Integer> numeros);

    public void crearCartas ();

    public boolean jugarTurnos () throws IOException;

    public Carta procesoCreacion (int numero, boolean azar);

    public int checkStats(int stat);
}
