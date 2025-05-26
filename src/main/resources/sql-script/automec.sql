-- automec_desenv.grupo (Grupo de produtos) definição

select * from automec_desenv.grupo g;

select * from automec_desenv.grupo g where g.des_grupo like concat("%", "Sistema", "%")

insert into automec_desenv.grupo (des_grupo)
values
('Sistema de Freios'),
('Suspensão e Direção'),
('Sistema de Ignição'),
('Filtros e Lubrificantes'),
('Sistema de Arrefecimento'),
('Componentes Elétricos'),
('Embreagens e Transmissões'),
('Sistemas de Exaustão'),
('Peças de Motor'),
('Transmissão e Embreagem'),
('Acessórios e Equipamentos');

create table automec_desenv.grupo (
	cod_grupo int(11) not null auto_increment,
	des_grupo varchar(100) not null,
	primary key (cod_grupo),
	unique key grupo_des_grupo_uk (des_grupo)
);

-- automec_desenv.fabricante definição

select * from automec_desenv.fabricante f;

insert into automec_desenv.fabricante (des_fabricante) 
values 
	('Bosch'),
	('Magneti Marelli'),
	('Delphi Technologies'),
	('NGK'),
	('Denso'),
	('SKF'),
	('Dayco'),
	('Gates'),
	('TRW'),
	('Mann-Filter'),
	('Fras-le'),
	('Cofap'),
	('Mahle'),
	('Contitech'),
	('Valeo'),
	('Sachs'),
	('Nakata'),
	('Varga'),
	('Ferodo'),
	('Fremax'),
	('Zetec Parts'),
	('Tecfil'),
	('Hella'),
	('Metal Leve'),
	('KYB'),
	('Behr'),
	('Ajusa'),
	('Lucas'),
	('MTE-Thomson'),
	('Urba');

create table automec_desenv.fabricante (
	cod_fabricante int(11) not null auto_increment,
	des_fabricante varchar(100) not null,
	primary key (cod_fabricante),
	unique key fabricante_des_fabricante_uk (des_fabricante)
);

-- automec_desenv.cliente definição

select * from automec_desenv.cliente c where c.nom_cliente like concat("%", "Silva", "%")

select * from automec_desenv.cliente c where c.nro_telefone is null or c.nro_telefone = '';

select * from automec_desenv.cliente c order by 1 desc;

insert into automec_desenv.cliente (nom_cliente, nro_cpf, nro_celular, nro_telefone, email, obs)
values
	('Ana Beatriz Silva', '12345678901', '81 9 12345678', '81 23456789', 'ana.silva@email.com', 'Cliente frequente.'),
	('Bruno Oliveira Costa', '12345678902', '81 9 23456789', '81 34567890', 'bruno.costa@email.com', 'Solicita troca de óleo a cada 3 meses.'),
	('Carlos Henrique Souza', '12345678903', '81 9 34567890', '81 45678901', 'carlos.souza@email.com', NULL),
	('Daniela Pereira Lima', '12345678904', '81 9 45678901', '81 56789012', 'daniela.lima@email.com', 'Prefere atendimento pela manhã.'),
	('Eduardo Martins Rocha', '12345678905', '81 9 56789012', '81 67890123', 'eduardo.rocha@email.com', 'Veículo com manutenção recorrente.'),
	('Fernanda Alves Dias', '12345678906', '81 9 67890123', '81 78901234', 'fernanda.dias@email.com', NULL),
	('Gabriel Farias Pinto', '12345678907', '81 9 78901234', '81 89012345', 'gabriel.pinto@email.com', 'Cliente novo.'),
	('Helena Castro Ramos', '12345678908', '81 9 89012345', '81 90123456', 'helena.ramos@email.com', 'Indicada por outro cliente.'),
	('Igor Nascimento Melo', '12345678909', '81 9 90123456', '81 12345679', 'igor.melo@email.com', NULL),
	('Juliana Souza Pires', '12345678910', '81 9 01234567', '81 23456780', 'juliana.pires@email.com', 'Troca de pneus agendada.'),
	('Karen Almeida Costa', '12345678911', '81 9 22334455', '81 33445566', 'karen.costa@email.com', NULL),
	('Leandro Batista Silva', '12345678912', '81 9 33445566', '81 44556677', 'leandro.silva@email.com', 'Prefere agendamento online.'),
	('Mariana Rocha Lima', '12345678913', '81 9 44556677', '81 55667788', 'mariana.lima@email.com', NULL),
	('Nicolas Andrade Lopes', '12345678914', '81 9 55667788', '81 66778899', 'nicolas.lopes@email.com', 'Veículo novo em garantia.'),
	('Olívia Mendes Teixeira', '12345678915', '81 9 66778899', '81 77889900', 'olivia.teixeira@email.com', 'Gosta de explicações detalhadas.'),
	('Pedro Henrique Reis', '12345678916', '81 9 77889900', '81 88990011', 'pedro.reis@email.com', NULL),
	('Quésia Barbosa Soares', '12345678917', '81 9 88990011', '81 99001122', 'quesia.soares@email.com', NULL),
	('Rafael Moura Cardoso', '12345678918', '81 9 99001122', '81 10111213', 'rafael.cardoso@email.com', 'Cliente de longa data.'),
	('Sabrina Luz Oliveira', '12345678919', '81 9 10111213', '81 11213141', 'sabrina.oliveira@email.com', 'Trabalha próximo à oficina.'),
	('Tiago Silva Gomes', '12345678920', '81 9 11213141', '81 12131415', 'tiago.gomes@email.com', NULL),
	('Ursula Santos Lima', '12345678921', '81 9 12131415', '81 13141516', 'ursula.lima@email.com', 'Cliente VIP.'),
	('Victor Hugo Cunha', '12345678922', '81 9 13141516', '81 14151617', 'victor.cunha@email.com', NULL),
	('Wesley Fonseca Prado', '12345678923', '81 9 14151617', '81 15161718', 'wesley.prado@email.com', 'Consulta para revisão completa.'),
	('Xênia Rocha Martins', '12345678924', '81 9 15161718', '81 16171819', 'xenia.martins@email.com', NULL),
	('Yara Silva Figueiredo', '12345678925', '81 9 16171819', '81 17181920', 'yara.figueiredo@email.com', 'Agendamento mensal.'),
	('Zeca Amaral Souza', '12345678926', '81 9 17181920', '81 18192021', 'zeca.souza@email.com', NULL),
	('Adriana Vieira Lopes', '12345678927', '81 9 18192021', '81 19202122', 'adriana.lopes@email.com', 'Indicada por Victor.'),
	('Bruno César Oliveira', '12345678928', '81 9 19202122', '81 20212223', 'bruno.oliveira@email.com', NULL),
	('Camila Assis Barreto', '12345678929', '81 9 20212223', '81 21222324', 'camila.barreto@email.com', 'Revisão completa realizada em 2023.'),
	('Diego Martins Lima', '12345678930', '81 9 21222324', '81 22232425', 'diego.lima@email.com', NULL),
	('Elisa Menezes Farias', '12345678931', '81 9 22232425', '81 23242526', 'elisa.farias@email.com', NULL),
	('Fabiano Souza Rocha', '12345678932', '81 9 23242526', '81 24252627', 'fabiano.rocha@email.com', 'Solicita contato por e-mail.'),
	('Geovana Monteiro Dias', '12345678933', '81 9 24252627', '81 25262728', 'geovana.dias@email.com', NULL),
	('Henrique Batista Leal', '12345678934', '81 9 25262728', '81 26272829', 'henrique.leal@email.com', 'Não atende telefone, prefere WhatsApp.'),
	('Isabela Castro Lima', '12345678935', '81 9 26272829', '81 27282930', 'isabela.lima@email.com', NULL),
	('João Vitor Santos', '12345678936', '81 9 27282930', '81 28293031', 'joao.santos@email.com', NULL),
	('Katia Mendes Silva', '12345678937', '81 9 28293031', '81 29303132', 'katia.silva@email.com', 'Solicitou orçamento.'),
	('Lucas Almeida Torres', '12345678938', '81 9 29303132', '81 30313233', 'lucas.torres@email.com', NULL),
	('Marcos Antônio Cruz', '12345678939', '81 9 30313233', '81 31323334', 'marcos.cruz@email.com', NULL),
	('Natália Ramos Dias', '12345678940', '81 9 31323334', '81 32333435', 'natalia.dias@email.com', 'Precisa de orçamento com urgência.'),
	('Otávio Barros Melo', '12345678941', '81 9 32333435', '81 33343536', 'otavio.melo@email.com', NULL),
	('Paula Ferreira Nunes', '12345678942', '81 9 33343536', '81 34353637', 'paula.nunes@email.com', NULL),
	('Renato Luz Carvalho', '12345678943', '81 9 34353637', '81 35363738', 'renato.carvalho@email.com', 'Veículo importado.'),
	('Simone Mendes Prado', '12345678944', '81 9 35363738', '81 36373839', 'simone.prado@email.com', NULL),
	('Tatiane Rocha Silva', '12345678945', '81 9 36373839', '81 37383940', 'tatiane.silva@email.com', 'Cliente esporádica.'),
	('Ubirajara Costa Lima', '12345678946', '81 9 37383940', '81 38394041', 'ubirajara.lima@email.com', NULL),
	('Vanessa Teixeira Luz', '12345678947', '81 9 38394041', '81 39404142', 'vanessa.luz@email.com', NULL),
	('William Almeida Brito', '12345678948', '81 9 39404142', '81 40414243', 'william.brito@email.com', 'Cliente corporativo.'),
	('Xavier Torres Filho', '12345678949', '81 9 40414243', '81 41424344', 'xavier.filho@email.com', NULL),
	('Yuri Gomes Matos', '12345678950', '81 9 41424344', '81 42434445', 'yuri.matos@email.com', NULL);

create table automec_desenv.cliente (
	cod_cliente int(11) not null auto_increment primary key,
	nom_cliente varchar(100) not null,
	nro_cpf varchar(11) not null,
	nro_celular varchar(15) not null,
	nro_telefone varchar(15) null,
	email varchar(100) null,
	obs varchar(1000) null,
	unique key cliente_nro_cpf_uk (nro_cpf)
);

-- automec_desenv.mecanico definição

select * from automec_desenv.mecanico m;

insert into automec_desenv.mecanico (nom_mecanico, nro_celular)
values
('Carlos Eduardo Silva', '81 9 87652432'),
('Marcos Vinícius Oliveira', '81 9 96534785'),
('João Pedro Almeida', '81 9 87451523'),
('Ricardo Santos Lima', '81 9 91263456'),
('Bruno César Martins', '81 9 99878776'),
('Fábio Henrique Costa', '81 9 91120233'),
('Luciano Rocha Freitas', '81 9 93445566'),
('André Luiz Pires', '81 9 90019122'),
('Fernando Alves Moraes', '81 9 93433445'),
('Thiago Moreira Souza', '81 9 98488990');

create table automec_desenv.mecanico (
	cod_mecanico int(11) not null auto_increment primary key,
	nom_mecanico varchar(100) not null,
	nro_celular varchar(15) not null
);

-- automec_desenv.vw_modelos_marca definição

select v.*, m.des_modelo from automec_desenv.veiculo v
inner join automec_desenv.modelo m on m.cod_modelo = v.cod_modelo

select * from automec_desenv.veiculo v;

insert into automec_desenv.veiculo (cod_modelo, des_cor, nro_placa, ano_veiculo, des_motor, qtd_km_veiculo, qtd_km_dia)
values
(31, 'Prata', 'AAA1B23', '2020', '1.0 Flex', 44000, 50),
(32, 'Branco', 'BBB2C34', '2019', '1.6 Flex', 53000, 48),
(33, 'Preto', 'CCC3D45', '2021', '2.0 Diesel', 36000, 52),
(34, 'Azul', 'DDD4E56', '2022', '1.4 Flex', 25000, 47),
(35, 'Vermelho', 'EEE5F67', '2018', '1.0 Flex', 70000, 43),
(36, 'Cinza', 'FFF6G78', '2023', '1.6 Flex', 13000, 62),
(37, 'Preto', 'GGG7H89', '2020', '1.4 Flex', 47000, 58),
(38, 'Verde', 'HHH8I90', '2021', '2.0 Turbo', 32000, 53),
(39, 'Amarelo', 'III9J01', '2019', '1.0 Flex', 60000, 49),
(40, 'Branco', 'JJJ0K12', '2023', '1.8 Diesel', 14000, 46),
(41, 'Prata', 'KKK1L23', '2020', '1.0 Flex', 41000, 56),
(42, 'Cinza', 'LLL2M34', '2022', '1.6 Flex', 26000, 44),
(43, 'Vermelho', 'MMM3N45', '2018', '1.4 Flex', 73000, 42),
(44, 'Preto', 'NNN4O56', '2021', '1.0 Flex', 34000, 51),
(45, 'Branco', 'OOO5P67', '2019', '1.8 Diesel', 59000, 59),
(46, 'Azul', 'PPP6Q78', '2023', '1.6 Flex', 12000, 63),
(47, 'Verde', 'QQQ7R89', '2020', '2.0 Turbo', 46000, 57),
(48, 'Prata', 'RRR8S90', '2021', '1.0 Flex', 39000, 60),
(25, 'Branco', 'SSS9T01', '2020', '1.4 Flex', 48000, 54),
(26, 'Preto', 'TTT0U12', '2022', '2.0 Turbo', 21000, 45),
(27, 'Cinza', 'UUU1V23', '2023', '1.6 Flex', 17000, 58),
(28, 'Azul', 'VVV2W34', '2021', '1.4 Flex', 36000, 49),
(29, 'Vermelho', 'WWW3X45', '2019', '1.0 Flex', 62000, 50),
(30, 'Branco', 'XXX4Y56', '2020', '1.8 Diesel', 47000, 55),
(31, 'Preto', 'YYY5Z67', '2022', '1.0 Flex', 24000, 47),
(32, 'Verde', 'ZZZ6A78', '2023', '1.4 Flex', 16000, 52),
(33, 'Amarelo', 'A1B2C3D', '2021', '1.6 Flex', 33000, 46),
(34, 'Branco', 'B4C5D6E', '2019', '1.0 Flex', 64000, 40),
(35, 'Prata', 'C7D8E9F', '2020', '2.0 Turbo', 45000, 61),
(36, 'Azul', 'D0E1F2G', '2018', '1.8 Diesel', 71000, 39);

insert into automec_desenv.veiculo (cod_modelo, des_cor, nro_placa, ano_veiculo, des_motor, qtd_km_veiculo, qtd_km_dia)
values 
(25, 'Prata', 'ABC1A23', '2020', '1.0 Flex', 45000, 60),
(26, 'Preto', 'DEF2B34', '2019', '1.6 Flex', 52000, 55),
(27, 'Branco', 'GHI3C45', '2021', '1.8 Diesel', 31000, 50),
(28, 'Vermelho', 'JKL4D56', '2022', '2.0 Turbo', 22000, 45),
(29, 'Azul', 'MNO5E67', '2018', '1.0 Flex', 67000, 40),
(30, 'Cinza', 'PQR6F78', '2023', '1.6 Flex', 15000, 65),
(31, 'Verde', 'STU7G89', '2020', '1.0 Flex', 49000, 55),
(32, 'Amarelo', 'VWX8H90', '2021', '2.0 Turbo', 33000, 60),
(33, 'Branco', 'YZA9I01', '2019', '1.4 Flex', 58000, 50),
(34, 'Preto', 'BCD0J12', '2023', '1.8 Diesel', 12000, 40),
(35, 'Prata', 'EFG1K23', '2020', '1.0 Flex', 47000, 55),
(36, 'Cinza', 'HIJ2L34', '2022', '2.0 Turbo', 25000, 45),
(37, 'Vermelho', 'KLM3M45', '2018', '1.6 Flex', 69000, 40),
(38, 'Preto', 'NOP4N56', '2021', '1.4 Flex', 35000, 50),
(39, 'Branco', 'QRS5O67', '2019', '1.0 Flex', 62000, 60),
(40, 'Prata', 'TUV6P78', '2023', '1.8 Diesel', 14000, 55),
(41, 'Azul', 'WXY7Q89', '2020', '2.0 Turbo', 43000, 65),
(42, 'Verde', 'ZAB8R90', '2021', '1.0 Flex', 37000, 60),
(43, 'Preto', 'CDE9S01', '2022', '1.6 Flex', 28000, 55),
(44, 'Branco', 'FGH0T12', '2019', '1.0 Flex', 59000, 45),
(45, 'Vermelho', 'IJK1U23', '2020', '2.0 Turbo', 46000, 50),
(46, 'Cinza', 'LMN2V34', '2018', '1.4 Flex', 71000, 35),
(47, 'Preto', 'OPQ3W45', '2023', '1.8 Diesel', 13000, 60),
(48, 'Prata', 'RST4X56', '2021', '1.6 Flex', 39000, 55),
(25, 'Branco', 'UVW5Y67', '2020', '1.0 Flex', 47000, 60),
(26, 'Preto', 'XYZ6Z78', '2022', '2.0 Turbo', 22000, 45),
(27, 'Azul', 'ABC7A89', '2023', '1.4 Flex', 16000, 55),
(28, 'Cinza', 'DEF8B90', '2021', '1.6 Flex', 34000, 50),
(29, 'Prata', 'GHI9C01', '2019', '1.0 Flex', 64000, 40),
(30, 'Branco', 'JKL0D12', '2020', '1.8 Diesel', 48000, 65);

create table automec_desenv.veiculo (
  cod_veiculo int(11) not null auto_increment,
  cod_modelo  int(11) not null,
  des_cor varchar(25) null,
  nro_placa varchar(10) not null,
  ano_veiculo varchar(10) null,
  des_motor varchar(25) null,
  qtd_km_veiculo int(10) not null default 0,
  qtd_km_dia int(10) not null default 0,
  dta_cadastro date not null default current_date,
  primary key (cod_veiculo),
  unique index veiculo_nro_placa (nro_placa),
  constraint veiculo_cod_modelo_fk foreign key (cod_modelo) references modelo (cod_modelo)
);

-- automec_desenv.vw_modelos_marca definição

select vmm.cod_modelo, vmm.des_modelo
  from automec_desenv.vw_modelos_marca vmm 
 where cod_marca = 1;

select * from automec_desenv.vw_modelos_marca vmm 
 where cod_marca = 4;

create view automec_desenv.vw_modelos_marca as
	select modelo.cod_modelo, modelo.des_modelo, marca.cod_marca
	  from automec_desenv.modelo as modelo
	  inner join automec_desenv.marca as marca on marca.cod_marca = modelo.cod_marca
	  order by modelo.des_modelo;

-- select marca.cod_marca, marca.des_marca, modelo.cod_modelo, modelo.des_modelo
--   from automec_desenv.modelo as modelo
--   inner join automec_desenv.marca as marca on marca.cod_marca = modelo.cod_marca
--   order by marca.des_marca;

-- automec_desenv.modelo definição

select * from automec_desenv.modelo m order by des_modelo desc;

insert into automec_desenv.modelo (des_modelo,cod_marca) 
values
	 ('Gol G3',4),
	 ('HB20 Sensi',3),
	 ('HB20 SX',3),
	 ('Siena',1),
	 ('Toro',1),
	 ('Golf',4),
	 ('Estrada',1),
	 ('Creta',3),
	 ('Brasilia',4),
	 ('Monza',2),
	 ('Santana',4),
	 ('Uno Mille',1),
	 ('Fiesta',2),
	 ('KA',2),
	 ('Onix',5),
	 ('Corolla',6),
	 ('GR Corolla',6),
	 ('SW 4',6),
	 ('Argo',1),
	 ('Cronos',6),
	 ('Doblo',1),
	 ('Fiorino',1),
	 ('Mobi',1);

create table automec_desenv.modelo (
	cod_modelo int(11) not null auto_increment,
	des_modelo varchar(100) not null,
	cod_marca int(11) not null,
	primary key (cod_modelo),
	unique key modelo_des_modelo_uk (des_modelo),
	constraint modelo_cod_marca_fk foreign key (cod_marca) references marca(cod_marca);
);

-- automec_desenv.marca definição

select * from automec_desenv.marca m;

insert into automec_desenv.marca (des_marca)
values 
	('Fiat')
	('Hyundai'),
	('Volkswagen'),
	('Chevrolet'),
	('Honda'),
	('Nissan'),
	('Renault'),
	('Peugeot'),
	('Citroën'),
	('Jeep'),
	('Mitsubishi'),
	('Subaru'),
	('Kia'),
	('Mercedes-Benz'),
	('BMW'),
	('Audi'),
	('Volvo'),
	('Land Rover'),
	('Porsche'),
	('Chery'),
	('JAC Motors'),
	('Lifan'),
	('Geely'),
	('Bugatti'),
	('Ferrari'),
	('Lamborghini'),
	('Rolls-Royce'),
	('Bentley');

create table automec_desenv.marca (
	cod_marca int(11) not null auto_increment,
	des_marca varchar(100) not null,
	primary key (cod_marca),
	unique key marca_des_marca_uk (des_marca)
);

-- automec_desenv.recurso definição

select * from automec_desenv.recurso r;

insert into automec_desenv.recurso (des_recurso)
values 
	('Controle de acesso'),
	('Funcionalidade'),
	('Grupo de peça'),
	('Marca'),
	('Modelo'),
	('Perfil'),
	('Tabela de apoio'),
	('Usuário');

create table automec_desenv.recurso (
  cod_recurso int(11) not null auto_increment,
  des_recurso varchar(100) not null,
  situacao varchar(1) not null default 'A',
  primary key (cod_recurso),
  unique key recurso_des_recurso_uk (des_recurso),
  constraint recurso_situacao_chk check (situacao in ('A','C'))
);

-- automec_desenv.usuario definição

select * from automec_desenv.usuario u;

insert into automec_desenv.usuario (email, login, nome, senha, situacao, cod_perfil)
values 
	('mecanico.lider@email.com', 'mecanico.lider', 'MECANICO LIDER', 'mec@159', 'I', 4),
	('getulio.silva@email.br', 'getulio.silva', 'GETÚLIO SILVA', 'get@159', 'A', 1),
	('bob.sauro@email.com', 'bob.sauro', 'BOB SAURO', 'bob@159', 'A', 3);

create table automec_desenv.usuario (
  cod_usuario int(11) not null auto_increment,
  nome varchar(100) not null,
  email varchar(100) not null,
  login varchar(25) not null,
  senha varchar(25) not null,
  situacao varchar(1) not null,
  cod_perfil int(11) not null,
  primary key (cod_usuario),
  unique key usuario_login_uk (login),
  constraint usuario_cod_perfil_fk foreign key (cod_perfil) references perfil (cod_perfil),
  constraint usuario_situacao_chk check (situacao in ('A','C','I','S'))
);

-- automec_desenv.perfil definição

select * from automec_desenv.perfil p;

insert into automec_desenv.perfil (des_perfil)
values 
	('Analista'),
	('Balconista'),
	('Gerente');

create table automec_desenv.perfil (
	cod_perfil int(11) not null auto_increment,
	des_perfil varchar(100) not null,
	primary key (cod_perfil),
	unique key perfil_des_perfil_uk (des_perfil)
);
