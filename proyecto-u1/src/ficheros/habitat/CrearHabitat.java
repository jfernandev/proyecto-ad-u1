package ficheros.habitat;

import ficheros.Utilidades;
import modelo.Habitat;

import java.io.*;
import java.util.Scanner;

public class CrearHabitat {

    public static void crear(Scanner sc) throws IOException {

        int id = Utilidades.pedirEntero(sc, "ID: ");

        try {
            if (ExisteHabitat.existe(id)) {
                System.out.println("Ya existe un habitat con ese ID.");
                return;
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Error al comprobar el habitat.");
            return;
        }

        String nombre = Utilidades.pedirTextoNoVacio(sc, "Nombre: ");
        String descripcion = Utilidades.pedirTextoNoVacio(sc, "Descripción: ");

        Habitat habitat = new Habitat(id, nombre, descripcion);

        File fichero = new File(".//datos//Habitats.dat");

        boolean existe = fichero.exists() && fichero.length() > 0;

        FileOutputStream fileout = new FileOutputStream(fichero, true);

        ObjectOutputStream dataOS;

        if (existe) {
            dataOS = new ObjectOutputStream(fileout) {
                @Override
                protected void writeStreamHeader() throws IOException {
                    reset();
                }
            };
        } else {
            dataOS = new ObjectOutputStream(fileout);
        }

        dataOS.writeObject(habitat);
        dataOS.close();

        System.out.println("Habitat añadido correctamente.");
    }
}