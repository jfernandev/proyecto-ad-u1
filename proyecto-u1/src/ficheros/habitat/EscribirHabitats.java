package ficheros.habitat;
import modelo.Habitat;
import java.io.*;

public class EscribirHabitats {
    public static void main(String[] args) throws IOException {
        File fichero = new File(".//datos//Habitats.dat");
        FileOutputStream fileout = new FileOutputStream(fichero);
        ObjectOutputStream dataOS = new ObjectOutputStream(fileout);

        int ids[] = {1, 2, 3, 4, 5};
        String nombres[] = {
                "Pinar",
                "Hayedo",
                "Robledal",
                "Pradera",
                "Bosque mixto"
        };
        String descripciones[] = {
                "Bosque dominado por pinos.",
                "Bosque formado principalmente por hayas.",
                "Bosque donde predominan los robles.",
                "Zona abierta con abundante vegetación herbácea.",
                "Bosque formado por diferentes especies de árboles."
        };

        for (int i = 0; i < ids.length; i++) {
            Habitat habitat = new Habitat(
                    ids[i],
                    nombres[i],
                    descripciones[i]
            );
            dataOS.writeObject(habitat);
        }
        dataOS.close();
    }
}