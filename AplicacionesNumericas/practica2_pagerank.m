clear;

%load web-Stanford.mat
load web-Google.mat
Problem
A=Problem.A;
N=size(A,1);

    Nj=sum(A);
    dj=zeros(1,N);
    dj=(Nj == 0);
    Nj(Nj==0)=1;
    A=A./Nj;

whos
nonulos=nnz(A);
N=size(A,1);
spy(A);title('Gráfica de la dispersión de la matriz A');

[pagerank, ordenpagerank, precision, tiempo]=calculo_PR(A,0.85,100)
precision