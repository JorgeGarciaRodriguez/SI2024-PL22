package revision_articulos;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import giis.demo.util.SwingUtil;

public class HU29579_Controller {
    private HU29579_Model model;
    private HU29579_View view;

    public HU29579_Controller(HU29579_Model m, HU29579_View v) {
        this.model = m;
        this.view = v;
        cargarArticulos();
        initController();
    }

 // Cargar los artículos enviados en la tabla superior
    private void cargarArticulos() {
        List<Object[]> articulos = model.getArticulosEnviados();
        DefaultTableModel tableModel = (DefaultTableModel) view.getTableArticulos().getModel();
        tableModel.setRowCount(0);
        for (Object[] articulo : articulos) {
            tableModel.addRow(articulo);
        }
    }

    // Inicializar controladores y eventos
    private void initController() {
        view.getTableArticulos().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                int fila = view.getTableArticulos().getSelectedRow();
                if (fila != -1) {
                    String tituloArticulo = (String) view.getTableArticulos().getValueAt(fila, 0);
                    cargarComentarios(tituloArticulo);
                }
            }
        });

        view.getBtnVolver().addActionListener(e -> view.dispose());
    }

    // Cargar comentarios del artículo seleccionado en la tabla inferior
    private void cargarComentarios(String tituloArticulo) {
        List<Object[]> comentarios = model.getComentariosArticulo(tituloArticulo);
        DefaultTableModel tableModel = (DefaultTableModel) view.getTableRevisiones().getModel();
        tableModel.setRowCount(0);
        for (Object[] comentario : comentarios) {
            tableModel.addRow(comentario);
        }
    }
}
