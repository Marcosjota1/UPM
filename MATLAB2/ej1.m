clear 
clc
x = 0:0.01:2*pi;
x(end);
7*ones(3,7);
A=[1 2 3 4;5 6 7 8;9 10 11 12];
A(1:2:3,:);
A(:,end);
C=A(3:-1:2,2:1:3);
C = A(3:-1:1,end:-1:3);
notas = 6+1.2*randn(1,70);
med = mean(notas);
mx = max(notas);
med = mean(notas(notas>=5));
notas(notas>=4.5 & notas<5) = 5;
x=[0:3:30];
x(1:2:end)=0;
x=[1 2 3 4];
y=[5 6 7 8];
A = [x;y]';
x=[1 2 3 4] ;
y=[5 6 7 8]; 
z=1./x;
x.^x;
A = [1 2;3 4];
B= [5 6 ; 7 8];
A*B;
B*A;
n=[1:1000];
vap=(1+1./n).^n;
error=abs(vap-exp(1));
plot(n,error,'*r');
semilogy(n,error);





