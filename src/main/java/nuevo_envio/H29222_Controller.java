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
	            // 1. Obtener los valores de los campos de texto
	            String titulo = vista.getTfTitulo().getText().trim();
	            String palabrasClave = vista.getTfPalabrasClave().getText().trim();
	            String articulo = vista.getTfArticulo().getText().trim();
	            String resumen = vista.getTfResumen().getText().trim();
	            articulo += ".pdf"; // Agregar la extensión de archivo

	            // 2. Validar que no haya campos vacíos
	            if (titulo.isEmpty() || palabrasClave.isEmpty() || articulo.isEmpty() || resumen.isEmpty()) {
	                JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
	                return;
	            }

	            // 3. Insertar el artículo en la base de datos
	            model.insertarArticulo(titulo, palabrasClave, resumen, articulo);

	            // 4. Obtener el ID del artículo recién insertado
	            int idArticulo = model.obtenerIdArticuloPorTitulo(titulo);
	            if (idArticulo == -1) {
	                JOptionPane.showMessageDialog(null, "Error al obtener el ID del artículo.", "Error", JOptionPane.ERROR_MESSAGE);
	                return;
	            }

	            // 5. Obtener los autores de la tabla
	            DefaultTableModel tablaModel = vista.getTableModel();
	            int numFilas = tablaModel.getRowCount();
	            
	            if (numFilas == 0) {
	                JOptionPane.showMessageDialog(null, "Debe añadir al menos un autor.", "Error", JOptionPane.ERROR_MESSAGE);
	                return;
	            }

	            for (int i = 0; i < numFilas; i++) {
	                String correoAutor = (String) tablaModel.getValueAt(i, 1); // Obtener el correo del autor
	                int idAutor = model.obtenerIdAutorPorCorreo(correoAutor);

	                if (idAutor != -1) {
	                    model.asignarAutorArticulo(idArticulo, idAutor);
	                } else {
	                    JOptionPane.showMessageDialog(null, "No se encontró el autor con correo: " + correoAutor, "Error", JOptionPane.ERROR_MESSAGE);
	                }
	            }

	            // 6. Generar un número único para el artículo (ejemplo: timestamp)
	            int numeroArticulo = (int) (System.currentTimeMillis() % 100000);
	            vista.getLb().setText("Nº Artículo: " + numeroArticulo);

	            // 7. Mensaje de confirmación
	            JOptionPane.showMessageDialog(null, "Artículo registrado con éxito. Nº: " + numeroArticulo, "Éxito", JOptionPane.INFORMATION_MESSAGE);
	        }
	    });


											}	
						}

