package ver_revisiones;


public class Ver_revisionesMain {

	public static void main(String[] args) {
        // Crear la vista
        Ver_revisionesView vista = new Ver_revisionesView();

        // Crear el modelo
        Ver_revisionesModel modelo = new Ver_revisionesModel();
        
        Ver_revisionesController controlador= new Ver_revisionesController(modelo,vista);
        
        vista.getFrame().setVisible(true);

	}

}
