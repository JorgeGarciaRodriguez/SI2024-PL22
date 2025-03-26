package revision_articulos;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class HU29579_View extends JFrame {
    private JTable tableArticulos;
    private JTable tableRevisiones;
    private JButton btnVolver;

    public HU29579_View() {
        setTitle("Revisión de Artículos Enviados");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        setLocationRelativeTo(null); // Centra la ventana

        // Panel para la tabla de artículos
        JPanel panelArticulos = new JPanel(new BorderLayout());
        panelArticulos.setBorder(BorderFactory.createTitledBorder("Artículos Enviados"));

        String[] columnNamesArticulos = {"Artículo", "Decisión", "Autor"};
        DefaultTableModel modelArticulos = new DefaultTableModel(columnNamesArticulos, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tableArticulos = new JTable(modelArticulos);
        JScrollPane scrollPaneArticulos = new JScrollPane(tableArticulos);
        panelArticulos.add(scrollPaneArticulos, BorderLayout.CENTER);

        add(panelArticulos, BorderLayout.NORTH);

        // Panel para la tabla de revisiones
        JPanel panelRevisiones = new JPanel(new BorderLayout());
        panelRevisiones.setBorder(BorderFactory.createTitledBorder("Revisiones del Artículo"));

        String[] columnNamesRevisiones = {"Comentarios", "Nivel Experto", "Decisión"};
        DefaultTableModel modelRevisiones = new DefaultTableModel(columnNamesRevisiones, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tableRevisiones = new JTable(modelRevisiones);
        JScrollPane scrollPaneRevisiones = new JScrollPane(tableRevisiones);
        panelRevisiones.add(scrollPaneRevisiones, BorderLayout.CENTER);

        add(panelRevisiones, BorderLayout.CENTER);

        // Botón Volver
        JPanel panelBoton = new JPanel();
        btnVolver = new JButton("Volver");
        panelBoton.add(btnVolver);
        add(panelBoton, BorderLayout.SOUTH);
    }

    public JTable getTableArticulos() {
        return tableArticulos;
    }

    public JTable getTableRevisiones() {
        return tableRevisiones;
    }

    public JButton getBtnVolver() {
        return btnVolver;
    }

    // Devuelve el frame por si necesitas cerrarlo desde el controlador
    public JFrame getFrame() {
        return this;
    }

    // Main de prueba
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            HU29579_View view = new HU29579_View();
            view.setVisible(true);
        });
    }
}
