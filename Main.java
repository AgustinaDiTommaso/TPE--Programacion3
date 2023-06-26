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
		for (String path : paths) {
			CSVReader reader= new CSVReader(path);
			reader.read();
			//Grafo<Integer> g = reader.getInfo();

			List<Arco<Integer>> tuneles= reader.getTuneles();
    		List<Integer> estaciones= reader.getEstaciones();

			ServicioGreedy greedy= new ServicioGreedy(estaciones,tuneles);
			System.out.println("GREEDY");
			System.out.println(greedy.dijkstra());
			System.out.println(greedy.getDistancia()+" KM");
			System.out.println("Métrica: "+greedy.metrica);

			System.out.println(" ");

			ServBacktracking back= new ServBacktracking(estaciones, tuneles);
			System.out.println("BACKTRACKING");
			System.out.println(back.backtracking());
			System.out.println(back.distanciaTotal+" KM");
			System.out.println("Métrica: "+back.metrica);
			System.out.println("---------");
		}
		/* 
		String path1 = "../datasets/dataset1.txt";
		CSVReader reader1 = new CSVReader(path1);
		reader1.read();

		String path2 = "../datasets/dataset2.txt";
		CSVReader reader2 = new CSVReader(path2);
		reader2.read();


		String path3 = "../datasets/dataset3.txt";
		CSVReader reader3 = new CSVReader(path3);
		reader3.read();*/

		/*
		Grafo<Integer> g1= reader1.getInfo() ;
		Grafo<Integer> g2= reader2.getInfo() ;
		Grafo<Integer> g3= reader3.getInfo() ;
		*/

		/*
		ServGreedy greedy= new ServGreedy(g);
		System.out.println(greedy.dijkstra());
		System.out.println(greedy.getDistancia());
		*/

		/*
		ServBacktracking back= new ServBacktracking(g);
		System.out.println(back.caminos());
		System.out.println(back.distanciaTotal);
		*/

		/*
		ServicioBack back= new ServicioBack(g);
		System.out.println(back.caminos());
		System.out.println(back.distanciaTotal);
		*/
		

		
	}


}
