-- Inserir três novas pessoas
INSERT INTO pessoa(nome, data_de_nascimento) 
VALUES 
  ('Maria', DATE '1995-10-10'),
  ('Joao', DATE '1988-05-20'),
  ('Ana', DATE '2001-12-01');

-- Inserir endereços para as pessoas
INSERT INTO endereco(logradouro, cep, numero, cidade, pessoa_id) 
VALUES 
  ('Rua D', '64009-860', 6379, 'Teresina', 1),
  ('Rua C', '64009-865', 6380, 'Teresina', 1),
  ('Av. Paulista', '01310-100', 1000, 'São Paulo', 2),
  ('Rua das Palmeiras', '04135-050', 150, 'São Paulo', 2),
  ('Av. Atlântica', '22011-010', 200, 'Rio de Janeiro', 2),
  ('Rua das Flores', '79002-050', 50, 'Campo Grande', 3),
  ('Rua das Pedras', '79002-100', 100, 'Campo Grande', 3),
  ('Rua da Rocha', '79002-200', 150, 'Campo Grande', 3);
  
-- Inserir o endereço principal para as pessoas  
UPDATE pessoa SET endereco_principal_id = 2 WHERE id = 1;
UPDATE pessoa SET endereco_principal_id = 3 WHERE id = 2;
UPDATE pessoa SET endereco_principal_id = 8 WHERE id = 3;





