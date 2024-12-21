
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(reader.readLine());
        int[] number = new int[N];
        String[] inputNumber = reader.readLine().split(" ");
        int goal = Integer.parseInt(reader.readLine());

        for (int i = 0; i < N; i++) {
            number[i] = Integer.parseInt(inputNumber[i]);
        }

        Arrays.sort(number);
        int answer = 0;

        int start = 0;
        int end = N - 1;
        while (start < end) {
            if (number[start] + number[end] == goal) {
                answer++;
            }

            if (number[start] + number[end] >= goal) {
                end--;
            } else {
                start++;
            }

        }
        System.out.println(answer);
    }
}
