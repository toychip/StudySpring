import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(reader.readLine());
        for (int i = 0; i < n; i++) {
            String input = reader.readLine();
            String answer = bracket(input);
            writer.write(answer);
            writer.write("\n");
        }
        writer.flush();
        writer.close();
    }

    private static String bracket(final String input) {
        String[] inputArray = input.split("");
        Stack<String> stack = new Stack<>();
        for (String s : inputArray) {
            if (s.equals("(")) {
                stack.push(s);
            }
            if (s.equals(")")) {
                if (stack.isEmpty()) {
                    return "NO";
                }
                stack.pop();
            }
        }
        if (!stack.isEmpty()) {
            return "NO";
        }
        return "YES";
    }
}