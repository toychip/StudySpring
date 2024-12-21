import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int A = scanner.nextInt();
        int B = scanner.nextInt();
        int C = scanner.nextInt();
        int D = scanner.nextInt();
        int E = scanner.nextInt();
        int F = scanner.nextInt();

        for (int x = -10000; x <= 10000; x++) {
            for (int y = -10000; y <= 10000; y++) {
                if (A * x + B * y == C) {
                    if (D * x + E * y == F) {
                        System.out.print(x + " ");
                        System.out.print(y);
                    }
                }
            }
        }
    }
}
