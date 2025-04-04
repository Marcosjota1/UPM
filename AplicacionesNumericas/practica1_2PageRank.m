% A
% Creamos matriz conectividad
clear;
i=[3 3 5 7 7 8 11 11 11];
j=[8 10 11 8 11 9 2 9 10];
N=11;
C=sparse(j,i,1,N,N);
Nj=sum(C)

dj=find(Nj == 0);
djC=find(Nj~=0);

S=zeros(N);
for k=djC
    S(:,k)=C(:,k)/Nj(k)
end

sum(S)
S(:,dj)=1/N;
alfa=0.85;
G=alfa*S+(1-alfa)*ones(N)/N

[lambda, pagerank2]=potencia3(G,50);
bar(pagerank2)