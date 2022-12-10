INSERT INTO pessoa (cpf, nome)
VALUES
  	('56637927082', 'Pessoa 1'),
  	('56637927082', 'Pessoa 2');

INSERT INTO produto (descricao, valor_unitario)
VALUES
  	('Produto 1', 10.0),
  	('Produto 2', 20.0),
  	('Produto 3', 20.0)
  	;
INSERT INTO pedido (pessoa_id)
VALUES
    (1),
    (2)
;
INSERT INTO pedido_item (produto_id, quantidade,pedido_id)
VALUES
    (1, 10, 1),
    (3, 15, 1),
    (2, 50, 2)
;