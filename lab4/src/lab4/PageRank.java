package lab4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class PageRank {

	private HashMap<Integer, ArrayList<Integer>> graph;
	private Random rand;
	private int dumping = 85;

	public PageRank(String input) {

		try {

			Scanner scan = new Scanner(new File(input));

			int n = scan.nextInt();
			graph = new HashMap<Integer, ArrayList<Integer>>();

			while (scan.hasNext()) {
				int node = scan.nextInt();
				int to = scan.nextInt();

				if (graph.containsKey(new Integer(node))) {
					graph.get(node).add(to);
					if (!graph.containsKey(to)) {
						graph.put(to, new ArrayList<Integer>());
					}
				} else {
					ArrayList<Integer> list = new ArrayList<Integer>();
					list.add(to);
					graph.put(node, list);
					if (!graph.containsKey(to)) {
						graph.put(to, new ArrayList<Integer>());
					}
				}

			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		rand = new Random();

	}

	public void randomSurf(int iterations) {

		HashMap<Integer, Integer> res = new HashMap<Integer, Integer>();

		int size = graph.get(0).size();
		int r = 0;
		int to = 0;;
		if (size != 0) {
			r = rand.nextInt(size);
			to = graph.get(0).get(r);
		} else {
			to = 0;
		}


		for (int i = 0; i < iterations; i++) {

			if (!res.containsKey(to)) {
				res.put(to, 1);
			} else {
				int rel = res.get(to);
				res.put(to, rel + 1);
			}
			if (graph.get(to) == null) {
				System.out.println(to);
			}
			size = graph.get(to).size();
			int random = rand.nextInt(101);

			if (random < dumping) {
				if (size == 0) {
					size = graph.keySet().size();
					to = rand.nextInt(size);
				} else {
					to = graph.get(to).get(rand.nextInt(size));
				}

			} else {
				size = graph.keySet().size();
				to = rand.nextInt(size);
			}
		}

		for (Integer key : res.keySet()) {
			System.out.println("Page: " + key + " \tvisited: " + res.get(key));
		}
		System.out.println();
	}

	public static void main(String[] args) {

		if (args.length != 1) {
			System.out.println("Needs to give number of iterations");
			System.exit(1);
		}

		int iterations = 0;

		try {

			iterations = Integer.parseInt(args[0]);

		} catch (NumberFormatException e) {
			System.out.println("Only numbers allowed");
			System.exit(1);
		}


		Scanner scan = new Scanner(System.in);
		File[] files = new File("input/").listFiles();

		while (true) {
			for (int i = 0; i < files.length; i++) {
				if (files[i].isFile()) {
					System.out.println("Press " + i + " for " + files[i].getName() + " as input file");
				}
			}
			System.out.println("Press 42 for quit");
			int choice;
			String filename = "";
			
			
			try {
				choice = scan.nextInt();
				
				if (choice == 42) {
					System.out.println("HEJDÅ");
					System.exit(0);
				} else {
					filename = files[choice].getPath();
				}
				
			} catch (InputMismatchException e) {
				System.out.println("Only numbers!!");
				continue;
			} catch (ArrayIndexOutOfBoundsException e) {
				System.out.println("Only right numbers!!!");
				continue;
			}
			
			PageRank pr = new PageRank(filename);
			pr.randomSurf(iterations);

		}

	}

}
