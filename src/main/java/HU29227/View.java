package HU29227;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class View {
    private JFrame frame;
    private JTable tableArticulos;
    private JTable tableComentarios;
    private JButton btnVolver;

    public View() {
        frame = new JFrame("Revisión de Artículos Enviados");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Panel para la tabla de artículos
        String[] columnNamesArticulos = {"Título", "Decisión", "Autor"};
        tableArticulos = new JTable(new DefaultTableModel(columnNamesArticulos, 0));
        JScrollPane scrollPaneArticulos = new JScrollPane(tableArticulos);
        frame.add(scrollPaneArticulos, BorderLayout.NORTH);

        // Panel para la tabla de comentarios
        String[] columnNamesComentarios = {"Comentario", "Nivel Experto", "Decisión"};
        tableComentarios = new JTable(new DefaultTableModel(columnNamesComentarios, 0));
        JScrollPane scrollPaneComentarios = new JScrollPane(tableComentarios);
        frame.add(scrollPaneComentarios, BorderLayout.CENTER);

        // Botón Volver
        btnVolver = new JButton("Volver");
        JPanel panelBoton = new JPanel();
        panelBoton.add(btnVolver);
        frame.add(panelBoton, BorderLayout.SOUTH);
    }

    public JFrame getFrame() {
        return frame;
    }

    public JTable getTableArticulos() {
        return tableArticulos;
    }

    public JTable getTableComentarios() {
        return tableComentarios;
    }

    public JButton getBtnVolver() {
        return btnVolver;
    }
}
