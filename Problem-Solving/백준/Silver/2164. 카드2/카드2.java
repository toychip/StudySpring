import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());

        Queue<Integer> queue = new LinkedList<>();

        for (int i = 1; i <= n; i++) {
            queue.offer(i);
        }

        while (!queue.isEmpty()) {

            if (queue.size() == 1) {
                System.out.print(queue.poll());
                return;
            }

            queue.poll();
            Integer currentValue = queue.poll();
            queue.offer(currentValue);
        }
    }
}
