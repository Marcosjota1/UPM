%CHULETA EJERCICIO INTERPOLAR:
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

%------------------------------------------------------------------------


%INTERPOLACIÓN:
a)
clear all;
xk=[0 1 2 3 4]';
yk=[-1 2 1 1 3]';
H=[xk.^0 xk.^1 xk.^2 xk.^3 xk.^4];
v=yk;
c=H\v
plot(xk,yk,'bo') %Datos de la tabla
hold on;
xx=[-0.5:0.01:4.5]; %Soporte fino para pintar p(x)
pp=c(1)+c(2)*xx+c(3)*xx.^2+c(4)*xx.^3+c(5)*xx.^4; %Evaluo polinomio en xx
plot(xx,pp,'r')
hold off;
b)
clear all;
xk=[0 1 2 3 4]';
yk=[-1 2 1 1 3]';
H=[exp(-xk) cos(xk) sin(xk) 1./(1+xk) xk.^0];
v=yk;
c=H\v
plot(xk,yk,'bo')
hold on;
xx=[-0.5:0.01:4.5];
pp=c(1)*exp(-xx)+c(2)*cos(xx)+c(3)*sin(xx)+c(4)./(1+xx)+c(5)
plot(xx,pp,'r')
hold off;


%USO DE NEWTON (ejer 4 interpolación):
%Gráficas
xx=[-1:0.01:1];
for n=[1:1:4]
dx=2.^(1-n);
xi=[-1:dx:1]';
yi=fun(xi);
p=newton(xi,yi,xx);
subplot(2,2,n)
plot(xi,yi,'ro',xx,p,'b',xx,fun(xx),'g')
end
yx=fun(xx);
figure
%Errores:
for n=[1:1:4]
dx=2.^(1-n);
xi=[-1:dx:1]';
yi=fun(xi);
p=newton(xi,yi,xx);
%Error Interpolación:
Err=abs(yx-p);
e_interp_max=max(Err)
subplot(2,2,n)
plot(xx,Err,'r.')
%Error redondeo:
pp=newton(xi,yi,xi);
e_redondeo=max(abs(yi-pp))
end
function y=int_newton(xi,fi,xx)
N=length(xi);
D=zeros(N);
D(:,1)=fi;
for k=2:N
for j=1:N-k+1
D(j,k)=(D(j,k-1)-D(j+1,k-1));
D(j,k)=D(j,k)/(xi(j)-xi(j+k-1));
end
end
dd=D(1,:);
y=dd(N);
for k=N-1:-1:1
y=y.*(xx-xi(k))+dd(k);
end
return




%--------------------------------------------------------------------------
%TEMA 1
%Datos
x= 10.^-n
n=[0:20]
%Definicion de las funciones
seno_Hiperb = sin(x)
seno_Hiperb_Aprox = (exp(1).^x - exp(1).^-x) ./ 2
%Errores Absolutos y relativos
Eabs = abs(seno_Hiperb - seno_Hiperb_Aprox)
Erel=Eabs./ seno_Hiperb
%Cotas
Cota_E_rel_trunc=(h./2).*(sin(x)/cos(x))
Cota_E_rel_redondeo=(eps(1)./h).*(sin(x)/cos(x))
Cota_E_numer_total=Cota_E_rel_trunc + Cota_E_rel_redondeo
%Cifras Significativas
Cifras_signifi=floor(-log10(Erel))
%Representacion
figure
semilogy(n, Erel)
fprintf('n=%02d\tapr=%.13f\n',[n;apr])
-> n=01(2 crifas) apr=2.3435(13 cifras sifnificativas)
%EJEMPLO
n=[1:1:30]
h=0.5.^n
funReal= 1.253380767493447
funAprox= (3*exp(sin(x)) -4*exp(sin(x - h)) + exp(sin(x - 2*h)))./(2*h)
%TEMA 2
%Si no me dan los xi e fi, los calculo de esta forma:
xi=linspace(0,7,9); (A,B(intervalo, n(grado del polinomio +1))
fi=fun1(xi);
fprintf('%f %f \n'[xi;fi]) ;
%Te dan una funcion y te dicen que calcules su polinomio interpolador:
xi=[1:5/10:6]' %intervalo
yi=atan(xi); %funcion
H=[xi.^0,xi.^1,xi.^2,xi.^3,xi.^4,xi.^5,xi.^6,xi.^7,xi.^8,xi.^9,xi.^1
    0]; %base del polinomio, en este caso 10
c=inv(H)*yi %coeficientes
x=[0:0.01:8]; %nos dan un intervalo
fx=atan(x);
px=polyval(c(end:-1:1),x);
figure
plot(x,abs(px-fx)) %y nos dicen que hagamos la grafica
%Forma general
xi= [-1 0 1 2]'
yi= [-2 -1 0 3]'
H=[xi.^0, xi.^1, xi.^2]
C=inv(H)*yi %coeficientes de la funcion interpoladora
XX=[-1:0.01:1]
yy=c(1) ...xx;
    b_x=[xx.^0;xx.^1; xx.^2] ->IMPORTANTE LOS ; ENTRE ARGUMENTOS
P_X=C'*b_x
plot(xi,yi,'ro',XX,P_x, 'g')
y= ¿?-> te lo pueden pedir haces igual que la funcion que te den pero con
A=c(1),B=c(2) -> sin declaralos SOLO SI TE PIDEN EVALUARLA , LUEGO LA PINTAS
%PARA EVALUAR POLINOMIOS LO PODEMOS HACER:
c=H\y; xx =[0.5:0.01:5]
----> pp=polyval(c,xx); <---
%SI ME DAN BASE Y NO POLINOMIO
H = [exp(-xi) xi cos(xi) xi.^2]; %->Me han dado la base {e^-x,x,cos(x),x^2)
C=inv(H)*yi
xx = [-0.5:0.01:4.5];
yy = C(1)*exp(-xx) + C(2) *xx +C(3)*cos(xx)+C(4)*xx.^2;
%Newton
xi= [-1 0 1 2]'
yi= [-2 -1 0 3]'
dd=dif_div(xi,fi) %habiendo añadido el script de newton en la misma carpeta,te
va a dar unos valores
%que van a ser la matriz PP=[]
dd = [1 -8 40 -106.67] -> nos sale de haber echo dd=dif_div(xi,fi)
XX = [-0.15:0.01:0.9]
P_x = dd(1) + dd(2).*(XX-0) + dd(3).*(XX-0).*(XX-0.25) + dd(4).*(XX-0).*(XX-
0.25).*(XX-0.5) //igual que en los otros casos pero con dd y habiendo escrito el
script
%cuatro sumandos, porque hay 4 elementos
plot(xi,fi,'ro',XX,P_x,'b')
PP=dd(xi,yi,'go',XX,PP,'r')
%Hermite
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
    fprintf('Usando %d nodos,el error de interpolacion es = %.18f
    \n',length(xi),ERROR_INTERPOLACION);
    fprintf('Usando %d nodos,el error maximo de los nodos es = %.18f
    \n',length(xi),ERROR_REDONDEO);
    %plot(xi,ERROR_REDONDEO,'ro');
    %descomentar linea anterior para el ultimo apartado
end