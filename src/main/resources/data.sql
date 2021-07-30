-- test

INSERT INTO customer(customer_id, name, first_name, account_balance)
VALUES (1, 'Iwanesko', 'Alexandre', 1000),
       (2, 'Thibault', 'Vuillaume', 10000),
       (3, 'Jeff', 'Bezos', 2000000);


INSERT INTO distributeur(id, quantity_money_available, automat_identifier)
VALUES (1, 0, 'broke'),
       (2, 10000, 'gates'),
       (3, 100, 'stanadrd');
