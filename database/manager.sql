-- insert new employee
INSERT INTO Employee (
        id_employee,
        empl_surname,
        empl_name,
        password,
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
        'Jr.',
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
        '404457230720', 1, 10.5, 100, false);