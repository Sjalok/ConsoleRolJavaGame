package interfaces;

import clases.Carta;

import java.io.IOException;

public interface Recompensas {
    public void recompensa (Carta carta, boolean jugadoroenemigo) throws IOException;
}
