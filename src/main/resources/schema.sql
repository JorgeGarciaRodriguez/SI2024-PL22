-- Eliminar tablas en orden inverso a su creación (de detalle a maestro)
DROP TABLE IF EXISTS Decision;
DROP TABLE IF EXISTS RevisorTrack;
DROP TABLE IF EXISTS ArticuloTrack;
DROP TABLE IF EXISTS PalabraClaveTrack;
DROP TABLE IF EXISTS Track;
DROP TABLE IF EXISTS Notificacion;
DROP TABLE IF EXISTS Discusion;
DROP TABLE IF EXISTS Revision;
DROP TABLE IF EXISTS Autor_articulo;
DROP TABLE IF EXISTS Articulo;
DROP TABLE IF EXISTS Autor;
DROP TABLE IF EXISTS Revisor;
DROP TABLE IF EXISTS Persona;

-- Crear tablas en orden correcto (de maestro a detalle)

-- Tabla Persona (maestra)
CREATE TABLE Persona (
    id INT PRIMARY KEY NOT NULL,
    nombre VARCHAR NOT NULL,
    organizacion VARCHAR NOT NULL,
    grupo VARCHAR NOT NULL
);

-- Tabla Revisor (depende de Persona)
CREATE TABLE Revisor (
    idRevisor INT PRIMARY KEY NOT NULL,
    FOREIGN KEY (idRevisor) REFERENCES Persona(id)
);

-- Tabla Autor (depende de Persona)
CREATE TABLE Autor (
    idAutor INT PRIMARY KEY NOT NULL,
    correo VARCHAR UNIQUE NOT NULL,
    FOREIGN KEY (idAutor) REFERENCES Persona(id)
);

-- Tabla Articulo (independiente)
CREATE TABLE Articulo (
    id INT PRIMARY KEY NOT NULL,
    titulo VARCHAR NOT NULL,
    palabras_clave VARCHAR NOT NULL,
    resumen VARCHAR NOT NULL,
    fichero VARCHAR NOT NULL,
    fecha DATE NOT NULL,
    aceptado INT
);

-- Tabla Autor_articulo (depende de Autor y Articulo)
CREATE TABLE Autor_articulo (
    idAutor INT NOT NULL,
    idArticulo INT NOT NULL,
    envia BOOLEAN NOT NULL,
    PRIMARY KEY (idAutor, idArticulo),
    FOREIGN KEY (idAutor) REFERENCES Autor(id),
    FOREIGN KEY (idArticulo) REFERENCES Articulo(id)
);

-- Tabla Revision (depende de Revisor y Articulo)
CREATE TABLE Revision (
    idRevision INT NOT NULL,
    idRevisor INT NOT NULL,
    idArticulo INT NOT NULL,
    experto VARCHAR,
    decision INT,
    coment_autor VARCHAR,
    coment_coor VARCHAR,
    PRIMARY KEY (idRevisor, idArticulo)
);

-- Tabla Discusion (depende de Articulo)
CREATE TABLE Discusion (
    id_discusion INTEGER PRIMARY KEY AUTOINCREMENT,
    id_articulo INT,
    estado VARCHAR(20) CHECK (estado IN ('abierta', 'cerrada')),
    FOREIGN KEY (id_articulo) REFERENCES Articulo(id) ON DELETE CASCADE,
    CONSTRAINT unique_discusion UNIQUE(id_articulo)
);

-- Tabla Notificacion (depende de Revisor y Discusion)
CREATE TABLE Notificacion (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    id_revisor INT,
    id_discusion INT,
    estado VARCHAR(10) DEFAULT 'pendiente' CHECK (estado IN ('pendiente', 'enviado')),
    FOREIGN KEY (id_revisor) REFERENCES Revisor(id),
    FOREIGN KEY (id_discusion) REFERENCES Discusion(id)
);

-- Tabla Track (independiente)
CREATE TABLE Track (
    id INT PRIMARY KEY NOT NULL,
    nombre VARCHAR NOT NULL,
    deadline DATE NOT NULL
);

-- Tabla PalabraClaveTrack (depende de Track)
CREATE TABLE PalabraClaveTrack (
    id INT PRIMARY KEY NOT NULL,
    idTrack INT NOT NULL,
    palabra_clave VARCHAR NOT NULL,
    FOREIGN KEY (idTrack) REFERENCES Track(id)
);

-- Tabla ArticuloTrack (depende de Articulo y Track)
CREATE TABLE ArticuloTrack (
    idArticulo INT NOT NULL,
    idTrack INT NOT NULL,
    palabras_clave_seleccionadas VARCHAR NOT NULL,
    PRIMARY KEY (idArticulo, idTrack),
    FOREIGN KEY (idArticulo) REFERENCES Articulo(id),
    FOREIGN KEY (idTrack) REFERENCES Track(id)
);

-- Tabla RevisorTrack (depende de Revisor y Track)
CREATE TABLE RevisorTrack (
    idRevisor INT,
    idTrack INT,
    PRIMARY KEY (idRevisor, idTrack),
    FOREIGN KEY (idRevisor) REFERENCES Revisor(idRevisor),
    FOREIGN KEY (idTrack) REFERENCES Track(id)
);