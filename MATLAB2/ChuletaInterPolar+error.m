clear
clc


%1-------------------------------------------------------------------------
% 1. Calcular el polinomio interpolador p(x) de la función f x(1./(1+25*xi.^2) ) en 3 nodos equidistantes en el intervalo [-1, 1] (incluyendo
% los extremos), esto es en -1:1:1. Dibujar las gráficas del polinomio p(x) (rojo) y de f(x) (verde) en el intervalo [-1,1] y los
% valores interpolados (puntos verdes), tomando como vector auxiliar para crear las gráficas xx= [-1:0.01:1]
% (subplot(2,1,1)). Dibujar la gráfica del error E x =f x- p x  en xx, superponiendo los puntos interpolados
% (subplot(2,1,2)). Calcular el valor máximo del error en xx.

xi=[-1 0 1]';  %INTERVALO EN EL QUE INTERPOLAR
yi= 1./(1+25*xi.^2); % LA FUNCION QUE TE DEN

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


% 3. El objetivo ahora es sistematizar lo hecho en el Apartado 1, generando polinomios que intepolen f(x) en un nº cada
% vez mayor 3, 5, 9 y 17 nodos equidistantes en el intervalo [-1,1] (incluyendo los extremos) y estudiar el error de
% interpolación. Para ello, hacer un bucle de n=1 a n=4 ((n=1), 5 (n=2), 9 (n=3) y 17 (n=4) nodos equidistantes en [-1,1]),
% de forma que para cada n:
% - xi: Nodos donde se interpola: h=1/2^(n-1) (distancia entre nodos consecutivos), xi=[-1:h:1]’
% - yi: Valores de la función f(x) en xi.
% - Aplicar la función creada en Apartado 2 para resolver el sistema lineal resultante.
% - Para pintar las gráficas de los polinomios resultantes: Para cada n hay que evaluar el polinomio en los puntos
% xx. Previamente podemos crear una matriz pp de ceros de dimensión 4xlenght(xx), de forma que en la fila nésima pp(n,:) guardaremos los valores del correspondiente polinomio en xx. Nota: Para evaluar el polinomio
% en xx utilizar comando polyval o algoritmo recursivo tipo for k=1:l, pp(n,:)=pp(n,:)+c(k)*xx.^(k-1);end o método
% de Horner (ver anexo1).
% - Para pintar las gráficas del error de interpolación en cada caso: Previamente creamos una matriz (que
% llamaremos error) de ceros de dimensión 4xlenght(xx), de forma que en la fila n-ésima error(n,:) guardaremos
% los valores del correspondiente error de interpolación en xx.
% - Para cada n pintar las gráficas de la función f(x) y los polinomios interpolantes (subplot(2,4,n)) y las de los
% correspondientes errores de interpolación ((subplot(2,4,n+4)).
% - Calcular para cada n el valor máximo del error (max(err(n,:))) y guardar en el vector maxerr. Utilizar el
% comando fprintf para que en cada línea muestre el nº de nodos empleados (1+2^n %2d) y el máximo del error
% (%0.2e). Comentar los resultados.

n = [3 5 9 17];
for i = 1:4
    xi = linspace(-1,1,n(i))';
    yi = 1./(1+25.*xi.^2);
    c = coef_interp(xi,yi);
    
    pp = zeros(4,length(xx));
    for k=1:n(i)
        pp(i,:)=pp(i,:)+c(k)*xx.^(k-1);
    end
    
    error = zeros(4,length(xx));
    error(i,:) = abs(ff-pp(i,:));
    
    subplot(2,4,i), plot(xi,yi,'g*',xx,ff,'g',xx,pp(i,:),'r')
    subplot(2,4,i+4), plot(xi,yi*0,'g*',xx,error(i,:),'b')
    
    maxerr = max(error(i,:));
    fprintf("Nodos = %2d, Error máximo = %0.2e\n",n(i),maxerr)
end



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

function c = coef_interp(xi,yi)
    n = length(xi);
    H = zeros(n,n);
    for i = 1:n
        H(:,i) = xi.^(i-1);
    end
    c = H\yi;
end

%------------OTRO EJERCICIO -----------------------------------------------
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


%---------------------------EJEMPLO-TRIGONOMETRICA------------------------

% EJEMPLO 2. Interpolar por una función trigonométrica del tipo
% p(x)=c1 1+c2 cos(2*pi*x )+c3 sen(2*pi*x )+c4 cos(4*pi*x )
%DATOS DE ENUNCIADO:

xi=[0:0.25:3/4]';
yi=[1 -1 2 0]';
% Construimos y resolvemos el sistema lineal Hc=b:
H=[xi.^0 cos(2*pi*xi) sin(2*pi*xi) cos(4*pi*xi)];
b=yi;
c=H\b %Resolvemos sistema lineal
% Gráfica de la función interpolante en [0,3/4] y puntos donde interpola
xx=0:0.01:3/4;
yy=c(1)+c(2)*cos(2*pi*xx)+c(3)*sin(2*pi*xx)+c(4)*cos(4*pi*xx);
plot(xx,yy,'g',xi,yi,'*r‘)
% Estimación que proporciona la función interpolante en el punto 0.4
x=0.4;
Val_Interp=c(1)+c(2)*cos(2*pi*x)+c(3)*sin(2*pi*x)+c(4)*cos(4*pi*x);

% • ¿Cuál es la estimación del valor de la función que proporciona la funcióninterpolante en x=0.4?
% Val_Interp

%-----------------------INTERPOLAR FUNCION MIXTA----------------------------
% Interpolar por una funcion del tipo 
% p(x)=c1*e^x+c2*cos(x)+c3*x^3+c4*1./1+x.^2

xi=[0:3]';
yi=[0 1 1.2 1.5]';

H=[exp(xi) cos(xi) xi.^3 1./(1+xi.^2)];
b=yi;
c=H\b

xx= 0:0.01:3 %primer hasta ultimo valor de xi separados por 0.01
yy=c(1)*exp(xx)+c(2)*cos(xx)+c(3)*xx.^3+c(4)*1./(1+xx.^2);
plot(xx,yy,'g',xi,yi,'*r')


%----------------------EJERCICIO CONDICION DERIVADA------------------------
% Te dan la tabla con datos xi e yi, se pide interpolar por 
% una función p(x) polinomial de grado mínimo con derivada igual a 1 en x=0
xi=[0 1]'; %Datos
yi=[0 2]';
% 2 datos xi en tabla + 1 condicion --> 3 incognitas, grado 2
% p(x) = c0 + c1x + c2x^2
% p'(0)=1 --> c1 = 1
% = p(x)=c0 + x + c2x^2
% p(0) = c0+0+c2*0 =0 -> c0=0
% p(1) = c0+1+c2*1^2=2 -> c2=2-1/1=1
% p(x) = x+x^2
H=[xi.^0 xi.^2]; b=yi-xi; %matrix coeficientes y término independiente sistema
c=H\b; %Resolvemos sistema y obtenemos dos coeficientes
xx=0:0.01:1; %Gráfica de la función y de los puntos donde interpola
yy=c(1)*xx.^0+xx+c(2)*xx.^2;
plot(xx,yy,'g',xi,yi,'*r') 

%------Interpolación polinomial clásica: Construcción preliminar-----------
% Construir el polinomio p(x) de menor grado que interpola
% los datos f(0)=0, f(1)=2, f(2)=0.
xi=[0:2]';
yi=[0 2 0]';

% 2. MATRIZ COEFICIENTES (H) Y TÉRMINO INDEPENDIENTE (b) DEL SISTEMA:
H=[xi.^0 xi.^1 xi.^2];
b=yi;
% 3 parametros -> grado 2
%Sustituyendo con las condiciones hayas c0=0,c1=4,c2=2
c=H\b;
c0=c(1);
c1=c(2);
c2=c(3);
%p(x)=4x-2x.^2
% 4. GRÁFICA DE LA FUNCIÓN INTERPOLANTE: Queremos representar gráficamente el polinomio p(x) y
%los puntos dónde interpola:
% Primero definimos un conjunto de puntos auxiliares en el intervalo [0,2] sobre los que haremos la gráfica:
xx=0:0.1:2;
% Creamos el vector de valores del polinomio obtenido en dichos puntos:
yy=c0* xx.^0+c1* xx.^1 +c2*xx.^2; 
% Pintamos la gráfica del polinomio interpolante en verde, señalando los puntos en los que se interpola con * rojos:
plot (xx, yy, 'g', xi, yi,'*r');
% 5. ESTIMAMOS f(1.1)
a=1.1; 
faprox=c0*+c1* a +c2*a^2;









