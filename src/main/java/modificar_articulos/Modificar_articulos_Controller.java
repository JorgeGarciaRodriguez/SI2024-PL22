package modificar_articulos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JOptionPane;



public class Modificar_articulos_Controller {
	private Modificar_articulos_Model model;
	private Modificar_articulos_View view;

	public Modificar_articulos_Controller(Modificar_articulos_Model m, Modificar_articulos_View v) {
		this.model = m;
		this.view = v;
		initController();
	}

	// Método para cargar los artículos que sean modificables en la vista
	public void cargarArticulosModificables() {
		view.getListArticulos().setListData(model.getListaArticulosModificables().toArray(new String[0]));
	}
	/* Metodo que al pulsar el boton de discutir un articulo
	   actualiza la base de datos poniendolo como dudoso y genera 
	   una notificacion
	 */

	public void modificarArticulo() {
		view.getBtnModificar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Método que se ejecuta cuando el botón es presionado
				view.getJTAresumen().setText(view.getJTAresumen().getText());
				view.getJTFfichero().setText(view.getJTFfichero().getText());
				view.getJTFpalabras_clave().setText(view.getJTFpalabras_clave().getText());
				String titulo=view.getListArticulos().getSelectedValue();
				if(ComprobacionCampos() == false) {
					JOptionPane.showMessageDialog(view.getFrame(), "Debe rellenar todos los campos acerca del articulo", "Error", JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					int id = model.getIdArticulo(titulo);
					model.updateArticulo(id, view.getJTFpalabras_clave().getText(), view.getJTFfichero().getText(), view.getJTAresumen().getText());
					borrarCampos();
				}
			}
		});

	}
	public void regresar() {
		view.getBtnRegresar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				view.getFrame().setVisible(false);
				borrarCampos();
			}
		});

	}
	public void borrarCampos() {
		view.getJTAresumen().setText("");
		view.getJTFidentificacion().setText("");
		view.getJTFfichero().setText("");
		view.getJTFpalabras_clave().setText("");
	}
	public void identificacion() {
		view.getBtnIdentificarse().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String titulo = view.getListArticulos().getSelectedValue();
				String autentificacion = view.getJTFidentificacion().getText();
				
				ComprobarAutor(titulo,autentificacion);
			}
		});
	}
	public void ComprobarAutor(String titulo, String autentificacion) {
	    List<String> autores = model.getAutores(titulo);

	    for (String autor : autores) {
	        if (autentificacion.equals(autor)) correcto();
	    }
		
	}
	public void correcto() {
		view.getJTAresumen().setEnabled(true);
		view.getJTFfichero().setEnabled(true);
		view.getJTFpalabras_clave().setEnabled(true);
	}
	public boolean ComprobacionCampos() {
		if(view.getJTAresumen().getText().equals("") ||
		   view.getJTFfichero().getText().equals("")||
		   view.getJTFpalabras_clave().equals("")) return false; 
		
		else return true;
	}
	/*
	 Genera una notificacion para los revisores acerca del articulo
	 que se ha marcado como dudoso
	 */


	public void initController() {
		cargarArticulosModificables();
		modificarArticulo();
		identificacion();
		regresar();
	}

}
