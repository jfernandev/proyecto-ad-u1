package gui;

import modelo.Avistamiento;
import modelo.Especie;
import modelo.Habitat;
import xml.ExportarAvistamientosXML;
import xml.ExportarEspeciesXML;
import xml.ExportarHabitatsXML;
import xml.LeerAvistamientosXML;
import xml.LeerEspeciesXML;
import xml.LeerHabitatsXML;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.GridLayout;
import java.io.IOException;
import java.util.List;

public class VentanaXML extends JFrame {

    private interface Accion {
        void ejecutar() throws IOException, ClassNotFoundException;
    }

    public VentanaXML() {
        super("Gestión XML");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(380, 240);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(4, 1, 8, 8));
        agregarBoton(panel, "Exportar especies, hábitats y avistamientos", this::exportar);
        agregarBoton(panel, "Leer especies desde XML", () -> mostrar("Especies", LeerEspeciesXML.leerLista()));
        agregarBoton(panel, "Leer hábitats desde XML", () -> mostrar("Hábitats", LeerHabitatsXML.leerLista()));
        agregarBoton(panel, "Leer avistamientos desde XML",
                () -> mostrar("Avistamientos", LeerAvistamientosXML.leerLista()));
        add(panel);
    }

    private void agregarBoton(JPanel panel, String texto, Accion accion) {
        JButton boton = new JButton(texto);
        boton.addActionListener(evento -> {
            try {
                accion.ejecutar();
            } catch (IOException | ClassNotFoundException | IllegalArgumentException
                     | IllegalStateException | SecurityException
                     | com.thoughtworks.xstream.XStreamException error) {
                JOptionPane.showMessageDialog(this, error.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        panel.add(boton);
    }

    private void exportar() throws IOException, ClassNotFoundException {
        ExportarEspeciesXML.exportar();
        ExportarHabitatsXML.exportar();
        ExportarAvistamientosXML.exportar();
        JOptionPane.showMessageDialog(this, "Exportación XML completada.");
    }

    private void mostrar(String titulo, List<?> elementos) {
        JTextArea texto = new JTextArea(18, 50);
        texto.setEditable(false);
        for (Object elemento : elementos) {
            texto.append(elemento.toString());
            texto.append("\n--------------------\n");
        }
        if (elementos.isEmpty()) {
            texto.setText("No hay registros en este archivo XML.");
        }
        JOptionPane.showMessageDialog(this, new JScrollPane(texto), titulo, JOptionPane.INFORMATION_MESSAGE);
    }
}
