clear 
clc
%x=-5:0.5:5; 
%y=x.^2; 
%plot(x, y, 'b:s');
%x1=linspace(0,2*pi,100);
%x2 = 0:0.01:2*pi;
%y1=sin(x2);
%plot(y1,'r');
%x=0:0.1:10;
%subplot(2,2,1), plot(x,cos(x),'r')
%title('Cosine');
%subplot(2,2,2), plot(x,sin(x),'s')
%title('Sine'); 
%x=0:0.1:10;
%plot(x,sin(x),'r',x,cos(x),'g')
%x=0:0.1:10;
%plot(x,sin(x),'r')
%hold on 
%plot(x,cos(x),'g')
%hold off
%x=0:0.1:50;
%f=exp(-x);
%plot(x,f)
%semilogy(x,f)
%var1=5, var2=pi, var3=1.2e-7;
%fprintf('texto1 %4d texto2 %16.15f texto3 %e \n', var1,var2,var3, var1*2)
%a=1;
%fprintf('ENT %4d FLOAT %16.15f CIENT %e \n', a,a,a)
%a=[1 2 3; 4 5 6; 7 8 9]
%fprintf('Primer parametro %d segundo parametro %d tercer parametro %d \n',a);
%x=1./(2.^(0:1:10))
%s = sum(x)
%[r,s] = test(1)
%Buscamos esto: xn+1=cos(xn)

%x = zeros(51,1)
%for k=1:50,
%    x(k+1)=cos(x(k));
%end
%plot(x,'.')
%x(end)
%A = rand(5,7)
%for k=1:5,
%    for j=1:7,
%        if A(k,j)<=0.4,
%            A(k,j)=0.2;
%        else
%            A(k,j)=0.7;
%        end
%    end
%end
%A

n= 0:10;
x = 10.^-n;
f = sqrt(1+x.^2)-1;
g = x.^2./(sqrt(1+x.^2)+1);
eabs= abs(f-g);
erel = eabs./abs(g);
subplot(2,1,1); loglog(x,erel,'*r')
ncif=floor(-log10(erel));
subplot(2,1,2); semilogx(x,ncif,'*g')

%{
n = 1 : 16;
x = 10.^-n;
vex = log1p(x);
vaprox=log(1+x);
eabs = abs(vex-vaprox);
erel = eabs./abs(vex);
ncif = floor(-log10(erel));
%semilogx(x,ncif,'*r')
suma = 0;
a = 10^-8;
for i = 1:100
    suma = suma + ((((-1)^(i+1))*a^i)/i);
end
fprintf('suma(a) = %.10e \nlog1p(a) = %.10e \nlog(1+a) = %.10e \n', suma, vex(8), vaprox(8))
%}
