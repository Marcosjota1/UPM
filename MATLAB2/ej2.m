clear; 
clc;
%{
%Ejercicio 1

x = -2:0.01:2;
l = length(x);
p=[funcion_ej2(x,1);funcion_ej2(x,2);funcion_ej2(x,3);funcion_ej2(x,4)];
plot(x,p(1,:),'r');
hold on
plot(x,p(2,:),'g');
plot(x,p(3,:),'b');
plot(x,p(4,:),'k');
hold off

%Ejercicio 2

A = [2 -15 3 127; -97 32 0 3]
fprintf('%d %d \n',A);
fprintf('\n');
fprintf('%d %d %d %d \n',A');
fprintf('\n');
fprintf('%4d %4d %4d %4d \n',A');
%}
%Ejercicio 3

s = sum(1./2.^(0:60));
s = 1;
for i=1:60
    s = s + (1/(2^i));
end
s = 1;
i = 0;
while abs(s-2) > 10^-16
    i=i+1;
    s = s + 1/2^i;
end
s = zeros(1,61);
for i=1:61
    s(i) = sum(1./(2.^(0:i)));
end
erel = abs((2-s)/2);
[erelm, i] = min(erel);
ncif = floor(-log10(erel));
ncif(50:60);
x=(0:60)
subplot(1,2,1), semilogy(x,erel,'r*');
subplot(1,2,2), plot(x,ncif,'g*');
