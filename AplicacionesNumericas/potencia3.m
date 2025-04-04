function [lambda, pagerank] = potencia3(G, niter)
    % POTENCIA calcula el autovalor dominante y el pagerank asociado
    % usando el método de la potencia.
    %
    % Entrada:
    %   G     - Matriz estocástica (matriz de transición para PageRank)
    %   niter - Número máximo de iteraciones
    %
    % Salida:
    %   lambda   - Autovalor dominante (debería ser 1 para G)
    %   pagerank - Vector pagerank (autovector asociado al autovalor 1)

    % Inicializar el vector inicial (distribución uniforme)
    N = size(G, 1);
    x = ones(N, 1) / N;

    % Iteración del método de la potencia
    for k = 1:niter
        % Multiplicar por la matriz G
        x_new = G * x;

        % Normalizar el vector para evitar desbordamiento
        x_new = x_new / norm(x_new, 1);

        % Comprobar la convergencia
        if norm(x_new - x, 1) < 1e-12
            break;
        end
        x = x_new;
    end

    % El autovalor dominante para G debe ser 1
    lambda = 1;

    % El autovector asociado es el PageRank
    pagerank = x_new / sum(x_new); % Normalizar para que las componentes sumen 1
end