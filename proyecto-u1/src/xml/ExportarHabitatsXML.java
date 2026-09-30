package xml;

import com.thoughtworks.xstream.XStream;
import modelo.Habitat;

import java.io.*;
import java.util.ArrayList;

public class ExportarHabitatsXML {

    public static void exportar() throws IOException, ClassNotFoundException {

        ArrayList<Habitat> lista = new ArrayList<>();

        File fichero = new File(".//datos//Habitats.dat");

        if (!fichero.exists()) {
            System.out.println("No existe el fichero de habitats.");
            return;
        }

        FileInputStream filein = new FileInputStream(fichero);
        ObjectInputStream dataIS = new ObjectInputStream(filein);

        try {
            while (true) {
                Habitat habitat = (Habitat) dataIS.readObject();
                lista.add(habitat);
            }
        } catch (EOFException e) {
        }

        dataIS.close();

        XStream xstream = new XStream();

        xstream.alias("habitat", Habitat.class);
        xstream.alias("habitats", ArrayList.class);

        xstream.allowTypes(new Class[]{Habitat.class});

        FileOutputStream fos = new FileOutputStream(".//datos//Habitats.xml");

        xstream.toXML(lista, fos);

        fos.close();

        System.out.println("XML de habitats generado correctamente.");
    }
}