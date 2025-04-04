%TEMA 3
clear,clc
%EJERCICIO 1
% Se simula la sección de la bolsa de gas por una parábola dada por el polinomio p1(x) de grado 2 que interpola
% los datos de la tabla. Dar el vector c1 de coeficientes del polinomio p1(x): 

%1.1 Construir los vectores xi (nodos de interpolación) y fi (valores de la función que mide la profundidad a la
%que se encuentra gas en los nodos xi) y resolver el correspondiente sistema lineal asociado. Dar la expresión
%de la matriz H1 del sistema y el vector c1 solución de dicho sistema (con los coeficientes de p1(x)) ¿Qué
%dimensiones tiene H1?
xi=[0 1 2]'; 
fi=[18.90 14.50 17.90]'; 
H=[xi.^0 xi.^1 xi.^2];
b=fi;
c1=H\b;
%1.2 Utilizando la simulación parabólica anterior, dar una estimación de a qué profundidad se espera
%encontrar gas en la abcisa 1.35. 

p = c1(1) + c1(2).*1.35.^1+c1(3).*1.35.^2;
p1=c1(1)

% 2. (Técnica ajuste de datos) En una 2ª fase se recopilan más datos realizando perforaciones cada 0.2 hm
% en el intervalo [0,2] y midiendo a qué profundidad se encuentra gas. Las medidas obtenidas son las
% siguientes (Tabla 2) 
% Se simula la sección de la bolsa de gas por una parábola dada por el polinomio p2(x) de grado 2 que ajusta
% los datos anteriores (en sentido mínimos cuadrados):
xx=(0:0.2:2)';
fxx = [18.90 17.90 17.10 16.30 15.20 14.50 15.30 15.90 16.30 17.30 17.90]';

%2.1
% Si H2 es la matriz de coeficientes del sistema sobredeterminado resultante ¿qué dimensiones tiene H2?
% Dar el vector c2 de coeficientes del polinomio p2(x).
H2 = [xx.^0 xx xx.^2]; % La dimensión de H2 es de 11x3
c2 = H2\fxx;
%2.2
% Dibujar en una misma gráfica en el intervalo [-0.5,2.5] (usar como soporte x_aux=-0.5:0.01:2.5):
% - La parábola que simula la bolsa de gas del apartado 1 (polinomio p1(x)) en azul;
% - La parábola que simula la bolsa de gas del apartado 2 (polinomio p2(x)) en rojo;
% - Los puntos xx, fxx con las mediciones de la Tabla 2, en * verde. 

xaux=-0.5:0.01:2.5;

p1 = c1(1) + c1(2).*xaux + c1(3).*xaux.^2;
p2 = c2(1) + c2(2).*xaux + c2(3).*xaux.^2;

plot(xaux,p1,'b',xaux,p2,'r',xx,fxx,'*g')

%2.3
% Utilizando las simulaciones proporcionadas por p1(x) y p2(x) ¿a qué profundidades se espera encontrar
% gas en x=1.4? Conociendo el valor exacto en el que se ha encontrado gas en ese punto (Tabla 2) ¿cuántas
% cifras decimales proporcionan cada una de dichas simulaciones? 

p114 = c1(1) + c1(2).*1.4 + c1(3).*1.4.^2;
p214 = c2(1) + c2(2).*1.4 + c2(3).*1.4.^2;

erel_p1 = abs(15.9 - p114)/abs(15.9); %15.9 = valor tabla 1.4
erel_p2 = abs(15.9 - p214)/abs(15.9);

ncif_p1 = round(-log10(erel_p1))
ncif_p2 = round(-log10(erel_p2))

% Para p1(1.4) obtenemos 14.9240 con una única cifra significativa de
% precisión correcta; mientras que para p2(1.4) obtenemos 15.5902 con dos
% cifras significativas de precisión correctas.

%2.4
%. Calcular los vectores de residuos R1=abs(p1(xx)-fxx) y R2=abs(p2(xx)-fxx) y los errores E1=norm(R1) y
% E2=norm(R2) que producen las simulaciones pl(x) y p2(x), respectivamente, de la bolsa respecto de las
% mediciones realizadas (Tabla 2). Comentar los resultados.
% Determinar en cada caso, cuál es el error máximo y en qué posición se ha producido, atendiendo a la
% información disponible.

pp1 = c1(1) + c1(2).*xx + c1(3).*xx.^2;
pp2 = c2(1) + c2(2).*xx + c2(3).*xx.^2;

R1 = abs(pp1-fxx);
R2 = abs(pp2-fxx);

E1 = norm(R1);
E2 = norm(R2);

% El máximo error obtenido ha sido 0.9760 en p1 para x = 0.6 y 1.4
% El máximo error obtenido ha sido 0.7352 en p2 para x = 1

% Ejercicio 2
xi = [-2 -1 0 2 3]';
yi = [0 1 1.2 0 3.1]';

% 1. ¿De qué grado será el polinomio u1(x) que interpola los datos de la tabla?
% Representar gráficamente
% dicho polinomio en el intervalo [-2, 3] en rojo
% y los puntos donde se interpola con asteriscos rojos.

xx = -2:0.01:3;
H = [xi.^0 xi xi.^2 xi.^3 xi.^4];
c = H\yi;
u1 = c(1) + c(2).*xx + c(3).*xx.^2 + c(4).*xx.^3 + c(5).*xx.^4;
plot(xi,yi,'r*',xx,u1,'k')

% 2. Se quiere ajustar los datos de la tabla por un polinomio u2(x) de grado dos. Dar los coeficientes de dicho
% polinomio en la base {1, x, x^2}. Calcular el vector de residuos y el error que produce dicho ajuste ¿En qué
% punto el residuo es mayor y cuánto vale? 
H = [xi.^0 xi xi.^2];
c = H\yi;
u2 = c(1) + c(2).*xx + c(3).*xx.^2;

r2 = abs(H*c - yi);
E2 = sum(r2.^2);
% El mayor error es 1.4906 y se produce para en el punto (2,0)

% 3. Se quiere ajustar los datos de la tabla por una función del tipo 2 u x ax bsen x 3( ) ( )   . Dar los valores
% de a y b. Calcular el vector residuo (no mostrarlo) y el error. 
H = [xi.^2 sin(xi)];
c = H\yi;
a = c(1); b = c(2);
fprintf('a = %f, b = %f\n',a,b)

u3 = a.*xx.^2 + b.*sin(xx);
r3 = abs(H*c - yi);
E3 = sum(r3.^2);
% El mayor error es 1.2131 y se produce para en el punto (-2,0)

% 4. Calcular la función del tipo 3 4( ) cos( ) x u x Ae B x Cx    que ajusta los datos de la tabla. Dar los
% valores de A, B y C. Calcular el vector residuo (no mostrarlo) y el error.

H = [exp(xi) cos(xi) xi.^3];
c = H\yi;
A = c(1); B = c(2); C = c(3);
fprintf('A = %f, B = %f, C = %f\n',A,B,C)

u4 = A.*exp(xx) + B.*cos(xx) + C.*xx.^3;
r4 = abs(H*c - yi);
E4 = sum(r4.^2);
% El mayor error es 1.0018 y se produce para en el punto (2,0)

% Apartado 5
hold on, plot(xx,u2,'g',xx,u3,'b',xx,u4,'k'), hold off
% La que produce un peor error global es la función u3 ya que al tener tan
% solo dos params libres en comparación con las otras dos funciones de
% ajuste (donde ambas tienen 3 params para ajustar) produce un peor ajuste.

% La función del apartado 1 es la que produce un menor error, ya que al
% tratarse de un problema de interpolación, su error es nulo (igual a 0).

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%Ejercicio Tipico
xi= [-1 0 1 2]'
yi= [-2 -1 0 3]'
b= yi
H=[xi.^0, xi.^1, xi.^2]
c = H\b
A=c(1)
B=c(2)
r= yi-(H*c)
E=sum (r.^2)
xx=[-1:0.01:2]
p_x=c(1)+c(2)*xx+c(3)*xx.^2+c(4)*xx.^3+c(5)*xx.^4; % me pueden pedir polinomioque mejor ajusete
y=(construir la funcion, dond, hacer el cambio de variable con A=c(1)... etc ydonde poga xi, ponemos xx)
plot(xi,yi,'rs',XX,y,'b')
% Cond y discrepancias
fprintf('Cond de H1 = %.0f\nCond de H2 %.0f\n',cond(H1),cond(H2));
disc_1=yk-H1*c1, disc_2=yk-H2*c2,
%Primer ejercicio
H=[1 1 1 1 ; -1 1 0 2]
Hc=[1 1 1 1; -1 1 0 2]
b=[-2.05 0.05 -1.15 1.05]
c=Hc/b
r=(Hc*c)-b
E=sum(r.^2)
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%-----------RESIDUOS-GRAFICAS---------------------------------------------
%Datos a ajustar:
xi=[-1 0 1 2]';
fi=[-2 -1 0 3]';
%Ajuste por r(x):
H1=[xi.^0 xi.^1];
c1=H1\fi
%Ajuste por p(x):
H2=[xi.^0 xi.^2];
c2=H2\fi
%Ajuste por q(x):
H3=[xi.^0 xi.^1 xi.^2];
c3=H3\fi
% Residuos y errores, ajustará mejor la funcion cuyo error sea menor
R1=abs(fi-H1*c1);
R2=abs(fi-H2*c2);
R3=abs(fi-H3*c3);
e1=norm(R1)
e2=norm(R2)
e3=norm(R3)
% Gráficas
xx=-1:0.01:2;
p1=c1(1)+c1(2)*xx;
p2=c2(1)+c2(2)*xx.^2;
p3=c3(1)+c3(2)*xx+c3(3)*xx.^2
plot(xx,p1,'r', xx,p2,'b', xx,p3,'g',xi,fi,'*r')
%---------------------------OTRO IGUAL-------------------------------------
 xi=[-1 1 2 3 4]';
 yi=[4 2 1 1 2 ]';
 %p(xi)=2+c1x+o.5x2+c3x
 H=[xi xi.^3];
 b=yi-3-0.5*xi.^2  %B= termino independiente, restar a yi el otro 'Independiente' = 0.5x^2
 c=H\b

% R: Vector de residuos

R=abs(H*c-b); %abs(p(xi)-yi)
% E: Error
E=norm(R);

% v_ap: Valor aproximado en 1.2
a=1.2; v_ap= 3+c(1)*a +0.5*a^2+c(2)*a^3

% Vector auxiliar en intervalo [-1,4]
 v_aux=-1:0.1:4;

% p_aux = Valor del polinomio obtenido en v_aux:
p_aux=3+c(1)*v_aux+0.5*v_aux.^2+c(2)*v_aux.^3
plot(v_aux, p_aux,xi,yi,'*r')


