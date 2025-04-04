package claseIndexedLists;

public interface Complejo {
	
		  /** 
		   * Devuelve la parte real 
		   **/
		  public double getReal();
		  
		  
		  /**
		   * Devuelve la parte imaginaria 
		   **/
		  public double getImaginaria();

		  /**
		   * Devuelve el módulo 
		   **/
		  public double getModulo();

		  /**
		   * Devuelve el argumento 
		   **/
		  public double getArgumento();
		  
		  /**
		   * Devuelve un nuevo Complejo haciendo la suma con 'c' 
		   * @param c Complejo a sumar
		   * @return un nuevo Complejo haciendo la suma con 'c'
		   */
		  public Complejo suma(Complejo c);

		  /**
		   * Devuelve un nuevo Complejo haciendo la resta con 'c' 
		   * @param c Complejo a restar
		   * @return un nuevo Complejo haciendo la resta con 'c'
		   */
		  public Complejo resta(Complejo c);

		  /**
		   * Devuelve un nuevo Complejo haciendo el conjugado con 'c' 
		   * @param c Complejo con el que hacer el conjugado 
		   * @return un nuevo Complejo haciendo el conjugado con 'c'
		   */
		  public Complejo conj(Complejo c);
		

}
