clear

A=[ 1 1.5 2 4;
    0.75 1 1.5 3;
    0.5 0.75 1 2;
    0.25 0.33 0.5 1];

%Aplicamos metodo potencia
iter=0;
x0=ones(4,1);
x0=x0/norm(x0)
x=A*x0
lambda=x0'*x; %autovalor dominante
while iter<20
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
%Autovalor 
lambda
%Autovector
v=x0
%Vector pesos %SUM(wi)=1
w=v/sum(v) % -----> Ranking Asociado P4>P3>P2>P1




%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%%%%%%%%%%%%%%%%%%%%% OPCION MINIMOS CUADRADOS %%%%%%%%%%%%%%%%%%%%%%%%%%%%
 
H=[ 1 -A(1,2) 0 0;   %Coges diagonal de unos (procesas los datos i<j)                       
    1 0 -A(1,3) 0;   % Y completas con datos de la matriz inicial
    1 0 0 -A(1,4);
    0 1 -A(2,3) 0 ;
    0 1 0 -A(2,4) ;
    0 0 1 -A(3,4);
    1 1 1 1]
b=[0 0 0 0 0 0 1]'

w2=H\b %Autovector asociado
w2=w2/sum(w2) %Normalizas para vector pesos=Autovector normalizado





%Si M consistente CI(M)=0. Si M recíproca: CI(M)>=0. 
CI=(lambda-4)/(4-1)