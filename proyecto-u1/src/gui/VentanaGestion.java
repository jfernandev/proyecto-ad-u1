package gui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class VentanaGestion<T> extends JFrame {

    public interface Operaciones<T> {
        String titulo();
        String[] columnas();
        String[] campos();
        List<T> listar() throws IOException, ClassNotFoundException;
        Object[] fila(T elemento);
        String[] valores(T elemento);
        int id(T elemento);
        T buscar(int id) throws IOException, ClassNotFoundException;
        void crear(String[] valores) throws IOException, ClassNotFoundException;
        boolean modificar(int id, String[] valores) throws IOException, ClassNotFoundException;
        boolean eliminar(int id) throws IOException, ClassNotFoundException;
    }

    private interface Accion {
        void ejecutar() throws IOException, ClassNotFoundException;
    }

    private final Operaciones<T> operaciones;
    private final DefaultTableModel modelo;
    private final JTable tabla;
    private List<T> filas = new ArrayList<>();

    public VentanaGestion(Operaciones<T> operaciones) {
        super(operaciones.titulo());
        this.operaciones = operaciones;
        this.modelo = new DefaultTableModel(operaciones.columnas(), 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        this.tabla = new JTable(modelo);
        construirVentana();
        setLocationRelativeTo(null);
        ejecutar(this::cargarDatos);
    }

    private void construirVentana() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(820, 420);
        setLayout(new BorderLayout(8, 8));
        add(new JScrollPane(tabla), BorderLayout.CENTER);

        JPanel acciones = new JPanel();
        agregarBoton(acciones, "Listar", this::cargarDatos);
        agregarBoton(acciones, "Añadir", () -> mostrarFormulario(null));
        agregarBoton(acciones, "Buscar", this::buscar);
        agregarBoton(acciones, "Modificar", this::editarSeleccionado);
        agregarBoton(acciones, "Eliminar", this::eliminarSeleccionado);
        add(acciones, BorderLayout.SOUTH);
    }

    private void agregarBoton(JPanel panel, String texto, Accion accion) {
        JButton boton = new JButton(texto);
        boton.addActionListener(evento -> ejecutar(accion));
        panel.add(boton);
    }

    private void ejecutar(Accion accion) {
        try {
            accion.ejecutar();
        } catch (IOException | ClassNotFoundException | IllegalArgumentException
                 | IllegalStateException | SecurityException error) {
            JOptionPane.showMessageDialog(this, error.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cargarDatos() throws IOException, ClassNotFoundException {
        mostrarFilas(operaciones.listar());
    }

    private void mostrarFilas(List<T> elementos) {
        filas = new ArrayList<>(elementos);
        modelo.setRowCount(0);
        for (T elemento : filas) {
            modelo.addRow(operaciones.fila(elemento));
        }
    }

    private void buscar() throws IOException, ClassNotFoundException {
        String entrada = JOptionPane.showInputDialog(this, "Introduce el ID que quieres buscar:");
        if (entrada == null) {
            return;
        }
        int id = Integer.parseInt(entrada.trim());
        T elemento = operaciones.buscar(id);
        if (elemento == null) {
            mostrarFilas(new ArrayList<>());
            JOptionPane.showMessageDialog(this, "No se encontró ningún registro con ese ID.");
            return;
        }
        mostrarFilas(java.util.Collections.singletonList(elemento));
    }

    private void mostrarFormulario(T elemento) throws IOException, ClassNotFoundException {
        String[] campos = operaciones.campos();
        String[] valores = elemento == null ? new String[campos.length] : operaciones.valores(elemento);
        JTextField[] entradas = new JTextField[campos.length];
        JPanel formulario = new JPanel(new GridLayout(campos.length, 2, 6, 6));

        for (int i = 0; i < campos.length; i++) {
            formulario.add(new JLabel(campos[i] + ":"));
            entradas[i] = new JTextField(valores[i], 24);
            if (elemento != null && i == 0) {
                entradas[i].setEditable(false);
            }
            formulario.add(entradas[i]);
        }

        int resultado = JOptionPane.showConfirmDialog(
                this,
                formulario,
                elemento == null ? "Añadir " + operaciones.titulo().toLowerCase() : "Modificar " + operaciones.titulo().toLowerCase(),
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );
        if (resultado != JOptionPane.OK_OPTION) {
            return;
        }

        String[] nuevosValores = new String[entradas.length];
        for (int i = 0; i < entradas.length; i++) {
            nuevosValores[i] = entradas[i].getText().trim();
            if (nuevosValores[i].isEmpty()) {
                throw new IllegalArgumentException("Todos los campos son obligatorios.");
            }
        }

        if (elemento == null) {
            operaciones.crear(nuevosValores);
            JOptionPane.showMessageDialog(this, "Registro añadido correctamente.");
        } else if (operaciones.modificar(operaciones.id(elemento), nuevosValores)) {
            JOptionPane.showMessageDialog(this, "Registro modificado correctamente.");
        } else {
            JOptionPane.showMessageDialog(this, "No se encontró el registro que quieres modificar.");
        }
        cargarDatos();
    }

    private void editarSeleccionado() throws IOException, ClassNotFoundException {
        T seleccionado = obtenerSeleccionado();
        if (seleccionado != null) {
            mostrarFormulario(seleccionado);
        }
    }

    private void eliminarSeleccionado() throws IOException, ClassNotFoundException {
        T seleccionado = obtenerSeleccionado();
        if (seleccionado == null) {
            return;
        }
        int confirmar = JOptionPane.showConfirmDialog(
                this,
                "¿Seguro que quieres eliminar el registro seleccionado?",
                "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
        );
        if (confirmar != JOptionPane.YES_OPTION) {
            return;
        }
        if (operaciones.eliminar(operaciones.id(seleccionado))) {
            JOptionPane.showMessageDialog(this, "Registro eliminado correctamente.");
        } else {
            JOptionPane.showMessageDialog(this, "No se encontró el registro que quieres eliminar.");
        }
        cargarDatos();
    }

    private T obtenerSeleccionado() {
        int indice = tabla.getSelectedRow();
        if (indice < 0 || indice >= filas.size()) {
            JOptionPane.showMessageDialog(this, "Selecciona primero una fila de la tabla.");
            return null;
        }
        return filas.get(indice);
    }
}
