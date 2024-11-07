CREATE DATABASE IF NOT EXISTS tiendafacil;
USE tiendafacil;
SET GLOBAL event_scheduler = "ON";
CREATE TABLE IF NOT EXISTS `customers`
(
    `customer_id` INTEGER      NOT NULL AUTO_INCREMENT UNIQUE,
    `first_name`  VARCHAR(100) NOT NULL,
    `last_name`   VARCHAR(300) NOT NULL,
    `address`     VARCHAR(100) NOT NULL,
    `mail`        VARCHAR(100) NOT NULL,
    `reg_date`    DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `active`      BOOLEAN      NOT NULL DEFAULT FALSE,
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
    `product_id`          INTEGER        NOT NULL AUTO_INCREMENT UNIQUE,
    `product_name`        VARCHAR(100)   NOT NULL,
    `product_description` TEXT,
    `unit_price`          DECIMAL(10, 2) NOT NULL,
    `current_stock`       INTEGER        NOT NULL DEFAULT 0,
    PRIMARY KEY (`product_id`)
);

CREATE TABLE IF NOT EXISTS `orders`
(
    `order_id`      INTEGER  NOT NULL AUTO_INCREMENT UNIQUE,
    `customer_id`   INTEGER  NOT NULL,
    `order_date`    DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `delivery_date` DATE     NOT NULL,
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


-- Funciones --
-- Compureba si la fecha de un pedido es reciente --
DELIMITER $$
CREATE FUNCTION `is_recent_order`(order_date DATETIME)
    RETURNS BOOLEAN
BEGIN
    RETURN order_date >= DATE_SUB(CURRENT_DATE, INTERVAL 14 DAY);
END $$
DELIMITER ;

-- Triggers --
-- Actualiza el estado del cliente a activo cuando se realiza un pedido --
DELIMITER $$
CREATE TRIGGER `activate_customer_on_order`
    AFTER INSERT
    ON `orders`
    FOR EACH ROW
BEGIN
    UPDATE `customers`
    SET `active` = TRUE
    WHERE `customer_id` = NEW.`customer_id`
    AND is_recent_order(NEW.`order_date`);
END $$
DELIMITER ;

-- Desactiva el cliente cuando se elimina un pedido si no tiene más pedidos recientes --
DELIMITER $$
CREATE TRIGGER `deactivate_customer_on_order_delete`
    AFTER DELETE
    ON `orders`
    FOR EACH ROW
BEGIN
    DECLARE recent_customer_orders INTEGER;
    SELECT COUNT(`order_id`)
    INTO recent_customer_orders
    FROM `orders`
    WHERE `customer_id` = OLD.`customer_id`
      AND is_recent_order(`order_date`);

    IF recent_customer_orders = 0 THEN
        UPDATE `customers`
        SET `active` = FALSE
        WHERE `customer_id` = OLD.`customer_id`;
    END IF;
END $$
DELIMITER ;

-- Procedimientos almacenados --
-- Desactiva los clientes que no han realizado pedidos en los últimos 14 días --
DELIMITER $$
CREATE PROCEDURE `set_customers_inactive`()
BEGIN
    UPDATE `customers`
    SET `active` = FALSE
    WHERE `active` = TRUE
      AND `customer_id` NOT IN (SELECT DISTINCT `customer_id`
                                FROM `orders`
                                WHERE is_recent_order(`order_date`));
END $$
DELIMITER ;

-- Eventos --
-- Llama al procedimiento almacenado para desactivar clientes inactivos cada día --
DELIMITER $$
CREATE EVENT `deactivate_inactive_customers`
    ON SCHEDULE EVERY 1 DAY
        STARTS CURRENT_TIMESTAMP
    DO
    BEGIN
        CALL `set_customers_inactive`();
    END $$
DELIMITER ;