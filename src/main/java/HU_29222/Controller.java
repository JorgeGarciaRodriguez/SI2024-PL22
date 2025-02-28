package HU_29222;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import giis.demo.util.SwingUtil;

public class Controller {
	private Model model;
	private View vista;
	
	public Controller(Model m, View v)	{
	    this.model = m;
	    this.vista = v;

        // Acceder al botón "Añadir" usando el getter
	    vista.getBtnNewButton().addActionListener(new ActionListener() 	{
	        @Override
	        public void actionPerformed(ActionEvent e)	{
	        	//Datos
	        	String nombre = vista.getTfNombre().getText();
	            String organizacion = vista.getTfOrganizacion().getText();
	            String grupo = vista.getTfGrupInvs().getText();
	            String correo = vista.getTfCorreo().getText();
	            DefaultTableModel tabla = vista.getTableModel();
	            //Consultas en la base de datos
	            model.asignacionPersona(nombre ,organizacion,grupo);
	            model.asignacionAutor(correo);
	            //Cuerpo del codigo
	            if (nombre.isEmpty() || organizacion.isEmpty() || grupo.isEmpty() || correo.isEmpty()) {
	                JOptionPane.showMessageDialog(vista.getFrame(), "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
	                return;
	            }else {
	            	tabla.addRow(new Object[]{nombre, correo, organizacion, grupo});
	            }
	            // Limpiar campos
	            vista.getTfNombre().setText("");
	            vista.getTfOrganizacion().setText("");
	            vista.getTfGrupInvs().setText("");
	            vista.getTfCorreo().setText("");
	            //Confirmacion
	            JOptionPane.showMessageDialog(vista.getFrame(), "Autor añadido correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
	        											}
	    																});
											}	
						}

