package ficheros.especie;

import ficheros.Utilidades;
import modelo.Especie;

import java.io.*;
import java.util.Scanner;

public class BuscarEspecie {

    public static Especie buscarPorId(int id) throws IOException, ClassNotFoundException {
        for (Especie especie : LeerEspecies.obtenerEspecies()) {
            if (especie.getId() == id) {
                return especie;
            }
        }
        return null;
    }

    public static void buscar(Scanner sc) throws IOException, ClassNotFoundException {

        int idBuscar = Utilidades.pedirEntero(sc, "Introduce el ID de la especie: ");

        File fichero = new File(".//datos//Especies.dat");

        if (!fichero.exists()) {
            System.out.println("No existe el fichero de especies.");
            return;
        }

        FileInputStream filein = new FileInputStream(fichero);
        ObjectInputStream dataIS = new ObjectInputStream(filein);

        boolean encontrado = false;

        try {
            while (true) {

                Especie especie = (Especie) dataIS.readObject();

                if (especie.getId() == idBuscar) {
                    System.out.println("\nEspecie encontrada:");
                    System.out.println(especie);
                    encontrado = true;
                    break;
                }
            }

        } catch (EOFException e) {
        }

        dataIS.close();

        if (!encontrado) {
            System.out.println("No se ha encontrado ninguna especie con ese ID.");
        }
    }
}