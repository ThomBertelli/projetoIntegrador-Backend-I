CREATE TABLE IF NOT EXISTS endereco (
id int auto_increment primary key,
rua varchar(32),
numero varchar(12),
cidade varchar(32),
estado char(2));

CREATE TABLE IF NOT EXISTS dentista (
  id int auto_increment primary key,
  nome varchar(255),
  sobrenome varchar(255),
  matricula int
);

CREATE TABLE IF NOT EXISTS paciente (
id int auto_increment primary key,
nome varchar(16),
sobrenome varchar(48),
rg varchar(10),
data_cadastro TIMESTAMP WITHOUT TIME ZONE,
endereco_id int);

--CREATE TABLE IF NOT EXISTS consulta (
--  id int auto_increment primary key,
--  dentista_id int,
--  paciente_id int,
--  data_consulta date,
--);
