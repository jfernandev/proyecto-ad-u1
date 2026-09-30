package xml;

import com.thoughtworks.xstream.XStream;
import modelo.Avistamiento;

import java.io.*;
import java.util.ArrayList;

public class ExportarAvistamientosXML {

    public static void exportar() throws IOException, ClassNotFoundException {

        ArrayList<Avistamiento> lista = new ArrayList<>();

        File fichero = new File(".//datos//Avistamientos.dat");

        if (!fichero.exists()) {
            System.out.println("No existe el fichero de avistamientos.");
            return;
        }

        FileInputStream filein = new FileInputStream(fichero);
        ObjectInputStream dataIS = new ObjectInputStream(filein);

        try {
            while (true) {
                Avistamiento avistamiento = (Avistamiento) dataIS.readObject();
                lista.add(avistamiento);
            }
        } catch (EOFException e) {
        }

        dataIS.close();

        XStream xstream = new XStream();

        xstream.alias("avistamiento", Avistamiento.class);
        xstream.alias("avistamientos", ArrayList.class);

        xstream.allowTypes(new Class[]{Avistamiento.class});

        FileOutputStream fos = new FileOutputStream(".//datos//Avistamientos.xml");

        xstream.toXML(lista, fos);

        fos.close();

        System.out.println("XML de avistamientos generado correctamente.");
    }
}