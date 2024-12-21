import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N;
    static List<Meeting> meetings;
    static int[] dp1;
    static int[] dp2;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(reader.readLine());
        meetings = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            String[] input = reader.readLine().split(" ");
            meetings.add(new Meeting(Integer.parseInt(input[0]), Integer.parseInt(input[1])));
        }

//        int answer = solution_topdownDP(0);
        // 바텀업 DP로 도전
        int answer = solution_bottomupDP();

        writer.write(String.valueOf(answer));
        writer.flush();
        writer.close();
    }

    private static int solution_bottomupDP() {
        dp2 = new int[N + 1];
        recur2();
        return dp2[0];
    }

    private static void recur2() {
        for (int i = N - 1; i >= 0; i--) {
            Meeting meeting = meetings.get(i);
            if (i + meeting.getDay() > N) {
                dp2[i] = dp2[i + 1];  // 상담을 할 수 없는 경우 다음 날의 값 복사
            } else {
                dp2[i] = Math.max(dp2[i + meeting.getDay()] + meeting.getPay(), dp2[i + 1]);
            }
        }
    }

    // 탑다운 DP, 현재 풀이에 미사용. 어떤 방법을 쓰던 상관 없음
    private static int solution_topdownDP(int input) {
        dp1 = new int[N + 1];
        Arrays.fill(dp1, -1);
        return recur1(input);
    }

    private static int recur1(int index) {
        if (index == N) {
            return 0;
        }

        if (index > N) {
            return Integer.MIN_VALUE;
        }

        Meeting meeting = meetings.get(index);

        // 이미 저장되었다면
        if (dp1[index] != -1) {
            return dp1[index];
        }

        // 상담을 받거나, 안받거나, 그 중에서 더 많은 돈을 버는 경우를 내 DP 테이블에 저장
        dp1[index] = Math.max(
                recur1(index + meeting.getDay()) + meeting.getPay(), recur1(index + 1)
        );

        return dp1[index];
    }

    static class Meeting {
        private final int day;
        private final int pay;

        public Meeting(final int day, final int pay) {
            this.day = day;
            this.pay = pay;
        }

        public int getDay() {
            return day;
        }

        public int getPay() {
            return pay;
        }
    }
}


