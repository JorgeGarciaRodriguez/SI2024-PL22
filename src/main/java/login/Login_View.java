package login;

import javax.swing.*;
import java.awt.*;

public class Login_View {

    private JFrame frame;
    private JTextField tfNombre;
    private JTextField tfCorreo;
    private JTextField tfOrganizacion;
    private JTextField tfGrupoInvs;
    private JButton btnLogin;

    // Constructor
    public Login_View() {
        initialize();
    }

    // Método para inicializar los componentes de la interfaz
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 400, 350);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        // Etiqueta de Nombre
        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(30, 50, 80, 25);
        frame.getContentPane().add(lblNombre);

        // Campo de texto para Nombre
        tfNombre = new JTextField();
        tfNombre.setBounds(120, 50, 150, 25);
        frame.getContentPane().add(tfNombre);

        // Etiqueta de Correo
        JLabel lblCorreo = new JLabel("Correo:");
        lblCorreo.setBounds(30, 90, 80, 25);
        frame.getContentPane().add(lblCorreo);

        // Campo de texto para Correo
        tfCorreo = new JTextField();
        tfCorreo.setBounds(120, 90, 150, 25);
        frame.getContentPane().add(tfCorreo);

        // Etiqueta de Organización
        JLabel lblOrganizacion = new JLabel("Organización:");
        lblOrganizacion.setBounds(30, 130, 100, 25);
        frame.getContentPane().add(lblOrganizacion);

        // Campo de texto para Organización
        tfOrganizacion = new JTextField();
        tfOrganizacion.setBounds(120, 130, 150, 25);
        frame.getContentPane().add(tfOrganizacion);

        // Etiqueta de Grupo de Inv.
        JLabel lblGrupoInvs = new JLabel("Grupo Inv.");
        lblGrupoInvs.setBounds(30, 170, 100, 25);
        frame.getContentPane().add(lblGrupoInvs);

        // Campo de texto para Grupo de Investigación
        tfGrupoInvs = new JTextField();
        tfGrupoInvs.setBounds(120, 170, 150, 25);
        frame.getContentPane().add(tfGrupoInvs);

        // Botón de Login
        btnLogin = new JButton("Login");
        btnLogin.setBounds(120, 210, 90, 25);
        frame.getContentPane().add(btnLogin);
    
    
    
    
    
    }

    
    // Métodos getter para obtener los valores introducidos
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
