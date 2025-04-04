package nueva_version;

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
}
