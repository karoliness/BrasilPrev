CREATE TABLE Pedido_item
(
    id BIGSERIAL NOT NULL,
    pedido_id INTEGER,
    produto_id INTEGER,
    quantidade int4,
    valor_unitario NUMERIC(17,2),
    subtotal NUMERIC(17,2),
    PRIMARY KEY(id),
    FOREIGN KEY (pedido_id) REFERENCES pedido(id),
    FOREIGN KEY (produto_id) REFERENCES produto(id)
);
