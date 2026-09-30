package ficheros.avistamiento;
import modelo.Avistamiento;
import ficheros.especie.ExisteEspecie;
import ficheros.habitat.ExisteHabitat;
import java.io.*;
import java.util.Scanner;

public class CrearAvistamiento {

    public static void crear(Scanner sc) throws IOException {

        System.out.print("ID: ");
        int id = sc.nextInt();

        System.out.print("ID de la especie: ");
        int idEspecie = sc.nextInt();

        System.out.print("ID del habitat: ");
        int idHabitat = sc.nextInt();
        sc.nextLine();

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

        System.out.print("Fecha: ");
        String fecha = sc.nextLine();

        System.out.print("Localización: ");
        String localizacion = sc.nextLine();

        System.out.print("Observaciones: ");
        String observaciones = sc.nextLine();

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