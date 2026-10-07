package ficheros.especie;

import ficheros.Utilidades;
import ficheros.RepositorioObjetos;
import modelo.Especie;

import java.io.*;
import java.util.Scanner;

public class CrearEspecie {

    public static void crear(Especie especie) throws IOException, ClassNotFoundException {
        if (ExisteEspecie.existe(especie.getId())) {
            throw new IllegalArgumentException("Ya existe una especie con ese ID.");
        }
        java.util.List<Especie> especies = LeerEspecies.obtenerEspecies();
        especies.add(especie);
        RepositorioObjetos.escribir(new File(".//datos//Especies.dat"), especies);
    }

    public static void crear(Scanner sc) throws IOException {

        int id = Utilidades.pedirEntero(sc, "ID: ");

        try {
            if (ExisteEspecie.existe(id)) {
                System.out.println("Ya existe una especie con ese ID.");
                return;
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Error al comprobar la especie.");
            return;
        }

        String nombreComun = Utilidades.pedirTextoNoVacio(sc, "Nombre común: ");
        String nombreCientifico = Utilidades.pedirTextoNoVacio(sc, "Nombre científico: ");
        String comestibilidad = Utilidades.pedirTextoNoVacio(sc, "Comestibilidad: ");
        String descripcion = Utilidades.pedirTextoNoVacio(sc, "Descripción: ");

        Especie especie = new Especie(
                id,
                nombreComun,
                nombreCientifico,
                comestibilidad,
                descripcion
        );

        File fichero = new File(".//datos//Especies.dat");

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

        dataOS.writeObject(especie);
        dataOS.close();

        System.out.println("Especie añadida correctamente.");
    }
}