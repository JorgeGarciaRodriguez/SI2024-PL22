package modificar_articulos;

import java.util.ArrayList;
import java.util.List;

import giis.demo.util.Database;

public class Modificar_articulos_Model {
	private Database db=new Database();

	// OBTENGO LISTA DE ARTÍCULOS Modificables
	public List<String> getListaArticulosModificables() {
		List<String> resultado = new ArrayList<>();
		String sql = "SELECT titulo from Articulo where modificable=TRUE AND CAST(deadline AS DATE) != CURRENT_DATE"
				+ "";

		List<Object[]> resultados = db.executeQueryArray(sql);
		for (Object[] a : resultados) {
			resultado.add((String) a[0]);
		}
		return resultado;
	}

	//Obetiene el ID de un articulo
	public int getIdArticulo(String titulo){
		String sql="SELECT id FROM Articulo WHERE titulo=?";
		List<Object[]> resultado= db.executeQueryArray(sql, titulo);
		return (int) resultado.get(0)[0];
	}
	//Obtiene los ids de los revisores de un articulo
	public List<Integer> getIdRevisores(int id) {
		String sql = "SELECT idRevisor FROM Revision WHERE idArticulo=?";
		List<Object[]> resultado = db.executeQueryArray(sql, id);

		List<Integer> revisores = new ArrayList<>();
		for (Object[] fila : resultado) {
			revisores.add((int) fila[0]);
		}
		return revisores;
	}
	public List<String> getAutores(String titulo){
		String sql="SELECT p.nombre "
				+ "FROM Persona p "
				+ "JOIN Autor a ON p.id = a.idAutor "
				+ "JOIN Autor_articulo aa ON a.idAutor = aa.idAutor "
				+ "JOIN Articulo art ON aa.idArticulo = art.id "
				+ "WHERE art.titulo = ?";
		   List<Object[]> resultados = db.executeQueryArray(sql, titulo);

		    // Convertir la lista de Object[] a List<String>
		    List<String> autores = new ArrayList<>();
		    for (Object[] fila : resultados) {
		        if (fila.length > 0 && fila[0] instanceof String) {
		            autores.add((String) fila[0]);
		        }
		    }
		    
		    return autores;
	}
	//Obtiene el id de una discusion
	public int getIdDiscusion(int id){
		String sql="SELECT d.id_discusion FROM Discusion d JOIN Articulo a on a.id=d.id_articulo WHERE a.id = ?";
		List<Object[]> resultado= db.executeQueryArray(sql, id);
		return (int) resultado.get(0)[0];
	}

	public static final String modificar = "UPDATE Articulo SET palabras_clave = ?, fichero = ?, resumen = ? WHERE id = ?";

	public void updateArticulo(int id, String palabras, String fichero, String resumen) {
		db.executeUpdate(modificar,palabras,fichero,resumen,id);
	}


}
