--Datos para carga inicial de la base de datos

--Para giis.demo.tkrun:
delete from Autor;
insert into Autor(id,nombre,organizacion,grupo,revisor) values 
	(1, 'Ana García', 'Universidad de Madrid', 'IA y ML', TRUE),
	(2, 'Luis Fernández', 'TechCorp', 'Desarrollo Web', FALSE),
	(3, 'María López', 'InnovateX', 'Ciencia de Datos', TRUE),
	(4, 'Carlos Sánchez', 'Data Solutions', 'Big Data', FALSE),
	(5, 'Laura Martín', 'StartUp Inc.', 'Seguridad Informática', TRUE);

delete from Autor_articulo;
insert into Autor_articulo(idAutor,idArticulo) values 
	(1,2),
	(2,2),
	(3,3),
	(4,4),
	(5,5);

delete from Articulo;
insert into Articulo(id,titulo,palabras_clave,fichero,fecha) values 
	(1, 'Inteligencia Artificial en la Educación', 'IA, educación, aprendizaje', 'ia_educacion.pdf', '2024-01-15'),
	(2, 'Desarrollo Web con React', 'React, desarrollo web, JavaScript', 'react_web.pdf', '2024-02-20'),
	(3, 'Análisis de Datos en Marketing', 'datos, marketing, análisis', 'marketing_datos.pdf', '2024-03-10'),
	(4, 'Big Data y su Impacto en la Economía', 'big data, economía, análisis', 'big_data_economia.pdf', '2024-04-05'),
	(5, 'Ciberseguridad en la Era Digital', 'ciberseguridad, privacidad, digital', 'ciberseguridad.pdf', '2024-05-12');
