package login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import giis.demo.util.Database;

public class Login_Model {
	private Database db=new Database();
    private String nombre;
    private String correo;
    private String organizacion;
    private String grupoInvs;

    // Método para validar si los datos son válidos (en este caso solo verificamos que no estén vacíos)
    public boolean validarLogin(String nombre, String correo, String organizacion, String grupoInvs) {
        if (nombre.isEmpty() || correo.isEmpty() || organizacion.isEmpty() || grupoInvs.isEmpty()) {
            return false; // Si alguno de los campos está vacío, se considera no válido
        }
        // Guardamos los datos si son válidos
        this.nombre = nombre;
        this.correo = correo;
        this.organizacion = organizacion;
        this.grupoInvs = grupoInvs;
        return true; // Validación exitosa
    }
    public static final String asignar_persona="INSERT INTO Persona(id,nombre,organizacion,grupo) VALUES (?,?,?,?)";
	public static final String asignar_autor = "INSERT INTO Autor(idAutor, correo) VALUES (?, ?)";
	
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
	
	
	public void asignacionPersona(String nombre, String organizacion, String grupo) {
		
	    int id = ultimoID("Persona", "id");
	    System.out.println("Insertando en Persona con ID: " + id);

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
	        System.out.println("Insertando en Autor con ID: " + id);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}else {System.out.println("CORREO REPETIDO");}
	    }

    // Métodos getter para obtener los datos del usuario
    public String getNombre() {
        return nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public String getOrganizacion() {
        return organizacion;
    }

    public String getGrupoInvs() {
        return grupoInvs;
    }
}

