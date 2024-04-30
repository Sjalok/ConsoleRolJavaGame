package implementations;

import clases.Carta;
import interfaces.Recompensas;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class RecompensasImpl implements Recompensas {

    private final Random r = new Random();

    private final Scanner sc = new Scanner(System.in);

    private String texto;

    private FileManagerImpl fileManager = new FileManagerImpl();

    private String rutaArchivo = System.getProperty("user.dir") + "/src/logs.txt";

    @Override
    public void recompensa(Carta carta, boolean jugadorOEnemigo) throws IOException {
        int sorteoRecompensa = r.nextInt(3);
        int opcion;

        if (jugadorOEnemigo) {
            System.out.println(" ");
            System.out.println("Gracias a tu victoria puede elegir recompensa, la decision de que sea al azar o elegida, es tuya!");
            do {
                System.out.println("Escriba 1 para recompensa al azar o 2 para elegir: ");
                try {
                    opcion = sc.nextInt();

                    if (opcion == 1) {
                        if (sorteoRecompensa == 0) {
                            carta.setNivel(carta.getNivel() + 1);
                            texto = "Tu personaje subio 1 nivel!";
                            System.out.println(texto);
                            fileManager.escribirArchivo(rutaArchivo,texto);
                        } else if (sorteoRecompensa == 1) {
                            carta.setSalud(carta.getSalud() + 20);
                            texto = "Tu personaje gano 20 puntos de vida!";
                            System.out.println(texto);
                            fileManager.escribirArchivo(rutaArchivo,texto);
                        } else {
                            carta.setArmadura(carta.getArmadura() + 1);
                            texto = "Tu personaje ha ganado un punto de armadura!";
                            fileManager.escribirArchivo(rutaArchivo,texto);
                            System.out.println(texto);
                        }
                        break;
                    } else if (opcion == 2){
                        System.out.println("Hay 3 tipos de recompensas: 1, subir 1 nivel. 2, ganar 20 de salud y 3, un punto mas de armadura.");
                        do {
                            System.out.println("Escriba 1 para nivel, 2 para salud y 3 para armadura: ");
                            opcion = sc.nextInt();

                            if (opcion == 1) {
                                carta.setNivel(carta.getNivel() + 1);
                                texto = "Tu personaje subio 1 nivel!";
                                System.out.println(texto);
                                fileManager.escribirArchivo(rutaArchivo,texto);
                                break;
                            } else if (opcion == 2) {
                                carta.setSalud(carta.getSalud() + 20);
                                texto = "Tu personaje gano 20 puntos de vida!";
                                System.out.println(texto);
                                fileManager.escribirArchivo(rutaArchivo,texto);
                                break;
                            } else if (opcion == 3){
                                carta.setArmadura(carta.getArmadura() + 1);
                                texto = "Tu personaje ha ganado un punto de armadura!";
                                fileManager.escribirArchivo(rutaArchivo,texto);
                                System.out.println(texto);
                                break;
                            } else {
                                System.out.println("Numero incorrecto. Por favor, intente de vuelta!");
                            }
                        } while (true);
                        break;
                    } else {
                        System.out.println("Las opciones son 1 y 2!");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Error: tiene que ser un tipo de dato entero!");
                    sc.nextLine();
                    opcion = 0;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }while (true);
            sc.nextLine();
        } else {
            if (sorteoRecompensa == 0) {
                carta.setNivel(carta.getNivel() + 1);

            } else if (sorteoRecompensa == 1) {
                carta.setSalud(carta.getSalud() + 20);

            } else {
                carta.setArmadura(carta.getArmadura() + 1);

            }
        }
    }
}
