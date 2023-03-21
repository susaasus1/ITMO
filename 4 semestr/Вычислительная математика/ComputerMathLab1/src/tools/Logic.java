package tools;

public class Logic {
    private Matrix matrix;
    private int size;

    public Logic(Matrix matrix, int size) {
        this.matrix = matrix;
        this.size = size;
        if (1 > size || size > 20) {
            throw new IllegalArgumentException("Размер должен быть равен от 1 до 20");
        }
        if (matrix.getRow() != size || matrix.getColumn() != size + 1) {
            throw new IllegalArgumentException("Размер расширенной матрицы не соответствует");
        }
    }

    public int[] getPermutedRowsIfPossible() {
        int[] indices = new int[size];
        boolean[] check = new boolean[size];
        boolean checker = false;
        for (int i = 0; i < size; ++i) {
            double sum = 0;
            int maxIndex = 0;
            for (int j = 0; j < size; ++j) {
                if (Math.abs(matrix.get(i, maxIndex)) < Math.abs(matrix.get(i, j))) {
                    maxIndex = j;
                }
                sum += Math.abs(matrix.get(i, j));
            }
            if (2 * Math.abs(matrix.get(i, maxIndex)) >= sum && !check[maxIndex]) {
                if (2 * Math.abs(matrix.get(i, maxIndex)) > sum && !check[maxIndex]) {
                    checker = true;
                }
                check[maxIndex] = true;
                indices[maxIndex] = i;
            } else {
                return null;
            }
        }
        if (checker == true) {
            return indices;
        } else {
            return null;
        }
    }

    public void doRowPermutation(int[] rowIndices) {
        if (rowIndices.length != size) {
            throw new IllegalArgumentException("Размер строки не совпадает");
        }
        this.matrix = matrix.subMatrix(rowIndices, 0, size);
    }

    public Matrix getMatrixCoefficients() {
        return this.matrix.subMatrix(0, 0, size - 1, size - 1);
    }

    public Matrix getMatrixFreeMembers() {
        return this.matrix.subMatrix(0, size, size - 1, size);
    }
}
