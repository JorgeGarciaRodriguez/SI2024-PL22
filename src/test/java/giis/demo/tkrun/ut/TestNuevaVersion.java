package giis.demo.tkrun.ut;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import giis.demo.util.Database;
import nueva_version.NuevaVersion_Model;

public class TestNuevaVersion {
    private NuevaVersion_Model model;
    private Database db;
	@BeforeEach
	public void setUp() {
        db = new Database();
        db.createDatabase(true);
        loadCleanDatabase(db); 
        model = new NuevaVersion_Model();
	}
	@AfterEach
	public void tearDown(){
	}
	
	public static void loadCleanDatabase(Database db) {
		db.executeBatch(new String[] {
				"DELETE FROM ARTICULO",
				"INSERT INTO Articulo(id, titulo, palabras_clave, resumen, fichero, fecha, aceptado, modificable, vers, deadline) VALUES (50, 'Artículo Versionable', 'original', 'original', 'original.pdf','2024-01-10', 2, TRUE, 0, '2026-04-06')",
				"INSERT INTO Articulo(id, titulo, palabras_clave, resumen, fichero, vers) VALUES (100, 'Artículo Borrable', 'original', 'original', 'original.pdf', 0)",
				"INSERT INTO Articulo(id, titulo, palabras_clave, resumen, fichero, vers) VALUES (101, 'Artículo Borrable', 'nuevo', 'nuevo', 'nuevo.pdf', 1)",
				"INSERT INTO Articulo(id, titulo, palabras_clave, resumen, fichero, vers) VALUES (500, 'Artículo Actualizable', 'original', 'original', 'original.pdf', 0)",
				"INSERT INTO Articulo(id, titulo, palabras_clave, resumen, fichero, vers) VALUES (501, 'Artículo Actualizable', 'original', 'original', 'original.pdf', 1)"
			});
	}
	@Test
	public void TestCreacionNuevaVersion() {
		//Comprobamos que existe el artículo original
        List<Object[]> articuloOriginal = db.executeQueryArray("SELECT titulo, fecha, aceptado, modificable, deadline FROM Articulo WHERE titulo = 'Artículo Versionable' AND vers = 0");
        assertFalse(articuloOriginal.isEmpty(),"Debe existir el artículo original");
        
        //Creamos nueva version
		int elementos_antes = db.executeQueryForInt("SELECT COUNT(*) FROM Articulo");
		String palabrasClave="IA,Machine Learning";
		String resumen="Resumen de prueba";
		String fichero="mereketengue.pdf";
		int idOriginal=model.getID("Artículo Versionable");
		model.nueva_version(palabrasClave,resumen,fichero,idOriginal);
		
		//Verificamos que hay un artículo más
		int elementos_despues = db.executeQueryForInt("SELECT COUNT(*) FROM Articulo");
		assertEquals(elementos_antes + 1, elementos_despues, "La tabla debería tener un artículo más");
		
		//Verificamos que se creó la nueva versión
        int contadorNuevaVersion = db.executeQueryForInt("SELECT COUNT(*) FROM Articulo WHERE titulo = 'Artículo Versionable' AND vers = 1");
        assertEquals(1, contadorNuevaVersion, "Debería existir la nueva versión");
        
        //Verificamos que los campos específicos se actualizaron correctamente
        List<Object[]> nuevaVersion = db.executeQueryArray("SELECT palabras_clave, resumen, fichero FROM Articulo WHERE titulo = 'Artículo Versionable' AND vers = 1");   
        assertEquals(palabrasClave, nuevaVersion.get(0)[0], "Palabras clave actualizadas");
        assertEquals(resumen, nuevaVersion.get(0)[1], "Resumen actualizado");
        assertEquals(fichero, nuevaVersion.get(0)[2], "Fichero actualizado");
        
        //Verificamos que los campos no modificados se copiaron correctamente
        List<Object[]> camposCopiados = db.executeQueryArray("SELECT fecha, aceptado, modificable, deadline FROM Articulo WHERE titulo = 'Artículo Versionable' AND vers = 1");    
        assertEquals(articuloOriginal.get(0)[1], camposCopiados.get(0)[0], "Fecha copiada correctamente");
        assertEquals(articuloOriginal.get(0)[2], camposCopiados.get(0)[1], "Estado aceptado copiado");
        assertEquals(articuloOriginal.get(0)[3], camposCopiados.get(0)[2], "Modificable copiado");
        assertEquals(articuloOriginal.get(0)[4], camposCopiados.get(0)[3], "Deadline copiado");
        
        //Verificamos que el ID de la nueva versión es mayor que el original
        int idNuevaVersion = db.executeQueryForInt("SELECT id FROM Articulo WHERE titulo = 'Artículo Versionable' AND vers = 1");
        assertTrue(idNuevaVersion > idOriginal, "El ID de la nueva versión debe ser mayor");
	}
	@Test
	public void TestBorradoNuevaVersion() {
		//Borramos la nueva versión
		int elementos_antes = db.executeQueryForInt("SELECT COUNT(*) FROM Articulo");
		model.borrar_nueva_version_articulo("Artículo Borrable");
		
		//Verificamos que hay un articulo menos en la tabla Articulo
		int elementos_despues = db.executeQueryForInt("SELECT COUNT(*) FROM Articulo");
		assertEquals(elementos_antes, elementos_despues + 1, "La tabla debería tener un artículo menos");
		
		//Verificamos que se borró específicamente la versión 1
		int numeroVersiones1 = db.executeQueryForInt("SELECT COUNT(*) FROM Articulo WHERE titulo = 'Artículo Borrable' AND vers = 1");
		assertEquals(0, numeroVersiones1, "La versión 1 debería haberse borrado");
	    
	    //Verificamos que la version 0 sigue existiendo
        int numeroVersiones0 = db.executeQueryForInt("SELECT COUNT(*) FROM Articulo WHERE titulo = 'Artículo Borrable' AND vers = 0");
        assertEquals(1, numeroVersiones0, "La versión 0 debería permanecer");
	}
	@Test
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

