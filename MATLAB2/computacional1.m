clear;
clc;

%EJERCICIO 1 ----------------------------------------------
N=47; k=1:N; 
h=2.^(-k);
f=nan(size(h));
g=nan(size(h));
for x=1:N
    h=2.^(-x);
    f(x)=(sqrt(120+x))-(sqrt(120-x));
end
for x=1:N
    h=2.^(-x);
    g(x)=(2.*x)./(sqrt(120+x))+(sqrt(120-x));
end
f(1)
f(end)
g(1)
g(47)

%1b)
erel=abs((g-f)./g);
ncif=(-log10(erel));
[max,px]=max(ncif)
[min,pn]= min(ncif)
%1c)
a=[k,erel,ncif]
fprintf('Exponente k =%2d-Error relativo=%0.17f-Cifras Logradas=%d\n',[k,erel,ncif])
%1d)
figure;
subplot(121);semilogy(k,erel,'*b')
subplot(122);plot(k,ncif,'g*')

%EJERCICIO2----------------------------------------------------------------
%2a)
clear;
clc;
n=15:-1:0;
h=10.^-n;
x=0.1;
y=x+h;
ex1=(x.^2)-(y.^2);
ex2=(x+y).*(x-y);
erel=abs((ex1-ex2)./ex2)
ncif= floor(-log10(erel))
subplot(121);loglog(h,ex1,'b')
subplot(122);loglog(h,ex2,'g')
%2b)

for n=15:-1:0
    h=10^-n;
    x=0.1;
    y=x+h;
    ex1=(x.^2)-(y.^2);
    ex2=(x+y).*(x-y);
    erel=abs((ex1-ex2)./ex2);
    plot(n,erel,'r*');hold on;

end
hold off;

%2c
clear
clc
x=10^-1
for n=15:-1:11
    h=10^-n;
    y=x+h;
    ex1=(x.^2)-(y.^2);
    ex2=(x+y).*(x-y);
    erel=abs((ex1-ex2)./ex2);
    ncif=(-log10(erel))
    plot(h,ncif,'*');hold on;
    x=x-100;
    
end
hold off;




