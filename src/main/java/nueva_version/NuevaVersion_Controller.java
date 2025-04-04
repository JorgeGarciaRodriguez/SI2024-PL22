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
		System.out.print(idAutorLogueado);
    	view.getListaArticulos().setListData(model.getListaArticulosAceptadosArray(idAutorLogueado).toArray(new String[0]));
		
		view.getListaArticulos().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				String tituloseleccionado=view.getListaArticulos().getSelectedValue();
				
				view.getTA_Resumen().setText(model.getResumen(tituloseleccionado));
				view.getTF_PalabrasClave().setText(model.getPalabrasClave(tituloseleccionado));
				view.getTF_Fichero().setText(model.getFichero(tituloseleccionado));
				
				int AutorEnvio=model.getAutorEnvio(tituloseleccionado);
				String AutorEnvioString=String.valueOf(AutorEnvio);
				view.getTF_IdAutorEnvio().setText(AutorEnvioString);
				if(idAutorLogueado==AutorEnvio) {
					view.getTA_Resumen().setEnabled(true);
					view.getTF_PalabrasClave().setEnabled(true);
					view.getTF_Fichero().setEnabled(true);
				}
			}
		});
	}
	
}
