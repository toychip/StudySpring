import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
    
        if(str.length()<=10&&str.length()>=1){
          for (int i=0; i<str.length(); i++){
            System.out.println(str.charAt(i));  
            }  
        }
    }
}