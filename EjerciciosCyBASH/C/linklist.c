#include <stdio.h>
#include <stdlib.h>
#include <string.h>

struct Vagon{
	int num_vagon;
	int num_pasajeros;
	struct Vagon* siguiente_vagon; //puntero de vagones cada uno tendra las cualidades de arriba

};

#define TAM_VAGON sizeof(struct Vagon) //defines TAM_Vagon siempre que lo llames hara size(struct vagon)

int main(int argc, char *argv[]){
//MALLOC = Memory Allocation, te reserva memoria 

	struct Vagon* cabeza;   // CREACION DE PUNTEROS
	struct Vagon* vagon1;
	struct Vagon* vagon2;
	struct Vagon* cafeteria;

	//MALLOC devuelve un puntero  
	cabeza =(struct Vagon*) malloc (TAM_VAGON); //Reservas en memoria (malloc) para la cabeza el TAM_VAGON
	//pon que cabeza reserva tres espacios en memoria, 12 bits = x , y, z. Para coger cada valor haces variable = cabeza.y, cogerias segundo
	vagon1 =(struct Vagon*) malloc (TAM_VAGON);
	vagon2 =(struct Vagon*) malloc (TAM_VAGON);
	cafeteria =(struct Vagon*) malloc (TAM_VAGON);

	cabeza -> num_vagon = 0;
	cabeza -> num_pasajeros = 1;
	//sin malloc se haria => num_vagon = &dir_malloc +0;     //&dir accede a memoria
							//num_pasajeros = &dir_malloc + 4

	vagon1 -> num_vagon = 1;
	vagon1 -> num_pasajeros = 30;

	vagon2 -> num_vagon = 2;
	vagon2 -> num_pasajeros = 30;

	cafeteria -> num_vagon = 3;
	cafeteria -> num_pasajeros = 12;


//PARA ENLAZAR VAGONES

	cabeza->siguiente_vagon=vagon1;
	vagon1->siguiente_vagon=vagon2;
	vagon2->siguiente_vagon=cafeteria;
	cafeteria->siguiente_vagon=NULL;

	struct Vagon* vagon = cabeza;

	while (vagon!=NULL ){
		printf("Vagon %d, capacidad %d pasageros\n",
			vagon->num_vagon,vagon->num_pasajeros);
		struct Vagon* old_vagon = vagon;
		vagon=vagon->siguiente_vagon;
		free(old_vagon);
	}
	free(vagon);
} 