CREATE TABLE customer
(
    id integer NOT NULL,
    fullname character varying(255),
    email character varying(255),
    CONSTRAINT customer_pkey PRIMARY KEY (id)
);


INSERT INTO customer (id, fullname, email) VALUES (1, 'John Doe', 'jd@example.com')
UPDATE customer t SET t.email = 'john.doe@example.com' WHERE t.id = 1
DELETE FROM customer WHERE id = 1
