class Solution {
    public int solution(int a, int b) {
        
        int answer = 0;
        int answer2 = 0;
        answer = Integer.valueOf(String.valueOf(a) + String.valueOf(b));
        answer2 = Integer.valueOf(String.valueOf(b) + String.valueOf(a));
        
        if (answer>=answer2){
            return answer;
        } else {
            return answer2;
        }
    }
}