package clases;

public class Carta {
    private final String raza;

    private final String nombre;

    private String apodo = "Ninguno";

    private int anioNacimiento;

    private final int velocidad;
    private final int destreza;
    private final int fuerza;
    private int armadura;
    private final int edad;

    private int nivel = 1;

    private int poderDisparo;

    private int poderDefensa;

    private int salud = 100;

    private int cartaNro;

    public Carta(String raza, String nombre, String apodo, int anioNacimiento, int velocidad, int destreza, int fuerza, int armadura, int edad) {
        this.raza = raza;
        this.nombre = nombre;
        this.apodo = apodo;
        this.velocidad = velocidad;
        this.destreza = destreza;
        this.fuerza = fuerza;
        this.armadura = armadura;
        this.edad = edad;
        this.anioNacimiento = 800 - edad;
        this.poderDisparo = destreza * fuerza * nivel;
        this.poderDefensa = armadura * velocidad;
    }

    public Carta(String raza, String nombre, int velocidad, int destreza, int fuerza, int armadura, int edad) {
        this.raza = raza;
        this.nombre = nombre;
        this.velocidad = velocidad;
        this.destreza = destreza;
        this.fuerza = fuerza;
        this.armadura = armadura;
        this.edad = edad;
        this.anioNacimiento = 800 - edad;
        this.poderDisparo = destreza * fuerza * nivel;
        this.poderDefensa = armadura * velocidad;
    }

    public String getRaza() {
        return raza;
    }

    public String getNombre() {
        return nombre;
    }

    public int getNivel() {
        return nivel;
    }

    public int getArmadura() {
        return armadura;
    }

    public int getSalud() {
        return salud;
    }

    public int getCartaNro() {
        return cartaNro;
    }

    public int getPoderDisparo() {
        return poderDisparo;
    }

    public int getPoderDefensa() {
        return poderDefensa;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
        this.poderDisparo = destreza * fuerza * nivel;
    }

    public void setArmadura(int armadura) {
        this.armadura = armadura;
        this.poderDefensa = armadura * velocidad;
    }

    public void setSalud(int salud) {
        this.salud = salud;
    }

    public void setCartaNro(int cartaNro) {
        this.cartaNro = cartaNro;
    }

    public void mostrarCarta() {
        System.out.println("nombre: " + this.nombre);
        System.out.println("raza: " + this.raza);
        System.out.println("apodo: " + this.apodo);
        System.out.println("edad: " + this.edad);
        System.out.println("Año de nacimiento: " + this.anioNacimiento);
        System.out.println("nivel: " + this.nivel);
        System.out.println("velocidad: " + this.velocidad);
        System.out.println("destreza: " + this.destreza);
        System.out.println("fuerza: " + this.fuerza);
        System.out.println("armadura: " + this.armadura);
        System.out.println(" ");
        System.out.print("------------------------------");
    }

    public String mostrarCartaResumen() {
        String texto = "nombre: " + this.nombre + "\n" +
                "raza: " + this.raza + "\n" +
                "nivel: " + this.nivel + "\n--------------------------\n";

        return texto;
    }
}