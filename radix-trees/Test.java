public class Test {

    public static void main(String[] args) {
        AbstractRadixTree rt = new RadixTreeSequential();

        rt.add("banner");
        rt.add("backbreaking");
        rt.add("ba");
        rt.add("ban");
        rt.add("bann");
        rt.add("hitchhiker");
        rt.add("banns");
        rt.add("banns");
        rt.add("banns");
        rt.add("banns");
        rt.add("banns");
        rt.add("banana");
        rt.add("background");
        rt.add("hello");
        rt.add("hi");

        RadixTreeUtils.printRadixTree(rt);

        rt.remove("ba", true);
        rt.remove("ban", false);
        rt.remove("banana", true);
        rt.remove("banns", false);
        rt.remove("back", false);
        rt.remove("highlander", false);

        RadixTreeUtils.printRadixTree(rt);
    }

}
