package lab1;

import java.util.ArrayList;
import java.util.Random;

import lab1.TreeR3.Node;

public class InputList2 {
	private ArrayList<Integer> inputs;
	private int index;
	private Random rand;
	private int N;
	private ArrayList<Node> tree;

	public InputList2(TreeR3 tree, int N) {
		this.tree = tree.tree;
		index = 0;
		this.N = N;
		rand = new Random();
		inputs = new ArrayList<Integer>();
		for (int i = 0; i < N; i++) {
			inputs.add(i);
			// System.out.println(inputs.get(i));
		}
		// for (int i = 0; i < N; i++) {
		// int j = rand.nextInt(N - i) + i;
		// int newI = inputs.get(j);
		// int newJ = inputs.get(i);
		// inputs.set(j, newJ);
		// inputs.set(i, newI);
		// }
	}

	public int pop() {

		
		
		int is = (N - index);
		int select = rand.nextInt(is) + index;
//		if (is != 0) {
//			select = rand.nextInt(is) + index;
//		} else {
//			select = 0;
//			System.out.println("KRUPPCPDAMP???");
//		}
		while (tree.get(inputs.get(select)).marked) {			
//			System.out.println("index: " + index + " select: " + select);
			
//			StringBuilder sb = new StringBuilder();
//			for (int i = 0; i < inputs.size(); i++) {
//				sb.append(inputs.get(i) + " ");
//			}
//			System.out.println(sb.toString());
		
			int i = inputs.get(select);
			int s = inputs.get(index);
			inputs.set(index, i);
			inputs.set(select, s);
			index++;

			is = (N - index);
			

//			if (is != 0) {
//				select = rand.nextInt(is) + index;
//			} else {
//				select = 0;
//			}
		}
//		System.out.println("I have found an unmarked node with index nbr: " + select);

		int i = inputs.get(select);
		int s = inputs.get(index);
		inputs.set(index, i);
		inputs.set(select, s);
		index++;
		return i;
	}

}