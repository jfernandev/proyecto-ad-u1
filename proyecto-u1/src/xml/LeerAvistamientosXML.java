package xml;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import modelo.Avistamiento;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LeerAvistamientosXML {

    public static List<Avistamiento> leerLista() throws IOException {

        FileInputStream fis = new FileInputStream(".//datos//Avistamientos.xml");

        XStream xstream = new XStream(new DomDriver());

        xstream.alias("avistamiento", Avistamiento.class);
        xstream.alias("avistamientos", ArrayList.class);
        xstream.allowTypes(new Class[]{Avistamiento.class, ArrayList.class});

        ArrayList<Avistamiento> lista;
        try {
            lista = (ArrayList<Avistamiento>) xstream.fromXML(fis);
        } finally {
            fis.close();
        }
        return lista;
    }

    public static void leer() throws IOException {
        for (Avistamiento avistamiento : leerLista()) {
            System.out.println(avistamiento);
            System.out.println("--------------------");
        }
    }
}