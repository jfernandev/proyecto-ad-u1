package principal;
import java.util.Scanner;
import ficheros.especie.*;
import ficheros.habitat.*;
import java.io.*;

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
                    menuEspecies(sc);
                    break;
                case 2:
                    menuHabitats(sc);
                    break;
                case 3:
                    menuAvistamientos(sc);
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

    private static void menuEspecies(Scanner sc) {
        int opcion;

        do {
            System.out.println("\n===== ESPECIES =====");
            System.out.println("1. Listar especies");
            System.out.println("2. Añadir especie");
            System.out.println("3. Modificar especie");
            System.out.println("4. Eliminar especie");
            System.out.println("5. Buscar especie");
            System.out.println("0. Volver");
            System.out.print("Elige una opción: ");

            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    try {
                        LeerEspecies.listarEspecies();
                    } catch (IOException | ClassNotFoundException e) {
                        System.out.println("Error al leer las especies.");
                    }
                    break;
                case 2:
                    try {
                        CrearEspecie.anadir(sc);
                    } catch (IOException e) {
                        System.out.println("Error al añadir la especie.");
                    }
                    break;
                case 3:
                    try {
                        ModificarEspecie.modificar(sc);
                    } catch (IOException | ClassNotFoundException e) {
                        System.out.println("Error al modificar la especie.");
                    }
                    break;
                case 4:
                    try {
                        EliminarEspecie.eliminar(sc);
                    } catch (IOException | ClassNotFoundException e) {
                        System.out.println("Error al eliminar la especie.");
                    }
                    break;
                case 5:
                    try {
                        BuscarEspecie.buscar(sc);
                    } catch (IOException | ClassNotFoundException e) {
                        System.out.println("Error al buscar la especie.");
                    }
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }

    private static void menuHabitats(Scanner sc) {
        int opcion;

        do {
            System.out.println("\n===== HABITATS =====");
            System.out.println("1. Listar habitats");
            System.out.println("2. Añadir habitat");
            System.out.println("3. Modificar habitat");
            System.out.println("4. Eliminar habitat");
            System.out.println("5. Buscar habitat");
            System.out.println("0. Volver");
            System.out.print("Elige una opción: ");

            opcion = sc.nextInt();

            switch (opcion) {

                case 1:
                    System.out.println("Listar habitats");
                    break;
                case 2:
                    System.out.println("Añadir habitat");
                    break;
                case 3:
                    try {
                        ModificarHabitats.modificar(sc);
                    } catch (IOException | ClassNotFoundException e) {
                        System.out.println("Error al modificar el habitat.");
                    }
                    break;
                case 4:
                    System.out.println("Eliminar habitat");
                    break;
                case 5:
                    System.out.println("Buscar habitat");
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }

    private static void menuAvistamientos(Scanner sc) {
        int opcion;

        do {
            System.out.println("\n===== AVISTAMIENTOS =====");
            System.out.println("1. Listar avistamientos");
            System.out.println("2. Añadir avistamiento");
            System.out.println("3. Modificar avistamiento");
            System.out.println("4. Eliminar avistamiento");
            System.out.println("5. Buscar avistamiento");
            System.out.println("0. Volver");
            System.out.print("Elige una opción: ");

            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    System.out.println("Listar avistamientos");
                    break;
                case 2:
                    System.out.println("Añadir avistamiento");
                    break;
                case 3:
                    System.out.println("Modificar avistamiento");
                    break;
                case 4:
                    System.out.println("Eliminar avistamiento");
                    break;
                case 5:
                    System.out.println("Buscar avistamiento");
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }
}