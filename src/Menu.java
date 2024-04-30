import clases.Pausa;
import implementations.FileManagerImpl;
import implementations.JuegoImpl;
import implementations.MensajesImpl;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int opcion = 0;
        MensajesImpl mensajes = new MensajesImpl();
        JuegoImpl juego = new JuegoImpl();
        Pausa pausa = new Pausa();
        String rutaArchivo = System.getProperty("user.dir") + "/src/logs.txt";
        FileManagerImpl fileManager = new FileManagerImpl();

        do {
            System.out.println(" ");
            mensajes.cartelMenu();

            pausa.tiempo();

            System.out.println("Las opciones para elegir son: ");
            System.out.println("1. Jugar!");
            System.out.println("2. Instrucciones");
            System.out.println("3. un poco de Lore");
            System.out.println("4. Creditos");
            System.out.println("5. Leer los logs de batallas");
            System.out.println("6. Borrar los logs de batallas");
            System.out.println("7. Salir!");
            System.out.println("Que opcion deseas elegir? ");
            try {
                opcion = sc.nextInt();
                sc.nextLine();

                switch (opcion) {
                    case 1:
                        juego.preJuego();
                        break;
                    case 2:
                        mensajes.instrucciones();
                        break;
                    case 3:
                        mensajes.lore();
                        break;
                    case 4:
                        mensajes.creditos();
                        break;
                    case 5:
                        System.out.println(fileManager.leerArchivo(rutaArchivo));
                        break;
                    case 6:
                        fileManager.borrarContenido(rutaArchivo);
                        break;
                    case 7:
                        System.out.println("Gracias por jugar! (o por echar un vistazo al menú simplemente :).");
                        break;
                    default:
                        System.out.println("Los valores validos son entre el 1 y el 7!");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: solamente se valen numero enteros y no otro tipo de dato");
                sc.nextLine();
                opcion = 0;
            } catch (IOException e) {
                System.out.println("Error al leer el archivo de texto.");
            }
        } while ( opcion != 7);
        System.out.println("Gracias por jugar! ( o por echar un vistazo al menu simplemente :).");
    }
}