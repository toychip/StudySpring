import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class Main {

    static int M;
    static int N;
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] input = reader.readLine().split(" ");

        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);

        // 길이가 M인 배열 선언 (수열을 저장할 공간)
        List<Integer> sequence = new ArrayList<>();
        backtracking(0, sequence);
        writer.flush();
        writer.close();
    }

    public static void backtracking(int depth, List<Integer> sequence) throws IOException {
        if (depth == M) {
            for (int num : sequence) {
                writer.write(num + " ");
            }
            writer.write("\n");
            // 재귀 함수 종료 후, 호출한 위치로 돌아감
            return;
        }

        for (int i = 1; i <= N; i++) {
            if (sequence.contains(i)) {
                continue;
            }
            sequence.add(i);
            // 다음 숫자 고르기
            backtracking(depth + 1, sequence);
            // 마지막으로 추가된 숫자 제거 (백트래킹)
            sequence.remove(sequence.size() - 1);
        }
    }

}
