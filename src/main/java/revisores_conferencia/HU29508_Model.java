package revisores_conferencia;

import giis.demo.util.Database;
import java.util.ArrayList;
import java.util.List;

public class HU29508_Model {
    private Database db = new Database();

    public HU29508_Model() {
        // db.createDatabase(true); // Solo si necesitas inicializar
        // db.loadDatabase();       // Solo si necesitas datos de ejemplo
    }

    public List<String> getListaArticulosDisponibles() {
        List<String> articulos = new ArrayList<>();
        String sql = "SELECT titulo FROM Articulo";
        List<Object[]> resultados = db.executeQueryArray(sql);
        for (Object[] fila : resultados) {
            articulos.add((String) fila[0]);
        }
        return articulos;
    }

    // ✅ NUEVO método para info completa del artículo
    public String getInformacionArticulo(String titulo) {
        String sql = "SELECT titulo, palabras_clave, resumen, fichero FROM Articulo WHERE titulo = ?";
        List<Object[]> resultado = db.executeQueryArray(sql, titulo);
        if (resultado.isEmpty()) {
            return "No se encontró información del artículo.";
        }

        Object[] fila = resultado.get(0);
        return "Título: " + fila[0] + "\n" +
               "Palabras clave: " + (fila[1] != null ? fila[1] : "N/A") + "\n\n" +
               "Resumen:\n" + (fila[2] != null ? fila[2] : "N/A") + "\n\n" +
               "Fichero: " + (fila[3] != null ? fila[3] : "N/A");
    }

    public List<Object[]> getRevisoresFiltrados(boolean sinConflicto, boolean conPreferencia) {
        List<Object[]> resultado = new ArrayList<>();

        String sql = "SELECT p.nombre, r.temas_interes, r.conflicto_interes " +
                     "FROM Revisor r " +
                     "JOIN Persona p ON r.idRevisor = p.id";
        List<Object[]> datos = db.executeQueryArray(sql);

        for (Object[] fila : datos) {
            String preferencias = fila[1] != null ? fila[1].toString() : "";
            String conflicto = fila[2] != null ? fila[2].toString() : "NO";

            if (sinConflicto && conflicto.equalsIgnoreCase("SI"))
                continue;
            if (conPreferencia && preferencias.trim().isEmpty())
                continue;

            resultado.add(new Object[]{fila[0], preferencias, conflicto});
        }

        return resultado;
    }

    public void asignarRevisor(String nombreRevisor, String tituloArticulo) {
        int idRevisor = getIdRevisor(nombreRevisor);
        int idArticulo = getIdArticulo(tituloArticulo);
        int idRevision = getNextRevisionId();

        String sql = "INSERT INTO Revision (idRevision, idRevisor, idArticulo, decision) VALUES (?, ?, ?, NULL)";
        db.executeUpdate(sql, idRevision, idRevisor, idArticulo);
    }

    public String getDetalleRevisor(String nombre) {
        String sql = "SELECT p.nombre, au.correo, r.temas_interes, r.conflicto_interes " +
                     "FROM Revisor r " +
                     "JOIN Persona p ON r.idRevisor = p.id " +
                     "LEFT JOIN Autor au ON p.id = au.idAutor " +
                     "WHERE p.nombre = ?";

        List<Object[]> resultados = db.executeQueryArray(sql, nombre);
        if (!resultados.isEmpty()) {
            Object[] fila = resultados.get(0);
            return "Nombre: " + fila[0] + "\n" +
                   "Correo: " + (fila[1] != null ? fila[1] : "No disponible") + "\n" +
                   "Temas de Interés: " + (fila[2] != null ? fila[2] : "No especificado") + "\n" +
                   "Conflicto de Interés: " + (fila[3] != null ? fila[3] : "No especificado");
        } else {
            return "No se encontró información del revisor.";
        }
    }

    private int getIdRevisor(String nombre) {
        String sql = "SELECT r.idRevisor FROM Revisor r JOIN Persona p ON r.idRevisor = p.id WHERE p.nombre = ?";
        return (int) db.executeQueryArray(sql, nombre).get(0)[0];
    }

    private int getIdArticulo(String titulo) {
        String sql = "SELECT id FROM Articulo WHERE titulo = ?";
        return (int) db.executeQueryArray(sql, titulo).get(0)[0];
    }

    private int getNextRevisionId() {
        String sql = "SELECT MAX(idRevision) FROM Revision";
        List<Object[]> res = db.executeQueryArray(sql);
        if (res.isEmpty() || res.get(0)[0] == null) return 1;
        return (int) res.get(0)[0] + 1;
    }
}
