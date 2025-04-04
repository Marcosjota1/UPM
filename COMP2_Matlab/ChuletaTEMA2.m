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
%-----------------INTERPOLAR CON FUNCIONES---------------------------------
fun(x)=exp(-x.^3)
%a) Polinomio de grado minimo que interpola(f(xi),xi)
xi=[-1:1/2:1]'; %5 nodos
yi = fun(xi);
H = [xi.^0 xi.^1 xi.^2 xi.^3 xi.^4];
v = yi;
c = H\v
x=-1:0.01:1
p1 = c(1)+c(2)*x + c(3)*x.^2 + c(4)*x.^3 + c(5)*x.^4;

E1=abs(fun(x)-p1);
AE1=sum(E1)*0.01;
subplot(121);
plot(xi,yi,'ro',x,p1,'b.',x,fun(x),'m.'); %Dibujamos nodos/valores, p1(azul), f(x)(magenta)
hold on
subplot(122);
plot(x,E1,'b.');
hold on

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
plot(xx,yy,'g',xi,yi,'*r')
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

%------------------------INTERPOLACION DE NEWTON---------------------------
% 1. Visualizar en el intervalo [0,1.2] cómo se parecen los polinomios p1(x) y p2(x) que
% interpolan a la función f(x)=cos(x) en los puntos {0, 1.2} y {0,0.6, 1.2}, respectivamente
% Caso 1: Interpol. por polinomio grado 1: 
xi1=[0 1.2]';
yi1=cos(xi1);
H1=[xi1.^0 xi1.^1];
c1=H1\yi1;
% Caso 2: Interpol. por polinomio grado 2:
xi2=[0 0.6 1.2]';
yi2=cos(xi2);
H2=[xi2.^0 xi2.^1 xi2.^2];
c2=H2\yi2;
% Gráficas de la función y los polinomios de interpolación:
xx=0:0.1:1.2;
yy1=c1(1)+c1(2)*xx;
yy2=c2(1)+c2(2)*xx+c2(3)*xx.^2;
plot(xx,cos(xx),'r',xx,yy1,'g',xx,yy2,'b',xi2,yi2,'or')
% Gráficas de la función error de interpolación en los casos 1 y 2 respectivamente.
E1=abs(cos(xx)-yy1);
E2=abs(cos(xx)-yy2);
plot(xx,E1,'r',xx,E2,'g')

% 2. Número de cifras decimales significativas que se obtienen al aproximar cos(0.3)
% por p1(0.3) y p2(0.3) respectivamente.
%HACER erel con cos, yy1 e yy2 sustituyendo las x por o,3
ncif1=floor(-log10(abs(cos(0.3)-(c1(1)+c1(2)*0.3))/cos(0.3)))
ncif2=floor(-log10(abs(cos(0.3)-(c2(1)+c2(2)*0.3+c2(3)*0.3^2))/cos(0.3)))

%Newton--------------------------------------------------------------------
xi= [-1 0 1 2]'
yi= [-2 -1 0 3]'
dd=dif_div(xi,fi) %habiendo añadido el script de newton en la misma carpeta,te
%va a dar unos valores
%que van a ser la matriz PP=[]
dd = [1 -8 40 -106.67] % nos sale de haber echo dd=dif_div(xi,fi)
XX = [-0.15:0.01:0.9]
P_x = dd(1) + dd(2).*(XX-0) + dd(3).*(XX-0).*(XX-0.25) + dd(4).*(XX-0).*(XX-0.25).*(XX-0.5) 
%igual que en los otros casos pero con dd y habiendo escrito el script
%cuatro sumandos, porque hay 4 elementos
plot(xi,fi,'ro',XX,P_x,'b')
PP=dd(xi,yi,'go',XX,PP,'r')

%Hermite-------------------------------------------------------------------
xi= [-1 0 1 2]'
yi= [-2 -1 0 3]'
xx=[0,pi,2*pi] %puntos
Fun= sin(x)
FunDerivada= cos(x)
[f_x]=dif_hermite(xi,Fun,FunDerivada,xx)
%HH=fi(xi,yi,'ro',Fun,XX,'go')
plot(xx,f_x,xi,FUN,'o',xx,pp,'rr'),title('dasdg')

%EJERCICIO TIPICO DE INTERPOLACION DE NEWTON
function y=fun(x)
y=1./(1+25*x.*x); % Función f(x)
return
xx=[-1:0.01:1]; %vector para evaluar el polinomio
ff=fun1(xx);
k=1:4;
dx=2.^(1-k);
for k=1:4
xi=(-2:dx(k):2); %intervalo [-2,2]
fi=fun(xi);
pp=pol_newton(xi,fi,xx); %polinomio de interpolacion de la funcion
subplot(2,2,k);
plot(xi,fi,'or',xx,ff,'b',xx,pp,'r');
ERROR_INTERPOLACION=max(abs(ff-pp));
ERROR_REDONDEO=max(abs(fi-pol_newton(xi,fi,xi)));
fprintf('Usando %d nodos,el error de interpolacion es = %.18f\n',length(xi),ERROR_INTERPOLACION);
fprintf('Usando %d nodos,el error maximo de los nodos es = %.18f\n',length(xi),ERROR_REDONDEO);
plot(xi,ERROR_REDONDEO,'ro');
end

%-----------HERMITE--------------------------------------------------------
% Ejemplo 1: Construir el polinomio p(x) de interpolación deHermite
% de f(x)=ln(x) en los puntos 1 y 2.
% Datos numéricos (4 datos):
% p(1)=f(1)= ln(1)= 0, p(2)=f(2)= ln(2)= 0.69
% p'(1)=f'(1)=1/1= 1, p'(2)=f'(2)=1/2
% Tipo función interpolante: p(x) polinomio grado 3
%CONSTRUCCION POLINOMIO: p(x)=c0+c1x+c2x^2+c3x^3
% p(1)=c0+c1+c2+c3 =ln(1)=0
% p(2)=c0+2c1+4c2+8c3 =ln(1)=0
% p'(1)=c1+2c2+3c3 =ln(1)=0
% p'(2)=c1+c2+c3 =ln(1)=0


%%FALTA TAYLOR, HERMITE,REGLA HORNER






