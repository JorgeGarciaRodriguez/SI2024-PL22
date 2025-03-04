package HU29228;

import java.util.ArrayList;
import java.util.List;
import giis.demo.util.Database;

public class Model {
    private Database db = new Database();
    
    public Model() {
        // db.createDatabase(true); // Crea la base de datos si no existe
        // db.loadDatabase(); // Carga datos iniciales
    }
    
    // Obtiene la lista de artículos del autor
    public List<Object[]> getListaArticulos() {
        String sql = "SELECT id, titulo FROM Articulo";
        return db.executeQueryArray(sql);
    }
    
    // Obtiene los detalles de un artículo específico
    public Object[] getDetallesArticulo(String titulo) {
        String sql = "SELECT id, titulo, palabras_clave, resumen, fichero, fecha_envio, autor FROM Articulo WHERE titulo = ?";
        List<Object[]> resultados = db.executeQueryArray(sql, titulo);
        return resultados.isEmpty() ? new Object[]{} : resultados.get(0);
    }
    
    // Obtiene la lista de autores de un artículo específico
    public List<Object[]> getAutoresArticulo(String titulo) {
        String sql = "SELECT p.nombre, p.correo, p.organizacion, p.grupo_investigacion " +
                     "FROM Persona p " +
                     "JOIN Autor_articulo aa ON p.id = aa.idAutor " +
                     "JOIN Articulo art ON aa.idArticulo = art.id " +
                     "WHERE art.titulo = ?";
        return db.executeQueryArray(sql, titulo);
    }
}
