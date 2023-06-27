import java.util.ArrayList;
import java.util.List;

public class ServBacktracking {
    private List<Arco<Integer>> tuneles;
    private List<Integer> estaciones;
    public List<Arco<Integer>> mejorSolucion;
    public int distanciaMinima;
    public int distanciaActual;
    public int metrica;

    public ServBacktracking(List<Integer> estaciones, List<Arco<Integer>> tuneles) {
        this.tuneles = tuneles;
        this.estaciones = estaciones;
        this.mejorSolucion = new ArrayList<>();
        this.distanciaMinima = Integer.MAX_VALUE;
        this.distanciaActual = 0;
        this.metrica = 0;

    }

    public List<Arco<Integer>> backtracking() {
        // Se crea una solucion nueva
        List<Arco<Integer>> solucionActual = new ArrayList<Arco<Integer>>();

        // Se crea un clase DisjointSet para trabajar con conjuntos disjuntos
        DisjointSet disjointSet = new DisjointSet(estaciones.size());

        int tunelActual = 0;
        this.backtracking(solucionActual, tunelActual, disjointSet);

        return mejorSolucion;
    }

    private void backtracking(List<Arco<Integer>> solActual, int tunelActual, DisjointSet disjointSet) {
        this.metrica++;

        // Si la cantidad de elementos de la solución es igual a la cantidad de
        // estaciones -1
        if (solActual.size() == (estaciones.size() - 1)) {

            // Guarda la distancia actual en la variable distanciaTotal
            this.distanciaMinima = this.distanciaActual;
            // Crea la mejor solucion
            this.mejorSolucion = new ArrayList<Arco<Integer>>(solActual);

        } else {
            
            // Si la posición del túnel actual esta dentro del rango
            if (tunelActual < tuneles.size()) {

                Arco<Integer> tunel = tuneles.get(tunelActual);

                // PODA: Si la distancia actual más las distancia que se quiere agregar ya es
                // mayor a la distancia mínima guardada no continúa
                if ((this.distanciaActual + tunel.getEtiqueta()) < this.distanciaMinima) {
                    
                    // Verifica que los estaciones no estén dentro del mismo conjunto
                    int x = tunel.getVerticeOrigen() - 1;
                    int y = tunel.getVerticeDestino() - 1;

                    if (!(disjointSet.mismoConjunto(x, y))) {

                        // Clono el conjunto disjunto para guardar su estado
                        DisjointSet disjointSetClone = disjointSet.clone();

                        //Unión de las estaciones
                        disjointSet.union(x, y);

                        // Añade el túnel a la solución
                        solActual.add(tunel);

                        // Suma la distancia del túnel actual a la distanciaTotal
                        this.distanciaActual += tunel.getEtiqueta();

                        //Incrementa la posición en la lista de tuneles
                        tunelActual++;

                        // LLamado recursivo
                        backtracking(solActual, tunelActual, disjointSet);

                        // Resta la distancia que habia sido agregada
                        this.distanciaActual -= tunel.getEtiqueta();

                        // Remueve el último elemento añadido a la solución
                        solActual.remove(solActual.size() - 1);

                        // Vuelve atrás el estado del conjunto disjunto
                        disjointSet = disjointSetClone;

                    }
                }
                //Continúa el procedimiento con el siguiente túnel
                if (tunelActual + 1 < tuneles.size()) {
                    tunelActual++;
                    backtracking(solActual, tunelActual, disjointSet);
                }
            }

        }
    }

    public int getDistanciaTotal() {
        return this.distanciaMinima;
    }

    public int getMetrica() {
        return this.metrica;
    }
}
