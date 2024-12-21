import java.util.Arrays;
import java.util.HashMap;

class Solution {

    public static int solution(int n, int[] lost, int[] reserve) {
        Arrays.sort(lost);
        Arrays.sort(reserve);

        HashMap<Integer, Boolean> reserveList = new HashMap<>();

        boolean[] have = new boolean[n + 1];
        Arrays.fill(have, true);

        // 잃어버린 사람들 have 배열에 false로 변경
        for (int l : lost) {
            have[l] = false;
        }

        // 남는 사람 리스트 타입으로 생성
        for (int reserveMember : reserve) {
            reserveList.put(reserveMember, true);
        }

        // 도난당한 학생이 여벌 체육복을 가져온 경우 본인의 체육복을 사용함
        for (int i = 0; i < lost.length; i++) {
            if (reserveList.getOrDefault(lost[i], false)) {
                have[lost[i]] = true;
                reserveList.put(lost[i], false);
                lost[i] = -1; // 처리된 도난 케이스를 표시
            }
        }

        for (int lostMember : lost) {
            if (lostMember == -1) {
                continue; // 이미 처리된 경우 건너뜀
            }

            if (lostMember > 1 && reserveList.getOrDefault(lostMember - 1, false)) {
                // 이전 번호 학생에게서 받는 경우
                have[lostMember] = true;
                reserveList.put(lostMember - 1, false);
                continue;
            }

            if (lostMember < n && reserveList.getOrDefault(lostMember + 1, false)) {
                // 다음 번호 학생에게서 받는 경우
                have[lostMember] = true;
                reserveList.put(lostMember + 1, false);
            }
        }

        int answer = 0;
        for (int i = 1; i <= n; i++) {
            if (have[i]) {
                answer++;
            }
        }
        return answer;
    }
}