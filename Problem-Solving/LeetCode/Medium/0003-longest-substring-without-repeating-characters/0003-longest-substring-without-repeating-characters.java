import java.util.HashSet;

class Solution {
    public int lengthOfLongestSubstring(String s) {
        char[] charArray = s.toCharArray();
        HashSet<Character> characters = new HashSet<>();
        int length = charArray.length;
        int answer = 0;
        int left = 0;
        for (int right = 0; right < length; right++) {
            char c = charArray[right];
            while (characters.contains(c)) {
                characters.remove(charArray[left]);
                left++;
            }

            characters.add(c);
            answer = Math.max(answer, right - left + 1);
        }

        return answer;
    }
}