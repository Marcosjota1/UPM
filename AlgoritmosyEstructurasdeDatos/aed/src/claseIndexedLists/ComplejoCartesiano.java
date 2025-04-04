package claseIndexedLists;

public class ComplejoCartesiano {
	
		private double real; 
		private double imaginaria;

		public ComplejoCartesiano() 
		{}
		public ComplejoCartesiano(double re, double im) {
			this.real = re;
			this.imaginaria = im;
		}

		public ComplejoCartesiano(double re) {  // Real a complejo
			this.real = re;
			this.imaginaria = 0.0;
		}

		public ComplejoCartesiano(Complejo c) { // Constructor de copia
			real = c.getReal();
			imaginaria = c.getImaginaria();
		}

		public double getReal() { 
			return real; 
		}
		public double getImaginaria() { 
			return imaginaria;
		}
		public double getModulo() {
			// TODO Completar método
			return Math.sqrt(Math.pow(real,2)+ Math.pow(imaginaria,2));
		} 
		public double getArgumento() { 
			// TODO Completar método
			return Math.asin(real/imaginaria);
		} 

		public Complejo suma(Complejo c) {  // ¡ "Complejo" es un interfaz !
			return (Complejo) new ComplejoCartesiano(this.getReal() + c.getReal(), this.getImaginaria() + c.getImaginaria()) ;
		}

		public Complejo resta(Complejo c) {
			// TODO Completar método
			return null;
		}

		public Complejo conj(Complejo c) {
			// TODO Completar método
			return null;
		}

		public boolean equals(Object o) {
			if (o == this) return true;
			if (o instanceof Complejo) {
				Complejo c = (Complejo) o;       // ¡downcasting al interfaz!
				return this.real == c.getReal() && this.imaginaria == c.getImaginaria();
			} 
			else {
				return false;
			}
		}
		public String toString()
		{
			return "cartesiano";
		}
		
}
