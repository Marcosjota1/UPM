clear,clc
%Aplicamos método de la bisección para calcular la solución con un error menor o igual que 10^(-10),
%iterando un nº máximo de 20 iteraciones
%[s,n]=biseccion2('fun',0,1,1e-10,20)
%Comprobar que s es efectivamente la raíz de f(x). ¿Cuál es el valor de f(s)?
%fs=fun(s)


 s=biseccion('fun1',0,1,100) 


%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
 %%%%%%%%%%%%%%% FUNCIONES %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
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

function [s,n]=biseccion2(fun,a,b,tol,N)
% Entrada: como en función anterior, se añade tol precisión deseada.
% N: nº max de iteraciones (opcional, por defecto 20)
% Salida : s, estimación de la raíz y n, número iteraciones realizadas.
s=NaN;n=NaN;
if nargin==4, N=20; end
fa=fun(a); fb=fun(b); % Evaluación de fx en extremos intervalo
if (fa*fb>0), fprintf('ERROR: Método no aplicable en intervalo [a,b]\n'); end
n=1;
while ( ((b-a)/2 > tol) & (n<=N) ) % Condiciones salida
s = (a+b)/2;
fs=fun(s); % Evalúa la función en la estimación de la raiz
if (fs*fa <0), b=s; fb=fs; else a=s; fa=fs; end
n=n+1; end
s = (a+b)/2; % Mejor hipótesis dado el intervalo final [a,b]
end


function[f,fp]=fun(x)  %evaluacion de la funcion y su derivada
    f = x.^2-exp(-x)-1;
    if nargout == 1, return; end
    fp = 2*x+exp(-x);
end

