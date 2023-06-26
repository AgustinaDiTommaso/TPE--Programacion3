import java.util.ArrayList;
import java.util.List;

public class ServBacktracking {
   private List<Arco<Integer>> tuneles;
    private List<Integer> estaciones;
    public List<Arco<Integer>> solucionParcial;
    public List<Arco<Integer>> mejorSolucion;
    public int distanciaTotal;
    public int distanciaActual;
    public  DisjointSet disjointSet;
    public int metrica;

    public ServBacktracking(List<Integer> estaciones, List<Arco<Integer>> tuneles){
		this.tuneles=tuneles;
        this.estaciones= estaciones;
        this.mejorSolucion= new ArrayList<>();
        this.distanciaTotal=0;
        this.distanciaActual=0;
        
    }
    public List<Arco<Integer>> backtracking() {

        //Se crea una solucion nueva
        List<Arco<Integer>> solucionActual= new ArrayList<Arco<Integer>>();

        // Se crea un clase DisjointSet para trabajar con conjuntos disjuntos
        disjointSet= new DisjointSet(estaciones.size());  
        int tunelActual=0;
        this.backtracking ( solucionActual,tunelActual);
        
        
        return mejorSolucion;
    }

    private void backtracking(List<Arco<Integer>> solActual, int tunelActual){
        //this.metrica++;
    	//Si la solucion no esta vacia y la cantidad de elementos es igual a la cantidad de vertices -1
        if(!solActual.isEmpty() && solActual.size()==(estaciones.size()-1)){
            //Si la distancia actual es menor que la distancia total o aun no fue guardada ninguna
            if(this.distanciaTotal==0 || this.distanciaActual< this.distanciaTotal){
            	// Guarda la distancia actual en la variable distanciaTotal 
                this.distanciaTotal=this.distanciaActual;
                //Crea la mejor solucion
                this.mejorSolucion= new ArrayList<Arco<Integer>>(solActual);
            
            }


        }else{
            //por cada arco que tenga el vertice que viene por parámetro
           for(int i=tunelActual; i<tuneles.size();i++){
                this.metrica++;
                Arco<Integer> tunel = tuneles.get(i);   
		   
		//PODA: Si la distancia actual más las distancia que se quiere agregar ya es mayor a la mejor distancia total no continua, 
                //a no ser que la mejor distancia total todavia no haya sido ingresada  
                if((this.distanciaActual+tunel.getEtiqueta())<this.distanciaTotal || this.distanciaTotal==0 ){
	                // verifico que no estén dentro del mismo conjunto
	                int x= tunel.getVerticeOrigen()-1;
	                int y= tunel.getVerticeDestino()-1;         
	                
	                int padreOrigen= disjointSet.find(tunel.getVerticeOrigen()-1);
	                int padreDestino= disjointSet.find(tunel.getVerticeDestino()-1);
	
	                if(padreOrigen !=padreDestino){
	                    //clono el conjunto disjunto para guardar su estado
	                    DisjointSet disjointSetClone= disjointSet.clone();
	                    //Si no estaban unidos, los uno
	                    disjointSet.union(x, y); 
	
	                    //Añado el arco a la solucion                      
	                    solActual.add(tunel);
	                    //Sumo la distancia
	                    int distancia= tunel.getEtiqueta();
	                    this.distanciaActual+=distancia;
	
	                    // LLamado recursivo
	                    backtracking(solActual, i+1);
	
	                    // resto la distancia que habia sido agregada
	                    this.distanciaActual-=distancia;
	
	                    //remueve el último elemento añadido a la solución
	                    solActual.remove(solActual.size()-1);		  
	                    //Vuelvo atras el conjunto disjunto         
	                    disjointSet = disjointSetClone;
	
	                }            
		}

            }
		
        }
    }
}
