package evaluacion;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Evaluacion_View {
    private JFrame frame;
    private JList<String> listaArticulos;
    private JComboBox<String> comboDecisiones;
    private JComboBox<String> comboRevisores; // Nuevo combo para seleccionar el revisor
    private JButton btnEnviarDecision;

    public Evaluacion_View() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Selección de Artículos para Revisión");
        frame.setBounds(100, 100, 600, 400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout());

        // Lista de artículos
        listaArticulos = new JList<>();
        JScrollPane scrollPane = new JScrollPane(listaArticulos);
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);

        // Panel inferior para decisiones y selección de revisor
        JPanel panelInferior = new JPanel();
        panelInferior.setLayout(new FlowLayout());

        // Combo para seleccionar el revisor
        comboRevisores = new JComboBox<>();
        panelInferior.add(new JLabel("Seleccionar Revisor:"));
        panelInferior.add(comboRevisores);

        // Combo para seleccionar la decisión
        comboDecisiones = new JComboBox<>(new String[]{"Quiero revisar", "No quiero revisar", "Podría revisar", "Tengo conflicto"});
        panelInferior.add(comboDecisiones);

        // Botón para enviar la decisión
        btnEnviarDecision = new JButton("Enviar Decisión");
        panelInferior.add(btnEnviarDecision);

        frame.getContentPane().add(panelInferior, BorderLayout.SOUTH);
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
    }

    public JFrame getFrame() {
        return frame;
    }

    public JList<String> getListaArticulos() {
        return listaArticulos;
    }

    public JComboBox<String> getComboDecisiones() {
        return comboDecisiones;
    }

    public JComboBox<String> getComboRevisores() {
        return comboRevisores;
    }

    public JButton getBtnEnviarDecision() {
        return btnEnviarDecision;
    }
}