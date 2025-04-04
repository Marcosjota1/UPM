% A
% Creamos matriz conectividad
clear;
i=[1 1 1 2 2 3 3 4 4 6 6 6 7 7];
j=[2 4 5 3 7 4 6 2 7 4 5 7 2 4];
N=7;
C=sparse(j,i,1,N,N);
Ccompleta=full(C);
whos;
Nj=sum(Ccompleta)

dj=find(Nj == 0);
djC=find(Nj~=0);

S=zeros(N);
for k=djC
    S(:,k)=C(:,k)/Nj(k)
end

sum(S)

alfa=0.85;
G=alfa*S+(1-alfa)*ones(N)/N

%B
[V D]=eig(G)
abs(diag(D))
[v idx]=max(abs(diag(D)));
a = abs(V(:,idx));


%C
[lambda x]=potencia2(G,50)
precision=max(norm(norm(G*x-x)),abs(lambda-1));


for k=1:1000
    x=G*x;
end
r1=x/norm(x)

for k=1:1000
    a=G*a;
end
r2=a/norm(a)

%D
pagerank=x/sum(x)
[lambda, pagerank2]=potencia3(G,50);
bar(pagerank2)
sort(pagerank2)




%2




