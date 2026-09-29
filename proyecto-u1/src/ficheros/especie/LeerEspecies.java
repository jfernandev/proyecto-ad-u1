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
                System.out.println("Nombre común: " + especie.getNombreComun());
                System.out.println("\nNombre científico: " + especie.getNombreCientifico());
                System.out.println("\nDescripción: " + especie.getDescripcion());
                System.out.println("\nComestibilidad: " + especie.getComestibilidad());
                System.out.println("=======================================================");
            }
        } catch (EOFException e) {
            System.out.println("Fin del fichero.");
        }
        dataIS.close();
    }
}