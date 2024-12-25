import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        int answer = 0;
        for (int i = 0; i < n; i++) {
            String input = reader.readLine();
            String[] inputArray = input.split("");
            boolean isGood = isGoodWord(inputArray);
            if (isGood) {
                answer++;
            }
        }
        System.out.print(answer);
    }

    private static boolean isGoodWord(final String[] inputArray) {
        Stack<String> stack = new Stack<>();
        stack.push(inputArray[0]);
        for (int i = 1; i < inputArray.length; i++) {
            String s = inputArray[i];
            if (s.equals("A")) {
                if (stack.isEmpty()) {
                    stack.push(s);
                    continue;
                }
                String current = stack.peek();
                if (current.equals("A")) {
                    stack.pop();
                } else {
                    stack.push(s);
                }
            }

            if (s.equals("B")) {
                if (stack.isEmpty()) {
                    stack.push(s);
                    continue;
                }
                String current = stack.peek();
                if (current.equals("B")) {
                    stack.pop();
                } else {
                    stack.push(s);
                }
            }
        }

        if (!stack.isEmpty()) {
            return false;
        }

        return true;
    }
}
