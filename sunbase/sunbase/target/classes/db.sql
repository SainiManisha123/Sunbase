-- Create the customer database
CREATE DATABASE customer;

-- Connect to the customer database

-- Create the Customer table in the public schema
CREATE TABLE public.customer (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    street VARCHAR(255),
    address VARCHAR(255),
    city VARCHAR(255),
    state VARCHAR(255),
    email VARCHAR(255) UNIQUE,
    phone VARCHAR(20),
	password VARCHAR(255),
    enabled BOOLEAN
);

-- Grant access to the postgres user
GRANT ALL PRIVILEGES ON DATABASE customer TO postgres;

-- Insert the sample customer record
-- Insert the sample customer record
INSERT INTO public.customer (
    first_name,
    last_name,
    street,
    address,
    city,
    state,
    email,
    phone,
    password,
    enabled
) VALUES (
    'Jane',
    'Doe',
    'Elvnu Street',
    'H no 2',
    'Delhi',
    'Delhi',
    'sam@gmail.com',
    '12345678',
    '123',
    true
);