/*Autor: Alexsandro Façanha------------------------------------------ 24/08/2014*/
/*Banco de dados do Sistema SADA V 1.1-----------------------------------------*/
/* create database BDAnaliseAcad2; */

use BDAnaliseAcad2;

create table tbaluno(
	idalu int(8) not null primary key auto_increment, 
        nome varchar(150) not null, 
        nasc date default '1990-01-01',
        rua varchar(100),
        numero char(5),
        bairro varchar(100),
	cep char(10),
	cidade varchar(50),
	estado char(2),
	email varchar(150)
);

create table tbtelefone(
	idfone int(8) not null primary key auto_increment,
	idalu int(8) not null,
	ddd char(2) default '85',
	numero varchar(15) not null,
	tipo char(5) not null,
	operadora varchar(20),
	foreign key (idalu) references tbaluno(idalu)
);

create table tbcurso(
	idcur int(8) not null primary key auto_increment,
	especificacao varchar(150) not null,
	carga_horaria int(4) not null,
	observacao varchar(250)
);


create table tbdisciplina(
	iddisc int(8) not null primary key,
	especificacao varchar(150) not null,
	natureza varchar(100),
	objetivo varchar(150),
	carga_horaria int(4) not null,
	observacao varchar(250)
);

create table tbcurdisc(
	idcur int(8) not null,
	iddisc int(8) not null,
	foreign key (idcur) references tbcurso(idcur),
	foreign key (iddisc) references tbdisciplina(iddisc),
	primary key(idcur, iddisc)
);

create table tbmovimento(
	idmov int(8) not null primary key auto_increment,
	idcur int(8) not null,
	iddisc int(8) not null,
	idalu int(8) not null,
	nota1 float,
	nota2 float,
	nota3 float,
	nota4 float,
	nota5 float,
	nota6 float,
	falta int(3),
	turma varchar(100),
	dataAlteracao date,
	foreign key (idalu) references tbaluno(idalu),
	foreign key (idcur) references tbcurso(idcur),
	foreign key (iddisc) references tbdisciplina(iddisc)
);

/*********************************************************************************/
/*PROCEDURES DE INSERÇÃO**********************************************************/
/*********************************************************************************/

DELIMITER $$
DROP PROCEDURE IF EXISTS INSERIR_DISC2$$
CREATE PROCEDURE INSERIR_DISC2 (
	idc INT(8),
	ido INT(8),
	esp VARCHAR(150),
	nat VARCHAR(100),
	obj VARCHAR(150),
	carga INT(4),
	obs VARCHAR(250))
	main : BEGIN
		insert into tbdisciplina (iddisc,especificacao,natureza,objetivo,carga_horaria,observacao) 
		values (idd,esp,nat,obj,carga,obs);
		insert into tbcurdisc values (idc,idd);
	end$$
DELIMITER ;

DELIMITER $$
DROP PROCEDURE IF EXISTS INSERT_ALUNO12 $$
CREATE PROCEDURE INSERT_ALUNO12(
	id int(8),
	nome_alu varchar(150),
	nasc_alu date,
	rua_alu varchar(150),
	num char(5),
	bairro_alu varchar(100),
	cep_alu char(10),
	cidade_alu varchar(50),
	uf char(2),
	email_alu varchar(150),
	idfone1 int(8),
	ddd1 char(2),
	numero1 varchar(15),
	tipo1 char(5),
	oper1 varchar(20)
)
	main : begin
		insert into tbaluno (idalu,nome,nasc,rua,numero,bairro,cep,cidade,estado,email)
		values (id,nome_alu,nasc_alu,rua_alu,num,bairro_alu,cep_alu,cidade_alu,uf,email_alu);
		insert into tbtelefone (idfone,idalu,ddd,numero,tipo,operadora) values (idfone1,id,ddd1,numero1,tipo1,oper1);
	end$$
DELIMITER ;

DELIMITER $$
DROP PROCEDURE IF EXISTS INSERT_ALUNO22 $$
CREATE PROCEDURE INSERT_ALUNO22(
	id int(8),
	nome_alu varchar(150),
	nasc_alu date,
	rua_alu varchar(150),
	num char(5),
	bairro_alu varchar(100),
	cep_alu char(10),
	cidade_alu varchar(50),
	uf char(2),
	email_alu varchar(150),
	idfone1 int(8),
	ddd1 char(2),
	numero1 varchar(15),
	tipo1 char(5),
	oper1 varchar(20),
	idfone2 int(8),
	ddd2 char(2),
	numero2 varchar(15),
	tipo2 char(5),
	oper2 varchar(20)
)
	main : begin
		insert into tbaluno (idalu,nome,nasc,rua,numero,bairro,cep,cidade,estado,email)
		values (id,nome_alu,nasc_alu,rua_alu,num,bairro_alu,cep_alu,cidade_alu,uf,email_alu);
		insert into tbtelefone (idfone,idalu,ddd,numero,tipo,operadora) values (idfone1,id,ddd1,numero1,tipo1,oper1);
		insert into tbtelefone (idfone,idalu,ddd,numero,tipo,operadora) values (idfone2,id,ddd2,numero2,tipo2,oper2);
	end$$
DELIMITER ;

/*
	PROCEDURES DE ALTERAÇÃO DE DADOS
*/

delimiter $$
drop procedure if exists alterar_disc2 $$
create procedure alterar_disc2(
	idd int(8),
	idc int(8),
	esp varchar(150),
	nat varchar(100),
	obj varchar(150),
	carga int(4),
	obs varchar(150))
	main : begin
		update tbdisciplina set especificacao=esp,natureza=nat,objetivo=obj,carga_horaria=carga,observacao=obs where iddisc=idd;
		update tbcurdisc set idcur=idc where iddisc=idd;
	end$$
delimiter ;

/*********************************************************************************/
/*PROCEDURE ALTERAR 1*************************************************************/
/******************ALUNO**********************************************************/

delimiter $$
drop procedure if exists alterar_aluno2$$
create procedure alterar_aluno2(
	ida  int(8),
	nome_alu varchar(150),
	nasc_alu date,
	rua_alu varchar(100),
	num char(5),
	bairro_alu varchar(100),
	cep_alu char(10),
	cidade_alu varchar(50),
	est_alu char(2),
	email_alu varchar(150),
	idfone1 int(8),
	ddd1 char(2),
	fone1 varchar(15),
	tipo1 char(5),
	oper1 varchar(20))
	main:begin
            update tbaluno set
		nome=nome_alu,
		nasc=nasc_alu,
		rua=rua_alu,
		numero=num,
		bairro=bairro_alu,
		cep=cep_alu,
		cidade=cidade_alu,
		estado=est_alu,
		email=email_alu
		where idalu=ida;
            update tbtelefone set
		ddd=ddd1,
		numero=fone1,
		tipo=tipo1,
		operadora=oper1
            where idfone=idfone1;
	end$$
delimiter ;

/*FIM*****************************************************************************/
/*********************************************************************************/

/*********************************************************************************/
/*PROCEDURE ALTERAR 2*************************************************************/
/******************ALUNO**********************************************************/

delimiter $$
drop procedure if exists alterar_aluno22$$
create procedure alterar_aluno22(
	ida  int(8),
	nome_alu varchar(150),
	nasc_alu date,
	rua_alu varchar(100),
	num char(5),
	bairro_alu varchar(100),
	cep_alu char(10),
	cidade_alu varchar(50),
	est_alu char(2),
	email_alu varchar(150),
	idfone1 int(8),
	ddd1 char(2),
	fone1 varchar(15),
	tipo1 char(5),
	oper1 varchar(20),
	idfone2 int(8),
	ddd2 char(2),
	fone2 varchar(15),
	tipo2 char(5),
	oper2 varchar(20))
	main:begin
            update tbaluno set
		nome=nome_alu,
		nasc=nasc_alu,
		rua=rua_alu,
		numero=num,
		bairro=bairro_alu,
		cep=cep_alu,
		cidade=cidade_alu,
		estado=est_alu,
		email=email_alu
            where idalu=ida;
            update tbtelefone set
		ddd=ddd1,
		numero=fone1,
		tipo=tipo1,
		operadora=oper1
		where idfone=idfone1;
            update tbtelefone set
		ddd=ddd2,
		numero=fone2,
		tipo=tipo2,
		operadora=oper2
		where idfone=idfone2;
	end$$
delimiter ;

/*FIM*****************************************************************************/
/*********************************************************************************/

/*
	PROCEDORES DE EXCLUSÃO DE DADOS
*/

delimiter $$
drop procedure if exists excluir_disc2$$
create procedure excluir_disc2(
	idd int(8))
	main : begin
		delete from tbcurdisc where iddisc=idd;
		delete from tbmovimento where iddisc=idd;
		delete from tbdisciplina where iddisc=idd;
	end$$
delimiter ;

/*********************************************************************************/
/*PROCEDURE EXCLUIR***************************************************************/
/******************ALUNO**********************************************************/

delimiter $$
drop procedure if exists excluir_aluno2$$
create procedure excluir_aluno2(
	ida  int(8))
	main:begin
		delete from tbmovimento where idalu=ida;
		delete from tbtelefone where idalu=ida;
		delete from tbaluno where idalu=ida;
	end$$
delimiter ;

/*FIM*****************************************************************************/
/*********************************************************************************/


/*********************************************************************************/
/*PROCEDURE EXCLUIR***************************************************************/
/******************CURSO**********************************************************/

delimiter $$
drop procedure if exists excluir_curso2$$
create procedure excluir_curso2(
	idc  int(8))
	main:begin
		delete from tbcurdisc where idcur=idc;
		delete from tbmovimento where idcur=idc;
		delete from tbcurso where idcur=idc;
	end$$
delimiter ;

/*FIM*****************************************************************************/
/*********************************************************************************/
