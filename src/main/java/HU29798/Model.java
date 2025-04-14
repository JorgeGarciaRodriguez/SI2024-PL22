package HU29798;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import giis.demo.util.Database;

public class Model {
    private Database db = new Database();

    public Model() {
        // Constructor vacío
    }

    // Clase interna para representar una discusión
    public static class Discusion {
        private int idDiscusion;
        private int idArticulo;
        private String tituloArticulo;
        private String estado;
        private List<Revision> revisiones;

        public Discusion(int idDiscusion, int idArticulo, String tituloArticulo, String estado) {
            this.idDiscusion = idDiscusion;
            this.idArticulo = idArticulo;
            this.tituloArticulo = tituloArticulo;
            this.estado = estado;
            this.revisiones = new ArrayList<>();
        }

        public int getIdDiscusion() { return idDiscusion; }
        public int getIdArticulo() { return idArticulo; }
        public String getTituloArticulo() { return tituloArticulo; }
        public String getEstado() { return estado; }
        public List<Revision> getRevisiones() { return revisiones; }

        public void agregarRevision(Revision revision) {
            this.revisiones.add(revision);
        }
    }

    // Clase interna para representar una revisión
    public static class Revision {
        private int idRevision;
        private int idRevisor;
        private String nombreRevisor;
        private String experto;
        private Integer decision;
        private String comentAutor;
        private String comentCoor;

        public Revision(int idRevision, int idRevisor, String nombreRevisor, String experto,
                        Integer decision, String comentAutor, String comentCoor) {
            this.idRevision = idRevision;
            this.idRevisor = idRevisor;
            this.nombreRevisor = nombreRevisor;
            this.experto = experto;
            this.decision = decision;
            this.comentAutor = comentAutor;
            this.comentCoor = comentCoor;
        }

        public int getIdRevision() { return idRevision; }
        public int getIdRevisor() { return idRevisor; }
        public String getNombreRevisor() { return nombreRevisor; }
        public String getExperto() { return experto; }
        public Integer getDecision() { return decision; }
        public String getComentAutor() { return comentAutor; }
        public String getComentCoor() { return comentCoor; }

        public String getDecisionTexto() {
            if (decision == null) return "Pendiente";
            return switch (decision) {
                case 2 -> "Aceptado (Fuerte)";
                case 1 -> "Aceptado (Débil)";
                case -1 -> "Rechazado (Débil)";
                case -2 -> "Rechazado (Fuerte)";
                default -> "Desconocido";
            };
        }
    }

    public List<Discusion> obtenerTodasLasDiscusiones() {
        List<Discusion> discusiones = new ArrayList<>();
        String queryDiscusiones = "SELECT d.id_discusion, d.id_articulo, a.titulo, d.estado " +
                                  "FROM Discusion d JOIN Articulo a ON d.id_articulo = a.id " +
                                  "ORDER BY d.id_discusion DESC";

        try (Connection conn = db.getConnection();
             PreparedStatement ps = conn.prepareStatement(queryDiscusiones);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int idDiscusion = rs.getInt("id_discusion");
                int idArticulo = rs.getInt("id_articulo");
                String titulo = rs.getString("titulo");
                String estado = rs.getString("estado");

                Discusion discusion = new Discusion(idDiscusion, idArticulo, titulo, estado);

                String queryRevisiones = "SELECT r.idRevision, r.idRevisor, p.nombre, r.experto, " +
                                         "r.decision, r.coment_autor, r.coment_coor " +
                                         "FROM Revision r JOIN Persona p ON r.idRevisor = p.id " +
                                         "WHERE r.idArticulo = ? ORDER BY r.idRevision";

                try (PreparedStatement psRev = conn.prepareStatement(queryRevisiones)) {
                    psRev.setInt(1, idArticulo);
                    try (ResultSet rsRev = psRev.executeQuery()) {
                        while (rsRev.next()) {
                            int idRevision = rsRev.getInt("idRevision");
                            int idRevisor = rsRev.getInt("idRevisor");
                            String nombreRevisor = rsRev.getString("nombre");
                            String experto = rsRev.getString("experto");
                            Integer decision = rsRev.getInt("decision");
                            if (rsRev.wasNull()) decision = null;
                            String comentAutor = rsRev.getString("coment_autor");
                            String comentCoor = rsRev.getString("coment_coor");

                            Revision revision = new Revision(idRevision, idRevisor, nombreRevisor,
                                                             experto, decision, comentAutor, comentCoor);
                            discusion.agregarRevision(revision);
                        }
                    }
                }

                discusiones.add(discusion);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return discusiones;
    }
}
