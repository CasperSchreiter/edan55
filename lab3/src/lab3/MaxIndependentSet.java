package lab3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

public class MaxIndependentSet {
	HashMap<Integer, ArrayList<Integer>> graph;
	int rCalls;

	public MaxIndependentSet(String input) {

		try {

			Scanner scan = new Scanner(new File(input));

			int n = scan.nextInt();
			graph = new HashMap<Integer, ArrayList<Integer>>();

			for (int i = 0; i < n; i++) {
				graph.put(i + 1, new ArrayList<Integer>());
				for (int j = 0; j < n; j++) {
					if (scan.nextInt() == 1) {
						graph.get(i + 1).add(j + 1);
					}
				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	public int run(int choice) {
		int result = 0;
		switch (choice) {
		case 0:
			result = R0(graph);
			break;
		case 1:
			result = R1(graph);
			break;
		case 2:
			result = R2(graph);
			break;
		}
		return result;
	}

	public int R0(HashMap<Integer, ArrayList<Integer>> g) {
		rCalls++;
		if (g.isEmpty()) {
			return 0;
		}
		for (Integer i : g.keySet()) {
			if (g.get(i).isEmpty()) {
				g.remove(i);
				return 1 + R0(g);
			}
		}
		int max = 0;
		int vertice = 0;
		for (Integer i : g.keySet()) {
			if (g.get(i).size() > max) {
				vertice = i;
				max = g.get(i).size();
			}
		}

		HashMap<Integer, ArrayList<Integer>> clone = new HashMap<Integer, ArrayList<Integer>>();

		for (Integer i : g.keySet()) {
			ArrayList<Integer> ints = new ArrayList<>();
			for (Integer j : g.get(i)) {
				ints.add(j);
			}
			clone.put(i, ints);
		}

		ArrayList<Integer> copy = new ArrayList<Integer>(clone.get(vertice));

		for (Integer i : copy) {
			ArrayList<Integer> list = clone.get(i);
			ArrayList<Integer> list2 = g.get(i);

			for (Integer j : list) {
				clone.get(j).remove(new Integer(i));

			}
			list2.remove(new Integer(vertice));
			clone.remove(i);
		}
		clone.remove(vertice);
		g.remove(vertice);

		return Math.max(1 + R0(clone), R0(g));

	}

	public int R1(HashMap<Integer, ArrayList<Integer>> g) {
		rCalls++;
		if (g.isEmpty()) {
			return 0;
		}

		for (Integer i : g.keySet()) {
			if (g.get(i).size() == 1) {
				Integer remove = g.get(i).get(0);
				ArrayList<Integer> list = g.get(remove);
				for (Integer j : list) {
					g.get(j).remove(new Integer(remove));
				}
				g.remove(remove);
				g.remove(i);
				return 1 + R1(g);

			}
		}

		for (Integer i : g.keySet()) {
			if (g.get(i).isEmpty()) {
				g.remove(i);
				return 1 + R1(g);
			}
		}
		int max = 0;
		int vertice = 0;
		for (Integer i : g.keySet()) {
			if (g.get(i).size() > max) {
				vertice = i;
				max = g.get(i).size();
			}
		}

		HashMap<Integer, ArrayList<Integer>> clone = new HashMap<Integer, ArrayList<Integer>>();

		for (Integer i : g.keySet()) {
			ArrayList<Integer> ints = new ArrayList<>();
			for (Integer j : g.get(i)) {
				ints.add(j);
			}
			clone.put(i, ints);
		}

		ArrayList<Integer> copy = new ArrayList<Integer>(clone.get(vertice));

		for (Integer i : copy) {
			ArrayList<Integer> list = clone.get(i);
			ArrayList<Integer> list2 = g.get(i);

			for (Integer j : list) {
				clone.get(j).remove(new Integer(i));

			}
			list2.remove(new Integer(vertice));
			clone.remove(i);
		}
		clone.remove(vertice);
		g.remove(vertice);

		return Math.max(1 + R1(clone), R1(g));
	}

	public int R2(HashMap<Integer, ArrayList<Integer>> g) {
		rCalls++;
		if (g.isEmpty()) {
			return 0;
		}

		for (Integer v : g.keySet()) {
			if (g.get(v).size() == 2) {
				Integer u = g.get(v).get(0);
				Integer w = g.get(v).get(1);

				ArrayList<Integer> list1 = g.get(u);
				ArrayList<Integer> list2 = g.get(w);

				if (g.get(u).contains(w)) {
					for (Integer j : list1) {
						g.get(j).remove(new Integer(u));
					}
					for (Integer j : list2) {
						g.get(j).remove(new Integer(w));
					}

					g.remove(u);
					g.remove(w);
					g.remove(v);
					return 1 + R2(g);

				} else {
					for (Integer i : list2) {
						g.get(i).remove(new Integer(w));

						if (!g.get(i).contains(new Integer(u))) {
							g.get(i).add(u);
							list1.add(i);
						}
					}
					g.get(u).remove(v);
					g.remove(w);
					g.remove(v);
					return 1 + R2(g);
				}

			}
		}

		for (Integer i : g.keySet()) {
			if (g.get(i).size() == 1) {
				Integer remove = g.get(i).get(0);
				ArrayList<Integer> list = g.get(remove);
				for (Integer j : list) {
					g.get(j).remove(new Integer(remove));
				}
				g.remove(remove);
				g.remove(i);
				return 1 + R2(g);
			}
		}

		for (Integer i : g.keySet()) {
			if (g.get(i).isEmpty()) {
				g.remove(i);
				return 1 + R2(g);
			}
		}
		int max = 0;
		int vertice = 0;
		for (Integer i : g.keySet()) {
			if (g.get(i).size() > max) {
				vertice = i;
				max = g.get(i).size();
			}
		}

		HashMap<Integer, ArrayList<Integer>> clone = new HashMap<Integer, ArrayList<Integer>>();

		for (Integer i : g.keySet()) {
			ArrayList<Integer> ints = new ArrayList<>();
			for (Integer j : g.get(i)) {
				ints.add(j);
			}
			clone.put(i, ints);
		}

		ArrayList<Integer> copy = new ArrayList<Integer>(clone.get(vertice));

		for (Integer i : copy) {
			ArrayList<Integer> list = clone.get(i);
			ArrayList<Integer> list2 = g.get(i);

			for (Integer j : list) {
				clone.get(j).remove(new Integer(i));

			}
			list2.remove(new Integer(vertice));
			clone.remove(i);
		}
		clone.remove(vertice);
		g.remove(vertice);

		return Math.max(1 + R2(clone), R2(g));
	}
	

	public static void main(String[] args) {
		int n = 130;
		double sum = 0;
		int choice = 2;
		
		long startTime = 0;
		long endTime = 0;

		
		
		int k = 1;
//		int k = 8;
//		int k = 11;
		
		for(int i = 0; i < k; i++) {
			String s = "input/g"+n+".in";
			MaxIndependentSet m = new MaxIndependentSet(s);
			startTime = System.nanoTime();
			int run = m.run(choice);
			endTime = System.nanoTime();
			int rec = m.rCalls;
			double c = Math.pow(rec, (1.0/(double)n));
			sum += c;
			System.out.println(s + "\t" + "Rec: " + rec + "\tindset: " + run + "\tc: " + c);
			n += 10;
		}
		System.out.println("C: " + (double)sum/(double)k);
		
		long duration = (endTime - startTime); 
		System.out.println("Time: " + (duration / 1000000) + "ms");
		
	}

}
