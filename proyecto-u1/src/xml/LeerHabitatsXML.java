package xml;

import com.thoughtworks.xstream.XStream;
import modelo.Habitat;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class LeerHabitatsXML {

    public static void leer() throws IOException {

        FileInputStream fis = new FileInputStream(".//datos//Habitats.xml");

        XStream xstream = new XStream();

        xstream.alias("habitat", Habitat.class);
        xstream.alias("habitats", ArrayList.class);

        ArrayList<Habitat> lista = (ArrayList<Habitat>) xstream.fromXML(fis);

        fis.close();

        for (Habitat habitat : lista) {
            System.out.println(habitat);
            System.out.println("--------------------");
        }
    }
}