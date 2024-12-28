import java.util.Scanner;

public class Main {

    static int cnt;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        cnt = 0;

        hanoi(N, 1, 3, 2);
        System.out.println(cnt);
        System.out.println(sb.toString());
    }

    private static void hanoi(int disk, int start, int dest, int temp) {
        if (disk == 1) {
            sb.append(start).append(" ").append(dest).append("\n");
            cnt++;
            return;
        }

        hanoi(disk - 1, start, temp, dest);

        sb.append(start).append(" ").append(dest).append("\n");
        cnt++;

        hanoi(disk - 1, temp, dest, start);
    }

}
