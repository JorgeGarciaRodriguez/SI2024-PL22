--Datos para carga inicial de la base de datos

--Para giis.demo.tkrun:
-- Insertar personas (Autores y Revisores)
insert into Persona(id, nombre, organizacion, grupo) values
    (1, 'Ana García', 'Universidad de Madrid', 'IA y ML'),
    (2, 'Luis Fernández', 'TechCorp', 'Desarrollo Web'),
    (3, 'María López', 'InnovateX', 'Ciencia de Datos'),
    (4, 'Carlos Sánchez', 'Data Solutions', 'Big Data'),
    (5, 'Laura Martín', 'StartUp Inc.', 'Seguridad Informática'),
    (6, 'Antonio Ruiz', 'Universidad de Barcelona', 'IA y ML'),
    (7, 'Sara Pérez', 'TechXperts', 'Ciberseguridad'),
    (8, 'David Hernández', 'Global Tech', 'Desarrollo Web'),
    (9, 'Elena Sánchez', 'Academia Científica', 'Ciencia de Datos'),
    (10, 'Jorge Martín', 'Innovative Solutions', 'Desarrollo Web'),
    (11, 'Ana Torres', 'Quantum Labs', 'Ciberseguridad'),
    (12, 'Pedro García', 'TechCorp', 'IA y ML');

-- Insertar autores (Los autores son personas con id en la tabla Persona)
insert into Autor(idAutor, correo) values
    (1, 'anag@gmail.com'),
    (2, 'luisf@techcorp.com'),
    (3, 'marialo@innovatex.com'),
    (4, 'carloss@datasolutions.com'),
    (5, 'lauram@startupinc.com'),
    (6, 'antonior@ub.es'),
    (7, 'sarap@techxperts.com'),
    (8, 'davidh@globaltech.com'),
    (9, 'elenas@academiacientifica.com'),
    (10, 'jorge.m@innovativesolutions.com');

-- Insertar revisores (He eliminado algunos revisores)
insert into Revisor(idRevisor) values
    (1),  -- Ana García es revisor
    (3),  -- María López es revisor
    (5),  -- Laura Martín es revisor
    (6),  -- Antonio Ruiz es revisor
    (7),  -- Sara Pérez es revisor
    (8);  -- David Hernández es revisor

-- Insertar artículos (Artículos que se pueden asignar a los revisores)
insert into Articulo(id, titulo, palabras_clave, resumen, fichero, fecha, decisionfinal) values
    (1, 'Machine Learning en la Sanidad', 'Machine Learning, sanidad, salud', 'Exploración del uso de machine learning para mejorar los diagnósticos médicos.', 'ml_sanidad.pdf', '2024-05-20', NULL),
    (2, 'Blockchain para la Seguridad Web', 'Blockchain, seguridad, web', 'Estudio sobre cómo blockchain puede mejorar la seguridad en aplicaciones web.', 'blockchain_seguridad.pdf', '2024-06-10', NULL),
    (3, 'Automatización en la Industria 4.0', 'automatización, Industria 4.0, producción', 'Análisis del impacto de la automatización en la producción industrial.', 'industria_4.0.pdf', '2024-07-25', NULL),
    (4, 'Redes Neuronales Convolucionales en Visión por Computadora', 'redes neuronales, visión por computadora', 'Estudio sobre el uso de redes neuronales convolucionales para mejorar la visión por computadora.', 'redes_neuronales.pdf', '2024-08-05', NULL);

-- Insertar relaciones entre autores y artículos
insert into Autor_articulo(idAutor, idArticulo, envia) values
    (1, 1, TRUE),  -- Ana García es autora del artículo 1
    (2, 2, TRUE),  -- Luis Fernández es autor del artículo 2
    (3, 3, TRUE),  -- María López es autora del artículo 3
    (4, 4, TRUE),  -- Carlos Sánchez es autor del artículo 4
    (5, 1, TRUE),  -- Laura Martín es autora del artículo 1
    (6, 2, TRUE),  -- Antonio Ruiz es autor del artículo 2
    (7, 3, TRUE),  -- Sara Pérez es autora del artículo 3
    (8, 4, TRUE);  -- David Hernández es autor del artículo 4

-- Insertar revisiones (Estos registros indican que los artículos ya han sido revisados o están disponibles para revisión)
insert into Revision(idRevision, idRevisor, idArticulo, experto, decision, coment_autor, coment_coor) values
    (1, 1, 1, NULL, NULL, NULL, NULL),  -- Ana García revisó el artículo 1
    (2, 2, 2, NULL, NULL, NULL, NULL),  -- Luis Fernández revisó el artículo 2
    (3, 3, 3, NULL, NULL, NULL, NULL),  -- María López revisó el artículo 3
    (4, 4, 4, NULL, NULL, NULL, NULL),  -- Carlos Sánchez revisó el artículo 4
    (5, 5, 1, NULL, NULL, NULL, NULL),  -- Laura Martín revisó el artículo 1
    (6, 6, 2, NULL, NULL, NULL, NULL),  -- Antonio Ruiz revisó el artículo 2
    (7, 7, 3, NULL, NULL, NULL, NULL),  -- Sara Pérez revisó el artículo 3
    (8, 8, 4, NULL, NULL, NULL, NULL);  -- David Hernández revisó el artículo 4
