package HU_29229;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JList;

import giis.demo.util.Database;
import giis.demo.util.Util;

public class Model {
	private Database db=new Database();
	
	public Model() {
	    //db.createDatabase(true); // Crea la base de datos si no existe
	    //db.loadDatabase(); // Carga datos iniciales
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
	/*public List<String> getRevisoresDisponibles(String titulo){
		List<String> prueba=new ArrayList<>();
		prueba.add("Pepe");
		prueba.add("Juan");
		return prueba;
	}

	public List<String> getRevisoresAsignados(String titulo){
		List<String> prueba=new ArrayList<>();
		prueba.add("Maria");
		prueba.add("Jesus");
		return prueba;
	}
	*/
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
	public List<String> getRevisoresAsignados(String titulo){
	List<String> resultado=new ArrayList<>();
	String sql = "SELECT p.nombre "
	           + "FROM Revisor r "
	           + "JOIN Persona p ON r.idRevisor = p.id "
	           + "JOIN Revision rev ON rev.idRevisor = r.idRevisor "
	           + "JOIN Articulo art ON rev.idArticulo = art.id "
	           + "WHERE art.titulo = ?";

	List<Object[]> resultados=db.executeQueryArray(sql,titulo);
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
	public int ultimoID() {
		String sql="SELECT MAX (idRevision) FROM Revision";
		List<Object[]> resultado=db.executeQueryArray(sql);
		return (int) resultado.get(0)[0]+1;
	}
	public static final String asignar_revisor="INSERT INTO Revision(idRevision,idRevisor,idArticulo,experto,decision,coment_autor,coment_coor) VALUES (?,?,?,NULL,NULL,NULL,NULL)";
	public void asignacion(int idRevisor, int idArticulo){
		db.executeUpdate(asignar_revisor,ultimoID(),idRevisor,idArticulo);
	}
	public int getIdRevision(String titulo,int idRevisor) {
		String sql="SELECT r.idRevision "
				+ "FROM Revision r "
				+ "JOIN Articulo a ON r.idArticulo = a.id "
				+ "JOIN Revisor rev ON r.idRevisor = rev.idRevisor "
				+ "WHERE a.titulo = ? "
				+ "AND rev.idRevisor = ?";
		List<Object[]> resultado=db.executeQueryArray(sql,titulo,idRevisor);
		return (int) resultado.get(0)[0];
	}
	public static final String borrar_revisor="DELETE FROM Revision WHERE idRevision=?";
	public void desasignacion(int idRevision) {
		db.executeUpdate(borrar_revisor, idRevision);
	}
	
	

}
