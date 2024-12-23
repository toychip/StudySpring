import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(reader.readLine());

        for (int i = 0; i < N; i++) {
            String[] functions = reader.readLine().split("");
            int innerN = Integer.parseInt(reader.readLine());
            Deque<Integer> deque = new LinkedList<>();
            String innerInput = reader.readLine();
            if (innerN > 0) {
                String[] values = innerInput.replaceAll("[\\[\\]]", "").split(",");
                for (String value : values) {
                    deque.add(Integer.parseInt(value.trim()));
                }
            }

            boolean isError = false;
            boolean isReversed = false;

            if (functions.length == 1) {
                boolean error = Arrays.stream(functions).findAny().equals("D");
                if (error) {
                    writer.write("error");
                    writer.write("\n");
                    continue;
                }
            }

            for (int j = 0; j < functions.length; j++) {
                String function = functions[j];
                if (function.equals("R")) {
                    isReversed = !isReversed;
                }

                if (function.equals("D")) {
                    if (deque.isEmpty()) {
                        writer.write("error");
                        writer.write("\n");
                        isError = true;
                        break;
                    }

                    if (isReversed) {
                        deque.pollLast();
                    } else {
                        deque.pollFirst();
                    }
                }
            }

            if (!isError) {
                writer.write("[");
                int size = deque.size();
                for (int j = 0; j < size; j++) {
                    Integer value;
                    if (isReversed) {
                        value = deque.pollLast();
                    } else {
                        value = deque.pollFirst();
                    }
                    writer.write(String.valueOf(value));
                    if (j != size - 1) {
                        writer.write(",");
                    }
                }
                writer.write("]\n");
            }
        }
        writer.flush();
        writer.close();
    }
}
