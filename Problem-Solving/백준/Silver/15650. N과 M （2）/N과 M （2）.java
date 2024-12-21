import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N;
    static int M;

    public static void main(String[] args) throws IOException {
        String[] input = reader.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        List<Integer> sequence = new ArrayList<>();

        backtracking(1, sequence);
        writer.flush();
        writer.close();
    }

    private static void backtracking(int start, List<Integer> sequence) throws IOException {
        if (sequence.size() == M) {
            for (Integer i : sequence) {
                writer.write(i + " ");
            }
            writer.write("\n");
            return;
        }

        for (int i = start; i <= N; i++) {
            sequence.add(i);
            backtracking(i + 1, sequence);
            sequence.remove(sequence.size() - 1);
        }

    }
}
