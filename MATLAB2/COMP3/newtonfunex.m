x0 = 1.5;
 N=7;
 

 for k = 1:N
     [f fp] = funex (x0);
     if f==0 break;
     end;
     x = x0 - f/fp;
     e = abs (x-x0);
     cif =floor(-log10(e));
     fprintf('k: %d x_k: %.16f e_k: %0.2e cif_dec: %d \n',k,x,e,cif)
     x0=x;
 end

 %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
 %%%%%%%%%%%%%%% FUNCIONES %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
 %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

function[f,fp]=funex(x)  %evaluacion de la funcion y su derivada
    f = x.^2-exp(-x)-1;
    if nargout == 1, return; end
    fp = 2*x+exp(-x);
end

