import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Comparator;
import java.util.Deque;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input1 = reader.readLine().split(" ");
        int N = Integer.parseInt(input1[0]);
        int windowLength = Integer.parseInt(input1[1]);

        String[] input = reader.readLine().split(" ");
        int[] values = new int[input.length];
        for (int i = 0; i < values.length; i++) {
            values[i] = Integer.parseInt(input[i]);
        }

        Deque<Integer> deque = new LinkedList<>();
        for (int i = 0; i < values.length; i++) {
            // 1. 윈도우에서 벗어난 값을 제거
            if (!deque.isEmpty() && i >= windowLength && deque.peekFirst() == values[i - windowLength]) {
                deque.pollFirst();
            }

            // 2. 현재 값보다 큰 값은 모두 제거 (정렬 유지)
            while (!deque.isEmpty() && deque.peekLast() > values[i]) {
                deque.pollLast();
            }

            // 3. 현재 값을 덱에 추가
            deque.offerLast(values[i]);

            // 4. 덱의 첫 번째 값은 현재 윈도우의 최소값
            writer.write(deque.peekFirst() + " ");
        }
        writer.flush();
        writer.close();
    }
}
