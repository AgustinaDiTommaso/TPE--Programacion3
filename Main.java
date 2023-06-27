import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		ArrayList<String> paths = new ArrayList<>(Arrays.asList(
			"../datasets/dataset1.txt",
			"../datasets/dataset2.txt",
			"../datasets/dataset3.txt"
		));
		int i=1;
		for (String path : paths) {
			
			CSVReader reader= new CSVReader(path);
			reader.read();
			

			List<Arco<Integer>> tuneles= reader.getTuneles();
    		List<Integer> estaciones= reader.getEstaciones();
			System.out.println("Dataset "+i);
			

			ServicioGreedy greedy= new ServicioGreedy(estaciones,tuneles);
			System.out.println("GREEDY");
			System.out.println(greedy.greedy());
			System.out.println(greedy.getDistancia()+" KM");
			System.out.println("Métrica: "+greedy.getMetrica());

			System.out.println(" ");
			
			System.out.println("Dataset "+i);
			i++;
			ServBacktracking back= new ServBacktracking(estaciones, tuneles);
			System.out.println("BACKTRACKING");
			System.out.println(back.backtracking());
			System.out.println(back.getDistanciaTotal()+" KM");
			System.out.println("Métrica: "+back.getMetrica());
			System.out.println("---------");
			
		}
	}


}
