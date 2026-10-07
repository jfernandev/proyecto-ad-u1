package xml;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import modelo.Habitat;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LeerHabitatsXML {

    public static List<Habitat> leerLista() throws IOException {

        FileInputStream fis = new FileInputStream(".//datos//Habitats.xml");

        XStream xstream = new XStream(new DomDriver());

        xstream.alias("habitat", Habitat.class);
        xstream.alias("habitats", ArrayList.class);
        xstream.allowTypes(new Class[]{Habitat.class, ArrayList.class});

        ArrayList<Habitat> lista;
        try {
            lista = (ArrayList<Habitat>) xstream.fromXML(fis);
        } finally {
            fis.close();
        }
        return lista;
    }

    public static void leer() throws IOException {
        for (Habitat habitat : leerLista()) {
            System.out.println(habitat);
            System.out.println("--------------------");
        }
    }
}