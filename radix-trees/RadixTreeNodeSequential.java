import java.util.LinkedList;
import java.util.List;

public class RadixTreeNodeSequential implements RadixTreeNodeInterface {

    private String prefix;
    private Integer count;
    private List<RadixTreeNodeInterface> children;

    public RadixTreeNodeSequential(String prefix) {
        this.prefix = prefix;
        count = 0;
        children = new LinkedList<>();
    }

    @Override
    public String getPrefix() {
        return prefix;
    }

    @Override
    public boolean hasValue() {
        return !prefix.equals("");
    }

    @Override
    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public Integer getCount() {
        return count;
    }

    @Override
    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public void incrementCount() {
        count += 1;
    }

    @Override
    public void decrementCount() {
        count -= 1;
    }

    @Override
    public void resetCounter() {
        count = 0;
    }

    @Override
    public List<RadixTreeNodeInterface> getChildren() {
        return children;
    }
}
