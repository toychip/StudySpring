import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int[] inputNumbers;
    static int N;
    static int distance;
    static int[] prefix;
    static int answer;

    public static void main(String[] args) throws IOException {
        getInput();
        initPrefix();
        calculate();
    }

    private static void initPrefix() {
        prefix = new int[inputNumbers.length + 1];
        for (int i = 0; i < inputNumbers.length; i++) {
            prefix[i + 1] = inputNumbers[i] + prefix[i];
        }
    }

    private static void calculate() {
        answer = Integer.MIN_VALUE;
        for (int i = distance; i < N + 1; i++) {
            answer = Math.max(prefix[i] - prefix[i - distance], answer);
        }
        System.out.println(answer);
    }

    private static void getInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs = br.readLine().split(" ");
        N = Integer.parseInt(inputs[0]);
        distance = Integer.parseInt(inputs[1]);

        String[] inputs2 = br.readLine().split(" ");
        inputNumbers = new int[inputs2.length];
        for (int i = 0; i < inputs2.length; i++) {
            inputNumbers[i] = Integer.parseInt(inputs2[i]);
        }
    }
}
