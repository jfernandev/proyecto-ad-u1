package ficheros.especie;

import ficheros.RepositorioObjetos;
import modelo.Especie;
import java.io.*;
import java.util.List;

public class LeerEspecies {
    public static List<Especie> obtenerEspecies() throws IOException, ClassNotFoundException {
        return RepositorioObjetos.leer(new File(".//datos//Especies.dat"), Especie.class);
    }

    public static void listarEspecies() throws IOException, ClassNotFoundException {
        if (!new File(".//datos//Especies.dat").exists()) {
            throw new FileNotFoundException("No existe el fichero de especies.");
        }
        for (Especie especie : obtenerEspecies()) {
            System.out.println(especie);
        }
        System.out.println("\nFin del fichero.");
    }
}