import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int[][] dir = {
            {0, 1}, {0, -1}, {1, 0}, {-1, 0}
    };

    static int staticY;
    static int staticX;
    static int[][] graph;
    static int[][] dp;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] input = reader.readLine().split(" ");
        staticY = Integer.parseInt(input[0]);
        staticX = Integer.parseInt(input[1]);
        graph = new int[staticY][staticX];
        dp = new int[staticY][staticX];

        for (int[] ints : dp) {
            Arrays.fill(ints, -1);
        }

        visited = new boolean[staticY][staticX];

        for (int i = 0; i < staticY; i++) {
            String[] inputNUmber = reader.readLine().split(" ");
            for (int j = 0; j < staticX; j++) {
                graph[i][j] = Integer.parseInt(inputNUmber[j]);
            }
        }
        visited[0][0] = true;
        int answer = recur(0, 0);
        System.out.print(answer);
    }

    private static int recur(int y, int x) {

        if (y == staticY - 1 && x == staticX - 1) {
            return 1;
        }

        if (dp[y][x] != -1) {
            return dp[y][x];
        }

        int route = 0;
        for (int i = 0; i < 4; i++) {
            int[] nextDir = dir[i];
            int dy = nextDir[0];
            int dx = nextDir[1];

            int ny = y + dy;
            int nx = x + dx;

            if (ny >= 0 && nx >= 0 && staticY > ny && staticX > nx && !visited[ny][nx]) {
                if (graph[ny][nx] < graph[y][x]) {
                    visited[ny][nx] = true;
                    route += recur(ny, nx);
                    visited[ny][nx] = false;
                }
            }
        }
        dp[y][x] = route;
        return dp[y][x];
    }
}
