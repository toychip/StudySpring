import java.util.Stack;

class Solution {
    boolean solution(String s) {
        char[] inputArray = s.toCharArray();

        Stack<Character> stack = new Stack<>();
        for (char character : inputArray) {
            if (character == '(') {
                stack.push(character);
            }

            if (stack.isEmpty() && character == ')') {
                return false;
            }
            
            if (character == ')') {
                stack.pop();
            }
        }

        if (stack.isEmpty()) {
            return true;
        } else {
            return false;
        } 
    }
}