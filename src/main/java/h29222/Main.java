package h29222;


import java.awt.EventQueue;
import java.util.Arrays;
import java.util.List;

import giis.demo.util.Database;
public class Main {
    private static Database db = new Database();

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Vista vista = new Vista();
                    vista.getFrame().setVisible(true);
                    Model modelo = new Model();
                    Controller controlador = new Controller(modelo, vista);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        //PERSONA
        try {
            List<Object[]> resultado = db.executeQueryArray("PRAGMA table_info(Persona);");

            if (resultado.isEmpty()) {
                System.out.println("⚠️ La tabla Persona no existe o no tiene columnas.");
            } else {
                for (Object[] fila : resultado) {
                    System.out.println(Arrays.toString(fila)); // Imprime cada columna correctamente
                }
            }
        } catch (Exception e) {
            System.out.println("❌ Error al ejecutar PRAGMA: " + e.getMessage());
        }
     
        List<Object[]> resultados = db.executeQueryArray("SELECT * FROM Persona");
        for (Object[] fila : resultados) {
            System.out.println(Arrays.toString(fila));
            
        
        }
        //AUTOR
        try {
            List<Object[]> resultado = db.executeQueryArray("PRAGMA table_info(Autor);");

            if (resultado.isEmpty()) {
                System.out.println("⚠️ La tabla Persona no existe o no tiene columnas.");
            } else {
                for (Object[] fila : resultado) {
                    System.out.println(Arrays.toString(fila)); // Imprime cada columna correctamente
                }
            }
        } catch (Exception e) {
            System.out.println("❌ Error al ejecutar PRAGMA: " + e.getMessage());
        }
        
        List<Object[]> resultados2 = db.executeQueryArray("SELECT * FROM Autor");
        for (Object[] fila : resultados2) {
            System.out.println(Arrays.toString(fila));
            
        
        }
        
    }
}


