% MODELO A
%1
clear;
clc;
p=0;
while((1/2)+2^-p ~=(1/2))
    p=p+1;
end
p_pedido=p-1
fprintf('1/2: %0.17f, siguiente: %0.17f /n',1/2,(1/2)+2^-(p-1))
%Numero inmediatamente inferior a 1 es 1-2^-53= 0.999999999989

%2
clear;clc;
x=pi/4;k=0;vap=1;erel=abs((vap-cos(x))/cos(x));cif=floor(-log10(erel));
while(cif<14)
 k=k+1;
vap=vap+(((-1)^k)*((x)^(2*k))/factorial(2*k)); erel=abs((vap-cos(x))/cos(x)); cif=floor(-log10(erel));
fprintf('k:%d,vap:%.17f,erel:%0.2e, cifras:%d\n',k,vap,erel,cif)
end



%3
clear;
clc;
k=1:54;
n=2.^-k;
x=(pi/4)+n;
g=2*((cos(x)).^2)-1;
f=cos(2*x);
f(end)
erel= abs((f-g)./f);
ncif=floor(-log10(erel));
ncif(2)
[max_erel,p_maxerel]=max(erel)
x_maxerel=x(p_maxerel)
[max_ncif,p_maxncif]=max(ncif)
x_maxncif=x(p_maxncif)
subplot(1,2,1), semilogy(erel,'*r');
subplot(1,2,2), plot(ncif,'*g');



%-------------------------------------------------------------------------
%MODELO B 
%1
x=pi/4;k=0;vap=x;erel=abs((vap-sin(x))./sin(x));cif=floor(-log10(erel));
while(cif<14)
vap=sum(((-1).^[0:k]).*((x).^((2*[0:k])+1))./factorial((2*[0:k])+1));
erel=abs((vap-sin(x))./sin(x)); cif=floor(-log10(erel));
fprintf('k:%d,vap:%.17f,erel:%0.2e,cifras:%d\n',k,vap,erel,cif)
k=k+1;
end

%2
p=0;
while ((1/2)+2^-p~= (1/2))
 p=p+1;
end
p_pedido=p-1
fprintf('1/2:%0.17f, siguiente:%0.17f\n',1/2, 1/2+2^-(p-1)) 

%3

k=1:17; n=10.^-k;x=(pi/4)+n;f=cos(2*x);g=1-2*(sin(x)).^2; f(end)
erel=abs((f-g)./f); ncif=floor(-log10(erel)); ncif(3)
[max_erel,p_maxerel]=max(erel)
 x_maxerel=x(p_maxerel)
[maxcif,p_maxcif]=max(ncif)
x_maxcif=x(p_maxcif)
subplot(1,2,1), semilogy(erel, '*r');
subplot(1,2,2), plot(ncif,'*g'); 




