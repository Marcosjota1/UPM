
create database mis_datos;

use mis_datos;

#Para crear una tabla
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
);

create table Gusta(
	ID_socio int not null,
    ID_cerveza int not null,
    foreign key(ID_socio) references Socio(ID),
    foreign key(ID_cerveza) references Cerveza(ID)
);

create table Gusta(
	Licencia_bar 
	ID_socio int not null,
    ID_cerveza int not null,
    foreign key(ID_socio) references Socio(ID),
    foreign key(ID_cerveza) references Cerveza(ID)
);