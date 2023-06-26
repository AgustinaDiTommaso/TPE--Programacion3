
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



public class CSVReader {

	private String path;
	//--	
	//public Grafo<Integer> grafo;
	public List<Arco<Integer>> tuneles;
	public List<Integer> estaciones;
	
	public CSVReader(String path) {
		this.path = path;
		//--
		//this.grafo= new GrafoNoDirigido<Integer>();
		this.tuneles = new ArrayList<Arco<Integer>>();
		this.estaciones = new ArrayList<Integer>();
	}
	
	public void read() {
		
		// Obtengo una lista con las lineas del archivo
		// lines.get(0) tiene la primer linea del archivo
		// lines.get(1) tiene la segunda linea del archivo... y así
		ArrayList<String[]> lines = this.readContent();
		
		for (String[] line: lines) {
			// Cada linea es un arreglo de Strings, donde cada posicion guarda un elemento
			Integer origen = Integer.parseInt(line[0].trim().substring(1));
			Integer destino = Integer.parseInt(line[1].trim().substring(1));
			Integer etiqueta = Integer.parseInt(line[2].trim());
			
			// Aca instanciar lo que necesiten en base a los datos leidos
			//---
		
			tuneles.add(new Arco<Integer>(origen, destino, etiqueta));

			 if(!estaciones.contains(origen)){
				estaciones.add(origen);
			 }
			 if(!estaciones.contains(destino)){
				estaciones.add(destino);
			 }
			 /*
			//agrega vertice y arco al grafo
			this.grafo.agregarVertice(origen);
			this.grafo.agregarVertice(destino);
			this.grafo.agregarArco(origen, destino, etiqueta);*/
		}
		
	}

	private ArrayList<String[]> readContent() {
		ArrayList<String[]> lines = new ArrayList<String[]>();

		File file = new File(this.path);
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;
		try {
			fileReader = new FileReader(file);
			bufferedReader = new BufferedReader(fileReader);
			String line = null;
			while ((line = bufferedReader.readLine()) != null) {
				line = line.trim();
				lines.add(line.split(";"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (bufferedReader != null)
				try {
					bufferedReader.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
		}
		
		return lines;
	}
	/* 	
	public Grafo<Integer> getInfo(){
		return this.grafo;//rompe privacidad??
	}*/
	public List<Arco<Integer>> getTuneles(){
		return this.tuneles;
	}
	public List<Integer> getEstaciones(){
		return this.estaciones;
	}	


}
