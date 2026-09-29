package ficheros.especie;
import modelo.Especie;
import java.io.*;

public class LeerEspecies {
    public static void listarEspecies() throws IOException, ClassNotFoundException {
        File fichero = new File(".//datos//Especies.dat");
        FileInputStream filein = new FileInputStream(fichero);
        ObjectInputStream dataIS = new ObjectInputStream(filein);

        try {
            while (true) {
                Especie especie = (Especie) dataIS.readObject();
                System.out.println("\nNombre común: " + especie.getNombreComun());
                System.out.println("Nombre científico: " + especie.getNombreCientifico());
                System.out.println("Descripción: " + especie.getDescripcion());
                System.out.println("Comestibilidad: " + especie.getComestibilidad());
                System.out.println("\n=======================================================");
            }
        } catch (EOFException e) {
            System.out.println("\nFin del fichero.");
        }
        dataIS.close();
    }
}