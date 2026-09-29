package ficheros.habitat;

import modelo.Habitat;
import java.io.*;

public class ExisteHabitat {

    public static boolean existe(int id) throws IOException, ClassNotFoundException {

        File fichero = new File(".//datos//Habitats.dat");

        if (!fichero.exists()) {
            return false;
        }

        FileInputStream filein = new FileInputStream(fichero);
        ObjectInputStream dataIS = new ObjectInputStream(filein);

        try {
            while (true) {

                Habitat habitat = (Habitat) dataIS.readObject();

                if (habitat.getId() == id) {
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