package ficheros.avistamiento;
import ficheros.RepositorioObjetos;
import modelo.Avistamiento;
import java.io.*;
import java.util.List;

public class LeerAvistamientos {
    public static List<Avistamiento> obtenerAvistamientos() throws IOException, ClassNotFoundException {
        return RepositorioObjetos.leer(new File(".//datos//Avistamientos.dat"), Avistamiento.class);
    }

    public static void listarAvistamientos() throws IOException, ClassNotFoundException {
        if (!new File(".//datos//Avistamientos.dat").exists()) {
            System.out.println("No existe el fichero de avistamientos.");
            return;
        }
        for (Avistamiento avistamiento : obtenerAvistamientos()) {
            System.out.println(avistamiento);
        }
        System.out.println("Fin del fichero.");
    }
}