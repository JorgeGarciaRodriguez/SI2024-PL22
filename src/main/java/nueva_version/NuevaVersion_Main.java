package nueva_version;

public class NuevaVersion_Main {

	public static void main(String[] args) {
        // Crear la vista
        NuevaVersion_View vista = new NuevaVersion_View();

        // Crear el modelo
        NuevaVersion_Model modelo = new NuevaVersion_Model();
        
        NuevaVersion_Controller controlador= new NuevaVersion_Controller(modelo,vista);
        
        vista.getFrame().setVisible(true);

	}
	
}
