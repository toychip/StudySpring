import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String word1 = reader.readLine();
        String word2 = reader.readLine();

        int w1Length = word1.length();
        int w2Length = word2.length();

        int[][] dp = new int[w1Length + 1][w2Length + 1];

        for (int i = 1; i < w1Length + 1; i++) {
            for (int j = 1; j < w2Length + 1; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        System.out.print(dp[w1Length][w2Length]);
    }
}
