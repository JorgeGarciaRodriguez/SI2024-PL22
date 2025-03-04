package HU29227;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class View {
    private JFrame frame;
    private JTable tableArticulos;
    private JTable tableComentarios;
    private JButton btnVolver;
    private DefaultTableModel modelArticulos;
    private DefaultTableModel modelComentarios;

    public View() {
        frame = new JFrame("Revisión de Artículos Enviados");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Panel para la tabla de artículos
        JPanel panelArticulos = new JPanel(new BorderLayout());
        panelArticulos.setBorder(BorderFactory.createTitledBorder("Artículos"));

        String[] columnNamesArticulos = {"Título", "Decisión", "Autor"};
        modelArticulos = new DefaultTableModel(columnNamesArticulos, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Hace que la tabla sea de solo lectura
            }
        };
        tableArticulos = new JTable(modelArticulos);
        JScrollPane scrollPaneArticulos = new JScrollPane(tableArticulos);
        panelArticulos.add(scrollPaneArticulos, BorderLayout.CENTER);
        frame.add(panelArticulos, BorderLayout.NORTH);

        // Panel para la tabla de comentarios
        JPanel panelComentarios = new JPanel(new BorderLayout());
        panelComentarios.setBorder(BorderFactory.createTitledBorder("Comentarios"));

        String[] columnNamesComentarios = {"Comentario", "Nivel Experto", "Decisión"};
        modelComentarios = new DefaultTableModel(columnNamesComentarios, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Solo lectura
            }
        };
        tableComentarios = new JTable(modelComentarios);
        JScrollPane scrollPaneComentarios = new JScrollPane(tableComentarios);
        panelComentarios.add(scrollPaneComentarios, BorderLayout.CENTER);
        frame.add(panelComentarios, BorderLayout.CENTER);

        // Botón Volver
        JPanel panelBoton = new JPanel();
        btnVolver = new JButton("Volver");
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

    public void setArticulosData(Object[][] data) {
        modelArticulos.setRowCount(0);
        for (Object[] row : data) {
            modelArticulos.addRow(row);
        }
    }

    public void setComentariosData(Object[][] data) {
        modelComentarios.setRowCount(0);
        for (Object[] row : data) {
            modelComentarios.addRow(row);
        }
    }

    public void addArticuloSelectionListener(MouseAdapter listener) {
        tableArticulos.addMouseListener(listener);
    }

    public void addVolverListener(ActionListener listener) {
        btnVolver.addActionListener(listener);
    }
}