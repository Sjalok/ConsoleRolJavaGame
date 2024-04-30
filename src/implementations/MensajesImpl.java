package implementations;

import clases.Carta;
import interfaces.Mensajes;

import java.io.IOException;

public class MensajesImpl implements Mensajes {

    private FileManagerImpl fileManager = new FileManagerImpl();
    private String rutaArchivo = System.getProperty("user.dir") + "/src/logs.txt";
    @Override
    public void cartelMenu() {
        System.out.println("-------------------------------");
        System.out.println("// CRG ( Console Role Game ) //");
        System.out.println("-------------------------------");
        System.out.println(" ");
    }

    @Override
    public void creditos() {
        System.out.println("-------------------------------------------------");
        System.out.println("// Juego creado por Franco 'Sjalok' Culotta! //");
        System.out.println("-------------------------------------------------");
        System.out.println(" ");
        System.out.println("------------------------------");
    }

    @Override
    public void instrucciones() {
        System.out.println("CRG es un juego por consola muy intuitivo en el que usaras 3 cartas ( aleatorias o creadas ) y ");
        System.out.println("te enfrentaras a algun general del malvado Sulfurus por el reino de Almoria. El juego se basa en un sistema por turnos, ");
        System.out.println("si tu carta derrota a la carta del oponente, tu carta vencedora va a recibir un power up aleatorio ( ojo que ocurre lo mismo con el oponente )");
        System.out.println("Buena suerte!");
        System.out.println(" ");
        System.out.print("------------------------------");
    }

    @Override
    public void lore() {
        System.out.println("Año 800 d.C., continente de Valtorius, el reino de Almoria siempre se vio envuelto en conflictos interminables");
        System.out.println("entre el benevolente emperador Lucarias III y el malvado emperador de Drakul, Sulfurus. Sulfurus con su codicia insaciable,");
        System.out.println("ha estado anhelando el control total sobre Almoria debido a sus vastos recursos y posicion estrategica");
        System.out.println(" ");
        System.out.println("Las tierras de Almoria, una vez prosperas se han visto empañadas por la opresion y el miedo que Sulfurus trae consigo");
        System.out.println("Es en este momento que el emperador Lucarias III decide poner fin a la epoca del miedo y decide enfrentar a Sulfurus en un duelo de comandantes");
        System.out.println("Sulfurus, orgulloso decide aceptar, sabiendo que sus implacables comandantes nunca pierden batallas, contando en sus filas a los guerreros mas temibles del mundo.");
        System.out.println("Ahi es cuando entras en juego tu, valiente comandante, elegido por el mismisimo Lucarias III");
        System.out.println("Si ganas, Sulfurus se retirara de Almoria, y si pierdes, la oscuridad se va a cernir sobre Almoria");
        System.out.println(" ");
        System.out.println("La libertad y esperanza de Almoria y millones de almorenses esta en tus manos, valiente guerrero!");
        System.out.println(" ");
        System.out.println("------------------------------");
    }

    @Override
    public void mensajeWin(String nombre,Carta[] vivos, Carta[] muertos) throws IOException {
        StringBuilder vivitos = new StringBuilder();
        StringBuilder perecidos = new StringBuilder();

        for (int i = 0; i < vivos.length; i++) {
            if (vivos[i] != null) {
                vivitos.append(vivos[i].mostrarCartaResumen());
            }
        }

        for (int i = 0; i < muertos.length; i++) {
            if (muertos[i] != null) {
                perecidos.append(muertos[i].mostrarCartaResumen());
            }
        }

        String texto = "Oh valiente " + nombre + ", gran defensor de Almoria\n" +
                "Has derrotado al temido comandante electo por Sulfurus\n" +
                "Forjando así un sendero de gloria y esperanza.\n\n" +
                "Los heraldos proclaman tu hazaña\n" +
                "y las campanas repican en tu honor.\n" +
                "En los salones y tabernas,\n" +
                "Los trovadores entonan canciones de tu victoria,\n" +
                "siempre acompañados por los instrumentos de los bardos.\n\n" +
                "Que tu nombre sea grabado en las crónicas,\n" +
                "como el héroe que trajo paz a nuestro reino.\n" +
                "Que resuene tu fama por todas las tierras de Almoria!\n\n" +
                "------------------------------\n\n" +
                "Los guerreros/heroes que quedaron vivos: \n";

        String texto2 = "\nLos honorificos guerreros que perecieron en la dura batalla: \n";

        System.out.println(texto);
        for (Carta vivo : vivos) {
            if (vivo != null) {
                System.out.println(vivo.mostrarCartaResumen());
            }
        }
        System.out.println(texto2);
        for (Carta muerto : muertos) {
            if (muerto != null) {
                System.out.println(muerto.mostrarCartaResumen());
            }
        }

        fileManager.escribirArchivo(rutaArchivo,texto);
        fileManager.escribirArchivo(rutaArchivo, vivitos.toString());
        fileManager.escribirArchivo(rutaArchivo,texto2);
        fileManager.escribirArchivo(rutaArchivo, perecidos.toString());
    }

    @Override
    public void mensajeLoose(String nombreJugador, String nombreComandante) throws IOException {

        String texto = "Oh " + nombreJugador + "\n" +
                "El destino ha tejido un amargo giro esta noche.\n" +
                nombreComandante + " se llevo la victoria,\n" +
                "llevando consigo sombras y lamentos.\n\n" +
                "Los corazones se llenan de pesar,\n" +
                "y las esperanzas del reino se desvanecen.\n" +
                "Las vaciones se tornan tristes,\n" +
                "lamentando la caida de nuestro valiente heroe\n\n" +
                "Que el eco de esta derrota nos recuerde la fragilidad,\n" +
                "y el malvado Sulfurus se regodee en su oscuro triunfo.\n" +
                "Pero aun hay un fuego en la llama de Almoria,\n" +
                "y mientras nos adentramos en una epoca de sufrimiento,\n" +
                "puede que el mañana sea testigo de una nueva aurora de esperanza.\n" +
                "-----------------------------\n";

        System.out.println(texto);
        fileManager.escribirArchivo(rutaArchivo,texto);
    }

    @Override
    public void mensajeResultado(Carta carta1, Carta carta2, int danio, boolean esCritico) throws IOException {
        String texto;
        if (danio == 0) {
            texto = carta2.getNombre() + " gracias a su implacable defensa y reflejos logro bloquear el ataque de " + carta1.getNombre() + ", por lo cual no hay daño alguno!";
            System.out.println(texto);
            fileManager.escribirArchivo(rutaArchivo,texto);
        } else if (esCritico) {
            texto = carta1.getNombre() + " ataca a " + carta2.getNombre() + ", asestandole un poderoso golpe" +
                    " critico, quitandole " + danio + " de salud." + carta2.getNombre() + " queda con " + carta2.getSalud() + " de vida.";
            System.out.println(texto);
            fileManager.escribirArchivo(rutaArchivo,texto);
        } else {
            texto = carta1.getNombre() + " ataca a " + carta2.getNombre()  + ", quitandole " + danio +
                    " de salud." + carta2.getNombre() + " queda con " + carta2.getSalud() + " de vida.";
            System.out.println(texto);
            fileManager.escribirArchivo(rutaArchivo,texto);
        }
    }

    @Override
    public void mensajeMuertePersonaje(String nombrePersonajeMuerto, String nombrePersonajeVencedor) throws IOException {
        String texto = nombrePersonajeMuerto + " muere a manos de " + nombrePersonajeVencedor + "\n---------------------------------\n";
        System.out.println(texto);

        fileManager.escribirArchivo(rutaArchivo,texto);
    }

    @Override
    public void mensajeDesempate(Carta carta1, Carta carta2) throws IOException {
        String texto = "Se terminaron la cantidad de turnos! se decidio ejecutar a " + carta2.getNombre() + " ya que tiene menor" +
                " cantidad de vida! asi que " + carta1.getNombre() + " se queda con la victoria!\n" +
                "no hay recompensa por este tipo de victoria!\n-----------------------------------\n";
        System.out.println(texto);

        fileManager.escribirArchivo(rutaArchivo,texto);
    }

    @Override
    public void mensajeSorteoDesempate(Carta carta1) throws IOException {
        String texto = "Se terminaron los turnos y ambos personajes quedaron empatados de vida! la posibilidad de que esto "
                + "pasara eran realmente bajas, pero paso!. Los nobles juntaran votos para decidir a quien ejecutan....\n" +
                "Los nobles han hablado, y quien sera ejecutado es " + carta1.getNombre() + ". Que mala suerte!\n" +
                "El unico consuelo es que ganando de esta forma no hay derecho a recompensa para el ganador!\n-----------------------------\n";

        System.out.println(texto);
        fileManager.escribirArchivo(rutaArchivo,texto);
    }
}
