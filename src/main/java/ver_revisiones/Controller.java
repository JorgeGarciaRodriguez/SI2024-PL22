package ver_revisiones;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

import giis.demo.util.SwingUtil;

public class Controller {
	private Model model;
	private View view;
	private int idRevisorLogueado;
		
	public Controller(Model m, View v) {
		this.model = m;
		this.view = v;
		revisarLogin();
		//initController();
	}

	private void revisarLogin() {
	    view.getBotonInicioSesion().addActionListener(new ActionListener() {
	        
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            String idRevisorText = view.getTF_IdRevisor().getText();
	            
	            if (idRevisorText.isEmpty()) {
	                JOptionPane.showMessageDialog(null, "Por favor, ingresa un ID de revisor.");
	                return;
	            }
	            try {
	                int idRevisor = Integer.parseInt(idRevisorText);
	                if (model.isRevisorValido(idRevisor)) {
	                    idRevisorLogueado = idRevisor;
	                    JOptionPane.showMessageDialog(null, "Se ha registrado el revisor con id: " + idRevisorLogueado);
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

        view.getBotonPendientes().addActionListener(new ActionListener() {
          	 
            @Override
            public void actionPerformed(ActionEvent e) {
            	view.getListaArticulos().setListData(model.getListaArticulosPendientesArray(idRevisorLogueado).toArray(new String[0]));
            }
        });
        
        view.getBotonRevisados().addActionListener(new ActionListener() {
         	 
            @Override
            public void actionPerformed(ActionEvent e) {
            	view.getListaArticulos().setListData(model.getListaArticulosRevisadosArray(idRevisorLogueado).toArray(new String[0]));
            }
        });
        
		view.getListaArticulos().addListSelectionListener(e -> SwingUtil.exceptionWrapper(() -> view.getListaArticulos()));
		
		view.getListaArticulos().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				SwingUtil.exceptionWrapper(() -> updateDetail());
			}
		});
	
	
	}
	
	private void updateDetail() {
		String tituloseleccionado=view.getListaArticulos().getSelectedValue();
		String comentario=model.getComentarioRevisor(tituloseleccionado, idRevisorLogueado);
		view.getTF_ComentarioRevisor().setText(comentario);
		
		int decision=model.getDecision(tituloseleccionado, idRevisorLogueado);
		int index = -1;
		switch (decision) {
		    case 2:
		        index = 0; // "Aceptado fuerte"
		        break;
		    case 1:
		        index = 1; // "Aceptado debil"
		        break;
		    case -1:
		        index = 2; // "Rechazo debil"
		        break;
		    case -2:
		        index = 3; // "Rechazo fuerte"
		        break;
		    default:
		        // En caso de que no se encuentre un valor válido de decisión
		        System.out.println("Decisión no válida");
		        break;
		}
		view.getcbDecision().setSelectedIndex(index);
	}
}
