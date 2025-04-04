clear
clc


%1-------------------------------------------------------------------------
% 1. Calcular el polinomio interpolador p(x) de la función f x( ) en 3 nodos equidistantes en el intervalo [-1, 1] (incluyendo
% los extremos), esto es en -1:1:1. Dibujar las gráficas del polinomio p(x) (rojo) y de f(x) (verde) en el intervalo [-1,1] y los
% valores interpolados (puntos verdes), tomando como vector auxiliar para crear las gráficas xx= [-1:0.01:1]
% (subplot(2,1,1)). Dibujar la gráfica del error E x =f x- p x  en xx, superponiendo los puntos interpolados
% (subplot(2,1,2)). Calcular el valor máximo del error en xx.

xi=[-1 0 1]';
yi= 1./(1+25*xi.^2);

H=[xi.^0 xi.^1 xi.^2];
b=yi;
c=H\b;
c0=c(1);
c1=c(2);
c2=c(3);

xx= [-1:0.01:1];
f = 1./(1+(25*(xx.^2)));
p1 = c0 + c1.*xx.^1+c2.*xx.^2;
error_interpolacion = abs(f - p1);
(subplot(2,1,1))
plot(xx, f, 'g', xx, p1, 'r', xi, yi, '*g')
title('Función original y polinomio interpolador');
legend('f(x)', 'p1(x)', 'Puntos de interpolación');
(subplot(2,1,2))
plot(xx, error_interpolacion, 'b')
title('Error de interpolación');
legend('Error');

%2-------------------------------------------------------------------------
% Crear la función Matlab (function c=coef_interp(xi,yi)) que implementa el 
% cálculo de los coeficientes del polinomio de
% interpolación en los datos xi, yi. Argumentos entrada: vectores xi, yi 
% (asumimos vectores columna) y argumento salida: c,
% vector de coeficientes del polinomio en la base {1,x,..,x^n}.
% Si l es la longitud de xi ¿qué grado tendrá el polinomio y cuál será 
% la dimensión de la matriz H de coeficientes del
% sistema?
% ¿Qué forma tendría la matriz H si trabajásemos con la expresión del
% polinomio en la base {1, (x-1),,…,(x-1)^n}?

function c =coef_interp(xi,yi)



end









