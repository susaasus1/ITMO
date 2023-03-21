package tools;

public class BasicFunctional {

    public BasicFunctional() {
    }

    public Answer answer(Logic system, double accuracy) {
        int[] rowIndices = system.getPermutedRowsIfPossible();

        if (rowIndices == null) {
            throw new RuntimeException("Не возможно получить диагональное преобладание");
        } else {
            system.doRowPermutation(rowIndices);
        }
        Matrix coefficients = system.getMatrixCoefficients();
        Matrix freeMembers = system.getMatrixFreeMembers();

        return iterate(coefficients, freeMembers, accuracy);
    }


    private Answer iterate(Matrix coefficients, Matrix freeMembers, double accuracy) {
        double[] diagonal = coefficients.getDiagonalArray();
        Matrix a = Matrix.identity(diagonal.length).minus(coefficients.div(diagonal));
        Matrix b = freeMembers.div(diagonal);
        Matrix prev, next = b.copy();
        int iters = 0;
        do {
            prev = next.copy();
            next = b.plus(a.mult(prev));
            //Высчитываем ответы, по методу простых итераций Якоби
            // X = B + A * X
            iters++;
        } while (next.minus(prev).getAbsMax() > Math.abs(accuracy));

        Matrix errors = next.minus(prev);

        return new Answer(next, errors, iters);
    }
}
