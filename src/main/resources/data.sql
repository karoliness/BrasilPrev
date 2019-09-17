INSERT INTO categoria (id, nome) VALUES(1, 'Categoria 1');

insert into produto (id, categoria_id, nome, preco, quantidade, descricao) values (1, 1,'Produto 1',1,10,'descricao produto 1');
insert into Endereco (id, rua, cidade, bairro, cep, estado) values (1,'rua 2','dourados', 'centro','79840000', 'MS');
insert into cliente (id, nome,email,senha,endereco_id) values (1,'cliente 1', 'cliente@com.br','123456',1);