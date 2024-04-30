package implementations;
import clases.Carta;
import clases.Datos;
import clases.Pausa;
import interfaces.Juego;

import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class JuegoImpl implements Juego {
    private Random r = new Random();
    private Scanner sc = new Scanner(System.in);
    private String nombreJugador;
    private String nombreEnemigo;
    private String texto;

    private ArrayList<Integer> numerosNombre = new ArrayList<>(); // arraylist para que no se repitan los nombres con los del enemigo si el jugador decide los nombres al azar

    private Carta[] cartasJugador = new Carta[3];

    private Carta[] cartasEnemigo = new Carta[3];

    private Carta[] muertosJugador = new Carta[3]; // array para luego mostrar cuales cartas perdieron del jugador

    private SorteosImpl sorteos = new SorteosImpl();

    private MensajesImpl mensajes = new MensajesImpl();

    private CalculosImpl calculos = new CalculosImpl();

    private RecompensasImpl recompensas = new RecompensasImpl();

    Pausa pausa = new Pausa();

    private FileManagerImpl fileManager = new FileManagerImpl();

    private String rutaArchivo = System.getProperty("user.dir") + "/src/logs.txt";

    @Override
    public void preJuego() throws IOException { // este metodo maneja to do el flujo del juego, invocando los correspondientes metodos
        int opcion = 0;
        String presentacion;
        boolean victoriaOrNot;
        registro();
        crearEnemigo(numerosNombre);
        System.out.println("Muy bien " + this.nombreJugador + ", ahora tenes que elegir entre usar cartas al azar o crearlas por vos mismo, es mucho mas divertido al azar ;) ");
        do {
            System.out.println("Opcion ( 1 cartas al azar, 2 crear cartas) : ");
            try {
                opcion = sc.nextInt();
                sc.nextLine();
                if (opcion == 1) {
                    cartasAlAzar(numerosNombre);
                    break;
                } else if (opcion == 2){
                    crearCartas();
                    break;
                } else {
                    System.out.println("Tiene que ser 1 o 2!");
                    opcion = 0;
                }
            } catch (InputMismatchException e) {
                System.out.println("Tiene que ser un numero entero!");
                sc.nextLine();
                opcion = 0;
            }
        } while (opcion != 3);
        System.out.println(" ");
        System.out.print("------------------------------");
        System.out.println(" ");
        System.out.println("Muy bien " + this.nombreJugador + ", el comandante enemigo sera " + this.nombreEnemigo);
        System.out.println("A continuacion te mostraremos tus cartas : ");
        System.out.println(" ");
        for (int i = 0; i < cartasJugador.length; i++) {
            System.out.println("Carta numero " + (i+1) + ": ");
            System.out.println("------------------------------");
            System.out.println(" ");
            cartasJugador[i].mostrarCarta();
            System.out.println(" ");
            pausa.seguir();
        }
        System.out.println("Y ahora si! espero que estes preparado para la batalla! >:) mucha suerte!");
        System.out.println(" ");
        System.out.println("------------------------------");
        System.out.println(" ");
        System.out.println(" ");
        System.out.println("tambores y trompetas suenan al unisono, y un hombre de pinta ostentosa presenta a... " + this.nombreJugador);
        System.out.println("Y su contrincante sera... " + this.nombreEnemigo);
        System.out.println("Quien saldra victorioso y decidira el destino de Almoria??");
        System.out.println(" ");
        System.out.print("------------------------------");

        presentacion = "tambores y trompetas suenan al unisono, y un hombre de pinta ostentosa presenta a... " + this.nombreJugador + "\n" +
                "Y su contrincante sera... " + this.nombreEnemigo + "\n" + "Quien saldra victorioso y decidira el destino de Almoria??";
        fileManager.escribirArchivo(rutaArchivo,presentacion);

        victoriaOrNot = jugarTurnos();
        System.out.println(" ");
        if (victoriaOrNot) {
            mensajes.mensajeWin(nombreJugador, cartasJugador, muertosJugador);
        } else {
            mensajes.mensajeLoose(nombreJugador, nombreEnemigo);
        }

        pausa.tiempo();
    }

    @Override
    public void registro() {
        System.out.println("Primero antes de arrancar... Dame un nombre ( en lo posible bien medieval! ;) ) : ");
        this.nombreJugador = sc.nextLine();
    }

    @Override
    public void cartasAlAzar(ArrayList<Integer> numeros) { // metodo para crear las cartas del jugador al azar
        String raza;
        String nombre;
        int razaNum, nombreNum, edad, velocidad, destreza, fuerza, armadura;
        for (int i = 0; i < 3; i++) {
            razaNum = r.nextInt(4) + 1;
            edad = r.nextInt(283) + 18;
            velocidad = r.nextInt(10) + 1;
            destreza = r.nextInt(10) + 1;
            fuerza = r.nextInt(10) + 1;
            armadura = r.nextInt(10) + 1;
            if ( razaNum == 1) {
                raza = "Humano";
                while (true) {
                    nombreNum = r.nextInt(20);
                    if (!numeros.contains(nombreNum)) {
                        numeros.add(nombreNum);
                        nombre = Datos.getNombreHumanos()[nombreNum];
                        break;
                    }
                }
            } else if ( razaNum == 2 ) {
                raza = "Orco";
                while (true) {
                    nombreNum = r.nextInt(19);
                    if (!numeros.contains(nombreNum)) {
                        numeros.add(nombreNum);
                        nombre = Datos.getNombreOrcos()[nombreNum];
                        break;
                    }
                }
            } else if ( razaNum == 3 ) {
                raza = "Elfo";
                while (true) {
                    nombreNum = r.nextInt(19);
                    if (!numeros.contains(nombreNum)) {
                        numeros.add(nombreNum);
                        nombre = Datos.getNombreElfos()[nombreNum];
                        break;
                    }
                }
            } else {
                raza = "Enano";
                while (true) {
                    nombreNum = r.nextInt(20);
                    if (!numeros.contains(nombreNum)) {
                        numeros.add(nombreNum);
                        nombre = Datos.getNombreEnanos()[nombreNum];
                        break;
                    }
                }
            }
            cartasJugador[i] = new Carta(raza, nombre,velocidad,destreza,fuerza,armadura,edad);
        }
    }

    @Override
    public void crearEnemigo(ArrayList<Integer> numeros) { // metodo para crear las cartas del enemigo al azar
        int nombreEne = r.nextInt(6);
        nombreEnemigo = Datos.getNombreEnemigo()[nombreEne];
        String raza = "";
        String nombre = "";
        int razaNum, nombreNum, edad, velocidad, destreza, fuerza, armadura;
        for (int i = 0; i < 3; i++) {
            razaNum = r.nextInt(4) + 1;
            edad = r.nextInt(300) + 1;
            velocidad = r.nextInt(10) + 1;
            destreza = r.nextInt(10) + 1;
            fuerza = r.nextInt(10) + 1;
            armadura = r.nextInt(10) + 1;
            if ( razaNum == 1) {
                raza = "Humano";
                while (true) {
                    nombreNum = r.nextInt(20);
                    if (!numeros.contains(nombreNum)) {
                        numeros.add(nombreNum);
                        nombre = Datos.getNombreHumanos()[nombreNum];
                        break;
                    }
                }
            } else if ( razaNum == 2 ) {
                raza = "Orco";
                while (true) {
                    nombreNum = r.nextInt(19);
                    if (!numeros.contains(nombreNum)) {
                        numeros.add(nombreNum);
                        nombre = Datos.getNombreOrcos()[nombreNum];
                        break;
                    }
                }
            } else if ( razaNum == 3 ) {
                raza = "Elfo";
                while (true) {
                    nombreNum = r.nextInt(19);
                    if (!numeros.contains(nombreNum)) {
                        numeros.add(nombreNum);
                        nombre = Datos.getNombreElfos()[nombreNum];
                        break;
                    }
                }
            } else {
                raza = "Enano";
                while (true) {
                    nombreNum = r.nextInt(20);
                    if (!numeros.contains(nombreNum)) {
                        numeros.add(nombreNum);
                        nombre = Datos.getNombreEnanos()[nombreNum];
                        break;
                    }
                }
            }
            cartasEnemigo[i] = new Carta(raza, nombre,velocidad,destreza,fuerza,armadura,edad);
        }
    }

    @Override
    public void crearCartas() { // metodo que para crear las cartas del jugador a mano, pudiendo elegir entre stats aleatorios o no
        int opcion = 0;

        System.out.println("Hay 2 caminos para elegir en lo que crear las cartas se refiere...");
        System.out.println("En los dos caminos, elegis raza, nombre, edad y apodo");
        System.out.println("Pero en uno podes elegir los stats ( velocidad, destreza, etc..) y en la otra se eligen al azar ( recomendado )");
        do {
            System.out.println("por que opcion deseas optar? 1 para stats random. 2 para elegir stats: ");
            try {
                opcion = sc.nextInt();
                sc.nextLine();
                if (opcion == 1) {
                    for (int i = 0; i < 3; i++) {
                        cartasJugador[i] = procesoCreacion(i+1,true);
                    }
                    break;
                } else if (opcion == 2) {
                    for (int i = 0; i < 3; i++) {
                        cartasJugador[i] = procesoCreacion(i+1,false);
                    }
                    break;
                }
                else {
                    System.out.println("Tiene que ser 1 o 2!");
                    opcion = 0;
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Tiene que ser un dato entero!");
                sc.nextLine();
                opcion = 0;
            }
        } while (opcion != 3);
    }

    @Override
    public boolean jugarTurnos() throws IOException { // metodo donde ocurren todas las batallas
        int nroRondas = 1;
        int danio;
        int turnoJug = 0;
        int turnoEne = 0;

        pausa.tiempo();
        System.out.println(" ");
        System.out.println("ARRANCA LA ENCARNIZADA BATALLA");
        System.out.println(" ");
        System.out.print("------------------------------");
        System.out.println(" ");

        while (true) {
            int turnos = 1;
            int sorteo = sorteos.sorteoPrimerTurno();
            String rondas, TCarta1, TCarta2, TSorteo;

            while (true) {
                Carta cartaJugador = null;
                Carta cartaEnemigo = null;
                System.out.println(" ");
                rondas = "Empieza la ronda numero " + nroRondas;
                System.out.println(rondas);
                fileManager.escribirArchivo(rutaArchivo,rondas);
                System.out.println("Empieza el sorteo de que cartas se van a enfrentar...");

                cartaJugador = sorteos.sorteoCartas(cartasJugador);
                cartaEnemigo = sorteos.sorteoCartas(cartasEnemigo);

                pausa.tiempo();

                TCarta1 = "Las cartas que se van a enfrentar son: \n" +
                        "Del jugador " + nombreJugador + " ha salido elegida la carta numero " + cartaJugador.getCartaNro() + "\n" +
                        "Los datos del personaje...\n" + cartaJugador.mostrarCartaResumen() + "\n";

                TCarta2 = "\nY de tu contrincante " + nombreEnemigo + " ha salido elegida la carta numero " + cartaEnemigo.getCartaNro() + "\n" +
                    "Los datos de la carta enemiga.....\n" + cartaEnemigo.mostrarCartaResumen() + "\n";

                fileManager.escribirArchivo(rutaArchivo, TCarta1);
                fileManager.escribirArchivo(rutaArchivo, TCarta2);

                System.out.println("Las cartas que se van a enfrentar son: ");
                System.out.println("Del jugador " + nombreJugador + " ha salido elegida la carta numero " + cartaJugador.getCartaNro());
                System.out.println("Los datos del personaje.... ");
                System.out.println(" ");
                cartaJugador.mostrarCarta();
                pausa.tiempo();
                System.out.println(" ");
                System.out.println("Y de tu contrincante " + nombreEnemigo + " ha salido elegida la carta numero " + cartaEnemigo.getCartaNro());
                System.out.println("Los datos del personaje enemigo.... ");
                System.out.println(" ");
                cartaEnemigo.mostrarCarta();
                pausa.tiempo();
                System.out.println(" ");

                if (sorteo == 0) {
                    TSorteo = "El sorteo determino que arranca atacando " + nombreJugador;
                    System.out.println("El sorteo determino que arranca atacando " + nombreJugador);
                    turnoJug = 1;
                } else {
                    TSorteo = "El sorteo determino que arranca atacando " + nombreEnemigo;
                    System.out.println("El sorteo determino que arranca atacando " + nombreEnemigo);
                    turnoEne = 1;
                }

                fileManager.escribirArchivo(rutaArchivo,TSorteo);
                System.out.println(" ");

                pausa.seguir();

                while (true) {
                    System.out.println("------------------------------");
                    if (turnoJug == 1) { // aca es donde esta toda la cuestion del daño y demas
                        danio = calculos.calculoDanio(cartaJugador, cartaEnemigo.getPoderDefensa());
                        if (danio >= cartaEnemigo.getSalud()) {
                            cartaEnemigo.setSalud(0);
                            mensajes.mensajeResultado(cartaJugador,cartaEnemigo,danio, calculos.golpeCritico());
                            mensajes.mensajeMuertePersonaje(cartaEnemigo.getNombre(), cartaJugador.getNombre());
                            if (calculos.checkArrays(cartasJugador,cartasEnemigo) == 2) {
                                cartasJugador[cartaJugador.getCartaNro() - 1] = cartaJugador;
                                return true;
                            }
                            recompensas.recompensa(cartaJugador, true);
                            cartasJugador[cartaJugador.getCartaNro() - 1] = cartaJugador;
                            break;
                        } else {
                            cartaEnemigo.setSalud(cartaEnemigo.getSalud() - danio);
                            mensajes.mensajeResultado(cartaJugador,cartaEnemigo,danio, calculos.golpeCritico());
                            danio = calculos.calculoDanio(cartaEnemigo,cartaJugador.getPoderDefensa());
                            if (danio >= cartaJugador.getSalud()) {
                                muertosJugador[cartaJugador.getCartaNro() - 1] = cartaJugador;
                                cartaJugador.setSalud(0);
                                mensajes.mensajeResultado(cartaEnemigo,cartaJugador,danio, calculos.golpeCritico());
                                mensajes.mensajeMuertePersonaje(cartaJugador.getNombre(),cartaEnemigo.getNombre());
                                if (calculos.checkArrays(cartasJugador,cartasEnemigo) == 1) {
                                    return false;
                                }
                                recompensas.recompensa(cartaEnemigo, false);
                                cartasEnemigo[cartaEnemigo.getCartaNro() - 1] = cartaEnemigo;
                                break;
                            } else {
                                cartaJugador.setSalud(cartaJugador.getSalud() - danio);
                                mensajes.mensajeResultado(cartaEnemigo,cartaJugador,danio, calculos.golpeCritico());
                            }
                        }
                    } else if (turnoEne == 1) {
                        danio = calculos.calculoDanio(cartaEnemigo, cartaJugador.getPoderDefensa());
                        if (danio >= cartaJugador.getSalud()) {
                            muertosJugador[cartaJugador.getCartaNro() - 1] = cartaJugador;
                            cartaJugador.setSalud(0);
                            mensajes.mensajeResultado(cartaEnemigo,cartaJugador,danio, calculos.golpeCritico());
                            mensajes.mensajeMuertePersonaje(cartaJugador.getNombre(),cartaEnemigo.getNombre());
                            if (calculos.checkArrays(cartasJugador,cartasEnemigo) == 1) {
                                return false;
                            }
                            recompensas.recompensa(cartaEnemigo, false);
                            cartasEnemigo[cartaEnemigo.getCartaNro() - 1] = cartaEnemigo;
                            break;
                        } else {
                            cartaJugador.setSalud(cartaJugador.getSalud() - danio);
                            mensajes.mensajeResultado(cartaEnemigo,cartaJugador,danio, calculos.golpeCritico());
                            danio = calculos.calculoDanio(cartaJugador, cartaEnemigo.getPoderDefensa());
                            if (danio >= cartaEnemigo.getSalud()) {
                                cartaEnemigo.setSalud(0);
                                mensajes.mensajeResultado(cartaJugador,cartaEnemigo,danio, calculos.golpeCritico());
                                mensajes.mensajeMuertePersonaje(cartaEnemigo.getNombre(), cartaJugador.getNombre());
                                if (calculos.checkArrays(cartasJugador,cartasEnemigo) == 2) {
                                    cartasJugador[cartaJugador.getCartaNro() - 1] = cartaJugador;
                                    return true;
                                }
                                recompensas.recompensa(cartaJugador,true);
                                cartasJugador[cartaJugador.getCartaNro() - 1] = cartaJugador;
                                break;
                            } else {
                                cartaEnemigo.setSalud(cartaEnemigo.getSalud() - danio);
                                mensajes.mensajeResultado(cartaJugador,cartaEnemigo,danio, calculos.golpeCritico());
                            }
                        }
                    }
                    if (turnos == 7) { // si la ronda llega a los 7 turnos, se decide quien se va a ejecutar decidiendose por el que tiene menos vida
                        if (cartaJugador.getSalud() > cartaEnemigo.getSalud()) {
                            cartasJugador[cartaJugador.getCartaNro() - 1] = cartaJugador;
                            mensajes.mensajeDesempate(cartaJugador,cartaEnemigo);
                            if (calculos.checkArrays(cartasJugador,cartasEnemigo) == 2) {
                                return true;
                            }
                            break;
                        } else if (cartaEnemigo.getSalud() > cartaJugador.getSalud()) {
                            cartasEnemigo[cartaEnemigo.getCartaNro() - 1] = cartaEnemigo;
                            muertosJugador[cartaJugador.getCartaNro() - 1] = cartaJugador;
                            mensajes.mensajeDesempate(cartaEnemigo,cartaJugador);
                            if (calculos.checkArrays(cartasJugador,cartasEnemigo) == 1) {
                                return false;
                            }
                            break;
                        } else { // si los 2 tienen la misma cantidad de vida, los " nobles " deciden quien se ejecuta
                            int sorteoDesempate = sorteos.sorteoDesempate();
                            if (sorteoDesempate > 50) {
                                cartasEnemigo[cartaEnemigo.getCartaNro() - 1] = cartaEnemigo;
                                mensajes.mensajeSorteoDesempate(cartaJugador);
                                if (calculos.checkArrays(cartasJugador,cartasEnemigo) == 1) {
                                    return false;
                                }
                            } else {
                                cartasJugador[cartaJugador.getCartaNro() - 1] = cartaJugador;
                                muertosJugador[cartaJugador.getCartaNro() - 1] = cartaJugador;
                                mensajes.mensajeSorteoDesempate(cartaEnemigo);
                                if (calculos.checkArrays(cartasJugador,cartasEnemigo) == 2) {
                                    return true;
                                }
                            }
                            break;
                        }
                    }
                    System.out.println("------------------------------");
                    System.out.println(" ");
                    pausa.seguir();

                    if (turnoJug == 1) {
                        turnoJug = 0;
                        turnoEne = 1;
                    } else {
                        turnoJug = 1;
                        turnoEne = 0;
                    }
                    turnos++;
                }
                nroRondas++;
                break;
            }
        }
    }

    @Override
    public Carta procesoCreacion(int numero, boolean azar) { // metodo para ayudar al metodo crear cartas, para que no tenga tanta carga
        Carta carta;
        int edad, velocidad = 0, fuerza = 0, destreza = 0, armadura = 0;
        String nombre = " ";
        String raza = " ";
        String apodo = " ";
        System.out.println("Las razas disponibles son: Humano, Orco, Elfo, Enano");
        if (azar) {
            do {
                System.out.println("Escribe que raza te gustaria que fuese la carta " + numero + " : ");
                raza = sc.nextLine().toLowerCase();

                if (raza.equals("humano") || raza.equals("orco") || raza.equals("elfo") || raza.equals("enano")) {
                    break;
                } else {
                    System.out.println("Raza no valida. Por favor, intente de nuevo!");
                }
            } while (true);

            System.out.println("Nombre? ");
            nombre = sc.nextLine();
            System.out.println("Apodo? ");
            apodo = sc.nextLine();

            do {
                System.out.println("Edad? ");
                try {
                    edad = sc.nextInt();
                    sc.nextLine();
                    if (edad >= 18 && edad <= 300) {
                        break;
                    } else {
                        System.out.println("La edad minima es 18 y la maxima es 300!");
                        System.out.println(" ");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Tiene que ser tipo de dato entero!");
                    sc.nextLine();
                    edad = 0;
                }
            } while (true);

            velocidad = r.nextInt(10) + 1;
            destreza = r.nextInt(10) + 1;
            fuerza = r.nextInt(10) + 1;
            armadura = r.nextInt(10) + 1;

            carta = new Carta(raza, nombre,apodo, edad, velocidad,destreza,fuerza,armadura,edad);
            System.out.println(" ");
            System.out.print("------------------------------");
            System.out.println(" ");
            return carta;
        } else {
            do {
                System.out.println("Escribe que raza te gustaria que fuese la carta " + numero + " : ");
                raza = sc.nextLine().toLowerCase();

                if (raza.equals("humano") || raza.equals("orco") || raza.equals("elfo") || raza.equals("enano")) {
                    break;
                } else {
                    System.out.println("Raza no valida. Por favor, intente de nuevo!");
                }
            } while (true);

            System.out.println("Nombre? ");
            nombre = sc.nextLine();
            System.out.println("Apodo? ");
            apodo = sc.nextLine();

            do {
                System.out.println("Edad? ");
                try {
                    edad = sc.nextInt();
                    sc.nextLine();
                    if (edad >= 18 && edad <= 300) {
                        break;
                    } else {
                        System.out.println("La edad minima es 18 y la maxima es 300!");
                        System.out.println(" ");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Tiene que ser tipo de dato entero!");
                    sc.nextLine();
                    edad = 0;
                }
            } while (true);

            try {
                System.out.println("Velocidad (entre 1 y 10): ");
                velocidad = checkStats(sc.nextInt());

                System.out.println("Destreza (entre 1 y 10): ");
                destreza = checkStats(sc.nextInt());

                System.out.println("Fuerza (entre 1 y 10): ");
                fuerza = checkStats(sc.nextInt());

                System.out.println("Armadura (entre 1 y 10): ");
                armadura = checkStats(sc.nextInt());

            } catch (InputMismatchException e) {
                System.out.println("Error: Debe ser un tipo de dato entero!");
                sc.nextLine();
            }

            carta = new Carta (raza, nombre,apodo, edad, velocidad,destreza,fuerza,armadura,edad);
            System.out.println(" ");
            System.out.print("------------------------------");
            System.out.println(" ");
            return carta;
        }
    }

    @Override
    public int checkStats(int stat) { // metodo para ayudar al metodo procesoCreacion con el tema de los stats, para que no quede tan cargado de codigo
        int ayudin = 0;

        if (stat >= 1 && stat <= 10){
            return stat;
        } else {
            do {
                System.out.println("Numero incorrecto! tiene que ser entre 1 y 10!");
                System.out.println("Intente de vuelta: ");
                try {
                    ayudin = sc.nextInt();
                    if (ayudin >= 1 && ayudin <=10) {
                        sc.nextLine();
                        return ayudin;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("El tipo de dato debe de ser un entero!");
                    sc.nextLine();
                    ayudin = 0;
                }
            }while (true);
        }
    }
}
