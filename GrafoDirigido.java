import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class GrafoDirigido<T> implements Grafo<T> {
	private Map<Integer, List<Arco<T>>> vertices;
	

	public GrafoDirigido(){
		this.vertices = new HashMap<>();
	
	}

	/**
	* Complejidad: O(1) debido a que debe verificar si no contiene el vértice y sino lo agrega
	* para lo cual implementa funcionalidades con complejidad O(1) 
	* 
	*/

	@Override
	public void agregarVertice(int verticeId) {
		if(!this.contieneVertice(verticeId)){
			this.vertices.put(verticeId, new ArrayList<>());
		}

	}


	/**
	* Complejidad: O(h) donde h es la cantidad de arcos debido a que en el peor de los casos se deben 
	* recorrer todos los arcos para verificar que el vértice no sea el destino de ninguno  
	*/
	@Override
	public void borrarVertice(int verticeId) {
		if(this.contieneVertice(verticeId)){
						
			Iterator<Arco<T>> it= this.obtenerArcos();
			while(it.hasNext()){
				Arco <T> arco= it.next();
				if(arco.getVerticeDestino()==verticeId){
					this.borrarArco(arco.getVerticeOrigen(), verticeId);
				}
			}
			this.vertices.remove(verticeId);

		}

	}

	
	/**
	* Complejidad: O(h) donde h es la cantidad de arcos debido a que implementa la funcionalidad
	* existeArco que tiene complejidad O(h).
	*/

	@Override
	public void agregarArco(int verticeId1, int verticeId2, T etiqueta) {
		//Controla si existen los vértices en el grafo
		if(this.contieneVertice(verticeId1) && this.contieneVertice(verticeId2)){
			// Controla que no exista aún un arco entre dicho vértices y lo crea
			if(!this.existeArco(verticeId1, verticeId2)){
				Arco<T> arco= new Arco<T>(verticeId1, verticeId2, etiqueta);			
				this.vertices.get(verticeId1).add(arco);
			}
		}
	}

	/**
	* Complejidad: O(h) donde h es la cantidad de arcos  
	* debido a que recorre todos los arcos del vertice origen(id1), 
	* buscando el arco que se debe borrar.
	*/

	@Override
	public void borrarArco(int verticeId1, int verticeId2) {

		if(this.contieneVertice(verticeId1) && this.contieneVertice(verticeId2)){
			
			List<Arco<T>> arcos= this.vertices.get(verticeId1);
			
			for(int i=0; i<arcos.size();i++){
				if(arcos.get(i).getVerticeDestino()== verticeId2){
					arcos.remove(i);
				}
			}

		}

	}

	/**
	* Complejidad: O(1) debido a que es un método propio de HashMap de la misma complejidad
	* y devuelve un boolean 
	*/

	@Override
	public boolean contieneVertice(int verticeId) {
		
		return this.vertices.containsKey(verticeId);
	}

	
	/**
	* Complejidad: O(h) donde h es la cantidad de arcos que puede tener el verticeId1 
	* debido a que se recorren para encontrar aquel que tenga como destino el verticeId2
	*/
	@Override
	public boolean existeArco(int verticeId1, int verticeId2) {
		if(this.contieneVertice(verticeId1) && this.contieneVertice(verticeId2)){
			
			List<Arco<T>> arcos= this.vertices.get(verticeId1);
			
			for(int i=0; i<arcos.size();i++){
				if(arcos.get(i).getVerticeDestino()== verticeId2){
					return true;
				}
			}

		}
		return false;
		
	}

	/**
	* Complejidad: O(h) donde h es la cantidad de arcos que puede tener el verticeId1 
	* debido a que se recorren para encontrar aquel que tenga como destino el verticeId2
	* 
	*/

	@Override
	public Arco<T> obtenerArco(int verticeId1, int verticeId2) {
		if(this.contieneVertice(verticeId1) && this.contieneVertice(verticeId2)){
			
			List<Arco<T>> arcos= this.vertices.get(verticeId1);
			
			for(int i=0; i<arcos.size();i++){
				if(arcos.get(i).getVerticeDestino()== verticeId2){
					return arcos.get(i);
				}
			}

		}
		return null;
	}

	/**
	* Complejidad: O(1) debido a que el HashMap vértices
	* tiene un método que devuelve su tamaño (cantidad de claves/vértices)
	* 
	*/

	@Override
	public int cantidadVertices() {
		
		return vertices.size();
	}

	/**
	* Complejidad: O(n) donde n es la cantidad de vértices que contiene el grafo
	* debido a que los recorre pidiendolé la cantidad de arcos
	*/

	@Override
	public int cantidadArcos() {
		int cantArcos=0;

		for(Entry<Integer, List<Arco<T>>> entry: vertices.entrySet()){
			cantArcos+= entry.getValue().size();
		}
		return cantArcos;
	}

	/**
	* Complejidad: O(1) debido a que keySet() es una funcionalidad propia de HashMap que trae las claves y con el iterator() 
	*  las hacemos iterables pero no se recorren
	*/

	@Override
	public Iterator<Integer> obtenerVertices() {
		
		return vertices.keySet().iterator();
	}

	/**
	* Complejidad: O(h) donde h es la cantidad de arcos que puede tener el verticeId 
	* debido a que se recorren para encontrar los adyascentes (vertices destino)
	* 
	*/

	@Override
	public Iterator<Integer> obtenerAdyacentes(int verticeId) {
		List<Integer> adyascentes= new ArrayList<Integer>();
		
		if(this.contieneVertice(verticeId)){
			Iterator<Arco<T>> it =this.obtenerArcos(verticeId);
			
			while(it.hasNext()){
				Arco<T> arco = (Arco<T>) it.next();				
				adyascentes.add(arco.getVerticeDestino());
			}
			
		}

		return adyascentes.iterator();
	}

	/**
	* Complejidad: O(h) donde h es la cantidad de arcos del grafo 
	* debido a que recorre los arcos de cada vértice y los agrega a una lista 
	*/

	@Override
	public Iterator<Arco<T>> obtenerArcos() {
		List<Arco<T>> arcos= new ArrayList<>();
		
		
		for( List<Arco<T>> lista : this.vertices.values()){
			arcos.addAll(lista);
		}

		return arcos.iterator();
	}

	/**
	* Complejidad: O(1) ya que el método vertices.get(verticesId) es propio de la estructura HashMap
	* y su complejidad es de O(1). Como no recorremos esos valores, la complejidad no aumenta y solo se guarda en una lista
	*/

	@Override
	public Iterator<Arco<T>> obtenerArcos(int verticeId) {
		List<Arco<T>> arcos= new ArrayList<>();
		
		if(this.contieneVertice(verticeId)){
			arcos= vertices.get(verticeId);
			
		}
		return arcos.iterator();
	}

}
