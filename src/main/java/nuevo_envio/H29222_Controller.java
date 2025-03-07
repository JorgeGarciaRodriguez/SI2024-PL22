package nuevo_envio;

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

public class H29222_Controller {
	private H29222_Model model;
	private H29222_View vista;
	
	public H29222_Controller(H29222_Model m, H29222_View v)	{
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
	 // Acceder al botón "Enviar" usando el getter 
	    vista.getBtnNewButton1().addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            // Obtener los valores de los campos de texto
	            String titulo = vista.getTfTitulo().getText().trim();
	            String palabrasClave = vista.getTfPalabrasClave().getText().trim();
	            String articulo = vista.getTfArticulo().getText().trim();
	            String resumen = vista.getTfResumen().getText().trim();
	            String autorCorreo = vista.getTfCorreo().getText().trim();
	            articulo+=articulo+"pdf";
	            // Validar que no haya campos vacíos
	            if (titulo.isEmpty() || palabrasClave.isEmpty() || articulo.isEmpty() || resumen.isEmpty() ) {
	                JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
	                return;
	            }

	            model.insertarArticulo(titulo,palabrasClave,resumen,articulo);
	            
	            
	            // Generar un número único para el artículo (por ejemplo, un timestamp)
	            int numeroArticulo = (int) (System.currentTimeMillis() % 100000);

	            // Mostrar el número en la etiqueta correspondiente
	            vista.getLb().setText("Nº Artículo: " + numeroArticulo);

	            // Mensaje de confirmación
	            JOptionPane.showMessageDialog(null, "Artículo registrado con éxito. Nº: " + numeroArticulo, "Éxito", JOptionPane.INFORMATION_MESSAGE);
	        }
	    });

											}	
						}

