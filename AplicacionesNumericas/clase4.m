%MODELO DE MASSEY
%   ri - rj = yy

%EJEMPLO DIAPOSITIVAS
%PRIMERA OPCION(Apartado 1)
% Hallar Xr=y -> X 1 en positivo equipo gano y negativo quien perdio, y = dif goles
%   r=X\y    

%SEGUNDA OPCION (Apartado 2)
%Ec.Normales -> X'Xr=X'y
% X = m*m => nºequipos x nºequipos
%1º Construir M = X'X (Grafo dominancia equipos con margen victoria)
%   Matriz con pesos, indica hacia el que tiene menos puntos y la diferencia
%Una vez hecho grafo hacemos matriz M:
%Si i=j numero partidos( nº flechas grafos)
%Si i!=j si no han jugado entre equipos 0 y si han jugado el nº partidos jugados en negativo
%Termino independiente b = diferencial puntos totales de cada equipo(suma flechas grafo)
%En M fila unos al final y b fila ceros al final(casi siempre para ajustar rangos)
%r=M\b

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%%%%%%%%%%%%%%%%%%% EJERCICIO 1 %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
clear
%1) Modelo Massey)
X=[1 0 0 -1 0 0;
    0 1 0 0 -1 0;
    0 1 0 0 0 -1;
    -1 0 1 0 0 0;
    0 0 1 -1 0 0];

y=[3;21;5;13;13];
r=X\y;

%2)
clear
M=[3 0 -1 -2 0 0;
    0 3 0 0 -1 -2;
    -1 0 2 -1 0 0;
    -2 0 -1 4 -1 0;
    0 -1 0 -1 4 -2;
    0 -2 0 0 -2 4; 
    1 1 1 1 1 1];

b=[-35 5 26 -6 -6 16 0]';
r2=M\b;








