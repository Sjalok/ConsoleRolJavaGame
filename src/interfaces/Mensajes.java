package interfaces;

import clases.Carta;

import java.io.IOException;

public interface Mensajes {

    public void cartelMenu();

    public void creditos();

    public void instrucciones();

    public void lore();

    public void mensajeWin(String nombre, Carta[] vivos, Carta[] muertos) throws IOException;

    public void mensajeLoose(String nombreJugador, String nombreComandante) throws IOException;

    public void mensajeResultado (Carta carta1, Carta carta2, int danio, boolean esCritico) throws IOException;

    public void mensajeMuertePersonaje (String nombrepersonajemuerto, String nombrepersonajevencedor) throws IOException;

    public void mensajeDesempate (Carta carta1, Carta carta2) throws IOException;

    public void mensajeSorteoDesempate (Carta carta1) throws IOException;
}
