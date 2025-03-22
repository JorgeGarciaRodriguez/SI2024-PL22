package Discusiones;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JOptionPane;


public class HU29511_Controller {
	private HU29511_Model model;
	private HU29511_View view;

	public HU29511_Controller(HU29511_Model m, HU29511_View v) {
		this.model = m;
		this.view = v;
		initController();
	}

	// Método para cargar los artículos que sean dudosos en la vista
	public void cargarArticulosDudosos() {
		view.getListArticulos().setListData(model.getListaArticulosDudosos().toArray(new String[0]));
	}
	/* Metodo que al pulsar el boton de discutir un articulo
	   actualiza la base de datos poniendolo como dudoso y genera 
	   una notificacion
	*/
	public void discutirArticulo() {
		view.getBtnDiscutir().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Método que se ejecuta cuando el botón es presionado
				String titulo=view.getListArticulos().getSelectedValue();
				if(titulo==null) {
					JOptionPane.showMessageDialog(view.getFrame(), "Debe seleccionar un articulo primero", "Error", JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					int id = model.getIdArticulo(titulo);
					model.insertDiscutirArticulo(id);
					Notificar(id);
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
	/*
	 Genera una notificacion para los revisores acerca del articulo
	 que se ha marcado como dudoso
	 */
	public void Notificar(int id_Articulo) {
		List<Integer> id_Revisores = model.getIdRevisores(id_Articulo);
		int id_Discusion = model.getIdDiscusion(id_Articulo);
		for (Integer num : id_Revisores) {
		    model.insertNotificarRevisores(num,id_Discusion);
		}
	}
	
	public void initController() {
		cargarArticulosDudosos();
		discutirArticulo();
		regresar();
	}

}
