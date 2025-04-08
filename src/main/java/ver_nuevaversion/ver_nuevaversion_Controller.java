package ver_nuevaversion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

public class ver_nuevaversion_Controller {
	private ver_nuevaversion_Model model;
	private ver_nuevaversion_View view;
	private int idAutorLogueado;
	
	public ver_nuevaversion_Controller(ver_nuevaversion_Model m, ver_nuevaversion_View v) {
		this.model = m;
		this.view = v;
		revisarLogin();
	}

	private void revisarLogin() {
	    view.getBotonInicio().addActionListener(new ActionListener() {
	        
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            String idAutorText = view.getTF_IdAutor().getText();
	            
	            if (idAutorText.isEmpty()) {
	                JOptionPane.showMessageDialog(null, "Por favor, ingresa un ID de autor.");
	                return;
	            }
	            try {
	                int idAutor = Integer.parseInt(idAutorText);
	                if (model.isAutorValido(idAutor)) {
	                    idAutorLogueado = idAutor;
	                    JOptionPane.showMessageDialog(null, "Se ha registrado el autor con id: " + idAutorLogueado);
	                    initController();
	                } else {
	                    JOptionPane.showMessageDialog(null, "ID de autor no válido.");
	                }
	            } catch (NumberFormatException ex) {
	                JOptionPane.showMessageDialog(null, "El ID de autor debe ser un número válido.");
	            }
	        }
	    });

	}

	private void initController() {
	    view.getBotonOriginal().addActionListener(new ActionListener() {
	        
	        @Override
	        public void actionPerformed(ActionEvent e) {	
	        	view.getListaArticulos().setListData(model.getListaArticulosOriginales(idAutorLogueado).toArray(new String[0]));
	        }
	    });
	    
	    view.getBotonNueva().addActionListener(new ActionListener() {
	        
	        @Override
	        public void actionPerformed(ActionEvent e) {	
	        	view.getListaArticulos().setListData(model.getListaArticulosNuevos(idAutorLogueado).toArray(new String[0]));
	        }
	    });
	    
		view.getListaArticulos().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				String tituloseleccionado=view.getListaArticulos().getSelectedValue();
				
				view.getTA_Resumen().setText(model.getResumen(tituloseleccionado));
				view.getTF_PalabrasClave().setText(model.getPalabrasClave(tituloseleccionado));
				view.getTF_Fichero().setText(model.getFichero(tituloseleccionado));
				
			}
		});
		
	    view.getBotonOK().addActionListener(new ActionListener() {
	        
	        @Override
	        public void actionPerformed(ActionEvent e) {	
	        	String tituloseleccionado=view.getListaArticulos().getSelectedValue();
				String resumen=view.getTA_Resumen().getText();
				String palabras_clave=view.getTF_PalabrasClave().getText();
				String fichero=view.getTF_Fichero().getText();
			    
				if(model.antes_deadline(tituloseleccionado)) {
					model.actualizacion_nueva_version(palabras_clave,resumen,fichero);
				}else {
					JOptionPane.showMessageDialog(null, "Error, este artículo lo estás modificando después del deadline", "Error", JOptionPane.ERROR_MESSAGE);
				}
	        }
	    });
		
	}
	
}
