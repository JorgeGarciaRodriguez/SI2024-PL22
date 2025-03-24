package rechazar_aceptarconcambios;

import java.util.ArrayList;
import java.util.List;

import giis.demo.util.Database;

public class Rechazar_aceptarModel {
	private Database db=new Database();
	
	public Rechazar_aceptarModel() {

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
		String sql = "SELECT SUM(r.decision) " + 
	             "FROM Revision r " + 
	             "JOIN Articulo a ON r.idArticulo = a.id " + 
	             "WHERE a.titulo = ?;";
		List<Object[]> resultados=db.executeQueryArray(sql,titulo);
		 return String.valueOf(resultados.get(0)[0]); 
	}
	
	public int getValoracionGlobalInt(String titulo){
		String sql = "SELECT SUM(r.decision) " + 
	             "FROM Revision r " + 
	             "JOIN Articulo a ON r.idArticulo = a.id " + 
	             "WHERE a.titulo = ?;";
		List<Object[]> resultados=db.executeQueryArray(sql,titulo);
		
	    if (resultados.isEmpty() || resultados.get(0)[0] == null) {
	        return -100; 
	    }

		
		return ((Number) resultados.get(0)[0]).intValue();
	}
	//PRUEBA IMPLEMENTACION CONDICIONESPECIAL
	public boolean cumpleCondicionEspecial(String titulo) {
	    String sql = "SELECT r.decision, r.experto " +
	                 "FROM Revision r " +
	                 "JOIN Articulo a ON r.idArticulo = a.id " +
	                 "JOIN Revisor rev ON r.idRevisor = rev.idRevisor " +
	                 "WHERE a.titulo = ?;";

	    List<Object[]> resultados = db.executeQueryArray(sql, titulo);

	    for (Object[] fila : resultados) {
	        int decision = (int) fila[0];   // Valor de la decisión (-2, -1, 0, 1, 2)
	        String experto = (String) fila[1]; // Nivel del revisor (bajo, normal, alto)

	        // Rechazo fuerte (-2) de un revisor de nivel bajo
	        if (decision == -2 && "Bajo".equalsIgnoreCase(experto)) {
	            return true;
	        }

	        // Rechazo débil (-1) de un revisor de nivel normal o alto
	        if (decision == -1 && ("Normal".equalsIgnoreCase(experto) || "Alto".equalsIgnoreCase(experto))) {
	            return true;
	        }
	    }
	    
	    return false; // No se cumplió ninguna de las condiciones
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
