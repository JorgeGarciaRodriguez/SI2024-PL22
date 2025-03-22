package Discusiones;

import java.util.ArrayList;
import java.util.List;

import giis.demo.util.Database;

public class HU29511_Model {
	private Database db=new Database();

	// OBTENGO LISTA DE ARTÍCULOS DUDOSOS
	public List<String> getListaArticulosDudosos() {
		List<String> resultado = new ArrayList<>();
		String sql = "WITH Valoraciones AS ("
				+ "    SELECT idArticulo, SUM(decision) AS valoracion_global "
				+ "    FROM Revision "
				+ "    GROUP BY idArticulo "
				+ "), Criterios AS ("
				+ "    SELECT DISTINCT r.idArticulo "
				+ "    FROM Revision r "
				+ "    JOIN Valoraciones v ON r.idArticulo = v.idArticulo "
				+ "    WHERE "
				+ "        (v.valoracion_global = 1 AND "
				+ "            ((r.decision = -2 AND r.experto IN ('Normal', 'Medio', 'Bajo')) OR "
				+ "             (r.decision = -1 AND r.experto IN ('Medio', 'Alto')))) "
				+ "        OR (v.valoracion_global = 0 AND "
				+ "            ((r.decision = 2 AND r.experto = 'Alto') OR "
				+ "             (r.decision = -1 AND r.experto IN ('Normal', 'Bajo')))) "
				+ ") "
				+ "SELECT a.titulo FROM Articulo a "
				+ "JOIN Criterios c ON a.id = c.idArticulo";

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
	//Obtiene el id de una discusion
	public int getIdDiscusion(int id){
		String sql="SELECT d.id_discusion FROM Discusion d JOIN Articulo a on a.id=d.id_articulo WHERE a.id = ?";
		List<Object[]> resultado= db.executeQueryArray(sql, id);
		return (int) resultado.get(0)[0];
	}

	public static final String discutir_articulo = "INSERT OR IGNORE INTO Discusion (id_articulo, estado) VALUES (?, 'abierta')";
	public static final String notificacion = "INSERT INTO Notificacion (id_revisor, id_discusion, estado) VALUES (?, ?, 'enviado')";
	public void insertDiscutirArticulo(int id) {
		db.executeUpdate(discutir_articulo,id);
	}
	public void insertNotificarRevisores(int id_Revisor,int id_Discusion) {
		db.executeUpdate(notificacion,id_Revisor,id_Discusion);
	}


}
