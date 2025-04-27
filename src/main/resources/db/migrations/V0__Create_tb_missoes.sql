-- db/migrations/V0__Create_tb_missoes.sql

CREATE TABLE tb_missoes (
                            id BIGINT PRIMARY KEY AUTO_INCREMENT,
                            nome VARCHAR(255),
                            dificuldade VARCHAR(255)
);