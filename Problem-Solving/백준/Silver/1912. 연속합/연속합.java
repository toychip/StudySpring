import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int[] numbers;
    static int N;
    static int[] prefix;

    public static void main(String[] args) throws IOException {
        getInput();
        initPrefix();
//        solution_timeout();
        solution();
    }

    private static void initPrefix() {
        prefix = new int[numbers.length + 1];
        prefix[0] = (int) (Integer.MIN_VALUE * 0.5);
        for (int i = 0; i < N; i++) {
            // 바로 다음 수를 더했을 때 더 작다면 굳이 들고갈 필요가 없음
            prefix[i + 1] = Math.max(numbers[i] + prefix[i], numbers[i]);
        }
    }

    private static void solution() {
        int answer = Integer.MIN_VALUE;
        for (int i = 1; i < prefix.length; i++) {
            answer = Math.max(answer, prefix[i]);
        }
        System.out.println(answer);
    }
    
    // 기존 방식의 거리를 for문을 돌리려 했으나 timeout
    private static void solution_timeout() {
        int answer = (int) (Integer.MIN_VALUE * 0.9);
        for (int i = 1; i < N; i++) {
            for (int j = i; j < N + 1; j++) {
                int prefixJ = prefix[j];
                int prefixJMinusI = prefix[j - i];
                int currentMinus = prefixJ - prefixJMinusI;
                answer = Math.max(currentMinus, answer);
            }
        }
        System.out.println(answer);
    }

    private static void getInput() throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        String[] inputNumbers = bf.readLine().split(" ");
        numbers = new int[inputNumbers.length];

        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(inputNumbers[i]);
        }
    }
}
