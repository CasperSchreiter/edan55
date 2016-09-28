package lab1;

import java.util.Random;

import java.util.ArrayList;
import java.util.Random;

public class MarkingTrees {

	private static int CHOICE = 1;
//	 private static int N = 3;
//	 private static int N = 7;
//	 private static int N = 15;
//	private static int N = 31;
//	 private static int N = 63;
//	 private static int N = 127;
//	 private static int N = 255;
//	 private static int N = 511;
//	 private static int N = 1023;
//	 private static int N = 524287;
	 private static int N = 1048575;
	private static double iterations = 1;
	// private static Random rand;

	public static void main(String[] args) {
		Tree tree = null;
		double totalrounds = 0;
		double average = 0;
		// rand = new Random();
		InputList inputs;
		Random rand = new Random();
		double varSum = 0;
		double[] var = new double[(int) iterations];
		switch (CHOICE) {
		case 1:

			for (int i = 0; i < iterations; i++) {
				int rounds = 0;
				tree = new Tree(N);
				while (tree.markedNodes() < N) {
					rounds++;
					tree.markNode((int) (Math.random() * N), rounds);
				}
				var[i] = rounds;
				totalrounds += rounds;
			}
			average = totalrounds / iterations;
			
			
			System.out.println("Average: " + average);

			
			
			varSum = 0;
			for (int i = 0; i < iterations; i++) {
				var[i] = Math.pow(var[i] - average, 2);
				varSum += var[i];
			}
			varSum = varSum / iterations;
			System.out.println("Deviation: " + Math.sqrt(varSum));

			break;
		case 2:
			for (int i = 0; i < iterations; i++) {
				int rounds = 0;
				tree = new Tree(N);
				inputs = new InputList(N);
				int index = 0;
				while (tree.markedNodes() < N) {
					rounds++;
					int s = inputs.pop(index);
					tree.markNode(s, rounds);
					index++;
				}
				var[i] = rounds;
				totalrounds += rounds;
			}
			average = totalrounds / iterations;
			
			
			System.out.println("Average: " + average);

			
			
			varSum = 0;
			for (int i = 0; i < iterations; i++) {
				var[i] = Math.pow(var[i] - average, 2);
				varSum += var[i];
			}
			varSum = varSum / iterations;
			System.out.println("Deviation: " + Math.sqrt(varSum));
			break;
		/*
		 * case 3: for (int i = 0; i < iterations; i++) { int rounds = 0; tree =
		 * new Tree(N); Integer[] nodeSelect; while (tree.markedNodes() < N) {
		 * rounds++; nodeSelect = tree.unmarkedArray(); int select =
		 * rand.nextInt(nodeSelect.length);
		 * tree.markNode(nodeSelect[select].intValue(), rounds); } totalrounds
		 * += rounds; } average = totalrounds / iterations; System.out.println(
		 * "Average: " + average);
		 * 
		 * 
		 * break;
		 */
		case 3:
			for (int i = 0; i < iterations; i++) {
				int rounds = 0;
				TreeR3 treeR3 = new TreeR3(N);				
				InputList2 input = new InputList2(treeR3, N);
				while (treeR3.markedNodes() < N) {					
					rounds++;
					treeR3.markNode(input.pop(), rounds);
					
				}
				var[i] = rounds;
				totalrounds += rounds;
			}
			average = totalrounds / iterations;
			
			
			System.out.println("Average: " + average);

			
			
			varSum = 0;
			for (int i = 0; i < iterations; i++) {
				var[i] = Math.pow(var[i] - average, 2);
				varSum += var[i];
			}
			varSum = varSum / iterations;
			System.out.println("Deviation: " + Math.sqrt(varSum));

			break;
		default:
			break;
		}

	}

	// public static ArrayList<Integer> knuthShuffle() {
	// ArrayList<Integer> inputs = new ArrayList<Integer>();
	// for (int i = 0; i < N; i++) {
	// inputs.add(i);
	// }
	// for (int i = 0; i < N - 2; i++) {
	// int j = rand.nextInt(N - i) + i;
	// int newI = inputs.get(j);
	// int newJ = inputs.get(i);
	// inputs.set(j, newJ);
	// inputs.set(i, newI);
	// }
	// return inputs;
	// }

}