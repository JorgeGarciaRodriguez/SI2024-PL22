package asignar_revisores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import giis.demo.util.SwingUtil;

public class HU29229_Controller {
	private HU29229_Model model;
	private Hu29229_View view;
	
	public HU29229_Controller(HU29229_Model m, Hu29229_View v) {
		this.model = m;
		this.view = v;
		//cargarArticulos();
		initController();
	}


	// Método para cargar los artículos en la vista
    /*public void cargarArticulos() {
        view.getListaArticulos().setListData(model.getListaArticulosDisponiblesArray().toArray(new String[0]));
    }*/
    
	public void initController() {
		view.getListaArticulos().addListSelectionListener(e -> SwingUtil.exceptionWrapper(() -> view.getListaArticulos()));
	
		view.getListaArticulos().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				SwingUtil.exceptionWrapper(() -> updateDetail());
			}
		});
		//POSIBLE IMPLEMENTACION DE ASIGNAR REVISORES CON BASE DE DATOS ACTUALIZADA
        view.getBotonAsignar().addActionListener(new ActionListener() {
 
            @Override
            public void actionPerformed(ActionEvent e) {
            	String tituloSeleccionado = view.getListaArticulos().getSelectedValue();
                String revisorSeleccionado = view.getListaRevisores().getSelectedValue();
                // Verificar el número de revisores ya asignados
                List<String> revisoresAsignados = model.getRevisoresAsignados(tituloSeleccionado);
                if (revisoresAsignados.size() >= 3) {
                    JOptionPane.showMessageDialog(view.getFrame(), "No se pueden asignar más de 3 revisores a un artículo.");
                    return;
                }
                //Verificar si has seleccionado un revisor para asignar y en ese caso asignar.
                if (revisorSeleccionado!=null) {
                    model.asignacion(model.getIdRevisor(revisorSeleccionado),model.getIdArticulo(tituloSeleccionado));
                    JOptionPane.showMessageDialog(view.getFrame(), "Nombre guardado en la BD.");
                    updateDetail();
                } else {
                    JOptionPane.showMessageDialog(view.getFrame(), "Seleccione un nombre.");
                }
            }
        });
        
        view.getBotonDesasignar().addActionListener(new ActionListener() {
        	 
            @Override
            public void actionPerformed(ActionEvent e) {
            	String tituloSeleccionado = view.getListaArticulos().getSelectedValue();
                String revisorAsignadoSeleccionado = view.getListaRevisoresAsignados().getSelectedValue();
                //Verificar si has seleccionado un revisor para asignar y en ese caso asignar.
                if (revisorAsignadoSeleccionado!=null) {
                    model.desasignacion(model.getIdRevision(tituloSeleccionado,model.getIdRevisor(revisorAsignadoSeleccionado)));
                    JOptionPane.showMessageDialog(view.getFrame(), "Revisor desasignado");
                    updateDetail();
                } else {
                    JOptionPane.showMessageDialog(view.getFrame(), "Seleccione un revisor");
                }
            }
        });
        
        view.getBotonSinRevisores().addActionListener(new ActionListener() {
       	 
            @Override
            public void actionPerformed(ActionEvent e) {
            	view.getListaArticulos().setListData(model.getListaArticulosSinRevisorArray().toArray(new String[0]));
            }
        });
        
        view.getBotonRevisores().addActionListener(new ActionListener() {
       	 
            @Override
            public void actionPerformed(ActionEvent e) {
            	view.getListaArticulos().setListData(model.getListaArticulosRevisorArray().toArray(new String[0]));
            }
        });
        
        view.getBotonRevisoresEspecializados().addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		String articuloSeleccionado = view.getListaArticulos().getSelectedValue();
        		if (articuloSeleccionado != null) {
        			view.getListaRevisores().setListData(model.getRevisoresEspecializados(articuloSeleccionado).toArray(new String[0]));
        		}
        	}
        });

        view.getListaRevisores().addListSelectionListener(e -> {
        	if (!e.getValueIsAdjusting()) {
        		String revisorSeleccionado = view.getListaRevisores().getSelectedValue();
        		if (revisorSeleccionado != null) {
        			List<String> palabrasClave = model.getPalabrasClaveRevisor(revisorSeleccionado);
        			view.getListaPalabrasClaveRevisor().setListData(palabrasClave.toArray(new String[0]));
        		}
        	}
        });
        
	}
	
	public void updateDetail() {
        String articuloSeleccionado = view.getListaArticulos().getSelectedValue();
        
        String palabrasClave = model.getPalabras_clave(articuloSeleccionado);
        String resumen = model.getResumen(articuloSeleccionado);
        
        view.getLabelPalabrasClave().setText(palabrasClave);
        view.getTextAreaResumen().setText(resumen);
        
        List<Object[]> lstAutores=model.getAutores(articuloSeleccionado);
        List<Object[]> lstGrupos=model.getAutores_Grupo(articuloSeleccionado);
        List<Object[]> lstOrganizacion=model.getAutores_Organizacion(articuloSeleccionado);
        
        int numFilas = lstAutores.size();
        Object[][] data = new Object[numFilas][3];
        
        for (int i = 0; i < numFilas; i++) {
            data[i][0] = lstAutores.get(i)[0]; // Tomamos el primer elemento de cada Object[]
            data[i][1] = lstGrupos.get(i)[0];
            data[i][2] = lstOrganizacion.get(i)[0];
        }
        
        String[] columnas = {"Autor", "Organizacion", "Grupo"};
        DefaultTableModel modelo=new DefaultTableModel(data, columnas);
        
        view.getTable().setModel(modelo);
        
        view.getListaRevisores().setListData(model.getRevisoresDisponibles(articuloSeleccionado).toArray(new String[0]));
        
        view.getListaRevisoresAsignados().setListData(model.getRevisoresAsignados(articuloSeleccionado).toArray(new String[0]));

    }

}