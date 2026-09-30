package ficheros.avistamiento;
import modelo.Avistamiento;
import ficheros.especie.ExisteEspecie;
import ficheros.habitat.ExisteHabitat;
import java.io.*;
import java.util.Scanner;

public class ModificarAvistamientos {
    public static void modificar(Scanner sc) throws IOException, ClassNotFoundException {

        System.out.print("Introduce el ID del avistamiento que quieres modificar: ");
        int idModificar = sc.nextInt();

        System.out.print("Nuevo ID de la especie: ");
        int idEspecie = sc.nextInt();

        System.out.print("Nuevo ID del habitat: ");
        int idHabitat = sc.nextInt();
        sc.nextLine();

        if (!ExisteEspecie.existe(idEspecie)) {
            System.out.println("No existe una especie con ese ID.");
            return;
        }

        if (!ExisteHabitat.existe(idHabitat)) {
            System.out.println("No existe un habitat con ese ID.");
            return;
        }

        System.out.print("Nueva fecha: ");
        String fecha = sc.nextLine();

        System.out.print("Nueva localización: ");
        String localizacion = sc.nextLine();

        System.out.print("Nuevas observaciones: ");
        String observaciones = sc.nextLine();

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