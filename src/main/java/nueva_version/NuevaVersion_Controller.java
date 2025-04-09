package nueva_version;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

public class NuevaVersion_Controller {
	private NuevaVersion_Model model;
	private NuevaVersion_View view;
	private int idAutorLogueado;
	private int version;
	
	public NuevaVersion_Controller(NuevaVersion_Model m, NuevaVersion_View v) {
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
	        	version=0;
	        	view.getListaArticulos().setListData(model.getListaArticulosOriginales(idAutorLogueado).toArray(new String[0]));
	        }
	    });
	    
	    view.getBotonNuevo().addActionListener(new ActionListener() {
	        
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	version=1;
	        	view.getListaArticulos().setListData(model.getListaArticulosNuevos(idAutorLogueado).toArray(new String[0]));
	        }
	    });
		
		view.getListaArticulos().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				String tituloseleccionado=view.getListaArticulos().getSelectedValue();
				
				view.getTA_Resumen().setText(model.getResumen(tituloseleccionado,version));
				view.getTF_PalabrasClave().setText(model.getPalabrasClave(tituloseleccionado,version));
				view.getTF_Fichero().setText(model.getFichero(tituloseleccionado,version));
				
				int AutorEnvio=model.getAutorEnvio(tituloseleccionado);
				String AutorEnvioString=String.valueOf(AutorEnvio);
				view.getTF_IdAutorEnvio().setText(AutorEnvioString);
				if(idAutorLogueado==AutorEnvio && version==1) {
					view.getBotonOK().setEnabled(true);
					view.getBotonBorrar().setEnabled(true);
				}else if(idAutorLogueado==AutorEnvio && version==0) {
					view.getBotonOK().setEnabled(true);
					view.getBotonBorrar().setEnabled(false);
				}else {
					view.getBotonOK().setEnabled(false);
					view.getBotonBorrar().setEnabled(false);
				}
			}
		});
		
	    view.getBotonOK().addActionListener(new ActionListener() {
	        
	        @Override
	        public void actionPerformed(ActionEvent e) {	
	        	String tituloseleccionado=view.getListaArticulos().getSelectedValue();
				String resumen=view.getTA_Resumen().getText();
				String palabras_clave=view.getTF_PalabrasClave().getText();
				String fichero=view.getTF_Fichero().getText();
			    
				if(version==0 && model.noTieneVersion1(tituloseleccionado) && model.antes_deadline(tituloseleccionado)) {
					model.nueva_version(palabras_clave,resumen,fichero,model.getID(tituloseleccionado));
					model.nuevo_envio(idAutorLogueado,model.ultimoID()-1);
					JOptionPane.showMessageDialog(null, "Nueva version del articulo creada", null, JOptionPane.INFORMATION_MESSAGE);
				}else if(version==1 && model.antes_deadline(tituloseleccionado)){
					model.actualizacion_nueva_version(fichero,palabras_clave,resumen,tituloseleccionado);
					JOptionPane.showMessageDialog(null, "Nueva version actualizada", null, JOptionPane.INFORMATION_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(null, "Error, este artículo ya tiene nueva versión o lo estás modificando después del deadline", "Error", JOptionPane.ERROR_MESSAGE);
				}
	        }
	    });
	    
	    view.getBotonBorrar().addActionListener(new ActionListener() {
	        
	        @Override
	        public void actionPerformed(ActionEvent e) {	
	        	String tituloseleccionado=view.getListaArticulos().getSelectedValue();
	        	model.borrar_nueva_version_relacion(tituloseleccionado);
	        	model.borrar_nueva_version_articulo(tituloseleccionado);
	        	JOptionPane.showMessageDialog(null, "Nueva version borrada", null, JOptionPane.INFORMATION_MESSAGE);
	        }
	    });
		
	}
	
}
