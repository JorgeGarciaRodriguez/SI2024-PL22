package HU29228;

import java.util.List;
import giis.demo.util.Database;

public class Model {
    private Database db = new Database();

    public Model() {}

    // Lista de artículos con su ID y título para un autor específico
    public List<Object[]> getArticulosDelAutor(String autor) {
        String sql = "SELECT id, titulo FROM Articulo WHERE autor = ?";
        return db.executeQueryArray(sql, autor);
    }

    // Detalles artículo seleccionado
    public Object[] getDetallesArticulo(int articuloId) {
        String sql = "SELECT id, titulo, palabras_clave, resumen, fichero, fecha_envio, autor FROM Articulo WHERE id = ?";
        List<Object[]> resultados = db.executeQueryArray(sql, articuloId);
        return resultados.isEmpty() ? null : resultados.get(0);
    }

    // Autores artículo seleccionado
    public List<Object[]> getAutoresArticulo(int articuloId) {
        String sql = "SELECT nombre, correo, organizacion, grupo_investigacion FROM Autor WHERE idArticulo = ?";
        return db.executeQueryArray(sql, articuloId);
    }
}