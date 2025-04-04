function [autovalor, autovector] = potencia2(A, niter)
    % Método de la Potencia
    % Calcula el autovalor dominante y el autovector asociado.
    %
    % Entrada:
    %   A     - Matriz cuadrada
    %   niter - Número de iteraciones
    %
    % Salida:
    %   autovalor  - Autovalor dominante
    %   autovector - Autovector asociado

    % Dimensión de la matriz
    n = size(A, 1);
    
    % Vector inicial aleatorio no nulo
    x = ones(n, 1);
    
    % Iteración del método de la potencia
    for k = 1:niter
        % Multiplicar por la matriz
        x = A * x;
        
        % Normalizar el vector
        x = x / norm(x);
    end
    
    % Aproximación del autovalor dominante
    autovalor = (x' * A * x) / (x' * x);
    
    % El autovector dominante
    autovector = x;
end