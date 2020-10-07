public class RadixTreeUtils {

    public static Integer longestPrefixLength(String s1, String s2) {
        int i = 0;
        for (; i < s1.length() && i < s2.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                return i;
            }
        }
        return i;
    }

    public static void printRadixTree(AbstractRadixTree tree) {
        printRadixTree(0, tree.root);
    }

    public static void printRadixTree(int depth, RadixTreeNodeInterface node) {
        for (int i = 0; i < depth; i++) {
            System.out.print("  ");
        }
        System.out.println(node.getPrefix() + ": " + node.getCount());
        for (RadixTreeNodeInterface child : node.getChildren()) {
            printRadixTree(depth + 1, child);
        }
    }

}
