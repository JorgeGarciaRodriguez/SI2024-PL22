package ver_articulo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

public class HU29365_29366_Controller {

    private HU29365_29366_Model model;
    private HU29365_29366_View view;

    public HU29365_29366_Controller(HU29365_29366_Model model, HU29365_29366_View view) {
        this.model = model;
        this.view = view;
        initController();
    }

    private void initController() {
        cargarAutores();
        view.getComboAutores().addActionListener(e -> actualizarListas());

        view.getBtnDondeSoyAutor().addActionListener(e -> cargarArticulosComoAutor());
        view.getBtnEnviadosPorMi().addActionListener(e -> cargarArticulosEnviados());

        view.getTablaArticulos().getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                mostrarDetallesArticulo();
            }
        });
    }

    private void cargarAutores() {
        List<String> autores = model.getListaAutores();
        DefaultComboBoxModel<String> comboModel = new DefaultComboBoxModel<>();
        for (String autor : autores) {
            comboModel.addElement(autor);
        }
        view.getComboAutores().setModel(comboModel);
    }

    private void actualizarListas() {
        limpiarTablaArticulos();
        limpiarDetalles();
        limpiarTablaAutores();
    }

    private void cargarArticulosComoAutor() {
        String autorSeleccionado = (String) view.getComboAutores().getSelectedItem();
        if (autorSeleccionado == null) return;

        List<Object[]> articulos = model.getArticulosDondeSoyAutor(autorSeleccionado);
        DefaultTableModel tabla = (DefaultTableModel) view.getTablaArticulos().getModel();
        tabla.setRowCount(0);

        for (Object[] fila : articulos) {
            tabla.addRow(new Object[]{fila[0], fila[1]});
        }
    }

    private void cargarArticulosEnviados() {
        String autorSeleccionado = (String) view.getComboAutores().getSelectedItem();
        if (autorSeleccionado == null) return;

        List<Object[]> articulos = model.getArticulosEnviadosPorMi(autorSeleccionado);
        DefaultTableModel tabla = (DefaultTableModel) view.getTablaArticulos().getModel();
        tabla.setRowCount(0);

        for (Object[] fila : articulos) {
            tabla.addRow(new Object[]{fila[0], fila[1]});
        }
    }

    private void mostrarDetallesArticulo() {
        int filaSeleccionada = view.getTablaArticulos().getSelectedRow();
        if (filaSeleccionada == -1) return;

        int idArticulo = (int) view.getTablaArticulos().getValueAt(filaSeleccionada, 0);
        Object[] detalles = model.getDetallesArticulo(idArticulo);

        if (detalles != null) {
            view.setInfoArticulo(
                "Número: " + detalles[0] + "\n" +
                "Título: " + detalles[1] + "\n" +
                "Palabras clave: " + detalles[2] + "\n" +
                "Resumen: " + detalles[3] + "\n" +
                "Fichero: " + detalles[4] + "\n" +
                "Fecha envío: " + detalles[5] + "\n" +
                "Enviado por: " + detalles[6]
            );
        }

        // Cargar tabla de autores
        List<Object[]> autores = model.getAutoresArticulo(idArticulo);
        DefaultTableModel tablaAutores = (DefaultTableModel) view.getTablaAutores().getModel();
        tablaAutores.setRowCount(0);

        for (Object[] fila : autores) {
            tablaAutores.addRow(fila);
        }
    }

    private void limpiarTablaArticulos() {
        ((DefaultTableModel) view.getTablaArticulos().getModel()).setRowCount(0);
    }

    private void limpiarDetalles() {
        view.setInfoArticulo("");
    }

    private void limpiarTablaAutores() {
        ((DefaultTableModel) view.getTablaAutores().getModel()).setRowCount(0);
    }
}
