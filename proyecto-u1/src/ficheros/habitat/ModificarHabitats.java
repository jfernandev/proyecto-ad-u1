package ficheros.habitat;
import modelo.Habitat;
import java.io.*;

public class ModificarHabitats {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        int idModificar = 1;

        File fichero = new File(".//datos//Habitats.dat");
        File ficheroAux = new File(".//datos//HabitatsAux.dat");
        FileInputStream filein = new FileInputStream(fichero);
        ObjectInputStream dataIS = new ObjectInputStream(filein);
        FileOutputStream fileout = new FileOutputStream(ficheroAux);
        ObjectOutputStream dataOS = new ObjectOutputStream(fileout);

        try {
            while (true) {
                Habitat habitat = (Habitat) dataIS.readObject();
                if (habitat.getId() == idModificar) {
                    habitat.setNombre("Pinar modificado");
                    habitat.setDescripcion("Descripción modificada desde el programa.");
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

        System.out.println("Hábitat modificado correctamente.");
    }
}