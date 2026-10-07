package ficheros;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

public final class RepositorioObjetos {

    private RepositorioObjetos() {
    }

    public static <T> List<T> leer(File fichero, Class<T> tipo)
            throws IOException, ClassNotFoundException {
        List<T> elementos = new ArrayList<>();
        if (!fichero.exists() || fichero.length() == 0) {
            return elementos;
        }

        try (ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(fichero))) {
            try {
                while (true) {
                    elementos.add(tipo.cast(entrada.readObject()));
                }
            } catch (EOFException fin) {
                return elementos;
            }
        }
    }

    public static void escribir(File fichero, List<?> elementos) throws IOException {
        File temporal = new File(fichero.getPath() + ".tmp");
        try {
            try (ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(temporal))) {
                for (Object elemento : elementos) {
                    salida.writeObject(elemento);
                }
            }
            Files.move(temporal.toPath(), fichero.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } finally {
            Files.deleteIfExists(temporal.toPath());
        }
    }
}
