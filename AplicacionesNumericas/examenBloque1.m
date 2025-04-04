clear

% 1)
P= [0.5 0.6 0.7 0.7; 0.4 0.5 0.7 0.5; 0.3 0.3 0.5 0.2; 0.3 0.5 0.8 0.5];

%Construir matriz M, por comparacion por pares
n=4;
M=zeros(n,n);
for i=1:n
    for j=1:n
        M(i,j)=P(i,j)/P(j,i);
    end
end    

%Calcular autovector asociado al autovalor dominante con metodo potencia
x0=ones(4,1);
x0=x0/norm(x0); %Autovector asociado normalizado
error=100;
x=M*x0;
lambda1=x0'*x; %autovalor dominante
iter=0;
while error>10e-15
    iter=iter+1;
    x0=x;
    x0=x0/norm(x0);
    x=M*x0;
    lambda_new=x0'*x;
    res(iter)=abs(lambda_new-lambda1); %Error a partir de la distancia entre ultimos autovalores
    error=res(iter);
    lambda_arr(iter)=lambda1;
    err(iter)=abs(0.8376/lambda1)^iter;
    lambda1=lambda_new;
    %Nº iteraciones,   autovalor dominante lambda  y error
    fprintf("n_iter: %2d lambda: %.16f error: %.2e \n", iter , lambda_arr(iter), res(iter))
end

%Vector errores
res
%Vector v
v=x0/sum(x0) %v2=x/sum(x), mismo resultado


tasa=abs(0.8376/lambda1); %Lambda2=0.8376 dado en enunciado
%Imprimimos res=vector errores('*r'), y la evolucion tasa convergencia('b')
semilogy(1:iter, res, '*r',1:iter, (err-tasa.^iter),'b')
CI=(lambda1-n)/(n-1)

%Si se hubiera borrado
%p13, estimar con autovectorvector asociado 
%Sabemos pij=vi/(vi+vj)
p13=v(1)/(v(1)+v(3))






%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%Apart 2)

H=[ 1 -P(1,2) 0 0;   %Coges diagonal de unos (procesas los datos i<j)                       
    1 0 -P(1,3) 0;   % Y completas con datos de la matriz inicial
    1 0 0 -P(1,4);
    0 1 -P(2,3) 0 ;
    0 1 0 -P(2,4) ;
    0 0 1 -P(3,4);
    1 1 1 1]
b=[0 0 0 0 0 0 1]'

w2=H\b %Autovector asociado
w2=w2/sum(w2) %Normalizas para sum=1

%Construimos matriz de residuos
R=zeros(n,n);
for i=1:n
    for j=1:n
        R(i,j)=abs((w2(i)/(w2(i)+w2(j)))-P(i,j));
    end
end   
max((R(:)))


