package rechazar_articulos;

import java.util.ArrayList;
import java.util.List;

import giis.demo.util.Database;

public class Model {
	private Database db=new Database();
	
	public Model() {

	}
	public List<String> getListaArticulosPendientesArray() {
		List<String> resultado=new ArrayList<>();
		String sql = "SELECT titulo FROM Articulo WHERE aceptado IS NULL;";
		List<Object[]> resultados=db.executeQueryArray(sql);
		for(Object[] a:resultados) {
			resultado.add((String)a[0]);
		}
		return resultado;
	} 
	
	public String getValoracionGlobal(String titulo){
		String sql = "SELECT r.decision " + 
	             "FROM Revision r " + 
	             "JOIN Articulo a ON r.idArticulo = a.id " + 
	             "WHERE a.titulo = '" + titulo + "';";
		List<Object[]> resultados=db.executeQueryArray(sql);
		 return String.valueOf(resultados.get(0)[0]); 
	}
	
	public int getValoracionGlobalInt(String titulo){
		String sql = "SELECT r.decision " + 
	             "FROM Revision r " + 
	             "JOIN Articulo a ON r.idArticulo = a.id " + 
	             "WHERE a.titulo = '" + titulo + "';";
		List<Object[]> resultados=db.executeQueryArray(sql);
		 return (int) resultados.get(0)[0]; 
	}
	
	public static final String rechazar_articulo="UPDATE Articulo SET aceptado = 0 WHERE titulo = ?";
	public void rechazar(String titulo) {
		db.executeUpdate(rechazar_articulo,titulo);
	}
	
	public static final String aceptar_concambios_articulo="UPDATE Articulo SET aceptado = 1 WHERE titulo = ?";
	public void aceptar_concambios(String titulo) {
		db.executeUpdate(aceptar_concambios_articulo,titulo);
	}
}
