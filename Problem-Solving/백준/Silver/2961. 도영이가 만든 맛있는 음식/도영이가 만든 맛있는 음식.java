import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N;
    static List<Food> datas;
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        getInput();
        recur(0, 1, 0, 0);
        writer.write(String.valueOf(answer));
        writer.flush();
        writer.close();
    }

    private static void recur(int idx, int sin, int ssun, int use) {
        if (idx == N) {
            // 아무 재료도 사용 안했다면
            if (use == 0) {
                return;
            }
            int result = Math.abs(sin - ssun);
            answer = Math.min(result, answer);
            return;
        }
        Food food = datas.get(idx);

        // 재료 사용
        recur(idx + 1, sin * food.getSin(), ssun + food.getSsun(), use + 1);

        // 재료 사용 x
        recur(idx + 1, sin, ssun, use);
    }

    private static void getInput() throws IOException {
        N = Integer.parseInt(reader.readLine());
        datas = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            String[] input = reader.readLine().split(" ");
            datas.add(new Food(Integer.parseInt(input[0]), Integer.parseInt(input[1])));
        }
    }

    static class Food {
        private final int sin;
        private final int ssun;

        public Food(final int sin, final int ssun) {
            this.sin = sin;
            this.ssun = ssun;
        }

        public int getSin() {
            return sin;
        }

        public int getSsun() {
            return ssun;
        }
    }
}
