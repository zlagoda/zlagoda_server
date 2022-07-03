-- кількість проданих продуктів в кожній категорії
SELECT
    SUM(Sale.product_nubmer) AS total_amount,
    SUM(Sale.selling_price) AS total_price,
    Category.category_name
FROM
    Product
INNER JOIN Store_Product ON Store_Product.id_product = Product.id_product
INNER JOIN Sale ON Sale.UPC = Store_Product.UPC
INNER JOIN Check ON Check.check_number = Sale.check_number
INNER JOIN Category ON Category.category_number = Product.category_number
WHERE
    Check.print_date >= '2022-05-29'
GROUP BY
    Product.category_number

-- сума куплених продуктів кожним постійним клієнтом
SELECT SUM(`Check`.sum_total) AS check_sum,
Check.card_number,
Customer_Card.cust_name,
Customer_Card.cust_surname

FROM
    Check
    INNER JOIN Customer_Card ON Customer_Card.card_number = 
Check.card_number
WHERE Check.print_date >= '2022-05-29'
 GROUP BY(Customer_Card.card_number)

-- кількість чеків, які створив кожен працівник за певний перід та їхня 
сума
SELECT
    COUNT(`Check`.`check_number`) AS checks_created,
    SUM(`Check`.`sum_total`) AS total_sum,
    Check.id_employee,
    Employee.empl_name,
    Employee.empl_surname,
    Employee.role
FROM
    Check
INNER JOIN Employee ON Employee.id_employee = Check.id_employee
WHERE
    Employee.role LIKE 'cashier' AND Check.print_date >= '2022-05-29'
GROUP BY
    Check.id_employee

-- iнформація про чеки які містять всі вибрані категорії товарів і тільки 
їх
SELECT
    Store_Product.UPC,
    Y.category_number,
    s1.`check_number`
FROM
    Sale AS s1
INNER JOIN Store_Product ON Store_Product.UPC = s1.UPC
INNER JOIN Product ON Product.id_product = Store_Product.id_product
INNER JOIN Category AS Y
ON
    Y.category_number = Product.category_number
WHERE
    s1.check_number NOT IN(
    SELECT
        s3.check_number
    FROM
        Sale AS s3
    INNER JOIN Store_Product ON Store_Product.UPC = s3.UPC
    INNER JOIN Product ON Product.id_product = Store_Product.id_product
    INNER JOIN Category AS Z
    ON
        Z.category_number = Product.category_number
    WHERE
        Z.category_number IN(
        SELECT
            Category.category_number
        FROM
            Category
        WHERE
            Category.category_number NOT IN('1', '2')
    )
) AND NOT EXISTS(
    SELECT
        *
    FROM
        Category AS c3
    WHERE
        c3.category_number IN('1', '2') AND NOT EXISTS(
        SELECT
            *
        FROM
            Sale AS s2
        INNER JOIN Store_Product ON Store_Product.UPC = s2.UPC
        INNER JOIN Product ON Product.id_product = 
Store_Product.id_product
        INNER JOIN Category AS X
        ON
            X.category_number = Product.category_number
        WHERE
            c3.category_number = X.category_number AND s1.check_number = 
s2.check_number
    )
)
ORDER BY
    s1.check_number


-- Інформація про чеки які містять всі вибрані товари

SELECT
    *
FROM
    Sale AS s1
INNER JOIN Store_Product ON Store_Product.UPC = s1.UPC
INNER JOIN Product AS Y ON Y.id_product = Store_Product.id_product
WHERE NOT
    EXISTS(
    SELECT
        *
    FROM
        Product as p3
    WHERE
        p3.id_product IN('1', '3' ) AND NOT EXISTS(
        SELECT
            *
        FROM
            Sale AS s2
        INNER JOIN Store_Product ON Store_Product.UPC = s2.UPC
        INNER JOIN Product AS X ON X.id_product = Store_Product.id_product
        WHERE
            p3.id_product = X.id_product AND s1.check_number = 
s2.check_number
    )
)


-- Інформацію про чеки які містять всі вибрані категорії товарів

SELECT 
    *
FROM
    Sale AS s1
        INNER JOIN
    Store_Product ON Store_Product.UPC = s1.UPC
        INNER JOIN
    Product ON Product.id_product = Store_Product.id_product
        INNER JOIN
    Category AS Y ON Y.category_number = Product.category_number
WHERE
    NOT EXISTS( SELECT 
            *
        FROM
            Category AS c3
        WHERE c3.category_number IN (1, 3) AND
            NOT EXISTS( SELECT 
                    *
                FROM
                    Sale AS s2
                        INNER JOIN
                    Store_Product ON Store_Product.UPC = s2.UPC
                        INNER JOIN
                    Product ON Product.id_product = 
Store_Product.id_product
                        INNER JOIN
                    Category AS X ON X.category_number = 
Product.category_number
                WHERE c3.category_number = X.category_number AND 
s1.check_number = s2.check_number))
