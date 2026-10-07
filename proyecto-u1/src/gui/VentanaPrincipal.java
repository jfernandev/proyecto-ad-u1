package gui;

import ficheros.avistamiento.*;
import ficheros.especie.*;
import ficheros.habitat.*;
import modelo.Avistamiento;
import modelo.Especie;
import modelo.Habitat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import java.awt.GridLayout;

public class VentanaPrincipal extends JFrame {

    public VentanaPrincipal() {
        super("Gestor de Setas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(360, 340);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(6, 1, 8, 8));
        panel.add(new JLabel("Gestor de Setas", SwingConstants.CENTER));
        agregarBoton(panel, "Especies", this::abrirEspecies);
        agregarBoton(panel, "Hábitats", this::abrirHabitats);
        agregarBoton(panel, "Avistamientos", this::abrirAvistamientos);
        agregarBoton(panel, "XML", () -> new VentanaXML().setVisible(true));
        agregarBoton(panel, "Salir", this::dispose);
        add(panel);
    }

    private void agregarBoton(JPanel panel, String texto, Runnable accion) {
        JButton boton = new JButton(texto);
        boton.addActionListener(evento -> accion.run());
        panel.add(boton);
    }

    private void abrirEspecies() {
        new VentanaGestion<>(new VentanaGestion.Operaciones<Especie>() {
            public String titulo() { return "Especies"; }
            public String[] columnas() { return campos(); }
            public String[] campos() {
                return new String[]{"ID", "Nombre común", "Nombre científico", "Comestibilidad", "Descripción"};
            }
            public java.util.List<Especie> listar() throws java.io.IOException, ClassNotFoundException {
                return LeerEspecies.obtenerEspecies();
            }
            public Object[] fila(Especie especie) {
                return new Object[]{especie.getId(), especie.getNombreComun(), especie.getNombreCientifico(),
                        especie.getComestibilidad(), especie.getDescripcion()};
            }
            public String[] valores(Especie especie) {
                return new String[]{String.valueOf(especie.getId()), especie.getNombreComun(),
                        especie.getNombreCientifico(), especie.getComestibilidad(), especie.getDescripcion()};
            }
            public int id(Especie especie) { return especie.getId(); }
            public Especie buscar(int id) throws java.io.IOException, ClassNotFoundException {
                return BuscarEspecie.buscarPorId(id);
            }
            public void crear(String[] v) throws java.io.IOException, ClassNotFoundException {
                CrearEspecie.crear(new Especie(entero(v[0]), v[1], v[2], v[3], v[4]));
            }
            public boolean modificar(int id, String[] v) throws java.io.IOException, ClassNotFoundException {
                return ModificarEspecies.modificar(id, new Especie(id, v[1], v[2], v[3], v[4]));
            }
            public boolean eliminar(int id) throws java.io.IOException, ClassNotFoundException {
                return EliminarEspecie.eliminar(id);
            }
        }).setVisible(true);
    }

    private void abrirHabitats() {
        new VentanaGestion<>(new VentanaGestion.Operaciones<Habitat>() {
            public String titulo() { return "Hábitats"; }
            public String[] columnas() { return campos(); }
            public String[] campos() { return new String[]{"ID", "Nombre", "Descripción"}; }
            public java.util.List<Habitat> listar() throws java.io.IOException, ClassNotFoundException {
                return LeerHabitats.obtenerHabitats();
            }
            public Object[] fila(Habitat habitat) {
                return new Object[]{habitat.getId(), habitat.getNombre(), habitat.getDescripcion()};
            }
            public String[] valores(Habitat habitat) {
                return new String[]{String.valueOf(habitat.getId()), habitat.getNombre(), habitat.getDescripcion()};
            }
            public int id(Habitat habitat) { return habitat.getId(); }
            public Habitat buscar(int id) throws java.io.IOException, ClassNotFoundException {
                return BuscarHabitat.buscarPorId(id);
            }
            public void crear(String[] v) throws java.io.IOException, ClassNotFoundException {
                CrearHabitat.crear(new Habitat(entero(v[0]), v[1], v[2]));
            }
            public boolean modificar(int id, String[] v) throws java.io.IOException, ClassNotFoundException {
                return ModificarHabitats.modificar(id, new Habitat(id, v[1], v[2]));
            }
            public boolean eliminar(int id) throws java.io.IOException, ClassNotFoundException {
                return EliminarHabitat.eliminar(id);
            }
        }).setVisible(true);
    }

    private void abrirAvistamientos() {
        new VentanaGestion<>(new VentanaGestion.Operaciones<Avistamiento>() {
            public String titulo() { return "Avistamientos"; }
            public String[] columnas() { return campos(); }
            public String[] campos() {
                return new String[]{"ID", "ID especie", "ID hábitat", "Fecha", "Localización", "Observaciones"};
            }
            public java.util.List<Avistamiento> listar() throws java.io.IOException, ClassNotFoundException {
                return LeerAvistamientos.obtenerAvistamientos();
            }
            public Object[] fila(Avistamiento a) {
                return new Object[]{a.getId(), a.getIdEspecie(), a.getIdHabitat(), a.getFecha(),
                        a.getLocalizacion(), a.getObservaciones()};
            }
            public String[] valores(Avistamiento a) {
                return new String[]{String.valueOf(a.getId()), String.valueOf(a.getIdEspecie()),
                        String.valueOf(a.getIdHabitat()), a.getFecha(), a.getLocalizacion(), a.getObservaciones()};
            }
            public int id(Avistamiento a) { return a.getId(); }
            public Avistamiento buscar(int id) throws java.io.IOException, ClassNotFoundException {
                return BuscarAvistamiento.buscarPorId(id);
            }
            public void crear(String[] v) throws java.io.IOException, ClassNotFoundException {
                CrearAvistamiento.crear(new Avistamiento(entero(v[0]), entero(v[1]), entero(v[2]), v[3], v[4], v[5]));
            }
            public boolean modificar(int id, String[] v) throws java.io.IOException, ClassNotFoundException {
                return ModificarAvistamientos.modificar(id,
                        new Avistamiento(id, entero(v[1]), entero(v[2]), v[3], v[4], v[5]));
            }
            public boolean eliminar(int id) throws java.io.IOException, ClassNotFoundException {
                return EliminarAvistamiento.eliminar(id);
            }
        }).setVisible(true);
    }

    private static int entero(String valor) {
        return Integer.parseInt(valor.trim());
    }

    public static void iniciar() {
        SwingUtilities.invokeLater(() -> new VentanaPrincipal().setVisible(true));
    }
}
