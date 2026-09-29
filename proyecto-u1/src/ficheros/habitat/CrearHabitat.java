package ficheros.habitat;
import modelo.Habitat;
import java.io.*;
import java.util.Scanner;

public class CrearHabitat {

    public static void crear(Scanner sc) throws IOException {

        System.out.print("ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        try {
            if (ExisteHabitat.existe(id)) {
                System.out.println("Ya existe un habitat con ese ID.");
                return;
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Error al comprobar el habitat.");
            return;
        }

        System.out.print("Nombre: ");
        String nombre = sc.nextLine();

        System.out.print("Descripción: ");
        String descripcion = sc.nextLine();

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