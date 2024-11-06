CREATE DATABASE IF NOT EXISTS tiendafacil;
USE tiendafacil;

CREATE TABLE IF NOT EXISTS `customers`
(
    `customer_id` INTEGER      NOT NULL AUTO_INCREMENT UNIQUE,
    `first_name`  VARCHAR(100) NOT NULL,
    `last_name`   VARCHAR(300) NOT NULL,
    `address`     VARCHAR(100) NOT NULL,
    `mail`        VARCHAR(100) NOT NULL,
    `reg_date`    DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `active`      BOOLEAN      NOT NULL,
    PRIMARY KEY (`customer_id`)
);

CREATE TABLE IF NOT EXISTS `order_status`
(
    `status_id` INTEGER     NOT NULL AUTO_INCREMENT UNIQUE,
    `status`    VARCHAR(10) NOT NULL UNIQUE,
    PRIMARY KEY (`status_id`)
);

INSERT INTO `order_status` (`status`)
VALUES ('Pendiente');
INSERT INTO `order_status` (`status`)
VALUES ('Enviado');
INSERT INTO `order_status` (`status`)
VALUES ('Entregado');


CREATE TABLE IF NOT EXISTS `products`
(
    `product_id`          INTEGER      NOT NULL AUTO_INCREMENT UNIQUE,
    `product_name`        VARCHAR(100) NOT NULL,
    `product_description` TEXT,
    `unit_price`          DECIMAL(10, 2)      NOT NULL,
    `current_stock`       INTEGER      NOT NULL DEFAULT 0,
    PRIMARY KEY (`product_id`)
);

CREATE TABLE IF NOT EXISTS `orders`
(
    `order_id`      INTEGER  NOT NULL AUTO_INCREMENT UNIQUE,
    `customer_id`   INTEGER  NOT NULL,
    `order_date`    DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `delivery_date` DATE NOT NULL,
    `status_id`     INTEGER  NOT NULL DEFAULT 1,
    PRIMARY KEY (`order_id`),
    FOREIGN KEY (`status_id`) REFERENCES `order_status` (`status_id`),
    FOREIGN KEY (`customer_id`) REFERENCES `customers` (`customer_id`)
);


CREATE TABLE IF NOT EXISTS `order_details`
(
    `details_id`     INTEGER NOT NULL AUTO_INCREMENT UNIQUE,
    `order_id`       INTEGER NOT NULL,
    `product_id`     INTEGER NOT NULL,
    `product_amount` INTEGER NOT NULL,
    PRIMARY KEY (`details_id`),
    FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`) ON DELETE CASCADE,
    FOREIGN KEY (`product_id`) REFERENCES `products` (`product_id`)
);


-- Registros de prueba --
-- Clientes --
INSERT INTO `customers` (`first_name`, `last_name`, `address`, `mail`, `active`)
VALUES ('Juan', 'Perez', 'Calle 123', 'juan@mail.es', TRUE);
INSERT INTO `customers` (`first_name`, `last_name`, `address`, `mail`, `active`)
VALUES ('Maria', 'Gomez', 'Calle 456', 'maria@mail.es', TRUE);
INSERT INTO `customers` (`first_name`, `last_name`, `address`, `mail`, `active`)
VALUES ('Pedro', 'Lopez', 'Calle 789', 'pedro@mail.es', TRUE);

-- Productos --
INSERT INTO `products` (`product_name`, `product_description`, `unit_price`, `current_stock`)
VALUES ('Manzanas', 'Manzana golden procedencia Marruecos', 1.6, 100);
INSERT INTO `products` (`product_name`, `product_description`, `unit_price`, `current_stock`)
VALUES ('Peras', 'Pera conferencia procedencia España', 1.2, 50);
INSERT INTO `products` (`product_name`, `product_description`, `unit_price`, `current_stock`)
VALUES ('Plátanos', 'Platano canario procedencia Canarias', 1.8, 75);

-- Pedidos --
INSERT INTO `orders` (`customer_id`, `delivery_date`) VALUES (1, '2024-11-07');
INSERT INTO `order_details` (`order_id`, `product_id`, `product_amount`) VALUES (1, 1, 10);
INSERT INTO `order_details` (`order_id`, `product_id`, `product_amount`) VALUES (1, 2, 5);
INSERT INTO `order_details` (`order_id`, `product_id`, `product_amount`) VALUES (1, 3, 7);

INSERT INTO `orders` (`customer_id`, `delivery_date`) VALUES (2, '2024-11-07');
INSERT INTO `order_details` (`order_id`, `product_id`, `product_amount`) VALUES (2, 1, 5);
INSERT INTO `order_details` (`order_id`, `product_id`, `product_amount`) VALUES (2, 2, 10);
INSERT INTO `order_details` (`order_id`, `product_id`, `product_amount`) VALUES (2, 3, 3);

