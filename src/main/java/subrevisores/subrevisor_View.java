package subrevisores;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

public class subrevisor_View {
    private JFrame frame;
    private JComboBox<String> comboRevisoresPrincipales;
    private JComboBox<String> comboRevisores;
    private JComboBox<String> comboTracks;
    private JComboBox<String> comboArticulos;
    private JButton btnEnviarInvitacion;

    public subrevisor_View() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Gestión de Subrevisores");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        
        JPanel mainPanel = new JPanel();
        mainPanel.setBorder(new EmptyBorder(15, 15, 15, 15));
        mainPanel.setLayout(new BorderLayout(10, 10));
        frame.getContentPane().add(mainPanel);
        
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(70, 130, 180));
        JLabel titleLabel = new JLabel("ASIGNACIÓN DE SUBREVISORES");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        titleLabel.setForeground(Color.WHITE);
        titlePanel.add(titleLabel);
        mainPanel.add(titlePanel, BorderLayout.NORTH);
        
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        Dimension comboSize = new Dimension(350, 30);
        Font comboFont = new Font("Segoe UI", Font.PLAIN, 14);
        
        // Revisor Principal
        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel lblRevisorPrincipal = createLabel("Revisor Principal:");
        formPanel.add(lblRevisorPrincipal, gbc);
        
        gbc.gridx = 1;
        comboRevisoresPrincipales = new JComboBox<>();
        comboRevisoresPrincipales.setFont(comboFont);
        comboRevisoresPrincipales.setPreferredSize(comboSize);
        comboRevisoresPrincipales.addItem("");
        formPanel.add(comboRevisoresPrincipales, gbc);
        
        // Track
        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel lblTrack = createLabel("Track:");
        formPanel.add(lblTrack, gbc);
        
        gbc.gridx = 1;
        comboTracks = new JComboBox<>();
        comboTracks.setFont(comboFont);
        comboTracks.setPreferredSize(comboSize);
        comboTracks.setEnabled(false);
        formPanel.add(comboTracks, gbc);
        
        // Artículo
        gbc.gridx = 0;
        gbc.gridy = 2;
        JLabel lblArticulo = createLabel("Artículo:");
        formPanel.add(lblArticulo, gbc);
        
        gbc.gridx = 1;
        comboArticulos = new JComboBox<>();
        comboArticulos.setFont(comboFont);
        comboArticulos.setPreferredSize(comboSize);
        comboArticulos.setEnabled(false);
        formPanel.add(comboArticulos, gbc);
        
        // Subrevisores disponibles
        gbc.gridx = 0;
        gbc.gridy = 3;
        JLabel lblSubrevisores = createLabel("Subrevisores disponibles:");
        formPanel.add(lblSubrevisores, gbc);
        
        gbc.gridx = 1;
        comboRevisores = new JComboBox<>();
        comboRevisores.setFont(comboFont);
        comboRevisores.setPreferredSize(comboSize);
        comboRevisores.setEnabled(false);
        formPanel.add(comboRevisores, gbc);
        
        // Botón Enviar Invitación
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.CENTER;
        btnEnviarInvitacion = createButton("Enviar Invitación", new Color(34, 139, 34));
        btnEnviarInvitacion.setEnabled(false);
        formPanel.add(btnEnviarInvitacion, gbc);
        
        mainPanel.add(formPanel, BorderLayout.CENTER);
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        return label;
    }

    private JButton createButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(8, 20, 8, 20));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return button;
    }

    public void setControlesHabilitados(boolean habilitado) {
        comboTracks.setEnabled(habilitado);
        comboArticulos.setEnabled(habilitado);
        comboRevisores.setEnabled(habilitado);
        btnEnviarInvitacion.setEnabled(habilitado);
    }

    public void mostrarRevisoresPrincipales(List<String> revisores) {
        comboRevisoresPrincipales.removeAllItems();
        comboRevisoresPrincipales.addItem("");
        revisores.forEach(comboRevisoresPrincipales::addItem);
    }

    public void mostrarRevisores(List<String> revisores) {
        comboRevisores.removeAllItems();
        if (revisores.isEmpty()) {
            comboRevisores.addItem("No hay revisores disponibles");
            comboRevisores.setEnabled(false);
        } else {
            revisores.forEach(comboRevisores::addItem);
            comboRevisores.setEnabled(true);
        }
    }

    public void mostrarTracks(List<String> tracks) {
        comboTracks.removeAllItems();
        tracks.forEach(comboTracks::addItem);
    }

    public void mostrarArticulos(List<String> articulos) {
        comboArticulos.removeAllItems();
        if (articulos.isEmpty()) {
            comboArticulos.addItem("No hay artículos disponibles");
            comboArticulos.setEnabled(false);
        } else {
            articulos.forEach(comboArticulos::addItem);
            comboArticulos.setEnabled(true);
        }
    }

    public JComboBox<String> getComboRevisoresPrincipales() { return comboRevisoresPrincipales; }
    public JComboBox<String> getComboRevisores() { return comboRevisores; }
    public JComboBox<String> getComboTracks() { return comboTracks; }
    public JComboBox<String> getComboArticulos() { return comboArticulos; }
    public JButton getBtnEnviarInvitacion() { return btnEnviarInvitacion; }
    public JFrame getFrame() { return frame; }
}