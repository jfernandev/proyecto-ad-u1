package ficheros.especie;
import modelo.Especie;
import java.io.*;
import java.util.Scanner;

public class CrearEspecie {
    public static void anadir(Scanner sc) throws IOException {
        System.out.print("ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Nombre común: ");
        String nombreComun = sc.nextLine();

        System.out.print("Nombre científico: ");
        String nombreCientifico = sc.nextLine();

        System.out.print("Comestibilidad: ");
        String comestibilidad = sc.nextLine();

        System.out.print("Descripción: ");
        String descripcion = sc.nextLine();

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