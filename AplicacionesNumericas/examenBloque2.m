clear
%1,2)
C=zeros(8);
%1 no tiene links entrada
C(2,:)=[1 0 0 0 0 1 0 0]; %Fila 1 = links entrada a Pagina 1
C(3,:)=[0 0 0 0 0 0 0 1]; %Columna 1 = links salida desde Pagina1
C(4,:)=[0 0 0 0 0 0 0 0];
C(5,:)=[0 0 0 0 0 0 1 0];
C(6,:)=[0 1 0 0 0 0 0 0];
C(7,:)=[1 0 1 0 0 0 0 0];
C(8,:)=[0 0 0 0 1 0 0 0];
C

%3)
Nj=sum(C);

%4)
iss=find(Nj==0);
ics=find(Nj>0);
%5)
A = zeros(8);
for i = ics
       A(:, i) = C(:, i) / Nj(i);
end
Nj2=sum(A);
%6)
dj=find(Nj2==0);
%7)
S=A;
S(:,dj)=1/8;
%8)
%No cumple, lo podemos demostrar observando que existen subgrafos en
%nuestro grafo principal sin links de salida, por ejemplo el subgrafo de
%los nodos 3,5,7,8 es un subgrafo del que no se puede salir

%9)
r1=0;
r2=r1/2+r6;
r3=r8;
r4=0;
r5=r7;
r6=r2;
r7=r1/2+r3;
r8=r5;

%10)
r4=r1=0;
r2=r6
r3=r5=r7=r8;
%Solo podemos afirmar que las paginas con menor importancia seran 1 y 4

%11)
%Calcular la matriz de Google G para un alfa=0.85
alfa=0.85;
n=8;
G=alfa*A+(1-alfa)*ones(n)/n
sum(G)

%12)
[a,v]=eig(G)
a=abs(a)
v=abs(v)

%13)
%Definimos potencia.m como un archivo auxiliar que contiene el código de la
%potencia
x0=ones(8,1);
x0=x0/norm(x0)
[x,iter]=potencia(G,1e-10,20,x0)
x

%14)
%No es el mismo resultado, pero ambos son autovalores ya que estan creados
%a partir de la matriz A

%15)
%Con x como estado inicial
for k=1:100
 x=G*x;
 end
x,sum(x)
norm(x-G*x)
bar(x);

%Con v como estado inicial
for k=1:100
 v=G*v;
 endx
 
v,sum(v)
norm(v-G*v)
bar(v);

%17)
%El vector de orden basta con mirar el resultado de un pagerank, por ejemplo miramos resultado de x
x
p=ones(8,1);
sort(abs(bar(x).YData),abs(bar(x).XData))

%Observamos el orden de las paginas
r1=r4<r3<r8<r5<r7<r6<r2
%Observamos valores muy cercanos como en las ecuaciones lineales definidas
%anteriormente, r1 y r4 son cero r3,r5,r7,r8 son practicamente iguales e
%igual pasa con r6 y r2
