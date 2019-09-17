CREATE TABLE Pedido (
    id BIGSERIAL NOT NULL,
    data_de_criacao TIMESTAMP,
    situacao int4,
    cliente_id int8,
    sessao varchar(255),
    PRIMARY KEY (id)
);

ALTER TABLE IF EXISTS Pedido
  ADD CONSTRAINT FK_clienteId
  FOREIGN KEY (cliente_id)
  REFERENCES Cliente;
