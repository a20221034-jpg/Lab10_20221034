-- ============================================================================
--  Laboratorio 10: DTO + Nube
-- ============================================================================

CREATE DATABASE IF NOT EXISTS lab10_telecom CHARACTER SET utf8mb4;
USE lab10_telecom;

DROP TABLE IF EXISTS equipos_red;
DROP TABLE IF EXISTS sedes;

-- Catálogo de sedes del operador
CREATE TABLE sedes (
    id_sede  INT PRIMARY KEY AUTO_INCREMENT,
    nombre   VARCHAR(80) NOT NULL,
    ciudad   VARCHAR(60) NOT NULL
);

-- Equipos de red registrados en el inventario
CREATE TABLE equipos_red (
    id_equipo  INT PRIMARY KEY AUTO_INCREMENT,
    hostname   VARCHAR(60) NOT NULL UNIQUE,
    tipo       ENUM('router','switch','firewall','access point') NOT NULL,
    ip_gestion VARCHAR(15) NOT NULL,
    estado     ENUM('operativo','en mantenimiento','fuera de servicio') NOT NULL DEFAULT 'operativo',
    fecha_alta DATE NOT NULL,
    id_sede    INT NOT NULL,
    FOREIGN KEY (id_sede) REFERENCES sedes(id_sede)
);

INSERT INTO sedes (nombre, ciudad) VALUES
    ('Sede Central San Isidro', 'Lima'),
    ('Nodo Callao',             'Lima'),
    ('POP Arequipa',            'Arequipa'),
    ('POP Cusco',               'Cusco'),
    ('POP Tarapoto',            'San Martín');   -- sede SIN equipos (para probar el LEFT JOIN)

INSERT INTO equipos_red (hostname, tipo, ip_gestion, estado, fecha_alta, id_sede) VALUES
    ('rtr-lim-01', 'router',       '10.10.1.1',  'operativo',         '2024-03-15', 1),
    ('sw-lim-01',  'switch',       '10.10.1.2',  'operativo',         '2024-03-15', 1),
    ('fw-lim-01',  'firewall',     '10.10.1.3',  'en mantenimiento',  '2024-04-02', 1),
    ('ap-lim-01',  'access point', '10.10.1.4',  'operativo',         '2024-05-20', 1),
    ('rtr-cal-01', 'router',       '10.20.1.1',  'operativo',         '2024-06-10', 2),
    ('sw-cal-02',  'switch',       '10.20.1.2',  'fuera de servicio', '2024-06-12', 2),
    ('rtr-aqp-01', 'router',       '10.30.1.1',  'operativo',         '2025-01-08', 3),
    ('sw-aqp-01',  'switch',       '10.30.1.2',  'en mantenimiento',  '2025-01-09', 3),
    ('rtr-cus-01', 'router',       '10.40.1.2',  'operativo',         '2025-02-14', 4),
    ('ap-cus-01',  'access point', '10.40.1.1',  'fuera de servicio', '2025-02-14', 4);

-- ---------------------------------------------------------------------------
-- Usuario de la aplicación (local y nube). Si ya existiera y cambiaste la
-- contraseña, ejecuta:  ALTER USER 'iweb'@'localhost' IDENTIFIED BY 'iweb2026';
-- ---------------------------------------------------------------------------
CREATE USER IF NOT EXISTS 'iweb'@'localhost' IDENTIFIED BY 'iweb2026';
CREATE USER IF NOT EXISTS 'iweb'@'127.0.0.1' IDENTIFIED BY 'iweb2026';
GRANT SELECT, INSERT, UPDATE, DELETE ON lab10_telecom.* TO 'iweb'@'localhost';
GRANT SELECT, INSERT, UPDATE, DELETE ON lab10_telecom.* TO 'iweb'@'127.0.0.1';
FLUSH PRIVILEGES;
