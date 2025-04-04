% Interpolación de la función f(x)=sin(exp(x)) en un conjunto de puntos equiespaciados en el intervalo [-1,1].
% a) Interpolar f(x) por el polinomio de grado mínimo en 6 puntos equiespaciados (xi+1-xi=h) en el intervalo [-1,1]
% ¿Cuál es el grado del polinomio? Para ello hay que construir los vectores xi e yi y resolver el correspondiente
% problema de interpolación. Hacer una gráfica que muestre simultáneamente el polinomio obtenido (‘b’), la función f(x)
% (‘g’) y los datos del problema de interpolación (‘ro’) en el intervalo [-1:0.001:1]. Calcular cúal es el valor máximo del
% error de interpolación en los puntos xx=-1:0.01:1.
% NOTA: 1. En este caso: nº de puntos=6 -> h=(1-(-1))/(n-1) distancia entre puntos consecutivos. También se puede
% usar comando linspace (linspace(x1,x2,n) generates n points. The spacing between the points is (x2-x1)/(n-1)).
% 2. Se puede utilizar la funcion polNewton(xi,fi,xx) del Anexo.
% Datos:
 n=6; h=2/(n-1);
 xi=-1:h:1; % xi=linspce(-1,1,6);
 yi=sin(2.*exp(xi));
% Evaluación del polinomio (usando F. Newton) y de la función sobre una muestra xx de puntos en [-1,1]. Gráfica de la función y el polinomio:
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
% semilogy(2:40,maxerror(2:40)); 




















 

%%FUNCION ANEXO POL NEWTON%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
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



p=c(n+1) 
for k=n:-1:1, 
p = p .* x + c(k);
end
