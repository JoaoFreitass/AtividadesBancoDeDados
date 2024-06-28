--Create database  Dinossauros
-- Criando as tabelas
CREATE TABLE Era (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    ano_inicio INT NOT NULL,
    ano_fim INT NOT NULL
);

CREATE TABLE Grupo (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(50) NOT NULL,
    tipo_alimentacao VARCHAR(50) NOT NULL
);

CREATE TABLE Dinossauro (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    grupo_id INT NOT NULL,
    toneladas INT NOT NULL,
    ano_descoberta INT NOT NULL,
    descobridor VARCHAR(255) NOT NULL,
    era_id INT NOT NULL,
    inicio_existencia INT NOT NULL,
    fim_existencia INT NOT NULL,
    pais VARCHAR(255) NOT NULL,
    FOREIGN KEY (grupo_id) REFERENCES Grupo(id),
    FOREIGN KEY (era_id) REFERENCES Era(id)
);

-- Colocando dados nas tabelas
INSERT INTO Era (nome, ano_inicio, ano_fim) VALUES
    ('Triássico', 251000000, 200000000),
    ('Jurássico', 200000000, 145000000),
    ('Cretáceo', 145000000, 65000000);


INSERT INTO Grupo (nome, tipo_alimentacao) VALUES
    ('Anquilossauros', 'Herbívora'),
    ('Ceratopsídeos', 'Herbívora'),
    ('Estegossauros', 'Herbívora'),
    ('Terápodes', 'Carnívora');


INSERT INTO Dinossauro (nome, grupo_id, toneladas, ano_descoberta, descobridor, era_id, inicio_existencia, fim_existencia, pais) VALUES
    ('Saichania', 1, 4, 1977, 'Maryanska', 3, 145000000, 66000000, 'Mongólia'),
    ('Tricerátops', 2, 6, 1887, 'John Bell Hatcher', 3, 70000000, 66000000, 'Canadá'),
    ('Kentrossauro', 3, 2, 1909, 'Cientistas Alemães', 2, 200000000, 145000000, 'Tanzânia'),
    ('Pinacossauro', 1, 6, 1999, 'Museu Americano de História Natural', 1, 85000000, 75000000, 'China'),
    ('Alossauro', 4, 3, 1877, 'Othniel Charles Marsh', 2, 155000000, 150000000, 'América do Norte');

CREATE TABLE Dinossauro (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    grupo_id INT NOT NULL,
    toneladas INT NOT NULL,
    ano_descoberta INT NOT NULL,
    descobridor VARCHAR(255) NOT NULL,
    era_id INT NOT NULL,
    inicio_existencia INT NOT NULL,
    fim_existencia INT NOT NULL,
    pais VARCHAR(255) NOT NULL,
    FOREIGN KEY (grupo_id) REFERENCES Grupo(id),
    FOREIGN KEY (era_id) REFERENCES Era(id)
);

-- Validar os anos da existencia do dino
CREATE OR REPLACE FUNCTION validate_years()
RETURNS TRIGGER AS $$
DECLARE
    era_inicio INT;
    era_fim INT;
BEGIN
-- Obter os anos de início e fim da era informada
    SELECT ano_inicio, ano_fim INTO era_inicio, era_fim
    FROM Era
    WHERE id = NEW.era_id;

-- Validar os anos de existência do dinossauro
    IF NEW.inicio_existencia < era_inicio OR NEW.fim_existencia > era_fim THEN
        RAISE EXCEPTION 'Os anos de existência do dinossauro não condizem com a era informada.';
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Validar antes do isert
DROP TRIGGER IF EXISTS validate_years_before_insert ON Dinossauro;
CREATE TRIGGER validate_years_before_insert
BEFORE INSERT ON Dinossauro
FOR EACH ROW
EXECUTE FUNCTION validate_years();

-- Validar antes do update
DROP TRIGGER IF EXISTS validate_years_before_update ON Dinossauro;
CREATE TRIGGER validate_years_before_update
BEFORE UPDATE ON Dinossauro
FOR EACH ROW
EXECUTE FUNCTION validate_years();
	 

-- Inserção dentro da era correta
INSERT INTO Dinossauro (nome, grupo_id, toneladas, ano_descoberta, descobridor, era_id, inicio_existencia, fim_existencia, pais)
VALUES ('Velociraptor', 4, 1, 1993, 'Alan Grant', 2, 200000000, 145000000, 'Mongólia');
INSERT INTO Dinossauro (nome, grupo_id, toneladas, ano_descoberta, descobridor, era_id, inicio_existencia, fim_existencia, pais)
VALUES ('Velociraptor', 4, 1, 1993, 'Alan Grant', 2, 200000000, 145000000, 'Mongólia');

-- Inserção fora da era
INSERT INTO Dinossauro (nome, grupo_id, toneladas, ano_descoberta, descobridor, era_id, inicio_existencia, fim_existencia, pais)
VALUES ('Stegosaurus', 3, 2, 1910, 'Charles Gilmore', 1, 230000000, 200000000, 'Estados Unidos');
INSERT INTO Dinossauro (nome, grupo_id, toneladas, ano_descoberta, descobridor, era_id, inicio_existencia, fim_existencia, pais)
VALUES ('Tricerátops', 2, 6, 1887, 'John Bell Hatcher', 3, 70000000, 66000000, 'Canadá');


SELECT * FROM DINOSSAURO