class Solution {
    public void setZeroes(int[][] matrix) {
        int y = matrix.length;
        int x = matrix[0].length;
        boolean[] yZero = new boolean[y];
        boolean[] xZero = new boolean[x];

        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                if (matrix[i][j] == 0) {
                    yZero[i] = true;
                    xZero[j] = true;
                }
            }
        }

        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                if (yZero[i] || xZero[j]) {
                    matrix[i][j] = 0;
                }
            }
        }
    }
}