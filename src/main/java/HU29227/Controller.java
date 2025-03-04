package HU29227;

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

    // Cargar los artículos enviados en la tabla
    public void cargarArticulos() {
        List<Object[]> articulos = model.getArticulosEnviados();
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

        view.getBtnVolver().addActionListener(e -> SwingUtil.exceptionWrapper(() -> view.getFrame().dispose()));
    }

    // Actualizar detalles cuando se selecciona un artículo
    public void updateDetail() {
        int selectedRow = view.getTableArticulos().getSelectedRow();
        if (selectedRow == -1) return;
        
        String tituloArticulo = (String) view.getTableArticulos().getValueAt(selectedRow, 0);
        List<Object[]> comentarios = model.getComentariosArticulo(tituloArticulo);

        DefaultTableModel tableModel = (DefaultTableModel) view.getTableComentarios().getModel();
        tableModel.setRowCount(0);

        for (Object[] comentario : comentarios) {
            tableModel.addRow(comentario);
        }
    }
}
