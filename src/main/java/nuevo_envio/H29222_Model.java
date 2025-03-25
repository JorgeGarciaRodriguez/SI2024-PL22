package nuevo_envio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JList;
import giis.demo.util.Database;
import giis.demo.util.Util;
import giis.demo.util.DbUtil;

public class H29222_Model {
	private Database db=new Database();
	
	public H29222_Model() {
	   // db.createDatabase(true); // Crea la base de datos si no existe
	   // db.loadDatabase(); // Carga datos iniciales
	    
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


	
	public static final String asignar_persona="INSERT INTO Persona(id,nombre,organizacion,grupo) VALUES (?,?,?,?)";
	public static final String asignar_autor = "INSERT INTO Autor(idAutor, correo) VALUES (?, ?)";
	private static final String insertarArticulo = "INSERT INTO Articulo (id, titulo, palabras_clave, resumen, fichero, fecha, aceptado) VALUES (?, ?, ?, ?, ?, ?, ?)";
	private static final String asignarAutorArticulo = "INSERT INTO Autor_articulo (idAutor, idArticulo, envia) VALUES (?, ?, ?)";


	public void asignacionPersona(String nombre, String organizacion, String grupo) {
		
	    int id = ultimoID("Persona", "id");
	    
	    try {
	        db.executeUpdate(asignar_persona, id, nombre, organizacion, grupo);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    
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
	public void insertarArticulo(String titulo, String palabrasClave, String resumen, String fichero) {
        int idArticulo = ultimoID("Articulo", "id");
        String fechaActual = new SimpleDateFormat("yyyy-MM-dd").format(new Date()); // Obtener fecha actual

        try {
            db.executeUpdate(insertarArticulo, idArticulo, titulo, palabrasClave, resumen, fichero, fechaActual, null);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
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

	public void asignarAutorArticulo(int idArticulo, int idAutor) {
	    String query = asignarAutorArticulo;

	    try (Connection conn = db.getConnection();
	         PreparedStatement ps = conn.prepareStatement(query)) {
	        ps.setInt(1, idArticulo);
	        ps.setInt(2, idAutor);
	        ps.setBoolean(3, true);
	        ps.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	
	
    // Método para obtener todos los nombres de los tracks
    public List<String> obtenerNombresTracks() {
        List<String> nombresTracks = new ArrayList<>();
        String query = "SELECT nombre FROM Track";

        try (Connection conn = db.getConnection();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                nombresTracks.add(rs.getString("nombre"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return nombresTracks;
    }

    // Método para obtener las palabras clave de un track específico
    public List<String> obtenerPalabrasClavePorTrack(String nombreTrack) {
        List<String> palabrasClave = new ArrayList<>();
        String query = "SELECT palabra_clave FROM PalabraClaveTrack WHERE idTrack = (SELECT id FROM Track WHERE nombre = ?)";

        try (Connection conn = db.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, nombreTrack);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    palabrasClave.add(rs.getString("palabra_clave"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return palabrasClave;
    }

    
 // Método para enviar un artículo a un track
    public void enviarArticuloATrack(int idArticulo, int idTrack, List<String> palabrasClaveSeleccionadas) {
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
}



