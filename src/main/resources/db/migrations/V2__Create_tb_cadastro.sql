-- db/migrations/V2__Create_tb_cadastro.sql
CREATE TABLE tb_cadastro (
                             id BIGINT AUTO_INCREMENT PRIMARY KEY,
                             nome VARCHAR(255),
                             email VARCHAR(255) UNIQUE,
                             img_url VARCHAR(255),
                             idade INT,
                             rank VARCHAR(255),
                             id_missoes BIGINT,

                             CONSTRAINT fk_tb_cadastro_missoes FOREIGN KEY (id_missoes)
                                 REFERENCES tb_missoes_ninja(id)
);
