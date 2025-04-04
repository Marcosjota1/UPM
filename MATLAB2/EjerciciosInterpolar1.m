%f(x)=2*sinh(x);
%p1(x)=x.^0 x.^1 x.^2
clear
clc
%1.0------DATOS A INTERPOLAR
x=[1 3 5]';
y=2*sinh(x);
%1.1------SISTEMA LINEAL
H=[x.^0 x.^1 x.^2 ];
b=y;
c=H\b;
c0=c(1);
c1=c(2);
c2=c(3);
% 1.2----- DIBUJAR COMANDO SUBPLOT

xx = 1:0.001:5;

% Calcular f(x), p1(x) y el error de interpolación
f = 2*sinh(xx);
p1 = c0.*xx.^0 + c1.*xx.^1 + c2.*xx.^2;
error_interpolacion = abs(f - p1);

% Configurar la primera gráfica
subplot(1,2,1)
plot(xx, f, 'g', xx, p1, 'r', x, 2*sinh(x), '*r')
title('Función original y polinomio interpolador');
legend('f(x)', 'p1(x)', 'Puntos de interpolación');

% Configurar la segunda gráfica
subplot(1,2,2)
plot(xx, error_interpolacion, 'b');
title('Error de interpolación');
legend('Error');
%Valor maximo y mínimo de error
[maximo,puntox]=max(error_interpolacion)
punto_maximo = xx(puntox)
[minimo,puntoy]=min(error_interpolacion)
punto_minimo = xx(puntoy)
% Aproximar f(3.1) por p1(3.1)
x_aprox = 3.1
f_x_aprox = 2*sinh(x_aprox)
p1_x_aprox = c0.*x_aprox.^0 + c1.*x_aprox.^1 + c2.*x_aprox.^2

% Calcular el número de cifras decimales significativas
erel= abs(p1_x_aprox-f_x_aprox)./abs(f_x_aprox)
ncif = floor(-log10(erel))



%2----------------------------------------------------------
%FUNCION F(X)
clear
clc
%Datos
x=[1 ,5]';
y=2*sinh(x);
H2=[x.^0 (x-1).^2 ];
b2=y;
c2=H2\b2;
c0=c2(1);
c1=c2(2);
















