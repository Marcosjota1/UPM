
public class TodasLasCosas {
	public static void main(String[] args) {
		
		/// Ejemplos
		
		//	Permutaciones
				permutaciones("CASIOPEA");		
		
		// Indice de Coincidencia
		//	Con o sin espacios, todas deben ser may�sculas o min�sculas
		//		indiceCoincidencia("VPRCG CIFOP CFZRS CDPLG CBWFT MSYON QCMNG TRNDM JPVFO RZEZW");
		
		// Homofonico
		//  Texto -> con mayusculas o minusculas (las tildes las reconoce a parte)
		//	Codigo numerico -> Con o sin espacios, separado por '-'
		//		homofonico("En un lugar de la Mancha de cuyo nombre no quiero acordarme",
		//						"12 - 5000 - 24 - 85 - 36 - 98 - 5000",
		//						"-");
		
		// Porta Bellaso
		//	Mayusculas y minusculas
		// 	Mejor dejar espacios en el texto origen
		//		porta("vqtxqbuk r wwi", "PROFUNDIS");
		
		// Vignere
		// 	Cualquier numero si key-autokey
		//	1 si text-autokey
		//		vignere("WFRLFI VZQYXE CCWICI LRXRRD IGEQEN","SUERTE",1);
		
		// 	ADFGVX	
		//		getAVDFGXTable("AMENOS");
	
	}
	
	public static void permutaciones(String palabra) {
		PermutacionPalabra.main(new String[] {palabra});
	}
	
	public static void indiceCoincidencia(String secuencia) {
		IndiceCoincidencia.main(new String[] {secuencia});	
	}
	
	public static void homofonico(String texto, String numeros, String separador) {
		Homofonico.main(new String[] {texto,
		numeros,
		separador});
	}
	
	public static void porta(String texto, String clave) {
		PortaBellaso.main(new String[] {texto,
				clave});
	}
	public static void vignere(String texto, String clave, int tipo) {
		Vignere.main(new String[] {texto,clave, Integer.toString(tipo)});
	}
	
	public static void getAVDFGXTable(String clave) {
		String dic = ADFGVX.getABCD(clave);
		System.out.println("Clave -> "+dic);
		System.out.println();
		ADFGVX.printTabla(dic);
	}
	
}
