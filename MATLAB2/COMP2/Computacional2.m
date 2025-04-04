clear,clc
%EJERCICIO 1
xi=[1, 2, 3, 4]';  
yi= [0.2, 8, 6, 20]'; 
%a)
H1=[xi.^0 xi.^1 xi.^2 xi.^3];
b1=yi;
c1=H1\b1;

%b)
%Resolviendo q`(0) = b = 0,al ser 0 el coeficiente no hace nada restar a la
%b
H2=[xi.^0 xi.^2 xi.^3 xi.^4];
b2=yi;
c2=H2\b2;

%c)
H3=[sin(xi) cos(xi) exp(xi) xi.^3]
b3=yi
c3=H3\b3

%d)

xx=1:0.01:4
u1=c1(1)+c1(2)*xx+c1(3).*xx.^2+c1(4).*xx.^3
u2=c2(1)+c2(2).*xx.^2+c2(3).*xx.^3+c2(4).*xx.^4
u3=c3(1).*sin(xx)+c3(2).*cos(xx)+c3(3).*exp(xx)+c3(4).*xx.^3
plot(xx,u1,'r',xx,u2,'g',xx,u3,'b',xi,yi,'k*')

%e)

errorqx= abs(u1-u2)
errorrx= abs(u1-u3)
plot(xx, errorqx, 'b',xx,errorrx,'g');
title('Error de interpolación');

%f)
[maxerr,pos]=max(errorqx); 
a = xx(pos)
[maxerr,pos]=max(errorrx); 
b = xx(pos)


%EJERCICIO2
clear,clc
xk=[0, 2, 4, 6]';
yk=[3.5,1, -4, 2]';
H1=[sin(xk) cos(xk)]

% u = a*sin(x)+bcos(x)
%a)
c1 = H1\yk;
xx=0:0.01:2*pi;
u=c1(1).*sin(xx)+c1(2).*cos(xx)
plot(xx,u,'g',xk,yk,'rs')

R1 = abs(H1*c1-yk);
e1=norm(R1)


%b)
%b=3.0
H2=[sin(xk)]
b2=yk-3
c2 = H2\b2;

R2 = abs(H2*c2-yk);
e2=norm(R2)












