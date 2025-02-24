--Datos para carga inicial de la base de datos

--Para giis.demo.tkrun:
insert into Autor(id,nombre,organizacion,grupo,revisor,coordinador) values 
	(1, 'Ana García', 'Universidad de Madrid', 'IA y ML', TRUE, FALSE),
	(2, 'Luis Fernández', 'TechCorp', 'Desarrollo Web', FALSE, FALSE),
	(3, 'María López', 'InnovateX', 'Ciencia de Datos', TRUE, FALSE),
	(4, 'Carlos Sánchez', 'Data Solutions', 'Big Data', FALSE, FALSE),
	(5, 'Laura Martín', 'StartUp Inc.', 'Seguridad Informática', TRUE, FALSE);

insert into Autor_articulo(idAutor,idArticulo,envia,revisa) values 
	(1,1,FALSE,FALSE),
	(2,2,FALSE,FALSE),
	(3,3,FALSE,FALSE),
	(4,4,FALSE,FALSE),
	(5,5,FALSE,FALSE);

insert into Articulo(id,titulo,palabras_clave,resumen,fichero,fecha) values 
	(1, 'Inteligencia Artificial en la Educación', 'IA, educación, aprendizaje','Este artículo explora el impacto de la inteligencia artificial en el aprendizaje y la educación moderna.', 'ia_educacion.pdf', '2024-01-15'),
	(2, 'Desarrollo Web con React', 'React, desarrollo web, JavaScript','Guía práctica para construir aplicaciones web dinámicas utilizando React y JavaScript.', 'react_web.pdf', '2024-02-20'),
	(3, 'Análisis de Datos en Marketing', 'datos, marketing, análisis','Estrategias de análisis de datos para optimizar campañas de marketing y mejorar la segmentación de audiencias.', 'marketing_datos.pdf', '2024-03-10'),
	(4, 'Big Data y su Impacto en la Economía', 'big data, economía, análisis','Estudio sobre cómo el Big Data está transformando la economía global y la toma de decisiones empresariales.', 'big_data_economia.pdf', '2024-04-05'),
	(5, 'Ciberseguridad en la Era Digital', 'ciberseguridad, privacidad, digital','Análisis de los desafíos de ciberseguridad en el mundo digital y estrategias para proteger la información.', 'ciberseguridad.pdf', '2024-05-12');
