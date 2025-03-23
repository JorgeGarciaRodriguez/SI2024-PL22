package login;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import nuevo_envio.H29222_Controller;
import nuevo_envio.H29222_Model;
import nuevo_envio.H29222_View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login_Controller {

    private Login_Model model;
    private Login_View view;

    // Constructor del controlador, recibe el modelo y la vista
    public Login_Controller(Login_Model model, Login_View view) {
        this.model = model;
        this.view = view;

        // Manejo del evento de clic en el botón de login
        this.view.getBtnLogin().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Obtener los datos ingresados en la vista
                String nombre = view.getNombreLogin();
                String correo = view.getCorreoLogin();
                String organizacion = view.getOrganizacionLogin();
                String grupoInvs = view.getGrupoInvsLogin();

                // Validar los datos con el modelo
                if (model.validarLogin(nombre, correo, organizacion, grupoInvs)) {
                    // Si la validación es exitosa
                    JOptionPane.showMessageDialog(view.getFrame(), "Login exitoso.");

                    
    	            model.asignacionPersona(nombre ,organizacion,grupoInvs);
    	            model.asignacionAutor(correo);
                    // Abrir la vista H29222 y pasar al controlador correspondiente
                    openH29222View();
                    
                } else {
                    // Si los datos no son válidos
                    JOptionPane.showMessageDialog(view.getFrame(), "Por favor, completa todos los campos.");
                }
            }
        });
    }

    // Método para abrir la vista H29222
    private void openH29222View() {
        // Crear la vista de H29222
        
        
        // Crear el modelo y el controlador de H29222
        H29222_Model h29222Model = new H29222_Model();
        H29222_View h29222View = new H29222_View(h29222Model);
        H29222_Controller h29222Controller = new H29222_Controller(h29222Model, h29222View);
       
        // Obtener los datos del login
        String nombre = view.getNombreLogin();
        String correo = view.getCorreoLogin();
        String organizacion = view.getOrganizacionLogin();
        String grupoInvs = view.getGrupoInvsLogin();
        
        // Añadir los datos del login a la tabla de la vista H29222
        DefaultTableModel tabla = h29222View.getTableModel();
        tabla.addRow(new Object[]{nombre, correo, organizacion, grupoInvs});
        
        // Hacer visible la ventana H29222
        h29222View.getFrame().setVisible(true);

        // Cerrar la ventana de login
        view.getFrame().dispose(); // Cierra la ventana de login
    }
}

