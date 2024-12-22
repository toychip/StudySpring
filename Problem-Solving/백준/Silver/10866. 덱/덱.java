import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Deque;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(reader.readLine());
        Deque<Integer> deque = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            String input = reader.readLine();

            if (input.startsWith("push")) {
                String[] inputSplit = input.split(" ");
                int value = Integer.parseInt(inputSplit[1]);
                if (inputSplit[0].equals("push_front")) {
                    deque.addFirst(value);
                } else {
                    deque.addLast(value);
                }
            }

            if (input.startsWith("pop")) {
                if (deque.isEmpty()) {
                    writer.write("-1");
                    writer.write("\n");
                    continue;
                }
                if (input.equals("pop_front")) {
                    Integer value = deque.pollFirst();
                    writer.write(String.valueOf(value));
                    writer.write("\n");
                } else {
                    Integer value = deque.pollLast();
                    writer.write(String.valueOf(value));
                    writer.write("\n");
                }
            }

            if (input.equals("size")) {
                writer.write(String.valueOf(deque.size()));
                writer.write("\n");
            }

            if (input.equals("empty")) {
                if (deque.isEmpty()) {
                    writer.write("1");
                    writer.write("\n");
                } else {
                    writer.write("0");
                    writer.write("\n");
                }
            }

            if (input.equals("front")) {
                if (deque.isEmpty()) {
                    writer.write("-1");
                    writer.write("\n");
                } else {
                    writer.write(String.valueOf(deque.peekFirst()));
                    writer.write("\n");
                }
            }

            if (input.equals("back")) {
                if (deque.isEmpty()) {
                    writer.write(String.valueOf(-1));
                    writer.write("\n");
                } else {
                    writer.write(String.valueOf(deque.peekLast()));
                    writer.write("\n");
                }
            }
        }
        writer.flush();
        writer.close();
    }
}
