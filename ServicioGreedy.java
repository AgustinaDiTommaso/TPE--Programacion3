
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class ServicioGreedy {
    private List<Arco<Integer>> tuneles;
    private List<Integer> estaciones;
    public List<Arco<Integer>> solucion;
    public int distancia;
    public int metrica;

    
    public ServicioGreedy(List<Integer> estaciones, List<Arco<Integer>> tuneles){
        
        this.tuneles=tuneles;
        this.estaciones= estaciones;
        this.metrica= 0;
        this.distancia=0;
        this.solucion= new ArrayList<>();   
    }


    public List<Arco<Integer>> greedy(){
        // Ordena según la distancia de menor a mayor-- Complejidad O(n log n) donde n es la cantidad de túneles
        Collections.sort(tuneles);
        
        //Crea un conjunto disjunto
        DisjointSet disjointSet= new DisjointSet(estaciones.size());  

        //Por cada túnel existente
        for(int i=0; i<tuneles.size();i++){ //O(n*e) donde n es la cantidad de túneles y  e es la cantidad de estaciones
            Arco<Integer> tunel= this.tuneles.get(i);          
            this.metrica++;
            
            //Busca el padre de origen y el padre del destino          

            if(solucion.size()< (estaciones.size()-1)){

                int x= tunel.getVerticeOrigen()-1;
                int y= tunel.getVerticeDestino()-1;

                //Si son distintos los padres
                if(!(disjointSet.mismoConjunto(x,y))){ //O(e) donde e es la cantidad de estaciones

                    //Une los dos valores
                    disjointSet.union(x, y);    //O(e) donde e es la cantidad de estaciones

                    //Añade el túnel a la solución               
                    solucion.add(tunel);

                    //Incrementa la distancia
                    this.distancia+=tunel.getEtiqueta();
                }
            }
        }
            return solucion;
    }
           
    public int getDistancia(){
        return this.distancia;
    }
    public int getMetrica(){
        return this.metrica;
    }
}
