function [pagerank, ordenpagerank, precision, tiempo] = calculo_PR(A, alfa, niter)


    % Inicia el cronómetro
        N = size(A,1);
    Nj=sum(A);
    dj = (Nj == 0);
    for k=1:N %Transformamos la matriz de conectividad en una matriz A
        if(Nj(k)~=0)
            A(:,k)=A(:,k)./Nj(k);
        end
    end
    e = ones(1, N);
    v=alfa*dj+(1-alfa)*e;

    size(dj);
    
    x=ones(N,1);
    
    for k=1:niter
        x=x/norm(x);
        x=(alfa*(A*x))+(1/N*(e'*(v*x)));
    end

    pagerank=x/sum(x);
    [~,ordenpagerank]=sort(-pagerank);
    precision = norm(alfa*(A*x)+(1/N*(e'*(v*x))) - x);
    
    
    tiempo=toc;

end
