import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(reader.readLine());

        for (int i = 0; i < N; i++) {
            Queue<Node> queue = new LinkedList<>();
            String[] input = reader.readLine().split(" ");
//            int number = Integer.parseInt(input[0]);
            int goalIndex = Integer.parseInt(input[1]) + 1;

            String[] numberStr = reader.readLine().split(" ");
            int[] numbers = new int[numberStr.length];
            for (int j = 0; j < numberStr.length; j++) {
                int number = Integer.parseInt(numberStr[j]);
                numbers[j] = number;
                queue.offer(new Node(j + 1, number));
            }

            if (queue.size() == 1) {
                writer.write(String.valueOf(1));
                writer.write("\n");
                continue;
            }

            int count = 0;
            while (!queue.isEmpty()) {
                Node current = queue.poll();

                boolean hasLarger = queue.stream().anyMatch(node -> node.getValue() > current.getValue());
                if (hasLarger) {
                    queue.offer(current);
                } else {
                    count++;
                    if (current.getIndex() == goalIndex) {
                        writer.write(String.valueOf(count));
                        writer.write("\n");
                    }
                }
            }
        }
        writer.flush();
        writer.close();
    }
}

class Node {
    private final int index;
    private final int value;

    public Node(final int index, final int value) {
        this.index = index;
        this.value = value;
    }

    public int getIndex() {
        return index;
    }

    public int getValue() {
        return value;
    }
}
