package ficheros.habitat;
import modelo.Habitat;
import java.io.*;
import java.util.Scanner;

public class ModificarHabitats {
    public static void modificar(Scanner sc) throws IOException, ClassNotFoundException {

        System.out.print("Introduce el ID del habitat que quieres modificar: ");
        int idModificar = sc.nextInt();
        sc.nextLine();

        File fichero = new File(".//datos//Habitats.dat");
        File ficheroAux = new File(".//datos//HabitatsAux.dat");

        if (!fichero.exists()) {
            System.out.println("No existe el fichero de habitats.");
            return;
        }

        System.out.print("Nuevo nombre: ");
        String nombre = sc.nextLine();

        System.out.print("Nueva descripción: ");
        String descripcion = sc.nextLine();

        FileInputStream filein = new FileInputStream(fichero);
        ObjectInputStream dataIS = new ObjectInputStream(filein);

        FileOutputStream fileout = new FileOutputStream(ficheroAux);
        ObjectOutputStream dataOS = new ObjectOutputStream(fileout);

        boolean encontrado = false;

        try {
            while (true) {
                Habitat habitat = (Habitat) dataIS.readObject();
                if (habitat.getId() == idModificar) {
                    habitat.setNombre(nombre);
                    habitat.setDescripcion(descripcion);
                    encontrado = true;
                }
                dataOS.writeObject(habitat);
            }

        } catch (EOFException e) {
            System.out.println("Fin del fichero.");
        }

        dataIS.close();
        dataOS.close();

        fichero.delete();
        ficheroAux.renameTo(fichero);

        if (encontrado) {
            System.out.println("Habitat modificado correctamente.");
        } else {
            System.out.println("No se ha encontrado ningun habitat con ese ID.");
        }
    }
}