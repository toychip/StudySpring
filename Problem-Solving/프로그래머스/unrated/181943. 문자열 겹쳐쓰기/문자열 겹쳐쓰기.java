class Solution {
    public String solution(String my_string, String overwrite_string, int s) {
        String answer = "";
        
        
        answer = my_string.substring(0,s) + overwrite_string + 
//             my_string을 처음부터 매개변수 s까지 보존, + 
//             새로 들어온 매개변수 overwrite_string +
            my_string.substring(s + overwrite_string.length());
//             my_string을 s부터 overwrite_string의 길이만큼까지의 보존
    
        return answer;
    }
}