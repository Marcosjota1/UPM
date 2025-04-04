%ALGORITMO ordenación paginas web
%xi = peso Pi como autoridad = sumatorio Pj que apuntan a Pi

% Algoritmo de hits de páginas webs
% 1 si Pi->Pj
% 0 en otro caso
%GRAFO = P2 -> P1 -> P3
% L= 0 0 1  -> paginas a las que apunta p1
%    1 0 0  -> paginas a las que apunta p2
%    0 0 0  -> paginas a las que apunta p3
% x=L'y    -> x=L'Lx  (x:autovector asociado a autovalor dominante de L'L)
% y=Lx     -> y=LL'y  (y:autovector asociado a autovalor dominante de LL')
%Aplicar metodo potencia a L'L
clear
L=zeros(6);
L(1,3)=1;L(1,5)=1;L(2,1)=1;L(3,5)=1;L(5,3:4)=1;L(6,5)=1;%Vas mirando por filas las filas a las que apunta el nº de fila actual
% L= 0 0 1 0 1 0 -> P1 apunta a las páginas p3 y p5

%Aplicamos metodo potencia para sacar autovector x que refleja pesos de autoridad

A=L' * L;
n = size(A, 1);
x0=ones(n,1);
x0=x0/norm(x0);
x=A*x0;
iter=0;
while iter<20
    iter = iter + 1;
    x0=x;
    x0=x0/norm(x0);
    x=A*x0;
    lambda=x'*x0

end

y=L*x
y0=L*x0






