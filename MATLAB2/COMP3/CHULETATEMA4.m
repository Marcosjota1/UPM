

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%%%%%%%%%%%%%%% MÉTODO DE NEWTON RAPHSON %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% x0 inicial
% xk+1 = xk - f(xk)/f'(xk)
% Aplicar el método de Newton iterando tres veces para calcular una
% raiz de la ecuación f(x)= 2-exp(x)=0 en el intervalo [0,1] .
x=0; 
error=zeros(1,6); %Inicializar err a ceros, para despues poder completar
for k=1:6,
x=((x-1)*exp(x)+2)/exp(x);
e=abs(x-log(2)); %conocemos la solución s=log(2)
fprintf('k: %d x_k: %.13f e_k %.2e \n',k,x,e)
error(k)=e;  %guardo errores en vector error
end
cif=floor(-log10(error/log(2)));
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%%%%%%%%%%%%%%%%%%%% ESTUDIO DE LA CONVERGENCIA %%%%%%%%%%%%%%%%%%%%%%%%%%%

x=x0; % Inicio
for k=1:N,
[f fp]=feval(fx,x); % Pido valores de f(x) y f'(x)
if f==0, break; end % Ya estoy en la raiz
x = x-f/fp; % Iteracion de Newton.
fprintf('k %d -> %.16f\n',k,x); % Volcar valores
end
s = x; % Devuelvo último término de la sucesión.
end

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% Considerando la funcion:  f(x)= x^2 -exp(-x ) -1
% Estudio de la convergencia si =1:5:
% Realizar 10 iteraciones, calcular soluciones aproximadas y estimaciones error:
x=1.5;
for k=1:10
x1=(x^2+(x+1)*exp(-x)+1)/(2*x+exp(-x)); % soluciones aproximadas
e=abs(x1-x); % estimación error en iteración anterior
fprintf('k: %d Sol_k: %.15f Error_(k-1): %0.2e \n', k,x1,e)
x=x1;
end





%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%  ORDEN DE CONVERGENCIA 
%si te piden la k , coges primer error y segundo 
% en+1 = ken -> k = en+1 / en
k = abs(ek(2)) / abs(ek(1)); %Calcular k, con el error+1/error+0
ganaciasiteracion = -log10(k(end)) %Iteraciones para ganar cifra decimal
it_10= 10/ganaciasiteracion %iteracion que se alcanzan 10 cif decimales

%K DE NEWTON 
% en+1 = k en.^2 -> k = en+1 / en.^2
k = abs(error(2)) / abs(error(1).^2);








%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%EJERCICIO 1
%1A)
%1. Comprobar gráficamente y verificar analíticamente que dicha función tiene una raíz en el intervalo [1, 2].
% considerando la función  f1=x.^2-exp(-x)-1;

 x = 1:0.01:2;
 f1=x.^2-exp(-x)-1;
 plot(x,x*0,'r',x,f1,'k'); %Tiene raiz al tener un valor positivo y otro negativo.Segun Bolzano 
 
 f1 = 1-exp(-1)-1
 f2 = 2.^2-exp(-2)-1
 esRaiz = f1*f2 < 0


%1B) Aplicar el método de Newton para calcular dicha solución tomando como valor inicial el punto medio del
% intervalo e iterando 7 veces. El código empleado debe proporcionar (comando fprintf): el número de
% iteración k (%d), la solución aproximada x_k con 16 decimales (%.16f), una estimación del error e_k
%  ( %0.2e) y el nº de cifras decimales correctas cif_dec(%d) para cada iteración. 

 xx = 1:0.01:2;
 yy = funex(xx);
 plot(xx,xx*0,'r',xx,yy,'k');

 x0 = 1.5;
 N=7;
 

 for k = 1:N
     [f fp] = funex (x0);
     if f==0 break;
     end;
     x = x0 - f/fp;
     e = abs (x-x0);
     cif =floor(-log10(e));
     fprintf('k: %d x_k: %.16f e_k: %0.2e cif_dec: %d \n',k,x,e,cif)
     x0=x;
 end
 %OPCION SIN FUNEX
 for k = 1:N
     x = x0 - (x0.^2-exp(-x0)-1)/(2*x0+exp(-x0));
     e = abs (x-x0);
     cif =floor(-log10(e));
     fprintf('k: %d x_k: %.16f e_k: %0.2e cif_dec: %d \n',k,x,e,cif)
     x0=x;
 end
 %3. ¿Orden de convergencia?: 
 %A la vista de los resultados obtenidos en el apartado anterior, se observa que el nº de cifras decimales
%significativas se duplica (al menos) en cada iteración, esto es los errores en dos iteraciones
%consecutivas verifican ek+1 = k ek² . Podemos concluir por tanto que el método es de orden 2 aplicado
%al cálculo de la solución pedida, como se ha estudiado en teoría.

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%%%%%%%%%%%%%%%%%%EJERCICIO 2 %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% Ejemplo: Resolver la ecuación f(x)= x^3-x^2-x-1=0 en [1.5,2] usando los métodos dados.
% Estudiar convergencia y velocidad convergencia (si procede), sabiendo que la solución es s=1.83928675521416
% 
% 1. Implementar la aplicación de los métodos 1 y 2 para que iteren 10 veces y se guarden los errores de cada
% iteración en un vector (error1 y error2) y en cada iteración se muestren los resultados (fprintf) con el
% siguiente formato:
% 1)Número de iteración (%2d).
% 2)Estimación de s con 16 decimales (%.16f)
% 3)Error relativo en notación científica con 2 decimales (%.2e)
% - En cada caso calcular el vector de cifras decimales correctas (cif1 y cif2) ¿Cuántas cifras decimales correctas 
% proporciona la iteración final de cada método? ¿Cuántas iteraciones son necesarias paraalcanzar la precisión de la máquina con el método de Newton?
% - Representar gráficamente (semilogy) los vectores de errores relativos obtenidos en cada iteración.
s=1.839286755214161;
n=10; 
x0=1.5; %Valores iniciales de donde te pidan evaluarla
y0=1.5; %Valor inicial metodo 2
e_rel1=zeros(1,n);
e_rel2=zeros(1,n);
%Método 1. Metodo Newton 
for k=1:n
    x= x0- ((x0^3-x0^2-x0-1) / (3*x0^2- 2*x0-1));
    e_abs1(k)=abs(x-s);
    e_rel1(k)=abs((x-s)/s);
    fprintf('iter:%2d x:%.16f e_rel1:%2e\n',k,x,e_rel1(k));
    x0=x;
end
cif_correct1=floor(-log10(e_abs1))
cif_sig1=floor(-log10(e_rel1))
%Método 2
x0=1.5;
for k=1:n
    y= (x0^2+x0+1)^(1/3);
    e_abs2(k)=abs(y-s);
    e_rel2(k)=abs((y-s)/s);
    fprintf('iter:%2d y:%.16f e_rel2:%2e\n',k,y,e_rel2(k));
    x0=y;
end
cif_correct2=floor(-log10(e_abs2));
cif_sig2=floor(-log10(e_rel2));
k=1:n;
semilogy(k,e_rel1, 'r', k, e_rel2, 'g');

% 2. Orden de convergencia:
% - ¿Qué tipo de convergencia proporciona el método 2? En una convergencia lineal se verificaDeterminar el valor de la constante K 
%     a partir de las estimaciones del error obtenidas y usarlo para estimar
% cuántas iteraciones son necesarias para ganar una cifra decimal ¿En qué iteración se alcanzarían 10cifras decimales correctas en el método 2?
% - Como se aprecia en los resultados y como sabemos el método de Newton tiene convergencia cuadráticaCalcular una estimación de K.

% orden convergencia: k1,k2
k2=e_abs2(2:end)./e_abs2(1:end-1) %0.46
gan_iter=-log10(k2(end)) %0.3363
it_10=10/gan_iter %29.73

% k DE NEWTON
k1=e_abs1(2:end)./(e_abs1(1:end-1).^2) %~ 4.5 10^15



%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%%%%%%%%%%%%%%%%%%%%%%EJERCICIO 3%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% a)Realizar una gráfica de la función f(x) en el intervalo [-2, 2]. 
% Dar unintervalo aproximado con una longitud máxima de 1 donde se encuentre
% la raíz a partir de la gráfica. Demostrar analíticamente que en dicho 
% intervaloexiste al menos una raíz. (LOCALIZAR RAICES. Teorema de Bolzano)
% Sea f(x)=x2-exp(-x).
% Pintamos la función en [-2,2]:
x=-2:0.01:2;
fx=x.^2-exp(-x);
plot(x,fx,'b',x,fx*0,'g')
        %fx*0 para bibujar linea horizontal y comprobar que tiene valores
       %negataivos y positivo (Teorema Bolzano)
% Vemos en la gráfica que hay una raiz en el intervalo [0,1] y confirmamos(Teorema Bolzano)
% que tiene al menos una raiz en dicho intervalo,

%evaluando la función (continua) en los extremos del intervalo y viendo quetoma signos distintos:
f0=0.^2-exp(-0);
f1=1.^2-exp(-1);
f0*f1

%b)Metodod iterativo 1Implementar y ejecutar el siguiente método para encontrar la raíz de f(x):
% =sqrt(exp(-x0)
% • El método deberá iterar hasta que el error < 1e-10
% • En cada iteración imprimir: número de iteración, la raíz obtenida y el error (con el
% formato 'Iter %d Sol %.15f Error %0.2e\n‘)

error=100.0;                                % Control.
iteracion=1;                                % Contador iteraciones, iniciar
x0=1.0;                                     % Valor inicial
while (error>1e-10)   % Criterio parada
    x1=sqrt(exp(-x0)); % Método iterativo
    error=abs(x1-x0); %Estimación error
    fprintf('Iter %d Sol %.15f Error %0.2e\n', iteracion,x1,error) %Imprimir soluciones aprox,...
    iteracion=iteracion+1;
    x0=x1;
end
%PRECISION PEDIDA = Nº MAX DE ITERACIONES
s=x0

%c) Metodo iterativo 2 (Metodod de Newton R
%  Implementar y ejecutar el siguiente método para encontrar la raíz de f(x): x-((x^2-exp(-x)) / (2*x+exp(-x)));
%  • El método deberá iterar hasta que el error < 1e-..tomando comosel valor final obtenido en el apartado anterior.
% • En cada iteración imprimir: número de iteración, la raíz obtenida y el error (con el
% formato 'Iter %d Sol %.15f Error %e\n‘)

%PRECISION PEDIDA = Nº MAX DE ITERACIONES
%OPCION ROI 
z0=0.5;
x0=1.0;
it=1;
error = abs(x0-z0);
while(error>1e-10)
    [f,fp]=funex(x0);
    z1=z0-(z0^2-exp(-z0))/fp;
    x1=x0-f/fp;
    error=abs(x1-z1);
    fprintf('Iter %d Sol %.15f Error %.2e\n',it,x1,error)
    it =it+1;
    x0=x1;
    z0=z1;
end
%SIN FUNEX OPCION MIA
z=0.5;x=1.0;iteracion=0;error=100.0;
while(error>1e-10)
	iteracion=iteracion+1;
	z=z-((z^2-exp(-z)) / (2*x+exp(-x)));
	x=x-((x^2-exp(-x)) / (2*x+exp(-x)));
	error=abs(z-x);
	fprintf('Iter %d Sol %.15f Error %e\n', iteracion,x,error)
end


%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%%%%%%%%%%%%%%%%%%%%%%EJERCICIO 4%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%Aplicar metodo newton para : fx = sinx -1 -> s = pi/2 y fx= x.²-2 -> s = sqrt2
%Representar gráficamente las funciones en un intervalo que contenga a las raíces y ver que en el primer
%caso se trata de una raíz doble y en el segundo de una raíz simple.
xx=0:0.01:2;
yy = sin(xx)-1;
zz = xx.^2-2;
 plot(xx,xx*0,'r',xx,yy,'k',xx,zz,'b');

%b) Aplicar el método de Newton durante 10 iteraciones
%en los casos anteriores (en los que conocemos el valor de la raiz) y calcular el nº de decimales exactos.
%Mostrar los resultados del valor aproximado, del error y del nº de decimales exactos en cada iteración
%(comando fprintf). Comentar los resultados y estudiar la velocidad de convergencia del método en cada
%caso.
x=1;
for k=1:10
x= x-(sin(x)-1)/(cos(x));
e1=abs(x-pi/2);
cif1=floor(-log10(e1));
fprintf('iter:%2d x:%.16f e_abs:%e cif1 %2d \n',k,x,e1,cif1);
end

y=1;
for k=1:10
y= y- (y^2-2) / (2*y);
e2=abs(y-sqrt(2));
cif2=floor(-log10(e2));
fprintf('iter:%2d x:%.16f e_abs:%e cif2 %2d \n',k,y,e2,cif2);
end



%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%%%%%%%%%%%%%%%%%%%%%%EJERCICIO 8 %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
x0=[-0.5 1 4];
xx=-1:0.01:5;
f=exp(xx)-3*xx.^2;
plot(xx,f,'r',xx,xx*0)
k=0;e=100;
while (e>1e-13)
k=k+1;
x1=x0-(exp(x0)-3*x0.^2)./(exp(x0)-6*x0);
e=abs(x1-x0);
fprintf('k:%d s1:%.13f s2:%.13f s3:%.13f e1:%.2e e2:%.2e e3:%.2e \n',k,x,e)
x0=x1;
end
[m,n]=size(error);
K=zeros(m-2,n) %obs. La última (para m-1) da valores muy pequeños
format long
for k=1:m-2
K(k,:)=error(k+1,:)./error(k,:).^2;
end




%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%%%%%%%%%%%%%%%%%%%%%%EJERCICIO 6%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

s=biseccion(x.^2-exp(-x),0,1,10)
	  %funcion,intervalo,iteraciones







%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

%Se quiere calcular la solución que la ecuación x-log(x)=2 tiene en el
%intervalo [3,4]. 
%Comprobar que la función f(x)=x-log(x)-2 tiene una raíz en el intervalo [3,4]. 
x=0.1:0.1:5;
fx=x-log(x)-2;
plot(x,fx,'.b',x,0,'.r')

x=3;
f3=x-log(x)-2,
x=4;
f4=x-log(x)-2,
f3*f4 %<0 por lo que es continua

%Programar un bucle que aplique 7 iteraciones del método de Newton para
%estimar dicha raíz partiendo del valor inicial x0=3. Para ello crear un vector
%x=zeros(8,1) con x(1)=x0=3 y guardar en él los resultados de las iteraciones.
%Sea s=x(end) ¿es s la raíz de f(x)? Comprobarlo. 

x=zeros(8,1);
n=7;
x0=3;
x(1)=x0;
for k=1:n
   x(k+1)=x(k)*(1+log(x(k)))/(x(k)-1),
end
%S es la raiz de fx

%A partir del vector x, calcular el vector Erel con los errores relativos (de cada
%iteración) con respecto a s. Calcular el vector Ncifras que contiene el nº de cifras
%significativas de precisión estimadas para cada iteración. 
subplot(131);plot(x,'bo');
s=x(end),fs=s-log(s)-2

%Dibujar los vectores x, Erel y Ncifras respecto del nº de iteración en tres
%gráficas independientes usando en cada caso la escala adecuada. 

Erel=abs(x-s)/abs(s);
Ncifras=floor(-log10(Erel)) % En cada iteración se duplica el número de cifras y por tanto la convergencia es cuadrática. 
subplot(132);semilogy(Erel,'bo');
subplot(133);plot(Ncifras,'ro')

%%%%%%%%%%%%%%% SIMULACRO JULIO 2020 %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%a) Implementar un bucle for que calcule 20 iteraciones y las guarde en un vector x , se dispone de :
s = sqrt(2);
x = ones (1,21);
for n= 1:20
    x(n+1) = (2+x(n)) / (1+x(n));
end

%Cuanto difiere la ultima iteración ?
dif = abs(s-x(21))

%b) Modificar script metiendo num iteracion, estimacion con 15 dec, y err rel
x = zeros (1,21);
for n= 1:20
    x(n+1) = (2+x(n)) / (1+x(n));
    erel = abs ((s-x(n+1)./s));
    fprintf("Iteración: %2d, xn = %.15f, Erel = %.2e\n",n,x(n+1),erel)
end

%c) A partir del vector x calcular erel de los valores de x respecto a s y
%dibujar la grafica en los ejes adecuados
Erel = abs((s-x)./s);
semilogy(0:20,Erel,'r*')

%Cifras de precision correctas ?
cif_correct=floor(-log10(abs(s-x)));
cif_sig=floor(-log10(Erel));

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%%%%%%%%%%%%% DICIEMBRE 2018 %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%Aplicar metodo newton raphson para la siguiente funcion x3 + x2 - 8x -12,
%realizando 5 iter, empezando en x0=-2.5 y en 4, Explicar convergencia
clear,clc
x=zeros(1,6);
x(1)=-2.5;
for n= 1:5
    x(n+1)=x(n)-((x(n).^3+x(n).^2-8*x(n)-12)./(3*x(n)^2 + 2*x(n) - 8));
end
s1 = x(end);
erel = abs((s1-x)./s1);
semilogy(0:5,erel,'r*'); %LINEAL

%Para x=4

x=zeros(1,6);
x(1)=4;
for n= 1:5
    x(n+1)=x(n)-((x(n).^3+x(n).^2-8*x(n)-12)./(3*x(n)^2 + 2*x(n) - 8));
end
s2 = x(end);
erel = abs((s2-x)./s2);
semilogy(0:5,erel,'r*'); %CUADRATICA, se multiplica y tarda muy poco en encontrar la sol exacta

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%%%%%%%%%%%%%%% DICIEMBRE 2021 A %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%Dada la funcion fx y el met iterativo x(n+1) = 1/2.*sqrt(10-x(n).^3

%a) demostrar bolzano de fx en [1,2]
xx = 1:0.01:2; % donde lo quiero pintar
ff = xx.^3 + 4.*xx.^2 - 10;

plot(xx,ff,'b', xx,ff*0,'k')

% Como solo corta una vez al eje x, entonces existe una raíz en el
% intervalo [1,2]. Lo demostramos analíticamente con el Teorema de Bolzano

% Tma Bolzano: f(1) * f(2) < 0
f1 = ff(1);
f2 = ff(end);

esRaiz = f1*f2 < 0; % Queda demostrado por el Tma Bolzano que existe, 
                    % al menos, una raíz en el intervalo [1,2] ya que 
                    % la función es continua y f(1) * f(2) < 0

%b) Aplicar metodo iterativo para calcular s tal que f(s)=0, iterando 20
%veces y x0=1.5 .hacer printf iteracion,raiz
x = zeros(1,21);
x(1)= 1.5;
for n=1:21
    x(n+1)= (1/2) * sqrt(10-x(n)^3);
    fprintf("Iteracion %d Raiz %.16f\n",n,x(n+1))
end

%c) Volver a aplicar con x0=1.5 y hasta que el error ek= xk+1-xk sea menor
%que 1e -12
iter = 1;
xk = zeros(1,50);
xk(1) = 1.5;
ek = zeros(1,50);
error = 100; % fuerzo a que entre la primera vez dentro del while

while abs(error) > 1e-12
    xk(iter+1) = (1/2) * sqrt(10-xk(iter)^3);
    
    ek(iter) = abs(xk(iter+1) - xk(iter));
    error = ek(iter);
    
    fprintf('Iteración %d Raíz %.16f Error estimado %e\n',iter-1,xk(iter),ek(iter))
    
    iter = iter + 1;
end

semilogy(0:40, ek(1:41), 'r*:')
%d) Dar una estimacion del valor k
% ek+1 = ken
k = abs(ek(2)) / abs(ek(1)); 

%  e) calcular el error real erk = xk-s de cada iteracion 
% Suponemos que s = xk(41) - valor obtenido en la ultima iteración antes de

s = xk(41); %El ultimo valor es el mas cercano al error, por lo que lo cogemos como raiz
er = abs(xk(1:41) - s);

hold on; semilogy(0:40, er, 'bo:'); hold off

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%%%%%%%%%%%%%%% DICIEMBRE 2021 B %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
 %a)Dado el metodod iterativo 2*x1(n)^3 + 7*x1(n)^2 + 14, ralizar 6
 %iteraciones
s = sqrt(2);

x1 = zeros(1,7);
x1(1) = 1.7;

e1 = zeros(1,7);
e1(1) = abs(s - x1(1));

fprintf("\nComenzando con x0 = 1.7\n");
for n = 1:6
    x1(n+1) = (2*x1(n)^3 + 7*x1(n)^2 + 14) / (3*x1(n)^2 + 14*x1(n) - 2);
    e1(n+1) = abs(s - x1(n+1));
    fprintf('xn = %.16f, en = %e\n',x1(n+1), e1(n+1))
end
% Como el error es cuadrático, entonces se cumple la segunda relación, es
% decir, e(n+1) ~= K e(n)^2 
%b)
x2 = zeros(1,7);
x2(1) = 1.4142;

e2 = zeros(1,7);
e2(1) = abs(s - x2(1));

fprintf("\nComenzando con x0 = 1.4142\n");
for n = 1:6
    x2(n+1) = x2(n) - (x2(n)^3 + 7*x2(n)^2 - 2*x2(n) - 14) / 23.7; %METODO ITERATIVO QUE TE DEN
    e2(n+1) = abs(s - x2(n+1));
    fprintf('xn = %.16f, en = %e\n',x2(n+1), e2(n+1))
end
% Ambos apartados convergen a la raíz de 2 ya que estamos partiendo de
% valores cercanos a dicho punto y una de las raíces de la función es esa.

% Si partimos de otros valores iniciales convergerían a otras posibles
% raíces de la función 

% La primera convergencia es cuadrática ya que entre cada par de
% iteraciones se duplica el número de cifras significativas de precisión
% correctas; la segunda convergencia se podría asemejar a la lineal ya que
% entre cada par de iteraciones se consiguen 2 cifras de precisión
% correctas más aproximadamente. 

% El segundo de los métodos es similar al método de Newton-Rapshon dando
% por hecho que el denominador es la derivada del numerador.

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%%%%%%%%%%%%%%% DICIEMBRE 2020 A %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% Apartado 1
xx = 1:0.01:2;
ff = xx.^3 + 1.6.*xx.^2 -8.16.*xx + 5.76;

plot(xx,ff,'b',xx,ff*0,'k')
% No se puede aplicar el Tma Bolzano, ya que como la raíz es doble y pasa
% por encima de la recta x, entonces no tengo ningún punto del intervalo
% que me verifique el Tma Bolzano; por lo que no podemos aplicar el método
% de la bisección. Ya que en dicho método debemos verificar en cada paso el
% Tma Bolzano. 

% Apartado 2
a = 1.6; b = -8.16; c = 5.76;

x = zeros(1,11);
x(1) = 1.5;

y = zeros(1,11);
y(1) = 1.5;

z = zeros(1,11);
z(1) = 1.5;

error1 = zeros(1,11);
error2 = zeros(1,11);
error3 = zeros(1,11);

for n = 1:10
    x(n+1) = x(n) - (x(n)^3 + a*x(n)^2 + b*x(n) + c) / (3*x(n)^2 + 2*a*x(n) + b);
    e1 = abs(x(n) - x(n+1));
    error1(n+1) = e1;
    
    y(n+1) = y(n) - 2 * (y(n)^3 + a*y(n)^2 + b*y(n) + c) / (3*y(n)^2 + 2*a*y(n) + b);
    e2 = abs(y(n) - y(n+1));
    error2(n+1) = e2;
    
    z(n+1) = (-a*z(n)^2 - b*z(n) - c)^(1/3);
    e3 = abs(z(n) - z(n+1));
    error3(n+1) = e3;
    
    fprintf('k:%d x:%.15f e1:%.2e y:%.15f e2:%.2e z:%.15f e3:%.2e\n',n,x(n+1),e1,y(n+1),e2,z(n+1),e3)
end

semilogy(0:10,error1,'r*:',0:10,error2,'bo:',0:10,error3,'gs:')

% El primer método y el último no convergen, mientras que el segundo
% de ellos sí que converge a la raíz en 3 iteraciones.

% El orden de convergencia del segundo método es cuadrático ya que entre
% cada par de iteraciones se duplica aproximadamente el número de cifras
% significativas de precisión correctas.

% Para obtener 3 cifras de precisión correctas con el primer método
% necesitamos 5 iteraciones; mientras que con el segundo necesitamos tan
% solo una iteración.

% Apartado 3 Aplicar el método de NEWTON
iter = 0;
x = 1.5;
e = 1;
cif = zeros(1,100);

while abs(e) >= 1e-15
    iter = iter+1;
    xant = x;
    x = x - (x^3 + 1.6*x^2 -8.16*x + 5.76) / (3*x^2 + 2*1.6*x -8.16);
    e = abs(xant-x);
    cif(iter) = round(-log10(e));
end

plot(1:10,cif(1:10),'r*:')

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%%%%%%%%%%%%%%% DICIEMBRE 2020 B %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
a = -3.59; b = -0.048; c = 6.9696;

% Apartado 1) BOLZANO indicar raices en los intervalos y su tipo
xx = -2:0.01:4;
ff = xx.^3 + a.*xx.^2 + b.*xx + c;

plot(xx,ff,'b',xx,ff*0,'k')

% Tenemos una raíz simple en el intervalo [-1.5, -0.5] y una raíz doble en
% el intervalo [2,3]

% Apartado 2) Implementar método newton hasta que le error sea menor que 10.^-15
% Para x0 = -1
iter = 0;
error = 1;
x0 = -1;

while error > 1e-15
    x1 = x0 - (x0^3 + a*x0^2 + b*x0 + c) / (3*x0^2 + 2*a*x0 + b);
    error = abs(x1 - x0); 
    iter = iter + 1;
    x0 = x1;
end

s1 = x0; % s1 = x1;

fprintf('s1 = %.15f, iters = %2d\n', s1, iter)

% Para x0 = 2
iter = 0;
error = 1;
x0 = 2;

while error > 1e-15
    x1 = x0 - (x0^3 + a*x0^2 + b*x0 + c) / (3*x0^2 + 2*a*x0 + b);
    error = abs(x1 - x0); 
    iter = iter + 1;
    x0 = x1;
end

s2 = x0; % s1 = x1;

fprintf('s2 = %.15f, iters = %2d\n', s2, iter)

% Podemos observar que en el primer caso solo necesita 4 iteraciones ya que
% al tratarse de una raíz simple el método de Newton calcula la raíz
% rápidamente; mientras que en el segundo caso necesita 40 iteraciones para
% alcanzar la raíz ya que es una raíz doble.

% Apartado 3
% Método 1: Newton-Raphson
x = zeros(1,11);
x(1) = -1;
er1 = zeros(1,11);
er1(1) = abs(s1 - x(1));

% Método 2: 
z = zeros(1,11);
z(1) = -1;
er2 = zeros(1,11);
er2(1) = abs(s1 - z(1));

for n = 1:10
    x(n+1) = x(n) - (x(n)^3 + a*x(n)^2 + b*x(n) + c) / (3*x(n)^2 + 2*a*x(n) + b);
    er1(n+1) = abs(s1 - x(n+1));
    
    z(n+1) = -sqrt((-z(n)^3-b*z(n)-c)/a);
    er2(n+1) = abs(s1 - z(n+1));
    
    fprintf('k:%d x:%.15f e1:%.2e z:%.15f e2:%.2e\n',n,x(n+1),er1(n+1),z(n+1),er2(n+1))
end

semilogy(0:10,er1,'r*:',0:10,er2,'bo:')

% Podemos observar que el primer método converge muy rápido a la raíz de la
% función. Su convergencia es cuadrática ya que entre cada par de
% iteraciones se duplica aproximadamente el número de cifras significativas
% de precisión correctas. Necesitamos 2 iteraciones para alcanzar 3
% cifras significativas de precisión correctas.

% Podemos observar que el segundo método converge muy lentamente hacia la
% raíz no siendo capaz de acercarse en las 10 iteraciones realizadas. Su
% convergencia es lineal ya que entre cada par de iteraciones se reduce
% aproximadamente el error a la mitad (tarda aprox unas 3 iters en alcanzar
% una nueva cifra de precisión correcta). Necesitamos 5 iteraciones para
% alcanzar 3 cifras significativas de precisión correctas.

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%%%%%%%%%%%%%%%%      JULIO 2023        %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

% Apartado a) 
xx = -2:0.01:5;
ff = exp(xx) - xx.^3 - 2;

plot(xx,ff,'b',xx,xx*0,'k')

xx1 = [-1.5:0.01:-1];
ff1 = exp(xx1) - xx1.^3 - 2;
subplot(131), plot(xx1,ff1,'b',xx1,ff1*0,'k')

xx2 = [4.25:0.01:4.75];
ff2 = exp(xx2) - xx2.^3 - 2;
subplot(132), plot(xx2,ff2,'b',xx2,ff2*0,'k')

xxp1 = [3.5:0.01:4];
ffp1 = exp(xxp1) - 3.*xxp1.^2;
subplot(133), plot(xxp1,ffp1,'b',xxp1,ffp1*0,'k')

% Apartado b) 
v = zeros(1,11);
v(1) = -1;
for n = 1:10
    v(n+1) = (exp(v(n))*(v(n)-1)-2*v(n)^3+2)/(exp(v(n))-3*v(n)^2);
end

s = v(end);
fs = exp(s) - s.^3 - 2;
fprintf('s = %.12f f(s) = %.6e\n', s, fs);

Erel = abs(s - v)./abs(s);
Ncifras = round(-log10(Erel));

subplot(131), plot(0:10,v,'b')
subplot(132), semilogy(0:10,Erel,'bo:')
subplot(133), plot(0:10,Ncifras,'r*:')

% Necesitamos 5 iteraciones para alcanzar la precisión de la máquina. Como
% entre cada par de iteraciones el número de cifras significativas se
% duplica aprox., entonces la velocidad de convergencia es cuadrática.

% Apartado c) 
v = zeros(1,11);
v(1) = 3.5;
for n = 1:10
    v(n+1) = v(n) - (exp(v(n))-3*v(n)^2)/(exp(v(n))-6*v(n));
end

s = v(end);
%fs = exp(s) - 3*s.^2;
%fprintf('s = %.12f f(s) = %.6e\n', s, fs);

Erel = abs(s - v)./abs(s);
Ncifras = round(-log10(Erel));

subplot(131), plot(0:10,v,'b')
subplot(132), semilogy(0:10,Erel,'bo:')
subplot(133), plot(0:10,Ncifras,'r*:')

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%%%%%%%%%%%%%%%%      JULIO 2022        %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% Apartado 1)
x = zeros(1,9);
px = zeros(1,9);
x(1) = 1;

for n = 1:8
    x(n+1) = x(n) - (x(n)^3-x(n)-4)/(3*x(n)^2-1);
    px(n+1) = x(n)^3-x(n)-4;
    fprintf('xn = %18.16f p(xn) = %18.16f\n',x(n+1),px(n+1))
end

% Apartado 2) 
% Dado que entre cada par de iteraciones su imagen disminuye
% exponencialmente duplicando aprox el número de cifras significativas de
% precisión correctas, entonces su convergencia es cuadrática.

s = x(end); % tomamos la raíz como el último valor de x calculado
e6 = abs(x(7) - s);
% En este caso tenemos 11 cifras significativas de precisión correctas

% Apartado 3
xi = px(4:6)';
yi = x(4:6)';

H = [xi.^0 xi xi.^2];
b = yi;
c = H\b;

xx = px(4):0.01:px(6);
uu = c(1) + c(2).*xx + c(3).*xx.^2;

u0 = c(1);

ex = abs(s - x(7));
eu = abs(s - u0);

Ncifx = round(-log10(ex));
Ncifu = round(-log10(eu));

% Dado que para x6 obtenemos una precisión de 10 cifras significativas de
% precisión correctas; mientras que para u0 obtenemos una precisión de 4
% cifras significativas de precisión correctas, es mejor la estimación
% obtenida con x6. 


 %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
 %%%%%%%%%%%%%%% FUNCIONES %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
 %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

function[f,fp]=funex(x)  %evaluacion de la funcion y su derivada
    f = x.^2-exp(-x)-1;
    if nargout == 1, return; end
    fp = 2*x+exp(-x);
end

%-------------------------------------------------------------------------

function s = biseccion ( fx,a,b,N )
% Evaluamos la función fx (previamente creada) en a y en b:
fa=feval(fx,a);fb=feval(fx,b);
if fa*fb > 0
fprintf('La función no tiene raices simples en el intervalo [a,b]\n')
end
for i=1:N
c=(a+b)/2
fc=feval(fx,c);
if fa*fc < 0
b=c;
else
a=c;
fa=fc; % Ahorramos una evaluación en la siguiente iteración
end
end
s=a % s solución aproximada, podría ser s=b ó s=(a+b)/2,
fprintf('La raiz aproximada es %12.8f\n',s)
end

%-------------------------------------------------------------------------

