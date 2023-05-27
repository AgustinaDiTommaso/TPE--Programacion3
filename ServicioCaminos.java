

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;



public class ServicioCaminos {

	private Grafo<Integer> grafo;
	private int origen;
	private int destino;
	private int lim;
	public List<List<Integer>> caminos;
	public Set<Arco<Integer>> visitados;
	
	
	public ServicioCaminos(Grafo<Integer> grafo, int origen, int destino, int lim) {
		this.grafo = grafo;
		this.origen = origen;
		this.destino = destino;
		this.lim = lim;
		this.caminos= new ArrayList<>();
		this.visitados= new HashSet<Arco<Integer>>();
	}

	
	
	public List<List<Integer>> caminos() {
		this.visitados.clear();
		
		ArrayList<Integer> caminoParcial = new ArrayList<Integer>();
		caminoParcial.add(this.origen);
		this.getCamino(this.origen, caminoParcial);
		return  caminos;
	}


	private void getCamino(int verticeO, ArrayList<Integer> caminoParcial) {
		if (verticeO == this.destino) {
			
				List<Integer> caminoActual= new ArrayList<Integer>(caminoParcial);
				caminos.add(caminoActual);
			
		} 
		else {
			
			
			Iterator<Integer> it_ady= this.grafo.obtenerAdyacentes(verticeO);
			
				while(it_ady.hasNext()){
				
					Integer vertice_aux= it_ady.next();

					Arco<Integer> arcoActual= this.grafo.obtenerArco(verticeO, vertice_aux);
					
					if(!visitados.contains(arcoActual)){
										
						if(visitados.size()<this.lim){
							this.visitados.add(arcoActual);
							caminoParcial.add(vertice_aux);
							this.getCamino(vertice_aux, caminoParcial);
							caminoParcial.remove(caminoParcial.size()-1);
							this.visitados.remove(arcoActual);
						}
					}
				}
				
			}
	}
}