package ver_articulo;

import java.util.List;
import java.util.ArrayList;
import giis.demo.util.Database;

public class HU29365_29366_Model {
    private Database db = new Database();
    
    public HU29365_29366_Model() {
        // db.createDatabase(true); // Solo si necesitas inicializar
        // db.loadDatabase();       // Solo si necesitas datos de ejemplo
    }

    // Obtener lista de autores para seleccionar en el combo
    public List<String> getListaAutores() {
        List<String> autores = new ArrayList<>();
        String sql = "SELECT DISTINCT nombre FROM Persona " +
                     "WHERE id IN (SELECT idAutor FROM Autor)";
        List<Object[]> resultados = db.executeQueryArray(sql);
        for (Object[] fila : resultados) {
            autores.add((String) fila[0]);
        }
        return autores;
    }

    // Obtener lista de artículos donde soy autor
    public List<Object[]> getArticulosDondeSoyAutor(String autorNombre) {
        String sql = "SELECT art.id, art.titulo FROM Articulo art " +
                     "JOIN Autor_articulo aa ON art.id = aa.idArticulo " +
                     "JOIN Autor a ON aa.idAutor = a.idAutor " +
                     "JOIN Persona p ON a.idAutor = p.id " +
                     "WHERE p.nombre = ?";
        return db.executeQueryArray(sql, autorNombre);
    }

    // Obtener lista de artículos enviados por mí (autor principal)
    public List<Object[]> getArticulosEnviadosPorMi(String autorNombre) {
        String sql = "SELECT art.id, art.titulo " +
                     "FROM Articulo art " +
                     "JOIN Autor_articulo aa ON art.id = aa.idArticulo " +
                     "JOIN Autor a ON aa.idAutor = a.idAutor " +
                     "JOIN Persona p ON a.idAutor = p.id " +
                     "WHERE p.nombre = ?";
        return db.executeQueryArray(sql, autorNombre);
    }

    // Obtener detalles del artículo seleccionado
    public Object[] getDetallesArticulo(int idArticulo) {
        String sql = "SELECT a.id, a.titulo, a.palabras_clave, a.resumen, a.fichero, a.fecha, p.nombre " +
                     "FROM Articulo a " +
                     "JOIN Autor_articulo aa ON a.id = aa.idArticulo " +
                     "JOIN Autor au ON aa.idAutor = au.idAutor " +
                     "JOIN Persona p ON au.idAutor = p.id " +
                     "WHERE a.id = ? " +
                     "LIMIT 1"; // Si solo quieres mostrar uno de los autores
        List<Object[]> resultado = db.executeQueryArray(sql, idArticulo);
        return resultado.isEmpty() ? null : resultado.get(0);
    }



    // Obtener autores del artículo (nombre, correo, organización, grupo)
    public List<Object[]> getAutoresArticulo(int idArticulo) {
        String sql = "SELECT p.nombre, a.correo, p.organizacion, p.grupo " +
                     "FROM Persona p " +
                     "JOIN Autor a ON p.id = a.idAutor " +
                     "JOIN Autor_articulo aa ON a.idAutor = aa.idAutor " +
                     "WHERE aa.idArticulo = ?";
        return db.executeQueryArray(sql, idArticulo);
    }

}
