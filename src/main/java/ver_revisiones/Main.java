package ver_revisiones;


public class Main {

	public static void main(String[] args) {
        // Crear la vista
        View vista = new View();

        // Crear el modelo
        Model modelo = new Model();
        
        Controller controlador= new Controller(modelo,vista);
        
        vista.getFrame().setVisible(true);

	}

}
