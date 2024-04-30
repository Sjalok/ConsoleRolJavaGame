package interfaces;

import java.io.IOException;

public interface FileManager {
    void escribirArchivo (String nombrearchivo, String contenido) throws IOException;
    String leerArchivo (String nombrearchivo) throws IOException;
    void borrarContenido (String nombrearchivo) throws IOException;
}
