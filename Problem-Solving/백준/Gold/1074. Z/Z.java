import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int count;
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] input = reader.readLine().split(" ");

        int N = Integer.parseInt(input[0]);
        int row = (int) Math.pow(2, N);

        int x = Integer.parseInt(input[1]);
        int y = Integer.parseInt(input[2]);
        count = 0;
        recur(row, x, y, 0, 0);
        System.out.print(count);
    }

    private static void recur(int size, int x, int y, int startX, int startY) {
        if (size == 1) {
            return;
        }

        int half = size / 2;
        int area = half * half;

        if (x < startX + half && y < startY + half) {
            recur(half, x, y, startX, startY);
        }

        else if (x < startX + half && y >= startY + half) {
            count += area;
            recur(half, x, y, startX, startY + half);
        }

        else if (x >= startX + half && y < startY + half) {
            count += area * 2;
            recur(half, x, y, startX + half, startY);
        }

        else if (x >= startX + half && y >= startY + half) {
            count += area * 3;
            recur(half, x, y, startX + half, startY + half);
        }
    }
}
