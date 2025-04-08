package nueva_version;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import giis.demo.util.Database;

public class NuevaVersion_Model {
	private Database db=new Database();
	
	public NuevaVersion_Model() {
	}
	
	public boolean isAutorValido(int idAutor) {
	    String sql = "SELECT COUNT(*) FROM Autor WHERE idAutor = ?";
	    List<Object[]> resultados = db.executeQueryArray(sql, idAutor);
	    return (Integer) resultados.get(0)[0] > 0;
	}

	public List<String> getListaArticulosAceptadosArray(int idAutor) {
		List<String> resultado=new ArrayList<>();
		String sql = "SELECT a.titulo " +
	             "FROM Articulo a " +
	             "JOIN Autor_articulo aa ON a.id = aa.idArticulo " +
	             "WHERE aa.idAutor = ? " +
	             "AND a.aceptado IN (1, 2) " +
	             "AND a.vers = 0;";
		
		List<Object[]> resultados=db.executeQueryArray(sql,idAutor);
		for(Object[] a:resultados) {
			resultado.add((String)a[0]);
		}
		return resultado;
	}
	
	public int getAutorEnvio(String titulo) {
		String sql = "SELECT p.id " +
	               "FROM Persona p " +
	               "JOIN Autor au ON p.id = au.idAutor " +
	               "JOIN Autor_articulo aa ON au.idAutor = aa.idAutor " +
	               "JOIN Articulo art ON aa.idArticulo = art.id " +
	               "WHERE aa.envia = TRUE AND art.titulo =  ?";
	    List<Object[]> resultados = db.executeQueryArray(sql, titulo);
	    return (int) resultados.get(0)[0];
	}
	
	public String getResumen(String titulo) {
		String sql = "SELECT resumen FROM Articulo WHERE titulo = ? AND vers = 0;";
	    List<Object[]> resultados = db.executeQueryArray(sql, titulo);
	    return (String) resultados.get(0)[0];
	}
	
	public String getPalabrasClave(String titulo) {
		String sql = "SELECT palabras_clave FROM Articulo WHERE titulo = ? AND vers = 0;";
	    List<Object[]> resultados = db.executeQueryArray(sql, titulo);
	    return (String) resultados.get(0)[0];
	}
	
	public String getFichero(String titulo) {
		String sql = "SELECT fichero FROM Articulo WHERE titulo = ? AND vers = 0;";
	    List<Object[]> resultados = db.executeQueryArray(sql, titulo);
	    return (String) resultados.get(0)[0];
	}
	
	public int getID(String titulo) {
		String sql = "SELECT id FROM Articulo WHERE titulo = ? AND vers = 0;";
	    List<Object[]> resultados = db.executeQueryArray(sql, titulo);
		return (int) resultados.get(0)[0];
	}
	
	public boolean noTieneVersion1(String titulo) {
		String sql="SELECT COUNT (*) FROM Articulo " +
		"WHERE titulo = ?";
		List<Object[]> resultados = db.executeQueryArray(sql, titulo);
		return (int)resultados.get(0)[0]<2;
	}
	
	public boolean antes_deadline(String titulo) {
	    String sql = "SELECT deadline FROM Articulo WHERE titulo = ? AND vers = 0;";
	    List<Object[]> resultados = db.executeQueryArray(sql,titulo);

	    if (resultados.isEmpty() || resultados.get(0)[0] == null) {
	        return true; 
	    }

	    Object resultado = resultados.get(0)[0];
	    Date deadline;

	    try {
	        if (resultado instanceof String) {
	            // Convertimos el String a java.sql.Date usando SimpleDateFormat
	            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	            java.util.Date parsedDate = format.parse((String) resultado);
	            deadline = new Date(parsedDate.getTime());
	        } else if (resultado instanceof Timestamp) {
	            deadline = new Date(((Timestamp) resultado).getTime());
	        } else if (resultado instanceof Date) {
	            deadline = (Date) resultado;
	        } else {
	            throw new IllegalArgumentException("Formato de fecha no reconocido: " + resultado.getClass().getName());
	        }
	    } catch (ParseException e) {
	        throw new RuntimeException("Error al parsear la fecha: " + resultado, e);
	    }

	    Date fechaActual = new Date(System.currentTimeMillis());

	    return !fechaActual.after(deadline);
	}
	
	public int ultimoID() {
		String sql="SELECT MAX (id) FROM Articulo";
		List<Object[]> resultado=db.executeQueryArray(sql);
		return (int) resultado.get(0)[0]+1;
	}
	
	public static final String nueva_version =
		    "INSERT INTO Articulo(id, titulo, palabras_clave, resumen, fichero, fecha, aceptado, modificable, deadline, vers) " +
		    "SELECT ?, titulo, ?, ?, ?, fecha, aceptado, modificable, deadline, 1 " +
		    "FROM Articulo WHERE id = ?";
	public void nueva_version(String palabras_clave,String resumen,String fichero,int id) {
		db.executeUpdate(nueva_version,ultimoID(),palabras_clave,resumen,fichero,id);
	}

	public static final String nuevo_envioo ="INSERT INTO Autor_Articulo(idAutor,idArticulo,envia) VALUES (?,?,TRUE)";
	public void nuevo_envio(int idAutorLogueado, int id) {
		db.executeUpdate(nuevo_envioo,idAutorLogueado,id);	
	}
	
}
