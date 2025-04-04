package nueva_version;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
	                    JOptionPane.showMessageDialog(null, "Se ha registrado el revisor con id: " + idAutorLogueado);
	                } else {
	                    JOptionPane.showMessageDialog(null, "ID de revisor no válido.");
	                }
	            } catch (NumberFormatException ex) {
	                JOptionPane.showMessageDialog(null, "El ID de revisor debe ser un número válido.");
	            }
	        }
	    });
	    

	    initController();
	}

	private void initController() {
		
	}
}
