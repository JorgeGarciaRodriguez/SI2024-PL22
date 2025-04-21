package evaluacion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;

public class Evaluacion_Controller {
    private Evaluacion_Model model;
    private Evaluacion_View vista;

    public Evaluacion_Controller(Evaluacion_Model m, Evaluacion_View v) {
        this.model = m;
        this.vista = v;

        // Cargar los revisores disponibles
        cargarRevisores();

        vista.getComboRevisores().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener el revisor seleccionado
                String revisorSeleccionado = (String) vista.getComboRevisores().getSelectedItem();
                if (revisorSeleccionado != null) {
                    // Extraer el idRevisor del texto seleccionado (asumiendo que el formato es "id - nombre")
                    int idRevisor = Integer.parseInt(revisorSeleccionado.split(" - ")[0]);

                    // Cargar los artículos asignados al revisor seleccionado
                    cargarArticulos(idRevisor);
                }
            }
        });
        
        // Configurar el listener del botón "Enviar Decisión"
        vista.getBtnEnviarDecision().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enviarDecision();
            }
        });
        
        vista.getBtnFiltro().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	String revisorSeleccionado = (String) vista.getComboRevisores().getSelectedItem();
                if (revisorSeleccionado != null) {
                    int idRevisor = Integer.parseInt(revisorSeleccionado.split(" - ")[0]);
                    filtrar(idRevisor);
                }
            }
        });
    }

    private void cargarRevisores() {
        List<String> revisores = model.obtenerRevisores();
        vista.mostrarRevisores(revisores);
    }

    private void cargarArticulos(int idRevisor) {
        List<String> articulos = model.obtenerArticulosParaRevisor(idRevisor);
        vista.mostrarArticulos(articulos);
    }

    private void enviarDecision() {
        // Obtener el revisor seleccionado
        String revisorSeleccionado = (String) vista.getComboRevisores().getSelectedItem();
        if (revisorSeleccionado == null) {
            JOptionPane.showMessageDialog(vista.getFrame(), "Debe seleccionar un revisor.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Extraer el idRevisor del texto seleccionado (asumiendo que el formato es "id - nombre")
        int idRevisor = Integer.parseInt(revisorSeleccionado.split(" - ")[0]);

        // Obtener el artículo seleccionado
        String tituloArticulo = vista.getListaArticulos().getSelectedValue();
        if (tituloArticulo == null) {
            JOptionPane.showMessageDialog(vista.getFrame(), "Debe seleccionar un artículo.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Verificar conflicto de intereses
        if (model.tieneConflictoIntereses(idRevisor, tituloArticulo)) {
            JOptionPane.showMessageDialog(vista.getFrame(), "Tiene un conflicto de intereses con este artículo.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Obtener la decisión seleccionada
        String decision = (String) vista.getComboDecisiones().getSelectedItem();

        // Registrar la decisión
        model.registrarDecision(idRevisor, tituloArticulo, decision);

        // Mostrar mensaje de éxito
        JOptionPane.showMessageDialog(vista.getFrame(), "Decisión registrada correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void filtrar(int idRevisor) {
        List<String> articulos = model.getListaArticulosArray(idRevisor);
        vista.mostrarArticulos(articulos);
    }
}