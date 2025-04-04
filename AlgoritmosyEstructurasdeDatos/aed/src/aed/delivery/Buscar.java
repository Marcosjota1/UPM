package aed.delivery;

import es.upm.aedlib.Pair;
import es.upm.aedlib.positionlist.PositionList;
import es.upm.aedlib.positionlist.NodePositionList;

public class Buscar {

  public static Pair<String, PositionList<Direccion>> busca(Laberinto laberinto) {
    // Inicializar la lista de movimientos
    PositionList<Direccion> movimientos = new NodePositionList<>();

    // Llamar al método auxiliar de búsqueda recursiva
    Pair<String, PositionList<Direccion>> resultado = buscaRecursivo(laberinto, movimientos);

    // Devolver el resultado
    return resultado;
  }

  private static Pair<String, PositionList<Direccion>> buscaRecursivo(Laberinto laberinto, PositionList<Direccion> movimientos) {
    // Verificar si el regalo está presente en la posición actual
    if (laberinto.tieneRegalo()) {
      // Regalo encontrado, devolver el Pair con el regalo y la lista de movimientos
      return new Pair<>(laberinto.getRegalo(), new NodePositionList<>(movimientos));
    }

    // Marcar la posición actual como visitada
    laberinto.marcaSueloConTiza();

    // Recorrer las direcciones posibles desde la posición actual
    for (Direccion direccion : laberinto.direccionesPosibles()) {
      // Guardar la posición actual antes de moverse
      Punto posicionAnterior = laberinto.getPunto();

      // Moverse en la dirección actual
      laberinto.moverHacia(direccion);
      movimientos.addLast(direccion);

      // Verificar si la nueva posición no ha sido visitada
      if (!laberinto.sueloMarcadoConTiza()) {
        // Llamada recursiva para explorar la nueva posición
        Pair<String, PositionList<Direccion>> resultado = buscaRecursivo(laberinto, movimientos);

        // Si se encuentra un regalo, devolver el resultado
        if (resultado != null) {
          return resultado;
        }
      }

      // Retroceder a la posición anterior
      laberinto.moverHacia(opuesta(direccion));
      movimientos.remove(movimientos.last());
    }

    // No se encontró un regalo alcanzable desde la posición actual
    return null;
  }

  // Método auxiliar para obtener la dirección opuesta
  private static Direccion opuesta(Direccion direccion) {
    switch (direccion) {
      case NORTE: return Direccion.SUR;
      case SUR: return Direccion.NORTE;
      case ESTE: return Direccion.OESTE;
      case OESTE: return Direccion.ESTE;
      default: return null;
    }
  }
}
