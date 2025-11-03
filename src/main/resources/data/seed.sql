INSERT INTO products (id, name, description, price, quantity) VALUES
(1, 'Rice', 'High-quality long-grain rice', 1500.00, 100),
(2, 'Noodles', 'Instant noodles, quick and easy meal', 300.00, 200),
(3, 'Yams', 'Fresh yams, locally sourced', 800.00, 150),
(4, 'Goat', 'Live goat for sale', 25000.00, 50);

INSERT INTO orders (id, productId, quantity, customerName, orderDate) VALUES
(1, 1, 2, 'John Doe', '2023-10-01'),
(2, 2, 5, 'Jane Smith', '2023-10-02'),
(3, 3, 3, 'Alice Johnson', '2023-10-03'),
(4, 4, 1, 'Bob Brown', '2023-10-04');