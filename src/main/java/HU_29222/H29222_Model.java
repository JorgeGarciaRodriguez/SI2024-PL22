package HU_29222;

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
	private static final String insertarArticulo = "INSERT INTO Articulo (id, titulo, palabras_clave, resumen, fichero, fecha, decisionfinal) VALUES (?, ?, ?, ?, ?, ?, ?)";
	private static final String asignarAutorArticulo = "INSERT INTO Articulo_Autor(idArticulo, idAutor) VALUES (?, ?)";


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
	
    // Método para insertar un artículo en la base de datos
	public void insertarArticulo(String titulo, String palabrasClave, String resumen, String fichero) {
        int idArticulo = ultimoID("Articulo", "id");
        String fechaActual = new SimpleDateFormat("yyyy-MM-dd").format(new Date()); // Obtener fecha actual

        try {
            db.executeUpdate(insertarArticulo, idArticulo, titulo, palabrasClave, resumen, fichero, fechaActual, null);
            System.out.println("Artículo insertado con ID: " + idArticulo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
	
}



