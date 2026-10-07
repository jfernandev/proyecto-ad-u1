package xml;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import modelo.Especie;
import java.io.*;
import java.util.ArrayList;

public class ExportarEspeciesXML {

    public static void exportar() throws IOException, ClassNotFoundException {

        ArrayList<Especie> lista = new ArrayList<>();

        File fichero = new File(".//datos//Especies.dat");

        if (!fichero.exists()) {
            throw new FileNotFoundException("No existe el fichero de especies.");
        }

        FileInputStream filein = new FileInputStream(fichero);
        ObjectInputStream dataIS = new ObjectInputStream(filein);

        try {
            while (true) {
                Especie especie = (Especie) dataIS.readObject();
                lista.add(especie);
            }
        } catch (EOFException e) {
        }

        dataIS.close();

        XStream xstream = new XStream(new DomDriver());

        xstream.alias("especie", Especie.class);
        xstream.alias("especies", ArrayList.class);

        xstream.allowTypes(new Class[]{Especie.class});

        FileOutputStream fos = new FileOutputStream(".//datos//Especies.xml");

        xstream.toXML(lista, fos);

        fos.close();

        System.out.println("XML de especies generado correctamente.");
    }
}