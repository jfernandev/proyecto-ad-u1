package ficheros;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Utilidades {

    public static int pedirEntero(Scanner sc, String mensaje) {

        while (true) {

            try {
                System.out.print(mensaje);
                int numero = sc.nextInt();
                sc.nextLine();
                return numero;

            } catch (InputMismatchException e) {

                System.out.println("Debes introducir un número entero.");
                sc.nextLine();
            }
        }
    }

    public static String pedirTexto(Scanner sc, String mensaje) {

        System.out.print(mensaje);
        return sc.nextLine();
    }

    public static String pedirTextoNoVacio(Scanner sc, String mensaje) {

        while (true) {

            System.out.print(mensaje);
            String texto = sc.nextLine();

            if (!texto.trim().isEmpty()) {
                return texto;
            }

            System.out.println("El texto no puede estar vacío.");
        }
    }
}