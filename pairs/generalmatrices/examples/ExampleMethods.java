package generalmatrices.examples;

import generalmatrices.matrix.Matrix;
import generalmatrices.pair.PairWithOperators;
import java.util.List;

public class ExampleMethods {

    public static Matrix<Matrix<Integer>> multiplyNestedMatrices(Matrix<Matrix<Integer>> first,
                                                                 Matrix<Matrix<Integer>> second) {
        return first.product(second,
            ((m1, m2) -> m1.sum(m2, ((a, b) -> a + b))),
            ((m1, m2) -> m1.product(m2, ((a, b) -> a + b), ((a, b) -> a * b))));
    }

    public static Matrix<PairWithOperators> multiplyPairMatrices(List<Matrix<PairWithOperators>> matrices) {
        return matrices.stream()
                .reduce((m1, m2) -> m1.product(m2, PairWithOperators::sum, PairWithOperators::product))
                .get();
    }

}
