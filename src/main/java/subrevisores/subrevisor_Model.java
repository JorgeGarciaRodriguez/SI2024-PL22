package subrevisores;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import giis.demo.util.Database;

public class subrevisor_Model {
    private Database db = new Database();

    public List<String> obtenerRevisoresMismoTrack(int idRevisorPrincipal, int idTrack) {
        List<String> revisores = new ArrayList<>();
        String query = "SELECT p.id, p.nombre FROM Persona p " +
                      "JOIN Revisor r ON p.id = r.idRevisor " +
                      "JOIN RevisorTrack rt ON r.idRevisor = rt.idRevisor " +
                      "WHERE rt.idTrack = ? " +
                      "AND p.id != ? " +
                      "AND p.id NOT IN (" +
                      "    SELECT idSubrevisor FROM Subrevisor " +
                      "    WHERE idRevisorPrincipal = ? AND idTrack = ? AND estado_invitacion != 'rechazada'" +
                      ")";

        try (Connection conn = db.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            
            ps.setInt(1, idTrack);
            ps.setInt(2, idRevisorPrincipal);
            ps.setInt(3, idRevisorPrincipal);
            ps.setInt(4, idTrack);
            
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                revisores.add(rs.getInt("id") + " - " + rs.getString("nombre"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return revisores;
    }

    public boolean enviarInvitacion(int idRevisorPrincipal, int idSubrevisor, int idTrack) {
        String query = "INSERT INTO Subrevisor (idRevisorPrincipal, idSubrevisor, idTrack, estado_invitacion) " +
                      "VALUES (?, ?, ?, 'pendiente')";
        try (Connection conn = db.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, idRevisorPrincipal);
            ps.setInt(2, idSubrevisor);
            ps.setInt(3, idTrack);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<String> obtenerTracksDeRevisor(int idRevisorPrincipal) {
        List<String> tracks = new ArrayList<>();
        String sql = "SELECT DISTINCT t.id, t.nombre FROM Track t " +
                    "JOIN RevisorTrack rt ON t.id = rt.idTrack " +
                    "WHERE rt.idRevisor = ?";
        
        try (Connection con = db.getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {
            st.setInt(1, idRevisorPrincipal);
            ResultSet rs = st.executeQuery();
            
            while (rs.next()) {
                tracks.add(rs.getInt("id") + " - " + rs.getString("nombre"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tracks;
    }

    public List<String> obtenerTodosRevisores() {
        List<String> revisores = new ArrayList<>();
        String query = "SELECT p.id, p.nombre FROM Persona p JOIN Revisor r ON p.id = r.idRevisor";
        
        try (Connection conn = db.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            
            while (rs.next()) {
                revisores.add(rs.getInt("id") + " - " + rs.getString("nombre"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return revisores;
    }
}