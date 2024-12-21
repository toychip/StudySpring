import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static int N;
    static int[][] values;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(reader.readLine());
        values = new int[N + 1][3];
        dp = new int[N + 1][3];
        for (int[] ints : dp) {
            Arrays.fill(ints, -2);
        }


        for (int i = 1; i <= N; i++) {
            String[] inputStr = reader.readLine().split(" ");
            for (int j = 0; j < inputStr.length; j++) {
                values[i][j] = Integer.parseInt(inputStr[j]);
            }
        }

        int answer = Integer.MAX_VALUE;
        for (int color = 0; color < 3; color++) {
            answer = Math.min(answer, recur(1, color));
        }
        System.out.print(answer);

    }

    // 색깔은 0, 1, 2 중 하나
    private static int recur(final int current, final int prevColor) {
        if (current > N) {
            return 0;
        }

        if (dp[current][prevColor] != -2) {
            return dp[current][prevColor];
        }

        int min = Integer.MAX_VALUE;

        // 0번째, 빨강을 고른다면, 다음에는 빨강을 못고르므로 1,2 중에 골라야함. 그러므로 recur에는 1, 2 가 들어가고, 다음 집을 계산하여 더 작은 값을 추출
        if (prevColor == 0) {
            min = Math.min(recur(current + 1, 1) + values[current][1], recur(current + 1, 2) + values[current][2]);
        }

        if (prevColor == 1) {
            min = Math.min(recur(current + 1, 0) + values[current][0], recur(current + 1, 2) + values[current][2]);
        }

        if (prevColor == 2) {
            min = Math.min(recur(current + 1, 1) + values[current][1], recur(current + 1, 0) + values[current][0]);
        }

        dp[current][prevColor] = min;
        return min;
    }
}
