package ficheros.habitat;

import modelo.Habitat;
import java.io.*;
import java.util.Scanner;

public class EliminarHabitat {

    public static void eliminar(Scanner sc) throws IOException, ClassNotFoundException {

        System.out.print("Introduce el ID del habitat que quieres eliminar: ");
        int idEliminar = sc.nextInt();

        if (ExisteHabitatEnAvistamientos.existe(idEliminar)) {
            System.out.println("No se puede eliminar el habitat porque tiene avistamientos asociados.");
            return;
        }

        File fichero = new File(".//datos//Habitats.dat");
        File ficheroAux = new File(".//datos//HabitatsAux.dat");

        if (!fichero.exists()) {
            System.out.println("No existe el fichero de habitats.");
            return;
        }

        FileInputStream filein = new FileInputStream(fichero);
        ObjectInputStream dataIS = new ObjectInputStream(filein);

        FileOutputStream fileout = new FileOutputStream(ficheroAux);
        ObjectOutputStream dataOS = new ObjectOutputStream(fileout);

        boolean encontrado = false;

        try {
            while (true) {

                Habitat habitat = (Habitat) dataIS.readObject();

                if (habitat.getId() == idEliminar) {
                    encontrado = true;
                } else {
                    dataOS.writeObject(habitat);
                }
            }

        } catch (EOFException e) {
        }

        dataIS.close();
        dataOS.close();

        fichero.delete();
        ficheroAux.renameTo(fichero);

        if (encontrado) {
            System.out.println("Habitat eliminado correctamente.");
        } else {
            System.out.println("No se ha encontrado ningun habitat con ese ID.");
        }
    }
}