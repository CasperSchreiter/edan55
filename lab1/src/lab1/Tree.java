package lab1;

import java.util.HashSet;
/*
public class Tree {
	private boolean[] nodes;
	private int N;
	private int nodesMarked;
	private HashSet<Integer> unmarkedNodes;

	public Tree(int N) {
		unmarkedNodes = new HashSet<Integer>();
		nodesMarked = 0;
		this.N = N;
		nodes = new boolean[N];
		for (int i = 0; i < N; i++) {
			unmarkedNodes.add(i);
		}
	}

	public int markedNodes() {
		return nodesMarked;
	}

	public void markNode(int id, int r) {
		if (nodes[id]) {
			return;
		}
		tellFamily(id);
		tellParent((id-1)/2);

//		StringBuilder sb = new StringBuilder();
//		sb.append(r + " i=" + id + ": ");
//		for (int i = 0; i < nodes.length; i++) {
//			if (nodes[i]) {
//				sb.append("| X ");
//			} else {
//				sb.append("| 0 ");
//			}
//
//		}
//		sb.append("|");
//		System.out.println(sb.toString());
	}

	public void mark(int i) {
		if (!nodes[i]) {
			nodes[i] = true;
			nodesMarked++;
			unmarkedNodes.remove(i);
		}
	}

	public void tellFamily(int id) {
		mark(id);
		int leftChildIndex = 2 * id + 1;
		int rightChildIndex = 2 * id + 2;
		
		if (leftChildIndex < N) {
			if (nodes[leftChildIndex]) {
				tellFamily(rightChildIndex);
			} else if (nodes[rightChildIndex]) {
				tellFamily(leftChildIndex);
			}
		}


	}

	public void tellParent(int id) {
		int leftChildIndex = 2 * id + 1;
		int rightChildIndex = 2 * id + 2;

		if (id == 0) {
			if (nodes[leftChildIndex] && nodes[rightChildIndex]) {
				mark(id);
			}
		}

		if (nodes[id]) {
			tellFamily(id);
		} else if (nodes[leftChildIndex] && nodes[rightChildIndex]) {
			mark(id);
			tellParent((id - 1) / 2);
		}
	}
	
	public Integer[] unmarkedArray() {
		return  unmarkedNodes.toArray(new Integer[unmarkedNodes.size()]);
	}
	

}*/

import java.util.ArrayList;

public class Tree {
	private ArrayList<Node> tree;
	private boolean[] checkedThisRound;
	private int nodes;
	private int nodesMarked;

	
	public Tree(int N) {
	
		nodesMarked = 0;
		nodes = N;
		tree = new ArrayList<Node>();

		for (int i = 0; i < nodes; i++) {
			tree.add(new Node());
		}
	}

	public int markedNodes() {
		return nodesMarked;
	}
	

	public void markNode(int id, int r) {
		Node node = tree.get(id);
		if (node.marked) {
			//System.out.println(r + " i=" + id + " with MarkedNodes = " + nodesMarked);
			return;
		}
		nodesMarked++;
		node.setMarked();
		tellFamily(id);
		
//		 StringBuilder sb = new StringBuilder();
//	        sb.append(r + " i=" + id + ": ");
//	        for (int i = 0; i < tree.size(); i++) {
//	            if (tree.get(i).marked) {
//	                sb.append("| X ");
//	            } else {
//	                sb.append("| 0 ");
//	            }
//	 
//	        }
//	        sb.append("|");
//	      System.out.println(sb.toString());
		
	}

	public void tellFamily(int id) {
		int parentIndex = (id - 1) / 2;
		int leftChildIndex = 2 * id + 1;
		int rightChildIndex = 2 * id + 2;

		Node parent = tree.get(parentIndex);
		Node sibling = null;

		if (id % 2 == 0) {
			sibling = tree.get(2 * parentIndex + 1);
		} else {
			sibling = tree.get(2 * parentIndex + 2);
		}

		Node leftChild = null;
		Node rightChild = null;

		boolean mySelf = tree.get(id).marked;

		if ((2 * id + 1) < nodes) {
			leftChild = tree.get(leftChildIndex);
			rightChild = tree.get(rightChildIndex);

			if (mySelf && leftChild.marked && !rightChild.marked) {
				rightChild.setMarked();
				nodesMarked++;
				tellFamily(rightChildIndex);
			} else if (mySelf && rightChild.marked && !leftChild.marked) {
				leftChild.setMarked();
				nodesMarked++;
				tellFamily(leftChildIndex);
			}
		}

		if (id != 0) { // We are not at root, need to check parent
			if (mySelf && sibling.marked && !parent.marked) {
				nodesMarked++;
				parent.setMarked();
			}
			tellFamily(parentIndex);
		}
	}

	
	
	private class Node {

		private boolean marked;

		public Node() {
			marked = false;
		}

		public void setMarked() {
			marked = true;
		}

	}

}