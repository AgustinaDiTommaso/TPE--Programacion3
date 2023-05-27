
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class ServicioBFS {

	private Grafo<?> grafo;
	private Map<Integer, Boolean> visitado;
	private Queue<Integer> fila;

	public ServicioBFS(Grafo<?> grafo) {
		this.grafo = grafo;
		this.visitado= new HashMap<>();
		this.fila = new LinkedList<>();
	}
	

	public List<Integer> bfsForest() {
		
		List<Integer> lista =new ArrayList<>();
		
		Iterator<Integer> itVertice= this.grafo.obtenerVertices();
		while(itVertice.hasNext()){
			Integer vertice= itVertice.next();				
			visitado.put(vertice, false);
		}
		for (Integer key : visitado.keySet()) {
		    Boolean VerticeVisitado = visitado.get(key);
		    if(!VerticeVisitado){
				lista.addAll(bfsForest(key));
				}
		}
		 
		return lista;
	}

	
	private List<Integer> bfsForest(Integer vertice){
		
		List<Integer> listaBfs =new ArrayList<>();

		visitado.put(vertice, true);

		fila.add(vertice);
		
		while(!fila.isEmpty()) {
			Integer primerVerticeFila = fila.poll();
			listaBfs.add(primerVerticeFila);	
			Iterator<Integer> itAdy= this.grafo.obtenerAdyacentes(primerVerticeFila); 

			while(itAdy.hasNext()){
				Integer ady= itAdy.next();
				if(!visitado.get(ady)) {
					visitado.put(ady, true);
					fila.add(ady);
					}				
				}			
		}
				return listaBfs;
	}
}
