function [x,iter]=potencia(A,tol,nmax,x0)
% Calcula el autovector dominante de A
n=size(A,1);
if nargin == 1
    tol = 1e-12;    % Tolerancia
    x0=ones(n,1);   % Vector de arranque
    nmax=500;       % Nº máx iteraciones
end
x0=x0/norm(x0);
x1=A*x0;
err = 1;
iter=0;
error=zeros(nmax,1);%error(iter+1)=err;
while err > tol & iter <= nmax
    x=x1; x=x/norm(x);
    x1=A*x; 
    err = norm(x-x1);
    iter = iter + 1;
end
return 
