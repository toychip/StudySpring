import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] input1 = reader.readLine().split(" ");
        String[] input2 = reader.readLine().split(" ");

        int N = Integer.parseInt(input1[0]);
        int count = Integer.parseInt(input1[1]);
        int[] values = new int[count];

        for (int i = 0; i < count; i++) {
            values[i] = Integer.parseInt(input2[i]);
        }

        Deque<Integer> deque = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            deque.add(i);
        }

        int answer = 0;
        for (int i = 0; i < count; i++) {
            int currentValue = values[i];

            int index = 0;
            int targetIdx = 0;
            for (int num : deque) {
                if (num == currentValue) {
                    targetIdx = index;
                    break;
                }
                index++;
            }

            if (targetIdx <= deque.size() / 2) {
                for (int j = 0; j < targetIdx; j++) {
                    deque.addLast(deque.pollFirst());
                    answer++;
                }
            } else {
                for (int j = 0; j < deque.size() - targetIdx; j++) {
                    deque.addFirst(deque.pollLast());
                    answer++;
                }
            }

            deque.pollFirst();
        }
        System.out.print(answer);
    }
}
