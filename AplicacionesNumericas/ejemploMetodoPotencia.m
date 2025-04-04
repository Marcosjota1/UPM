clear
A=[-1 -9 -5 0;
    3 11 4 0;
    -1 -1 2 0;
    0 0 0 56];
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
%AUTOVECTOR ASOCIADO NORMALIZADO
x0
%AUTOVALOR DOMINANTE
lambda