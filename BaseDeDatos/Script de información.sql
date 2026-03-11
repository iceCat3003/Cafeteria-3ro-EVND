-- Script de información para la base de datos  
-- =========================
-- ROLES
-- =========================
INSERT INTO Roles (nombreRol) VALUES
('Administrador'),
('Almacenista'),
('Encargado de Compras'),
('Encargado de Producción');

-- =========================
-- USUARIOS
-- =========================
INSERT INTO Usuarios (nombre1, nombre2, apellido1, apellido2, telefono, usuario, contrasenia, estadoUsuario, nivelAcceso, idRol, salario)
VALUES
('Juan', 'Carlos', 'Pérez', 'López', '3312345678', 'juan_admin', '1234seguro', 'ACTIVO', 'ALTO', 1, 25000.00), -- Administrador
('María', NULL, 'García', 'Hernández', '3323456789', 'maria_alm', 'claveSegura', 'ACTIVO', 'MEDIO', 2, 15000.00), -- Almacenista
('Luis', 'Alberto', 'Ramírez', NULL, '3334567890', 'luis_compras', 'passFuerte', 'ACTIVO', 'MEDIO', 3, 18000.00), -- Encargado de Compras
('Ana', 'Sofía', 'Martínez', 'Torres', '3345678901', 'ana_prod', 'contraseña123', 'ACTIVO', 'BAJO', 4, 16000.00); -- Encargado de Producción

-- =========================
-- MATERIAS PRIMAS
-- =========================
INSERT INTO MateriasPrimas (nombre, cantidad, costo, descripcion)
VALUES
('Algodón', 500, 50.00, 'Algodón de alta calidad para telas'),
('Poliéster', 300, 30.00, 'Fibra sintética resistente'),
('Lana', 200, 80.00, 'Lana natural para prendas de invierno'),
('Seda', 100, 150.00, 'Seda importada para textiles finos');

-- =========================
-- PRODUCTOS
-- =========================
INSERT INTO Productos (nombre, cantidad, precio, descripcion)
VALUES
('Camisa Algodón', 100, 250.00, 'Camisa hecha de algodón suave'),
('Pantalón Poliéster', 80, 300.00, 'Pantalón resistente de poliéster'),
('Suéter de Lana', 50, 500.00, 'Suéter cálido de lana'),
('Blusa de Seda', 30, 800.00, 'Blusa elegante de seda');

-- =========================
-- VENTAS
-- =========================
INSERT INTO Ventas (total, estado, idUsuario)
VALUES
(750.00, 'COMPLETADO', 2), -- Venta hecha por Almacenista
(1600.00, 'ESPERA', 1),    -- Venta hecha por Administrador
(500.00, 'CANCELADO', 2);  -- Venta hecha por Almacenista

-- =========================
-- COMPRAS
-- =========================
INSERT INTO Compras (total, estado, idUsuario)
VALUES
(2500.00, 'COMPLETADO', 3), -- Compra hecha por Encargado de Compras
(1200.00, 'ESPERA', 4),     -- Compra hecha por Encargado de Producción
(800.00, 'COMPLETADO', 1);  -- Compra hecha por Administrador

-- =========================
-- DETALLE VENTA
-- =========================
INSERT INTO Venta_Producto (idVenta, idProducto, cantidad, precioUnitario)
VALUES
(1, 1, 2, 250.00),
(1, 2, 1, 300.00),
(2, 3, 2, 500.00),
(3, 4, 1, 800.00);

-- =========================
-- DETALLE COMPRA
-- =========================
INSERT INTO Compra_Materia (idCompra, idMateria, cantidad, costoUnitario)
VALUES
(1, 1, 50, 50.00),
(1, 2, 30, 30.00),
(2, 3, 20, 80.00),
(3, 4, 10, 150.00);
