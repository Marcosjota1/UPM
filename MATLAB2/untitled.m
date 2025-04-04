clear;
clc;
x=10.^-(1:16);
v_ex = log1p(x);
v_aprox =  log(1+x);
eabs = abs(v_ex-v_aprox);
erel = eabs./abs(v_ex);
ncif= floor(-log10(erel));
%subplot(1,2,1);loglog(x,erel,'r*')
%subplot(1,2,2);semilogx(x,ncif,'b')
n = 1:100;
x = 10^-8;
v3=sum((((-1).^(n+1)).*(x.^n))./n);
a = log1p(x);
b = log(x+1);
fprintf("valor de log1p(x) = %.10e \n valor de log(1+x) = %.10e \n valor de v3 = %.10e \n ",a ,b ,v3)
%----------------------------------------------------------------------------
%2
n=0:15;
h = 10.^-n;
v_ex =1/4;
v_aprox = (1./(2.*h)).*(-3*log(4)+4*log(4+h)-log(4+2.*h));
erel = abs(v_ex -v_aprox)./abs(v_ex);
loglog(h,erel)

n = 0:4;
h = 10.^-n;
erel = zeros(5,10);
ncif = zeros(5,10);
x = 1:10;
v_ex = 1./x;
for i = 1:5
    v_aprox= (1/(2*h(i))).*(-3*log(x)+4*log(x+h(i))-log(x+2*h(i)));
    ncif(i,:)=-log10(abs(v_ex-v_aprox)./abs(v_ex));
end
subplot(2,1,1);plot(v_ex,ncif(1,:),'r');
hold on
plot(v_ex,ncif(2,:),'g');
plot(v_ex,ncif(3,:),'b');
plot(v_ex,ncif(4,:),'k');
plot(v_ex,ncif(5,:),'m');
hold off


for n=0:4 
    h=10^-n;
    x=[1:10];     
    vap=(1./(2*h)).*(-3*log(x)+4*log(x+h)-log(x+2*h));
    vex=1./x;
    error=abs(vap-vex)./vex;
    cifras=-log10(error);
    if (n==0) 
    subplot(1,1,1);plot(vex,cifras)
    hold on 
    else
    plot(vex,cifras)
    end
end
hold off 

clear
clc
for n=0:4 
    h=10^-n;
    x=[1:10];     
    vap=(1./(2*h)).*(-3*log(x)+4*log(x+h)-log(x+2*h));
    vex=1./x;
    error=abs(vap-vex)./vex;
    cifras=-log10(error);
    plot(vex,cifras) 
    if n==0
     hold on 
    end
end
hold off
%OTRA OPCION 
clear;
clc;

x=1:10;
res= 1./x;

for n=0:4
    h=10^-n;
    resap=(1./(2.*h)).*((-3).*log(x)+4.*log(x+h)-log(x+2.*h));
    eabs= abs(res-resap); 
    erel=eabs./abs(res);
    ncifras= floor(-log10(erel));
    plot(x,ncifras,'');hold on;

end
hold off;
