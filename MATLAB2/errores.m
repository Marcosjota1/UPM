clear;
clc;

k=1:15;
n=10.^k;
vex = exp(1);
vap = (1+(1./n)).^n;

erel = abs((vex-vap)/vex);
cf = floor(-log10(erel));

subplot(1,2,1); semilogy(erel,'*r');
subplot(1,2,2); plot(cf,'*g');

%fprintf('vals %f \n', n)

format long
