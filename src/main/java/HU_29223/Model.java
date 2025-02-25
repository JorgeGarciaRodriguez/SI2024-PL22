package HU_29223;

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
	//Si cambiamos la base de datos habra que cambiar la consulta
	public List<String> getListaArticulosDisponiblesArray() {
		List<String> resultado=new ArrayList<>();
		String sql="SELECT A.titulo"
				+ " FROM Articulo A"
				+ " WHERE A.id NOT IN ("
				+ " SELECT AA.idArticulo"
				+ " FROM Autor_articulo AA"
				+ " WHERE AA.revisa = TRUE)";
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
		String sql="SELECT a.nombre"+
					" FROM Autor a"+
					" JOIN Autor_articulo aa ON a.id = aa.idAutor"+
					" JOIN Articulo ar ON aa.idArticulo = ar.id"+
					" WHERE ar.titulo = ?";
		return db.executeQueryArray(sql, titulo);
	}
	public List<Object[]> getAutores_Organizacion(String titulo){
		String sql="SELECT a.organizacion"+
					" FROM Autor a"+
					" JOIN Autor_articulo aa ON a.id = aa.idAutor"+
					" JOIN Articulo ar ON aa.idArticulo = ar.id"+
					" WHERE ar.titulo = ?";
		return db.executeQueryArray(sql, titulo);
	}
	public List<Object[]> getAutores_Grupo(String titulo){
		String sql="SELECT a.grupo"+
					" FROM Autor a"+
					" JOIN Autor_articulo aa ON a.id = aa.idAutor"+
					" JOIN Articulo ar ON aa.idArticulo = ar.id"+
					" WHERE ar.titulo = ?";
		return db.executeQueryArray(sql, titulo);
	}
	public List<String> getRevisoresDisponibles(String titulo){
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
	
	//POSIBLE IMPLEMENTACION DE ASIGNAR REVISORES CON BASE DE DATOS ACTUALIZADA
	/*public List<String> getRevisoresDisponibles(String titulo){
	List<String> resultado=new ArrayList<>();
	String sql="";
	List<Object[]> resultados=db.executeQueryArray(sql);
		for(Object[] a:resultados) {
			resultado.add((String)a[0]);
		}
	return resultado;
	}
	/*public List<String> getRevisoresAsignados(String titulo){
	List<String> resultado=new ArrayList<>();
	String sql="";
	List<Object[]> resultados=db.executeQueryArray(sql);
		for(Object[] a:resultados) {
			resultado.add((String)a[0]);
		}
	return resultado;
	}
    public int getIdRevisor(String nombre){
		String sql="SELECT id FROM Autor WHERE nombre=?";
		List<Object[]> resultado= db.executeQueryArray(sql, nombre);
		return (int) resultado.get(0)[0];
	}
	public int getIdArticulo(String titulo){
		String sql="SELECT id FROM Articulo WHERE titulo=?";
		List<Object[]> resultado= db.executeQueryArray(sql, titulo);
		return (int) resultado.get(0)[0];
	}
	
	public static final String asignar_revisor="INSERT INTO Revision(idRevision,idRevisor,idArticulo) VALUES (?,?,?)";
	public void asignacion(int idRevisor, int idArticulo){
		int idRevision=ultimoID();
		db.executeUpdate(asignar_revisor,idRevision,idRevisor,idArticulo);
	}
	public int ultimoID() {
		String sql="SELECT COUNT (id) FROM Revisiones";
		List<Object[]> resultado=db.executeQueryArray(sql);
		return (int) resultado.get(0)[0]+1;
	}
	*/

}
