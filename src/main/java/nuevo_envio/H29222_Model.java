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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

    // Método para buscar autores por nombre o correo
    public List<String[]> buscarAutores(String criterio) {
        List<String[]> resultados = new ArrayList<>();
        String query = "SELECT p.nombre, a.correo, p.organizacion, p.grupo " +
                      "FROM Persona p JOIN Autor a ON p.id = a.idAutor " +
                      "WHERE p.nombre LIKE ? OR a.correo LIKE ?";
        
        try (Connection conn = db.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            
            ps.setString(1, "%" + criterio + "%");
            ps.setString(2, "%" + criterio + "%");
            
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String[] autor = new String[4];
                    autor[0] = rs.getString("nombre");
                    autor[1] = rs.getString("correo");
                    autor[2] = rs.getString("organizacion");
                    autor[3] = rs.getString("grupo");
                    resultados.add(autor);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return resultados;
    }
    
    // Método para obtener los datos de un autor por su correo
    public String[] obtenerAutorPorCorreo(String correo) {
        String query = "SELECT p.nombre, a.correo, p.organizacion, p.grupo " +
                      "FROM Persona p JOIN Autor a ON p.id = a.idAutor " +
                      "WHERE a.correo = ?";
        
        try (Connection conn = db.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            
            ps.setString(1, correo);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String[] autor = new String[4];
                    autor[0] = rs.getString("nombre");
                    autor[1] = rs.getString("correo");
                    autor[2] = rs.getString("organizacion");
                    autor[3] = rs.getString("grupo");
                    return autor;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return null;
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
    private static final String insertarArticulo = "INSERT INTO Articulo (id, titulo, palabras_clave, resumen, fichero, fecha, aceptado, modificable, deadline) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
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
    
    public void insertarArticulo(String titulo, String palabrasClave, String resumen, String fichero, boolean modificable) {
        int idArticulo = ultimoID("Articulo", "id");
        LocalDate fActual = LocalDate.now(); // Obtener fecha actual

        //sumar 5 días
        LocalDate fDeadline = fActual.plusDays(2);
        
        String fechaActual = fActual.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String fechaDeadline = fDeadline.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        
        try {
            db.executeUpdate(insertarArticulo, idArticulo, titulo, palabrasClave, resumen, fichero, fechaActual, null, modificable, fechaDeadline);
            
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

    public void enviarArticuloATrack(int idArticulo, int idTrack, List<String> palabrasClaveSeleccionadas) {
        String palabrasClave = String.join(", ", palabrasClaveSeleccionadas);

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