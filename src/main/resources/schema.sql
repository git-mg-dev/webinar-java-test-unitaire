
CREATE TABLE distributeur
(
    id IDENTITY NOT NULL,
    quantity_money_available INTEGER      NOT NULL,
    automat_identifier       VARCHAR(255) NOT NULL,
    CONSTRAINT distributeur_pk PRIMARY KEY (id)
);

CREATE TABLE customer
(
    customer_id IDENTITY NOT NULL,
    name            VARCHAR(255) NOT NULL,
    first_name      VARCHAR(255) NOT NULL,
    account_balance INTEGER      NOT NULL,
    CONSTRAINT customer_pk PRIMARY KEY (customer_id)
);


