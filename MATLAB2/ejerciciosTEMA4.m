clear,clc
%EJERCICIO 1
%1A)
%1. Comprobar gráficamente y verificar analíticamente que dicha función tiene una raíz en el intervalo [1, 2].
% considerando la función  f1=x.^2-exp(-x)-1;

 x = 1:0.01:2;
 f1=x.^2-exp(-x)-1;
 plot(x,x*0,'r',x,f1,'k'); %Tiene raiz al tener un valor positivo y otro negativo.Segun Bolzano 
       %x*0 para bibujar linea horizontal y comprobar que tiene valores
       %negataivos y positivo (Teorema Bolzano)

%1B)
% 2. Aplicar el método de Newton para calcular dicha solución tomando como valor inicial el punto medio del
% intervalo e iterando 7 veces. El código empleado debe proporcionar (comando fprintf): el número de
% iteración k (%d), la solución aproximada x_k con 16 decimales (%.16f), una estimación del error e_k
%  ( %0.2e) y el nº de cifras decimales correctas cif_dec(%d) para cada iteración. 

 xx = 1:0.01:2;
 yy = funex(xx);
 plot(xx,xx*0,'r',xx,yy,'k');

 x0 = 1.5;
 N=7;

 for k = 1:N
     [f fp] = funex (x0);
     if f==0 break;
     end;
     x = x0 - f/fp;
     e = abs (x-x0);
     cif =floor(-log10(e));
     fprintf('k: %d x_k: %.16f e_k: %0.2e cif_dec: %d \n',k,x,e,cif)
     x0=x;
 end

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%2) px = x^3 - x^2- x- 1,  
s=1.839286755214161;
n=10; 
x0=1.5; %Valores iniciales de donde te pidan evaluarla
y0=1.5; %Valor inicial metodo 2
e_rel1=zeros(1,n);
e_rel2=zeros(1,n);
%Método 1
for k=1:n
    x= x0- ((x0^3-x0^2-x0-1) / (3*x0^2- 2*x0-1));
    e_abs1(k)=abs(x-s);
    e_rel1(k)=abs((x-s)/s);
    fprintf('iter:%2d x:%.16f e_rel1:%2e\n',k,x,e_rel1(k));
    x0=x;
end
cif_correct1=floor(-log10(e_abs1))
cif_sig1=floor(-log10(e_rel1))
%Método 2
x0=1.5;
for k=1:n
    y= (x0^2+x0+1)^(1/3);
    e_abs2(k)=abs(y-s);
    e_rel2(k)=abs((y-s)/s);
    fprintf('iter:%2d y:%.16f e_rel2:%2e\n',k,y,e_rel2(k));
    x0=y;
end
cif_correct2=floor(-log10(e_abs2));
cif_sig2=floor(-log10(e_rel2));
k=1:n;
semilogy(k,e_rel1, 'r', k, e_rel2, 'g');


 %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
 %%%%%%%%%%%%%%% FUNCIONES %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
 %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

function[f,fp]=funex(x)  %evaluacion de la funcion y su derivada
    f = x.^2-exp(-x)-1;
    if nargout == 1, return; end
    fp = 2*x+exp(-x);
end