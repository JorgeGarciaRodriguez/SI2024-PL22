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
    private JButton btnEnviarInvitacion;

    public subrevisor_View() {
        initialize();
    }

    private void initialize() {
        // Configuración del frame principal
        frame = new JFrame("Gestión de Subrevisores");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(500, 350); // Tamaño más adecuado
        frame.setLocationRelativeTo(null); // Centrar en pantalla
        
        // Panel principal con márgenes
        JPanel mainPanel = new JPanel();
        mainPanel.setBorder(new EmptyBorder(15, 15, 15, 15));
        mainPanel.setLayout(new BorderLayout(10, 10));
        frame.getContentPane().add(mainPanel);
        
        // Panel de título
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(70, 130, 180));
        JLabel titleLabel = new JLabel("ASIGNACIÓN DE SUBREVISORES");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        titleLabel.setForeground(Color.WHITE);
        titlePanel.add(titleLabel);
        mainPanel.add(titlePanel, BorderLayout.NORTH);
        
        // Panel de formulario
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Configuración común para combobox
        Dimension comboSize = new Dimension(300, 30);
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
        
        // Subrevisores disponibles
        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel lblSubrevisores = createLabel("Subrevisores disponibles:");
        formPanel.add(lblSubrevisores, gbc);
        
        gbc.gridx = 1;
        comboRevisores = new JComboBox<>();
        comboRevisores.setFont(comboFont);
        comboRevisores.setPreferredSize(comboSize);
        comboRevisores.setEnabled(false);
        formPanel.add(comboRevisores, gbc);
        
        // Track
        gbc.gridx = 0;
        gbc.gridy = 2;
        JLabel lblTrack = createLabel("Track:");
        formPanel.add(lblTrack, gbc);
        
        gbc.gridx = 1;
        comboTracks = new JComboBox<>();
        comboTracks.setFont(comboFont);
        comboTracks.setPreferredSize(comboSize);
        comboTracks.setEnabled(false);
        formPanel.add(comboTracks, gbc);
        
        // Botón Enviar Invitación
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.CENTER;
        btnEnviarInvitacion = createButton("Enviar Invitación", new Color(34, 139, 34));
        btnEnviarInvitacion.setEnabled(false);
        formPanel.add(btnEnviarInvitacion, gbc);
        
        mainPanel.add(formPanel, BorderLayout.CENTER);
    }

    // Métodos auxiliares para crear componentes con estilo consistente
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

    // Getters
    public JComboBox<String> getComboRevisoresPrincipales() { return comboRevisoresPrincipales; }
    public JComboBox<String> getComboRevisores() { return comboRevisores; }
    public JComboBox<String> getComboTracks() { return comboTracks; }
    public JButton getBtnEnviarInvitacion() { return btnEnviarInvitacion; }
    public JFrame getFrame() { return frame; }
}