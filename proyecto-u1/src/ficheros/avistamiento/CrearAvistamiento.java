package ficheros.avistamiento;

import ficheros.Utilidades;
import ficheros.RepositorioObjetos;
import ficheros.especie.ExisteEspecie;
import ficheros.habitat.ExisteHabitat;
import modelo.Avistamiento;

import java.io.*;
import java.util.Scanner;

public class CrearAvistamiento {

    public static void crear(Avistamiento avistamiento) throws IOException, ClassNotFoundException {
        if (ExisteAvistamiento.existe(avistamiento.getId())) {
            throw new IllegalArgumentException("Ya existe un avistamiento con ese ID.");
        }
        if (!ExisteEspecie.existe(avistamiento.getIdEspecie())) {
            throw new IllegalArgumentException("No existe una especie con ese ID.");
        }
        if (!ExisteHabitat.existe(avistamiento.getIdHabitat())) {
            throw new IllegalArgumentException("No existe un hábitat con ese ID.");
        }
        java.util.List<Avistamiento> avistamientos = LeerAvistamientos.obtenerAvistamientos();
        avistamientos.add(avistamiento);
        RepositorioObjetos.escribir(new File(".//datos//Avistamientos.dat"), avistamientos);
    }

    public static void crear(Scanner sc) throws IOException {

        int id = Utilidades.pedirEntero(sc, "ID: ");
        int idEspecie = Utilidades.pedirEntero(sc, "ID de la especie: ");
        int idHabitat = Utilidades.pedirEntero(sc, "ID del habitat: ");

        try {
            if (ExisteAvistamiento.existe(id)) {
                System.out.println("Ya existe un avistamiento con ese ID.");
                return;
            }

            if (!ExisteEspecie.existe(idEspecie)) {
                System.out.println("No existe una especie con ese ID.");
                return;
            }

            if (!ExisteHabitat.existe(idHabitat)) {
                System.out.println("No existe un habitat con ese ID.");
                return;
            }

        } catch (ClassNotFoundException e) {
            System.out.println("Error al comprobar los datos.");
            return;
        }

        String fecha = Utilidades.pedirTextoNoVacio(sc, "Fecha: ");
        String localizacion = Utilidades.pedirTextoNoVacio(sc, "Localización: ");
        String observaciones = Utilidades.pedirTextoNoVacio(sc, "Observaciones: ");

        Avistamiento avistamiento = new Avistamiento(
                id,
                idEspecie,
                idHabitat,
                fecha,
                localizacion,
                observaciones
        );

        File fichero = new File(".//datos//Avistamientos.dat");

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

        dataOS.writeObject(avistamiento);
        dataOS.close();

        System.out.println("Avistamiento añadido correctamente.");
    }
}