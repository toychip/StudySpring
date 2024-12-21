import java.util.*;

public class Solution {
    public int[] solution(int[] arr) {
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < arr.length; i++) {
            if (i == 0) {
                stack.push(arr[i]);
                continue;
            }
            if (stack.peek() != arr[i]) {
                stack.push(arr[i]);
            }
        }

        List<Integer> list = new ArrayList<>(stack);

        return list.stream()
                .mapToInt(Integer::intValue)
                .toArray();
    }
}