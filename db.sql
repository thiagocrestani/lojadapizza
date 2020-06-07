
create table estabelecimento (
id serial primary key,
nome varchar(300),
nomeurl varchar(30) unique,
categoria varchar(300),
tempoentrega varchar(100),
taxadeentrega real,
foto varchar(500),
email varchar(100),
ddd integer,
telefone varchar(15),
rua varchar(100) not null,
numero integer not null,
bairro varchar(100),
complemento varchar(100),
pontodereferencia varchar(300),
cep varchar(50) not null,
cidade varchar(100),
estado varchar(100),
ufestado varchar(2),
pais varchar(50) default 'Brasil',
latitude varchar(80) not null,
longitude varchar(80) not null,
excluir boolean default false not null,
ativo boolean default true not null,
retirada boolean default true not null,
entrega boolean default true not null,
temporetirada varchar(100)
);

create table usuarioestabelecimento(
id serial primary key,
usuario varchar(80) unique,
senha varchar(80),
idestabelecimento integer references estabelecimento(id),
tentativas integer default 0,
excluir boolean default false
);

create table categoriaproduto(
id serial primary key,
nome varchar(200),
excluir boolean default false
);

create table tamanho(
id serial primary key,
idestabelecimento integer references estabelecimento(id),
idcategoriaproduto integer references categoriaproduto(id),
nome varchar(200),
preco real,
quantidadesabores integer default 1,
excluir boolean default false
);

create table borda(
id serial primary key,
idestabelecimento integer references estabelecimento(id),
idcategoriaproduto integer references categoriaproduto(id),
nome varchar(200),
preco real,
excluir boolean default false
);

create table produto(
id serial primary key,
nome varchar(200),
descricao text,
preco real,
categoria int references categoriaproduto(id),
datacadastro varchar(50),
foto varchar(400),
ativo boolean default true,
idestabelecimento int references estabelecimento(id),
excluir boolean default false
);


create table cliente (
id serial primary key,
nome varchar(300) not null,
ddd integer,
telefone integer,
email varchar(50) not null unique,
senha varchar(300),
excluir boolean default false
);

create table endereco (
id serial primary key,
rua varchar(100) not null,
numero integer not null,
bairro varchar(100),
complemento varchar(100),
pontodereferencia varchar(300),
cep varchar(50) not null,
cidade varchar(100),
estado varchar(100),
pais varchar(50) default 'Brasil',
idcliente int references cliente(id),
excluir boolean default false
);

create table formapagamento(
id serial primary key,
nome varchar(300),
icone varchar(300),
excluir boolean default false
);

create table formapagamentoestabelecimento(
idestabelecimento integer references estabelecimento(id),
idformapagamento integer references formapagamento(id),
excluir boolean default false,
PRIMARY KEY (idestabelecimento, idformapagamento)
);


create table horariofuncionamento(
id serial primary key,
adomingo varchar(20),
fdomingo varchar(20),
domingo boolean default true,
asegunda varchar(20),
fsegunda varchar(20),
segunda boolean default true,
aterca varchar(20),
fterca varchar(20),
terca boolean default true,
aquarta varchar(20),
fquarta varchar(20),
quarta boolean default true,
aquinta varchar(20),
fquinta varchar(20),
quinta boolean default true,
asexta varchar(20),
fsexta varchar(20),
sexta boolean default true,
asabado varchar(20),
fsabado varchar(20),
sabado boolean default true,
idestabelecimento integer,
excluir boolean default false
);


create table pedido(
id varchar(35) primary key,
idcliente integer references cliente(id),
idendereco integer references endereco(id),
idestabelecimento integer references estabelecimento(id),
idformapagamento integer references formapagamento(id),
itens real,
valor real,
tempoentrega varchar(100),
taxadeentrega varchar(100),
datapedido varchar(100),
datapedidoformatada varchar(100),
nome varchar(300),
ddd integer,
telefone integer,
email varchar(50) not null,
aberto boolean default true,
excluir boolean default false

);


create table produtopedido(
numero integer,
idpedido varchar(35) references pedido(id),
idproduto integer references produto(id),
nome varchar(300),
preco real,
tamanho varchar(100),
saborborda varchar(100) default 'Sem borda',
sabores integer,
excluir boolean default false,
idcategoria integer references categoriaproduto(id),
categoria varchar(50),
PRIMARY KEY (idpedido, numero)
);

create table locais(
id serial primary key,
cep varchar(50) not null,
cidade varchar(100),
estado varchar(100),
ufestado varchar(2),
pais varchar(50) default 'Brasil',
excluir boolean default false
);

create table bairros(
id serial primary key,
nome varchar(200),
idlocais integer references locais(id),
excluir boolean default false
);

create table taxaentrega(
idestabelecimento integer references estabelecimento(id) not null,
distancia real not null,
preco real not null,
excluir boolean default false,
PRIMARY KEY (idestabelecimento, distancia)
);

create table masteradmin(
id integer primary key,
usuario varchar(30) not null,
senha varchar(30) not null
);



insert into estabelecimento values(default, 'PIZZARIA EXEMPLO','pizzariaexemplo','Pizzaria','45','8', 'http://extras.lojadapizza.com/logos/logo1.jpg','lojadapizza@gmail.com','49','3566-0000','Rua padrao','0','padrao','não','não','89560-000','Videira','Santa Catarina','SC',default,'-27.003289', '-51.153196',default,default,true,true,'20');
insert into horariofuncionamento values(default,'0800','2000',default,'0800','2000',default,'0800','2000',default,'0800','2000',default,'0800','2000',default,'0800','2000',default,'0800','2000',default,1,default);
insert into usuarioestabelecimento values(default, 'user', 'user',1,default,default);