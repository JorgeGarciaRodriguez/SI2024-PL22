package ver_articulo;

import java.util.Arrays;
import java.util.List;

import giis.demo.util.Database;
import revision_articulos.HU29579_Controller;
import revision_articulos.HU29579_Model;
import revision_articulos.HU29579_View;
import ver_articulo.HU29365_29366_Controller;
import ver_articulo.HU29365_29366_Model;
import ver_articulo.HU29365_29366_View;
public class HU29365_29366_Main {
    private static Database db=new Database();
    public static void main(String[] args) {
        // Crear la vista
        HU29365_29366_View vista = new HU29365_29366_View();

        // Crear el modelo
        HU29365_29366_Model modelo = new HU29365_29366_Model();
        
        HU29365_29366_Controller controlador= new HU29365_29366_Controller(modelo,vista);
        
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