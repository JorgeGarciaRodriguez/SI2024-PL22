package HU29228;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import giis.demo.util.SwingUtil;

public class Controller {
    private Model model;
    private View view;

    public Controller(Model m, View v) {
        this.model = m;
        this.view = v;
        cargarArticulos();
        initController();
    }

    // Cargar los artículos del autor
    public void cargarArticulos() {
        List<Object[]> articulos = model.getArticulosDelAutor("Raquel Blanco"); // Se puede parametrizar
        DefaultTableModel tableModel = (DefaultTableModel) view.getTableArticulos().getModel();
        tableModel.setRowCount(0);
        for (Object[] articulo : articulos) {
            tableModel.addRow(articulo);
        }
    }

    public void initController() {
        view.getTableArticulos().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                SwingUtil.exceptionWrapper(() -> updateDetail());
            }
        });
    }

    // Actualizar información cuando se selecciona un artículo
    public void updateDetail() {
        int selectedRow = view.getTableArticulos().getSelectedRow();
        if (selectedRow == -1) return;
        int articuloId = (int) view.getTableArticulos().getValueAt(selectedRow, 0);

        Object[] detalles = model.getDetallesArticulo(articuloId);
        if (detalles != null) {
            view.updateArticleInfo(detalles);
        }

        List<Object[]> autores = model.getAutoresArticulo(articuloId);
        DefaultTableModel tableModelAutores = (DefaultTableModel) view.getTableAutores().getModel();
        tableModelAutores.setRowCount(0);
        for (Object[] autor : autores) {
            tableModelAutores.addRow(autor);
        }
    }
}

