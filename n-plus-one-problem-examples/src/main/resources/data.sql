-- Вставка клиентов
INSERT INTO clients (id, name, email) VALUES (1, 'Иван Иванов', 'ivan@example.com');
INSERT INTO clients (id, name, email) VALUES (2, 'Петр Петров', 'petr@example.com');
INSERT INTO clients (id, name, email) VALUES (3, 'Мария Сидорова', 'maria@example.com');
INSERT INTO clients (id, name, email) VALUES (4, 'Анна Смирнова', 'anna@example.com');
INSERT INTO clients (id, name, email) VALUES (5, 'Дмитрий Козлов', 'dmitry@example.com');

-- Заказы для клиента 1 (Иван Иванов) - 3 заказа
INSERT INTO orders (id, product_name, amount, order_date, client_id) 
VALUES (1, 'Ноутбук Dell', 75000.00, '2024-01-15 10:30:00', 1);

INSERT INTO orders (id, product_name, amount, order_date, client_id) 
VALUES (2, 'Мышь Logitech', 1500.00, '2024-01-20 14:20:00', 1);

INSERT INTO orders (id, product_name, amount, order_date, client_id) 
VALUES (3, 'Клавиатура Механическая', 8500.00, '2024-02-01 09:15:00', 1);

-- Заказы для клиента 2 (Петр Петров) - 1 заказ
INSERT INTO orders (id, product_name, amount, order_date, client_id) 
VALUES (4, 'Монитор Samsung 27"', 25000.00, '2024-01-18 16:45:00', 2);

-- Заказы для клиента 3 (Мария Сидорова) - 5 заказов
INSERT INTO orders (id, product_name, amount, order_date, client_id) 
VALUES (5, 'iPhone 15 Pro', 120000.00, '2024-01-10 11:00:00', 3);

INSERT INTO orders (id, product_name, amount, order_date, client_id) 
VALUES (6, 'AirPods Pro', 25000.00, '2024-01-10 11:05:00', 3);

INSERT INTO orders (id, product_name, amount, order_date, client_id) 
VALUES (7, 'Чехол для iPhone', 2000.00, '2024-01-12 13:30:00', 3);

INSERT INTO orders (id, product_name, amount, order_date, client_id) 
VALUES (8, 'Apple Watch Series 9', 45000.00, '2024-01-25 15:20:00', 3);

INSERT INTO orders (id, product_name, amount, order_date, client_id) 
VALUES (9, 'MacBook Air M2', 135000.00, '2024-02-05 10:00:00', 3);

-- Заказы для клиента 4 (Анна Смирнова) - 0 заказов
-- (у этого клиента нет заказов - для тестирования граничного случая)

-- Заказы для клиента 5 (Дмитрий Козлов) - 2 заказа
INSERT INTO orders (id, product_name, amount, order_date, client_id) 
VALUES (10, 'PlayStation 5', 55000.00, '2024-01-22 12:00:00', 5);

INSERT INTO orders (id, product_name, amount, order_date, client_id) 
VALUES (11, 'Игра Spider-Man 2', 5500.00, '2024-01-22 12:05:00', 5);
