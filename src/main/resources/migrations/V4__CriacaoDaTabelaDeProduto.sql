CREATE TABLE Produto(
    id BIGSERIAL NOT NULL,
    categoria_id int8,
    nome VARCHAR(255),
    preco NUMERIC(17,2),
    quantidade int4,
    descricao VARCHAR(255),
    foto VARCHAR(255),
    PRIMARY KEY(id)
);

ALTER TABLE IF EXISTS Produto
   ADD CONSTRAINT FK_id_categoriaId
   FOREIGN KEY (categoria_id)
   REFERENCES Categoria;