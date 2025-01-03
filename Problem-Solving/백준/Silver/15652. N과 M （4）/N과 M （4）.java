import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int M;
    static int N;
    static int[] array;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] input = reader.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        array = new int[M];
        recur(1, 0);
    }

    private static void recur(final int start, final int depth) {
        if (depth == M) {
            for (int i : array) {
                System.out.print(i + " ");
            }

            System.out.println();
            return;
        }

        for (int i = start; i < N + 1; i++) {
            array[depth] = i;
            recur(i, depth + 1);
        }

    }
}
