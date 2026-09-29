package ficheros.especie;
import modelo.Especie;
import java.io.*;

public class LeerEspecies {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        File fichero = new File(".//datos//Especies.dat");
        FileInputStream filein = new FileInputStream(fichero);
        ObjectInputStream dataIS = new ObjectInputStream(filein);

        try {
            while (true) {
                Especie especie = (Especie) dataIS.readObject();
                System.out.println(especie);
                System.out.println("=======================================================");
            }
        } catch (EOFException e) {
            System.out.println("Fin del fichero.");
        }
        dataIS.close();
    }
}