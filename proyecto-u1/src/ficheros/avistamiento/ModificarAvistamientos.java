package ficheros.avistamiento;
import modelo.Avistamiento;
import java.io.*;

public class ModificarAvistamientos {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        int idModificar = 1;

        File fichero = new File(".//datos//Avistamientos.dat");
        File ficheroAux = new File(".//datos//AvistamientosAux.dat");
        FileInputStream filein = new FileInputStream(fichero);
        ObjectInputStream dataIS = new ObjectInputStream(filein);
        FileOutputStream fileout = new FileOutputStream(ficheroAux);
        ObjectOutputStream dataOS = new ObjectOutputStream(fileout);

        try {
            while (true) {
                Avistamiento avistamiento = (Avistamiento) dataIS.readObject();
                if (avistamiento.getId() == idModificar) {
                    avistamiento.setIdEspecie(2);
                    avistamiento.setIdHabitat(2);
                    avistamiento.setFecha("25/09/2026");
                    avistamiento.setLocalizacion("Hayedo de Altube");
                    avistamiento.setObservaciones("Avistamiento modificado.");
                }
                dataOS.writeObject(avistamiento);
            }
        } catch (EOFException e) {
            System.out.println("Fin del fichero.");
        }

        dataIS.close();
        dataOS.close();

        fichero.delete();
        ficheroAux.renameTo(fichero);

        System.out.println("Avistamiento modificado correctamente.");
    }
}