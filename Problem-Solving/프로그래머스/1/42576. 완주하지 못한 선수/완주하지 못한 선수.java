import java.util.HashMap;
import java.util.Map.Entry;

class Solution {
    public String solution(String[] participant, String[] completion) {
        HashMap<String, Integer> repository = new HashMap<>();

        for (String s : participant) {
            Integer value = repository.getOrDefault(s, 0);
            repository.put(s, value + 1);
        }

        for (String s : completion) {
            Integer value = repository.getOrDefault(s, 0);
            repository.put(s, value - 1);
        }

        for (Entry<String, Integer> map : repository.entrySet()) {
            if (map.getValue() > 0) {
                return map.getKey();
            }
        }

        return "";
    }
}