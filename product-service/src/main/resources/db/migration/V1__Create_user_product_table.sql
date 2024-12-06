-- Удаление таблицы, если она существует
DROP TABLE IF EXISTS user_product;

-- Создание таблицы userproduct
CREATE TABLE user_product (
                              id BIGSERIAL PRIMARY KEY,
                              acc_number VARCHAR(255) UNIQUE NOT NULL,
                              amount NUMERIC NOT NULL,
                              type_product VARCHAR(255) NOT NULL,
                              user_id NUMERIC NOT NULL
);