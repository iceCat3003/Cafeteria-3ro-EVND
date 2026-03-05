CREATE DATABASE IF NOT EXISTS Cafeteria;

USE Cafeteria;

CREATE TABLE IF NOT EXISTS Empleados (
    idEmpleado INT PRIMARY KEY AUTO_INCREMENT,
    usuario VARCHAR(50) NOT NULL UNIQUE,
    contrasenia VARCHAR(255) NOT NULL,  -- Aumentado para hash
    telefono VARCHAR(10) NOT NULL UNIQUE,
    nombre1 VARCHAR(255) NOT NULL,
    nombre2 VARCHAR(255),
    apellido1 VARCHAR(255) NOT NULL,
    apellido2 VARCHAR(255),
    puesto ENUM ('GERENTE','CAJERO','BARISTA'),
    estatus ENUM ('ACTIVO','INACTIVO','BAJA_TEMPORAL') DEFAULT 'ACTIVO',
    fechaIngreso DATE DEFAULT (CURRENT_DATE),
    fechaBaja DATE,
    foto MEDIUMBLOB,
    CONSTRAINT check_fechas CHECK (fechaBaja IS NULL OR fechaBaja >= fechaIngreso)
);

CREATE TABLE IF NOT EXISTS ProductosCategorias(
    idCategoria INT PRIMARY KEY AUTO_INCREMENT,
    categoria VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS Productos (
    idProducto INT PRIMARY KEY AUTO_INCREMENT,
    idCategoria INT,
    nombre VARCHAR(255) NOT NULL,
    descripcion VARCHAR(255) NOT NULL,
    tamPorcion VARCHAR(50) NOT NULL,
    precio DECIMAL(10,2) NOT NULL CHECK (precio > 0),
    esDisponible ENUM ('SI','NO') DEFAULT 'SI',
    temperatura ENUM ('FRIO','CALIENTE','NO APLICA') DEFAULT 'NO APLICA',
    foto MEDIUMBLOB,
    CONSTRAINT fk_ProductosCategorias FOREIGN KEY (idCategoria)
    	REFERENCES ProductosCategorias(idCategoria)
    	ON DELETE SET NULL
);

CREATE TABLE IF NOT EXISTS Pedidos (
    idPedido INT PRIMARY KEY AUTO_INCREMENT,
    idEmpleado INT,
    estatusPedido ENUM ('PEDIDO RECIBIDO','EN PREPARACION','ESPERANDO RECOGIDA','PEDIDO ENTREGADO') DEFAULT 'PEDIDO RECIBIDO',
    horaRealizada DATETIME DEFAULT CURRENT_TIMESTAMP,
    horaEntregada DATETIME,
    CONSTRAINT fk_Empleados FOREIGN KEY (idEmpleado)
    	REFERENCES Empleados(idEmpleado)
    	ON DELETE SET NULL,
    CONSTRAINT check_horas CHECK (horaEntregada IS NULL OR horaEntregada >= horaRealizada)
);

CREATE TABLE IF NOT EXISTS PedidosDetalle (
    idDetallePedido INT PRIMARY KEY AUTO_INCREMENT,
    idPedido INT,
    idProducto INT,
    cantidad INT CHECK (cantidad > 0),
    precioUnitario DECIMAL(10,2) NOT NULL CHECK (precioUnitario > 0),
    CONSTRAINT fk_Pedidos FOREIGN KEY (idPedido) 
        REFERENCES Pedidos(idPedido) ON DELETE CASCADE,
    CONSTRAINT fk_Producto FOREIGN KEY (idProducto) 
        REFERENCES Productos(idProducto) ON DELETE CASCADE
);

-- Índices recomendados
CREATE INDEX idx_productos_categoria ON Productos(idCategoria);
CREATE INDEX idx_pedidos_empleado ON Pedidos(idEmpleado);
CREATE INDEX idx_pedidos_estatus ON Pedidos(estatusPedido);
CREATE INDEX idx_pedidosdetalle_pedido ON PedidosDetalle(idPedido);
CREATE INDEX idx_pedidosdetalle_producto ON PedidosDetalle(idProducto);
