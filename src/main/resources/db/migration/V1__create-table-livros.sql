create table livros(
	id bigint not null auto_increment,
	titulo varchar(100) not null,
	data_lancamento date not null,
	paginas int not null,
	primary key (id)
);