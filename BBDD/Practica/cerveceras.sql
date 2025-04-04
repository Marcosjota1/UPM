
create database if not exists cerveceras;

use cerveceras;

create table Socio(
	ID int unique not null,
    nombre VARCHAR(100) NOT NULL,
    direccion VARCHAR(100) NOT NULL,
    telefono VARCHAR(100) NOT NULL,
    primary key (ID)
);

create table Bar(
	nombre VARCHAR(100) NOT NULL,
    licencia VARCHAR(100) NOT NULL,
    direccion VARCHAR(100) NOT NULL,
    primary key (licencia)
);

Create table Cerveza(
	ID int unique not null,
    nombre VARCHAR(100) not null,
    caracteristicas VARCHAR(255),
    ID_fabricante int not null,
    primary key (ID),
    foreign key(ID_fabricante) references Fabricante(ID)
);

Create table Fabricante(
	ID int unique not null,
    nombre VARCHAR(100) NOT NULL,
    telefono VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
	primary key (ID)
    ON DELETE SET NULL ON UPDATE CASCADE 
);

create table Gusta(
	ID_socio int not null,
    ID_cerveza int not null,
    primary key (ID_socio,ID_cerveza),
    foreign key(ID_socio) references Socio(ID),
    foreign key(ID_cerveza) references Cerveza(ID)
);

create table Vende(
	licencia_bar int not null,
    ID_cerveza int not null,
    precio int not null,
    primary key (licencia_bar,ID_cerveza),
    foreign key(licencia_bar) references Bar(ID),
    foreign key(ID_cerveza) references Cerveza(ID)
);