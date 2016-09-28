package lab1;

import java.util.ArrayList;
import java.util.Random;

public class InputList {
	private ArrayList<Integer> inputs;
	
	public InputList(int N) {
		Random rand = new Random();
		inputs = new ArrayList<Integer>();
		for (int i = 0; i < N; i++) {
			inputs.add(i);				
//			System.out.println(inputs.get(i));
		}
		for (int i = 0; i < N; i++) {
			int j = rand.nextInt(N - i) + i;
			int newI = inputs.get(j);
			int newJ = inputs.get(i);
			inputs.set(j, newJ);
			inputs.set(i, newI);
		}	
		
	}
	
	public void remove(int node) {
		for (int i = 0; i < inputs.size(); i++) {
			if (inputs.get(i) == node) {
				inputs.remove(i);
				break;
			}
		}
	}
	
	public int pop(int i) {
//		System.out.println(inputs.size());
//		return inputs.remove(0);
		return inputs.get(i);
	}
	
	
}