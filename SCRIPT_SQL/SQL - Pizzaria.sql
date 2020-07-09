/* DIREITOS AUTORIAS: ADRIAN SALOMON FERREIRA ABDESALAN */

create database dbpizza;

use dbpizza;

create table codigoPais (
	id int not null auto_increment,
	codigo varchar(5) not null,
	pais varchar(50) not null,

	primary key(id)
);

create table cliente (
	id int not null auto_increment,
	idCodigo int not null,
	telefone varchar(11) not null,
	nome varchar(100) not null,
	cep int,
	logradouro varchar(100),
	numero varchar(10),
	complemento varchar(30),
	bairro varchar(50),
	cidade varchar(50),
	estado varchar(2),
	observacoes varchar(200),

	primary key(id)
);


create table categoriaProduto (
	id  int not null auto_increment,
	descricao varchar(50) not null,

	primary key (id)

);

create table produto (
	id int not null auto_increment,
	idCategoria int not null,
	nome varchar(100) not null,
	descricao varchar(250),
	quantidade int not null,
	valor int not null,
	disponivel int not null,

	primary key (id),
	foreign key (idCategoria) references categoriaProduto (id)

);

create table statusPedido (
	id int not null auto_increment,
	descricao varchar(50) not null,

	primary key(id)
);


create table pedido (
	id int not null auto_increment,
	idCliente int not null,
	dataPedido varchar(20) not null,
	idStatus int not null,
	observacao varchar(250),
	endereco varchar(250),
	taxa int not null,
	valorTotal int not null,

	primary key(id),
	foreign key (idCliente) references cliente (id),
	foreign key (idStatus) references statusPedido (id)
);

create table itemPedido (
	id int not null auto_increment,
	idPedido int not null,
	idProduto int not null,
	quantidade int not null,
	valor int not null,
	
	primary key (id),
	foreign key (idPedido) references pedido (id)
);

create table usuario (
	id int not null auto_increment,
	nome varchar(50) not null,
	email varchar(100) not null,
	login varchar(50) not null,
	senha varchar(100) not null,

	primary key(id)
);

/* NÃO REMOVER AS PROXIMAS LINHAS */ 
insert usuario values (null, "Administrador", "", "admin", "21232f297a57a5a743894a0e4a801fc3");

/* NÃO REMOVER OU EDITAR AS PROXIMAS LINHAS */
insert statusPedido values (null, "REALIZADO");
insert statusPedido values (null, "CANCELADO");
insert statusPedido values (null, "EM PRODUÇÃO");
insert statusPedido values (null, "PRONTO");
insert statusPedido values (null, "SAIU PARA ENTREGA");
insert statusPedido values (null, "ENTREGUE");

/* NÃO REMOVER OU EDITAR AS PROXIMAS LINHAS */
insert codigoPais values (null, "+55", "BRASIL");
insert codigoPais values (null, "+49", "ALEMANHA");
insert codigoPais values (null, "+966", "ARÁBIA SAUDITA");
insert codigoPais values (null, "+54", "ARGENTINA");
insert codigoPais values (null, "+61", "AUSTRÁLIA");
insert codigoPais values (null, "+591", "BOLIVIA");
insert codigoPais values (null, "+1", "CANADÁ");
insert codigoPais values (null, "+56", "CHILE");
insert codigoPais values (null, "+57", "COLÔMBIA");
