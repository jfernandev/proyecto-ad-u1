package ficheros.especie;
import modelo.Especie;
import java.io.*;
import java.util.Scanner;

public class ModificarEspecie {
    public static void modificar(Scanner sc) throws IOException, ClassNotFoundException {

        System.out.print("Introduce el ID de la especie que quieres modificar: ");
        int idModificar = sc.nextInt();
        sc.nextLine();

        File fichero = new File(".//datos//Especies.dat");
        File ficheroAux = new File(".//datos//EspeciesAux.dat");

        if (!fichero.exists()) {
            System.out.println("No existe el fichero de especies.");
            return;
        }

        System.out.print("Nuevo nombre común: ");
        String nombreComun = sc.nextLine();

        System.out.print("Nuevo nombre científico: ");
        String nombreCientifico = sc.nextLine();

        System.out.print("Nueva comestibilidad: ");
        String comestibilidad = sc.nextLine();

        System.out.print("Nueva descripción: ");
        String descripcion = sc.nextLine();

        FileInputStream filein = new FileInputStream(fichero);
        ObjectInputStream dataIS = new ObjectInputStream(filein);

        FileOutputStream fileout = new FileOutputStream(ficheroAux);
        ObjectOutputStream dataOS = new ObjectOutputStream(fileout);

        boolean encontrado = false;

        try {
            while (true) {

                Especie especie = (Especie) dataIS.readObject();

                if (especie.getId() == idModificar) {

                    especie.setNombreComun(nombreComun);
                    especie.setNombreCientifico(nombreCientifico);
                    especie.setComestibilidad(comestibilidad);
                    especie.setDescripcion(descripcion);

                    encontrado = true;
                }

                dataOS.writeObject(especie);
            }

        } catch (EOFException e) {
            System.out.println("Fin del fichero.");
        }

        dataIS.close();
        dataOS.close();

        fichero.delete();
        ficheroAux.renameTo(fichero);

        if (encontrado) {
            System.out.println("Especie modificada correctamente.");
        } else {
            System.out.println("No se ha encontrado ninguna especie con ese ID.");
        }
    }
}