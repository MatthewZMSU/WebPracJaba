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
    head_department INTEGER
);

DROP TABLE IF EXISTS Duty CASCADE;
CREATE TABLE Duty(
    duty_id SERIAL PRIMARY KEY,
    description TEXT
);

DROP TABLE IF EXISTS JobPost CASCADE;
CREATE TABLE JobPost(
    job_post_id SERIAL PRIMARY KEY,
    name VARCHAR(50),
    description TEXT,
    duties INTEGER ARRAY
);

DROP TABLE IF EXISTS EmployeeInfo CASCADE;
CREATE TABLE EmployeeInfo(
    employee_info_id SERIAL PRIMARY KEY,
    employee_id INTEGER REFERENCES Employee(employee_id),
    job_post_id INTEGER REFERENCES JobPost(job_post_id),
    department_id INTEGER REFERENCES Department(department_id),
    start_date TIMESTAMP,
    end_date TIMESTAMP
);

DROP TABLE IF EXISTS UserType CASCADE;
CREATE TABLE UserType(
    user_type_id SERIAL PRIMARY KEY,
    user_type_name VARCHAR(50),
    description TEXT
);

DROP TABLE IF EXISTS UserAccount CASCADE;
CREATE TABLE UserAccount(
    user_account_id SERIAL PRIMARY KEY,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    middle_name VARCHAR(50),
    user_type_id INTEGER REFERENCES UserType(user_type_id),
    registration_date TIMESTAMP,
    login VARCHAR(20),
    password VARCHAR(20)
);
