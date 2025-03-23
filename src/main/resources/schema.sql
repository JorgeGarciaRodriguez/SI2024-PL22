	--Primero se deben borrar todas las tablas (de detalle a maestro) y lugo anyadirlas (de maestro a detalle)
	--(en este caso en cada aplicacion se usa solo una tabla, por lo que no hace falta)
	
	--Para giis.demo.tkrun:
	DROP TABLE IF EXISTS Autor_articulo;
	DROP TABLE IF EXISTS Revision;
	DROP TABLE IF EXISTS Articulo;
	DROP TABLE IF EXISTS Persona;
	DROP TABLE IF EXISTS Autor;
	DROP TABLE IF EXISTS Revisor;
	DROP TABLE IF EXISTS Discusion;
	DROP TABLE IF EXISTS Notificacion;
	DROP TABLE IF EXISTS Track;
	DROP TABLE IF EXISTS PalabraClaveTrack;
	DROP TABLE IF EXISTS ArticuloTrack;
	
	create table Persona(id int primary key not null, nombre varchar not null, organizacion varchar not null, grupo varchar not null);
	
	create table Revisor(idRevisor int primary key not null, FOREIGN KEY(idRevisor) REFERENCES Persona(id));
	
	create table Autor(idAutor int primary key not null, correo varchar unique not null, FOREIGN KEY(idAutor) REFERENCES Persona(id));
	
	create table Autor_articulo(idAutor int not null, idArticulo int not null, envia boolean not null, PRIMARY KEY(idAutor,idArticulo),
		FOREIGN KEY(idAutor) REFERENCES Autor(id), FOREIGN KEY(idArticulo) REFERENCES Articulo(id));
	
	create table Articulo(id int primary key not null, titulo varchar not null, palabras_clave varchar not null, resumen varchar not null,
	 	fichero varchar not null, fecha date not null, aceptado int);
	 	
	create table Revision(idRevision int not null, idRevisor int not null, idArticulo int not null,experto varchar,decision int,coment_autor varchar,
		coment_coor varchar, PRIMARY KEY(idRevisor,idArticulo));
		
	CREATE TABLE Discusion (
	    id_discusion INTEGER PRIMARY KEY AUTOINCREMENT, 
	    id_articulo INT, 
	    estado VARCHAR(20) CHECK (estado IN ('abierta', 'cerrada')),
	    FOREIGN KEY (id_articulo) REFERENCES Articulo(id) ON DELETE CASCADE,
	    CONSTRAINT unique_discusion UNIQUE(id_articulo)
	);
		
	CREATE TABLE Notificacion (id INTEGER PRIMARY KEY AUTOINCREMENT, id_revisor INT,id_discusion INT,estado VARCHAR(10) DEFAULT 'pendiente' CHECK (estado IN ('pendiente', 'enviado')),
	    FOREIGN KEY (id_revisor) REFERENCES Revisor(id), FOREIGN KEY (id_discusion) REFERENCES Discusion(id));
	    
	    
	-- Tabla para los tracks
	CREATE TABLE Track (id INT PRIMARY KEY NOT NULL,nombre VARCHAR NOT NULL, deadline DATE NOT NULL);

	-- Tabla para las palabras clave asociadas a cada track
	CREATE TABLE PalabraClaveTrack (id INT PRIMARY KEY NOT NULL,idTrack INT NOT NULL,palabra_clave VARCHAR NOT NULL,FOREIGN KEY (idTrack) REFERENCES Track(id));

	-- Tabla para relacionar artículos con tracks y palabras clave seleccionadas
	CREATE TABLE ArticuloTrack (idArticulo INT NOT NULL,idTrack INT NOT NULL,palabras_clave_seleccionadas VARCHAR NOT NULL, PRIMARY KEY (idArticulo, idTrack),FOREIGN KEY (idArticulo) REFERENCES Articulo(id),FOREIGN KEY (idTrack) REFERENCES Track(id));