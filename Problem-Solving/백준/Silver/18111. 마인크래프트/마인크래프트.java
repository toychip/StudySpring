import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] input1 = reader.readLine().split(" ");
        int y = Integer.parseInt(input1[0]);
        int x = Integer.parseInt(input1[1]);
        int inventory = Integer.parseInt(input1[2]);

        int[][] board = new int[y][x];
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < y; i++) {
            String[] input2 = reader.readLine().split(" ");
            for (int j = 0; j < x; j++) {
                board[i][j] = Integer.parseInt(input2[j]);
                min = Math.min(min, board[i][j]);
                max = Math.max(max, board[i][j]);
            }
        }

        int prevTime = Integer.MAX_VALUE;
        int resultHeight = 0;

        for (int targetHeight = min; targetHeight <= max; targetHeight++) {
            int time = 0;
            int blocks = inventory;

            for (int i = 0; i < y; i++) {
                for (int j = 0; j < x; j++) {
                    int diff = board[i][j] - targetHeight;

                    if (diff > 0) {
                        time += diff * 2;
                        blocks += diff;
                    } else if (diff < 0) {
                        time -= diff;
                        blocks += diff;
                    }
                }
            }

            if (blocks >= 0) {
                if (time < prevTime || (time == prevTime && targetHeight > resultHeight)) {
                    prevTime = time;
                    resultHeight = targetHeight;
                }
            }
        }

        System.out.println(prevTime + " " + resultHeight);
    }
}
