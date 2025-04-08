package ver_nuevaversion;

public class ver_nuevaversion_Main {


	public static void main(String[] args) {
        // Crear la vista
        ver_nuevaversion_View vista = new ver_nuevaversion_View();

        // Crear el modelo
        ver_nuevaversion_Model modelo = new ver_nuevaversion_Model();
        
        ver_nuevaversion_Controller controlador= new ver_nuevaversion_Controller(modelo,vista);
        
        vista.getFrame().setVisible(true);

	}

}
