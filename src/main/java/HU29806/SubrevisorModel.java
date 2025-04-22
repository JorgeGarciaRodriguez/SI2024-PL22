package HU29806;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import giis.demo.util.Database;

public class SubrevisorModel {
    private Database db = new Database();
    
    public List<String[]> obtenerSubrevisores() {
        List<String[]> subrevisores = new ArrayList<>();
        String query = "SELECT p.id, p.nombre FROM Persona p JOIN Revisor r ON p.id = r.idRevisor";
        
        try (Connection conn = db.getConnection();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                String[] subrevisor = new String[2];
                subrevisor[0] = rs.getString("id");
                subrevisor[1] = rs.getString("nombre");
                subrevisores.add(subrevisor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return subrevisores;
    }

    public List<Map<String, Object>> obtenerInvitaciones(int idSubrevisor, Integer idTrack) {
        List<Map<String, Object>> invitaciones = new ArrayList<>();
        String query = """
            SELECT 
                a.id as idArticulo,
                a.titulo, 
                t.id as idTrack,
                t.nombre as track, 
                s.estado_invitacion,
                s.idRevisorPrincipal,
                p.nombre as revisor_principal
            FROM Subrevisor s
            JOIN Articulo a ON s.idArticulo = a.id
            JOIN Track t ON s.idTrack = t.id
            JOIN Persona p ON s.idRevisorPrincipal = p.id
            WHERE s.idSubrevisor = ?
            """ + (idTrack != null ? " AND t.id = ?" : "") + """
            ORDER BY a.titulo
            """;
        
        try (Connection conn = db.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            
            ps.setInt(1, idSubrevisor);
            if (idTrack != null) {
                ps.setInt(2, idTrack);
            }
            
            try (ResultSet rs = ps.executeQuery()) {
                ResultSetMetaData metaData = rs.getMetaData();
                int columnCount = metaData.getColumnCount();
                
                while (rs.next()) {
                    Map<String, Object> row = new HashMap<>();
                    for (int i = 1; i <= columnCount; i++) {
                        row.put(metaData.getColumnName(i), rs.getObject(i));
                    }
                    invitaciones.add(row);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return invitaciones;
    }

    public List<Map<String, Object>> obtenerTracksDisponibles(int idSubrevisor) {
        List<Map<String, Object>> tracks = new ArrayList<>();
        String query = """
            SELECT DISTINCT t.id, t.nombre 
            FROM Subrevisor s
            JOIN Track t ON s.idTrack = t.id
            WHERE s.idSubrevisor = ?
            ORDER BY t.nombre
            """;
        
        try (Connection conn = db.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            
            ps.setInt(1, idSubrevisor);
            
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Map<String, Object> track = new HashMap<>();
                    track.put("id", rs.getInt("id"));
                    track.put("nombre", rs.getString("nombre"));
                    tracks.add(track);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tracks;
    }

    public boolean responderInvitacion(int idPrincipal, int idSubrevisor, int idTrack, int idArticulo, boolean aceptar) {
        String estado = aceptar ? "aceptada" : "rechazada";
        String updateQuery = """
            UPDATE Subrevisor 
            SET estado_invitacion = ?
            WHERE idRevisorPrincipal = ? 
              AND idSubrevisor = ? 
              AND idTrack = ?
              AND idArticulo = ?
            """;

        String insertQuery = """
            INSERT OR IGNORE INTO RevisorTrack (idRevisor, idTrack)
            VALUES (?, ?)
            """;

        Connection conn = null;
        try {
            conn = db.getConnection();
            conn.setAutoCommit(false);

            try (PreparedStatement psUpdate = conn.prepareStatement(updateQuery)) {
                psUpdate.setString(1, estado);
                psUpdate.setInt(2, idPrincipal);
                psUpdate.setInt(3, idSubrevisor);
                psUpdate.setInt(4, idTrack);
                psUpdate.setInt(5, idArticulo);

                int affectedRows = psUpdate.executeUpdate();
                if (affectedRows == 0) {
                    return false;
                }

                if (aceptar) {
                    try (PreparedStatement psInsert = conn.prepareStatement(insertQuery)) {
                        psInsert.setInt(1, idSubrevisor);
                        psInsert.setInt(2, idTrack);
                        psInsert.executeUpdate();
                    }
                }

                conn.commit();
                return true;
            }
        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    System.err.println("Error al hacer rollback: " + ex.getMessage());
                }
            }
            System.err.println("Error al responder invitación: " + e.getMessage());
            return false;
        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(true);
                    conn.close();
                } catch (SQLException e) {
                    System.err.println("Error al cerrar conexión: " + e.getMessage());
                }
            }
        }
    }

    public List<Map<String, Object>> obtenerArticulosColaboracion(int idSubrevisor) {
        List<Map<String, Object>> articulos = new ArrayList<>();
        String query = """
            SELECT DISTINCT a.id, a.titulo, t.nombre as track
            FROM Subrevisor s
            JOIN Articulo a ON s.idArticulo = a.id
            JOIN Track t ON s.idTrack = t.id
            WHERE s.idSubrevisor = ? AND s.estado_invitacion = 'aceptada'
            ORDER BY a.titulo
            """;
        
        try (Connection conn = db.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            
            ps.setInt(1, idSubrevisor);
            
            try (ResultSet rs = ps.executeQuery()) {
                ResultSetMetaData metaData = rs.getMetaData();
                int columnCount = metaData.getColumnCount();
                
                while (rs.next()) {
                    Map<String, Object> row = new HashMap<>();
                    for (int i = 1; i <= columnCount; i++) {
                        row.put(metaData.getColumnName(i), rs.getObject(i));
                    }
                    articulos.add(row);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return articulos;
    }

    public boolean agregarComentario(int idSubrevisor, int idArticulo, String comentario) {
        String query = "INSERT INTO ComentariosSubrevisor (idSubrevisor, idArticulo, comentario) VALUES (?, ?, ?)";
        
        try (Connection conn = db.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            
            ps.setInt(1, idSubrevisor);
            ps.setInt(2, idArticulo);
            ps.setString(3, comentario);
            
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Map<String, Object>> obtenerComentarios(int idSubrevisor, int idArticulo) {
        List<Map<String, Object>> comentarios = new ArrayList<>();
        String query = """
            SELECT comentario, fecha 
            FROM ComentariosSubrevisor 
            WHERE idSubrevisor = ? AND idArticulo = ?
            ORDER BY fecha DESC
            """;
        
        try (Connection conn = db.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            
            ps.setInt(1, idSubrevisor);
            ps.setInt(2, idArticulo);
            
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Map<String, Object> comentario = new HashMap<>();
                    comentario.put("comentario", rs.getString("comentario"));
                    comentario.put("fecha", rs.getTimestamp("fecha"));
                    comentarios.add(comentario);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return comentarios;
    }
}