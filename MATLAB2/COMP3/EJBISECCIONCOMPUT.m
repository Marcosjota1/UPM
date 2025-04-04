clear,clc
x=1
fun(x) = exp(x) - cos(x) -2;
s=biseccion(@fun,0.5,1.5,40)

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
 %%%%%%%%%%%%%%% FUNCIONES %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
fun(x) = exp(x) - cos(x) -2;

function s=biseccion(fun,a,b,N) % Método de la bisección.
% Argumentos Entrada: fun puntero a la función; [a,b] intervalo donde está la solución
% N: número máximo de iteraciones
% Argumento Salida: s es la aproximación de la raiz
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