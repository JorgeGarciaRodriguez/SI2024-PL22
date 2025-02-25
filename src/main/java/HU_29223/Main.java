package HU_29223;

import java.util.Arrays;
import java.util.List;

import giis.demo.util.Database;
public class Main {
		    private static Database db=new Database();
		    public static void main(String[] args) {
		        // Crear la vista
		        View vista = new View();

		        // Crear el modelo
		        Model modelo = new Model();
		        
		        Controller controlador= new Controller(modelo,vista);
		        
		        vista.getFrame().setVisible(true);

	}

}
