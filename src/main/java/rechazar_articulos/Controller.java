package rechazar_articulos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JOptionPane;

import giis.demo.util.SwingUtil;

public class Controller {
	private Model model;
	private View view;

	public Controller(Model m, View v) {
		this.model = m;
		this.view = v;
		initController();
	}
	
	public void initController() {
		view.getListaArticulos().setListData(model.getListaArticulosPendientesArray().toArray(new String[0]));
		
		view.getListaArticulos().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				SwingUtil.exceptionWrapper(() -> updateDetail());
			}
		});
		
		view.getBotonRechazoAutomatico().addActionListener(new ActionListener() {
			 
            @Override
            public void actionPerformed(ActionEvent e) {
            	List<String> ListaArticulosPendientes=model.getListaArticulosPendientesArray();
            	int valoracion=0;
            	for(String titulo:ListaArticulosPendientes) {
            		valoracion=model.getValoracionGlobalInt(titulo);
            		if(valoracion==-2) {
            			model.rechazar(titulo);
            		}
            	}
            	view.getListaArticulos().setListData(model.getListaArticulosPendientesArray().toArray(new String[0]));
            }
        });
		
/*		view.getBotonAceptacionConCambios().addActionListener(new ActionListener() {
			 
            @Override
            public void actionPerformed(ActionEvent e) {
            	List<String> ListaArticulosPendientes=model.getListaArticulosPendientesArray();
            	int valoracion=0;
            	for(String titulo:ListaArticulosPendientes) {
            		valoracion=model.getValoracionGlobalInt(titulo);
            		if(valoracion==2) {
            			model.aceptar_concambios(titulo);
            		}else if(valoracion==1) {
            			if() {
            				model.aceptar_concambios(titulo);
            			}
            		}
            	}
            	view.getListaArticulos().setListData(model.getListaArticulosPendientesArray().toArray(new String[0]));
            }
        });
        */
	}
	
	public void updateDetail() {
		String articuloSeleccionado = view.getListaArticulos().getSelectedValue();
		
		String valoracion = model.getValoracionGlobal(articuloSeleccionado);
		view.getLabelValoracion().setText(valoracion);
	}
}
