%1)Construir grafo conectividad C(Matriz relaciones grafo)¿Hay alguna pagina sin links de salida?
%calcular Nj=sum(C)¿Cual es el significado de Nj(6)?
clear

C=[0 1 0 0 1 0;     %Paginas a las que apunta P1
    1 0 1 0 1 0;
    0 0 0 1 0 0;
    0 1 0 0 1 1;
    0 0 0 0 0 0;
    0 0 1 0 0 0]';%Pongo ' por que en wuolah hacen al reves que yo, mirar apuntes
                  %' necesario al estar mirando los links totales de salida
                  %del nodo, por lo que seria paginas que apuntan a P1
                  %Por ejemplo usado para pageRank Google
Nj=sum(C,1)
%Nj(6) seria igual a 1, al tener P6 solo 1 link de salida

%Tambien podrias C=zeros(6) -> C(1,2)=1 C(1,5)=1 ...

%2) Calcular matriz transición A del grafo(matriz pageRANK). Calcular nj=sum(A) y el vector dj. ¿Cuál es el significado de nj(5)?
A = zeros(6);
for j = 1:6
    if Nj(j) > 0
        A(:, j) = C(:, j) / Nj(j);  % Dividimos cada 1 por Nj de la columna
    %else
    %    A(:, j) = 0;  % Si no hay links de salida, se maneja con ceros.
    end
end
nj=sum(A);
%nj(5)=0 significa que la página 5 no tiene links de salida.
%OTRA OPCION
A2=C./Nj

%3. Calcular la matriz de transición modificada S.
%Por ejemplo hacemos que la matriz A en la columna 5 tenga link de salida a
%todas las paginas
S=A;
S(:,5)=1/6;
S(5,4)%= 0.333, esto significa que hay un 0.33 posibilidades de ir desde la pagina 4 a la 5
S(4,6)%La probabilidad de tener link de salida en 6 hacia 4 es cero

%Sea r=(sumatorio ri, sumas toda la columna de S menos la prob de la r que estes viedno)
%r1=r2/3 + r5/6
%r2=r1/2 + r5/6
%r3=r2/3 + r5/6
%r4=r3 + r5/6                       ------> Orden de mayor a menor r4>r5>r3>r6
%r5=r1/2+ r2/3+ r4/3 + r5/6
%r6=r4/3 + r5/6




