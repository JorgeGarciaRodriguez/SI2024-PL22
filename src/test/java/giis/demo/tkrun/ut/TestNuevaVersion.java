package giis.demo.tkrun.ut;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.Before;

import giis.demo.util.Database;
import giis.demo.util.Util;
import nueva_version.NuevaVersion_Model;

public class TestNuevaVersion {

    private NuevaVersion_Model model;
    private Database db;
	@Before
	public void setUp() {
        db = new Database();
        db.createDatabase(true);
        loadCleanDatabase(db); 
        model = new NuevaVersion_Model();
	}
	@After
	public void tearDown(){
	}
	
	public static void loadCleanDatabase(Database db) {
		db.executeBatch(new String[] {
				"DELETE FROM ARTICULOS",
				"INSERT INTO Articulo(id, titulo, palabras_clave, resumen, fichero, vers) VALUES (50, 'Artículo Versionable', 'original', 'original', 'original.pdf', 0)",
				"INSERT INTO Articulo(id, titulo, palabras_clave, resumen, fichero, vers) VALUES (100, 'Artículo Borrable', 'original', 'original', 'original.pdf', 0)",
				"INSERT INTO Articulo(id, titulo, palabras_clave, resumen, fichero, vers) VALUES (101, 'Artículo Borrable', 'nuevo', 'nuevo', 'nuevo.pdf', 1)",
				"INSERT INTO Articulo(id, titulo, palabras_clave, resumen, fichero, vers) VALUES (500, 'Artículo Actualizable', 'original', 'original', 'original.pdf', 0)",
				"INSERT INTO Articulo(id, titulo, palabras_clave, resumen, fichero, vers) VALUES (501, 'Artículo Actualizable', 'original', 'original', 'original.pdf', 1)"
			});
	}
	
	public void TestCreacionNuevaVersion() {
		//Comprobamos que existe el artículo original
        List<Object[]> articuloOriginal = db.executeQueryArray("SELECT titulo FROM Articulo WHERE titulo = 'Artículo Versionable' AND vers = 0");
        assertFalse("Debe existir el artículo original", articuloOriginal.isEmpty());
        
        //Creamos nueva version
		int elementos_antes = db.executeQueryForInt("SELECT COUNT(*) FROM Articulos");
		String palabrasClave="IA,Machine Learning";
		String resumen="Resumen de prueba";
		String fichero="mereketengue.pdf";
		int idOriginal=model.getID("Artículo Versionable");
		model.nueva_version(palabrasClave,resumen,fichero,idOriginal);
		
		//Verificamos que hay un artículo más
		int elementos_despues = db.executeQueryForInt("SELECT COUNT(*) FROM Articulos");
		assertEquals("La tabla debería tener un artículo más", elementos_antes + 1, elementos_despues);
		
		//Verificamos que se creó la nueva versión
        int contadorNuevaVersion = db.executeQueryForInt("SELECT COUNT(*) FROM Articulo WHERE titulo = 'Artículo Versionable' AND vers = 1");
        assertEquals("Debería existir la nueva versión", 1, contadorNuevaVersion);
        
        //Verificamos que los campos específicos se actualizaron correctamente
        List<Object[]> nuevaVersion = db.executeQueryArray("SELECT palabras_clave, resumen, fichero FROM Articulo WHERE titulo = 'Artículo Versionable' AND vers = 1");   
        assertEquals("Palabras clave actualizadas", palabrasClave, nuevaVersion.get(0)[0]);
        assertEquals("Resumen actualizado", resumen, nuevaVersion.get(0)[1]);
        assertEquals("Fichero actualizado", fichero, nuevaVersion.get(0)[2]);
        
        //Verificamos que los campos no modificados se copiaron correctamente
        List<Object[]> camposCopiados = db.executeQueryArray("SELECT fecha, aceptado, modificable, deadline FROM Articulo WHERE titulo = 'Artículo Versionable' AND vers = 1");    
        assertEquals("Fecha copiada correctamente", articuloOriginal.get(0)[1], camposCopiados.get(0)[0]);
        assertEquals("Estado aceptado copiado", articuloOriginal.get(0)[2], camposCopiados.get(0)[1]);
        assertEquals("Modificable copiado", articuloOriginal.get(0)[3], camposCopiados.get(0)[2]);
        assertEquals("Deadline copiado", articuloOriginal.get(0)[4], camposCopiados.get(0)[3]);
        
        //Verificamos que el ID de la nueva versión es mayor que el original
        int idNuevaVersion = db.executeQueryForInt("SELECT id FROM Articulo WHERE titulo = 'Visión por Computadora' AND vers = 1");
        assertTrue("El ID de la nueva versión debe ser mayor", idNuevaVersion > idOriginal);
	}
	
	public void TestBorradoNuevaVersion() {
		//Borramos la nueva versión
		int elementos_antes = db.executeQueryForInt("SELECT COUNT(*) FROM Articulos");
		String titulo="Articulo borrable";
		model.borrar_nueva_version_articulo(titulo);
		
		//Verificamos que hay un articulo menos en la tabla Articulos
		int elementos_despues = db.executeQueryForInt("SELECT COUNT(*) FROM Articulos");
		assertEquals("La tabla debería tener un artículo menos", elementos_antes, elementos_despues + 1);
		
		//Verificamos que se borró específicamente la versión 1
		int numeroVersiones1 = db.executeQueryForInt("SELECT COUNT(*) FROM Articulo WHERE titulo = 'Artículo Borrable' AND vers = 1");
	    assertEquals("La versión 1 debería haberse borrado", 0, numeroVersiones1);
	    
	    //Verificamos que la version 0 sigue existiendo
        int numeroVersiones0 = db.executeQueryForInt("SELECT COUNT(*) FROM Articulo WHERE titulo = 'Artículo Borrable' AND vers = 0");
        assertEquals("La versión 0 debería permanecer", 1, numeroVersiones0);
	}
	
	public void testActualizacionNuevaVersion() {
	    //Actualizamos la nueva versión
	    model.actualizacion_nueva_version("nuevo.pdf", "nuevas palabras", "nuevo resumen", "Artículo Actualizable");
	    
	    //Verificamos los cambios
	    List<Object[]> resultados = db.executeQueryArray("SELECT palabras_clave, resumen, fichero FROM Articulo WHERE titulo = 'Artículo Actualizable' AND vers = 1");
	    
	    assertEquals(1, resultados.size());
	    assertEquals("nuevas palabras", resultados.get(0)[0]);
	    assertEquals("nuevo resumen", resultados.get(0)[1]);
	    assertEquals("nuevo.pdf", resultados.get(0)[2]);
	    
	    //Verificamos que la versión 0 no cambió
	    resultados = db.executeQueryArray("SELECT palabras_clave, resumen, fichero FROM Articulo WHERE titulo = 'Artículo Actualizable' AND vers = 0");
	    assertEquals("original", resultados.get(0)[0]);
	}
	
}

