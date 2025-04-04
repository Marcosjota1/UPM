%TEMA 3
%Ejercicio Tipico
xi= [-1 0 1 2]'
yi= [-2 -1 0 3]'
b= yi
H=[xi.^0, xi.^1, xi.^2]
c = H\b
A=c(1)
B=c(2)
r= yi-(H*c)
E=sum (r.^2)
xx=[-1:0.01:2]
p_x=c(1)+c(2)*xx+c(3)*xx.^2+c(4)*xx.^3+c(5)*xx.^4; % me pueden pedir polinomioque mejor ajusete
y=(construir la funcion, dond, hacer el cambio de variable con A=c(1)... etc ydonde poga xi, ponemos xx)
plot(xi,yi,'rs',XX,y,'b')
% Cond y discrepancias
fprintf('Cond de H1 = %.0f\nCond de H2 %.0f\n',cond(H1),cond(H2));
disc_1=yk-H1*c1, disc_2=yk-H2*c2,
%Primer ejercicio
H=[1 1 1 1 ; -1 1 0 2]
Hc=[1 1 1 1; -1 1 0 2]
b=[-2.05 0.05 -1.15 1.05]
c=Hc/b
r=(Hc*c)-b
E=sum(r.^2)
%-----------RESIDUOS-GRAFICAS---------------------------------------------
%Datos a ajustar:
xi=[-1 0 1 2]';
fi=[-2 -1 0 3]';
%Ajuste por r(x):
H1=[xi.^0 xi.^1];
c1=H1\fi
%Ajuste por p(x):
H2=[xi.^0 xi.^2];
c2=H2\fi
%Ajuste por q(x):
H3=[xi.^0 xi.^1 xi.^2];
c3=H3\fi
% Residuos y errores
R1=abs(fi-H1*c1);
R2=abs(fi-H2*c2);
R3=abs(fi-H3*c3);
e1=norm(R1)
e2=norm(R2)
e3=norm(R3)
% Gráficas
xx=-1:0.01:2;
p1=c1(1)+c1(2)*xx;
p2=c2(1)+c2(2)*xx.^2;
p3=c3(1)+c3(2)*xx+c3(3)*xx.^2
plot(xx,p1,'r', xx,p2,'b', xx,p3,'g',xi,fi,'*r')
%---------------------------OTRO IGUAL-------------------------------------
 xi=[-1 1 2 3 4]';
 yi=[4 2 1 1 2 ]';
 H=[xi xi.^3];
 b=yi-3-0.5*xi.^2
 c=H\b

% R: Vector de residuos

R=abs(H*c-b); %abs(p(xi)-yi)
% E: Error
E=norm(R);

% v_ap: Valor aproximado en 1.2
a=1.2; v_ap= 3+c(1)*a +0.5*a^2+c(2)*a^3

% Vector auxiliar en intervalo [-1,4]
 v_aux=-1:0.1:4;

% p_aux = Valor del polinomio obtenido en v_aux:
p_aux=3+c(1)*v_aux+0.5*v_aux.^2+c(2)*v_aux.^3
plot(v_aux, p_aux,xi,yi,'*r')

%-------------------------------------------------------------------------
% Calcular la funcion u(x) que mejor ajusta(sentido de minimos cuadrados)
% los datos de la tabla
% Calcular el vector res de residuos y la norma e del error que produce 
% dicho ajuste, ¿Dar punto mayor desviación y cuanto vale?
% Superponer a la grafica del apartado anterior, la grafica de la nueva 
% funcion pedida, en el intervalo[0,0.8] y los puntos donde se realiza el ajuste
%
% Sistema a resolver
x=[1 0 1]'
y=[0 2 0]'
H=[x.^0 sin(pi*x) sin(2*pi*x)];
c=H\y;
%Residuos Error
res = abs(c(1)+c(2)*sin(pi*x)+c(3)*sin(2*pi*x)-y)
e = norm(res)
[resmax,m]=max(res)
%Grafica
xx=0:0.01:0.8
yyi=ci(1)+ci(2)*sin(pi*xx)+ci(3)*sin(2*pi*xx)
yy=c(1)+c(2)*sin(pi*xx)+c(3)*sin(2*pi*xx)
plo(xx,yyi,'g',xi,yi,'go',xx,yy,'*r')

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%Dada la tabla calcular la recta que mejor ajusta los datos (r(x)=a+bx)
%Datos
xi=[-1 0 1 2]';
fi=[-2 -1 0 3]';
H=[xi.^0 xi.^1]; %a+bx
b=fi;
c=H\b;
a=c(1)
b=c(2)
fprintf("r(x) = %.1f + %.1fx",a,b)
