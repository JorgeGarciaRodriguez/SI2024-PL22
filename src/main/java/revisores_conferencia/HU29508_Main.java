package revisores_conferencia;

import java.util.List;

import giis.demo.util.Database;
import revisores_conferencia.HU29508_Controller;
import revisores_conferencia.HU29508_Model;
import revisores_conferencia.HU29508_View;

public class HU29508_Main {
	private static Database db=new Database();
    public static void main(String[] args) {
        // Crear la vista
        HU29508_View vista = new HU29508_View();

        // Crear el modelo
        HU29508_Model modelo = new HU29508_Model();
        
        HU29508_Controller controlador= new HU29508_Controller(modelo,vista);
        
        vista.getFrame().setVisible(true);
        
        // Hacer visible la vista
        /*try {
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