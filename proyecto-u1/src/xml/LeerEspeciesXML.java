package xml;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import modelo.Especie;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LeerEspeciesXML {

    public static List<Especie> leerLista() throws IOException {

        FileInputStream fis = new FileInputStream(".//datos//Especies.xml");

        XStream xstream = new XStream(new DomDriver());

        xstream.alias("especie", Especie.class);
        xstream.alias("especies", ArrayList.class);
        xstream.allowTypes(new Class[]{Especie.class, ArrayList.class});

        ArrayList<Especie> lista;
        try {
            lista = (ArrayList<Especie>) xstream.fromXML(fis);
        } finally {
            fis.close();
        }
        return lista;
    }

    public static void leer() throws IOException {
        for (Especie especie : leerLista()) {
            System.out.println(especie);
            System.out.println("--------------------");
        }
    }
}