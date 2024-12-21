import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static int N, M;
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        
        N = sc.nextInt();
        M = sc.nextInt();
        
        List<Integer> sequence = new ArrayList<>();
        backtracking(0, sequence);
        bw.flush();  // 버퍼에 남아 있는 데이터 출력
        bw.close();  // 스트림 닫기
    }
    
    public static void backtracking(int depth, List<Integer> sequence) throws IOException {
        if (depth == M) {
            // 버퍼에 출력 내용을 추가
            for (int num : sequence) {
                bw.write(num + " ");
            }
            bw.write("\n");
            return;
        }

        for (int i = 1; i <= N; i++) {
            sequence.add(i);  // 리스트에 숫자 추가
            backtracking(depth + 1, sequence);  // 재귀 호출
            sequence.remove(sequence.size() - 1);  // 백트래킹: 마지막 숫자 제거
        }
    }
}
