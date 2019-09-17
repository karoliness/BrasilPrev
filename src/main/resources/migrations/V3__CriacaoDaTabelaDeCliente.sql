CREATE TABLE Cliente (
    id BIGSERIAL NOT NULL,
    nome VARCHAR(255),
    email VARCHAR(255),
    endereco_id int8,
    senha VARCHAR(255),
    PRIMARY KEY(id)
);

ALTER TABLE IF EXISTS Cliente
    ADD CONSTRAINT FK64nr9yt889by5lufr1boo5i4s
    FOREIGN KEY (endereco_id)
    REFERENCES Endereco;
