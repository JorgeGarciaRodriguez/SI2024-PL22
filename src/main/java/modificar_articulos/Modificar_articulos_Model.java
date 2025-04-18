package modificar_articulos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import giis.demo.util.Database;

public class Modificar_articulos_Model {
	private Database db=new Database();

	// OBTENGO LISTA DE ARTÍCULOS Modificables
	public List<String> getListaArticulosModificables() {
		List<String> resultado = new ArrayList<>();
		String sql = "SELECT titulo from Articulo where modificable=TRUE AND CAST(deadline AS DATE) != CURRENT_DATE"
				+ "";

		List<Object[]> resultados = db.executeQueryArray(sql);
		for (Object[] a : resultados) {
			resultado.add((String) a[0]);
		}
		return resultado;
	}

	//Obetiene el ID de un articulo
	public int getIdArticulo(String titulo){
		String sql="SELECT id FROM Articulo WHERE titulo=?";
		List<Object[]> resultado= db.executeQueryArray(sql, titulo);
		return (int) resultado.get(0)[0];
	}
	//Obtiene los ids de los revisores de un articulo
	public List<Integer> getIdRevisores(int id) {
		String sql = "SELECT idRevisor FROM Revision WHERE idArticulo=?";
		List<Object[]> resultado = db.executeQueryArray(sql, id);

		List<Integer> revisores = new ArrayList<>();
		for (Object[] fila : resultado) {
			revisores.add((int) fila[0]);
		}
		return revisores;
	}
	public List<String> getAutores(String titulo){
		String sql="SELECT p.nombre "
				+ "FROM Persona p "
				+ "JOIN Autor a ON p.id = a.idAutor "
				+ "JOIN Autor_articulo aa ON a.idAutor = aa.idAutor "
				+ "JOIN Articulo art ON aa.idArticulo = art.id "
				+ "WHERE art.titulo = ?";
		   List<Object[]> resultados = db.executeQueryArray(sql, titulo);

		    // Convertir la lista de Object[] a List<String>
		    List<String> autores = new ArrayList<>();
		    for (Object[] fila : resultados) {
		        if (fila.length > 0 && fila[0] instanceof String) {
		            autores.add((String) fila[0]);
		        }
		    }
		    
		    return autores;
	}
	public String getPalabrasClave(int id) {
		String sql = "SELECT palabras_clave FROM Articulo WHERE id=?";
		List<Object[]> resultado = db.executeQueryArray(sql, id);
		return (String) resultado.get(0)[0];
		
	}
	public String getFichero(int id) {
		String sql = "SELECT fichero FROM Articulo WHERE id=?";
		List<Object[]> resultado = db.executeQueryArray(sql, id);
		return (String) resultado.get(0)[0];
		
	}
	public String getResumen(int id) {
		String sql = "SELECT resumen FROM Articulo WHERE id=?";
		List<Object[]> resultado = db.executeQueryArray(sql, id);
		return (String) resultado.get(0)[0];
		
	}
	public boolean correoExiste(String correo) {
	    String query = "SELECT COUNT(*) FROM Autor WHERE correo = ?";

	    try (Connection conn = db.getConnection();
	         PreparedStatement ps = conn.prepareStatement(query)) {

	        ps.setString(1, correo);
	        
	        try (ResultSet rs = ps.executeQuery()) {
	            if (rs.next()) {
	                return rs.getInt(1) > 0;
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return false;
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
	public int obtenerIdArticuloPorTitulo(String titulo) {
	    String query = "SELECT id FROM Articulo WHERE titulo = ?";
	    
	    try (Connection conn = db.getConnection();
	         PreparedStatement ps = conn.prepareStatement(query)) {
	        ps.setString(1, titulo);
	        
	        try (ResultSet rs = ps.executeQuery()) {
	            if (rs.next()) {
	                return rs.getInt("id");
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    return -1; // Si no se encuentra, retorna -1
	}
    public int obtenerIdTrackPorNombre(String nombreTrack) {
        String query = "SELECT id FROM Track WHERE nombre = ?";
        try (Connection conn = db.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, nombreTrack);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // Si no se encuentra, retorna -1
    }
	public int obtenerIdAutorPorCorreo(String correo) {
	    String query = "SELECT idAutor FROM Autor WHERE correo = ?";
	    
	    try (Connection conn = db.getConnection();
	         PreparedStatement ps = conn.prepareStatement(query)) {
	        ps.setString(1, correo);
	        
	        try (ResultSet rs = ps.executeQuery()) {
	            if (rs.next()) {
	                return rs.getInt("idAutor");
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    return -1; // Si no se encuentra, retorna -1
	}

	public static final String modificar = "UPDATE Articulo SET palabras_clave = ?, fichero = ?, resumen = ? WHERE id = ?";
	public static final String asignar_persona="INSERT INTO Persona(id,nombre,organizacion,grupo) VALUES (?,?,?,?)";
	public static final String asignar_autor = "INSERT INTO Autor(idAutor, correo) VALUES (?, ?)";
	private static final String insertarArticulo = "INSERT INTO Articulo (id, titulo, palabras_clave, resumen, fichero, fecha, aceptado, modificable, deadline) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	public void updateArticulo(int id, String palabras, String fichero, String resumen) {
		db.executeUpdate(modificar,palabras,fichero,resumen,id);
	}

	public void asignacionPersona(String nombre, String organizacion, String grupo) {
		
	    int id = ultimoID("Persona", "id");
	    
	    try {
	        db.executeUpdate(asignar_persona, id, nombre, organizacion, grupo);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    
	}
	public void asignacionAutor(String correo) {
		int id = ultimoID("Autor", "idAutor");
	    

	    if(!correoExiste(correo)) {
	    try {
	        db.executeUpdate(asignar_autor, id, correo);
	    
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}else {System.out.println("CORREO REPETIDO");}
	    }
	
    // Método para insertar un artículo en la base de datos
	public void modificarArticulo(String titulo, String palabrasClave, String fichero, String resumen) {
        int idArticulo = ultimoID("Articulo", "id");

        try {
            db.executeUpdate(modificar, palabrasClave, fichero, resumen, idArticulo);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
	
	 // Método para enviar un artículo a un track
    public void actualizarArticuloATrack(int idArticulo, int idTrack, List<String> palabrasClaveSeleccionadas) {
        // Convertir la lista de palabras clave a una cadena separada por comas
        String palabrasClave = String.join(", ", palabrasClaveSeleccionadas);

        // Insertar en la tabla ArticuloTrack
        String query = "INSERT INTO ArticuloTrack (idArticulo, idTrack, palabras_clave_seleccionadas) VALUES (?, ?, ?)";
        try (Connection conn = db.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, idArticulo);
            ps.setInt(2, idTrack);
            ps.setString(3, palabrasClave);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
