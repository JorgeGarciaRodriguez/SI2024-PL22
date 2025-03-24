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
(10, 'Laura Castro', 'Universidad G', 'Grupo 7'),
(11, 'Sergio Mendoza', 'Universidad H', 'Grupo 8'),
(12, 'Isabel Rojas', 'Universidad I', 'Grupo 9');

-- Insertar datos en la tabla Autor
INSERT INTO Autor (idAutor, correo) VALUES
(1, 'juan.perez@email.com'),
(2, 'maria.gomez@email.com'),
(3, 'carlos.lopez@email.com'),
(6, 'elena.martinez@email.com'),
(8, 'marta.fernandez@email.com'),
(9, 'pablo.herrera@email.com'),
(11, 'sergio.mendoza@email.com'),
(12, 'isabel.rojas@email.com');

-- Insertar datos en la tabla Revisor
INSERT INTO Revisor (idRevisor) VALUES
(4),
(5),
(7),
(10),
(11),
(12);

-- Insertar datos en la tabla Articulo
INSERT INTO Articulo (id, titulo, palabras_clave, resumen, fichero, fecha, aceptado) VALUES
(1, 'Inteligencia Artificial', 'IA, Machine Learning', 'Resumen IA', 'ia.pdf', '2024-01-10', 2),
(2, 'Redes Neuronales', 'Deep Learning, Redes', 'Resumen redes', 'redes.pdf', '2024-02-15', 0),
(3, 'Procesamiento de Lenguaje Natural', 'PLN, NLP', 'Resumen PLN', 'pln.pdf', '2024-03-20', NULL),
(4, 'Visión por Computador', 'Computer Vision, CV', 'Resumen CV', 'cv.pdf', '2024-04-25', 2),
(5, 'Algoritmos Genéticos', 'Optimización, Evolución', 'Resumen AG', 'ag.pdf', '2024-05-30', NULL),
(6, 'Robótica Autónoma', 'Robots, IA', 'Resumen robótica', 'robotica.pdf', '2024-06-12', NULL),
(7, 'Ciberseguridad en Redes', 'Seguridad, Criptografía', 'Resumen ciberseguridad', 'ciberseguridad.pdf', '2024-07-18', NULL),
(8, 'Blockchain y Finanzas', 'Blockchain, Finanzas', 'Resumen blockchain', 'blockchain.pdf', '2024-08-22', NULL),
(9, 'Computación Cuántica', 'Quantum Computing, Algoritmos Cuánticos', 'Resumen computación cuántica', 'cuantica.pdf', '2024-09-15', NULL),
(10, 'Redes 5G y su impacto', '5G, Telecomunicaciones', 'Resumen redes 5G', '5g.pdf', '2024-10-10', 0);

-- Insertar datos en la tabla Autor_articulo
INSERT INTO Autor_articulo (idAutor, idArticulo, envia) VALUES
(1, 1, TRUE),
(2, 2, TRUE),
(3, 2, TRUE),
(3, 3, TRUE),
(6, 4, TRUE),
(8, 5, TRUE),
(9, 6, TRUE),
(11, 7, TRUE),
(12, 8, TRUE),
(1, 9, TRUE),
(2, 10, TRUE);

-- Insertar datos en la tabla Track
INSERT INTO Track (id, nombre, deadline) VALUES
(1, 'Inteligencia Artificial', '2024-12-31'),
(2, 'Machine Learning', '2024-11-30'),
(3, 'Procesamiento de Lenguaje Natural', '2024-10-31'),
(4, 'Visión por Computador', '2024-09-30'),
(5, 'Robótica', '2024-08-31'),
(6, 'Ciberseguridad', '2024-07-31'),
(7, 'Blockchain', '2024-06-30');

-- Insertar datos en la tabla PalabraClaveTrack
INSERT INTO PalabraClaveTrack (id, idTrack, palabra_clave) VALUES
(1, 1, 'Inteligencia Artificial'),
(2, 1, 'IA'),
(3, 2, 'Machine Learning'),
(4, 2, 'ML'),
(5, 3, 'Procesamiento de Lenguaje Natural'),
(6, 3, 'PLN'),
(7, 4, 'Visión por Computador'),
(8, 4, 'Computer Vision'),
(9, 5, 'Robótica'),
(10, 5, 'Robots'),
(11, 6, 'Ciberseguridad'),
(12, 6, 'Seguridad'),
(13, 7, 'Blockchain'),
(14, 7, 'Finanzas');

-- Insertar datos en la tabla ArticuloTrack
INSERT INTO ArticuloTrack (idArticulo, idTrack, palabras_clave_seleccionadas) VALUES
(1, 1, 'IA, Machine Learning'),
(4, 1, 'Computer Vision, CV'),
(9, 1, 'Quantum Computing, Algoritmos Cuánticos');

-- Insertar datos en la tabla RevisorTrack
INSERT INTO RevisorTrack (idRevisor, idTrack) VALUES
(4, 1),
(5, 2),
(7, 3),
(10, 4),
(11, 5),
(12, 6);

-- Insertar datos en la tabla Revision
INSERT INTO Revision (idRevision, idRevisor, idArticulo, experto, decision, coment_autor, coment_coor) VALUES
(1, 4, 1, 'Alto', 2, 'El artículo es excelente y bien fundamentado.', 'Recomiendo su publicación inmediata.'),
(2, 5, 2, 'Medio', -1, 'El trabajo tiene potencial, pero necesita mejoras.', 'Faltan referencias clave para su aceptación.'),
(3, 7, 3, 'Normal', -2, 'El artículo es bueno, pero hay algunos errores menores.', 'Sugiero realizar pequeñas correcciones antes de aceptar.'),
(4, 4, 4, 'Bajo', -2, 'El trabajo carece de originalidad y estructura.', 'Recomiendo rechazar en su estado actual.'),
(5, 10, 5, 'Alto', 2, 'Gran contribución al área, excelente calidad técnica.', 'Aprobado sin modificaciones.'),
(6, 5, 6, 'Medio', -1, 'Faltan detalles técnicos en algunos apartados.', 'Se recomienda una revisión en profundidad.'),
(7, 7, 6, 'Normal', 1, 'Buena investigación, aunque necesita más pruebas.', 'Se aceptará tras ajustes menores.'),
(8, 10, 6, 'Bajo', 1, 'No cumple con los estándares de calidad esperados.', 'Debe ser reformulado antes de reconsiderar.'),
(9, 11, 7, 'Alto', 2, 'Artículo innovador y bien estructurado.', 'Publicable en su versión actual.'),
(10, 12, 8, 'Normal', 1, 'Interesante, pero algunos puntos no están bien explicados.', 'Se recomienda una revisión menor antes de aceptar.');