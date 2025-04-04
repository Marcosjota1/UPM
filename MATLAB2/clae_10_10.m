clear;
clc;
%{
%}

x = 10.^(1:15);
vex = 1./(sqrt(x) + sqrt(x-1));
vaprox = sqrt(x)-sqrt(x-1);

eabs=abs(vex-vaprox);
erel=eabs./abs(vex);

ncifs = floor(-log10(erel));

subplot(1,2,1);loglog(x,erel), title("Error relativo")
subplot(1,2,2);semilogx(x,ncifs), title('Cifras significativas')

%-----------------------------------------------------------------
n= 0:10;
x = 10.^-n;
f = sqrt(1+x.^2)-1;
g = x.^2./(sqrt(1+x.^2)+1);
eabs= abs(f-g);
erel = eabs./abs(g);
subplot(2,1,1); loglog(x,erel,'*r')
ncif=floor(-log10(erel));
subplot(2,1,2); semilogx(x,ncif,'*g')

%-----------------------------------------------------------------
%1
v_ex=log(1.5);
k = 1:30;
x = 0.5;
terminos = (((-1).^(k+1)).*x.^k)./k;
v_aprox = 1:30;
for i = 1:30
v_aprox(i)=sum(terminos(1:i));
end
e_rel = abs(v_ex-v_aprox)/abs(v_ex);
n_dec_sig = floor(-log10(e_rel));
n_dec_sig(30);
subplot(1,2,1);semilogy(k,e_rel,'r*')
subplot(1,2,2);plot(k,n_dec_sig,'g*')
%2
xx=0:0.1:0.9;
suma = 0;
f = log(1+xx);
fap=1:10;
for i = 1:10
    for k = 1:20
        suma = suma + (((-1).^(k+1)).*xx(i).^k)./k;
    end
    fap(i) = suma;
    suma =0;
end
fap;
plot(xx, f, 'r',xx,fap,'g')
erel = abs(f-fap)./abs(f);
plot(xx,erel)

%------------------------------------------------------
n = 1:8;
h = 10.^(-n);
v_aprox=(cosh(1+h)-(2*cosh(1))+cosh(1-h))./(h.^2);
v_ex = cosh(1);
erel=abs(v_aprox-v_ex)/abs(v_ex);
semilogy(n,erel)
ncif=-log10(erel);
semilogx(h,ncif)
%---------------------------------------------------------

x = 1;
k_over = 1;
while(x<inf)
x=x*2;
k_over = k_over+1;
end
k_over
x = 1;
k_under = 1;
while(x>0)
x=x/2;
k_under = k_under+1;
end
k_under
k = 1;
while(1+2^(-k)>1)
k = k+1;
end
k






