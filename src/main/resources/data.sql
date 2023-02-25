INSERT INTO pessoa (nome, data_nascimento) VALUES
  ('João',  '1990-01-01'),
  ('Maria',  '1995-02-02');

INSERT INTO endereco (pessoa_id, logradouro, cep, numero, cidade, principal) VALUES
  ( 1, 'Rua A', '12345-678', 10, 'Cidade A', TRUE),
  ( 1, 'Rua B', '98765-432', 20, 'Cidade B', FALSE),
  ( 2, 'Rua C', '54321-876', 30, 'Cidade C', TRUE),
  ( 2, 'Rua D', '65432-987', 40, 'Cidade D', FALSE);






