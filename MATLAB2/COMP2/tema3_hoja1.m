clc,clear
% Ejercicio 1

% Apartado 1.1
xi = (0:2)';
yi = [18.9 14.5 17.9]';

H1 = [xi.^0 xi xi.^2]; % La dimensión de H1 es de 3x3
c1 = H1\yi;

% Apartado 1.2
p = c1(1) + c1(2)*1.35 + c1(3)*1.35^2;
% p1(1.35) = 14.8027

% Apartado 2.1
xx = (0:0.2:2)';
fxx = [18.90 17.90 17.10 16.30 15.20 14.50 15.30 15.90 16.30 17.30 17.90]';

H2 = [xx.^0 xx xx.^2]; % La dimensión de H2 es de 11x3
c2 = H2\fxx;

% Apartado 2.2
x_aux = -0.5:0.01:2.5;
p1 = c1(1) + c1(2).*x_aux + c1(3).*x_aux.^2;
p2 = c2(1) + c2(2).*x_aux + c2(3).*x_aux.^2;

plot(x_aux,p1,'b',x_aux,p2,'r',xx,fxx,'*g')

% Apartado 2.3
p1_14 = c1(1) + c1(2)*1.4 + c1(3)*1.4^2;
p2_14 = c2(1) + c2(2)*1.4 + c2(3)*1.4^2;

erel_p1 = abs(15.9 - p1_14)/abs(15.9);
erel_p2 = abs(15.9 - p2_14)/abs(15.9);

ncif_p1 = round(-log10(erel_p1))
ncif_p2 = round(-log10(erel_p2))

% Para p1(1.4) obtenemos 14.9240 con una única cifra significativa de
% precisión correcta; mientras que para p2(1.4) obtenemos 15.5902 con dos
% cifras significativas de precisión correctas.

% Apartado 2.4
pp1 = c1(1) + c1(2).*xx + c1(3).*xx.^2;
pp2 = c2(1) + c2(2).*xx + c2(3).*xx.^2;

R1 = abs(pp1-fxx);
R2 = abs(pp2-fxx);

E1 = norm(R1);
E2 = norm(R2);

% El máximo error obtenido ha sido 0.9760 en p1 para x = 0.6 y 1.4
% El máximo error obtenido ha sido 0.7352 en p2 para x = 1

% Apartado 3
% p3(x) = a + bx + cx^2
% p3(0) = a = 18.9
% p3(x) = 18.9 + bx + cx^2 -> p3(x) - 18.9 = bx + cx^2
H3 = [xx(2:end) xx(2:end).^2];
b3 = fxx(2:end) - 18.9;
c3 = H3\b3;

p3 = 18.9 + c3(1).*x_aux + c3(2).*x_aux.^2;
hold on, plot(0,18.9,'ro',x_aux,p3,'k'), hold off,

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

xx = -2:0.01:3;

% Apartado 1
% Como tenemos 5 datos, entonces el grado adecuado del polinomio es 4
H = [xi.^0 xi xi.^2 xi.^3 xi.^4];
c = H\yi;

u1 = c(1) + c(2).*xx + c(3).*xx.^2 + c(4).*xx.^3 + c(5).*xx.^4;

plot(xi,yi,'r*',xx,u1,'r')

% Apartado 2
H = [xi.^0 xi xi.^2];
c = H\yi;

u2 = c(1) + c(2).*xx + c(3).*xx.^2;

r2 = abs(H*c - yi);
E2 = sum(r2.^2);

% El mayor error es 1.4906 y se produce para en el punto (2,0)

% Apartado 3
H = [xi.^2 sin(xi)];
c = H\yi;

a = c(1); b = c(2);
fprintf('a = %f, b = %f\n',a,b)

u3 = a.*xx.^2 + b.*sin(xx);

r3 = abs(H*c - yi);
E3 = sum(r3.^2);

% El mayor error es 1.2131 y se produce para en el punto (-2,0)

% Apartado 4
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

% Apartado 6
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
% Se desea construir una autopista que comunique las poblaciones A,B,C,D y E cuyas coordenadas (xi,yi) se
% incluyen en la tabla. La vía debe pasar exactamente por las dos poblaciones principales A y B y pasar los
% más cerca posible del resto de poblaciones (sentido mínimos cuadrados). Por razones técnicas, el trazado en
% planta de la vía debe tener la forma de un polinomio de grado 3. 
% 1. ¿Cuántos parámetros libres tendrá un polinomio de grado 3 que pasa por A y B? Comprobar que la familia
% de polinomios de grado 3 que pasan por A y B admiten la forma u(x)=ax(-5+x)+bx(-25+x2 ). 
%p(x) = cx(x-5)+dx(x^2-25)
%DATOS:
%2
% Calcular los coeficientes del polinomio anterior que pasa lo más cerca posible (sentido mínimos
% cuadrados) de C, D y E.
%  - Calcular las distancias 1 2 d3 d ,d , (di=|u(xi)-yi|, teniendo en cuenta que cada unidad en nuestro "plano"
% representa 10 km en la realidad).
xi=[1,2,4]';
yi=[1,2,1]';
H=[xi.*(xi-5),xi.*((xi.^2)-25)];
b=yi;
c= H\b

res = abs(H*c-yi); %Nº reiduos = distancia 
err=norm(res); %Hces el error para comprobar que despues en el apartado con los pesos el error no varia 

% 3. Pensar el problema anterior, ahora teniendo en cuenta que el pueblo D tiene el triple de habitantes que C
% y que E (esto es, el dato D ‘pesa’ el triple que los datos C y de E). Plantear el problema de minimización
% resultante.
% - Calcular los coeficientes de la función resultante.
% - Si calculásemos ahora las distancias di, la relativa a la población D ¿sería mayor o menor que la del
% apartado anterior? 

wi=[1,3,1];
W=diag(wi);

c2=(sqrt(W)*H)\(sqrt(W)*yi);
res2= abs(H*c2-yi);
err2=norm(res);













