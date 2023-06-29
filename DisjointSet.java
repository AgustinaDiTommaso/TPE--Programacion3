
public class DisjointSet implements Cloneable{
    private int[] parent;
    private int[] rank;

    public DisjointSet(int size) {
        parent = new int[size];
        rank = new int[size];

        // Inicialmente, cada elemento es su propio padre y tiene rango 0
        for (int i = 0; i < size; i++) {
            parent[i] = i;
            rank[i] = 0;
        }
    }

    // Encuentra el representante (padre) del conjunto al que pertenece el elemento
    public int find(int x) {
        if (parent[x] != x) {            
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    // Unir dos conjuntos 
    public void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if (rootX == rootY) {
            return;
        }

        if (rank[rootX] < rank[rootY]) {
            parent[rootX] = rootY;
        } else if (rank[rootX] > rank[rootY]) {
            parent[rootY] = rootX;
        } else {
            parent[rootY] = rootX;
            rank[rootX]++;
        }
    }
    public int getCount(){// number of groups
        int ans = 0;
        for (int i = 0; i < parent.length; ++ i) {
            if (i == parent[i]) ans ++;
        }
        return ans;
    }

    public boolean mismoConjunto(int x, int y){
        return find(x)==find(y);
    }

    public DisjointSet clone(){
        try{
            DisjointSet cloned= (DisjointSet) super.clone();
            cloned.parent=parent.clone();
            cloned.rank= rank.clone();
            return cloned;
        }catch(CloneNotSupportedException e){
            throw new RuntimeException(e);
        }
    }
}
