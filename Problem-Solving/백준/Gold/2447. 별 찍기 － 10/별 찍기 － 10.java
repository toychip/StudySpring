import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    static int N;
    static String[][] board;
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(reader.readLine());
        board = new String[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                board[i][j] = " ";
            }
        }

        recur(N, 0, 0);

        StringBuilder builder = new StringBuilder();
        for (String[] strings : board) {
            for (String string : strings) {
                builder.append(string);
            }
            builder.append("\n");
        }
        System.out.println(builder);
    }

    private static void recur(int n, int y, int x) {
        if (n == 1) {
            board[y][x] = "*";
            return;
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (!(i == 1 && j == 1)) {
                    recur(n / 3, (n / 3) * i + y, (n / 3) * j + x);
                }
            }
        }
    }
}
