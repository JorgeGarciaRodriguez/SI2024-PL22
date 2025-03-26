package ver_articulo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import revisores_conferencia.HU29508_View;

import java.awt.*;

public class HU29365_29366_View {
    private JFrame frame;
    private JTable tablaArticulos;
    private JTable tablaAutores;
    private JTextArea infoArticulo;
    private JComboBox<String> comboAutores;
    private JButton btnDondeSoyAutor;
    private JButton btnEnviadosPorMi;
    
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                HU29365_29366_View window = new HU29365_29366_View();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public HU29365_29366_View() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Visualización Artículos del Autor");
        frame.setSize(1000, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout(10, 10));

        //
        JPanel panelSuperior = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelSuperior.add(new JLabel("Autor: "));
        comboAutores = new JComboBox<>();
        comboAutores.setPreferredSize(new Dimension(200, 25));
        panelSuperior.add(comboAutores);

        btnDondeSoyAutor = new JButton("Artículos donde soy autor");
        btnEnviadosPorMi = new JButton("Artículos enviados por mí");
        panelSuperior.add(btnDondeSoyAutor);
        panelSuperior.add(btnEnviadosPorMi);

        frame.add(panelSuperior, BorderLayout.NORTH);

        //
        JPanel panelCentral = new JPanel(new GridLayout(1, 2, 10, 10));

        // Tabla de artículos
        JPanel panelTablaArticulos = new JPanel(new BorderLayout());
        panelTablaArticulos.setBorder(BorderFactory.createTitledBorder("Lista de Artículos"));

        String[] columnasArticulos = {"ID", "Título"};
        tablaArticulos = new JTable(new DefaultTableModel(columnasArticulos, 0) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
        JScrollPane scrollTablaArt = new JScrollPane(tablaArticulos);
        panelTablaArticulos.add(scrollTablaArt, BorderLayout.CENTER);

        panelCentral.add(panelTablaArticulos);

        // Info del artículo seleccionado
        JPanel panelInfo = new JPanel(new BorderLayout());
        panelInfo.setBorder(BorderFactory.createTitledBorder("Información del Artículo"));

        infoArticulo = new JTextArea();
        infoArticulo.setEditable(false);
        JScrollPane scrollInfo = new JScrollPane(infoArticulo);
        panelInfo.add(scrollInfo, BorderLayout.CENTER);

        panelCentral.add(panelInfo);

        frame.add(panelCentral, BorderLayout.CENTER);

        // 
        JPanel panelInferior = new JPanel(new BorderLayout());
        panelInferior.setBorder(BorderFactory.createTitledBorder("Autores"));

        String[] columnasAutores = {"Nombre", "Email", "Organización", "Grupo Investigación"};
        tablaAutores = new JTable(new DefaultTableModel(columnasAutores, 0) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
        JScrollPane scrollAutores = new JScrollPane(tablaAutores);
        panelInferior.add(scrollAutores, BorderLayout.CENTER);

        frame.add(panelInferior, BorderLayout.SOUTH);
    }

    public JFrame getFrame() { return frame; }

    public JTable getTablaArticulos() { return tablaArticulos; }

    public JTable getTablaAutores() { return tablaAutores; }

    public JTextArea getInfoArticulo() { return infoArticulo; }

    public JComboBox<String> getComboAutores() { return comboAutores; }

    public JButton getBtnDondeSoyAutor() { return btnDondeSoyAutor; }

    public JButton getBtnEnviadosPorMi() { return btnEnviadosPorMi; }

    public void setInfoArticulo(String texto) {
        infoArticulo.setText(texto);
    }
}
