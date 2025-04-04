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
c=H\yi;
c0=c(1);
c1=c(2);
c2=c(3);

xx= [-1:0.01:1];
f = 1./(1+(25*(xx.^2)));
p1 = c0 + c1.*xx.^1+c2.*xx.^2; %=p1=polyval(c(end:-1:1), xx);
error_interpolacion = abs(f - p1); %ERROR = funcion interpolada - funcion interpolante
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
% Otra Manera
clear
a=-1;b=1;
error_max=zeros(1,8);
for n=1:4
 h=1/2^(n-1);
 xi=[a:h:b]';
 yi=1./(1+25*xi.^2);
 %llamamos a la función que hemos creado para resolver el sitema lineal
 c= coef_interp(xi,yi);
% Gráfica de los polinomios y errores de interpolación
xx=a:0.01:b; =length(xx);
pp=zeros(4, l);
pp(n,:)=polyval(c(end:-1:1), xx);
error=zeros(4,l);
error(n,:)=abs(pp(n,:)-(1./(1+25*xx.^2)));
subplot(2,4,n)
plot(xx,pp(n,:),xx, 1./(1+25*xx.^2), xi,yi,'*g')
subplot(2,4,n+4)
plot(xx,error(n,:), xi,yi,’*g’)
%Calculamos el máximo de la función error en los puntos xx
% y los guardamos en el vector eror_max:
maxerror=max(error(n,:));
fprintf('nº nodos: %d, maxerror: %0.2e \n',1+2^n, maxerror)
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
%¿Cuál es el valor máximo del error a partir de sus valores en xx? 
[maximo,puntox]=max(error_interpolacion)
punto_maximo = xx(puntox)
%¿Cuál es el valor mínimo del error y en qué puntos se alcanza?
[minimo,puntoy]=min(error_interpolacion)
punto_minimo = xx(puntoy)
% Aproximar f(3.1) por p1(3.1)
x_aprox = 3.1
f_x_aprox = 2*sinh(x_aprox)
p1_x_aprox = c0.*x_aprox.^0 + c1.*x_aprox.^1 + c2.*x_aprox.^2

% Calcular el número de cifras decimales significativas
erel= abs(p1_x_aprox-f_x_aprox)./abs(f_x_aprox)
ncif = floor(-log10(erel))

%-----------------INTERPOLAR CON FUNCIONES---------------------------------x
fun(xi)=exp(-xi^3)
%a) Polinomio de grado minimo que interpola(f(xi),xi)
xi=-1:1/2:1; %5 nodos
xi = xi';
yi = fun(xi);
H = [xi.^0 xi.^1 xi.^2 xi.^3 xi.^4];
v = yi;
c = H\v
xx=-1:0.01:1
p1 = c(1)+c(2)*xx + c(3)*xx.^2 + c(4)*xx.^3 + c(5)*xx.^4;

E1=abs(fun(xx)-p1);
AE1=sum(E1)*0.01;
subplot(121);
plot(xi,yi,'ro',xx,p1,'b.',xx,fun(xx),'m.'); %Dibujamos nodos/valores, p1(azul), f(x)(magenta)
hold on
subplot(122);
plot(xx,E1,'b.');
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
xi=[0 1 2]';
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
%%INTERPOLAR POLINOMIAL CLASICA: &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&

% 1. Visualizar en el intervalo [0,1.2] cómo se parecen los polinomios p1(x) y p2(x) que
% interpolan a la función f(x)=cos(x) en los puntos {0, 1.2} y {0,0.6, 1.2},
1 % Caso 1: Interpol. por polinomio grado 1: xi1=[0 1.2]';
yi1=cos(xi1);
H1=[xi1.^0 xi1.^1];
c1=H1\yi1;
% Caso 2: Interpol. por polinomio grado 2: xi2=[0 0.6 1.2]';
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

ncif1=floor(-log10(abs(cos(0.3)-(c1(1)+c1(2)*0.3))/cos(0.3)))
ncif2=floor(-log10(abs(cos(0.3)-(c2(1)+c2(2)*0.3+c2(3)*0.3^2))/cos(0.3)))


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
%va a dar unos valores que van a ser la matriz PP=[]
dd = [1 -8 40 -106.67] % nos sale de haber echo dd=dif_div(xi,fi)
XX = [-0.15:0.01:0.9]
P_x = dd(1) + dd(2).*(XX-0) + dd(3).*(XX-0).*(XX-0.25) + dd(4).*(XX-0).*(XX-0.25).*(XX-0.5) 
%igual que en los otros casos pero con dd y habiendo escrito el script
%cuatro sumandos, porque hay 4 elementos
plot(xi,fi,'ro',XX,P_x,'b')
PP=dd(xi,yi,'go',XX,PP,'r')

%Tabla de diferencias divididas--------------------------------------------
 % Datos de entrada
xi = [0 1 2]';
fi = [1 0 1]';

% Calcular la tabla de diferencias divididas
dd = difdiv(xi, fi);

% Vector xx
xx = 0:0.01:2;

% Evaluar el polinomio de Newton en xx
yy = zeros(size(xx));
for j = 1:length(xx)
    x_eval = xx(j);
    N = length(xi);
    result = dd(N, N);
    for k = N-1:-1:1
        result = result * (x_eval - xi(k)) + dd(k, k);
    end
    yy(j) = result;
end

% Graficar el polinomio y los puntos interpolados
figure;
plot(xx, yy, 'b', xi, fi, 'ro');
xlabel('x');
ylabel('y');
title('Polinomio de Newton y Puntos Interpolados');
legend('Polinomio de Newton', 'Puntos Interpolados', 'Location', 'northwest');
grid on;
%Hermite-------------------------------------------------------------------
xi= [-1 0 1 2]'
yi= [-2 -1 0 3]'
xx=[0,pi,2*pi] %puntos
Fun= sin(x)
FunDerivada= cos(x)
[f_x]=dif_hermite(xi,Fun,FunDerivada,xx)
%HH=fi(xi,yi,'ro',Fun,XX,'go')
plot(xx,f_x,xi,FUN,'o',xx,pp,'rr'),title('dasdg')

% EJERCICIO INTERPOLAR NEWTON
% Interpolación de la función f(x)=sin(exp(x)) en un conjunto de puntos equiespaciados en el intervalo [-1,1].
% a) Interpolar f(x) por el polinomio de grado mínimo en 6 puntos equiespaciados (xi+1-xi=h) en el intervalo [-1,1]
% ¿Cuál es el grado del polinomio? Para ello hay que construir los vectores xi e yi y resolver el correspondiente
% problema de interpolación. Hacer una gráfica que muestre simultáneamente el polinomio obtenido (‘b’), la función f(x)
% (‘g’) y los datos del problema de interpolación (‘ro’) en el intervalo [-1:0.001:1]. Calcular cúal es el valor máximo del
% error de interpolación en los puntos xx=-1:0.01:1.
% Datos:
n=6; h=2/(n-1);
xi=-1:h:1; % xi=linspce(-1,1,6);
yi=sin(2.*exp(xi));

xx=-1:0.01:1;
fx=sin(2.*exp(xx));
px= polNewton(xi',yi',xx);
plot(xi,yi,'ro',xx,px,'b',xx,fx,'g')

% Error interpolación en xx. Máximo:
error_interp=(abs(fx-px));
maxerror=max(error_interp)
% b) Vamos a repetir el apartado anterior considerando ahora n puntos equiespaciados con n=2:40, esto es calcular
% los correspondientes polinomios de interpolación en 2 puntos, en 3 puntos y así sucesivamente hasta 40 puntos
% equiespaciados en el intervalo [-1,1]. En cada caso, para cada n, calcular el valor máximo del error de interpolación
% y guardarlo en el vector maxerror (maxerror(n)=max(abs(f(xx)-p(xx))) sobre una muestra xx de puntos del intervalo
% [-1,1] (por ejemplo xx=-1:0.01:1). Representar gráficamente el máximo del error de interpolación en función del nº
% de puntos empleados, utilizar escala logarítmica en el eje y. Comentar el comportamiento del error al aumentar el nº
% de puntos de interpolación utilizados.

for n=2:40
 h=2/(n-1);
 xi=-1:h:1;
 yi=sin(2.*exp(xi));
 px= polNewton(xi',yi',xx);
 maxerror(n)=max(abs(fx-px));
end
semilogy(2:40,maxerror(2:40))
%---------------------EJERCICIOS INTERPOLAR TAYLOR-------------------------
%DATOS = f(a)=f, f'(a)=f',f''(a)...
%Tipo funcion interpolante = p(x)= c1+c2*e^x+c3*e^-x
x=[-0.5,0.5]
p(x)= c1+c2*e^x+c3*e^-x







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
% p(2)=c0+2c1+4c2+8c3 =ln(2)=0.69
% p'(1)=c1+2c2+3c3 =f'(1)=1
% p'(2)=c1+4c2+12c3 = f'(2)=1/2
syms x;

% Datos dados
x_data = [1, 2];
y_data = [0, log(2)];
y_prime_data = [1, 1/2];

% Construir el polinomio de interpolación de Hermite
p_hermite = hermite(x_data, y_data, y_prime_data);

% Mostrar el polinomio
disp('Polinomio de interpolación de Hermite:');
disp(p_hermite);

% Graficar el polinomio junto con los puntos de interpolación
fplot(p_hermite, [min(x_data), max(x_data)], 'LineWidth', 2);
hold on;
scatter(x_data, y_data, 'r', 'filled');
title('Polinomio de Interpolación de Hermite');
xlabel('x');
ylabel('y');
legend('Polinomio de Hermite', 'Puntos de interpolación');

% Mostrar los resultados
disp('Puntos de interpolación:');
disp([x_data; y_data]);

% Calcular el valor del polinomio en x = 1.5
x_interp = 1.5;
y_interp = subs(p_hermite, x, x_interp);
disp(['Valor interpolado en x = 1.5: ' num2str(y_interp)]);

hold off;



%%FALTA TAYLOR, HERMITE,REGLA HORNER,CREACION FUNCIONES, TABLA DIFERENCIAS
%%DIVIDIDAS



%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%-------------------ANEXO-------------------------------------------------%

%---------
function px=polNewton(xi,fi,xx)
% Calcula el polinomio de Newton que interpola la tabla (xi,fi) y lo evalúa en el vector xx
% Argumentos de Entrada: xi nodos donde se interpola, fi valores en los nodos, xx vector donde se evalúa el valor
%del polinomio de interpolación
% Salida: px=valor(es) del polinomio de Newton en xx
N=length(xi);m=length(xx);
%Llamamos a función difdiv
dd=difdiv(xi,fi);
pp=zeros(1,m);
pp=xx-xi(1);
px=zeros(1,m);
px=dd(1)+dd(2)*pp;
for k=3:N
 pp=pp.*(xx-xi(k-1));
 px=px+dd(k)*pp;
end
end
dd=D(1,:); %EXTRAES POLINOMIOS PARA FORMULA NEWTON

%Fórmula Newton: p(x)=dd(1)+dd(2)(x-xi(1))+…+dd(N)(x-xi(1)) ···(x-xi(N-1))
%--------------------------------------------------------------------------
function dd=difdiv(xi,fi)
% Función que calcula la tabla de diferencias divididas de una función f en puntos xi con f(x)=fi.
% Argumentos Entrada: xi nodos, fi valores de f(x) en xi
% Argumento Salida: dd vector de coeficientes en la Fórmula de Newton del polinomio de interpolación de f(x) en xi.
% En la matriz D se van guardando los valores de la tabla de diferencias divididas.
% Los coeficientes de la F. de Newton se disponen en la primera fila de D, atendiendo a cómo se guardan las dif.
%divididas en el procedimiento siguiente.
% Alternativamente se podría haber organizado de forma que los coeficientes de Newton estuvieran en la diagonal (ver
%en diapositivas de clase).
N=length(xi);
D=zeros(N);
D(:,1)=fi;
for k=2:N
 for j=1:N-k+1
 dif=D(j,k-1)-D(j+1,k-1);
 dx=xi(j)-xi(j+k-1);
 D(j,k)=dif/dx;
 end
end
% Vector con coeficientes F. Newton
dd=D(1,:);
end




