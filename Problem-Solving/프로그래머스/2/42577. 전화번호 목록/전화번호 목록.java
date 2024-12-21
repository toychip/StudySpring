import java.util.*;

class Solution {

        public boolean solution(String[] phone_book) {
        HashSet<String> phoneSet = new HashSet<>(Arrays.asList(phone_book));

        for (String number : phone_book) {
            for (int i = 1; i < number.length(); i++) {
                if (phoneSet.contains(number.substring(0, i))) {
                    return false;
                }
            }
        }

//          시간 복잡도 때문에 실패
//        for (int i = 0; i < length - 1; i++) {
//            for (int j = i  + 1; j < length; j++) {
//                if (
//                        phone_book[j].startsWith(phone_book[i])
//                ) {
//                    return false;
//                }
//            }
//        }
        return true;
    }
}