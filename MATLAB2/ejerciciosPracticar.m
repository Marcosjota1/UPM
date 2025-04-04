clear
clc
%1-----------------------------------------------------------------------
% Se pide:
% Construir un vector n con valores 1, 2, 3,..., 8.
% A partir de n construir un vector h con valores 0.1, 0.01, 0.001,....., 0.00000001.
% Construir un vector con los resultados de evaluar la expresión de la derecha para el vector h.
% Calcular el error relativo de los resultados de la expresión de la derecha con respecto a la de la
% izquierda. 
% Dibujar, en la escala adecuada, la relación entre n y el error relativo (los valores de n deben ir 
% en el eje horizontal y los del error relativo en el vertical).
% Calcular el número de cifras decimales correctas entre ambas expresiones.
% Dibujar, en la escala adecuada, la relación entre h y las cifras 
% (los valores de h deben ir en el eje horizontal y los de las cifras en el vertical).
% Para que valor de h se consiguen 8 cifras correctas.
% -->No se consiguen 8 cifras, lo más crecano es para h=10^-4, que resulta
% 0.0001
n=[1:8];
h=10.^-(n);
vex=cosh(1);
vap=(cosh(1+h)-2*cosh(1)+cosh(1-h))./(h.^2);
erel=abs((vap-vex)./vex);
ncif=(-log10(erel));
semilogy(n,erel)
figure(1),semilogy(n,erel)
figure(2),semilogx(h,ncif)



%2-------------------------------------------------------------------------
% xo = 0
% xn+1= cos(xn)
% Pintar la sucesion iterando 100 veces
x=0;
for (k=1:100)
    x=cos(x)
    plot(k,x,'*r')
    hold on
end
hold off
%B) crear scrip iterando 100 veces y estudiar errores
%Creamos vector inicializado en ceros
x=zeros(101,1);
%calculamos iteraciones y guardamos en x
for k=1:100
    x(k+1)=cos(x(k))
end
% dibujar iteraciones
plot(x,'r*')
%Valor estimado del límite de la sucesión 
x(end)
%error relativo, ncif y representacio
vex = x(end)
vap= x(1:end)
erel=abs((vex-vap)./vex)
ncif=floor(-log10(erel))
subplot(1,2,1), semilogy(erel,'*r')
subplot(1,2,2), plot(ncif,'*g')

%--------------------------------------------------------------------------
%3
%generar k y x
k= [1:10];
x= 10.^k;
vex = 4./(x+sqrt((x).^2-4));
vap= x-sqrt(x.^2-4)
erel=abs((vex-vap)./vex);
ncif=floor(-log10(erel));
subplot(1,2,1);loglog(x,erel,'r*'),title('Erel')
subplot(1,2,2);semilogx(x,ncif,'b*'),title('Ncif')
%Comentario graficas erel y Ncif:
% El máx numero de cif. que se obtiene es 14, para el caso x=10 (K=1),
% Luego se va reduciendo hasta llegar al min 0  para valores mayores o iguales a 8.
% El error rel. va creciendo valores de 10^-15 hesta casi orden 1, esto es un 100% error para K=9 y K=10.
% Esto significa que la expresión de la derecha proporciona mayor precisión para valores menores de x.
%Calcular el máximo y minimo de ncif y para que valor de x
[max,px]=max(ncif)
[min,pn]= min(ncif)
%Comando fprintf, tabla 1ª columna valor de k(%2d, 2ª erel %0.2e,ncif(%2d
a=[k,erel,ncif]
fprintf('k=%2d,erel=%0.2e,ncif=%2d\n',a)




