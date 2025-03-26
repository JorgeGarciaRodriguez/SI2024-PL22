package anotaciones_discusiones;

import java.util.Arrays;
import java.util.List;

import giis.demo.util.Database;
public class HU29513_Main {
		private static Database db = new Database();
		public static void main(String[] args) {
			HU29513_View vista = new HU29513_View();
			HU29513_Model modelo = new HU29513_Model();
			HU29513_Controller controlador =  new HU29513_Controller(modelo,vista);
			
			vista.getFrame().setVisible(true);
	        // Hacer visible la vista
	       /* try {
	            List<Object[]> resultado = db.executeQueryArray("PRAGMA table_info(Revision);");

	            if (resultado.isEmpty()) {
	                System.out.println("⚠️ La tabla Revision no existe o no tiene columnas.");
	            } else {
	                for (Object[] fila : resultado) {
	                    System.out.println(Arrays.toString(fila)); // Imprime cada columna correctamente
	                }
	            }
	        } catch (Exception e) {
	            System.out.println("❌ Error al ejecutar PRAGMA: " + e.getMessage());
	        }
	        vista.getFrame().setVisible(true);
	        List<Object[]> resultados = db.executeQueryArray("SELECT * FROM Revision");
	        for (Object[] fila : resultados) {
	            System.out.println(Arrays.toString(fila));
	        }
	*/
	}
		}
		
