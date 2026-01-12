import java.util.*;

public class Solution {

    public static List<List<String>> groupAnagrams(String[] strs) {
        ArrayList<String> input = new ArrayList<>(Arrays.asList(strs));
        ArrayList<Value> objects = new ArrayList<>();

        for (String origin : input) {
            char[] charArray = origin.toCharArray();
            Arrays.sort(charArray);
            StringBuilder stringBuilder = new StringBuilder();
            for (char c : charArray) {
                stringBuilder.append(c);
            }
            String sorted = stringBuilder.toString();
            objects.add(new Value(origin, sorted));
        }

        HashMap<String, List<String>> answer = new HashMap<>();

        for (Value object : objects) {
            boolean contains = answer.containsKey(object.sortKey);
            if (!contains) {
                answer.put(object.sortKey, new ArrayList<>(List.of(object.originKey)));
            } else {
                answer.get(object.sortKey).add(object.originKey);
            }
        }

        List<List<String>> finalAnswer = new ArrayList<>();

        for (Map.Entry<String, List<String>> stringListEntry : answer.entrySet()) {
            List<String> value = stringListEntry.getValue();
            finalAnswer.add(value);
        }
        return finalAnswer;
    }
}

class Value {
    String originKey;
    String sortKey;

    public Value(String originKey, String sortKey) {
        this.originKey = originKey;
        this.sortKey = sortKey;
    }
}
