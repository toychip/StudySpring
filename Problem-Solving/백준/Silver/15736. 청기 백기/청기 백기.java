import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int input = scanner.nextInt();
        double resultDouble = Math.pow(input, 0.5);
        int result = (int) resultDouble;
        System.out.println(result);
    }
}
