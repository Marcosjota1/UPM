clear,clc

error = 100.0;
iter = 1;
x0=1.0;
while(error > 1e-10)
    x1 = sqrt(exp(-x0));
    error = abs(x1-x0);
    fprintf("Iter %d Sol %.15f Error %0.2e\n",iter,x1,error)
    iter = iter+1;
    x0=x1;
end
s=x0;


error = 100.0;
iter = 1;
x0=1.0;
while(error > 1e-10)
    x1 = x0-((x0.^2-exp(-x0))/(2*x0+exp(-x0)));
    error = abs(x1-x0);
    fprintf("Iter %d Sol %.15f Error %0.2e\n",iter,x1,error)
    iter = iter+1;
    x0=x1;
end
s2=x0;