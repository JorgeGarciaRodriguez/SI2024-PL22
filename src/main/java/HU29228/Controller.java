package HU29228;

import javax.swing.table.DefaultTableModel;
import java.util.List;

public class Controller {
    private Model model;
    private View view;
    
    public Controller(Model m, View v) {
        this.model = m;
        this.view = v;
        cargarArticulos();
        initController();
    }

    // Carga la lista de artículos en la vista
    private void cargarArticulos() {
        List<Object[]> listaArticulos = model.getListaArticulos();
        DefaultTableModel modelTabla = (DefaultTableModel) view.getTableArticulos().getModel();
        modelTabla.setRowCount(0); // Limpiar tabla
        
        for (Object[] fila : listaArticulos) {
            modelTabla.addRow(fila);
        }
    }

    private void initController() {
        view.getTableArticulos().getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = view.getTableArticulos().getSelectedRow();
                if (selectedRow != -1) {
                    String titulo = (String) view.getTableArticulos().getValueAt(selectedRow, 1);
                    cargarDetallesArticulo(titulo);
                }
            }
        });
    }

    private void cargarDetallesArticulo(String titulo) {
        Object[] detalles = model.getDetallesArticulo(titulo);
        view.actualizarInformacionArticulo(detalles);
        
        List<Object[]> listaAutores = model.getAutoresArticulo(titulo);
        DefaultTableModel modelAutores = (DefaultTableModel) view.getTableAutores().getModel();
        modelAutores.setRowCount(0);
        
        for (Object[] fila : listaAutores) {
            modelAutores.addRow(fila);
        }
    }
}