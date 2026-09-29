package ficheros.avistamiento;
import modelo.Avistamiento;
import java.io.*;

public class EscribirAvistamientos {
    public static void main(String[] args) throws IOException {

        File fichero = new File(".//datos//Avistamientos.dat");
        FileOutputStream fileout = new FileOutputStream(fichero);
        ObjectOutputStream dataOS = new ObjectOutputStream(fileout);

        int ids[] = {1, 2, 3, 4, 5};
        int idsEspecies[] = {1, 2, 3, 4, 5};
        int idsHabitats[] = {1, 1, 2, 3, 4};
        String fechas[] = {
                "20/09/2026",
                "21/09/2026",
                "22/09/2026",
                "23/09/2026",
                "24/09/2026"
        };
        String localizaciones[] = {
                "Pinar de Vitoria",
                "Pinar de Álava",
                "Hayedo de Altube",
                "Robledal de Izki",
                "Pradera de Gorbeia"
        };
        String observaciones[] = {
                "Varios ejemplares encontrados cerca del camino.",
                "Ejemplar de buen tamaño.",
                "Encontrado junto a varios ejemplares.",
                "Se observaron varios ejemplares.",
                "Ejemplar aislado."
        };

        for (int i = 0; i < ids.length; i++) {
            Avistamiento avistamiento = new Avistamiento(
                    ids[i],
                    idsEspecies[i],
                    idsHabitats[i],
                    fechas[i],
                    localizaciones[i],
                    observaciones[i]
            );
            dataOS.writeObject(avistamiento);
        }
        dataOS.close();
    }
}