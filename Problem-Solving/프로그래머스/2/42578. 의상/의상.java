import java.util.HashMap;
import java.util.Iterator;

class Solution {
    public int solution(String[][] clothes) {

        HashMap<String, Integer> clothesMap = new HashMap<>();

        for (String[] clothe : clothes) {
            String type = clothe[1];
            clothesMap.put(type, clothesMap.getOrDefault(type, 0) + 1);
        }

        Iterator<Integer> iterator = clothesMap.values().iterator();
        int answer = 1;
        
        // 이터레이터를 사용하여 값들에 접근
        while (iterator.hasNext()) {
            Integer value = iterator.next();
            answer *= value.intValue() + 1;
        }
        return answer - 1;
    }
}