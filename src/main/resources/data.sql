--Datos para carga inicial de la base de datos

--Para giis.demo.tkrun:
insert into Autor(id,nombre,organizacion,grupo,revisor) values 
	(1, 'Ana García', 'Universidad de Madrid', 'IA y ML', TRUE),
	(2, 'Luis Fernández', 'TechCorp', 'Desarrollo Web', FALSE),
	(3, 'María López', 'InnovateX', 'Ciencia de Datos', TRUE),
	(4, 'Carlos Sánchez', 'Data Solutions', 'Big Data', FALSE),
	(5, 'Laura Martín', 'StartUp Inc.', 'Seguridad Informática', TRUE);

insert into Autor_articulo(idAutor,idArticulo) values 
	(1,2),
	(2,2),
	(3,3),
	(4,4),
	(5,5);

insert into Articulo(id,titulo,palabras_clave,resumen,fichero,fecha,revisado_por) values 
	(1, 'Inteligencia Artificial en la Educación', 'IA, educación, aprendizaje','Este artículo explora el impacto de la inteligencia artificial en el aprendizaje y la educación moderna.', 'ia_educacion.pdf', '2024-01-15',NULL),
	(2, 'Desarrollo Web con React', 'React, desarrollo web, JavaScript','Guía práctica para construir aplicaciones web dinámicas utilizando React y JavaScript.', 'react_web.pdf', '2024-02-20',NULL),
	(3, 'Análisis de Datos en Marketing', 'datos, marketing, análisis','Estrategias de análisis de datos para optimizar campañas de marketing y mejorar la segmentación de audiencias.', 'marketing_datos.pdf', '2024-03-10',NULL),
	(4, 'Big Data y su Impacto en la Economía', 'big data, economía, análisis','Estudio sobre cómo el Big Data está transformando la economía global y la toma de decisiones empresariales.', 'big_data_economia.pdf', '2024-04-05',NULL),
	(5, 'Ciberseguridad en la Era Digital', 'ciberseguridad, privacidad, digital','Análisis de los desafíos de ciberseguridad en el mundo digital y estrategias para proteger la información.', 'ciberseguridad.pdf', '2024-05-12',NULL);
