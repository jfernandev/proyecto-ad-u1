package ficheros.habitat;
import modelo.Habitat;
import java.io.*;

public class LeerHabitats {
    public static void listarHabitats() throws IOException, ClassNotFoundException {
        File fichero = new File(".//datos//Habitats.dat");
        FileInputStream filein = new FileInputStream(fichero);
        ObjectInputStream dataIS = new ObjectInputStream(filein);

        try {
            while (true) {
                Habitat habitat = (Habitat) dataIS.readObject();
                System.out.println("\nNombre: " + habitat.getNombre());
                System.out.println("Descripción: " + habitat.getDescripcion());
                System.out.println("\n=======================================================");
            }
        } catch (EOFException e) {
            System.out.println("Fin del fichero.");
        }
        dataIS.close();
    }
}