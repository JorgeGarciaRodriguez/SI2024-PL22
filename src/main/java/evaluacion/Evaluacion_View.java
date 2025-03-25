package evaluacion;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Evaluacion_View {
    private JFrame frame;
    private JList<String> listaArticulos;
    private JComboBox<String> comboDecisiones;
    private JComboBox<String> comboRevisores;
    private JButton btnEnviarDecision;

    public Evaluacion_View() {
        initialize();
    }

    private void initialize() {
        // Configuración del frame principal
        frame = new JFrame("Selección de Artículos para Revisión");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setBounds(100, 100, 600, 400);
        
        // Panel principal con layout null (como en la versión original)
        frame.getContentPane().setLayout(null);
        
        // Tamaño fijo para ambos ComboBox
        Dimension comboSize = new Dimension(250, 25);
        
        // Lista de artículos
        listaArticulos = new JList<>();
        listaArticulos.setBounds(10, 10, 580, 200);
        listaArticulos.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        listaArticulos.setBackground(new Color(240, 240, 240));
        listaArticulos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        frame.getContentPane().add(listaArticulos);

        // ScrollPane para la lista
        JScrollPane scrollPane = new JScrollPane(listaArticulos);
        scrollPane.setBounds(10, 10, 580, 200);
        frame.getContentPane().add(scrollPane);

        // Label y ComboBox de Revisor
        JLabel lblRevisor = new JLabel("Seleccionar Revisor:");
        lblRevisor.setBounds(10, 220, 150, 25);
        lblRevisor.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        frame.getContentPane().add(lblRevisor);
        
        comboRevisores = new JComboBox<>();
        comboRevisores.setBounds(160, 220, comboSize.width, comboSize.height);
        comboRevisores.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        comboRevisores.setPrototypeDisplayValue("999 - Nombre Largo de Ejemplo");
        comboRevisores.addItemListener(e -> actualizarEstadoBoton());
        frame.getContentPane().add(comboRevisores);

        // Label y ComboBox de Decisión
        JLabel lblDecision = new JLabel("Decisión:");
        lblDecision.setBounds(10, 250, 150, 25);
        lblDecision.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        frame.getContentPane().add(lblDecision);
        
        comboDecisiones = new JComboBox<>(new String[]{
            "Quiero revisar", 
            "No quiero revisar", 
            "Podría revisar", 
            "Tengo conflicto"
        });
        comboDecisiones.setBounds(160, 250, comboSize.width, comboSize.height);
        comboDecisiones.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        comboDecisiones.setSelectedIndex(-1);
        comboDecisiones.addItemListener(e -> actualizarEstadoBoton());
        frame.getContentPane().add(comboDecisiones);

        // Botón Enviar Decisión
        btnEnviarDecision = new JButton("Enviar Decisión");
        btnEnviarDecision.setBounds(420, 280, 150, 30);
        btnEnviarDecision.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnEnviarDecision.setBackground(new Color(70, 130, 180));
        btnEnviarDecision.setForeground(Color.WHITE);
        btnEnviarDecision.setFocusPainted(false);
        btnEnviarDecision.setEnabled(false);
        frame.getContentPane().add(btnEnviarDecision);
    }

    // Métodos originales sin cambios
    private void actualizarEstadoBoton() {
        boolean ambosSeleccionados = 
            comboRevisores.getSelectedIndex() != -1 && 
            comboDecisiones.getSelectedIndex() != -1;
        btnEnviarDecision.setEnabled(ambosSeleccionados);
    }

    public void mostrarArticulos(List<String> articulos) {
        DefaultListModel<String> model = new DefaultListModel<>();
        for (String articulo : articulos) {
            model.addElement(articulo);
        }
        listaArticulos.setModel(model);
    }

    public void mostrarRevisores(List<String> revisores) {
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        for (String revisor : revisores) {
            model.addElement(revisor);
        }
        comboRevisores.setModel(model);
        comboRevisores.setSelectedIndex(-1);
    }

    public JFrame getFrame() { return frame; }
    public JList<String> getListaArticulos() { return listaArticulos; }
    public JComboBox<String> getComboDecisiones() { return comboDecisiones; }
    public JComboBox<String> getComboRevisores() { return comboRevisores; }
    public JButton getBtnEnviarDecision() { return btnEnviarDecision; }
}