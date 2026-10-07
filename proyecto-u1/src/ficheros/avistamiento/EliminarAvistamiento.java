package ficheros.avistamiento;

import ficheros.Utilidades;
import modelo.Avistamiento;

import java.io.*;
import java.util.Scanner;

public class EliminarAvistamiento {

    public static void eliminar(Scanner sc) throws IOException, ClassNotFoundException {

        int idEliminar = Utilidades.pedirEntero(sc, "Introduce el ID del avistamiento que quieres eliminar: ");

        File fichero = new File(".//datos//Avistamientos.dat");
        File ficheroAux = new File(".//datos//AvistamientosAux.dat");

        if (!fichero.exists()) {
            System.out.println("No existe el fichero de avistamientos.");
            return;
        }

        FileInputStream filein = new FileInputStream(fichero);
        ObjectInputStream dataIS = new ObjectInputStream(filein);

        FileOutputStream fileout = new FileOutputStream(ficheroAux);
        ObjectOutputStream dataOS = new ObjectOutputStream(fileout);

        boolean encontrado = false;

        try {
            while (true) {

                Avistamiento avistamiento = (Avistamiento) dataIS.readObject();

                if (avistamiento.getId() == idEliminar) {
                    encontrado = true;
                } else {
                    dataOS.writeObject(avistamiento);
                }
            }

        } catch (EOFException e) {
        }

        dataIS.close();
        dataOS.close();

        fichero.delete();
        ficheroAux.renameTo(fichero);

        if (encontrado) {
            System.out.println("Avistamiento eliminado correctamente.");
        } else {
            System.out.println("No se ha encontrado ningun avistamiento con ese ID.");
        }
    }
}