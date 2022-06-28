--
--
--  Insert queries
--
--
-- insert new employee
INSERT INTO Employee (
        id_employee,
        empl_surname,
        empl_name,
        password,
        role,
        empl_patronymic,
        salary,
        date_of_birth,
        date_of_start,
        phone_number,
        city,
        street,
        zip_code
    )
VALUES (
        'doe-cash',
        'Doe',
        'John',
        '$2a$10$ccTgX4CI.Bx4y9xbQUzSb.WPL2s7TwtZZ3WSW7MrBPFdQyxwwJ7ZS',
        'CASHIER' 'Jr.',
        '1000',
        '2000-12-08',
        '2022-12-12',
        12345,
        'New-York',
        'New-York street',
        '1221-4324'
    );
--insert new client card 
INSERT INTO Customer_Card (
        card_number,
        cust_surname,
        cust_name,
        cust_patronymic,
        phone_number,
        city,
        street,
        zip_code,
        percent
    )
VALUES (
        '1234567890123',
        'Іванов',
        'Іван',
        NULL,
        '+380961112233',
        'Київ',
        'Хрещатик',
        '00001',
        2.5
    );
-- Insert new category
INSERT INTO Category (category_number, category_name)
VALUES (NULL, 'Овочі');
-- Insert new product
INSERT INTO Product (
        id_product,
        category_number,
        product_name,
        characteristics
    )
VALUES (
        NULL,
        1,
        'Огірок звичайний',
        'Огірок звичайний зелений'
    );
-- Insert new promotional product in store
INSERT INTO Store_Product (
        UPC,
        UPC_prom,
        id_product,
        selling_price,
        products_number,
        promotional_product
    )
VALUES ('404457230720', NULL, 1, 5.25, 100, true);
-- Insert new product in store
INSERT INTO Store_Product (
        UPC,
        UPC_prom,
        id_product,
        selling_price,
        products_number,
        promotional_product
    )
VALUES (
        '404457230721',
        '404457230720',
        1,
        10.5,
        100,
        false
    );
--
--
--  Update queries
--
--
-- Update employee information
UPDATE Employee
SET empl_surname = '',
    empl_name = '',
    password = '',
    role = '',
    empl_patronymic = '',
    salary = '',
    date_of_birth = '',
    date_of_start = '',
    phone_number = '',
    city = '',
    street = '',
    zip_code = ''
WHERE id_employee = '';
-- Update customer card
UPDATE Customer_Card
SET cust_surname = '',
    cust_name = '',
    cust_patronymic = '',
    phone_number = '',
    city = '',
    street = '',
    zip_code = '',
    percent = 1
WHERE card_number = '';
-- Update category
UPDATE Category
SET category_name = ''
WHERE category_number = 0;
-- Update product
UPDATE Product
SET category_number = 1,
    product_name = '',
    characteristics = ''
WHERE id_product = 0;
-- Update product in store
UPDATE Store_Product
SET UPC_prom = NULL,
    id_product = 1,
    selling_price = 10,
    products_number = 10,
    promotional_product = false
WHERE UPC = '';
--
--
--  Delete queries
--
--
-- Delete employee
DELETE FROM Employee
WHERE id_employee = '';
--WHERE ...
--
-- Delete customer
DELETE FROM Customer_Card
WHERE card_number = '';
--WHERE ...
--
-- Delete category
DELETE FROM Category
WHERE category_number = 0;
--WHERE ...
--
-- Delete product
DELETE FROM Product
WHERE id_product = 0;
--WHERE ...
--
-- Delete product in store
DELETE FROM Store_Product
WHERE UPC = '';
--WHERE ...
--
-- Delete check 
DELETE FROM `Check`
WHERE check_number = '';
--WHERE ...
--
--
--
-- Select employee sorted by surname
-- Отримати інформацію про усіх працівників, відсортованих за прізвищем
SELECT id_employee,
    empl_surname,
    empl_name,
    empl_patronymic,
    role,
    salary,
    date_of_birth,
    date_of_start,
    phone_number,
    city,
    street,
    zip_code
FROM Employee
ORDER BY empl_surname;
--
-- Select employee with role sorted by surname
-- Отримати інформацію про усіх працівників, що займають посаду, відсортованих за прізвищем
SELECT id_employee,
    empl_surname,
    empl_name,
    empl_patronymic,
    salary,
    date_of_birth,
    date_of_start,
    phone_number,
    city,
    street,
    zip_code
FROM Employee
WHERE role = ''
ORDER BY empl_surname;
--
-- Отримати інформацію про усіх постійних клієнтів, відсортованих за прізвищем
SELECT *
FROM Customer_Card
ORDER BY cust_surname;
--
-- Отримати інформацію про усі категорії, відсортовані за назвою
SELECT *
FROM Category
ORDER BY category_name;
--
-- Отримати інформацію про усі товари, відсортовані за назвою
SELECT Product.id_product,
    Product.product_name,
    Product.characteristics,
    Category.category_name
FROM Product
    INNER JOIN Category ON Product.category_number = Category.category_number
ORDER BY Product.product_name;
-- 
-- Отримати інформацію про усі товари у магазині, відсортовані за кількістю
SELECT Store_Product.UPC,
    Store_Product.UPC_prom,
    Product.product_name,
    Product.characteristics,
    Category.category_name,
    Store_Product.selling_price,
    Store_Product.products_number,
    Store_Product.promotional_product
FROM Store_Product
    INNER JOIN Product ON Store_Product.id_product = Product.id_product
    INNER JOIN Category ON Product.category_number = Category.category_number
ORDER BY Store_Product.products_number;
--
-- За прізвищем працівника знайти його телефон та адресу
SELECT id_employee,
    empl_surname,
    phone_number,
    city,
    street,
    zip_code
FROM Employee
WHERE empl_surname LIKE 'Do%';
--
-- Отримати інформацію про усіх постійних клієнтів,
-- що мають карту клієнта із певним відсотком, посортованих за прізвищем
SELECT *
FROM Customer_Card
WHERE percent BETWEEN 2 AND 5
ORDER BY cust_surname;
--
-- За UPC-товару знайти ціну продажу товару, кількість наявних
-- одиниць товару, назву та характеристики товару
SELECT Store_Product.UPC,
    Product.product_name,
    Product.characteristics,
    Category.category_name,
    Store_Product.selling_price,
    Store_Product.products_number,
    Store_Product.promotional_product
FROM Store_Product
    INNER JOIN Product ON Store_Product.id_product = Product.id_product
    INNER JOIN Category ON Product.category_number = Category.category_number
WHERE Store_Product.UPC = '' --
    --
    -- Отримати інформацію про усі акційні товари,
    -- відсортовані за кількістю одиниць товару/ за назвою
SELECT Store_Product.UPC,
    Product.product_name,
    Product.characteristics,
    Category.category_name,
    Store_Product.selling_price,
    Store_Product.products_number,
    Store_Product.promotional_product
FROM Store_Product
    INNER JOIN Product ON Store_Product.id_product = Product.id_product
    INNER JOIN Category ON Product.category_number = Category.category_number
WHERE Store_Product.promotional_product = 1
ORDER BY Store_Product.products_number;
--
-- Отримати інформацію про усі не акційні товари,
-- відсортовані за кількістю одиниць товару/ за назвою
SELECT Store_Product.UPC,
    Product.product_name,
    Product.characteristics,
    Category.category_name,
    Store_Product.selling_price,
    Store_Product.products_number,
    Store_Product.promotional_product
FROM Store_Product
    INNER JOIN Product ON Store_Product.id_product = Product.id_product
    INNER JOIN Category ON Product.category_number = Category.category_number
WHERE Store_Product.promotional_product = 0
ORDER BY Store_Product.products_number;