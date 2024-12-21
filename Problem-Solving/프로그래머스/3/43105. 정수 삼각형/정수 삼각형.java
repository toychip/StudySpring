import java.util.Arrays;

class Solution {
    private int[][] dp;
    public int solution(int[][] triangle) {
        int answer = 0;
        dp = new int[triangle.length][triangle[triangle.length - 1].length];
        for (int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], -1);
        }

        return recur(triangle, 0, 0);
    }

    private int recur(int[][] triangle, int index, final int child) {
        if (index == triangle.length) {
            return 0;
        }

        if (dp[index][child] != -1) {
            return dp[index][child];
        }

        dp[index][child] = triangle[index][child] + Math.max(
                recur(triangle, index + 1, child),
                recur(triangle, index + 1, child + 1)
        );

        return dp[index][child];
    }
}