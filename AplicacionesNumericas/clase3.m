%EJERCICIO 3
clear

w=[0.9358 0.0638 0.0004];
n=3;
M=zeros(n);

for i=1:n
    for j=1:n
        M(i,j)=w(i)/w(j);
    end
end

x=[1 1 1]';
x=x/norm(x)
error=100;
iter=0;
while  iter<11
    x1 = M*x;
    lamba=x'*x1;
    x=x1;
    x=x/norm(x);
    iter=iter+1;
end
bar(x);

%EJERCICIO 4
clear
lambda2=0.8724;
n=5

A=[1 5 3 7 5; 1/5 1 1/3 3 1/2; 1/3 3 1 5 3; 1/7 1/3 1/5 1 1/5;1/5 2 1/3 5 1];
x0=ones(n,1);
x0=x0/norm(x0);
error=100;
x=A*x0;
lambda=x0'*x;
iter=0;
while error>10e-14
    iter=iter+1;
    x0=x;
    x0=x0/norm(x0);
    x=A*x0;
    lambda_new=x0'*x;
    res(iter)=abs(lambda_new-lambda);
    tasa_convergencia(iter)=abs((lambda2/lambda)^iter);
    error=res;
    lambda_vector(iter)=lambda
    lambda=lambda_new;
end

for i=1:iter
fprintf( "n_iter: %2d, lambda: %.16f, error: %.2e \n", i, lambda_vector(i), res(i));
end



semilogy(1:iter,res,'*r',1:iter,tasa_convergencia,'b');



H=[1 -A(1,2) 0 0 0; 1 0 -A(1,3) 0 0; 1 0 0 -A(1,4) 0; 1 0 0 0 -A(1,5); 0 1 -A(2,3) 0 0;0 1 0 -A(2,4) 0; 0 1 0 0 -A(2,5); 0 0 1 -A(3,4) 0; 0 0 1 0 -A(3,5);0 0 0 1 -A(4,5); 1 1 1 1 1]
b=[0 0 0 0 0 0 0 0 0 0 1]';

v=H\b;
w=v/sum(v);

lambda_max=mean(A*w./w);
CI=(lambda_max-n)/(n-1);

W=zeros(n,n);
for i=1:5
    for j=1:5
        W(i,j)=w(i)/w(j);
    end
end
Des_max=max(max(abs(W-A))) 
%otra opcion
Res=abs(W-A);
Des_max2=max(Res(:)) %Pones res de matriz a vector y sacas maximo del vector

A2=eye(n);
A2(1,2)=5;
A2(1,3)=3;
A2(1,4)=7;
A2(1,5)=5;
for i=1:n
    for j=1:n
        if j==1
            A2(i,j)= 1/A2(i,j);
        else
            A2(i,j)=A2(1,j)*A(i,1);
        end
    end
end    

H2=[1 -1 0 0 0;
    1 0 -1 0 0;
    1 0 0 -1 0
    1 0 0 0 -1
    0 1 -1 0 0
    0 1 0 -1 0
    0 1 0 0 -1
    0 0 1 -1 0;
    0 0 1 0 -1;
    0 0 0 1 -1
    1 1 1 1 1]; %Condicion necesaria, ultima = todo unos
L=log(A2);
b2=[L(1,2) L(1,3) L(1,4) L(1,5) L(2,3) L(2,4) L(2,5) L(3,4) L(3,5) L(4,5) 0]';%' para hacer de vector a columnas

v2=H2\b2;
w2=exp(v2)/sum(exp(v2));


%Otra opción H2
H3=zeros(4,5);
H3(:,1)=ones(4,1); %Meterias todo unos a 4 priemras fila de 1 columna
for i=1:4
    H(i,i+1)=-A(1,i+1);
end   
H3=[H;ones(1,5)];

