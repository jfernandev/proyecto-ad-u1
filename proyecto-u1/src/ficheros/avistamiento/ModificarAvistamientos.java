package ficheros.avistamiento;

import ficheros.Utilidades;
import ficheros.especie.ExisteEspecie;
import ficheros.habitat.ExisteHabitat;
import modelo.Avistamiento;

import java.io.*;
import java.util.Scanner;

public class ModificarAvistamientos {

    public static void modificar(Scanner sc) throws IOException, ClassNotFoundException {

        int idModificar = Utilidades.pedirEntero(sc, "Introduce el ID del avistamiento que quieres modificar: ");
        int idEspecie = Utilidades.pedirEntero(sc, "Nuevo ID de la especie: ");
        int idHabitat = Utilidades.pedirEntero(sc, "Nuevo ID del habitat: ");

        if (!ExisteEspecie.existe(idEspecie)) {
            System.out.println("No existe una especie con ese ID.");
            return;
        }

        if (!ExisteHabitat.existe(idHabitat)) {
            System.out.println("No existe un habitat con ese ID.");
            return;
        }

        String fecha = Utilidades.pedirTextoNoVacio(sc, "Nueva fecha: ");
        String localizacion = Utilidades.pedirTextoNoVacio(sc, "Nueva localización: ");
        String observaciones = Utilidades.pedirTextoNoVacio(sc, "Nuevas observaciones: ");

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

                if (avistamiento.getId() == idModificar) {

                    avistamiento.setIdEspecie(idEspecie);
                    avistamiento.setIdHabitat(idHabitat);
                    avistamiento.setFecha(fecha);
                    avistamiento.setLocalizacion(localizacion);
                    avistamiento.setObservaciones(observaciones);

                    encontrado = true;
                }

                dataOS.writeObject(avistamiento);
            }

        } catch (EOFException e) {
            System.out.println("Fin del fichero.");
        }

        dataIS.close();
        dataOS.close();

        fichero.delete();
        ficheroAux.renameTo(fichero);

        if (encontrado) {
            System.out.println("Avistamiento modificado correctamente.");
        } else {
            System.out.println("No se ha encontrado ningun avistamiento con ese ID.");
        }
    }
}