import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();

        int answer = 0;

        // A
        for (int i = 0; i <= N; i++) {
            // B
            for (int j = 0; j <= N; j++) {
                // C
                for (int k = 0; k <= N; k++) {
                    if (i + j + k == N) {
                        if (i >= j + 2) {
                            if (i > 0 && j > 0 && k > 0) {
                                if (k % 2 == 0) {
                                    answer++;
                                }
                            }
                        }
                    }
                }

            }
        }

        System.out.println(answer);
    }
}
