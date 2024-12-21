import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Solution {
public int[] solution(int[] array, int[][] commands) {
        List<Integer> answerList = new ArrayList<>();
        for (int[] command : commands) {
            int i = command[0];
            int j = command[1];
            int k = command[2];

            List<Integer> list = new ArrayList<>();
            for (int l = i - 1; l < j; l++) {
                int innerValue = array[l];
                list.add(innerValue);
            }
            Collections.sort(list);

            Integer answerValue = list.get(k - 1);
            answerList.add(answerValue);
        }
        int[] answer = answerList.stream()
                .mapToInt(Integer::intValue)
                .toArray();

        return answer;
    }
}