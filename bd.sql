-- Criação do Banco de Dados--
   -- DROP DATABASE GestaoAcademia;
CREATE DATABASE IF NOT EXISTS GestaoAcademia;

USE GestaoAcademia;


-- 1. Tabela Academia (Base para as outras)
CREATE TABLE IF NOT EXISTS Academia (
    idAcademia INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    endereco VARCHAR(255),
    telefone VARCHAR(20)
);

-- 2. Tabela Plano
CREATE TABLE IF NOT EXISTS Plano (
    idPlano INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    preco DECIMAL(10, 2) NOT NULL,
    descricao TEXT,
    status BOOLEAN DEFAULT TRUE
);

-- 3. Tabela Funcionario (Superclasse)
CREATE TABLE IF NOT EXISTS Funcionario (
    idFunc INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    cpf VARCHAR(14) UNIQUE NOT NULL,
    salario DECIMAL(10, 2),
    data_admissao DATE,
    idAcademia INT,
    FOREIGN KEY (idAcademia) REFERENCES Academia(idAcademia)
);

-- 4. Tabela Gerente (Herança de Funcionario)
CREATE TABLE IF NOT EXISTS Gerente (
    idFunc INT PRIMARY KEY,
    bonificacao DECIMAL(10, 2),
    nivelAcesso VARCHAR(50),
    FOREIGN KEY (idFunc) REFERENCES Funcionario(idFunc) ON DELETE CASCADE
);

-- 5. Tabela Recepcionista (Herança de Funcionario)
CREATE TABLE IF NOT EXISTS Recepcionista (
    idFunc INT PRIMARY KEY,
    turno VARCHAR(50),
    FOREIGN KEY (idFunc) REFERENCES Funcionario(idFunc) ON DELETE CASCADE
);

-- 6. Tabela Professor (Entidade separada conforme diagrama)
CREATE TABLE IF NOT EXISTS Professor (
    idProf INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    cpf VARCHAR(14) UNIQUE NOT NULL,
    valorPorAula DECIMAL(10, 2),
    idAcademia INT,
    FOREIGN KEY (idAcademia) REFERENCES Academia(idAcademia)
);

-- 7. Tabela Aluno
CREATE TABLE IF NOT EXISTS Aluno (
    idAluno INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    cpf VARCHAR(14) UNIQUE NOT NULL,
    idade INT,
    email VARCHAR(100),
    telefone VARCHAR(20),
    idAcademia INT,
    FOREIGN KEY (idAcademia) REFERENCES Academia(idAcademia)
);

-- 8. Tabela Matricula
CREATE TABLE IF NOT EXISTS Matricula (
    idMatricula INT AUTO_INCREMENT PRIMARY KEY,
    dataInicio DATE NOT NULL,
    dataFim DATE,
    status BOOLEAN DEFAULT TRUE,
    idAluno INT NOT NULL,
    idPlano INT NOT NULL,
    FOREIGN KEY (idAluno) REFERENCES Aluno(idAluno),
    FOREIGN KEY (idPlano) REFERENCES Plano(idPlano)
);

-- 9. Tabela Pagamento
CREATE TABLE IF NOT EXISTS Pagamento (
    idPagamento INT AUTO_INCREMENT PRIMARY KEY,
    dataPagamento DATETIME,
    dataVencimento DATETIME NOT NULL,
    valor DECIMAL(10, 2) NOT NULL,
    metodoPagamento VARCHAR(50),
    status BOOLEAN,
    idAluno INT NOT NULL,
    FOREIGN KEY (idAluno) REFERENCES Aluno(idAluno)
);

-- 10. Tabela AvaliacaoFisica
CREATE TABLE IF NOT EXISTS AvaliacaoFisica (
    idAvaliacao INT AUTO_INCREMENT PRIMARY KEY,
    data DATE NOT NULL,
    peso FLOAT,
    altura FLOAT,
    percentualGordura FLOAT,
    massaMuscular FLOAT,
    medidas TEXT,
    proximaAvaliacao DATE,
    idAluno INT NOT NULL,
    FOREIGN KEY (idAluno) REFERENCES Aluno(idAluno)
);

-- 11. Tabela Aula
CREATE TABLE IF NOT EXISTS Aula(
    idAula INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    horario TIME NOT NULL,
    capacidadeMaxima INT,
    idProf INT, -- Professor responsável pela aula
    FOREIGN KEY (idProf) REFERENCES Professor(idProf)
);

-- 12. Tabela Treino
CREATE TABLE IF NOT EXISTS Treino(
    idTreino INT AUTO_INCREMENT PRIMARY KEY,
    descricao TEXT,
    dataInicio DATE,
    dataFim DATE,
    idAluno INT NOT NULL,
    idProf INT, -- Professor que montou o treino
    FOREIGN KEY (idAluno) REFERENCES Aluno(idAluno),
    FOREIGN KEY (idProf) REFERENCES Professor(idProf)
);

-- 13. Tabela Associativa: Aluno_Aula (Para o relacionamento N:M entre Aluno e Aula)
CREATE TABLE IF NOT EXISTS Aluno_Aula  (
    idAluno INT,
    idAula INT,
    PRIMARY KEY (idAluno, idAula),
    FOREIGN KEY (idAluno) REFERENCES Aluno(idAluno),
    FOREIGN KEY (idAula) REFERENCES Aula(idAula)
);

-- PROCCEDURES ==============

DELIMITER $$

DROP PROCEDURE IF EXISTS sp_ListarAlunosETreinosPorProfessor $$


CREATE PROCEDURE sp_ListarAlunosETreinosPorProfessor(IN p_idProf INT)
BEGIN
    SELECT 
        -- Dados do Aluno
        a.idAluno, 
        a.nome, 
        a.cpf,
        -- Dados do Treino (evita duplicidade aqui pegamos o ativo)
        t.idTreino,
        t.descricao,
        t.dataInicio,
        t.dataFim
    FROM Aluno a
    INNER JOIN Treino t ON a.idAluno = t.idAluno
    WHERE t.idProf = p_idProf
    ORDER BY a.nome ASC;
END $$

DELIMITER ;

USE GestaoAcademia;

DELIMITER $$

-- Remove se já existir (para evitar erros de duplicidade)
DROP PROCEDURE IF EXISTS sp_ListarTodosOsTreinos $$

-- Cria a procedure que falta
CREATE PROCEDURE sp_ListarTodosOsTreinos()
BEGIN
    SELECT 
        idTreino, 
        descricao, 
        dataInicio, 
        dataFim, 
        idAluno
    FROM Treino;
END $$

DELIMITER ;


DELIMITER $$

-- 1. Buscar Aluno pelo CPF
DROP PROCEDURE IF EXISTS sp_BuscarAlunoPorCPF $$
CREATE PROCEDURE sp_BuscarAlunoPorCPF(IN p_cpf VARCHAR(14))
BEGIN
    SELECT * FROM Aluno WHERE cpf = p_cpf;
END $$

-- 2. Buscar Plano pelo ID
DROP PROCEDURE IF EXISTS sp_BuscarPlanoPorID $$
CREATE PROCEDURE sp_BuscarPlanoPorID(IN p_idPlano INT)
BEGIN
    SELECT * FROM Plano WHERE idPlano = p_idPlano;
END $$

DELIMITER $$
DELIMITER $$

CREATE PROCEDURE sp_BuscarProfessorPorCPF(IN p_CPF VARCHAR(14))
BEGIN
    -- Seleciona os dados do professor onde o CPF coincide
    SELECT 
        IdProf,
        Nome,
        CPF
        
    FROM 
        Professor
    WHERE 
        CPF = p_CPF;
END $$

CREATE PROCEDURE sp_BuscarProprioTreino(IN p_idAluno INT)
BEGIN
    SELECT 
        idTreino, 
        descricao, 
        dataInicio, 
        dataFim, 
        idProf 
    FROM 
        Treino
    WHERE 
        idAluno = p_idAluno
        -- Opcional: Garante que só traga treinos que ainda não venceram
        AND (dataFim >= CURDATE() OR dataFim IS NULL)
    ORDER BY 
        dataInicio DESC -- Pega o mais recente caso haja mais de um
    LIMIT 1; -- Retorna apenas um treino (o objeto objTreino do diagrama)
END $$

DELIMITER ;

-- 3. Salvar Matrícula (Recebendo IDs, pois o banco é relacional)
DELIMITER $$
CREATE PROCEDURE sp_AdicionarMatricula(
    IN p_idAluno INT, 
    IN p_idPlano INT, 
    IN p_dataInicio DATE, 
    IN p_dataFim DATE
)
BEGIN
    INSERT INTO Matricula (idAluno, idPlano, dataInicio, dataFim, status)
    VALUES (p_idAluno, p_idPlano, p_dataInicio, p_dataFim, 1);
END $$

DELIMITER ;


DELIMITER $$

DROP PROCEDURE IF EXISTS sp_BuscarPagamentoPorID $$

CREATE PROCEDURE sp_BuscarPagamentoPorID(IN p_idPagamento INT)
BEGIN
    SELECT * FROM Pagamento  
    WHERE idPagamento = p_idPagamento;
END $$

DELIMITER ;


-- ==================================================
-- 1. DADOS BASE (Academia e Planos)
-- ==================================================

INSERT INTO Academia (nome, endereco, telefone) VALUES 
('Academia JavaFit', 'Av. dos Programadores, 1010', '(11) 9999-0000');

INSERT INTO Plano (nome, preco, descricao, status) VALUES 
('Plano Iron', 89.90, 'Acesso à musculação', 1),
('Plano Gold', 129.90, 'Musculação + Aulas Coletivas', 1),
('Plano Platinum', 199.90, 'Tudo incluso + Personal', 1);

-- ==================================================
-- 2. PESSOAS (Funcionários, Professores e Alunos)
-- ==================================================

-- Funcionários (Tabela Pai)
INSERT INTO Funcionario (nome, cpf, salario, data_admissao, idAcademia) VALUES 
('Carlos Gerentão', '111.111.111-00', 5000.00, '2020-01-10', 1), -- ID 1
('Ana Recepcionista', '222.222.222-00', 1800.00, '2022-05-15', 1); -- ID 2

-- Gerente (Filho) - Vinculado ao ID 1
INSERT INTO Gerente (idFunc, bonificacao, nivelAcesso) VALUES 
(1, 1000.00, 'ADMIN_TOTAL');

-- Recepcionista (Filho) - Vinculado ao ID 2
INSERT INTO Recepcionista (idFunc, turno) VALUES 
(2, 'Manhã/Tarde');

-- Professores (Tabela Separada)
INSERT INTO Professor (nome, cpf, valorPorAula, idAcademia) VALUES 
('Prof. Jiraya', '333.333.333-33', 60.00, 1),   -- ID 1
('Prof. Kakashi', '444.444.444-44', 60.00, 1);  -- ID 2

-- Alunos
INSERT INTO Aluno (nome, cpf, idade, email, telefone, idAcademia) VALUES 
('Naruto Uzumaki', '555.555.555-55', 17, 'naruto@email.com', '9999-1111', 1), -- ID 1
('Sasuke Uchiha', '666.666.666-66', 18, 'sasuke@email.com', '9999-2222', 1),  -- ID 2
('Sakura Haruno', '777.777.777-77', 17, 'sakura@email.com', '9999-3333', 1),  -- ID 3
('Itachi Uchiha', '888.888.888-88', 26, 'itachinho@email.com', "9898-3131", 1); -- ID 4

-- ==================================================
-- 3. OPERACIONAL (Matrículas e Pagamentos)
-- ==================================================

-- Matrículas
INSERT INTO Matricula (dataInicio, dataFim, status, idAluno, idPlano) VALUES 
('2023-01-01', '2024-01-01', 1, 1, 2), -- Naruto no Plano Gold (Ativo)
('2023-02-01', '2024-02-01', 1, 2, 3), -- Sasuke no Plano Platinum (Ativo)
('2022-01-01', '2023-01-01', 0, 3, 1); -- Sakura (Vencido/Inativo)

-- Pagamentos
INSERT INTO Pagamento (dataPagamento, dataVencimento, valor, metodoPagamento, status, idAluno) VALUES 
('2025-10-05 14:50', '2026-10-10 14:50', 129.90, 'Pix', 1, 1), -- Pagamento do Naruto
('2025-10-05 14:50', '2026-10-10 14:50', 199.90, 'Boleto', 0, 2),       -- Pagamento pendente do Sasuke
('2025-12-07 14:50', '2026-10-10 14:50', 199.90, 'Cartao de crédito', 1, 4);

-- ==================================================
-- 4. TREINOS E AVALIAÇÕES (Onde suas procedures brilham)
-- ==================================================

-- Avaliação Física (Para o Naruto)
INSERT INTO AvaliacaoFisica (data, peso, altura, percentualGordura, massaMuscular, medidas, proximaAvaliacao, idAluno) VALUES 
('2023-09-01', 70.5, 1.75, 12.0, 35.0, 'Braço: 35cm, Peito: 100cm', '2023-12-01', 1);

-- TREINOS (Importante para o teste do TreinoControl)

-- Treino 1: Naruto (Monster Full Body - Foco em Força Bruta)
-- Uma lista gigante misturando tudo. 
-- OBS: Verifique se sua coluna 'descricao' no banco suporta mais de 255 caracteres (ex: VARCHAR(1000) ou TEXT).
INSERT INTO Treino (descricao, dataInicio, dataFim, idAluno, idProf) VALUES 
('Esteira 10min (Aquecimento), Agachamento Livre 4x8, Supino Reto com Barra 4x10, Levantamento Terra 3x6, Leg Press 45 4x12, Remada Curvada 4x10, Desenvolvimento Militar 4x10, Cadeira Extensora 3x15, Puxada Alta Frente 4x12, Tríceps Testa 3x12, Rosca Direta 3x12, Elevação Lateral 4x15, Panturrilha Sentado 4x20, Abdominal Supra com Carga 4x20, Alongamento Geral 5min', '2025-01-01', '2026-12-31', 1, 1);

-- Treino 2: Sasuke (Full Body Atlético/Funcional)
-- Muitos exercícios compostos e calistênicos misturados com pesos.
INSERT INTO Treino (descricao, dataInicio, dataFim, idAluno, idProf) VALUES 
('Mobilidade de Quadril e Ombros 10min, Agachamento Frontal 4x8, Barra Fixa com Carga 4x6, Afundo com Halteres 3x12, Supino Inclinado Halteres 4x10, Stiff 4x10, Remada Serrote 3x12, Mesa Flexora 4x12, Dips (Paralelas) 4x10, Face Pull 4x15, Rosca Martelo 4x12, Elevação Pélvica 3x12, Prancha Abdominal 4x1min, Cardio HIIT 15min', '2025-06-01', '2026-12-31', 2, 2);

-- Treino 3: Sakura (Endurance/Resistência Total)
-- Alto volume de repetições e muitos exercícios de isolamento.
INSERT INTO Treino (descricao, dataInicio, dataFim, idAluno, idProf) VALUES 
('Elíptico 10min, Agachamento Sumô 4x15, Puxada Alta Triângulo 3x15, Leg Press Horizontal 3x20, Supino Máquina Articulada 3x15, Cadeira Abdutora 4x20, Cadeira Adutora 4x20, Glúteo Caneleira 4x15, Tríceps Pulley 3x15, Rosca Alternada 3x15, Elevação Frontal 3x15, Abdominal Infra 3x20, Abdominal Obíquo 3x20, Bike 20min', '2024-01-01', '2024-12-31', 3, 2);-- ==================================================
-- 5. AULAS (Relacionamento N:M)
-- ==================================================

INSERT INTO Aula (nome, horario, capacidadeMaxima, idProf) VALUES 
('Muay Thai', '19:00:00', 20, 1),  -- Prof Jiraya dá aula
('Yoga Zen', '08:00:00', 15, 2);    -- Prof Kakashi dá aula

-- Alunos nas Aulas (Tabela Associativa)
INSERT INTO Aluno_Aula (idAluno, idAula) VALUES 
(1, 1), -- Naruto faz Muay Thai
(2, 1), -- Sasuke faz Muay Thai
(3, 2); -- Sakura faz Yoga


SELECT * FROM Matricula 
ORDER BY idMatricula DESC -- Mostra as últimas inseridas primeiro



