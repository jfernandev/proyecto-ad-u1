package ficheros.especie;

import ficheros.Utilidades;
import modelo.Especie;

import java.io.*;
import java.util.Scanner;

public class EliminarEspecie {

    public static void eliminar(Scanner sc) throws IOException, ClassNotFoundException {

        int idEliminar = Utilidades.pedirEntero(sc, "Introduce el ID de la especie que quieres eliminar: ");

        if (ExisteEspecieEnAvistamientos.existe(idEliminar)) {
            System.out.println("No se puede eliminar la especie porque tiene avistamientos asociados.");
            return;
        }

        File fichero = new File(".//datos//Especies.dat");
        File ficheroAux = new File(".//datos//EspeciesAux.dat");

        if (!fichero.exists()) {
            System.out.println("No existe el fichero de especies.");
            return;
        }

        FileInputStream filein = new FileInputStream(fichero);
        ObjectInputStream dataIS = new ObjectInputStream(filein);

        FileOutputStream fileout = new FileOutputStream(ficheroAux);
        ObjectOutputStream dataOS = new ObjectOutputStream(fileout);

        boolean encontrado = false;

        try {
            while (true) {

                Especie especie = (Especie) dataIS.readObject();

                if (especie.getId() == idEliminar) {
                    encontrado = true;
                } else {
                    dataOS.writeObject(especie);
                }
            }

        } catch (EOFException e) {
        }

        dataIS.close();
        dataOS.close();

        fichero.delete();
        ficheroAux.renameTo(fichero);

        if (encontrado) {
            System.out.println("Especie eliminada correctamente.");
        } else {
            System.out.println("No se ha encontrado ninguna especie con ese ID.");
        }
    }
}