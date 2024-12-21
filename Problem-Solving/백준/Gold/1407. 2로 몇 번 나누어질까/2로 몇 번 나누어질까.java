import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");

        long from = Long.parseLong(input[0]);
        long to = Long.parseLong(input[1]);
        from--;

        long fromCount = from;
        long toCount = to;

        for (long i = 1; i < 99; i++) {
            fromCount += ((long) (from / Math.pow(2, i)) * (long) (Math.pow(2, i) - (long) Math.pow(2, (i - 1))));
        }

        for (long i = 1; i < 99; i++) {
            toCount += ((long) (to / Math.pow(2, i)) * (long) (Math.pow(2, i) - (long) Math.pow(2, (i - 1))));
        }

        System.out.println(toCount - fromCount);
    }
}
