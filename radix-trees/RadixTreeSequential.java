public class RadixTreeSequential extends AbstractRadixTree {

    public RadixTreeSequential() {
        this.root = new RadixTreeNodeSequential("");
    }

    @Override
    public int add(String word) {
        return add(word, root);
    }

    private int add(String word, RadixTreeNodeInterface node) {
        if (word.length() == 0) {
            node.incrementCount();
            return node.getCount() - 1;
        }

        for (RadixTreeNodeInterface child : node.getChildren()) {
            int longestPrefixLength = RadixTreeUtils.longestPrefixLength(word, child.getPrefix());
            if (longestPrefixLength > 0) {

                if (longestPrefixLength == child.getPrefix().length()) {

                    return add(word.substring(longestPrefixLength, word.length()), child);

                } else if (longestPrefixLength == word.length()) {

                    RadixTreeNodeInterface newChild = addNewPrefix(word, node);
                    addNewPrefix(child.getPrefix().substring(word.length(), child.getPrefix().length()), newChild);
                    node.getChildren().remove(child);
                    return 0;

                } else {

                    RadixTreeNodeInterface newChild = new RadixTreeNodeSequential(word.substring(0, longestPrefixLength));
                    child.setPrefix(child.getPrefix().substring(longestPrefixLength, child.getPrefix().length()));
                    newChild.getChildren().add(child);
                    node.getChildren().remove(child);
                    node.getChildren().add(newChild);
                    return add(word.substring(longestPrefixLength, word.length()), newChild);

                }

            }
        }
        // Otherwise, found no prefix
        addNewPrefix(word, node);
        return 0;
    }

    private RadixTreeNodeInterface addNewPrefix(String prefix, RadixTreeNodeInterface node) {
        RadixTreeNodeInterface child = new RadixTreeNodeSequential(prefix);
        child.incrementCount();
        node.getChildren().add(child);
        return child;
    }

    @Override
    public int remove(String word, boolean removeAll) {
        if (word.length() == 0) {
            int count = root.getCount();
            if (count > 0) {
                root.decrementCount();
            }
            if (removeAll) {
                root.resetCounter();
            }
            return count;
        }

        return remove(word, removeAll, root);
    }

    private int remove(String word, boolean removeAll, RadixTreeNodeInterface node) {

        for (RadixTreeNodeInterface child : node.getChildren()) {

            if (child.getPrefix().equals(word)) {
                int count = child.getCount();

                if (count > 0) {
                    child.decrementCount();
                }
                if (count == 1 || removeAll) {
                    if (child.getChildren().size() == 0) {
                        // If word has no children, simply delete
                        node.getChildren().remove(child);
                        if (node.getChildren().size() == 1 && node.getCount() == 0 && node != root) {
                            // If current node now has one child, merge it with current node
                            child = node.getChildren().get(0);
                            mergeNode(child, node);
                        }
                    } else if (child.getChildren().size() == 1) {
                        // If word has one child, merge with that child with current node
                        RadixTreeNodeInterface grandchild = child.getChildren().get(0);
                        mergeNode(grandchild, child);
                    }
                    return count;
                }
            }

            if (word.length() > child.getPrefix().length()
                    && word.substring(0, child.getPrefix().length()).equals(child.getPrefix())) {
                return remove(word.substring(child.getPrefix().length(), word.length()), removeAll, child);
            }

        }

        // Doesn't exist
        return 0;
    }

    private void mergeNode(RadixTreeNodeInterface n1, RadixTreeNodeInterface n2) {
        n2.setPrefix(n2.getPrefix() + n1.getPrefix());
        n2.setCount(n1.getCount());
        n2.getChildren().clear();
        n2.getChildren().addAll(n1.getChildren());
    }

    @Override
    public void clear() {
        root.getChildren().clear();
        root.resetCounter();
    }

    @Override
    public boolean isEmpty() {
        return root.getChildren().isEmpty() && !root.hasValue();
    }

}
