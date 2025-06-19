-- automec_desenv.item_kit

select * from automec_desenv.item_kit ik order by ik.cod_kit, ik.cod_produto;

create table automec_desenv.item_kit (
	cod_kit int(11) not null,
	cod_produto int(11) not null,
	primary key (cod_kit, cod_produto),
	constraint item_kit_cod_produto foreign key (cod_produto) references produto (cod_produto)
);

-- automec_desenv.kit

select * from automec_desenv.kit k;

select * from automec_desenv.kit k where k.des_kit like concat("%", "Ali", "%")

insert into automec_desenv.kit (des_kit)
values
('Troca de Óleo e Filtros'),
('Revisão Periódica de 10.000 km'),
('Alinhamento e Balanceamento'),
('Revisão de Freios'),
('Higienização e Revisão do Ar-Condicionado'),
('Correia Dentada e Tensionador'),
('Iluminação e Sinalização'),
('Sistema de Partida (Bateria e Alternador)'),
('Suspensão Dianteira'),
('Revisão Completa de Motor (Preventiva)');

/* Relação de kits peças e serviços 
 * 
 * 
Kit Troca de Óleo e Filtros

	4 ou 5 litros de óleo lubrificante
	Filtro de óleo
	Filtro de ar
	Filtro de combustível (opcional)

	Serviços:
		Troca do óleo
		Substituição dos filtros
		Verificação de vazamentos e nível de fluídos

Revisão Periódica de 10.000 km

	Óleo do motor
	Filtro de óleo
	Filtro de ar
	Filtro de cabine
	Velas de ignição (se aplicável)
	
	Serviços:
	
		Troca de óleo e filtros
		Checagem de freios, suspensão e pneus
		Verificação de luzes, bateria, nível de fluídos e scan eletrônico

Alinhamento e Balanceamento

	Contrapesos
	
	Serviços:
		
		Alinhamento das rodas
		Balanceamento das rodas
		Verificação da suspensão e pneus

Revisão de Freios

	Pastilhas de freio
	Discos de freio (se necessário)
	Fluído de freio

	Serviços:
	
		Troca das pastilhas
		Usinagem ou troca dos discos
		Troca do fluido de freio
		Sangria do sistema

Higienização e Revisão do Ar-Condicionado

	Filtro de cabine
	Gás refrigerante (R134a ou R1234yf)
	Higienizador bactericida
	
	Serviços:
	
		Substituição do filtro
		Limpeza dos dutos
		Recarga de gás
		Checagem de compressor e condensador

Correia Dentada e Tensionador

	Correia dentada
	Tensionador
	Polias
	Bomba d’água (se necessário)
	
	Serviços:
	
		Remoção e substituição da correia
		Ajuste de sincronismo
		Substituição da bomba d’água (se necessário)

Iluminação e Sinalização

	Lâmpadas H7, H4, Pingo, LED, etc.
	Fusíveis (se aplicável)
	
	Serviços:
	
		Substituição das lâmpadas
		Verificação do sistema elétrico
		Ajuste dos fachos dos faróis

Sistema de Partida (Bateria e Alternador)

	Bateria
	Cabos ou terminais (se necessário)
	
	Serviços:
	
		Teste de bateria e alternador
		Substituição da bateria
		Limpeza dos terminais

Suspensão Dianteira

	Amortecedores dianteiros
	Coxins
	Batentes e kits de reparo
	Buchas
	
	Serviços:
	
		Substituição dos amortecedores
		Revisão dos coxins e buchas
		Alinhamento (pós-serviço)

Revisão Completa de Motor (Preventiva)

	Óleo e filtros
	Velas
	Correia dentada
	Jogo de juntas
	Fluídos diversos (freio, arrefecimento)
	
	Serviços:
	
		Troca de todos os itens
		Limpeza do sistema de arrefecimento
		Análise por scanner
		Ajuste de válvulas (se necessário)
*/

create table automec_desenv.kit (
	cod_kit int(11) not null auto_increment,
	des_kit varchar(100) not null,
	primary key (cod_kit),
	unique key kit_des_kit_uk (des_kit)
);

-- automec_desenv.vw_componentes_sistena

select vwcs.cod_componente, vwcs.des_componente from automec_desenv.vw_componentes_sistena vwcs where vwcs.cod_sistema = 2;

select * from automec_desenv.vw_componentes_sistena vwcs where vwcs.cod_sistema = 2;

create or replace view automec_desenv.vw_componentes_sistena as
	select componente.cod_componente, componente.des_componente, sistema.cod_sistema
	  from automec_desenv.componente as componente
	  inner join automec_desenv.sistema as sistema on sistema.cod_sistema = componente.cod_sistema
	  order by componente.des_componente;

-- automec_desenv.componente

select * from automec_desenv.componente c;

select * from automec_desenv.componente c where c.des_componente like concat("%", "ar", "%")

insert into automec_desenv.componente (des_componente, cod_sistema)
values
('Motor a combustão interna (gasolina, etanol, diesel, gás)', 1),
('Admissão e escape', 1),
('Alimentação de combustível', 1),
('Ignição', 1),
('Arrefecimento (radiador, bomba d’água)', 1),
('Lubrificação', 1),
('Embreagem (ou conversor de torque em câmbios automáticos)', 2),
('Caixa de câmbio (manual ou automática)', 2),
('Eixo cardã (veículos de tração traseira)', 2),
('Diferencial', 2),
('Semi-eixos', 2),
('Molas', 3),
('Amortecedores', 3),
('Barras estabilizadoras', 3),
('Braços e buchas de suspensão', 3),
('Freios a disco e/ou tambor', 4),
('Pinças, lonas e pastilhas', 4),
('Cilindro mestre', 4),
('ABS (antitravamento)', 4),
('Freio de estacionamento', 4),
('Caixa de direção (mecânica, hidráulica ou elétrica)', 5),
('Coluna e volante', 5),
('Braços e terminais de direção', 5),
('Bateria', 6),
('Alternador', 6),
('Chicote elétrico', 6),
('Unidades de controle eletrônico (ECUs)', 6),
('Iluminação interna e externa', 6),
('Sensores e atuadores', 6),
('Ar-condicionado', 7),
('Aquecedor', 7),
('Ventilação', 7),
('Bomba de combustível', 8),
('Injetores', 8),
('Tanque', 8),
('Filtros', 8),
('Coletor de escape', 9),
('Catalisador', 9),
('Silencioso', 9),
('Sonda lambda', 9),
('Airbags', 10),
('Cintos de segurança', 10),
('Estrutura de deformação programada', 10),
('Controles de tração e estabilidade (TCS/ESC)', 10),
('Velocímetro, conta-giros, indicadores', 11),
('Luzes-espia (check engine, ABS, etc.)', 11),
('Computador de bordo', 11),
('Rádio, multimídia, bluetooth, GPS', 12),
('Interface com celular (Android Auto, Apple CarPlay)', 12),
('Estrutura externa', 13),
('Portas, capô, para-choques', 13),
('Vidros, retrovisores', 13),
('Estofamento e painéis internos', 13),
('Rodas e pneus', 14),
('Calibragem', 14),
('Monitoramento da pressão dos pneus (TPMS)', 14);

create table automec_desenv.componente (
	cod_componente int(11) not null auto_increment,
	des_componente varchar(100) not null,
	cod_sistema int(11) not null,	
	primary key (cod_componente),
	unique key componente_des_componente_uk (des_componente),
	constraint componente_cod_sistema_fk foreign key (cod_sistema) references sistema (cod_sistema)
);

-
-- automec_desenv.categoria

select * from automec_desenv.sistema s;

select * from automec_desenv.sistema s where s.des_sistema like concat("%", "Ali", "%")

insert into automec_desenv.sistema (des_sistema)
values
('Propulsão ou Motorização'),
('Transmissão'),
('Suspensão'),
('Freios'),
('Direção'),
('Elétrico e Eletrônico'),
('Climatização'),
('Alimentação de Combustível'),
('Escape'),
('Segurança'),
('Instrumentação e Painel'),
('Entretenimento e Conectividade'),
('Carroceria e Acabamento'),
('Rodagem');

create table automec_desenv.sistema (
	cod_sistema int(11) not null auto_increment,
	des_sistema varchar(100) not null,
	primary key (cod_sistema),
	unique key sistema_des_sistema_uk (des_sistema)
);

-- automec_desenv.fornecedor

select * from automec_desenv.fornecedor f;

insert into automec_desenv.fornecedor (des_fornecedor, nro_celular, nro_telefone) 
values
('Auto Peças Brasil', '(11) 98445-6815', '(11) 2821-1194'),
('Motores Turbo Ltda', '(11) 98160-9827', NULL),
('Freios & Cia', '(11) 96669-8040', '(11) 7026-7462'),
('Suspensões São Paulo', '(11) 98859-8510', '(11) 3366-7454'),
('Baterias Unidas', '(11) 96916-5588', NULL),
('Vidros Rápidos', '(11) 97866-1762', '(11) 5582-9744'),
('Escapamentos Premium', '(11) 99819-1116', '(11) 9853-4271'),
('Radiadores Silva', '(11) 97187-9842', '(11) 3218-7159'),
('Lubrificantes Forte', '(11) 99534-1045', NULL),
('Filtros e Óleos Nacional', '(11) 99894-2338', '(11) 6211-1628');

create table automec_desenv.fornecedor (
	cod_fornecedor int(11) not null auto_increment,
	des_fornecedor varchar(100) not null,
	nro_celular varchar(15) null,
	nro_telefone varchar(15) null,
	primary key (cod_fornecedor),
	unique key fornecedor_des_fornecedor_uk (des_fornecedor)
);

-- automec_desenv.fabricante

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

-- automec_desenv.cliente

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

-- automec_desenv.mecanico

select * from automec_desenv.mecanico m where m.nom_mecanico = 'Bob Sauro da Silva Bababa';

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
	cod_mecanico int(11) not null auto_increment,
	nom_mecanico varchar(100) not null,
	nro_celular varchar(15) not null,
	primary key (cod_mecanico),
	unique key mecanico_nom_mecanico_uk (des_modelo)
);

-- automec_desenv.veiculo

select * from automec_desenv.veiculo v;

insert into automec_desenv.veiculo (cod_modelo, des_cor, nro_placa, ano_veiculo, des_motor, qtd_km_veiculo, qtd_km_dia)
values
(68, 'Branco', 'ED6-B5FB', 2015, '2.0', 42321, 64),
(68, 'Cinza', '0B7-442F', 2020, '1.6', 50443, 70),
(68, 'Azul', '60D-A5C0', 2015, '1.0', 80286, 41),
(68, 'Cinza', '530-63D6', 2020, 'Turbo 1.0', 49457, 30),
(67, 'Vermelho', 'B64-B25D', 2020, '2.0', 62689, 92),
(67, 'Vermelho', 'B3B-114A', 2022, '2.0', 67368, 91),
(67, 'Vermelho', '61F-4CD2', 2023, '2.0', 76184, 83),
(67, 'Vermelho', 'FCF-E593', 2022, 'Turbo 1.0', 41799, 99),
(66, 'Azul', 'FDA-5A80', 2020, 'Turbo 1.0', 18806, 45),
(66, 'Prata', '72C-2FE9', 2021, '2.0', 71182, 100),
(66, 'Prata', '273-53CD', 2019, '2.0', 84301, 37),
(66, 'Branco', '20A-A5CC', 2021, 'Turbo 1.0', 81396, 60),
(65, 'Preto', '284-5483', 2022, 'Turbo 1.6', 49476, 69),
(65, 'Vermelho', '8AF-AD97', 2023, 'Turbo 1.6', 43092, 71),
(65, 'Cinza', '2C4-CF98', 2021, '1.6', 23779, 97),
(65, 'Vermelho', '14F-B114', 2022, 'Turbo 1.6', 94561, 64),
(64, 'Branco', '17E-38BB', 2018, '1.3', 90925, 65),
(64, 'Prata', 'A24-35EA', 2015, '2.0', 80110, 55),
(64, 'Vermelho', '771-9505', 2023, '2.0', 24920, 91),
(64, 'Preto', 'FD9-8B45', 2016, '1.6', 37428, 56),
(63, 'Preto', 'C0E-9446', 2022, 'Turbo 1.0', 104570, 70),
(63, 'Azul', '8F6-0562', 2016, 'Turbo 1.0', 27851, 41),
(63, 'Prata', 'BE0-0895', 2020, '2.0', 74207, 95),
(63, 'Prata', '379-5938', 2018, '2.0', 110918, 92),
(62, 'Vermelho', '7F5-96A5', 2018, '1.0', 33518, 88),
(62, 'Azul', '87C-953F', 2015, 'Turbo 1.6', 39662, 36),
(62, 'Vermelho', 'B9D-198B', 2019, '1.6', 111675, 41),
(62, 'Cinza', '76D-1E0D', 2015, '1.0', 68921, 58),
(61, 'Branco', '56E-8F73', 2020, 'Turbo 1.0', 119536, 91),
(61, 'Branco', '404-A99B', 2020, 'Turbo 1.6', 42992, 70),
(61, 'Branco', '14E-0852', 2018, '1.6', 86573, 33),
(61, 'Azul', '2DA-7EF8', 2016, '1.3', 57250, 35),
(60, 'Vermelho', 'A67-7856', 2021, '2.0', 79746, 53),
(60, 'Cinza', '0CB-9EBB', 2022, '2.0', 55558, 87),
(60, 'Prata', 'B44-B78F', 2023, '1.6', 62362, 79),
(60, 'Prata', '068-D371', 2016, '1.6', 106120, 39),
(59, 'Cinza', '5D6-76F9', 2015, '1.3', 40562, 77),
(59, 'Cinza', '30D-BE81', 2015, 'Turbo 1.0', 22357, 72),
(59, 'Preto', '8C7-F420', 2016, 'Turbo 1.6', 20496, 51),
(59, 'Preto', '707-EC29', 2021, 'Turbo 1.0', 84439, 45),
(58, 'Preto', '407-303D', 2023, 'Turbo 1.6', 80552, 54),
(58, 'Prata', 'CBA-A636', 2023, '2.0', 29336, 59),
(58, 'Branco', 'CC9-4515', 2021, '1.0', 25704, 73),
(58, 'Preto', '639-F629', 2019, 'Turbo 1.6', 109545, 67),
(57, 'Azul', '1CA-F576', 2016, '1.3', 82566, 71),
(57, 'Azul', 'ACC-C6C5', 2015, '1.6', 36473, 63),
(57, 'Vermelho', 'A80-EF40', 2018, 'Turbo 1.0', 85298, 37),
(57, 'Preto', '9F4-79E9', 2017, '2.0', 51109, 99),
(56, 'Vermelho', '329-FAA7', 2016, 'Turbo 1.0', 92953, 49),
(56, 'Prata', 'B3A-87DF', 2016, 'Turbo 1.6', 81323, 86),
(56, 'Prata', '289-3642', 2023, '1.0', 36627, 48),
(56, 'Vermelho', '8A7-D0C0', 2019, '2.0', 89043, 88),
(55, 'Branco', 'DB4-622B', 2022, '1.0', 24614, 30),
(55, 'Branco', '79A-376D', 2015, '2.0', 44629, 67),
(55, 'Prata', '015-1FB8', 2015, '1.0', 63592, 81),
(55, 'Vermelho', '79A-5687', 2015, 'Turbo 1.0', 91722, 98),
(54, 'Preto', 'C9C-C028', 2018, '1.0', 72598, 58),
(54, 'Prata', 'DDC-25E1', 2023, 'Turbo 1.6', 40036, 53),
(54, 'Vermelho', '202-16DE', 2019, '1.3', 25036, 46),
(54, 'Azul', 'C9B-B0F9', 2023, '1.0', 74542, 88),
(53, 'Cinza', '072-E7BC', 2020, '1.6', 30932, 97),
(53, 'Vermelho', '470-7945', 2015, '1.0', 86745, 57),
(53, 'Cinza', '7AF-D8C8', 2016, 'Turbo 1.6', 74673, 97),
(53, 'Vermelho', 'E72-505C', 2020, '1.6', 115430, 31),
(52, 'Preto', '4A0-AF4A', 2016, '1.3', 119660, 80),
(52, 'Cinza', '3AB-10BA', 2018, '1.3', 56460, 71),
(52, 'Cinza', '173-C867', 2019, 'Turbo 1.6', 12868, 46),
(52, 'Prata', 'D63-024F', 2018, '1.6', 110053, 61),
(51, 'Branco', '19E-5B2B', 2016, '1.6', 92096, 88),
(51, 'Cinza', 'E98-BFD7', 2023, 'Turbo 1.6', 26509, 56),
(51, 'Cinza', 'F1F-395F', 2023, '1.0', 31528, 43),
(51, 'Preto', '3FC-4A03', 2023, 'Turbo 1.0', 111671, 52),
(50, 'Prata', '412-FA43', 2016, '2.0', 13054, 99),
(50, 'Azul', '77D-5BA2', 2019, 'Turbo 1.0', 87528, 37),
(50, 'Preto', '926-9214', 2016, '2.0', 64501, 72),
(50, 'Cinza', '017-44B1', 2020, '1.0', 63212, 78),
(49, 'Branco', '1CC-9DCD', 2015, 'Turbo 1.6', 48544, 77),
(49, 'Vermelho', 'B10-5644', 2019, 'Turbo 1.6', 65816, 54),
(49, 'Cinza', '300-DC72', 2015, 'Turbo 1.6', 112753, 64),
(49, 'Branco', 'B6C-B82C', 2020, '1.6', 84432, 48),
(48, 'Prata', 'A5F-B336', 2017, 'Turbo 1.6', 35422, 30),
(48, 'Azul', '0A1-3260', 2015, '1.0', 118529, 72),
(48, 'Cinza', 'D09-0E82', 2018, 'Turbo 1.6', 58270, 44),
(48, 'Prata', '758-B95F', 2015, 'Turbo 1.0', 55515, 33),
(47, 'Vermelho', '37C-009D', 2017, '2.0', 65289, 93),
(47, 'Prata', '3DA-81B9', 2019, '1.6', 85091, 93),
(47, 'Cinza', 'D89-8533', 2023, '2.0', 101520, 63),
(47, 'Preto', '0D1-5B72', 2022, 'Turbo 1.0', 30396, 72),
(46, 'Vermelho', 'D6F-6855', 2020, 'Turbo 1.6', 76690, 63),
(46, 'Prata', 'C16-9081', 2020, 'Turbo 1.0', 53923, 44),
(46, 'Prata', 'CA0-5BC1', 2022, '1.0', 58751, 58),
(46, 'Vermelho', '411-BC62', 2023, '1.6', 39169, 81),
(45, 'Preto', 'C40-0B1F', 2017, 'Turbo 1.6', 115163, 49),
(45, 'Vermelho', 'DD2-272A', 2023, 'Turbo 1.6', 43917, 94),
(45, 'Vermelho', 'E01-766C', 2022, '2.0', 86426, 100),
(45, 'Preto', 'FC2-A257', 2019, 'Turbo 1.0', 51967, 60),
(44, 'Branco', 'C2C-E929', 2015, 'Turbo 1.0', 94996, 56),
(44, 'Cinza', '33E-97F4', 2015, 'Turbo 1.6', 50000, 75),
(44, 'Azul', 'F94-0893', 2023, '1.6', 63575, 99),
(44, 'Branco', '602-6AB6', 2018, '1.6', 53231, 99),
(43, 'Vermelho', '5F6-55C8', 2016, '1.6', 77229, 84),
(43, 'Prata', 'BD4-0A36', 2018, '2.0', 92373, 94),
(43, 'Cinza', 'ABC-07FF', 2016, '1.0', 71268, 39),
(43, 'Prata', '102-A135', 2019, '1.0', 42194, 41),
(42, 'Preto', 'C26-2B6D', 2021, '1.0', 28163, 30),
(42, 'Prata', '0F4-A715', 2019, 'Turbo 1.6', 22990, 93),
(42, 'Azul', 'B5C-A683', 2022, 'Turbo 1.6', 22435, 100),
(42, 'Cinza', '9A9-3FEA', 2021, 'Turbo 1.6', 28341, 86),
(41, 'Branco', 'B40-A7B4', 2017, '1.3', 52416, 32),
(41, 'Cinza', '19E-2D4A', 2023, '1.3', 95050, 45),
(41, 'Vermelho', 'D95-B67C', 2017, '1.0', 119582, 36),
(41, 'Cinza', '2C3-624B', 2018, '2.0', 49923, 84),
(40, 'Cinza', '2C3-06D2', 2017, 'Turbo 1.6', 103956, 66),
(40, 'Preto', '69F-005F', 2020, '1.0', 14993, 79),
(40, 'Cinza', '199-4790', 2023, '1.0', 79936, 88),
(40, 'Preto', 'F9A-AC18', 2016, '1.3', 24909, 68),
(39, 'Branco', '4DE-0760', 2018, '2.0', 100494, 62),
(39, 'Preto', 'D34-16B2', 2023, 'Turbo 1.6', 54087, 78),
(39, 'Azul', '186-DC68', 2022, '1.3', 102474, 54),
(39, 'Branco', '88E-DD03', 2017, 'Turbo 1.6', 119971, 86),
(38, 'Vermelho', '3C8-FAA8', 2018, '1.6', 22318, 87),
(38, 'Cinza', 'F9A-4CB0', 2019, 'Turbo 1.6', 104012, 60),
(38, 'Vermelho', '0D0-518D', 2023, '1.3', 87513, 67),
(38, 'Cinza', 'CDD-5154', 2019, '1.6', 103758, 37),
(37, 'Preto', 'CE0-9A91', 2017, '1.0', 111374, 76),
(37, 'Branco', '842-1B58', 2021, '1.0', 57336, 63),
(37, 'Cinza', '94D-48B6', 2017, 'Turbo 1.0', 111193, 33),
(37, 'Branco', 'E50-15D8', 2020, '1.3', 100279, 33),
(36, 'Cinza', 'A7F-4167', 2023, '1.6', 43460, 52),
(36, 'Azul', '76C-7F81', 2017, '2.0', 119889, 40),
(36, 'Vermelho', '214-BE14', 2023, '1.0', 34440, 91),
(36, 'Preto', '17F-3D9A', 2015, '1.0', 100130, 96),
(35, 'Cinza', 'C9E-F5EC', 2019, 'Turbo 1.6', 38687, 37),
(35, 'Prata', '833-444F', 2020, '1.0', 68316, 67),
(35, 'Preto', 'FD4-6DAA', 2020, 'Turbo 1.6', 10842, 87),
(35, 'Branco', 'BCD-522B', 2021, '1.3', 112533, 60),
(34, 'Cinza', '34F-1A43', 2015, 'Turbo 1.0', 62941, 51),
(34, 'Cinza', '06D-BD69', 2019, '1.0', 26212, 61),
(34, 'Azul', 'AB0-CF97', 2021, 'Turbo 1.0', 62136, 40),
(34, 'Azul', '899-FA6E', 2020, '2.0', 54353, 56),
(33, 'Branco', 'B93-65A8', 2018, '1.6', 85138, 62),
(33, 'Branco', '9EA-4FD2', 2020, 'Turbo 1.0', 67579, 48),
(33, 'Prata', 'BB8-7988', 2018, '2.0', 90074, 93),
(33, 'Prata', 'AF8-0160', 2021, '1.0', 118628, 49),
(32, 'Preto', '07D-DDDA', 2017, '1.0', 115392, 31),
(32, 'Branco', '385-D692', 2023, '1.6', 26190, 82),
(32, 'Cinza', '931-3071', 2016, 'Turbo 1.0', 101691, 71),
(32, 'Azul', 'BDF-A187', 2018, 'Turbo 1.6', 35668, 96),
(31, 'Branco', 'D74-6DFE', 2019, '2.0', 110004, 100),
(31, 'Prata', 'B49-480B', 2016, '1.3', 107949, 80),
(31, 'Cinza', 'D9D-B178', 2015, '1.0', 43627, 30),
(31, 'Azul', '318-6FD8', 2022, '1.3', 62016, 85),
(30, 'Preto', '509-4095', 2018, 'Turbo 1.0', 33396, 65),
(30, 'Vermelho', 'DE9-270B', 2020, '1.3', 105784, 47),
(30, 'Vermelho', 'EE5-4CE2', 2016, '1.0', 101869, 94),
(30, 'Cinza', 'B4E-9CBA', 2015, '2.0', 23919, 55),
(29, 'Prata', '1FD-CD55', 2015, 'Turbo 1.0', 103551, 47),
(29, 'Cinza', '05D-EEA2', 2017, 'Turbo 1.0', 33502, 100),
(29, 'Cinza', '8D1-961C', 2022, '1.0', 47564, 53),
(29, 'Vermelho', '7D6-C8FD', 2019, '1.3', 14409, 88),
(28, 'Vermelho', 'F84-0D97', 2022, '1.6', 29248, 64),
(28, 'Azul', '9C8-58B8', 2023, '1.3', 47704, 96),
(28, 'Azul', '94C-D081', 2015, '1.3', 11312, 48),
(28, 'Preto', '701-3E57', 2019, '2.0', 107582, 48),
(27, 'Prata', '8E3-11B8', 2018, 'Turbo 1.6', 89838, 69),
(27, 'Cinza', 'F65-CD7A', 2016, '1.3', 73501, 75),
(27, 'Branco', 'E4C-E81E', 2023, 'Turbo 1.6', 79444, 44),
(27, 'Preto', 'CA4-35BA', 2019, '1.3', 24887, 83),
(26, 'Cinza', 'EAC-4A9D', 2015, '1.0', 93188, 93),
(26, 'Azul', 'CC6-307E', 2020, 'Turbo 1.0', 29182, 31),
(26, 'Vermelho', 'C1A-FB0E', 2023, '1.0', 76081, 30),
(26, 'Prata', '722-E477', 2016, '1.3', 99810, 83),
(25, 'Branco', '0BD-FE3D', 2016, 'Turbo 1.0', 51028, 94),
(25, 'Vermelho', '9E6-E1AF', 2022, '2.0', 82796, 73),
(25, 'Azul', '154-007D', 2023, 'Turbo 1.0', 101861, 58),
(25, 'Cinza', 'EDF-9ECE', 2019, '1.3', 54721, 36),
(24, 'Cinza', '407-BA0E', 2021, 'Turbo 1.0', 16606, 55),
(24, 'Cinza', '837-7FCA', 2021, '1.0', 52406, 44),
(24, 'Preto', '097-A59E', 2018, '2.0', 96884, 49),
(24, 'Vermelho', '9CF-A24E', 2023, '1.0', 112427, 73),
(23, 'Vermelho', '73D-529E', 2019, 'Turbo 1.0', 74687, 90),
(23, 'Vermelho', '4E2-151C', 2023, '1.0', 67535, 76),
(23, 'Vermelho', 'A6C-0969', 2020, 'Turbo 1.6', 32011, 78),
(23, 'Branco', '311-151F', 2018, '1.3', 42337, 77),
(22, 'Cinza', '4DD-1902', 2021, 'Turbo 1.6', 35242, 63),
(22, 'Cinza', 'F7A-087A', 2020, 'Turbo 1.6', 79882, 88),
(22, 'Prata', 'FE4-A8BA', 2015, '1.6', 60089, 56),
(22, 'Prata', '30D-5D1E', 2017, 'Turbo 1.0', 17775, 63),
(21, 'Vermelho', 'A13-B914', 2022, '1.3', 96962, 69),
(21, 'Azul', '79B-2175', 2021, 'Turbo 1.6', 87703, 50),
(21, 'Cinza', '962-84AB', 2016, '1.6', 92754, 56),
(21, 'Branco', 'D56-C641', 2019, '1.0', 99395, 49),
(20, 'Branco', '67C-456A', 2017, '1.6', 53523, 79),
(20, 'Prata', '90A-9AB2', 2015, '1.6', 57928, 73),
(20, 'Preto', '961-7BE0', 2019, '1.0', 29785, 74),
(20, 'Prata', '466-C7AD', 2018, '1.3', 103509, 91),
(19, 'Azul', '6DA-A703', 2018, '1.0', 32567, 70),
(19, 'Branco', '506-2B1C', 2016, 'Turbo 1.6', 101073, 70),
(19, 'Azul', 'E44-AE13', 2021, '1.0', 46129, 65),
(19, 'Prata', 'BD9-3D7B', 2017, '1.6', 71895, 70),
(18, 'Azul', '257-AE96', 2017, '2.0', 70846, 60),
(18, 'Branco', '9B9-8EA1', 2021, 'Turbo 1.0', 90975, 73),
(18, 'Azul', '15B-EC8C', 2018, 'Turbo 1.0', 54362, 64),
(18, 'Azul', '4EA-96AB', 2023, '1.6', 103316, 91),
(17, 'Preto', '409-2A0F', 2018, 'Turbo 1.0', 71723, 72),
(17, 'Azul', '6FD-A04A', 2016, '2.0', 14479, 77),
(17, 'Azul', '089-D3FB', 2022, '2.0', 64355, 91),
(17, 'Branco', '20A-7CFC', 2017, '1.6', 65590, 71),
(16, 'Azul', 'B33-09A9', 2023, 'Turbo 1.0', 48072, 97),
(16, 'Preto', '1B1-276E', 2021, '1.6', 31220, 87),
(16, 'Azul', '418-B5D6', 2020, '1.6', 55358, 42),
(16, 'Cinza', '238-F382', 2016, '1.0', 110786, 46),
(15, 'Vermelho', '999-8789', 2018, 'Turbo 1.0', 75694, 49),
(15, 'Prata', 'A7D-EB71', 2023, '1.3', 21739, 88),
(15, 'Preto', '3A6-D69D', 2021, '2.0', 29266, 76),
(15, 'Branco', '4A6-858C', 2022, '1.3', 26232, 98),
(14, 'Preto', '324-BF53', 2019, 'Turbo 1.0', 21437, 68),
(14, 'Vermelho', '3EF-74E4', 2021, 'Turbo 1.0', 112478, 84),
(14, 'Azul', 'ED4-F55E', 2020, 'Turbo 1.0', 29517, 47),
(14, 'Azul', '2A5-7993', 2017, '1.6', 108850, 43),
(13, 'Cinza', '9D9-E08E', 2021, '1.3', 105030, 83),
(13, 'Preto', '575-0E2F', 2021, '1.0', 100156, 70),
(13, 'Prata', '0DA-2A38', 2016, '1.3', 21434, 64),
(13, 'Prata', '653-5CF4', 2018, '1.0', 116910, 81),
(12, 'Azul', '49B-38B1', 2019, 'Turbo 1.6', 65314, 90),
(12, 'Azul', '7EF-BEE3', 2021, '2.0', 23450, 86),
(12, 'Branco', '709-9606', 2016, '2.0', 44362, 78),
(12, 'Preto', 'B96-ADB0', 2017, 'Turbo 1.6', 32922, 60),
(11, 'Preto', '5BB-B7CB', 2018, '2.0', 105492, 32),
(11, 'Prata', '377-2066', 2022, 'Turbo 1.6', 91082, 36),
(11, 'Prata', '8E8-A248', 2023, '2.0', 19235, 56),
(11, 'Azul', '5FE-C594', 2019, '1.3', 94554, 38),
(10, 'Preto', '981-61E4', 2016, '1.6', 43113, 81),
(10, 'Azul', 'EEC-35FD', 2018, 'Turbo 1.0', 70207, 82),
(10, 'Cinza', '357-8DB7', 2016, '1.6', 98806, 73),
(10, 'Cinza', 'B7B-7B48', 2020, '1.0', 85542, 95),
(9, 'Prata', 'F5F-E4B8', 2018, 'Turbo 1.6', 36975, 79),
(9, 'Cinza', '750-DB58', 2019, '2.0', 22041, 65),
(9, 'Preto', 'DAD-735C', 2022, 'Turbo 1.6', 33143, 85),
(9, 'Prata', 'D8A-BCE3', 2020, 'Turbo 1.6', 27185, 75),
(8, 'Vermelho', '942-06E2', 2015, '1.6', 93895, 36),
(8, 'Prata', '64D-9A29', 2018, 'Turbo 1.0', 84913, 54),
(8, 'Prata', '590-D584', 2023, '1.3', 67982, 72),
(8, 'Preto', '9AE-30F1', 2016, '1.0', 10640, 61),
(7, 'Cinza', '308-5E65', 2017, '2.0', 76685, 94),
(7, 'Cinza', '786-1BBD', 2022, '1.6', 92385, 51),
(7, 'Branco', '9CC-45A7', 2017, '1.0', 62138, 83),
(7, 'Cinza', 'B74-8C52', 2019, '1.6', 37626, 99),
(6, 'Cinza', '66A-34EC', 2023, '1.3', 46239, 69),
(6, 'Branco', '6D2-3E44', 2016, 'Turbo 1.0', 108397, 51),
(6, 'Azul', 'C1B-82D5', 2016, '1.3', 82132, 61),
(6, 'Cinza', '410-C780', 2016, 'Turbo 1.6', 58394, 79),
(5, 'Branco', 'A53-B79B', 2016, 'Turbo 1.0', 39658, 48),
(5, 'Azul', '3FA-7425', 2015, '1.0', 53579, 92),
(5, 'Prata', 'FDB-2325', 2020, '1.0', 24128, 42),
(5, 'Vermelho', 'E63-A64B', 2017, 'Turbo 1.6', 97486, 75),
(4, 'Branco', '734-0860', 2016, '1.3', 62715, 40),
(4, 'Cinza', '1EA-BD20', 2021, '1.3', 72589, 66),
(4, 'Branco', '20E-8CCE', 2021, 'Turbo 1.6', 94193, 60),
(4, 'Preto', '410-BF21', 2019, '1.0', 76902, 66),
(3, 'Branco', 'C2B-82EA', 2023, '1.6', 29210, 56),
(3, 'Prata', 'D23-CFF5', 2020, '2.0', 116770, 88),
(3, 'Branco', '76B-F88C', 2015, '2.0', 19097, 82),
(3, 'Prata', 'B16-B79B', 2016, '2.0', 115372, 78),
(2, 'Prata', '48C-550D', 2016, '1.6', 108460, 88),
(2, 'Preto', 'C8C-ACE9', 2020, '1.6', 107062, 39),
(2, 'Azul', 'A82-6D0D', 2021, '1.6', 26485, 99),
(2, 'Azul', 'B8D-D4F3', 2022, 'Turbo 1.6', 89641, 54),
(1, 'Vermelho', 'A1A-7C82', 2016, '1.0', 83964, 78),
(1, 'Vermelho', 'A38-8121', 2016, '1.6', 12354, 94),
(1, 'Preto', '345-FAD6', 2015, '1.6', 114985, 52),
(1, 'Azul', 'AD6-4E40', 2019, 'Turbo 1.0', 16796, 56);

create table automec_desenv.veiculo (
  cod_veiculo int(11) not null auto_increment,
  cod_modelo  int(11) not null,
  des_cor varchar(25) null,
  nro_placa varchar(10) not null,
  ano_veiculo varchar(10) null,
  des_motor varchar(25) null,
  qtd_km_veiculo int(10) not null default 0,
  qtd_km_dia int(10) not null default 0,
  dta_cadastro date not null default curdate(),
  primary key (cod_veiculo),
  unique index veiculo_nro_placa_idx (nro_placa),
  constraint veiculo_cod_modelo_fk foreign key (cod_modelo) references modelo (cod_modelo)
);

-- automec_desenv.vw_modelos_marca

select vmm.cod_modelo, vmm.des_modelo from automec_desenv.vw_modelos_marca vmm where cod_marca = 1;

select * from automec_desenv.vw_modelos_marca vmm where cod_marca = 1;

create or replace view automec_desenv.vw_modelos_marca as
	select modelo.cod_modelo, modelo.des_modelo, marca.cod_marca
	  from automec_desenv.modelo as modelo
	  inner join automec_desenv.marca as marca on marca.cod_marca = modelo.cod_marca
	  order by modelo.des_modelo;

-- select marca.cod_marca, marca.des_marca, modelo.cod_modelo, modelo.des_modelo
--   from automec_desenv.modelo as modelo
--   inner join automec_desenv.marca as marca on marca.cod_marca = modelo.cod_marca
--   order by marca.des_marca;

-- automec_desenv.modelo

select * from automec_desenv.modelo m order by des_modelo desc;

insert into modelo (des_modelo, cod_marca) 
values 
-- Fiat
('Argo', 1),
('Cronos', 1),
('Pulse', 1),
('Toro', 1),
-- Hyundai
('HB20', 2),
('Creta', 2),
('Tucson', 2),
('Santa Fe', 2),
-- Volkswagen
('Polo', 3),
('T-Cross', 3),
('Nivus', 3),
('Virtus', 3),
-- Chevrolet
('Onix', 4),
('Tracker', 4),
('S10', 4),
('Spin', 4),
-- Honda
('Civic', 5),
('City', 5),
('HR-V', 5),
('Fit', 5),
-- Nissan
('Kicks', 6),
('Versa', 6),
('Sentra', 6),
('Frontier', 6),
-- Renault
('Kwid', 7),
('Duster', 7),
('Captur', 7),
('Sandero', 7),
-- Peugeot
('208', 8),
('2008', 8),
('3008', 8),
('5008', 8),
-- Citroën
('C3', 9),
('C4 Cactus', 9),
('Aircross', 9),
('C5 Aircross', 9),
-- Jeep
('Renegade', 10),
('Compass', 10),
('Commander', 10),
('Wrangler', 10),
-- Mitsubishi
('L200', 11),
('Outlander', 11),
('ASX', 11),
('Eclipse Cross', 11),
-- Kia
('Sportage', 12),
('Seltos', 12),
('Cerato', 12),
('Stonic', 12),
-- Mercedes-Benz
('Classe A', 13),
('Classe C', 13),
('GLA', 13),
('GLC', 13),
-- BMW
('320i', 14),
('X1', 14),
('X3', 14),
('X5', 14),
-- Audi
('A3', 15),
('A4', 15),
('Q3', 15),
('Q5', 15),
-- Chery
('Tiggo 3x', 16),
('Tiggo 5x', 16),
('Tiggo 7', 16),
('Tiggo 8', 16),
-- JAC Motors
('T40', 17),
('T50', 17),
('T60', 17),
('e-JS1', 17);

create table automec_desenv.modelo (
	cod_modelo int(11) not null auto_increment,
	des_modelo varchar(100) not null,
	cod_marca int(11) not null,
	primary key (cod_modelo),
	unique key modelo_des_modelo_uk (des_modelo),
	constraint modelo_cod_marca_fk foreign key (cod_marca) references marca(cod_marca);
);

-- automec_desenv.marca

select * from automec_desenv.marca m;

insert into automec_desenv.marca (des_marca)
values 
	('Fiat'),
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
	('Kia'),
	('Mercedes-Benz'),
	('BMW'),
	('Audi'),
	('Chery'),
	('JAC Motors');

create table automec_desenv.marca (
	cod_marca int(11) not null auto_increment,
	des_marca varchar(100) not null,
	primary key (cod_marca),
	unique key marca_des_marca_uk (des_marca)
);

-- automec_desenv.recurso

select * from automec_desenv.recurso r;

insert into automec_desenv.recurso (des_recurso)
values
	('Controle de acesso'),
	('Perfil'),
	('Usuário'),
	('Recurso'),
	('Tabela de apoio'),
	('Marca e Modelo'),
	('Grupo de peça e serviço'),
    ('Subgrupo de peça e serviço'),
    ('Cadastro'),
    ('Produto e serviço');

create table automec_desenv.recurso (
  cod_recurso int(11) not null auto_increment,
  des_recurso varchar(100) not null,
  situacao varchar(1) not null default 'A',
  primary key (cod_recurso),
  unique key recurso_des_recurso_uk (des_recurso),
  constraint recurso_situacao_chk check (situacao in ('A','C'))
);

-- automec_desenv.usuario

select * from automec_desenv.usuario u;

insert into automec_desenv.usuario (email, login, nome, senha, situacao, cod_perfil)
values 
    ('getulio.silva@email.br', 'getulio.silva', 'GETÚLIO SILVA', 'get@159', 'A', 1),
    ('gerente.automec@email.br', 'viialdo.mosquito', 'VIVIALDO MOSQUITO', 'mosquito', 'A', 2),
	('balconista.automec@email.com', 'florencio.maracuja', 'FLORENCIO MARACUJÁ', 'maracuja', 'A', 3),	
	('mecanico.automec@email.com', 'mecanico.automec', 'JOÃO DA ARUELA', 'aruela', 'I', 4),	
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
  constraint usuario_situacao_chk check (situacao in ('A','I'))
);

-- automec_desenv.perfil

select * from automec_desenv.perfil p;

insert into automec_desenv.perfil (des_perfil)
values 
	('Analista'),
	('Gerente'),
	('Balconista'),
    ('Mecanico');

create table automec_desenv.perfil (
	cod_perfil int(11) not null auto_increment,
	des_perfil varchar(100) not null,
	primary key (cod_perfil),
	unique key perfil_des_perfil_uk (des_perfil)
);
