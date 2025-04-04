%TEOREMA SAATY
%Para calcular m23 si no tienes m32
%   m21*m13=m23


%EJEMPLO DIAPOSITIVAS
clear
M=[1 1.5 2 4;0.75 1 1.5 3;0.5 0.75 1 2;0.25 0.33 0.5 1];
L=log(M);

%Indices i=j no aporta informacion, no los usamos
% i=1 j=2 -> v1/v2=m12=1.5
% Aplicarias para rellenaer por debajo diagonal (bucle -> i>j)
%log(wi)-log(wj)=log(mij)
% i=1 j=2 -> v1-v2=l12=1.5 -> 1 -1 0 0 (simbolos v1 y v2, v3 y v4 no usas)
%i=1 j=3 -> v1-v2=l13=2 -> 1 0 -1 0
%Segumos creando matriz con signos
H=[1 -1 0 0;1 0 -1 0; 1 0 0 -1;0 1 -1 0;0 1 0 -1;0 0 1 -1;1 1 1 1];
%Matriz con resultados(Lij) de la linea anterior vi- vj = Lij
b=[M(1,2) ...]
v=H/b
%Deshacer T log
w=exp(v)/sum(exp(v))
%Normalizar(dividir entre norma)



% Mismo ejemplo distinto metodo
%Igual pero matriz de pesos H con todo el coeficiente no solo con su signo
%La b serán todo ceros salvo un 1 al final
%w=H\b
%Normalizas w=w/sum(w)







%ENTREGA PARA EL PROXIMO DÍA

% PASO 1: Construir la matriz de comparación por pares recíproca usando escala de Saaty
clear
n = 4; % Número de alternativas (P1, P2, P3, P4)
% Matriz M vacía
M = eye(n); 

% Rellenar la parte superior de la diagonal(i<j) con valores inventados (escala de Saaty)
M(1,2) = 1/3;  % P1 comparado con P2
M(1,3) = 5;    % P1 comparado con P3
M(1,4) = 7;    % P1 comparado con P4
M(2,3) = 3;    % P2 comparado con P3
M(2,4) = 1/2;  % P2 comparado con P4
M(3,4) = 1/6;  % P3 comparado con P4

% Bucle para completar la matriz de manera recíproca por debajo de la diagonal
for i = 1:n
    for j = i+1:n
        M(j,i) = 1 / M(i,j); % M(i,j) = 1 / M(j,i)
    end
end

% PASO 2: Calcular el autovalor dominante lambda_max mediante el método de la potencia
tol = 1e-06; % Tolerancia para la convergencia
max_iter = 1000; % Número máximo de iteraciones
v = ones(n, 1); % Vector inicial
lambda_max = 0;
iter = 0;
while true
    v_prev = v;
    v = M * v;
    lambda_max = norm(v, inf); % Aproximación al autovalor dominante
    v = v / lambda_max; % Normalizar el autovector
    iter = iter + 1;
    err = norm(v - v_prev, inf);
    if err < tol || iter >= max_iter %Mientras no lleguemos a la tolerancia ni a las iteraciones
        break;
    end
end

% PASO 3: Calcular el índice de inconsistencia
IC = (lambda_max - n) / (n - 1); % Índice de inconsistencia

% PASO 4: Calcular el vector de pesos (normalizado)
w = v / sum(v); 

% Determinar la mejor y la peor alternativa
[~, mejor] = max(w); % Mejor alternativa
[~, peor] = min(w); % Peor alternativa












