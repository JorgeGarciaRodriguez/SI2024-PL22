package evaluacion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import giis.demo.util.Database;

public class Evaluacion_Model {
    private Database db = new Database();
    public List<String> obtenerRevisores() {
        List<String> revisores = new ArrayList<>();
        String query = "SELECT r.idRevisor, p.nombre FROM Revisor r JOIN Persona p ON r.idRevisor = p.id";

        try (Connection conn = db.getConnection();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                revisores.add(rs.getInt("idRevisor") + " - " + rs.getString("nombre"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return revisores;
    }
    // Método para obtener los títulos de los artículos asignados a un revisor
    public List<String> obtenerArticulosParaRevisor(int idRevisor) {
        List<String> articulos = new ArrayList<>();
        String query = "SELECT a.titulo " +
                       "FROM Articulo a " +
                       "JOIN ArticuloTrack at ON a.id = at.idArticulo " +
                       "JOIN Track t ON at.idTrack = t.id " +
                       "JOIN RevisorTrack rt ON t.id = rt.idTrack " +
                       "WHERE rt.idRevisor = ? " +
                       "AND a.id NOT IN ( " +
                       "    SELECT aa.idArticulo FROM Autor_articulo aa " +
                       "    JOIN Autor aut ON aa.idAutor = aut.idAutor " +
                       "    WHERE aut.idAutor = ? " +
                       ") " +
                       "AND a.id NOT IN ( " +
                       "    SELECT aa.idArticulo FROM Autor_articulo aa " +
                       "    JOIN Autor aut ON aa.idAutor = aut.idAutor " +
                       "    JOIN Persona p1 ON aut.idAutor = p1.id " +
                       "    JOIN Persona p2 ON p2.id = ? " +
                       "    WHERE p1.grupo = p2.grupo " +
                       ")";

        try (Connection conn = db.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, idRevisor);
            ps.setInt(2, idRevisor);
            ps.setInt(3, idRevisor);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    articulos.add(rs.getString("titulo"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return articulos;
    }

    // Método para verificar si un revisor tiene conflicto de intereses con un artículo
    public boolean tieneConflictoIntereses(int idRevisor, String tituloArticulo) {
        String query = "SELECT COUNT(*) " +
                      "FROM Articulo a " +
                      "JOIN Autor_articulo aa ON a.id = aa.idArticulo " +
                      "JOIN Autor aut ON aa.idAutor = aut.idAutor " +
                      "JOIN Persona p ON aut.idAutor = p.id " +
                      "JOIN Revisor r ON r.idRevisor = ? " +
                      "JOIN Persona p2 ON r.idRevisor = p2.id " +  
                      "WHERE a.titulo = ? AND (r.idRevisor = aut.idAutor OR p2.grupo = p.grupo)";  

        try (Connection conn = db.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, idRevisor);
            ps.setString(2, tituloArticulo);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0; // Si hay al menos una coincidencia, hay conflicto
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Método para registrar la decisión de un revisor sobre un artículo
    public void registrarDecision(int idRevisor, String tituloArticulo, String decision) {
        String query = "INSERT INTO Decision (idRevisor, idArticulo, decision) " +
                       "SELECT ?, a.id, ? FROM Articulo a WHERE a.titulo = ?";

        try (Connection conn = db.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, idRevisor);
            ps.setString(2, decision);
            ps.setString(3, tituloArticulo);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
	public List<String> getListaArticulosArray(int idRevisor) {
		List<String> resultado=new ArrayList<>();
		String sql = "SELECT DISTINCT A.titulo " +
	             "FROM Revision R " +
	             "JOIN Articulo A ON R.idArticulo = A.id " +
	             "JOIN ArticuloTrack AT ON A.id = AT.idArticulo " +
	             "JOIN PalabraClaveTrack PCT ON AT.idTrack = PCT.idTrack " +
	             "JOIN PalabrasClaveRevisor PCR ON R.idRevisor = PCR.idRevisor " +
	             "WHERE PCT.palabra_clave = PCR.palabra_clave " +
	             "AND R.idRevisor = ?";

		
		List<Object[]> resultados=db.executeQueryArray(sql,idRevisor);
		for(Object[] a:resultados) {
			resultado.add((String)a[0]);
		}
		return resultado;
	}
    
    
}