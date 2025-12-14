package labuladong.prefixsum;

/**
 * 前缀和 矩阵
 * 以[0, 0]为原点
 */
public class NumMatrix_304 {
    // construct
    int[][] preSum;

    public NumMatrix_304(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return;
        }
        int rowNum = matrix.length;
        int colNum = matrix[0].length;
        preSum = new int[rowNum + 1][colNum + 1];
        for (int i = 1; i < rowNum + 1; i++) {
            for (int j = 1; j < colNum + 1; j++) {
                // 还需要加上该点的值 matrix[i - 1][j - 1]
                preSum[i][j] = matrix[i - 1][j - 1] + preSum[i][j - 1] + preSum[i - 1][j] - preSum[i - 1][j - 1];
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        return preSum[row2 + 1][col2 + 1] - preSum[row2 + 1][col1] - preSum[row1][col2 + 1] + preSum[row1][col1];
    }
}
