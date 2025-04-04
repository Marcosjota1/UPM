clc, clear;
% Examen computacional 2 23/11/2023
%DATOS TODO EL EJERCICIO
xk=[-1,0,1,2]';
yi=1./(1+xk.^4);
xx=-1:0.01:2;
%1

%Interpolar la funcion en los nodos mediante la combinacion lineal i(x) de 
%las funciones (1,x,cosx/2,s^x)
H=[xk.^0,xk.^1,cos(xk./2),exp(xk)];
b=yi;
c=H\b;
%Dibujar figura en la izquierda la grafica de f(x junto con la funcion
%interpolada i, en el soporte xx y los puntos de interpolación
i = c(1).*xx.^0 + c(2).*xx.^1 + c(3).*cos(xx./2) + c(4).*exp(xx);
fxx=1./(1+xx.^4);

subplot(1,2,1);
plot(xx,fxx,'g',xx,i,'b',xk,yi,'r*')
%Calcular error de interpolacion f(x)-i(x) en los puntos xx, calcular error
%maximo de interpolación dibujar a la derecha la grafica con el error y los
%puntos máximos 
E=abs(fxx-i);
[maxerr,pos]=max(E); % Te dira el maxerr(error máximo) y su posicion
a = xx(pos); % para pintar la posicion en xx
subplot(1,2,2);
plot(xx,E,'k',a,maxerr,'r*');

%2 Interpolar mediante un polinomio de grado minimo i2 que ademas verifica
%i'(1)= 1 , dibujar la funcion f(x con i2(x y los puntos de interpolación  
% 5(4+derivada) datos luego el polinomio de grado minimo es de grado 4
% p(x) = a +bx + cx^2 + dx^3 + ex^4;
% p'(x) = b + 2cx + 3dx^2 + 4ex^3;
% p'(1) = b +2c + 3d + 4e = -1
% b = -(1+2c+3d+4e);
% p(x) = a -x(1+2c+3d+4e) + cx^2 + dx^3 + ex^4;
% p(x) = -x + a + cx(x-2) + dx(x^2-3) + ex(x^3-4);
% p(x) + x = a + cx(x-2) + dx(x^2-3) + ex(x^3-4);
H2 = [xk.^0,xk.*(xk-2),xk.*(xk.^2-3),xk.*(xk.^3-4)]; %el x^1 ya lo sabes, no poner en H pones directamente en i2
b2=yi+xk.^1; %(el x^1 se te queda solo, ya sabes su valor)
c2=H2\b2;
i2 = c2(1).*xx.^0 - xx + c2(2).*xx.*(xx-2) + c2(3).*xx.*(xx.^2-3) +c2(4).*xx.*(xx.^3-4);
figure(1);clf;
plot(xx,fxx,'b',xx,i2,'g',xk,yi,'r*')


%3 Aproximar en sentido de mínimos cuadrados la funcion f(x en los nodods
%xk mediante una funcion i3 combinacion lineal de las funciones(1,cos x/2, senx/2)
%dibujar i3 con fxx y los nodods
%Dar la matriz del sistema sobredeterminado y la matriz de las ecuaciones
%normales
%Matriz sistema sobre determinado
H3 = [xk.^0,cos(xk./2),sin(xk./2)]
%Matriz ecuaciones normales
HN=H3'*H3 % Esto te lo hace solo matlab, pero como piden 2 matrices lo ponemos
          % siempre que haya que aproximar por minimos cuadrados 
% Se puede hacer de las dos maneras da igual
%Manera 1
b3= yi;
c3 = H3\b3;
%Manera 2 (minimos cuadrados, segun christian)
bN= H3'*b3;
cN=HN\bN;
i3 = c3(1).*xx.^0 + c3(2).*cos(xx./2) + c3(3).*sin(xx./2);
figure(2);clf;
plot(xx,fxx,'b',xx,i3,'g',xk,yi,'r*')


%Calcular el vector residuos y el error = sum r2, en que nodos se alcanza
%el maximo residuo ?
%Comandos y volcado para calcular el residuo y el Error.

r = abs(H3*c3-yi) % El maximo residuo se alcanza en el 0 y en el 1.
Error=sum(r.^2) % 0.0510




