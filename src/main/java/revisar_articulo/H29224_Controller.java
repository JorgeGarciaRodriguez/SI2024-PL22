package revisar_articulo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import revisar_articulo.H29224_Model;
import revisar_articulo.H29224_View;

public class H29224_Controller {
	private H29224_Model model;
	private H29224_View vista;
	
	public H29224_Controller(H29224_Model m, H29224_View v)	{
	    this.model = m;
	    this.vista = v;
	    
	    
	    vista.getBtnEnviarRevision().addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            // Obtener los datos de la revisión
	            String comentarioCoor = vista.getTfComentarioCoordinador().getText().trim();
	            String comentarioAutor = vista.getTfComentarioAutor().getText().trim();
	            String decision = (String) vista.getComboBoxDecision().getSelectedItem();
	            String experto = (String) vista.getComboBoxExperto().getSelectedItem();
	            int idRevisor=Integer.parseInt(vista.gettFidAutor().getText().trim());
	            // Obtener el artículo seleccionado
	            String tituloSeleccionado = vista.getListadoTitulos().getSelectedValue();

	            // Validar que los campos no estén vacíos
	            if (comentarioCoor.isEmpty() || comentarioAutor.isEmpty() || decision == null || experto == null || tituloSeleccionado == null) {
	                JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
	                return;
	            }

	            // Buscar el idArticulo basado en el título seleccionado
	            int idArticulo = model.obtenerIdArticuloPorTitulo(tituloSeleccionado);

	            // Verificar que se encontró un idArticulo válido
	            if (idArticulo == -1) {
	                JOptionPane.showMessageDialog(null, "No se encontró el artículo seleccionado.", "Error", JOptionPane.ERROR_MESSAGE);
	                return;
	            }

	            // Guardar la revisión en la base de datos
	            model.guardarRevision(2,idArticulo, experto, decision, comentarioAutor, comentarioCoor);

	            // Confirmación
	            JOptionPane.showMessageDialog(null, "Revisión guardada correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
	        }
	    });


}
}