import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

/*
    1. 한 번에 1 or 2개씩
    2. 연속된 3개 x
    3. 마지막 꼭 밟아야 함
 */

public class Main {
    static int[] values;
    static int[][] dp;
    static int N;
    public static void main(String[] args) throws IOException {
        Reader reader = getReader();
        Writer writer = getWriter();
        N = reader.readInt();
        values = new int[N + 1];
        dp = new int[N + 1][3];
        for (int[] array : dp) {
            Arrays.fill(array, -1);
        }

        for (int i = 1; i <= N; i++) {
            int value = reader.readInt();
            values[i] = value;
        }

        int answer = recur(0, 0);
        writer.writeInt(answer);
        writer.end();
    }

    private static int recur(int dept, int continuous) {
        if (dept > N) {
            return Integer.MIN_VALUE;
        }

        if (dept == N) {
            return values[dept];
        }

        if (dp[dept][continuous] != -1) {
            return dp[dept][continuous];
        }

        int max = Integer.MIN_VALUE;

        // 연속된 2개이므로 1개밖에 못올라감
        if (continuous < 2) {
            max = Math.max(recur(dept + 1, continuous + 1) + values[dept], max);
        }

        // 1개 오르거나, 2개 오르거나
        if (continuous <= 2) {
            max = Math.max(recur(dept + 2, 1) + values[dept], max);
        }

        dp[dept][continuous] = max;
        return max;
    }
    
    private static BojWriter getWriter() {
        return new BojWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    }

    private static BojReader getReader() {
        return new BojReader(new BufferedReader(new InputStreamReader(System.in)));
    }
}

interface Reader {
    int readInt() throws IOException;
}

class BojReader implements Reader {

    private final BufferedReader bufferedReader;

    public BojReader(final BufferedReader bufferedReader) {
        this.bufferedReader = bufferedReader;
    }

    @Override
    public int readInt() throws IOException {
        return Integer.parseInt(bufferedReader.readLine());
    }
}

interface Writer {
    void writeString(String value) throws IOException;

    void writeInt(int value) throws IOException;

    void nextLine() throws IOException;

    void end() throws IOException;
}

class BojWriter implements Writer {

    private final BufferedWriter bufferedWriter;

    BojWriter(final BufferedWriter bufferedWriter) {
        this.bufferedWriter = bufferedWriter;
    }

    @Override
    public void writeString(final String value) throws IOException {
        bufferedWriter.write(value);
    }

    @Override
    public void writeInt(final int value) throws IOException {
        bufferedWriter.write(String.valueOf(value));
    }

    @Override
    public void nextLine() throws IOException {
        bufferedWriter.write("\n");
    }

    @Override
    public void end() throws IOException {
        bufferedWriter.flush();
        bufferedWriter.close();
    }
}