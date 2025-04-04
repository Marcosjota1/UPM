function [Valor,Erel,Ncif] = inversoe(n)
vex = exp(-1);
k=0:n;
y=((-1).^k)*1./factorial(k);
Valor = sum(y);
Erel = abs((vex-Valor)/vex);
Ncif = floor(-log10(Erel));
end