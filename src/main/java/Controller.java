import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import giis.demo.util.SwingUtil;

public class Controller {
	private Model model;
	private View view;

	public Controller(Model m, View v) {
		this.model = m;
		this.view = v;
		initController();
	}


	// Método para cargar los artículos en la vista
	public void cargarArticulosSinDecision() {
		view.getListArticulos().setListData(model.getListaArticulosSinDecisionArray().toArray(new String[0]));

	}
	// Método para cargar los artículos en la vista
	public void cargarArticulosValMay3() {
		view.getListArticulos().setListData(model.getListaArticulosConValMay3().toArray(new String[0]));


	}
	public void cargarListaArticulos() {
		view.getBtnListaCompleta().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				view.getListRevisores().setListData(new String[0]);
				view.getLblNombre_Articulo().setText("");
				view.getLblNivelExperto().setText("");
				view.getLblValoracionFinal().setText("");
				view.getTextAreaComentAutor().setText("");
				view.getTextAreaComentCoord().setText("");
				cargarArticulosSinDecision();
				cargarRevisores();
				view.getBtnAceptaTodos().setEnabled(false);

			}
		});
	}
	public void cargarListaAutomatica() {
		view.getBtnListaAutomatica().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				view.getListRevisores().setListData(new String[0]);
				view.getLblNombre_Articulo().setText("");
				view.getLblNivelExperto().setText("");
				view.getLblValoracionFinal().setText("");
				view.getTextAreaComentAutor().setText("");
				view.getTextAreaComentCoord().setText("");
				cargarArticulosValMay3();
				cargarRevisores();
				view.getBtnAceptaTodos().setEnabled(true);
			}
		});
	}
	public void aceptarTodosLosArticulos() {
		view.getBtnAceptaTodos().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Método que se ejecuta cuando el botón es presionado
				String titulo;
				if(view.getListArticulos().getModel().getSize()==0) {
					JOptionPane.showMessageDialog(view.getFrame(), "No hay articulos", "Error", JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					for(int i=0;i<view.getListArticulos().getModel().getSize();i++) {
						titulo=view.getListArticulos().getModel().getElementAt(i);
						model.updateAceptaArticulo(
								titulo,
								model.getPalabras_clave(titulo),
								model.getResumen(titulo),
								model.getFichero(titulo),
								model.getFecha(titulo),
								model.getDecisionFinal(titulo),
								model.getIdArticulo(titulo));
					}
				}
			}
		});
	}
	// Metodo para cargar en la Jlist Revisores todos los revisores asignados a un articulo en concreto y cambiar la label de nombre articulo
	public void cargarRevisores() {
		view.getListArticulos().addListSelectionListener(e -> {
			if (!e.getValueIsAdjusting()) {
				String tituloSeleccionado = view.getListArticulos().getSelectedValue();
				view.getLblNombre_Articulo().setText(tituloSeleccionado);
				if (tituloSeleccionado != null) {
					//Limpio la lista
					view.getListRevisores().removeAll();
					// Obtener revisores del artículo
					view.getListRevisores().setListData(model.getRevisoresDeArticulo(tituloSeleccionado).toArray(new String[0]));
					//Obtengo la decision de cada revisor y las sumo para obtener la valoracion final
					int valoracionFinal = calcularValoracionFinal(tituloSeleccionado);

					view.getLblValoracionFinal().setText(String.valueOf(valoracionFinal));
				}
			}
		});

	}

	public void configurarSeleccionRevisores() {
		view.getListRevisores().addListSelectionListener(e -> {
			if (!e.getValueIsAdjusting()) {
				String tituloSeleccionado = view.getListArticulos().getSelectedValue();
				String revisorSeleccionado = view.getListRevisores().getSelectedValue();
				if(revisorSeleccionado != null) {
					view.getLblNivelExperto().setText(model.getNivelExperto(revisorSeleccionado,tituloSeleccionado));
					view.getTextAreaComentAutor().setText(model.getComentarioAutor(revisorSeleccionado, tituloSeleccionado));
					view.getTextAreaComentCoord().setText(model.getComentarioCoordinador(revisorSeleccionado, tituloSeleccionado));

				}
			}
		});
	}
	public void aceptarArticulo() {
		view.getBtnAceptar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Método que se ejecuta cuando el botón es presionado
				String titulo=view.getListArticulos().getSelectedValue();
				if(titulo==null) {
					JOptionPane.showMessageDialog(view.getFrame(), "Debe seleccionar un articulo primero", "Error", JOptionPane.INFORMATION_MESSAGE);
				}
				else model.updateAceptaArticulo(
						titulo,
						model.getPalabras_clave(titulo),
						model.getResumen(titulo),
						model.getFichero(titulo),
						model.getFecha(titulo),
						model.getDecisionFinal(titulo),
						model.getIdArticulo(titulo));
			}
		});

	}
	public void denegarArticulo() {
		view.getBtnDenegar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Método que se ejecuta cuando el botón es presionado
				String titulo=view.getListArticulos().getSelectedValue();
				if(titulo==null) {
					JOptionPane.showMessageDialog(view.getFrame(), "Debe seleccionar un articulo primero", "Error", JOptionPane.INFORMATION_MESSAGE);
				}
				else model.updateDenegarArticulo(
						titulo,
						model.getPalabras_clave(titulo),
						model.getResumen(titulo),
						model.getFichero(titulo),
						model.getFecha(titulo),
						model.getDecisionFinal(titulo),
						model.getIdArticulo(titulo));
			}
		});

	}
	public int calcularValoracionFinal(String titulo) {
		int valoracionFinal = 0;
		for(int i=0;i<view.getListRevisores().getModel().getSize();i++) {
			valoracionFinal+= model.getDecisionArticulo(view.getListRevisores().getModel().getElementAt(i), titulo);

		}
		return valoracionFinal;
	}
	public void initController() {
		aceptarTodosLosArticulos();
		cargarListaArticulos();
		cargarListaAutomatica();
		configurarSeleccionRevisores();
		aceptarArticulo();
		denegarArticulo();
	}

}