public class Huffman {

	private HuffmanData[] leafEntries;
	private final static int SIZE = 50;
	private PriorityQueueInterface<BinaryTreeInterface<HuffmanData>> pq;
	private BinaryTreeInterface<HuffmanData> huffmanTree;

	public Huffman() {
		leafEntries = new HuffmanData[SIZE];
		pq = new PriorityQueue<BinaryTreeInterface<HuffmanData>>();
		huffmanTree = new BinaryTree<HuffmanData>();
	}

	public void setFrequencies() {
	// Create test data and store as an array of HuffData
		leafEntries[0] = new HuffmanData(5000, 'a');
		leafEntries[1] = new HuffmanData(2000, 'b');
		leafEntries[2] = new HuffmanData(10000, 'c');
		leafEntries[3] = new HuffmanData(8000, 'd');
		leafEntries[4] = new HuffmanData(22000, 'e');
		leafEntries[5] = new HuffmanData(49000, 'f');
		leafEntries[6] = new HuffmanData(4000, 'g');
	}

	public void setPriorityQueue() {
	// Copy test data from array LeafEntries of HuffData
	// into a PriorityQueue of HuffmanTree
		for (int i = 0; i < 7; i++) {
			if (leafEntries[i].getFrequency() > 0)
				pq.add(new BinaryTree<HuffmanData>(leafEntries[i]));
		}
	}

	public void createHuffmanTree() {
		while(pq.getSize() > 1) {
			BinaryTreeInterface t1 = pq.removeMin();
			BinaryTreeInterface t2 = pq.removeMin();
			int f = ((HuffmanData) t1.getRootData()).getFrequency() + ((HuffmanData) t2.getRootData()).getFrequency();
			BinaryTree<HuffmanData> t = new BinaryTree<HuffmanData>(new HuffmanData(f), (BinaryTree<HuffmanData>) t1, (BinaryTree<HuffmanData>) t2);
			pq.add(t);
		}
		huffmanTree = pq.getMin();
	}

	public void printCode() {
		printCodeProcedure("", huffmanTree);
	}

	private void printCodeProcedure(String code, BinaryTreeInterface<HuffmanData> tree) {
		if (tree.getRootData().getSymbol() == '\u0000') {
			printCodeProcedure(code + "0", tree.getLeftSubtree());
			printCodeProcedure(code + "1", tree.getRightSubtree());
		} else {
			System.out.print(tree.getRootData().getSymbol() + ": ");
			System.out.println(code);
		}
	}

//	public void printHuffmanTree(String code, BinaryTreeInterface<HuffmanData> tree) {
//		System.out.print(tree.getRootData().getSymbol() + ": ");
//		System.out.println(code);
//		if (tree.getLeftSubtree() != null) {
//			printHuffmanTree(code + "0", tree.getLeftSubtree());
//		}
//		if (tree.getRightSubtree() != null) {
//			printHuffmanTree(code + "1", tree.getRightSubtree());
//		}
//	}

}
