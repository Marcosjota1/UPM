clear;
clc;
%Ejercicio 5

vex = exp(-1);
k=0:10;
n=((-1).^k)*1./factorial(k);
suma = sum(n);
erel=abs((vex-suma)/vex);
cf = floor(-log10(erel));

i = 5;
[a,b,c] =inversoe(i);
while(b>1e-15)
fprintf('n= %d  numero de cifras significativas= %d \n', i, c )
i = i+5;
[a,b,c] =inversoe(i);
end


%Ejercicio 7
n =  0:20;
x = 10.^-n;
v_exact = sinh(x);
e = exp(1);
v_aprox=((e.^x)-(e.^-x))./2;
loglog(x,v_exact,'bo',x,v_aprox,'*r')

eabs = abs(v_exact-v_aprox);
erel = eabs./abs(v_exact);
ncif = floor(-log10(erel));
cota=eps(1)./v_exact;
whos eabs erel ncif

subplot(1,2,1);loglog(x,erel,'bo-',x,cota,'ro-')
subplot(1,2,2);semilogx(x,ncif,'r*')

a = [x;erel;ncif];
fprintf('n=%2d error rel: %0.2e nº cifras: %2d \n', a)


