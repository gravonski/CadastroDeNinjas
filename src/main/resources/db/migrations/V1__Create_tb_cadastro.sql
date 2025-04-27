-- db/migrations/V1__Create_tb_cadastro.sql

CREATE TABLE tb_cadastro (
                             id BIGINT PRIMARY KEY AUTO_INCREMENT,
                             nome VARCHAR(255),
                             email VARCHAR(255) UNIQUE,
                             img_url VARCHAR(255),
                             idade INTEGER,
                             rank VARCHAR(255),
                             id_missoes BIGINT,
                             FOREIGN KEY (id_missoes) REFERENCES tb_missoes(id)
);