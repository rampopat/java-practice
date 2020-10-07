import java.util.List;

public interface RadixTreeNodeInterface {

    String getPrefix();

    boolean hasValue();

    void setPrefix(String prefix);

    Integer getCount();

    void setCount(int count);

    void incrementCount();

    void decrementCount();

    void resetCounter();

    List<RadixTreeNodeInterface> getChildren();

}
