import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JList;

import giis.demo.util.Database;
import giis.demo.util.Util;

public class Model {
	private Database db=new Database();

	public Model() {
		db.createDatabase(true); // Crea la base de datos si no existe
		db.loadDatabase(); // Carga datos iniciales
	}
	//OBTENGO LISTA DE ARTICULOS CON MENOS DE 3 REVISORES Y QUE YA ESTAN ENVIADOS
	public List<String> getListaArticulosDisponiblesArray() {
		List<String> resultado=new ArrayList<>();
		String sql = "SELECT art.titulo "
				+ "FROM Articulo art "
				+ "JOIN Autor_articulo aa ON art.id = aa.idArticulo "
				+ "LEFT JOIN Revision rev ON art.id = rev.idArticulo "
				+ "GROUP BY art.id "
				+ "HAVING aa.envia = TRUE "
				+ "AND COUNT(rev.idRevisor) < 3 ";

		List<Object[]> resultados=db.executeQueryArray(sql);
		for(Object[] a:resultados) {
			resultado.add((String)a[0]);
		}
		return resultado;
	}
	//OBTENGO LISTA DE ARTICULOS QUE TODAVIA NO TIENEN DECISION DE ACEPTACION O RECHAZO
	public List<String> getListaArticulosSinDecisionArray() {
		List<String> resultado=new ArrayList<>();
		String sql = "SELECT titulo FROM Articulo "
				+ "WHERE aceptado IS NULL ";

		List<Object[]> resultados=db.executeQueryArray(sql);
		for(Object[] a:resultados) {
			resultado.add((String)a[0]);
		}
		return resultado;
	}
	//OBTENGO LISTA DE ARTICULOS QUE TODAVIA NO TIENEN DECISION DE ACEPTACION O RECHAZO
	public List<String> getListaArticulosConValMay3() {
		List<String> resultado=new ArrayList<>();
	    String sql = "SELECT a.titulo "
	               + "FROM Articulo a "
	               + "JOIN Revision r ON a.id = r.idArticulo "
	               + "WHERE a.aceptado IS NULL "
	               + "GROUP BY a.id, a.titulo "
	               + "HAVING SUM(r.decision) >= 3";
		List<Object[]> resultados=db.executeQueryArray(sql);
		for(Object[] a:resultados) {
			resultado.add((String)a[0]);
		}
		return resultado;
	}
	// Obtiene los nombres de los revisores asignados a un artículo concreto por su título
	public List<String> getRevisoresDeArticulo(String tituloArticulo) {
		List<String> resultado=new ArrayList<>();
		String sql = "SELECT per.nombre " +
				"FROM Revision rev " +
				"JOIN Revisor revs ON rev.idRevisor = revs.idRevisor " +
				"JOIN Persona per ON revs.idRevisor = per.id " +
				"JOIN Articulo art ON rev.idArticulo = art.id " +
				"WHERE art.titulo = ?";

		List<Object[]> resultados=db.executeQueryArray(sql,tituloArticulo);
		for(Object[] a:resultados) {
			resultado.add((String)a[0]);
		}
		return resultado;
	}
	//Devuelve el nivel de Experto
	public String getNivelExperto(String revisor,String titulo){
		String sql = "SELECT rev.experto " +
	             "FROM Revision rev " +
	             "JOIN Revisor r ON rev.idRevisor = r.idRevisor " +
	             "JOIN Persona p ON r.idRevisor = p.id " +
	             "JOIN Articulo a ON rev.idArticulo = a.id " +
	             "WHERE p.nombre = ? AND a.titulo = ?";

		List<Object[]> resultados=db.executeQueryArray(sql, revisor,titulo);
		return (String) resultados.get(0)[0];
	}
	public String getComentarioAutor(String revisor, String titulo){
		String sql = "SELECT rev.coment_autor " +
	             "FROM Revision rev " +
	             "JOIN Revisor r ON rev.idRevisor = r.idRevisor " +
	             "JOIN Persona p ON r.idRevisor = p.id " +
	             "JOIN Articulo a ON rev.idArticulo = a.id " +
	             "WHERE p.nombre = ? AND a.titulo = ?";

		List<Object[]> resultados=db.executeQueryArray(sql, revisor,titulo);
		return (String) resultados.get(0)[0];
	}
	public String getComentarioCoordinador(String revisor, String titulo){
		String sql = "SELECT rev.coment_coor " +
	             "FROM Revision rev " +
	             "JOIN Revisor r ON rev.idRevisor = r.idRevisor " +
	             "JOIN Persona p ON r.idRevisor = p.id " +
	             "JOIN Articulo a ON rev.idArticulo = a.id " +
	             "WHERE p.nombre = ? AND a.titulo = ?";

		List<Object[]> resultados=db.executeQueryArray(sql, revisor,titulo);
		return (String) resultados.get(0)[0];
	}
	public int getDecisionArticulo(String revisor, String titulo){
		String sql = "SELECT rev.decision " +
	             "FROM Revision rev " +
	             "JOIN Articulo art ON rev.idArticulo = art.id " +
	             "JOIN Revisor r ON rev.idRevisor = r.idRevisor " +
	             "JOIN Persona p ON r.idRevisor = p.id " +
	             "WHERE art.titulo = ? AND p.nombre = ?";
		List<Object[]> resultados=db.executeQueryArray(sql, titulo,revisor);
		return (int) resultados.get(0)[0];
	}
	public String getResumen(String titulo){
		String sql="SELECT resumen FROM Articulo WHERE titulo = ?";
		List<Object[]> resultados=db.executeQueryArray(sql, titulo);
		return (String) resultados.get(0)[0];
	}
	public String getPalabras_clave(String titulo){
		String sql="SELECT palabras_clave FROM Articulo WHERE titulo = ?";
		List<Object[]> resultados=db.executeQueryArray(sql, titulo);
		return (String) resultados.get(0)[0];
	}
	public List<Object[]> getAutores(String titulo){
		String sql="SELECT p.nombre "
				+ "FROM Persona p "
				+ "JOIN Autor a ON p.id = a.idAutor "
				+ "JOIN Autor_articulo aa ON a.idAutor = aa.idAutor "
				+ "JOIN Articulo art ON aa.idArticulo = art.id "
				+ "WHERE art.titulo = ?";
		return db.executeQueryArray(sql, titulo);
	}
	public List<Object[]> getAutores_Organizacion(String titulo){
		String sql="SELECT p.organizacion "
				+ "FROM Persona p "
				+ "JOIN Autor a ON p.id = a.idAutor "
				+ "JOIN Autor_articulo aa ON a.idAutor = aa.idAutor "
				+ "JOIN Articulo art ON aa.idArticulo = art.id "
				+ "WHERE art.titulo = ?";

		return db.executeQueryArray(sql, titulo);
	}
	public List<Object[]> getAutores_Grupo(String titulo){
		String sql="SELECT p.grupo "
				+ "FROM Persona p "
				+ "JOIN Autor a ON p.id = a.idAutor "
				+ "JOIN Autor_articulo aa ON a.idAutor = aa.idAutor "
				+ "JOIN Articulo art ON aa.idArticulo = art.id "
				+ "WHERE art.titulo = ?";
		return db.executeQueryArray(sql, titulo);
	}

	//POSIBLE IMPLEMENTACION DE ASIGNAR REVISORES
	public List<String> getRevisoresDisponibles(String titulo){
		List<String> resultado=new ArrayList<>();
		String sql = "SELECT p.nombre "
				+ "FROM Revisor r "
				+ "JOIN Persona p ON r.idRevisor = p.id "
				+ "WHERE NOT EXISTS ( "
				+ "    SELECT * "
				+ "    FROM Autor_articulo aa "
				+ "    JOIN Articulo art ON aa.idArticulo = art.id "
				+ "    JOIN Autor a ON aa.idAutor = a.idAutor "
				+ "    JOIN Persona ap ON a.idAutor = ap.id "
				+ "    WHERE art.titulo = ? "
				+ "    AND (a.idAutor = r.idRevisor OR ap.grupo = p.grupo) "
				+ ") "
				+ "AND NOT EXISTS ( "
				+ "    SELECT * "
				+ "    FROM Revision rev "
				+ "    WHERE rev.idRevisor = r.idRevisor "
				+ "    AND rev.idArticulo = ( "
				+ "        SELECT id "
				+ "        FROM Articulo "
				+ "        WHERE titulo = ? "
				+ "    ) "
				+ ");";

		List<Object[]> resultados=db.executeQueryArray(sql,titulo,titulo);
		for(Object[] a:resultados) {
			resultado.add((String)a[0]);
		}
		return resultado;
	}

	//POSIBLE INSERCION EN LA BASE DE DATOS
	public int getIdRevisor(String nombre){
		String sql="SELECT r.idRevisor "
				+ "FROM Revisor r "
				+ "JOIN Persona p ON r.idRevisor = p.id "
				+ "WHERE p.nombre = ?";
		List<Object[]> resultado= db.executeQueryArray(sql, nombre);
		return (int) resultado.get(0)[0];
	}
	public int getIdArticulo(String titulo){
		String sql="SELECT id FROM Articulo WHERE titulo=?";
		List<Object[]> resultado= db.executeQueryArray(sql, titulo);
		return (int) resultado.get(0)[0];
	}
	public String getPalabrasClave(String titulo){
		String sql="SELECT palabras_clave FROM Articulo WHERE titulo=?";
		List<Object[]> resultado= db.executeQueryArray(sql, titulo);
		return String.valueOf(resultado.get(0)[0]);
	}
	public String getFichero(String titulo){
		String sql="SELECT fichero FROM Articulo WHERE titulo=?";
		List<Object[]> resultado= db.executeQueryArray(sql, titulo);
		return String.valueOf(resultado.get(0)[0]);
	}
	public String getFecha(String titulo){
		String sql="SELECT palabras_clave FROM Articulo WHERE titulo=?";
		List<Object[]> resultado= db.executeQueryArray(sql, titulo);
		return String.valueOf(resultado.get(0)[0]);
	}
	public String getDecisionFinal(String titulo){
		String sql="SELECT palabras_clave FROM Articulo WHERE titulo=?";
		List<Object[]> resultado= db.executeQueryArray(sql, titulo);
		return String.valueOf(resultado.get(0)[0]);
	}
	public String getAceptado(String titulo){
		String sql="SELECT palabras_clave FROM Articulo WHERE titulo=?";
		List<Object[]> resultado= db.executeQueryArray(sql, titulo);
		return String.valueOf(resultado.get(0)[0]);
	}
	public int ultimoID() {
		String sql="SELECT MAX (idRevision) FROM Revision";
		List<Object[]> resultado=db.executeQueryArray(sql);
		return (int) resultado.get(0)[0]+1;
	}
	public static final String asignar_revisor="INSERT INTO Revision(idRevision,idRevisor,idArticulo,experto,decision,coment_autor,coment_coor) VALUES (?,?,?,NULL,NULL,NULL,NULL)";
	public static final String aceptar_articulo = "UPDATE Articulo SET titulo = ?, palabras_clave = ?, resumen = ?, fichero = ?, fecha = ?, decisionfinal = ?, aceptado = TRUE WHERE id = ?";
	public static final String denegar_articulo = "UPDATE Articulo SET titulo = ?, palabras_clave = ?, resumen = ?, fichero = ?, fecha = ?, decisionfinal = ?, aceptado = FALSE WHERE id = ?";

	public void asignacion(int idRevisor, int idArticulo){
		db.executeUpdate(asignar_revisor,ultimoID(),idRevisor,idArticulo);
	}
	public void updateAceptaArticulo(String titulo,String palabras_clave,String resumen,String fichero, String fecha, String decision_final,int id) {
		db.executeUpdate(aceptar_articulo,titulo,palabras_clave,resumen,fichero,fecha,decision_final,id);
	}
	public void updateDenegarArticulo(String titulo,String palabras_clave,String resumen,String fichero, String fecha, String decision_final,int id) {
		db.executeUpdate(denegar_articulo,titulo,palabras_clave,resumen,fichero,fecha,decision_final,id);
	}


}