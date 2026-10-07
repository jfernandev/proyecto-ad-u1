package ficheros.especie;

import ficheros.Utilidades;
import modelo.Especie;

import java.io.*;
import java.util.Scanner;

public class ModificarEspecies {

    public static void modificar(Scanner sc) throws IOException, ClassNotFoundException {

        int idModificar = Utilidades.pedirEntero(sc, "Introduce el ID de la especie a modificar: ");

        File fichero = new File(".//datos//Especies.dat");
        File ficheroAux = new File(".//datos//EspeciesAux.dat");

        if (!fichero.exists()) {
            System.out.println("No existe el fichero de especies.");
            return;
        }

        String nombreComun = Utilidades.pedirTextoNoVacio(sc, "Nuevo nombre común: ");
        String nombreCientifico = Utilidades.pedirTextoNoVacio(sc, "Nuevo nombre científico: ");
        String comestibilidad = Utilidades.pedirTextoNoVacio(sc, "Nueva comestibilidad: ");
        String descripcion = Utilidades.pedirTextoNoVacio(sc, "Nueva descripción: ");

        FileInputStream filein = new FileInputStream(fichero);
        ObjectInputStream dataIS = new ObjectInputStream(filein);

        FileOutputStream fileout = new FileOutputStream(ficheroAux);
        ObjectOutputStream dataOS = new ObjectOutputStream(fileout);

        boolean encontrado = false;

        try {
            while (true) {

                Especie especie = (Especie) dataIS.readObject();

                if (especie.getId() == idModificar) {

                    especie.setNombreComun(nombreComun);
                    especie.setNombreCientifico(nombreCientifico);
                    especie.setComestibilidad(comestibilidad);
                    especie.setDescripcion(descripcion);

                    encontrado = true;
                }

                dataOS.writeObject(especie);
            }

        } catch (EOFException e) {
            System.out.println("Fin del fichero.");
        }

        dataIS.close();
        dataOS.close();

        fichero.delete();
        ficheroAux.renameTo(fichero);

        if (encontrado) {
            System.out.println("Especie modificada correctamente.");
        } else {
            System.out.println("No se ha encontrado ninguna especie con ese ID.");
        }
    }
}