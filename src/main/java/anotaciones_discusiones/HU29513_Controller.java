package anotaciones_discusiones;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

public class HU29513_Controller {

    private HU29513_View view;
    private HU29513_Model model;

    public HU29513_Controller(HU29513_Model model, HU29513_View view) {
        this.model = model;
        this.view = view;
        cargarArticulos();
        cargarRevisores();
        initListeners();
    }

    private void initListeners() {
        view.getComboArticulo().addActionListener(e -> actualizarVista());
        view.getComboRevisor().addActionListener(e -> actualizarVista());

        view.getBtnGuardar().addActionListener(e -> guardarAnotacion());
        view.getBtnMarcarDecision().addActionListener(e -> marcarDecisionFinal());
        view.getBtnVolver().addActionListener(e -> view.getFrame().dispose());
    }

    private void cargarArticulos() {
        for (String titulo : model.getListaArticulos()) {
            view.getComboArticulo().addItem(titulo);
        }
    }

    private void cargarRevisores() {
        for (String revisor : model.getListaRevisores()) {
            view.getComboRevisor().addItem(revisor);
        }
    }

    private void actualizarVista() {
        String articulo = (String) view.getComboArticulo().getSelectedItem();
        String revisor = (String) view.getComboRevisor().getSelectedItem();

        if (articulo == null || revisor == null)
            return;

        boolean decisionMarcada = model.estaDecisionMarcada(articulo, revisor);
        view.setEstadoTexto("En discusión");
        view.setDecisionMarcada(decisionMarcada);
        view.habilitarEdicion(!decisionMarcada);

        view.getAreaMisAnotaciones().setText(model.getAnotacionesRevisor(articulo, revisor));
        view.getAreaOtrasAnotaciones().setText(model.getAnotacionesOtros(articulo, revisor));
    }

    private void guardarAnotacion() {
        String articulo = (String) view.getComboArticulo().getSelectedItem();
        String revisor = (String) view.getComboRevisor().getSelectedItem();
        String texto = view.getAreaNuevaAnotacion().getText().trim();

        if (articulo == null || revisor == null) {
            JOptionPane.showMessageDialog(null, "Selecciona artículo y revisor.");
            return;
        }

        if (texto.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Escribe una anotación antes de guardar.");
            return;
        }

        model.insertarAnotacion(articulo, revisor, texto);
        view.getAreaNuevaAnotacion().setText("");
        actualizarVista(); // Refresca vista
    }

    private void marcarDecisionFinal() {
        String articulo = (String) view.getComboArticulo().getSelectedItem();
        String revisor = (String) view.getComboRevisor().getSelectedItem();

        if (articulo == null || revisor == null) {
            JOptionPane.showMessageDialog(null, "Selecciona artículo y revisor.");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(null,
                "¿Estás seguro de marcar la decisión final?\nNo podrás modificar anotaciones luego.",
                "Confirmar decisión final", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            model.marcarDecisionFinal(articulo, revisor);
            actualizarVista();
        }
    }
}
