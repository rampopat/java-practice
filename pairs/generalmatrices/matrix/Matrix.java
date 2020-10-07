package generalmatrices.matrix;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BinaryOperator;

public class Matrix<T> {

    private final ArrayList<ArrayList<T>> matrix;
    private final double EPSILON = 0.01;

    public Matrix(List<T> elements) {
        if (elements.isEmpty()) {
            throw new IllegalArgumentException("Cannot initiate an empty matrix.");
        }
        double squareRootSize = Math.sqrt((double)elements.size());
        int n = (int) Math.round(squareRootSize);
        if (Math.abs(squareRootSize - n) > EPSILON) {
            throw new IllegalArgumentException("Cannot initiate non-square matrix.");
        }
        matrix = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            matrix.add(new ArrayList<T>(n));
            for (int j = 0; j < n; j++) {
                matrix.get(i).add(elements.get(i*n + j));
            }
        }
    }

    public T get (int row, int col) {
        return matrix.get(row).get(col);
    }

    public int getOrder() {
        return matrix.size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < getOrder(); i++) {
            sb.append("[");
            sb.append(get(i, 0));
            for (int j = 1; j < getOrder(); j++) {
                sb.append("[");
                sb.append(get(i, j));
            }
            sb.append("]");
        }
        sb.append("]");

        return sb.toString();
    }

    public static <T> Matrix<T> makeIdentityMatrix(int order, T zero, T one) {
        List<T> identity = new ArrayList<>(order * order);
        for (int i = 0; i < order; i++) {
            for (int j = 0; j < order; j++) {
                if (i != j) {
                    identity.add(zero);
                } else {
                    identity.add(one);
                }
            }
        }
        return new Matrix<>(identity);
    }

    public Matrix<T> sum(Matrix<T> other, BinaryOperator<T> elementSum) {
        List<T> sum = new ArrayList<>(getOrder() * getOrder());
        for (int i = 0; i < getOrder(); i++) {
            for (int j = 0; j < getOrder(); j++) {
                sum.add(elementSum.apply(get(i, j), other.get(i, j)));
            }
        }
        return new Matrix<>(sum);
    }

    public Matrix<T> product(Matrix<T> other,BinaryOperator<T> elementSum,BinaryOperator<T> elementProduct) {
        List<T> product = new ArrayList<>(getOrder() * getOrder());
        for (int i = 0; i < getOrder(); i++) {
            for (int j = 0; j < getOrder(); j++) {
                product.add(elementProduct.apply(get(i, 0), other.get(0, j)));
                for (int k = 1; k < getOrder(); k++) {
                    product.set(i * getOrder() + j,
                    elementSum.apply(
                        product.get(i * getOrder() + j),
                        elementProduct.apply(get(i, k), other.get(k, j))
                    ));
                }
            }
        }
        return new Matrix<>(product);
    }

}