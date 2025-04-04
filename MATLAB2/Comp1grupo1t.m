%1
clear
clc
n=1:21;
a=(-5/6).^(n-1);

b(1)=1;
b(2)=-5/6;
for k=3:21
    b(k)=5*b(k-2)+31*b(k-1)/6;
end
vex =a;
vap=b;
erel=abs((vex-vap)./vex);
ncif=floor(-log10(erel))
figure;
subplot(121);plot(a,'b');hold on
subplot(121);plot(b,'g')
hold off
subplot(122);semilogy(erel,'or')
fprintf('para n= %2d el error = %e\n',[n;erel])




















%2a
k=0;
while(1+2.^(-k)) ~= 1
    k=k+1;
end
k
fprintf('Para k= %2d el valor de 1+2^-k es %.20f y el de 1+2^-(k-1) es %.20f\n',k,1+2^k,1+2^-(k-1))

%2b
vex=exp(1);
n= 1:60;
x=2.^-n;
vap=(1+x+(x.^2./2)+(x.^3./6)).^(1./x);
erel=abs((vex-vap)./vex);
ncif=floor(-log10(erel));
figure;
subplot(121);semilogy(n,erel,'*b')
subplot(122);plot(n,ncif,'go')
fprintf('para n %2d tenemos %2d cifras decimales significativas\n',[n;ncif]) %para que salga ordenadas

%2c