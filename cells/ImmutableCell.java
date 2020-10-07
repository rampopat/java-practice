public class ImmutableCell<T> implements Cell<T> {

  private final T value;

  public ImmutableCell(T value) {
    if (value == null) {
      throw new IllegalArgumentException("Cannot set cell value to be null.");
    }
    this.value = value;
  }

  @Override
  public void set(T value) {
    throw new UnsupportedOperationException("Cannot set an immutable cell.");
  }

  @Override
  public T get() {
    return value;
  }

  @Override
  public boolean isSet() {
    return true;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null) {
      return false;
    }
    if (!(o instanceof ImmutableCell)) {
      return false;
    } else {
      return ((ImmutableCell) o).get().equals(get());
    }
  }

  @Override
  public int hashCode() {
    return get().hashCode();
  }
}
