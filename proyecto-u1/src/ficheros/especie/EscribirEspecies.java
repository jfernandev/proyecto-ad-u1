package ficheros.especie;
import modelo.Especie;
import java.io.*;

public class EscribirEspecies {
    public static void main(String[] args) throws IOException {
        File fichero = new File(".//datos//Especies.dat");
        FileOutputStream fileout = new FileOutputStream(fichero);
        ObjectOutputStream dataOS = new ObjectOutputStream(fileout);

        int ids[] = {1, 2, 3, 4, 5};
        String nombres[] = {
                "Niscalo",
                "Boletus",
                "Galamperna",
                "Huevo de rey",
                "Carbonera"
        };
        String nombresCientificos[] = {
                "Lactarius deliciosus",
                "Boletus edulis",
                "Macrolepiota procera",
                "Amanita caesarea",
                "Russula cyanoxantha"
        };
        String comestibilidad[] = {
                "Comestible",
                "Comestible",
                "Comestible",
                "Comestible",
                "Comestible"
        };
        String descripciones[] = {
                "Seta de color naranja que suele aparecer en pinares.",
                "Seta de gran tamaño y carne blanca.",
                "Seta de sombrero grande y pie alto.",
                "Seta de sombrero anaranjado.",
                "Seta de colores variados."
        };

        for (int i = 0; i < ids.length; i++) {
            Especie especie = new Especie(
                    ids[i],
                    nombres[i],
                    nombresCientificos[i],
                    comestibilidad[i],
                    descripciones[i]
            );
            dataOS.writeObject(especie);
        }
        dataOS.close();
    }
}
