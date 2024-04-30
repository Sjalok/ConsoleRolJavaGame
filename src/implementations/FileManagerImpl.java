package implementations;

import interfaces.FileManager;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileManagerImpl implements FileManager {
    @Override
    public void escribirArchivo(String nombrearchivo, String contenido) throws IOException {
        try (FileWriter writer = new FileWriter(nombrearchivo, true)) {
            writer.write(contenido);
            writer.write(System.lineSeparator());
        }
    }

    @Override
    public String leerArchivo(String nombrearchivo) throws IOException {
        StringBuilder contenido = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(nombrearchivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                contenido.append(linea).append(System.lineSeparator());
            }
        }

        if (contenido.length() == 0) {
            System.out.println("El log esta vacio!");
        }
        return contenido.toString();
    }

    @Override
    public void borrarContenido(String nombrearchivo) throws IOException {
        try (FileWriter writer = new FileWriter(nombrearchivo)) {
            writer.write("");
        }
    }
}
