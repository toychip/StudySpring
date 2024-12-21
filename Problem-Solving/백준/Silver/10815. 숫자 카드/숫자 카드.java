import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(reader.readLine());
        int[] array1 = new int[N];
        String[] input1 = reader.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            array1[i] = Integer.parseInt(input1[i]);
        }

        int M = Integer.parseInt(reader.readLine());
        String[] input2 = reader.readLine().split(" ");
        int[] array2 = new int[M];

        for (int i = 0; i < M; i++) {
            array2[i] = Integer.parseInt(input2[i]);
        }

        Arrays.sort(array1);

        for (int number : array2) {
            int s = 0;
            int e = N - 1;
            boolean flag = false;
            while (s <= e) {
                int mid = (s + e) / 2;

                if (number == array1[mid]) {
                    flag = true;
                    break;
                } else if (number > array1[mid]) {
                    s = mid + 1;
                } else if (number < array1[mid]) {
                    e = mid - 1;
                }
            }

            if (flag) {
                System.out.print(1 + " ");
            } else {
                System.out.print(0 + " ");
            }

        }
    }
}
