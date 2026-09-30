package ficheros.avistamiento;
import modelo.Avistamiento;
import java.io.*;
import java.util.Scanner;

public class BuscarAvistamiento {

    public static void buscar(Scanner sc) throws IOException, ClassNotFoundException {

        System.out.print("Introduce el ID del avistamiento: ");
        int idBuscar = sc.nextInt();

        File fichero = new File(".//datos//Avistamientos.dat");

        if (!fichero.exists()) {
            System.out.println("No existe el fichero de avistamientos.");
            return;
        }

        FileInputStream filein = new FileInputStream(fichero);
        ObjectInputStream dataIS = new ObjectInputStream(filein);

        boolean encontrado = false;

        try {
            while (true) {
                Avistamiento avistamiento = (Avistamiento) dataIS.readObject();
                if (avistamiento.getId() == idBuscar) {
                    System.out.println("\nAvistamiento encontrado:");
                    System.out.println(avistamiento);
                    encontrado = true;
                    break;
                }
            }
        } catch (EOFException e) {
        }
        dataIS.close();

        if (!encontrado) {
            System.out.println("No se ha encontrado ningun avistamiento con ese ID.");
        }
    }
}