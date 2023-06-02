-- DROP DATABASE loteria;
CREATE DATABASE loteria;
use loteria;
CREATE TABLE IF NOT EXISTS jugador (
    id serial,
    correo VARCHAR(100) NOT NULL,
    dni varchar(9) not null,
    contraseña VARCHAR(100) NOT NULL,
    telefono varchar(12),
    saldo decimal(10,2),
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS sorteo (
    id serial,
	fechaApertura date,
	fechaCierre date,
    fechaCelebracion dateTime,
    combinacionGanadora varchar(500),
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS apuesta(
    id int,
	precio decimal(10,2) not null,
    premio LONG,
	fecha date,
	apuestas varchar(500),
    jugador BIGINT UNSIGNED NOT NULL,
    sorteo  BIGINT UNSIGNED NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (jugador)
		REFERENCES jugador(id),
	FOREIGN KEY (sorteo)
		REFERENCES sorteo(id)
);

