package ficheros.especie;

import modelo.Especie;

import java.io.*;

public class ExisteEspecie {

    public static boolean existe(int id) throws IOException, ClassNotFoundException {

        File fichero = new File(".//datos//Especies.dat");

        if (!fichero.exists()) {
            return false;
        }

        FileInputStream filein = new FileInputStream(fichero);
        ObjectInputStream dataIS = new ObjectInputStream(filein);

        try {
            while (true) {

                Especie especie = (Especie) dataIS.readObject();

                if (especie.getId() == id) {
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