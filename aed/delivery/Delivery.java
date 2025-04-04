package aed.delivery;

import es.upm.aedlib.positionlist.PositionList;
import es.upm.aedlib.Position;
import es.upm.aedlib.positionlist.NodePositionList;
import es.upm.aedlib.graph.DirectedGraph;
import es.upm.aedlib.graph.DirectedAdjacencyListGraph;
import es.upm.aedlib.graph.Vertex;
import es.upm.aedlib.indexedlist.ArrayIndexedList;
import es.upm.aedlib.indexedlist.IndexedList;
import es.upm.aedlib.graph.Edge;
import es.upm.aedlib.set.HashTableMapSet;
import es.upm.aedlib.set.Set;
import java.util.Iterator;

public class Delivery<V> {
	
	private DirectedGraph <V,Integer> graph = new DirectedAdjacencyListGraph<>() ;
	private IndexedList <Vertex<V>> listVertex = new ArrayIndexedList<>();
	

  // Construct a graph out of a series of vertices and an adjacency matrix.
  // There are 'len' vertices. A negative number means no connection. A non-negative
  // number represents distance between nodes.
  public Delivery(V[] places, Integer[][] gmat) {
	  for (int i=0; i< places.length;i++) {
		  listVertex.add(i, graph.insertVertex(places[i]));
	  }
		  for(int i=0;i<gmat.length;i++) {
			  for(int j=0;j<gmat[i].length;j++) {
				  if (gmat[i][j] != null && gmat[i][j] > 0) {
					  graph.insertDirectedEdge(listVertex.get(i), listVertex.get(j), gmat[i][j]);
				  }
			  }
		  } 
  }
  
  // Just return the graph that was constructed
  public DirectedGraph<V, Integer> getGraph() {
    return graph;
  }

  // Return a Hamiltonian path for the stored graph, or null if there is none.
  // The list containts a series of vertices, with no repetitions (even if the path
  // can be expanded to a cycle).
  public PositionList <Vertex<V>> tour() {
	PositionList<Vertex<V>> path = null;
	if(graph.size() > 0) {
		Iterator<Vertex<V>> it = graph.vertices().iterator();
		while(it.hasNext() && path == null) {
			path = findTour(it.next(), new HashTableMapSet<Vertex<V>>());
			}
		}
	return path; //grafo.size() < 0
}

  private PositionList<Vertex<V>> findTour(Vertex<V> v, Set<Vertex<V>> reachedVertex) {
	    PositionList<Vertex<V>> path = new NodePositionList<>();
	    reachedVertex.add(v);

	    if (graph.numVertices() == reachedVertex.size()) {
	        path.addLast(v);
	        return path;
	    }

	    for (Edge<Integer> edge : graph.outgoingEdges(v)) {
	        Vertex<V> nextVertex = graph.endVertex(edge);
	        if (!reachedVertex.contains(nextVertex)) {
	            path = findTour(nextVertex, reachedVertex);
	            if (path != null) {
	                path.addFirst(v);
	                return path;
	            }
	        }
	    }

	    reachedVertex.remove(v);
	    return null;
	}


public int length(PositionList<Vertex<V>> path) {
    int longitud = 0;
    Position<Vertex<V>> pos1 = path.first();

    for (Position<Vertex<V>> pos2 = path.next(pos1); pos2 != null; pos2 = path.next(pos1)) {
        Vertex<V> vertex1 = pos1.element();
        Vertex<V> vertex2 = pos2.element();

        for (Edge<Integer> edge : graph.outgoingEdges(vertex1)) {
            if (vertex2.equals(graph.endVertex(edge))) {
                longitud += edge.element();
                break;
            }
        }

        pos1 = pos2;
    }

    return longitud;
}

  public String toString() {
    return "Delivery";
  }
}
