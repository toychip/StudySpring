import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String a = sc.next();
        String answer = "";
        for(char ex : a.toCharArray()){
            if(Character.isLowerCase(ex)){
                answer += Character.toUpperCase(ex);
            }
            else if(Character.isUpperCase(ex)){
                answer += Character.toLowerCase(ex);
            }
        }
        System.out.println(answer);
    }
}