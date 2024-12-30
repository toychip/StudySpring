import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int n;
    static String[][] board;
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(reader.readLine());
        int weight = n * 2 - 1;
        board = new String[n][weight];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < weight; j++) {
                board[i][j] = " ";
            }
        }

        recur(n, 0, n - 1);
        StringBuilder builder = new StringBuilder();
        for (String[] strings : board) {
            for (String string : strings) {
                builder.append(string);
            }
            builder.append("\n");
        }
        String answer = builder.toString();
        System.out.print(answer);
    }

    private static void recur(final int n, final int y, final int x) {
        if (n == 3) {
            insertStar(y, x);
            return;
        }

        // 위
        recur(n / 2, y, x);

        // 왼쪽
        recur(n / 2, y + n / 2, x  - n / 2);

        // 오른쪽
        recur(n / 2, y + n / 2, x  + n / 2);
    }

    private static void insertStar(final int y, final int x) {
        board[y][x] ="*";

        board[y + 1][x - 1] ="*";
        board[y + 1][x + 1] ="*";

        board[y + 2][x - 1] ="*";
        board[y + 2][x - 2] ="*";
        board[y + 2][x] ="*";
        board[y + 2][x + 1] ="*";
        board[y + 2][x + 2] ="*";
    }
}
