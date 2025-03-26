package revision_articulos;

import java.util.ArrayList;
import java.util.List;
import giis.demo.util.Database;

public class HU29579_Model {
    private Database db = new Database();

    public HU29579_Model() {
        //db.createDatabase(true);
        //db.loadDatabase();
    }

    // Artículos enviados con autor y decisión final (de la tabla Revision)
    public List<Object[]> getArticulosEnviados() {
        String sql = "SELECT a.titulo, " +
                     "       r.decision, " +
                     "       p.nombre AS autor " +
                     "FROM Articulo a " +
                     "JOIN Revision r ON a.id = r.idArticulo " +
                     "JOIN Autor_articulo aa ON a.id = aa.idArticulo " +
                     "JOIN Autor au ON aa.idAutor = au.idAutor " +
                     "JOIN Persona p ON au.idAutor = p.id";
        return db.executeQueryArray(sql);
    }


    // Comentarios, nivel de experto y decisión por artículo
    public List<Object[]> getComentariosArticulo(String titulo) {
        String sql = "SELECT r.coment_autor, r.experto, r.decision " +
                     "FROM Revision r " +
                     "JOIN Articulo a ON r.idArticulo = a.id " +
                     "WHERE a.titulo = ?";
        return db.executeQueryArray(sql, titulo);
    }

    // Obtener decisión final (si todas las revisiones tienen decisión marcada)
    public String getDecisionFinal(String titulo) {
        String sql = "SELECT CASE " +
                     "WHEN COUNT(*) = SUM(CASE WHEN decision IS NOT NULL THEN 1 ELSE 0 END) THEN 'Completa' " +
                     "ELSE 'En discusión' END AS estado " +
                     "FROM Revision r " +
                     "JOIN Articulo a ON r.idArticulo = a.id " +
                     "WHERE a.titulo = ?";
        List<Object[]> res = db.executeQueryArray(sql, titulo);
        return res.isEmpty() ? "Desconocida" : (String) res.get(0)[0];
    }
}
