package xml;

import com.thoughtworks.xstream.XStream;
import modelo.Especie;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class LeerEspeciesXML {

    public static void leer() throws IOException {

        FileInputStream fis = new FileInputStream(".//datos//Especies.xml");

        XStream xstream = new XStream();

        xstream.alias("especie", Especie.class);
        xstream.alias("especies", ArrayList.class);

        ArrayList<Especie> lista = (ArrayList<Especie>) xstream.fromXML(fis);

        fis.close();

        for (Especie especie : lista) {
            System.out.println(especie);
            System.out.println("--------------------");
        }
    }
}