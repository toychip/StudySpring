import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int[][] dir = {
        {0, 1}, {0, -1}, {1, 0}, {-1, 0}
    };

    static int N;
    static int[][] graph;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(reader.readLine());
        graph = new int[N][N];
        dp = new int[N][N];

        for (int i = 0; i < N; i++) {
            String[] input = reader.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                graph[i][j] = Integer.parseInt(input[j]);
            }
        }
        int answer = 0;
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                recur(y, x);
            }
        }

        for (int[] ints : dp) {
            for (int anInt : ints) {
                answer = Math.max(anInt, answer);
            }
        }
        System.out.print(answer + 1);
    }

    private static int recur(int y, int x) {
        if (dp[y][x] != 0) {
            return dp[y][x];
        }

        for (int i = 0; i < 4; i++) {
            int dy = dir[i][0];
            int dx = dir[i][1];

            int newY = y + dy;
            int newX = x + dx;

            if (newX >= 0 && newY >= 0 && newX < N && newY < N) {
                if (graph[newY][newX] > graph[y][x]) {
                    dp[y][x] = Math.max(dp[y][x], recur(newY, newX) + 1);
                }
            }
        }
//        return Math.max(Math.max(recur(y - 1, x), recur(y + 1, x)), Math.max(recur(y, x - 1), recur(y, x + 1)));
        return dp[y][x];
    }
}
