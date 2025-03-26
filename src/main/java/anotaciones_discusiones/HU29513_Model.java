package anotaciones_discusiones;

import java.util.ArrayList;
import java.util.List;
import giis.demo.util.Database;

public class HU29513_Model {
    private Database db = new Database();
    public HU29513_Model() {
		//db.createDatabase(true); // Crea la base de datos si no existe
		//db.loadDatabase(); // Carga datos iniciales
    }
    public List<String> getListaArticulos() {
        List<String> lista = new ArrayList<>();
        String sql = "SELECT titulo FROM Articulo";
        for (Object[] fila : db.executeQueryArray(sql)) {
            lista.add((String) fila[0]);
        }
        return lista;
    }

    public List<String> getListaRevisores() {
        List<String> lista = new ArrayList<>();
        String sql = "SELECT p.nombre FROM Revisor r JOIN Persona p ON r.idRevisor = p.id";
        for (Object[] fila : db.executeQueryArray(sql)) {
            lista.add((String) fila[0]);
        }
        return lista;
    }

    public String getAnotacionesRevisor(String titulo, String revisor) {
        String sql = "SELECT a.texto, a.fecha " +
                     "FROM Anotaciones a " +
                     "JOIN Articulo art ON a.idArticulo = art.id " +
                     "JOIN Persona p ON a.idRevisor = p.id " +
                     "WHERE art.titulo = ? AND p.nombre = ? " +
                     "ORDER BY a.fecha ASC";
        return formatearAnotaciones(db.executeQueryArray(sql, titulo, revisor));
    }

    public String getAnotacionesOtros(String titulo, String revisor) {
        String sql = "SELECT p.nombre, a.texto, a.fecha " +
                     "FROM Anotaciones a " +
                     "JOIN Articulo art ON a.idArticulo = art.id " +
                     "JOIN Persona p ON a.idRevisor = p.id " +
                     "WHERE art.titulo = ? AND p.nombre <> ? " +
                     "ORDER BY a.fecha ASC";
        return formatearAnotacionesConNombre(db.executeQueryArray(sql, titulo, revisor));
    }

    public void insertarAnotacion(String titulo, String revisor, String texto) {
        int idArticulo = getIdArticulo(titulo);
        int idRevisor = getIdPersona(revisor);
        String sql = "INSERT INTO Anotaciones (idArticulo, idRevisor, texto, fecha) VALUES (?, ?, ?, datetime('now'))";
        db.executeUpdate(sql, idArticulo, idRevisor, texto);
    }

    public void marcarDecisionFinal(String titulo, String revisor) {
        int idArticulo = getIdArticulo(titulo);
        int idRevisor = getIdRevisor(revisor);
        String sql = "UPDATE Revision SET decision = 1 WHERE idArticulo = ? AND idRevisor = ?";
        db.executeUpdate(sql, idArticulo, idRevisor);
    }

    public boolean estaDecisionMarcada(String titulo, String revisor) {
        String sql = "SELECT decision FROM Revision r " +
                     "JOIN Articulo a ON r.idArticulo = a.id " +
                     "JOIN Revisor rev ON r.idRevisor = rev.idRevisor " +
                     "JOIN Persona p ON rev.idRevisor = p.id " +
                     "WHERE a.titulo = ? AND p.nombre = ?";
        List<Object[]> resultados = db.executeQueryArray(sql, titulo, revisor);
        if (resultados.isEmpty()) return false;
        Object valor = resultados.get(0)[0];
        return valor != null && ((Integer) valor) == 1;
    }

    private int getIdArticulo(String titulo) {
        String sql = "SELECT id FROM Articulo WHERE titulo = ?";
        return (int) db.executeQueryArray(sql, titulo).get(0)[0];
    }

    private int getIdPersona(String nombre) {
        String sql = "SELECT id FROM Persona WHERE nombre = ?";
        return (int) db.executeQueryArray(sql, nombre).get(0)[0];
    }

    private int getIdRevisor(String nombre) {
        String sql = "SELECT r.idRevisor FROM Revisor r JOIN Persona p ON r.idRevisor = p.id WHERE p.nombre = ?";
        return (int) db.executeQueryArray(sql, nombre).get(0)[0];
    }

    private String formatearAnotaciones(List<Object[]> resultados) {
        StringBuilder sb = new StringBuilder();
        for (Object[] fila : resultados) {
            sb.append("[").append(fila[1]).append("] ").append(fila[0]).append("\n");
        }
        return sb.toString();
    }

    private String formatearAnotacionesConNombre(List<Object[]> resultados) {
        StringBuilder sb = new StringBuilder();
        for (Object[] fila : resultados) {
            sb.append(fila[0]).append(" [").append(fila[2]).append("]: ").append(fila[1]).append("\n");
        }
        return sb.toString();
    }
}
