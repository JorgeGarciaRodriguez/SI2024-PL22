package HU29227;

import java.util.ArrayList;
import java.util.List;
import giis.demo.util.Database;

public class Model {
    private Database db = new Database();
    
    public Model() {
        // db.createDatabase(true); // Crea la base de datos si no existe
        // db.loadDatabase(); // Carga datos iniciales
    }
    
    // Obtiene la lista de artículos con su decisión y autor
    public List<Object[]> getListaArticulosDisponiblesArray() {
        List<Object[]> resultado = new ArrayList<>();
        String sql = "SELECT titulo, decision, autor FROM Articulo";
        
        List<Object[]> resultados = db.executeQueryArray(sql);
        return resultados;
    }
    
    public List<Object[]> getComentariosArticulo(String titulo) {
        String sql = "SELECT comentario, nivel_experto, decision FROM Revision WHERE idArticulo = (SELECT id FROM Articulo WHERE titulo = ?)";
        return db.executeQueryArray(sql, titulo);
    }
    
    public String getDecision(String titulo) {
        String sql = "SELECT decision FROM Articulo WHERE titulo = ?";
        List<Object[]> resultados = db.executeQueryArray(sql, titulo);
        return resultados.isEmpty() ? "" : (String) resultados.get(0)[0];
    }
    
    public String getAutor(String titulo) {
        String sql = "SELECT autor FROM Articulo WHERE titulo = ?";
        List<Object[]> resultados = db.executeQueryArray(sql, titulo);
        return resultados.isEmpty() ? "" : (String) resultados.get(0)[0];
    }
}
