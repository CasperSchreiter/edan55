package lab1;

import java.util.ArrayList;

public class TreeR3 {
	public ArrayList<Node> tree;
	private int nodes;
	private int nodesMarked;
	private InputList2 list;


	public TreeR3(int N) {
		this.list = list;
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

	// int r parameter is only for debugging
	public void markNode(int id, int r) {
		Node node = tree.get(id);
		if (node.marked) {
			 System.out.println(r + " i=" + id + " with MarkedNodes = " +
			 nodesMarked);
			return;
		}
		nodesMarked++;
		node.setMarked();
		tellFamily(id);

//		 StringBuilder sb = new StringBuilder();
//		 sb.append(r + " i=" + id + ": ");
//		 for (int i = 0; i < tree.size(); i++) {
//		 if (tree.get(i).marked) {
//		 sb.append("| X ");
//		 } else {
//		 sb.append("| 0 ");
//		 }
//		
//		 }
//		 sb.append("|");
//		 System.out.println(sb.toString());

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

	public class Node {

		public boolean marked;

		public Node() {
			marked = false;
		}

		public void setMarked() {
			marked = true;
		}

	}

}