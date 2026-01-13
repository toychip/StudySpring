import java.util.Stack;

class Solution {
    public static boolean isValid(String s) {
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (c != '"') {
                sb.append(c);
            }
        }
        char[] charArray = sb.toString().toCharArray();
        Stack<Character> characters = new Stack<>();
        for (char c : charArray) {
            if (c == '(' || c == '[' || c =='{') {
                characters.add(c);
            } else {
                if (characters.isEmpty()) {
                    return false;
                }

                Character poll = characters.pop();
                if (poll.equals('(') && c == ')') {
                    continue;
                }
                if (poll.equals('[') && c == ']') {
                    continue;
                }
                if (poll.equals('{') && c == '}') {
                    continue;
                }
                
                return false;
            }
        }

        if (!characters.isEmpty()) {
            return false;
        }
        return true;
    }
}