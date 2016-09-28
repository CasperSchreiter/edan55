package lab2;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class R {
	
	public R() {

		HashMap<Integer, Vertice> vertices = new HashMap<Integer, Vertice> ();
		Random rand = new Random();
		PrintWriter writer = null;;
		
		try {
//			Scanner scan = new Scanner(new File("input/pw09_100.9.txt"));
			Scanner scan = new Scanner(new File("input/matching_1000.txt"));
			writer = new PrintWriter("output.txt", "UTF-8");
	


			int nV = scan.nextInt();
			int nE = scan.nextInt();
			
			for (int i = 0; i < nV; i++) {
				vertices.put(i+1, new Vertice(i+1));
			}
			
			while (scan.hasNextInt()) {
				int vertice = scan.nextInt();
				int to = scan.nextInt();
				int weight = scan.nextInt();
				vertices.get(vertice).addEdge(to, weight);
				vertices.get(to).addEdge(vertice, weight);
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int N = 100;
		int avg = 0;
		
		
		
		for (int j = 0; j < N; j++) {
			int totalWeight = 0;
			HashMap<Integer, Vertice> vertixs = (HashMap<Integer, Vertice>) vertices.clone();
			HashMap<Integer, Vertice> a = new HashMap<Integer, Vertice> ();
			int size = vertixs.size();
			
			for (int i = 0; i < size; i++) {
				if (rand.nextBoolean()) {
					a.put(i+1, (vertixs.remove(i+1)));
				}
			}
				
			for (Vertice v: a.values()) {
				for (Integer to: v.edges.keySet()) {
					if (vertixs.containsKey(to)) {
						totalWeight += v.getWeight(to);
					}
					
				}
			}
			writer.println(totalWeight);
			avg += totalWeight;
			
		}
		System.out.println(avg/N);
		writer.close();
		
	}
	
	
	

	private class Vertice {
		
		HashMap<Integer, Integer> edges;
		
		public Vertice(int index) {
			edges = new HashMap<Integer, Integer>();
		}
		
		public void addEdge(int to, int weight) {
			edges.put(to, weight);
		}
		
		public int getWeight(int to) {
			return edges.get(to);
		}
		
		
	}
	
	public static void main(String[] args) {
		new R();
	}
}