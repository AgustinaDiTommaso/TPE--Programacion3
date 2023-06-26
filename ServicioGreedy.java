
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class ServicioGreedy {
    private List<Arco<Integer>> tuneles;
    private List<Integer> estaciones;
    public List<Arco<Integer>> solucion;
    private int distancia;
    public int metrica;

    
    public ServicioGreedy(List<Integer> estaciones, List<Arco<Integer>> tuneles){
        
        this.tuneles=tuneles;
        this.estaciones= estaciones;
        this.metrica= 0;
        this.distancia=0;
        this.solucion= new ArrayList<>();   
    }


    public List<Arco<Integer>> dijkstra(){//cambiar nombre
        //ordena según la distancia
        Collections.sort(tuneles);
        
        DisjointSet disjointSet= new DisjointSet(estaciones.size());  

        for(int i=0; i<tuneles.size();i++){
            //this.metrica++;
            Arco<Integer> tunel= this.tuneles.get(i);          
            
            
            //busca el padre de origen y el padre del destino
            //si son distintos 

                if(solucion.size()< (estaciones.size()-1)){
                    //this.metrica++;

                    int padreOrigen= disjointSet.find(tunel.getVerticeOrigen()-1);
                    int padreDestino= disjointSet.find(tunel.getVerticeDestino()-1);

                    
                    if(padreOrigen!=padreDestino){
                        disjointSet.union((tunel.getVerticeDestino()-1), (tunel.getVerticeOrigen()-1));
                        this.metrica++;
                        solucion.add(tunel);
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
