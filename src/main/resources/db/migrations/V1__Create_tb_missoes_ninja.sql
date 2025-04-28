-- db/migrations/V1__Create_tb_missoes_ninja.sql
CREATE TABLE tb_missoes_ninja (
                                  id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                  nome VARCHAR(255),
                                  dificuldade VARCHAR(255)
);
