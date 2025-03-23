package ver_revisiones;

import java.util.ArrayList;
import java.util.List;

import giis.demo.util.Database;

public class Model {
private Database db=new Database();
	
	public Model() {
	    //db.createDatabase(true); // Crea la base de datos si no existe
	    //db.loadDatabase(); // Carga datos iniciales
	}
	
	public List<String> getListaArticulosRevisadosArray(int idRevisor) {
		List<String> resultado=new ArrayList<>();
	    String sql = "SELECT DISTINCT art.titulo "
	               + "FROM Articulo art "
	               + "JOIN Revision rev ON art.id = rev.idArticulo "
	               + "WHERE rev.decision IS NOT NULL "
	               + "AND rev.idRevisor = ?";  // Filtra solo para el revisor dado
		
		List<Object[]> resultados=db.executeQueryArray(sql,idRevisor);
		for(Object[] a:resultados) {
			resultado.add((String)a[0]);
		}
		return resultado;
	}
	
	public List<String> getListaArticulosPendientesArray(int idRevisor) {
		List<String> resultado=new ArrayList<>();
	    String sql = "SELECT DISTINCT art.titulo "
	               + "FROM Articulo art "
	               + "JOIN Revision rev ON art.id = rev.idArticulo "
	               + "WHERE rev.decision IS NULL "
	               + "AND rev.idRevisor = ?";  // Filtra solo para el revisor dado
		
		List<Object[]> resultados=db.executeQueryArray(sql,idRevisor);
		for(Object[] a:resultados) {
			resultado.add((String)a[0]);
		}
		return resultado;
	}
	
	public String getComentarioRevisor(String titulo,int idRevisor){
		String sql = "SELECT r.coment_autor " +
	               "FROM Revision r " +
	               "JOIN Articulo a ON r.idArticulo = a.id " +
	               "WHERE a.titulo = ? AND r.idRevisor = ?;";
		List<Object[]> resultados=db.executeQueryArray(sql, titulo, idRevisor);
		return (String) resultados.get(0)[0];
	}
	
	public int getDecision(String titulo,int idRevisor){
		String sql = "SELECT r.decision " +  
		"FROM Revision r "+  
		"JOIN Articulo a ON r.idArticulo = a.id "+ 
		"WHERE a.titulo = ? AND r.idRevisor = ?;";
		List<Object[]> resultados=db.executeQueryArray(sql, titulo, idRevisor);
		return (int) resultados.get(0)[0];
	}
	
	public boolean isRevisorValido(int idRevisor) {
	    String sql = "SELECT COUNT(*) FROM Revisor WHERE idRevisor = ?";
	    List<Object[]> resultados = db.executeQueryArray(sql, idRevisor);
	    return (Integer) resultados.get(0)[0] > 0;
	}

}
