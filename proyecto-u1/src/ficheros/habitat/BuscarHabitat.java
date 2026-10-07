package ficheros.habitat;

import ficheros.Utilidades;
import modelo.Habitat;

import java.io.*;
import java.util.Scanner;

public class BuscarHabitat {

    public static void buscar(Scanner sc) throws IOException, ClassNotFoundException {

        int idBuscar = Utilidades.pedirEntero(sc, "Introduce el ID del habitat: ");

        File fichero = new File(".//datos//Habitats.dat");

        if (!fichero.exists()) {
            System.out.println("No existe el fichero de habitats.");
            return;
        }

        FileInputStream filein = new FileInputStream(fichero);
        ObjectInputStream dataIS = new ObjectInputStream(filein);

        boolean encontrado = false;

        try {
            while (true) {

                Habitat habitat = (Habitat) dataIS.readObject();

                if (habitat.getId() == idBuscar) {
                    System.out.println("\nHabitat encontrado:");
                    System.out.println(habitat);

                    encontrado = true;
                    break;
                }
            }

        } catch (EOFException e) {
        }

        dataIS.close();

        if (!encontrado) {
            System.out.println("No se ha encontrado ningun habitat con ese ID.");
        }
    }
}