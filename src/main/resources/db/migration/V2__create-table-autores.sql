create table autores(
	id bigint not null auto_increment,
	nome varchar(100) not null,
	email varchar(100) not null,
	data_nascimento date not null,
	mini_cv varchar(255) not null,
	primary key (id)
);