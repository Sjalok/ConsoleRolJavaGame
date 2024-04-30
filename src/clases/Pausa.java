package clases;

import java.util.Scanner;

public class Pausa {

    // clase de ayuda para hacer que el juego no sea tan rapido y pueda ser mas legible

    Scanner sc = new Scanner(System.in);
    public void tiempo() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void seguir() {
        System.out.println("Presiona cualquier tecla para continuar.....");
        sc.nextLine();
        System.out.println(" ");
    }
}
