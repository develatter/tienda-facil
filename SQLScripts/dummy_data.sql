-- Registros de prueba --
-- Clientes --
INSERT INTO `customers` (`first_name`, `last_name`, `address`, `mail`)
VALUES ('Juan', 'Perez', 'Calle 123', 'juan@mail.es');
INSERT INTO `customers` (`first_name`, `last_name`, `address`, `mail`)
VALUES ('Maria', 'Gomez', 'Calle 456', 'maria@mail.es');
INSERT INTO `customers` (`first_name`, `last_name`, `address`, `mail`)
VALUES ('Pedro', 'Lopez', 'Calle 789', 'pedro@mail.es');
INSERT INTO `customers` (`first_name`, `last_name`, `address`, `mail`)
VALUES ('Ana', 'Martinez', 'Calle 1011', 'ana@mail.es');
INSERT INTO `customers` (`first_name`, `last_name`, `address`, `mail`)
VALUES ('Luis', 'Sanchez', 'Calle 1213', 'luis@mail.es');
INSERT INTO `customers` (`first_name`, `last_name`, `address`, `mail`)
VALUES ('Carmen', 'Torres', 'Calle 1415', 'carmen@mail.es');
INSERT INTO `customers` (`first_name`, `last_name`, `address`, `mail`)
VALUES ('Jose', 'Ramirez', 'Calle 1617', 'jose@mail.es');
INSERT INTO `customers` (`first_name`, `last_name`, `address`, `mail`)
VALUES ('Laura', 'Fernandez', 'Calle 1819', 'laura@mail.es');
INSERT INTO `customers` (`first_name`, `last_name`, `address`, `mail`)
VALUES ('Carlos', 'Hernandez', 'Calle 2021', 'carlos@mail.es');
INSERT INTO `customers` (`first_name`, `last_name`, `address`, `mail`)
VALUES ('Elena', 'Diaz', 'Calle 2223', 'elena@mail.es');
INSERT INTO `customers` (`first_name`, `last_name`, `address`, `mail`)
VALUES ('Miguel', 'Garcia', 'Calle 2425', 'miguel@mail.es');
INSERT INTO `customers` (`first_name`, `last_name`, `address`, `mail`)
VALUES ('Sara', 'Martinez', 'Calle 2627', 'sara@mail.es');
INSERT INTO `customers` (`first_name`, `last_name`, `address`, `mail`)
VALUES ('David', 'Lopez', 'Calle 2829', 'david@mail.es');
INSERT INTO `customers` (`first_name`, `last_name`, `address`, `mail`)
VALUES ('Isabel', 'Gonzalez', 'Calle 3031', 'isabel@mail.es');
INSERT INTO `customers` (`first_name`, `last_name`, `address`, `mail`)
VALUES ('Javier', 'Rodriguez', 'Calle 3233', 'javier@mail.es');
INSERT INTO `customers` (`first_name`, `last_name`, `address`, `mail`)
VALUES ('Patricia', 'Santos', 'Calle 3435', 'patricia@mail.es');

-- Productos --
INSERT INTO `products` (`product_name`, `product_description`, `unit_price`, `current_stock`)
VALUES ('Manzana Golden', 'Lugar de origen: Granada', 1.6, 100);
INSERT INTO `products` (`product_name`, `product_description`, `unit_price`, `current_stock`)
VALUES ('Pera Himalaya', 'Lugar de origen: Córdoba', 1.2, 50);
INSERT INTO `products` (`product_name`, `product_description`, `unit_price`, `current_stock`)
VALUES ('Plátano Canario', 'Lugar de origen: La Palma', 1.8, 75);
INSERT INTO `products` (`product_name`, `product_description`, `unit_price`, `current_stock`)
VALUES( 'Chopped Pork', 'Pack de 200g. Bajo en sal', 2.5, 100);
INSERT INTO `products` (`product_name`, `product_description`, `unit_price`, `current_stock`)
VALUES ('Leche Entera', '1 litro. Marca X', 0.9, 200);
INSERT INTO `products` (`product_name`, `product_description`, `unit_price`, `current_stock`)
VALUES ('Pan Integral', '500g. Marca Y', 1.5, 150);
INSERT INTO `products` (`product_name`, `product_description`, `unit_price`, `current_stock`)
VALUES ('Huevos', 'Docena. Marca Z', 2.3, 100);
INSERT INTO `products` (`product_name`, `product_description`, `unit_price`, `current_stock`)
VALUES ('Queso Manchego', '250g. Marca A', 3.5, 80);
INSERT INTO `products` (`product_name`, `product_description`, `unit_price`, `current_stock`)
VALUES ('Yogur Natural', 'Pack de 4. Marca B', 1.2, 120);
INSERT INTO `products` (`product_name`, `product_description`, `unit_price`, `current_stock`)
VALUES ('Café Molido', '250g. Marca C', 4.0, 60);
INSERT INTO `products` (`product_name`, `product_description`, `unit_price`, `current_stock`)
VALUES ('Azúcar', '1kg. Marca D', 1.0, 200);
INSERT INTO `products` (`product_name`, `product_description`, `unit_price`, `current_stock`)
VALUES ('Aceite de Oliva', '1 litro. Marca E', 5.0, 50);
INSERT INTO `products` (`product_name`, `product_description`, `unit_price`, `current_stock`)
VALUES ('Arroz', '1kg. Marca F', 1.2, 180);
INSERT INTO `products` (`product_name`, `product_description`, `unit_price`, `current_stock`)
VALUES ('Pasta', '500g. Marca G', 0.8, 160);

-- Pedidos --
INSERT INTO `orders` (`customer_id`, `delivery_date`)
VALUES (1, '2024-11-07');
INSERT INTO `order_details` (`order_id`, `product_id`, `product_amount`)
VALUES (1, 1, 10);
INSERT INTO `order_details` (`order_id`, `product_id`, `product_amount`)
VALUES (1, 2, 5);
INSERT INTO `order_details` (`order_id`, `product_id`, `product_amount`)
VALUES (1, 3, 7);

INSERT INTO `orders` (`customer_id`, `delivery_date`)
VALUES (2, '2024-11-07');
INSERT INTO `order_details` (`order_id`, `product_id`, `product_amount`)
VALUES (2, 1, 5);
INSERT INTO `order_details` (`order_id`, `product_id`, `product_amount`)
VALUES (2, 2, 10);
INSERT INTO `order_details` (`order_id`, `product_id`, `product_amount`)
VALUES (2, 3, 3);

INSERT INTO `orders` (`customer_id`, `order_date`, `delivery_date`)
VALUES (3, '2023-11-07 00:00:00', '2024-11-07');
INSERT INTO `order_details` (`order_id`, `product_id`, `product_amount`)
VALUES (3, 1, 10);

INSERT INTO `orders` (`customer_id`, `delivery_date`)
VALUES (4, '2024-11-07');
INSERT INTO `order_details` (`order_id`, `product_id`, `product_amount`)
VALUES (4, 4, 2);
INSERT INTO `order_details` (`order_id`, `product_id`, `product_amount`)
VALUES (4, 5, 3);

INSERT INTO `orders` (`customer_id`, `order_date`, `delivery_date`)
VALUES (5, '2023-10-01 00:00:00', '2024-11-07');
INSERT INTO `order_details` (`order_id`, `product_id`, `product_amount`)
VALUES (5, 6, 1);
INSERT INTO `order_details` (`order_id`, `product_id`, `product_amount`)
VALUES (5, 7, 4);

INSERT INTO `orders` (`customer_id`, `delivery_date`)
VALUES (6, '2024-11-07');
INSERT INTO `order_details` (`order_id`, `product_id`, `product_amount`)
VALUES (6, 8, 5);
INSERT INTO `order_details` (`order_id`, `product_id`, `product_amount`)
VALUES (6, 9, 2);

INSERT INTO `orders` (`customer_id`, `order_date`, `delivery_date`)
VALUES (7, '2023-09-15 00:00:00', '2024-11-07');
INSERT INTO `order_details` (`order_id`, `product_id`, `product_amount`)
VALUES (7, 10, 3);
INSERT INTO `order_details` (`order_id`, `product_id`, `product_amount`)
VALUES (7, 11, 6);
INSERT INTO `order_details` (`order_id`, `product_id`, `product_amount`)
VALUES (7, 13, 8);

INSERT INTO `orders` (`customer_id`, `delivery_date`)
VALUES (8, '2024-11-07');
INSERT INTO `order_details` (`order_id`, `product_id`, `product_amount`)
VALUES (8, 12, 7);


