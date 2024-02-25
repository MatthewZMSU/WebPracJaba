DROP TABLE IF EXISTS Employee CASCADE;
CREATE TABLE Employee(
    employee_id SERIAL PRIMARY KEY,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    middle_name VARCHAR(50),
    address VARCHAR(200),
    education VARCHAR(200)
);

DROP TABLE IF EXISTS Department CASCADE;
CREATE TABLE Department(
    department_id SERIAL PRIMARY KEY,
    name VARCHAR(50),
    description TEXT,
    director INTEGER REFERENCES Employee(employee_id),
    internal_departments VARCHAR(50) ARRAY,
    head_department VARCHAR(50)
);

DROP TABLE IF EXISTS Position_ CASCADE;
CREATE TABLE Position_(
    position_id SERIAL PRIMARY KEY,
    name VARCHAR(50),
    description TEXT,
    duties TEXT
);

DROP TABLE IF EXISTS EmployeeInfo CASCADE;
CREATE TABLE EmployeeInfo(
    employee_info_id SERIAL PRIMARY KEY,
    employee_id INTEGER REFERENCES Employee(employee_id),
    position_id INTEGER REFERENCES Position_(position_id),
    department_id INTEGER REFERENCES Department(department_id),
    start_date TIMESTAMP,
    end_date TIMESTAMP
);

DROP TABLE IF EXISTS Role_ CASCADE;
CREATE TABLE Role_(
    role_id SERIAL PRIMARY KEY,
    role_name VARCHAR(50),
    description TEXT
);

DROP TABLE IF EXISTS User_ CASCADE;
CREATE TABLE User_(
    user_id SERIAL PRIMARY KEY,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    middle_name VARCHAR(50),
    role_id INTEGER REFERENCES User_(role_id),
    registration_date TIMESTAMP,
    login VARCHAR(20),
    password VARCHAR(20)
);
