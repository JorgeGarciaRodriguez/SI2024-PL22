package nueva_version;

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
	
}
