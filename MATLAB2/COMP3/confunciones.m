clear,clc
%VISUALIZAMOS SI EXISTE RAIZ EN EL INTERVALO
fun7(1)*fun7(2);

%PINTAMOS LA FUNCION
 xx=1:0.001:2;
 yy=fun7(xx);
 plot(xx,0*xx,'-k',xx,yy,'-r');

 %NEWTON 
 s=newton(@fun7,1.5,8)

 %Evaluamos valor obtenido
 fun7(1.2784645427610737)



 %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
 %%%%%%%%%%%%%%% FUNCIONES %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
 %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

 function [f fp]= fun7(x)
f = x-exp(-x)-1;
fp=1+exp(-x);
 end

 %NEWTON CON FUNCIONES
 function s=newton(fx,x0,N)
% Entrada puntero a fx nombre de la función (debe devolver f(x) y f'(x))
% x0 punto inicial
% N N max iteraciones.
 % Inicio
for k=1:N,
 [f fp]=fun7(x0); % Pido valores de f(x) y f'(x)
 if f==0, break; end % Ya estoy en la raiz
 x = x0-f/fp; % Iteracion de Newton.
 e=abs(x-x0); % Estimación del error en cada iteración.
 x0=x;
 fprintf('k: %d -> x_k:%.16f e_k:%5.2e\n',k,x,e); % Vuelco valores.
 end
 s = x; % Devuelvo último término de la sucesión.
 end

%NEWTON WHILE CON FUNCIONES
function s=newtonwhile(fun,x0,N,tol)
% Entrada fun nombre de la función (debe devolver f(x) y f'(x))
% x0 punto inicial, N N max iteraciones, tol (tolerancia en relación error)
if nargin==3, tol=1e-8; end % Si no hay 4º argumento hacer tol =1e-8
e=10; iter=1; % Inicializar
while ( (e>tol)&(iter<=N))
 [f fp]=fun(x0); % Pido valores de f(x) y f'(x)
 x1 = x0-f/fp; % Iteracion de Newton.
 e=abs(x1-x0); % aproximacion error (en~abs(xn+1-xn))
 fprintf('iter: %2d x: %.16f error:%.2e \n',iter,x1,e);
 x0=x1;
 iter=iter+1;
end
s = x1; % Devuelvo último término de la sucesión.
end 

