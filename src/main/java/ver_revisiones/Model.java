package ver_revisiones;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
	
	/*public int getDecision(String titulo,int idRevisor){
		String sql = "SELECT r.decision " +  
		"FROM Revision r "+  
		"JOIN Articulo a ON r.idArticulo = a.id "+ 
		"WHERE a.titulo = ? AND r.idRevisor = ?;";
		List<Object[]> resultados=db.executeQueryArray(sql, titulo, idRevisor);
		return (int) resultados.get(0)[0];
	  }*/
	
	public Integer getDecision(String titulo, int idRevisor) {
	    String sql = "SELECT r.decision " +  
	                 "FROM Revision r "+  
	                 "JOIN Articulo a ON r.idArticulo = a.id "+ 
	                 "WHERE a.titulo = ? AND r.idRevisor = ?;";
	    
	    List<Object[]> resultados = db.executeQueryArray(sql, titulo, idRevisor);
	    
	    if (resultados != null && !resultados.isEmpty()) {
	        return (Integer) resultados.get(0)[0];  
	    } else {
	        return null;  
	    }
	}

	
	public boolean isRevisorValido(int idRevisor) {
	    String sql = "SELECT COUNT(*) FROM Revisor WHERE idRevisor = ?";
	    List<Object[]> resultados = db.executeQueryArray(sql, idRevisor);
	    return (Integer) resultados.get(0)[0] > 0;
	}
	
	public int getIdRevision(String titulo,int idRevisor){
		String sql = "SELECT r.idRevision " +  
		"FROM Revision r "+  
		"JOIN Articulo a ON r.idArticulo = a.id "+ 
		"WHERE a.titulo = ? AND r.idRevisor = ?;";
		List<Object[]> resultados=db.executeQueryArray(sql, titulo, idRevisor);
		return (int) resultados.get(0)[0];
	}
	
	public boolean isListaRevisados(int idRevision) {
	    String sql = "SELECT COUNT(*) FROM Revision WHERE idRevision = ? AND decision IS NOT NULL";
	    List<Object[]> resultados = db.executeQueryArray(sql, idRevision);
	    return (Integer) resultados.get(0)[0] > 0;
	}
	
	public List<RevisionesDTO> getDatosTabla(String titulo){
		String sql = "SELECT r.idRevisor, r.decision, r.coment_autor FROM Revision r "
		           + "JOIN Articulo a ON r.idArticulo = a.id WHERE a.titulo = ?";
		List<RevisionesDTO> resultados=db.executeQueryPojo(RevisionesDTO.class,sql,titulo);
		return resultados;
	}
	
	public boolean Revision_antes_deadline() {
	    String sql = "SELECT deadline FROM Revision LIMIT 1;";
	    List<Object[]> resultados = db.executeQueryArray(sql);

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

	
	public static final String modificar_revision = "UPDATE Revision SET coment_autor = ?, decision = ? WHERE idRevision = ?";
	public void modificar_revision(String coment,int decision,int idRevision) {
		db.executeUpdate(modificar_revision,coment,decision,idRevision);
	}

}
