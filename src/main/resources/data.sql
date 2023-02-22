INSERT INTO pessoa(nome, data_de_nascimento) VALUES ('Maria', DATE '1995-10-10');

INSERT INTO endereco(logradouro, cep, numero, cidade, pessoa_id) VALUES ('Rua D', 64009860, 6379, 'Teresina', 1);
INSERT INTO endereco(logradouro, cep, numero, cidade, pessoa_id) VALUES ('Rua C', 64009865, 6380, 'Teresina', 1);

UPDATE pessoa SET endereco_principal_id = 1 WHERE id = 1;