package ficheros.especie;
import modelo.Especie;
import java.io.*;
import java.util.Scanner;

public class BuscarEspecie {
    public static void buscar(Scanner sc) throws IOException, ClassNotFoundException {

        System.out.print("Introduce el ID de la especie: ");
        int idBuscar = sc.nextInt();

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