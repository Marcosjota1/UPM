% Ejercicio 1

% Apartado 1)
xi = [-1:1]';
yi = 1./(1+25.*xi.^2);

xx = -1:0.01:1;

H = [xi.^0 xi xi.^2];
c = H\yi;

pp = c(1) + c(2).*xx + c(3).*xx.^2;
ff = 1./(1+25.*xx.^2);

E = abs(ff-pp);

subplot(211), plot(xi,yi,'g*',xx,ff,'g',xx,pp,'r')
subplot(212), plot(xi,yi*0,'g*',xx,E,'b')

% Apartado 3) 
n = [3 5 9 17];
for i = 1:4
    xi = linspace(-1,1,n(i))';
    yi = 1./(1+25.*xi.^2);
    c = coef_interp(xi,yi);
    
    pp = zeros(4,length(xx));
    for k=1:n(i)
        pp(i,:)=pp(i,:)+c(k)*xx.^(k-1);
    end
    
    error = zeros(4,length(xx));
    error(i,:) = abs(ff-pp(i,:));
    
    subplot(2,4,i), plot(xi,yi,'g*',xx,ff,'g',xx,pp(i,:),'r')
    subplot(2,4,i+4), plot(xi,yi*0,'g*',xx,error(i,:),'b')
    
    maxerr = max(error(i,:));
    fprintf("Nodos = %2d, Error máximo = %0.2e\n",n(i),maxerr)
end

% Apartado 2) 
function c = coef_interp(xi,yi)
    n = length(xi);
    H = zeros(n,n);
    for i = 1:n
        H(:,i) = xi.^(i-1);
    end
    c = H\yi;
end

% Si l es la longitud de xi, entonces el grado del polinomio es l-1 y la
% dimensión de la matriz H debe ser lxl.

% La primera columna serían 1's, la segunda columna serían los valores
% (x-1), la tercera columna serían (x-1)^2, ..., la última columna serían
% (x-1)^n 
