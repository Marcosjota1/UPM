clear,clc
%1)
xx = -2:0.01:2;
yy = exp(xx) - cos(xx)-2;
plot(xx,xx*0,'r',xx,yy,'k');

f0=exp(0.5) - cos(0.5)-2;
f1=exp(1.5) - cos(1.5)-2;
f0*f1
 %2)

s=biseccion(@fun,0.5,1.5,40)



%3)
clear,clc
x=zeros(1,25);
x0=0.5;
for k=1:25
   x(k)=log(cos(x0)+2);
   fprintf("Iteración: %2d, Valor calculado = %.12f\n",k,x(k))
   x0=x(k);
end
s1 = x(end);
error = abs((s1-x));
semilogy(0:24,error,'bo:'); 

cif_correct=floor(-log10(abs(s1-x))); 

k = abs(error(2)) / abs(error(1));

%4)
error=100.0;                                % Control.
iteracion=1;                                % Contador iteraciones, iniciar
x0=0.5;                                     % Valor inicial
while (error>1e-12)   % Criterio parada
    x1=x0-(exp(x0)-cos(x0)-2)./(exp(x0)+sin(x0)); % Método iterativo
    error=abs(x1-x0); %Estimación error
    ncif = floor(-log10(error));
    fprintf('Iteracion %d Valor calculado %.12f Error %e Cifras %d\n', iteracion,x1,error,ncif) %Imprimir soluciones aprox,...
    iteracion=iteracion+1;
    x0=x1;
end















%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
 %%%%%%%%%%%%%%% FUNCIONES %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
 function f = fun(x)
    exp(x) - cos(x)-2;
 end

function s=biseccion(fun,a,b,N) % Método de la bisección.
% Argumentos Entrada: fun puntero a la función; [a,b] intervalo donde está la solución
% N: número máximo de iteraciones
% Argumento Salida: s es la aproximación de la raiz
fa=fun(a);fb=fun(b); % Evalúo la función f en a y en b.
if fa*fb > 0
 fprintf('Método no aplicable en intervalo [a,b]\n')
end
for i=1:N
 c=(a+b)/2
 fc=fun(c); %evalúo la función en punto medio intervalo
 if fa*fc < 0
 b=c; %fb=fc; (no es necesario)
 else
 a=c;
 fa=fc;
 end

end
s=a % pueder ser s=b o s=(a+b)/2,
fprintf('La raiz aproximada es %12.8f\n',s)
end