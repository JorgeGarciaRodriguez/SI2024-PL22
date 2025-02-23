--Primero se deben borrar todas las tablas (de detalle a maestro) y lugo anyadirlas (de maestro a detalle)
--(en este caso en cada aplicacion se usa solo una tabla, por lo que no hace falta)

--Para giis.demo.tkrun:
drop if exists table Autor;
create table Autor(id int primary key not null, nombre varchar not null, organizacion varchar not null, grupo varchar not null, revisor boolean not null);

drop if exists table Autor_articulo;
create table Autor_articulo(idAutor int not null, idArticulo int not null, PRIMARY KEY(idAutor,idArticulo),
	FOREIGN KEY(idAutor) REFERENCES Autor(id), FOREIGN KEY(idArticulo) REFERENCES Articulo(id));

drop if exists table Articulo;
create table Articulo(id int primary key not null, titulo varchar not null, palabras_clave varchar not null, fichero varchar not null, fecha date not null);