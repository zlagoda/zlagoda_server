-- Отримати інформацію про усі товари, відсортовані за назвою;
SELECT Product.id_product,
       Product.product_name,
       Product.characteristics,
       Category.category_name
FROM Product
         INNER JOIN Category ON Product.category_number = Category.category_number
ORDER BY Product.product_name;

-- Отримати інформацію про усі товари у магазині, відсортовані за назвою;
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
ORDER BY Product.product_name;

-- Отримати інформацію про усіх постійних клієнтів, відсортованих за прізвищем
SELECT *
FROM Customer_card
ORDER BY Customer_Card.cust_surname;

-- Здійснити пошук товарів за назвою;
SELECT *
FROM Product
WHERE Product.product_name = 'cocumber'

-- Здійснити пошук товарів, що належать певній категорії, відсортованих за назвою;
SELECT  Product.id_product,
        Product.product_name,
        Product.category_number,
        Category.category_name,
        Product.characteristics
FROM Product
        INNER JOIN Category ON Product.category_number = Category.category_number
WHERE Category.category_name LIKE 'grocery'
ORDER BY Product.product_name

-- Здійснити пошук постійних клієнтів за прізвищем;
SELECT *
FROM Customer_Card
WHERE Customer_Card.cust_surname LIKE 'Shevchenko'

-- Здійснювати продаж товарів (додавання чеків);
INSERT INTO `Check` (
    card_number,
    city,
    cust_name,
    cust_patronymic,
    cust_surname,
    percent,
    phone_number,
    street,
    zip_code
)
VALUES ('1', 'Lviv', 'Vasyl', 'Vasyliovych', 'kek', '1', '223', 'denysenka', '02001');

INSERT INTO Sale (
    check_number,
    product_nubmer,
    selling_price,
    UPC
)
VALUES ('1', '2', '12', '1');

-- Додавати/редагувати інформацію про постійних клієнтів;
UPDATE Customer_Card
SET cust_surname = 'mohamedov',
    cust_name = 'mohamed',
    cust_patronymic = 'mohamedovich',
    phone_number = '05069',
    city = 'Київ',
    street = 'nigerska',
    zip_code = '228',
    percent = 1
WHERE card_number = '2';

-- Переглянути список усіх чеків, що створив касир за цей день;
SELECT *
FROM `Check`
WHERE   YEAR (CURRENT_DATE) LIKE  YEAR (CURRENT_DATE)
        AND MONTH(CURRENT_DATE) LIKE MONTH( print_date)
        AND DAY(CURRENT_DATE) - DAY( print_date) <= 1

-- Переглянути список усіх чеків, що створив касир за певний період часу;
SELECT *
FROM `Check`
WHERE print_date >= '2022-05-29'

-- За номером чеку вивести усю інформацію про даний чек,
-- в тому числі інформацію про назву,
-- к-сть та ціну товарів, придбаних в даному чеку.
SELECT
    `Check`.`check_number`,
    `Check`.`id_employee`,
    `Check`.`card_number`,
    `Check`.`print_date`,
    `Check`.`sum_total`,
    `Check`.`vat`,
    Sale.product_nubmer,
    Sale.selling_price,
    Product.product_name
FROM
    `Check`
        INNER JOIN Sale ON Sale.check_number = `Check`.`check_number`
        INNER JOIN Store_Product ON Store_Product.UPC = Sale.UPC
        INNER JOIN Product ON Store_Product.id_product = Product.id_product
WHERE
        `Check`.check_number LIKE '1'

-- Отримати інформацію про усі акційні товари, відсортовані за кількістю одиниць
-- товару/ за назвою;
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

-- Отримати інформацію про усі не акційні товарів, відсортовані за кількістю одиниць товару/ за назвою;
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

-- за UPC-товару знайти ціну продажу товару, кількість наявних одиниць товару.
SELECT Store_Product.UPC,
       Product.product_name,
       Store_Product.selling_price,
       Store_Product.products_number

FROM Store_Product
         INNER JOIN Product ON Store_Product.id_product = Product.id_product
WHERE Store_Product.UPC = 'two'

-- Можливість отримати усю інформацію про себе.

SELECT *
FROM Employee
WHERE Employee.role = 'cashier' AND Employee.id_employee = '' -- id касира, який зайшов

