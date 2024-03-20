INSERT INTO Employee(first_name, last_name, middle_name, address, education) VALUES
    ('Юлия', 'Викторова', 'Николаевна', 'г. Москва, пр-кт Большакова, д. 7, кв. 33', 'МГУ имени М.В.Ломоносова'),
    ('Николай', 'Андреев', 'Викторович', 'г. Москва, пр-кт Большакова, д. 7, кв. 34', 'МГУ имени М.В.Ломоносова'),
    ('Денис', 'Николаев', 'Николаевич', 'г. Москва, пр-кт Большакова, д. 7, кв. 32', 'МГУ имени М.В.Ломоносова'),
    ('Андрей', 'Вахтангов', 'Матвеевич', 'г. Москва, пр-кт Большакова, д. 7, кв. 40', 'НИУ ВШЭ'),
    ('Полина', 'Нечаева', 'Тимуровна', 'г. Москва, пр-кт Большакова, д. 7, кв. 121', 'МГУ имени М.В.Ломоносова'),
    ('Кирилл', 'Стрелочников', 'Андреевич', 'г. Москва, Ломоносовский пр-кт, д. 27, к.11', 'МГУ имени М.В.Ломоносова');

INSERT INTO Department(name, description, director, head_department) VALUES
    ('Аналитика', 'Аналитика всего', 3, NULL),
    ('Разработка', 'Разработка ПО', 1, NULL),
    ('Системная аналитика', 'Систематическое анализирование бизнесс процессов', 3, 1),
    ('Большие данные', 'Анализ больших данных', 3, 1),
    ('Python разработка', 'Разработка приложений на Python', 1, 2),
    ('Java разработка', 'Разработка приложений на Java', 1, 2),
    ('JavaScript разработка', 'Разработка приложений на JavaScript', 1, 2);

INSERT INTO Position_(name, description, duties) VALUES
    ('Разработчик', 'Разрабатывает ПО', '[1]'),
    ('Главный разработчик', 'Опытный и хорошо разрабатывает ПО', '[1, 2]'),
    ('Аналитик', 'Ищет закономерности, анализирует', '[3]'),
    ('Главный аналитик', 'Ищет закономерности, анализирует', '[3, 4]');

INSERT INTO Duty(description) VALUES
    ('Раработка и отладка ПО'),
    ('Проверка ПО, написанного другими разработчиками'),
    ('Построение гипотез, их анализ'),
    ('Проверка гипотез других аналитиков');

INSERT INTO EmployeeInfo(employee_id, position_id, department_id, start_date, end_date) VALUES
    (1, 1, 2, '2022-10-10 6:00:00', '2023-09-09 5:59:59'),
    (2, 1, 5, '2022-10-10 6:00:00', '2022-12-01 5:59:59'),
    (3, 1, 6, '2022-10-10 6:00:00', '2024-01-10 5:59:59'),
    (4, 3, 1, '2021-09-20 6:00:00', '2023-09-09 5:59:59'),
    (4, 4, 1, '2023-09-09 6:00:00', NULL),
    (5, 4, 3, '2023-10-09 6:00:00', NULL),
    (6, 1, 6, '2022-10-10 6:00:00', '2023-09-09 5:59:59'),
    (3, 2, 2, '2024-01-10 6:00:00', NULL);

INSERT INTO Role_(role_name, description) VALUES
    ('Обычный пользователь', 'Ничего особенного'),
    ('Продвинутый пользователь', 'Доступны некоторые функции данные дополнительно');

INSERT INTO User_(first_name, last_name, middle_name, role_id, registration_date, login, password) VALUES
    ('Юлия', 'Викторова', 'Николаевна', 1, '2023-09-09 6:00:00', 'UVN', '1234'),
    ('Николай', 'Андреев', 'Викторович', 1, '2023-09-09 6:00:00', 'NAV88', '1234'),
    ('Денис', 'Николаев', 'Николаевич', 2, '2023-09-09 6:00:00', 'DNN123', '1234'),
    ('Андрей', 'Вахтангов', 'Матвеевич', 1, '2023-09-09 6:00:00', 'ABMlnd256', '2567'),
    ('Полина', 'Нечаева', 'Тимуровна', 1, '2023-09-09 6:00:00', 'PNTPMS123', '5678'),
    ('Кирилл', 'Стрелочников', 'Андреевич', 2, '2023-09-09 6:00:00', 'KSAPMax89', '2t567');
