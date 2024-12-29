import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int N;
    static int[][] board;
    static int zeroCnt;
    static int oneCnt;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(reader.readLine());
        board = new int[N][N];
        for (int i = 0; i < N; i++) {
            String[] input2 = reader.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(input2[j]);
            }
        }

        recur(N, 0, 0);
        System.out.println(zeroCnt);
        System.out.println(oneCnt);
    }

    private static void recur(final int n, final int y, final int x) {
        if (check(n, y, x)) {
            if (board[y][x] == 0) {
                zeroCnt++;
            } else {
                oneCnt++;
            }
            return;
        }

        int newSize = n / 2;

        recur(newSize, y, x);
        recur(newSize, y, x + newSize);
        recur(newSize, y + newSize, x);
        recur(newSize, y + newSize, x + newSize);
    }

    private static boolean check(final int n, final int y, final int x) {
        int start = board[y][x];

        for (int i = y; i < n + y; i++) {
            for (int j = x; j < n + x; j++) {
                if (board[i][j] != start) {
                    return false;
                }
            }
        }
        return true;
    }
}
