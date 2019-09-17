create table Endereco(
    id bigserial not null,
    rua VARCHAR(255),
    cidade VARCHAR(255),
    bairro VARCHAR(255),
    cep VARCHAR(8),
    estado VARCHAR(2),
    primary key(id)
)