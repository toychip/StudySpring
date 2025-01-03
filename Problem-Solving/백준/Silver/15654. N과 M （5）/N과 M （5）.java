import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static int M;
    static int N;
    static int[] arr;
    static int[] numbers;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] input = reader.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);

        String[] input2 = reader.readLine().split(" ");
        numbers = new int[input2.length];
        for (int i = 0; i < input2.length; i++) {
            numbers[i] = Integer.parseInt(input2[i]);
        }

        Arrays.sort(numbers);

        arr = new int[M];
        visited = new boolean[N];

        recur(0);
    }

    private static void recur(final int depth) {
        if (depth == M) {
            for (int i : arr) {
                System.out.print(i + " ");
            }

            System.out.println();
            return;
        }

        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                arr[depth] = numbers[i];
                recur(depth + 1);
                visited[i] = false;
            }
        }
    }
}
