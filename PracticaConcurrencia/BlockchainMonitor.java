package cc.blockchain;


public class BlockchainMonitor implements Blockchain {

  public BlockchainMonitor() { }

  /**
   * Ordena la creacion de una cuenta cuyo saldo inicial es v.
   * @param idPrivado identidad privada de la cuenta
   * @param idPublico identidad publica de la cuenta
   * @param v saldo inicial
   * @throws IllegalArgumentException si v < 0, o si idPrivado o idPublico identifican
   * cuentas ya creadas.
   */
  private boolean estaCreada (String idPrivado, String idPublico) {
        for (int i = 0; i < privCuentas.length; i++) {  
            if (idPrivado.equals(privCuentas[i])) {
                return true;
            }
        }
        for (int i = 0; i < pubCuentas.length; i++) { 
            if (idPublico.equals(pubCuentas[i])) {
                return true;
            }
        return false;
    }

  public void crear(String idPrivado, String idPublico, int v) {
    if(v < 0 || existeCuenta(idPrivado, idPublico))
      throw new IllegalArgumentException("Mensaje error");
    
    for(int i = 0; i < privCuentas.length; i++){
      if(privCuentas[i] == null && pubCuentas[i] == null){
        privCuentas[i] = idPrivado;
        pubCuentas[i] = idPublico;
        valor[i] = v;
        break;
      }
    }
  }

  /**
   * Un orden de transferir un determinado valor v desde
   * una cuenta origen a otra cuenta destino.
   * @param idPrivado la identidad privada de la cuenta origen
   * @param idPublico la identidad publica de la cuenta destino
   * @param v valor a transferir
   * @throws IllegalArgumentException si v <= 0 o si idPrivado y idPublico identifica 
   * la misma cuenta o si idPrivado y idPublico no identifican cuentas creadas
   *
   */
  public void transferir(String idPrivado, String idPublico, int v) {
    if(v<0 || !existeCuenta(idPrivado, idPublico) || idPrivado.equals(idPublico))
      throw new IllegalArgumentException("Mensaje error");

    
  }

  /**
   * Una pregunta sobre el saldo de una cuenta
   * @param idPrivado la identidad privada de la cuenta
   * @return saldo disponible en la cuenta
   * @throws IllegalArgumentException si idPrivado no identifica una cuenta
   */
  public int disponible(String idPrivado) {
    return 0;
  }

  /**
   * Un avisador establece una alerta para una cuenta. La operación
   * termina cuando el saldo de la cuenta c sube por enicima de m.
   * @param idPrivado la identidad privada de la cuenta
   * @param m saldo maximo
   * @throws IllegalArgumentException si m < 0 o si idPrivado no identifica una cuenta
   */
  public void alertarMax(String idPrivado, int m) {
  }
  
}
