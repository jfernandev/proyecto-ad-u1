package ficheros.habitat;

import modelo.Avistamiento;
import java.io.*;

public class ExisteHabitatEnAvistamientos {

    public static boolean existe(int idHabitat) throws IOException, ClassNotFoundException {

        File fichero = new File(".//datos//Avistamientos.dat");

        if (!fichero.exists()) {
            return false;
        }

        FileInputStream filein = new FileInputStream(fichero);
        ObjectInputStream dataIS = new ObjectInputStream(filein);

        try {
            while (true) {

                Avistamiento avistamiento = (Avistamiento) dataIS.readObject();

                if (avistamiento.getIdHabitat() == idHabitat) {
                    dataIS.close();
                    return true;
                }
            }

        } catch (EOFException e) {
        }

        dataIS.close();
        return false;
    }
}