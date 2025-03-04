-- Insertar datos en la tabla Persona
INSERT INTO Persona (id, nombre, organizacion, grupo) VALUES
(1, 'Juan Perez', 'Universidad A', 'Grupo 1'),
(2, 'Maria Gomez', 'Universidad B', 'Grupo 2'),
(3, 'Carlos Lopez', 'Universidad A', 'Grupo 1'),
(4, 'Ana Torres', 'Universidad C', 'Grupo 3'),
(5, 'Luis Ramirez', 'Universidad B', 'Grupo 2'),
(6, 'Elena Martínez', 'Universidad D', 'Grupo 4'),
(7, 'Roberto Sanchez', 'Universidad A', 'Grupo 1'),
(8, 'Marta Fernández', 'Universidad E', 'Grupo 5'),
(9, 'Pablo Herrera', 'Universidad F', 'Grupo 6'),
(10, 'Laura Castro', 'Universidad G', 'Grupo 7');

-- Insertar datos en la tabla Autor
INSERT INTO Autor (idAutor, correo) VALUES
(1, 'juan.perez@email.com'),
(2, 'maria.gomez@email.com'),
(3, 'carlos.lopez@email.com'),
(6, 'elena.martinez@email.com'),
(8, 'marta.fernandez@email.com'),
(9, 'pablo.herrera@email.com');

-- Insertar datos en la tabla Revisor
INSERT INTO Revisor (idRevisor) VALUES
(4),
(5),
(7),
(10);

-- Insertar datos en la tabla Articulo
INSERT INTO Articulo (id, titulo, palabras_clave, resumen, fichero, fecha, decisionfinal, aceptado) VALUES
(1, 'Inteligencia Artificial', 'IA, Machine Learning', 'Resumen IA', 'ia.pdf', '2024-01-10', NULL, TRUE),
(2, 'Redes Neuronales', 'Deep Learning, Redes', 'Resumen redes', 'redes.pdf', '2024-02-15', NULL, NULL),
(3, 'Procesamiento de Lenguaje Natural', 'PLN, NLP', 'Resumen PLN', 'pln.pdf', '2024-03-20', NULL, NULL),
(4, 'Visión por Computador', 'Computer Vision, CV', 'Resumen CV', 'cv.pdf', '2024-04-25', NULL, FALSE),
(5, 'Algoritmos Genéticos', 'Optimización, Evolución', 'Resumen AG', 'ag.pdf', '2024-05-30', NULL, FALSE),
(6, 'Robótica Autónoma', 'Robots, IA', 'Resumen robótica', 'robotica.pdf', '2024-06-12', NULL, NULL);

-- Insertar datos en la tabla Autor_articulo
INSERT INTO Autor_articulo (idAutor, idArticulo, envia) VALUES
(1, 1, TRUE),
(2, 2, TRUE),
(3, 2, TRUE),
(3, 3, TRUE),
(6, 4, TRUE),
(8, 5, TRUE),
(9, 6, TRUE);

-- Insertar datos en la tabla Revision
INSERT INTO Revision (idRevision, idRevisor, idArticulo, experto, decision, coment_autor, coment_coor) VALUES
(1, 4, 1, "alto", 1, "2", "3"),
(2, 5, 2, "bajo", 2, NULL, NULL),
(3, 7, 3, "normal", 3, NULL, NULL),
(4, 4, 4, "medio", 3, NULL, NULL),
(5, 10, 5, "bajo", 1, NULL, NULL),
(6, 5, 6, "alto", -1, "Hola", "Adios"),
(7, 7, 6, "bajo", 2, "B", "C"),
(8, 10, 6, "medio", 1, "Autor", "Coord");