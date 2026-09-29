package principal;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n===== GESTOR DE SETAS =====");
            System.out.println("1. Gestionar especies");
            System.out.println("2. Gestionar hábitats");
            System.out.println("3. Gestionar avistamientos");
            System.out.println("4. Exportar datos a XML");
            System.out.println("0. Salir");
            System.out.print("Elige una opción: ");

            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    System.out.println("Gestión de especies");
                    break;
                case 2:
                    System.out.println("Gestión de hábitats");
                    break;
                case 3:
                    System.out.println("Gestión de avistamientos");
                    break;
                case 4:
                    System.out.println("Exportación a XML");
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
        sc.close();
    }
}