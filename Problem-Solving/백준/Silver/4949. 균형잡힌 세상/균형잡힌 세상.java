import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

public class Main {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        while (true) {
            String input = reader.readLine();
            if (input.equals(".")) {
                break;
            }
            char[] inputArray = input.toCharArray();
            String currentAnswer = getAnswerLine(inputArray);
            writer.write(currentAnswer);
            writer.write("\n");
        }
        writer.flush();
        writer.close();
    }

    private static String getAnswerLine(final char[] input) {
        Stack<Character> stack = new Stack<>();
        for (char s : input) {
            if (s == ')') {
                if (stack.isEmpty()) {
                    return "no";
                }
                char value = stack.peek();
                if (value != '(') {
                    return "no";
                }
                stack.pop();
            }

            if (s == ']') {
                if (stack.isEmpty()) {
                    return "no";
                }
                char value = stack.peek();
                if (value != '[') {
                    return "no";
                }
                stack.pop();
            }

            if (s == '(' || s == '[') {
                stack.add(s);
            }
        }
        
        if (!stack.isEmpty()) {
            return "no";
        }
        return "yes";
    }
}
