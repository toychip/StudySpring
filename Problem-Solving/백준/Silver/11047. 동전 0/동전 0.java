import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] input = reader.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int goal = Integer.parseInt(input[1]);
        int[] coins = new int[n];

        for (int i = 0; i < n; i++) {
            coins[i] = Integer.parseInt(reader.readLine());
        }

        int sum = 0;
        int count = 0;

        for (int i = coins.length - 1; i >= 0; i--) {
            int currentCoin = coins[i];
            if (goal >= currentCoin) {
                int mock = (goal - sum) / currentCoin;
                sum = sum + mock * currentCoin;
                count += mock;
            }

            if (sum == goal) {
                System.out.print(count);
                break;
            }
        }
    }
}
