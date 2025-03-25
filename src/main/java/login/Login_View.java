package login;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;


public class Login_View {

    private JFrame frame;
    private JTextField tfNombre;
    private JTextField tfCorreo;
    private JTextField tfOrganizacion;
    private JTextField tfGrupoInvs;
    private JButton btnLogin;

    public Login_View() {
        initialize();
    }

    private void initialize() {
        // Configuración del frame principal
        frame = new JFrame("Registro de Autor");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(650, 500); // Ventana más grande para campos más largos
        frame.setLocationRelativeTo(null);
        
        // Panel principal con márgenes
        JPanel mainPanel = new JPanel();
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        mainPanel.setLayout(new BorderLayout(10, 10));
        frame.getContentPane().add(mainPanel);
        
        // Panel de título
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(70, 130, 180));
        JLabel titleLabel = new JLabel("REGISTRO DE AUTOR");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        titleLabel.setForeground(Color.WHITE);
        titlePanel.add(titleLabel);
        mainPanel.add(titlePanel, BorderLayout.NORTH);
        
        // Panel de formulario (ahora con GridBagLayout para mejor control)
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;
        
        // Configuración para campos de texto EXTRA LARGOS
        Dimension fieldSize = new Dimension(350, 30); // Campos mucho más largos
        
        // Campo Nombre
        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel lblNombre = createLabel("Nombre:");
        formPanel.add(lblNombre, gbc);
        
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        tfNombre = createTextField();
        tfNombre.setPreferredSize(fieldSize);
        formPanel.add(tfNombre, gbc);
        
        // Campo Correo
        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel lblCorreo = createLabel("Correo:");
        formPanel.add(lblCorreo, gbc);
        
        gbc.gridx = 1;
        tfCorreo = createTextField();
        tfCorreo.setPreferredSize(fieldSize);
        formPanel.add(tfCorreo, gbc);
        
        // Campo Organización
        gbc.gridx = 0;
        gbc.gridy = 2;
        JLabel lblOrganizacion = createLabel("Organización:");
        formPanel.add(lblOrganizacion, gbc);
        
        gbc.gridx = 1;
        tfOrganizacion = createTextField();
        tfOrganizacion.setPreferredSize(fieldSize);
        formPanel.add(tfOrganizacion, gbc);
        
        // Campo Grupo de Investigación
        gbc.gridx = 0;
        gbc.gridy = 3;
        JLabel lblGrupoInvs = createLabel("Grupo Inv.:");
        formPanel.add(lblGrupoInvs, gbc);
        
        gbc.gridx = 1;
        tfGrupoInvs = createTextField();
        tfGrupoInvs.setPreferredSize(fieldSize);
        formPanel.add(tfGrupoInvs, gbc);
        
        // Botón Login (centrado y con buen tamaño)
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.CENTER;
        btnLogin = createButton("ACCEDER", new Color(34, 139, 34));
        btnLogin.setPreferredSize(new Dimension(180, 35));
        formPanel.add(btnLogin, gbc);
        
        mainPanel.add(formPanel, BorderLayout.CENTER);
    }

    // Métodos auxiliares para mantener el estilo consistente
    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        return label;
    }

    private JTextField createTextField() {
        JTextField textField = new JTextField();
        textField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        textField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200)),
            BorderFactory.createEmptyBorder(5, 8, 5, 8)));
        return textField;
    }

    private JButton createButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(8, 20, 8, 20));
        return button;
    }

    // Métodos getter
    public String getNombreLogin() {
        return tfNombre.getText();
    }

    public String getCorreoLogin() {
        return tfCorreo.getText();
    }

    public String getOrganizacionLogin() {
        return tfOrganizacion.getText();
    }

    public String getGrupoInvsLogin() {
        return tfGrupoInvs.getText();
    }

    public JButton getBtnLogin() {
        return btnLogin;
    }

    public JFrame getFrame() {
        return frame;
    }
}