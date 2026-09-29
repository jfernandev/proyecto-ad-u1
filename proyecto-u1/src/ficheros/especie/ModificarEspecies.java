package ficheros.especie;
import modelo.Especie;
import java.io.*;

public class ModificarEspecies {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        int idModificar = 2;

        File fichero = new File(".//datos//Especies.dat");
        File ficheroAux = new File(".//datos//EspeciesAux.dat");
        FileInputStream filein = new FileInputStream(fichero);
        ObjectInputStream dataIS = new ObjectInputStream(filein);
        FileOutputStream fileout = new FileOutputStream(ficheroAux);
        ObjectOutputStream dataOS = new ObjectOutputStream(fileout);

        try {
            while (true) {
                Especie especie = (Especie) dataIS.readObject();
                if (especie.getId() == idModificar) {
                    especie.setNombreComun("Boletus");
                    especie.setNombreCientifico("Boletus edulis");
                    especie.setComestibilidad("Comestible");
                    especie.setDescripcion("Especie modificada desde el programa.");
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

        System.out.println("Especie modificada correctamente.");
    }
}