package ficheros.habitat;
import ficheros.RepositorioObjetos;
import modelo.Habitat;
import java.io.*;
import java.util.List;

public class LeerHabitats {
    public static List<Habitat> obtenerHabitats() throws IOException, ClassNotFoundException {
        return RepositorioObjetos.leer(new File(".//datos//Habitats.dat"), Habitat.class);
    }

    public static void listarHabitats() throws IOException, ClassNotFoundException {
        if (!new File(".//datos//Habitats.dat").exists()) {
            throw new FileNotFoundException("No existe el fichero de habitats.");
        }
        for (Habitat habitat : obtenerHabitats()) {
            System.out.println(habitat);
        }
        System.out.println("Fin del fichero.");
    }
}