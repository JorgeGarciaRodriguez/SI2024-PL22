package giis.demo.util;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.commons.dbutils.DbUtils;

/**
 * Encapsula los datos de acceso JDBC, lectura de la configuracion
 * y scripts de base de datos para creacion y carga.
 */
public class Database extends DbUtil {
	//Localizacion de ficheros de configuracion y carga de bases de datos
	private static final String APP_PROPERTIES = "src/main/resources/application.properties";
	private static final String SQL_SCHEMA = "src/main/resources/schema.sql";
	private static final String SQL_LOAD = "src/main/resources/data.sql";
	//parametros de la base de datos leidos de application.properties (base de datos local sin usuario/password)
	private String driver;
	private String url;
	private static boolean databaseCreated=false;

	/**
	 * Crea una instancia, leyendo los parametros de driver y url de application.properties
	 */
	public Database() {
		Properties prop=new Properties();
		try (FileInputStream fs=new FileInputStream(APP_PROPERTIES)) {
			prop.load(fs);
		} catch (IOException e) {
			throw new ApplicationException(e);
		}
		driver=prop.getProperty("datasource.driver");
		url=prop.getProperty("datasource.url");
		if (driver==null || url==null)
			throw new ApplicationException("Configuracion de driver y/o url no encontrada en application.properties");
		DbUtils.loadDriver(driver);
	}
	public String getUrl() {
		return url;
	}
	/** 
	 * Creacion de una base de datos limpia a partir del script schema.sql en src/main/properties
	 * (si onlyOnce=true solo ejecutara el script la primera vez
	 */
	public void createDatabase(boolean onlyOnce) {
		//actua como singleton si onlyOnce=true: solo la primera vez que se instancia para mejorar rendimiento en pruebas
		if (!databaseCreated || !onlyOnce) { 
			executeScript(SQL_SCHEMA);
			databaseCreated=true; //NOSONAR
		}
	}
	/** 
	 * Carga de datos iniciales a partir del script data.sql en src/main/properties
	 * (si onlyOnce=true solo ejecutara el script la primera vez
	 */
	public void loadDatabase() {
		executeScript(SQL_LOAD);
	}
	public int executeQueryForInt(String sql) {
	    try (Connection conn = this.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql);
	         ResultSet rs = stmt.executeQuery()) {

	        if (rs.next()) {
	            return rs.getInt(1); // Devuelve el primer valor de la primera columna
	        } else {
	            return 0; // Si no hay resultados, devuelve 0 (o el valor adecuado)
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return 0; // En caso de error, devuelve 0
	    }
	}
	
	public void executeUpdate(String query, Object... params) {
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            for (int i = 0; i < params.length; i++) {
                pstmt.setObject(i + 1, params[i]);
            }
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
	public void appendToDataSql(String insertStatement) {
	    try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/data.sql", true))) {
	        // Verifica si el archivo está vacío, si es así, agrega el encabezado de los insert.
	        // Para evitar escribir los encabezados una y otra vez.
	        boolean isEmpty = new File("src/main/resources/data.sql").length() == 0;
	        if (isEmpty) {
	            writer.write("--Datos para carga inicial de la base de datos\n\n");
	            writer.write("--Para giis.demo.tkrun:\n");
	        }

	        // Escribe el statement insert en el archivo data.sql dentro de la instrucción insert.
	        writer.write("\ninsert into Autor(id,nombre,organizacion,grupo,revisor,coordinador) values\n");

	        // Escribe el nuevo autor dentro de la misma sentencia insert
	        writer.write(insertStatement); // Aquí se agregan los nuevos valores.

	        // Cierra el escritor
	        writer.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

	
	
}