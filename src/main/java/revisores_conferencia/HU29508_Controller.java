package revisores_conferencia;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class HU29508_Controller {
    private HU29508_Model model;
    private HU29508_View view;

    public HU29508_Controller(HU29508_Model model, HU29508_View view) {
        this.model = model;
        this.view = view;
        initController();
        cargarArticulos();
        cargarRevisores();
        cargarInfoArticulo(); // Mostrar info del artículo al arrancar
    }

    private void initController() {
        view.getBtnAsignar().addActionListener(e -> asignarRevisores());
        view.getBtnDetalle().addActionListener(e -> mostrarDetalleRevisor());
        view.getBtnVolver().addActionListener(e -> view.getFrame().dispose());

        view.getChkSinConflicto().addActionListener(e -> cargarRevisores());
        view.getChkConPreferencia().addActionListener(e -> cargarRevisores());

        view.getComboArticulo().addActionListener(e -> {
            cargarRevisores();
            cargarInfoArticulo();
        });
    }

    private void cargarArticulos() {
        List<String> articulos = model.getListaArticulosDisponibles();
        JComboBox<String> combo = view.getComboArticulo();
        combo.removeAllItems();
        for (String titulo : articulos) {
            combo.addItem(titulo);
        }
    }

    private void cargarRevisores() {
        boolean sinConflicto = view.getChkSinConflicto().isSelected();
        boolean conPreferencia = view.getChkConPreferencia().isSelected();

        List<Object[]> revisores = model.getRevisoresFiltrados(sinConflicto, conPreferencia);
        DefaultTableModel tableModel = (DefaultTableModel) view.getTablaRevisores().getModel();
        tableModel.setRowCount(0);

        for (Object[] rev : revisores) {
            tableModel.addRow(new Object[]{rev[0], rev[1], rev[2], false});
        }
    }

    private void asignarRevisores() {
        String articulo = (String) view.getComboArticulo().getSelectedItem();
        if (articulo == null) {
            JOptionPane.showMessageDialog(view.getFrame(), "Selecciona un artículo primero.");
            return;
        }

        JTable tabla = view.getTablaRevisores();
        DefaultTableModel modelTabla = (DefaultTableModel) tabla.getModel();

        boolean algunoAsignado = false;

        for (int i = 0; i < modelTabla.getRowCount(); i++) {
            boolean asignado = (boolean) modelTabla.getValueAt(i, 3);
            if (asignado) {
                String nombreRevisor = (String) modelTabla.getValueAt(i, 0);
                model.asignarRevisor(nombreRevisor, articulo);
                algunoAsignado = true;
            }
        }

        if (algunoAsignado) {
            JOptionPane.showMessageDialog(view.getFrame(), "Revisores asignados correctamente.");
            cargarRevisores();
        } else {
            JOptionPane.showMessageDialog(view.getFrame(), "No has marcado ningún revisor.");
        }
    }

    private void mostrarDetalleRevisor() {
        int fila = view.getTablaRevisores().getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(view.getFrame(), "Selecciona un revisor para ver detalles.");
            return;
        }

        String nombre = (String) view.getTablaRevisores().getValueAt(fila, 0);
        String detalle = model.getDetalleRevisor(nombre);
        JOptionPane.showMessageDialog(view.getFrame(), detalle, "Detalle del Revisor", JOptionPane.INFORMATION_MESSAGE);
    }

    // Mostrar información del artículo seleccionado
    private void cargarInfoArticulo() {
        String articulo = (String) view.getComboArticulo().getSelectedItem();
        if (articulo != null) {
            String texto = model.getInformacionArticulo(articulo);
            view.getInfoArticulo().setText(texto);
        } else {
            view.getInfoArticulo().setText("Selecciona un artículo para ver su información.");
        }
    }
}
