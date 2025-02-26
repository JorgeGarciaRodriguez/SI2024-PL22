--Primero se deben borrar todas las tablas (de detalle a maestro) y lugo anyadirlas (de maestro a detalle)
--(en este caso en cada aplicacion se usa solo una tabla, por lo que no hace falta)

--Para giis.demo.tkrun:

DROP TABLE IF EXISTS Autor_articulo;
DROP TABLE IF EXISTS Revision;
DROP TABLE IF EXISTS Articulo;
DROP TABLE IF EXISTS Persona;
DROP TABLE IF EXISTS Autor;
DROP TABLE IF EXISTS Revisor;

create table Persona(id int primary key not null, nombre varchar not null, organizacion varchar not null, grupo varchar not null);

create table Revisor(idRevisor int primary key not null, FOREIGN KEY(idRevisor) REFERENCES Persona(id));

create table Autor(idAutor int primary key not null, FOREIGN KEY(idAutor) REFERENCES Persona(id), correo varchar unique not null);

create table Autor_articulo(idAutor int not null, idArticulo int not null,boolean envia not null, PRIMARY KEY(idAutor,idArticulo),
	FOREIGN KEY(idAutor) REFERENCES Autor(id), FOREIGN KEY(idArticulo) REFERENCES Articulo(id));

create table Articulo(id int primary key not null, titulo varchar not null, palabras_clave varchar not null, resumen varchar not null,
 	fichero varchar not null, fecha date not null, boolean decisionfinal not null);
 	
create table Revision(idRevision int not null, idRevisor int not null, idArticulo int not null,experto int not null,decision int not null,coment_autor varchar not null,
	coment_coor varchar not null, PRIMARY KEY(idRevisor,idArticulo));