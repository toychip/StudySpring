class Solution {
    public int solution(int a, int b) {
        int temp = 0;
        int possible;
        temp = Integer.valueOf(String.valueOf(a) + String.valueOf(b));
        possible = 2 * a * b;
        
        if (temp >= possible){
            return temp;
        } else {
            return possible;
        }
    }
}