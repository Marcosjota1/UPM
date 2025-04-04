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

% 3
% (Ajuste de datos con restricciones) Se trata ahora de simular la sección de la bolsa por un polinomio p3(x)
% de grado 2 que pasa exactamente por el punto inicial (0, 18.90) y que ajuste mejor posible el resto de datos
% de la Tabla 2 (en el sentido mínimos cuadrados):
% - Dar la expresión de la matriz (H3) y el término independiente del sistema lineal sobredeterminado que
% hay que resolver para determinar dicha función y la expresión de dicho polinomio.
% - Sobre la gráfica del apartado 3, añadir la gráfica de p3, añadiendo el punto (0, 18.90) con o rojo.
% - Calcular el vector residuo R3 y su norma E3. Comparar los resultados de E2 y E3. 

% p3(x) = a + bx + cx^2
% p3(0) = a = 18.9
% p3(x) = 18.9 + bx + cx^2 -> p3(x) - 18.9 = bx + cx^2
xaux=-0.5:0.01:2.5;
xx=[0 1 2]'; 
fxx=[18.90 14.50 17.90]';
H = [xx.^0 xx.^1 xx.^2];
b = fxx;
c3 = H\b;


p3 = 18.9 + c3(1).*xaux + c3(2).*xaux.^2;
hold on, plot(0,18.9,'ro',xaux,p3,'k'), hold off,

pp3 = 18.9 + c3(1).*xx + c3(2).*xx.^2;
R3 = abs(pp3-fxx);
E3 = norm(R3);

% Tal y como era de esperar en este caso (E3) el error es mayor, ya que al
% tener que pasar obligatoriamente por el punto (0,18.9), entonces queda un
% parámetro menos de libertad que en el caso anterior, provocando un error
% global mayor. 


% %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% %

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

% 6. (Ajuste datos con restricción) Se ajustan los datos de la tabla por un polinomio de grado 2 con la condición
% de que p’(0)=1. Dar la matriz H y el vector término independiente B del sistema lineal sobredeterminado que
% resulta. ¿El error que producirá el ajuste es menor o mayor que el calculado en el apartado 2? Justificarlo y
% comprobarlo calculando el error del ajuste en este caso.

% p(x) = a + bx + cx^2 
% p'(x) = 0a + 1b + 2xc
% p'(0) = 0a + 1b + 0c = 1 -> b = 1
% Luego, aplicando la restricción adicional nos queda p(x) = a + x + cx^2
% Para resolver los coeficientes debemos despejar p(x) - x = a + cx^2
H = [xi.^0 xi.^2];
B = yi - xi;
c = H\B;

% En este caso, al tener un grado menos de libertad en comparación con el
% apartado 2, entonces el error global será peor que en dicho apartado.

res = abs(H*c - B);
E = sum(res.^2);

% Lo comprobamos y observamos que el error obtenido en este caso es de
% 9.633 frente al error obtenido en el apartado 2 (3.8836); tal y como era
% de esperar.

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

%EJERCICIO 6
xi=[0 5 1 2 4]';
yi=[0 0 1 2 1]';





















































