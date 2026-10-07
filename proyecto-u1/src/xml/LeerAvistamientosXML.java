package xml;

import com.thoughtworks.xstream.XStream;
import modelo.Avistamiento;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class LeerAvistamientosXML {

    public static void leer() throws IOException {

        FileInputStream fis = new FileInputStream(".//datos//Avistamientos.xml");

        XStream xstream = new XStream();

        xstream.alias("avistamiento", Avistamiento.class);
        xstream.alias("avistamientos", ArrayList.class);

        ArrayList<Avistamiento> lista = (ArrayList<Avistamiento>) xstream.fromXML(fis);

        fis.close();

        for (Avistamiento avistamiento : lista) {
            System.out.println(avistamiento);
            System.out.println("--------------------");
        }
    }
}