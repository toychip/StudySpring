import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(reader.readLine());
        String[] input = reader.readLine().split(" ");

        int[] dp = new int[N];
        int[] values = new int[N];

        for (int i = 0; i < N; i++) {
            values[i] = Integer.parseInt(input[i]);
        }

        Arrays.fill(dp, 1);

        for (int right = 0; right < N; right++) {
            for (int left = 0; left < right; left++) {
                if (values[right] > values[left]) {
                    dp[right] = Math.max(dp[right], dp[left] + 1);
                }
            }
        }

        int max = Arrays.stream(dp).max().getAsInt();
        System.out.print(max);

    }
}
