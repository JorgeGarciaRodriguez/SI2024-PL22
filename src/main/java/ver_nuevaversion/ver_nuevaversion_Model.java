package ver_nuevaversion;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import giis.demo.util.Database;

public class ver_nuevaversion_Model {
	private Database db=new Database();
	
	public ver_nuevaversion_Model() {
	}
	
	public boolean isAutorValido(int idAutor) {
	    String sql = "SELECT COUNT(*) FROM Autor WHERE idAutor = ?";
	    List<Object[]> resultados = db.executeQueryArray(sql, idAutor);
	    return (Integer) resultados.get(0)[0] > 0;
	}

	public List<String> getListaArticulosOriginales(int idAutor) {
		List<String> resultado=new ArrayList<>();
		String sql = "SELECT a.titulo " +
	             "FROM Articulo a " +
	             "JOIN Autor_articulo aa ON a.id = aa.idArticulo " +
	             "WHERE a.aceptado IN (1, 2) " +
	             "AND a.vers = 0 " +
	             "AND aa.envia = TRUE " +
	             "AND aa.idAutor = ?";
		List<Object[]> resultados=db.executeQueryArray(sql,idAutor);
		for(Object[] a:resultados) {
			resultado.add((String)a[0]);
		}
		return resultado;
	}
	
	public List<String> getListaArticulosNuevos(int idAutor) {
		List<String> resultado=new ArrayList<>();
		String sql = "SELECT a.titulo " +
	             "FROM Articulo a " +
	             "JOIN Autor_articulo aa ON a.id = aa.idArticulo " +
	             "WHERE a.aceptado IN (1, 2) " +
	             "AND a.vers = 1 " +
	             "AND aa.envia = TRUE " +
	             "AND aa.idAutor = ?";

		List<Object[]> resultados=db.executeQueryArray(sql,idAutor);
		for(Object[] a:resultados) {
			resultado.add((String)a[0]);
		}
		return resultado;
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
	
	public static final String actualizarArticulo = "UPDATE Articulo SET fichero = ?, palabras_clave = ?, resumen = ? WHERE titulo = ? AND vers = 1";
	public void actualizacion_nueva_version(String palabras_clave,String resumen,String fichero) {
		db.executeUpdate(actualizarArticulo,palabras_clave,resumen,fichero);
	}
	
	public static final String borrarArticuloYRelacion = 
		    "DELETE FROM Autor_articulo WHERE idArticulo = (SELECT id FROM Articulo WHERE titulo = ? AND vers = 1);" +
		    "DELETE FROM Articulo WHERE titulo = ? AND vers = 1";
	public void borrar_nueva_version(String titulo) {
		db.executeUpdate(borrarArticuloYRelacion,titulo);
	}

}
