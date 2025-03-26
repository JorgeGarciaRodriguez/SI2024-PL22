package anotaciones_discusiones;

import javax.swing.*;
import java.awt.*;

public class HU29513_View {

    private JFrame frame;
    private JComboBox<String> comboArticulo;
    private JComboBox<String> comboRevisor;
    private JLabel lblEstado;
    private JCheckBox chkDecisionMarcada;

    private JTextArea areaMisAnotaciones;
    private JTextArea areaOtrasAnotaciones;
    private JTextArea areaNuevaAnotacion;

    private JButton btnGuardar;
    private JButton btnMarcarDecision;
    private JButton btnVolver;

    public HU29513_View() {
        frame = new JFrame("Anotaciones de Discusión");
        frame.setSize(900, 700);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Panel superior
        JPanel panelSuperior = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        panelSuperior.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel lblArticulo = new JLabel("Artículo:");
        gbc.gridx = 0; gbc.gridy = 0;
        panelSuperior.add(lblArticulo, gbc);

        comboArticulo = new JComboBox<>();
        comboArticulo.setPreferredSize(new Dimension(300, 25));
        gbc.gridx = 1;
        panelSuperior.add(comboArticulo, gbc);

        JLabel lblRevisor = new JLabel("Revisor:");
        gbc.gridx = 2;
        panelSuperior.add(lblRevisor, gbc);

        comboRevisor = new JComboBox<>();
        comboRevisor.setPreferredSize(new Dimension(300, 25));
        gbc.gridx = 3;
        panelSuperior.add(comboRevisor, gbc);

        lblEstado = new JLabel("Estado: ");
        gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth = 2;
        panelSuperior.add(lblEstado, gbc);

        chkDecisionMarcada = new JCheckBox("Decisión marcada: NO");
        chkDecisionMarcada.setEnabled(false);
        gbc.gridx = 2; gbc.gridwidth = 2;
        panelSuperior.add(chkDecisionMarcada, gbc);

        frame.add(panelSuperior, BorderLayout.NORTH);

        // Panel central
        JPanel panelCentro = new JPanel(new GridLayout(3, 1, 10, 10));
        panelCentro.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        areaMisAnotaciones = new JTextArea();
        areaMisAnotaciones.setEditable(false);
        panelCentro.add(createPanelConTitulo("Mis Anotaciones", areaMisAnotaciones));

        areaOtrasAnotaciones = new JTextArea();
        areaOtrasAnotaciones.setEditable(false);
        panelCentro.add(createPanelConTitulo("Anotaciones de otros revisores", areaOtrasAnotaciones));

        areaNuevaAnotacion = new JTextArea();
        panelCentro.add(createPanelConTitulo("Nueva Anotación", areaNuevaAnotacion));

        frame.add(panelCentro, BorderLayout.CENTER);

        // Panel inferior
        JPanel panelInferior = new JPanel();
        btnGuardar = new JButton("Guardar Anotación");
        btnMarcarDecision = new JButton("Marcar Decisión Final");
        btnVolver = new JButton("Volver");

        panelInferior.add(btnGuardar);
        panelInferior.add(btnMarcarDecision);
        panelInferior.add(btnVolver);

        frame.add(panelInferior, BorderLayout.SOUTH);
    }

    private JPanel createPanelConTitulo(String titulo, JTextArea area) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder(titulo));
        panel.add(new JScrollPane(area), BorderLayout.CENTER);
        return panel;
    }

    // Getters
    public JFrame getFrame() { return frame; }
    public JComboBox<String> getComboArticulo() { return comboArticulo; }
    public JComboBox<String> getComboRevisor() { return comboRevisor; }
    public JTextArea getAreaMisAnotaciones() { return areaMisAnotaciones; }
    public JTextArea getAreaOtrasAnotaciones() { return areaOtrasAnotaciones; }
    public JTextArea getAreaNuevaAnotacion() { return areaNuevaAnotacion; }
    public JButton getBtnGuardar() { return btnGuardar; }
    public JButton getBtnMarcarDecision() { return btnMarcarDecision; }
    public JButton getBtnVolver() { return btnVolver; }

    // Setters para estado
    public void setEstadoTexto(String estado) {
        lblEstado.setText("Estado: " + estado);
    }

    public void setDecisionMarcada(boolean marcada) {
        chkDecisionMarcada.setSelected(marcada);
        chkDecisionMarcada.setText("Decisión marcada: " + (marcada ? "SÍ" : "NO"));
    }

    public void habilitarEdicion(boolean habilitado) {
        areaNuevaAnotacion.setEnabled(habilitado);
        btnGuardar.setEnabled(habilitado);
        btnMarcarDecision.setEnabled(habilitado);
    }

    // Main para prueba
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            HU29513_View vista = new HU29513_View();
            vista.getFrame().setVisible(true);
        });
    }
}
