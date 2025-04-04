%Ejercicio 4
%Definimos matriz
clear
A=[1 5 3 7 5; 1/5 1 1/3 3 1/2; 1/3 3 1 5 3; 1/7 1/3 1/5 1 1/5;1/5 2 1/3 5 1];

%Metodo de la potencia
x0=ones(5,1);
x0=x0/norm(x0); %Autovector asociado normalizado
error=100;
x=A*x0;
lambda=x0'*x; %autovalor dominante
iter=1;
while error>10e-14
    iter=iter+1;
    x0=x;
    x0=x0/norm(x0);
    x=A*x0;
    lambda_new=x0'*x;
    res(iter)=abs(lambda_new-lambda);
    error=res(iter);
    lambda_arr(iter)=lambda;
    err(iter)=abs(0.8724/lambda)^iter;
    lambda=lambda_new;
    %Nº iteraciones,   autovalor dominante lambda  y error
    fprintf("n_iter: %2d lambda: %.16f error: %.2e \n", iter , lambda_arr(iter), res(iter))
end

tasa=abs(0.8724/lambda); %Tasa convergencia

%Si M consistente CI(M)=0. Si M recíproca: CI(M)>=0. 
%CI=(lambda-n)/(n-1)
CI=(lambda-5)/(5-1); %Lambda ultima - dimnensiones vector = IndiceInconsistencia
                     % Si es cercano a cero, la matriz es consistente
                     % luego menos iteraciones al converger mas rapidamente y
                     % conseguir menor error
%       Vector errores             Tasa de convergencia
semilogy(1:iter, res, '*r',1:iter, err-tasa.^iter,'b')

%Metodo minimos cuadrados para sacar valor vector de pesos w
H=[1 -A(1,2) 0 0 0;
    1 0 -A(1,3) 0 0;
    1 0 0 -A(1,4) 0;
    1 0 0 0 -A(1,5);
    0 1 -A(2,3) 0 0;
    0 1 0 -A(2,4) 0;
    0 1 0 0 -A(2,5); 
    0 0 1 -A(3,4) 0;
    0 0 1 0 -A(3,5);
    0 0 0 1 -A(4,5);
    1 1 1 1 1]
b=[0 0 0 0 0 0 0 0 0 0 1]'

v=H\b;
w=v/sum(v); %Piden suma componentes = 1, normalizas el vector obtenido

bar(w);

%Construir matriz W a partir del vector w
W=zeros(5,5);
for i=1:5
    for j=1:5
        W(i,j)=w(i)/w(j);
    end
end
Res=abs(W-A); %Que elementos se desvian más de la matriz W a la A?
Des_max=max(Res(:));

%2)
%Tecnica de minimos cuadrados para solucionar sistema lineal
L=log(A);
H2=[1 -1 0 0 0; 1 0 -1 0 0; 1 0 0 -1 0; 1 0 0 0 -1; 1 1 1 1 1]; %Piden procesar unicamente la primera fila
b2=[L(1,2) L(1,3) L(1,4) L(1,5) 0]';

v2=H2\b2;
w2=exp(v2)/sum(exp(v2));