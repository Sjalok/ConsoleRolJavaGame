package clases;

public class Datos {

    // clase de ayuda que tiene datos de nombres
    static String[] nombreHumanos = {"Cedric", "Eadric", "Aric", "Bryn", "Gideon", "Darius", "Rhys", "Einar", "Thorne", "Alden", "Kael", "Galen", "Eldric", "Torin", "Finn", "Leofric", "Rowan", "Magnus", "Wulfric", "Fang"};

    static String[] nombreOrcos = {"Grommash", "Thrak", "Grimgor", "Durotan", "Azog", "Karg", "Gruk", "Morgoth", "Drakar", "Skullcrusher", "Ragnor", "Groth", "Gorefang", "Hrothgar", "Uzgash", "Grimlok", "Zogar", "Gul'dan", "Mogor"};

    static String[] nombreElfos = {"Legolas", "Thranduil", "Galadriel", "Elrond", "Arwen", "Feanor", "Earendil", "Celeborn", "Luthien", "Gil-galad", "Arator", "Finrod", "Glorfindel", "Eol", "Maedhros", "Thalion", "Tauriel", "Elladan", "Yertse"};

    static String[] nombreEnanos = {"Thorin", "Gimli", "Durin", "Dwalin", "Balin", "Fili", "Kili", "Bofur", "Bombur", "Gloin", "Dori", "Nori", "Ori", "Dain", "Thrain", "Groin", "Thror", "Bifur", "Nain", "Oin"};

    static String[] nombreEnemigo = {"Lord Malvo", "Condesa Ermengarde", "Baron Sombrio", "Lady Avelina", "General Balzor", "Malincrot"};

    public static String[] getNombreHumanos() {
        return nombreHumanos;
    }

    public static String[] getNombreOrcos() {
        return nombreOrcos;
    }

    public static String[] getNombreElfos() {
        return nombreElfos;
    }

    public static String[] getNombreEnanos() {
        return nombreEnanos;
    }

    public static String[] getNombreEnemigo() {
        return nombreEnemigo;
    }
}