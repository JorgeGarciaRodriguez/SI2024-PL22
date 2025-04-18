package modificar_articulos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;



public class Modificar_articulos_Controller {
	private Modificar_articulos_Model model;
	private Modificar_articulos_View view;
	private Cambios_View cambios;

	public Modificar_articulos_Controller(Modificar_articulos_Model m, Modificar_articulos_View v, Cambios_View c) {
		this.model = m;
		this.view = v;
		this.cambios = c;
		initController();
	}

	// Método para cargar los artículos que sean modificables en la vista
	public void cargarArticulosModificables() {
		view.getListArticulos().setListData(model.getListaArticulosModificables().toArray(new String[0]));
	}


	public void modificarArticulo() {
		view.getBtnModificar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Método que se ejecuta cuando el botón es presionado
				String titulo=view.getListArticulos().getSelectedValue();
				if(titulo==null) {
					JOptionPane.showMessageDialog(view.getFrame(), "Debe seleccionar un articulo primero", "Error", JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					int id = model.getIdArticulo(titulo);
					cargarDatos(id);
					cambios.getFrame().setVisible(true);
				}
			}
		});

	}
	public void regresar() {
		view.getBtnRegresar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				view.getFrame().setVisible(false);
			}
		});

	}

	public void cargarDatos(int id) {
		cambios.getTfResumen().setText(model.getResumen(id));
		cambios.getTfPalabrasClave().setText(model.getPalabrasClave(id));
		cambios.getTfArticulo().setText(model.getFichero(id));
	}

	public void añadirAutores() {
		// Acceder al botón "Añadir" usando el getter
		cambios.getBtnNewButton().addActionListener(new ActionListener() 	{
			@Override
			public void actionPerformed(ActionEvent e)	{
				//Datos
				String nombre = cambios.getTfNombre().getText();
				String organizacion = cambios.getTfOrganizacion().getText();
				String grupo = cambios.getTfGrupInvs().getText();
				String correo = cambios.getTfCorreo().getText();
				DefaultTableModel tabla = cambios.getTableModel();
				//Consultas en la base de datos
				model.asignacionPersona(nombre ,organizacion,grupo);
				model.asignacionAutor(correo);
				//Cuerpo del codigo
				if (nombre.isEmpty() || organizacion.isEmpty() || grupo.isEmpty() || correo.isEmpty()) {
					JOptionPane.showMessageDialog(cambios.getFrame(), "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}else {
					tabla.addRow(new Object[]{nombre, correo, organizacion, grupo});
				}
				// Limpiar campos
				cambios.getTfNombre().setText("");
				cambios.getTfOrganizacion().setText("");
				cambios.getTfGrupInvs().setText("");
				cambios.getTfCorreo().setText("");
				//Confirmacion
				JOptionPane.showMessageDialog(cambios.getFrame(), "Autor añadido correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
			}
		});
	}

	public void Envio() {
		cambios.getBtnNewButton1().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 1. Obtener los valores de los campos de texto
				String titulo = cambios.getTfTitulo().getText().trim();
				String palabrasClave = cambios.getTfPalabrasClave().getText().trim();
				String articulo = cambios.getTfArticulo().getText().trim();
				String resumen = cambios.getTfResumen().getText().trim();
				articulo += ".pdf"; // Agregar la extensión de archivo

				// 2. Validar que no haya campos vacíos
				if (titulo.isEmpty() || palabrasClave.isEmpty() || articulo.isEmpty() || resumen.isEmpty()) {
					JOptionPane.showMessageDialog(cambios.getFrame(), "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}

				// 3. Validar que se haya seleccionado un track
				String nombreTrack = cambios.getListaTracks().getSelectedValue();
				if (nombreTrack == null) {
					JOptionPane.showMessageDialog(cambios.getFrame(), "Debe seleccionar un track.", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}

				// 4. Validar que se haya seleccionado al menos una palabra clave
				List<String> palabrasClaveSeleccionadas = cambios.getListaPalabrasClave().getSelectedValuesList();
				if (palabrasClaveSeleccionadas.isEmpty()) {
					JOptionPane.showMessageDialog(cambios.getFrame(), "Debe seleccionar al menos una palabra clave.", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}

				// 5. Si todas las validaciones pasan, insertar el artículo en la base de datos
				model.modificarArticulo(titulo, palabrasClave, articulo, resumen);

				// 6. Obtener el ID del artículo recién insertado
				int idArticulo = model.obtenerIdArticuloPorTitulo(titulo);
				if (idArticulo == -1) {
					JOptionPane.showMessageDialog(cambios.getFrame(), "Error al obtener el ID del artículo.", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}

				// 7. Obtener el ID del track seleccionado
				int idTrack = model.obtenerIdTrackPorNombre(nombreTrack);

				// 8. Enviar el artículo al track
				model.actualizarArticuloATrack(idArticulo, idTrack, palabrasClaveSeleccionadas);

				// 9. Obtener los autores de la tabla
				DefaultTableModel tablaModel = cambios.getTableModel();
				int numFilas = tablaModel.getRowCount();

				if (numFilas == 0) {
					JOptionPane.showMessageDialog(cambios.getFrame(), "Debe añadir al menos un autor.", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}

				for (int i = 0; i < numFilas; i++) {
					String correoAutor = (String) tablaModel.getValueAt(i, 1); // Obtener el correo del autor
					int idAutor = model.obtenerIdAutorPorCorreo(correoAutor);

					if (idAutor != -1) {
						//model.asignarAutorArticulo(idAutor, idArticulo);
					} else {
						JOptionPane.showMessageDialog(cambios.getFrame(), "No se encontró el autor con correo: " + correoAutor, "Error", JOptionPane.ERROR_MESSAGE);
					}
				}

				// 10. Generar un número único para el artículo (ejemplo: timestamp)
				int numeroArticulo = (int) (System.currentTimeMillis() % 100000);
				cambios.getLb().setText("Nº Artículo: " + numeroArticulo);

				// 11. Mensaje de confirmación
				JOptionPane.showMessageDialog(cambios.getFrame(), "Artículo enviado correctamente al track.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
			}
		});
	}

	public void initController() {
		cargarArticulosModificables();
		modificarArticulo();
		regresar();

	}

}
