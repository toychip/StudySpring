import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        Stack<Integer> result = new Stack<>();

        for (int i = 0; i < n; i++) {
            int currentValue = Integer.parseInt(reader.readLine());

            if (currentValue != 0) {
                result.add(currentValue);
            } else {
                result.pop();
            }
        }

        int answer = 0;
        for (Integer i : result) {
            answer += i;
        }
        System.out.print(answer);
    }
}
