package aed.individual1;

public class ArrayCheckSumUtils {

    public static int[] arrayCheckSum(int[] arr, int n) {
        if (arr.length == 0) { 
            return new int[0];
        } 
        else {
            int extra = (arr.length % n != 0) ? 1 : 0; // Add 1 if the last group of n numbers is incomplete
            int[] nuevo = new int[arr.length + (arr.length / n) + extra]; 
            
            int contador = 0;     
            int checksum = 0; 
            int arrayAntiguo = 0; 
            int arrayNuevo = 0;  
            while (arrayNuevo < nuevo.length) {
                if (contador == n || arrayNuevo == nuevo.length - 1) {
                    nuevo[arrayNuevo] = checksum; 
                    contador = 0;  
                    checksum = 0;
                    arrayNuevo++; // solo suma arrayNuevo, arrayAntiguo no mover  
                } else {
                    nuevo[arrayNuevo] = arr[arrayAntiguo]; 
                    checksum += arr[arrayAntiguo];
                    contador++;
                    arrayAntiguo++;
                    arrayNuevo++;
                }
            }

            return nuevo; 
        }
    }
}


/*           int contador = 0;
int nsuma = 0;

for (int i = 0; i < arr.length; i++) {
    for (int j = 0; j < nuevo.length; j++) {
        if (contador == n || j == nuevo.length - 1) {
            nuevo[j] = nsuma;
            contador = 0;
            nsuma = 0;
            
            
        } else {
            nuevo[j] = arr[i];
            contador++;
            nsuma += arr[i];
        }
    }
}
return nuevo;
}
}
}
*/