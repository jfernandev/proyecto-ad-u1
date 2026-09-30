package ficheros.avistamiento;
import ficheros.especie.BuscarEspecie;
import modelo.Avistamiento;
import java.io.*;

public class LeerAvistamientos {
    public static void listarAvistamientos() throws IOException, ClassNotFoundException {
        File fichero = new File(".//datos//Avistamientos.dat");

        if (!fichero.exists()) {
            System.out.println("No existe el fichero de avistamientos.");
            return;
        }

        FileInputStream filein = new FileInputStream(fichero);
        ObjectInputStream dataIS = new ObjectInputStream(filein);

        try {
            while (true) {
                Avistamiento avistamiento = (Avistamiento) dataIS.readObject();
                System.out.println(avistamiento.toString());
            }
        } catch (EOFException e) {
            System.out.println("Fin del fichero.");
        }
        dataIS.close();
    }
}