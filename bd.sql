CREATE TABLE LocalEvento(
	Id UNIQUEIDENTIFIER PRIMARY KEY NOT NULL,
	Nome VARCHAR(70),
	CEP VARCHAR(10)
)

CREATE TABLE Setor(
	Id UNIQUEIDENTIFIER PRIMARY KEY NOT NULL,
	LocalEvento UNIQUEIDENTIFIER NOT NULL FOREIGN KEY REFERENCES LocalEvento(Id),
	Nome VARCHAR(20)
)

CREATE TABLE Assento(
	Id UNIQUEIDENTIFIER PRIMARY KEY NOT NULL,
	Setor UNIQUEIDENTIFIER NOT NULL FOREIGN KEY REFERENCES Setor(ID),

	Posicao INT NOT NULL,
	Fileira INT NOT NULL,

	UNIQUE(Setor, Posicao, Fileira)
)

CREATE TABLE Espetaculo(
	Id UNIQUEIDENTIFIER PRIMARY KEY NOT NULL,
	Nome VARCHAR(70) NOT NULL,
	ClassificacaoIndicativa INT NOT NULL
)

CREATE TABLE EdicaoEspetaculo(
	Id UNIQUEIDENTIFIER PRIMARY KEY NOT NULL,
	Espetaculo UNIQUEIDENTIFIER NOT NULL FOREIGN KEY REFERENCES Espetaculo(Id),
	DataEspetaculo DATETIME NOT NULL,
	LocalEvento UNIQUEIDENTIFIER NOT NULL FOREIGN KEY REFERENCES LocalEvento(Id),

	UNIQUE (Espetaculo, DataEspetaculo, LocalEvento)
)

CREATE TABLE TipoIngresso(
	Id UNIQUEIDENTIFIER PRIMARY KEY NOT NULL,
	Espetaculo UNIQUEIDENTIFIER NOT NULL,
	Nome VARCHAR(25) NOT NULL,
	Preco FLOAT NOT NULL,
	Quantidade INT NOT NULL
)

CREATE TABLE Espectador(
	CPF CHAR(14) PRIMARY KEY NOT NULL,

	NomeCompleto VARCHAR(150) NOT NULL,
	Sexo CHAR(1) NOT NULL,
	DataNasc DATE NOT NULL,
	Telefone VARCHAR(11) NOT NULL,
	Email VARCHAR(40) NOT NULL,

	Senha VARCHAR(64) NOT NULL
)

CREATE TABLE Ingresso(
	TipoIngresso UNIQUEIDENTIFIER NOT NULL FOREIGN KEY REFERENCES TipoIngresso(Id),
	EdicaoEspetaculo UNIQUEIDENTIFIER NOT NULL FOREIGN KEY REFERENCES EdicaoEspetaculo(Id),
	Assento UNIQUEIDENTIFIER NOT NULL FOREIGN KEY REFERENCES Assento(Id),
	Espectador CHAR(14) NOT NULL FOREIGN KEY REFERENCES Espectador(CPF),
	StatusIngresso SMALLINT NOT NULL DEFAULT 0 -- 0: pendente / 1: confirmado 
)

CREATE TRIGGER tg_CheckIngresso ON Ingresso
INSTEAD OF INSERT, UPDATE
AS
BEGIN
	IF ((SELECT COUNT(ee.Id) FROM EdicaoEspetaculo ee
		INNER JOIN inserted i ON i.EdicaoEspetaculo = ee.Id
		INNER JOIN Assento a ON i.Assento = a.Id
		INNER JOIN Setor s ON s.Id = a.Setor
		INNER JOIN LocalEvento ON ee.LocalEvento = s.LocalEvento) = 0) -- Se os locais são diferentes
	BEGIN
		RAISERROR ('Locais do espetáculo diferentes', 5, 1);
		ROLLBACK TRANSACTION;
		RETURN;
	END
	ELSE IF ((SELECT COUNT(e.Id) FROM Espetaculo e
			  INNER JOIN EdicaoEspetaculo ee ON e.Id = ee.Espetaculo
			  INNER JOIN inserted i ON i.EdicaoEspetaculo = ee.Id
			  INNER JOIN Ingresso ig ON i.TipoIngresso = ig.TipoIngresso) = 0) -- Se os eventos são diferentes
	BEGIN
		RAISERROR('Espetáculos diferentes', 5, 1);
		ROLLBACK TRANSACTION;
		RETURN;
	END
	ELSE
		INSERT INTO Ingresso SELECT * FROM inserted;
END;


----- VIEWS -----

CREATE FUNCTION vw_AssentosOcupados(@edicaoespetaculo UNIQUEIDENTIFIER)
RETURNS TABLE
AS
	RETURN SELECT a.Id FROM Assento a
		   INNER JOIN Ingresso i ON i.Assento = a.Id
		   WHERE i.EdicaoEspetaculo = @edicaoespetaculo 

CREATE FUNCTION vw_AssentosDisponiveis(@edicaoespetaculo UNIQUEIDENTIFIER)
RETURNS TABLE
AS
	RETURN SELECT s.Nome AS 'Setor', a.Fileira AS 'Fileira', a.Posicao AS 'Posição' FROM LocalEvento le
		   INNER JOIN Setor s ON s.LocalEvento = le.Id
		   INNER JOIN Assento a ON s.Id = a.Setor
		   WHERE le.Id = (SELECT ee.LocalEvento FROM EdicaoEspetaculo ee WHERE ee.Id = @edicaoespetaculo)
		   -- seleciona todos os assentos do local de evento
		   AND a.Id NOT IN (SELECT * FROM vw_AssentosOcupados(@edicaoespetaculo))

SELECT * FROM  vw_AssentosDisponiveis(NEWID())