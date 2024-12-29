import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int[][] board;
    static int N;
    static int zeroCnt = 0;
    static int minusOneCnt = 0;
    static int plusOneCnt = 0;

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
        System.out.println(minusOneCnt);
        System.out.println(zeroCnt);
        System.out.println(plusOneCnt);
    }

    private static void recur(int size, int y, int x) {
        // 모든 숫자가 같다면
        if (check(size, y, x)) {
            if (board[y][x] == -1) {
                minusOneCnt++;
            } else if (board[y][x] == 0) {
                zeroCnt++;
            } else {
                plusOneCnt++;
            }
            return;
        }

        int newSize = size / 3;

        recur(newSize, y, x); // 왼쪽 위
        recur(newSize, y, x + newSize); // 중간 위
        recur(newSize, y, x + newSize * 2); // 오른쪽 위

        recur(newSize, y + newSize, x); // 왼쪽 중간
        recur(newSize, y + newSize, x + newSize); // 중간 중간
        recur(newSize, y + newSize, x + newSize * 2); // 오른쪽 중간

        recur(newSize, y + newSize * 2, x); // 왼쪽 아래
        recur(newSize, y + newSize * 2, x + newSize); // 중간 아래
        recur(newSize, y + newSize * 2, x + newSize * 2); // 오른쪽 이레

    }

    private static boolean check(int size, int y, int x) {
        int start = board[y][x];

        for (int i = y; i < y + size; i++) {
            for (int j = x; j < x + size; j++) {
                if (board[i][j] != start) {
                    return false;
                }
            }
        }
        return true;
    }
}
