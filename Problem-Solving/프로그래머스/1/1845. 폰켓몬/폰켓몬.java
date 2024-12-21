import java.util.HashMap;

class Solution {
    public int solution(final int[] array) {

        int N = array.length / 2;
        HashMap<Integer, Integer> repository = new HashMap<>();

        for (Integer key : array) {
            Integer value = repository.getOrDefault(key, 0);
            repository.put(key, value + 1);
        }

        return Math.min(repository.size(), N);
    }
}