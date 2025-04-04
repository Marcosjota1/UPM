%Dada la matriz conectividad C
clear
C=zeros(6);
C(2,:)=[1 0 0 1 0 1]; %Fila 1 = links entrada a Pagina 1
C(3,:)=[0 0 0 0 1 1]; %Columna 1 = links salida desde Pagina1
C(4,:)=[1 1 0 0 0 1];
C(5,:)=[0 0 1 1 0 0];
C(6,:)=[0 1 0 1 0 0];
C

%Calcular Nj=sum(C)
Nj=sum(C);
A=C./Nj;
S=A;

%Escribir las 6 ecuaciones lineales del sistema(Valor que ponga en la matriz A para cada fila)
r1=0
r2=r1/2+r4/3+r6/3
r3=r5+r6/3
r4=r1/2+r2/2+r6/3
r5=r3+r4/3
r6=r2/2+r4/3
%ORDENACION:r1=0   De la 3ra y 5ª ecuación: r3 >= r5 y r5 >= r3. Luego
%r3=r5 y r4=r6=0. De la 2a ecuación: r2=0.  Resumen: r1=r2=r4=r6=0, r3=r5.


%Teorema de Perron-Frobenius se cumple cuando no existen subgrafos sin
%salida, en este caso no se cumple ya que hay un nodo sin links de entrada(Primera fila todo ceros) y
%se producen tambien subgrafos sin links de salida(Columnas 3 y 5 solo conectadas entre si)


%A partir del estado E0=(1 0 0 0 0 0), calcular el estado E100 de la tabla de para la matriz S. ¿Cuál es el valor
%de E100? Calcular sum(E100). ¿E100 verifica la ecuación (1)?.
x=zeros(6,1);
x(1)=1; %Estado cero
for k=1:100 %SOL=10000
 x=A*x;
end
%El vector x verifica x(1)= x(2)= x(4)= x(6)=0. Pero no se verifica x(3)=x(5).
Error=A*x-x % error cometido

%OTRA OPCION GUARDANDO ESTADOS
x=zeros(6,100);
x(1)=1; %Estado cero
for k=1:100 %SOL=10000
 x(:,k+1)=A*x(:,k);
end
xfin=x(:,end);



%Calcular la matriz de Google G para un alfa=0.85
alfa=0.85;
n=6;
G=alfa*A+(1-alfa)*ones(n)/n
sum(G)
%La matriz G verifica Perron-Frobenius:
%El definida positiva: todos sus elementos son >= 0
%Es estocástica por columnas: sus elementos son positivos y suman 1.
%Es irreducible: Todos los nodos se pueden alcanzar desde cualquier otro nodo (todos sus elementos son no
%nulos).




%Sea r el pagerank de la matriz G:
%Gr r =
%Vamos a calcular el vector r. Partir del vector r=(1 0 0 0 0 0) y realizar 100 iteraciones r=G*r ¿cuál es el valor
%final de r? Calcular sum(r). Calcular
%norm (G * r - r)
x=zeros(6,1);
x(1)=1;
for k=1:100
 % x=x/norm(x);
 x=G*x;
 end
%x=x/sum(x),
x,sum(x)
norm(x-G*x)%Con 100 iteraciones obtenemos una precisión de 1e-8 (8 cifras de precisión)


%Calcular el pagerank r que verifique la siguiente condición de tolerancia, con tol=1e-15
x=zeros(6,1);
x(1)=1;
iter=0;
tol=1;
while tol > 1e-15
 x=x/norm(x);
 x=G*x;
 tol=norm(x-G*x);
 iter=iter+1;
end
x=x/sum(x);
x,sum(x),iter,tol %Con 211 iteraciones obtenemos una precisión de 1e-16 (16 cifras de precisión). La precisión de la máquina.
%El orden de las páginas según su pagerank es: P5 > P3 > P4 > P6 > P2 > P1 (mirar x)
%El orden de las páginas según la matriz S sería P3 = P5 > P2 = P1 = P4 = P6 =0. 

%A(I,J) i = fila j = columna, Va de J hacia I

G(4,5),G(2,4),G(4,5)*G(2,4)%La probabilidad p(P5->P4)=0.025, p(P4->P2)=0.3083, p(P5->P4->P2)=0.0077
%¿Cuál es la probabilidad 5 2 pP P ( ... ) −> −> ?. Este es, ¿Cuál es la probabilidad que un surfirsta que está enel nodo P5 pase a cualquier otro nodo (P1, P2, P3,…., ó P6) y luego pase al nodo P2
G(2,:)*G(:,5)%Por tanto, p(P5->…->P2)=0.0498
