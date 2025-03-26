package revisores_conferencia;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class HU29508_View {
    private JFrame frame;
    private JTable tablaRevisores;
    private JTextArea infoArticulo;
    private JCheckBox chkSinConflicto;
    private JCheckBox chkConPreferencia;
    private JButton btnAsignar;
    private JButton btnDetalle;
    private JButton btnVolver;
    private JComboBox<String> comboArticulo;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                HU29508_View window = new HU29508_View();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public HU29508_View() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setTitle("Asignación de Revisores");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 700);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout(10, 10));

        // Panel superior: combo + filtros
        JPanel panelFiltros = new JPanel(new FlowLayout(FlowLayout.LEFT));

        panelFiltros.add(new JLabel("Artículo:"));
        comboArticulo = new JComboBox<>();
        comboArticulo.setPreferredSize(new Dimension(300, 25));
        panelFiltros.add(comboArticulo);

        chkSinConflicto = new JCheckBox("Solo sin conflicto");
        chkConPreferencia = new JCheckBox("Solo con preferencia");
        panelFiltros.add(chkSinConflicto);
        panelFiltros.add(chkConPreferencia);

        frame.add(panelFiltros, BorderLayout.NORTH);

        // Panel central dividido (izquierda info artículo, derecha tabla)
        JPanel panelCentro = new JPanel(new GridLayout(1, 2, 10, 10));

        // Panel izquierdo: Información del artículo
        JPanel panelArticulo = new JPanel(new BorderLayout());
        panelArticulo.setBorder(BorderFactory.createTitledBorder("Información del Artículo"));

        infoArticulo = new JTextArea();
        infoArticulo.setEditable(false);
        infoArticulo.setLineWrap(true);
        infoArticulo.setWrapStyleWord(true);

        JScrollPane scrollInfo = new JScrollPane(infoArticulo);
        panelArticulo.add(scrollInfo, BorderLayout.CENTER);

        panelCentro.add(panelArticulo);

        // Panel derecho: Tabla de revisores
        JPanel panelTabla = new JPanel(new BorderLayout());
        panelTabla.setBorder(BorderFactory.createTitledBorder("Lista de Revisores"));

        String[] columnas = {"Nombre", "Preferencias", "Conflicto", "Asignar"};
        tablaRevisores = new JTable(new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 3;
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnIndex == 3 ? Boolean.class : String.class;
            }
        });

        panelTabla.add(new JScrollPane(tablaRevisores), BorderLayout.CENTER);
        panelCentro.add(panelTabla);

        frame.add(panelCentro, BorderLayout.CENTER);

        // Panel inferior: botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnAsignar = new JButton("Asignar Seleccionados");
        btnDetalle = new JButton("Detalle Revisor");
        btnVolver = new JButton("Volver");
        panelBotones.add(btnAsignar);
        panelBotones.add(btnDetalle);
        panelBotones.add(btnVolver);

        frame.add(panelBotones, BorderLayout.SOUTH);
    }

    // Getters para el Controller
    public JFrame getFrame() { return frame; }
    public JTable getTablaRevisores() { return tablaRevisores; }
    public JTextArea getInfoArticulo() { return infoArticulo; }
    public JCheckBox getChkSinConflicto() { return chkSinConflicto; }
    public JCheckBox getChkConPreferencia() { return chkConPreferencia; }
    public JButton getBtnAsignar() { return btnAsignar; }
    public JButton getBtnDetalle() { return btnDetalle; }
    public JButton getBtnVolver() { return btnVolver; }
    public JComboBox<String> getComboArticulo() { return comboArticulo; }

    // Setter para mostrar texto en el área de información del artículo
    public void setInfoArticulo(String texto) {
        infoArticulo.setText(texto);
    }
}
