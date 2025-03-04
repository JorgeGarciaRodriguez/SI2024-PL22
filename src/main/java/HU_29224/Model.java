package HU_29224;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import giis.demo.util.Database;

public class Model {
private Database db=new Database();
	
	public Model() {
	   // db.createDatabase(true); // Crea la base de datos si no existe
	   // db.loadDatabase(); // Carga datos iniciales
	    
	}
	
	public int obtenerIdArticuloPorTitulo(String titulo) {
	    String query = "SELECT id FROM Articulo WHERE titulo = ?";
	    int idArticulo = 0;

	    try (Connection conn = db.getConnection();
	         PreparedStatement ps = conn.prepareStatement(query)) {

	        ps.setString(1, titulo);  // Establecemos el título como parámetro
	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) {
	            idArticulo = rs.getInt("id");  // Obtenemos el id del artículo
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return idArticulo;
	}

	
	public List<String> obtenerTitulosArticulos() {
	    List<String> titulos = new ArrayList<>();
	    String query = "SELECT titulo FROM Articulo"; // Consulta para obtener los títulos

	    try (Connection conn = db.getConnection();
	         PreparedStatement ps = conn.prepareStatement(query);
	         ResultSet rs = ps.executeQuery()) {

	        while (rs.next()) {
	            titulos.add(rs.getString("titulo")); // Agrega cada título a la lista
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return titulos; // Retorna la lista de títulos
	}
	
	public int ultimoID(String nombreTabla, String nombreID) {
	    String query = "SELECT MAX(" + nombreID + ") FROM " + nombreTabla;
	    int maxID = 1;  // Si no hay registros, empieza en 1

	    try (PreparedStatement ps = db.getConnection().prepareStatement(query);
	         ResultSet rs = ps.executeQuery()) {
	        
	        if (rs.next()) {
	            maxID = rs.getInt(1) + 1;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return maxID;
	}

	public void guardarRevision(int idRevisor,int idArticulo, String experto, String decision, String comentarioAutor, String comentarioCoor) {
	      // Esto debe obtenerse de alguna manera según la lógica de tu aplicación
	    int id = ultimoID("Revision", "idRevision");

	    String query = "INSERT INTO Revision (idRevision, idRevisor, idArticulo, experto, decision, coment_autor, coment_coor) VALUES (?,?,?,?, ?, ?, ?)";
	    
	    try (Connection conn = db.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(query)) {

	        // Establecer los parámetros en el orden correcto
	        stmt.setInt(1, id);                // idRevision
	        stmt.setInt(2, idRevisor);         // idRevisor
	        stmt.setInt(3, idArticulo);        // idArticulo (usamos el ID obtenido)
	        stmt.setString(4, experto);        // experto
	        stmt.setString(5, decision);       // decision
	        stmt.setString(6, comentarioAutor); // comentarioAutor
	        stmt.setString(7, comentarioCoor);  // comentarioCoordinador

	        // Ejecutar la actualización
	        stmt.executeUpdate(); // Usar executeUpdate() para consultas de modificación (INSERT, UPDATE, DELETE)
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
}




