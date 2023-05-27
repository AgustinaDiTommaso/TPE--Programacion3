import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class ServicioDFS {

	private Grafo<?> grafo;
	private Map<Integer, String> color;

	public ServicioDFS(Grafo<?> grafo) {
		this.grafo = grafo;
		this.color= new HashMap<>();
	}
	
	public List<Integer> dfsForest() {
		List<Integer> lista =new ArrayList<>();

		Iterator<Integer> itVertice= this.grafo.obtenerVertices();
		while(itVertice.hasNext()){
			Integer vertice= itVertice.next();			
			color.put(vertice,"blanco");			
		}
		for (Integer key : color.keySet()) {
			
			if( color.get(key).equalsIgnoreCase("blanco")){
				lista.addAll(dfs_visit(key));
				}
		}
		
		return lista;
	}

	
	private List<Integer> dfs_visit( Integer vertice){
		
		List<Integer> lista =new ArrayList<>();

		color.put(vertice, "amarillo");
		lista.add(vertice);
		Iterator<Integer> itAdy= this.grafo.obtenerAdyacentes(vertice);
		while(itAdy.hasNext()){
			Integer ady= itAdy.next();
			
			if(color.get(ady).equals("blanco")){
				lista.addAll(dfs_visit(ady));
			}
		}
		return lista;
	}	
		
		
}
























