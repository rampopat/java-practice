public abstract class AbstractRadixTree {

    RadixTreeNodeInterface root;

    public abstract int add(String word);
    public abstract int remove(String word, boolean removeAll);
    public abstract void clear();
    public abstract boolean isEmpty();

}
