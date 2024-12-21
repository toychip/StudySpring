import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        long[] inputs = new long[N];

        for (int i = 0; i < N; i++) {
            long input = scanner.nextLong();
            inputs[i] = input;
        }

        for (int i = 0; i < N; i++) {

            if (inputs[i] < 1_000_000) {
                System.out.println("NO");
                break;
            }

            for (long j = 2; j <= 1_000_000; j++) {
                if (inputs[i] % j == 0) {
                    System.out.println("NO");
                    break;
                }

                if (j == 1_000_000) {
                    System.out.println("YES");
                    break;
                }
            }
        }
    }
}
