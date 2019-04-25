create database academia;
use academia;

create table cliente(id_cli int not null primary key auto_increment,
					 nome varchar(45) not null,
					 sexo varchar(10) not null,
                     data_nascimento varchar(20) not null,
                     cidade varchar(20) not null,
                     endereco varchar(45) not null,
                     telefone varchar(20) not null,
                     email varchar(30) not null);
                     

create table funcionario(id_fun int not null primary key auto_increment,
						 nome varchar(45) not null,
						 sexo varchar(10) not null,
                         data_nascimento varchar(20) not null,
                         salario float not null,
                         turno varchar(10) not null,
                         funcao varchar(20) not null,
                         telefone varchar(20) not null,
						 email varchar(30) not null);
                         
create table ficha(id_ficha int not null primary key auto_increment,
				   id_cli int not null,
                   id_fun int not null,
                   data_inicio varchar(20) not null,
                   data_termino varchar(20) not null,
                   constraint fk_ficha_cliente foreign key(id_cli) references cliente(id_cli),
                   constraint fk_ficha_funcionario foreign key(id_fun) references funcionario(id_fun));
                   
create table exercicio(id_ex int not null primary key auto_increment,
					   nome varchar(45) not null,
                       grupo_musc varchar(20) not null);

create table ficha_exercicio(id_ficha_exercicio int not null primary key auto_increment,
							 id_ficha int not null,
                             id_ex int not null,
                             series int not null,
                             peso int not null,
                             constraint fk_ficha foreign key(id_ficha) references ficha(id_ficha),
                             constraint fk_ex foreign key(id_ex) references exercicio(id_ex));
							
alter table cliente change nome nome_cli varchar(20) not null;
alter table funcionario change nome nome_fun varchar(20) not null;
alter table ficha_exercicio add repeticoes int not null;