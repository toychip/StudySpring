import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(reader.readLine());
        String[] words = new String[N];
        for (int i = 0; i < N; i++) {
            words[i] = reader.readLine();
        }

        int answer = 0;
        for (String word : words) {
            boolean isGroupWord = isIsGroupWord(word);
            if (isGroupWord) {
                answer++;
            }
        }
        System.out.println(answer);
    }

    private static boolean isIsGroupWord(final String word) {
        HashSet<String> repository = new HashSet<>();
        repository.add(String.valueOf(word.charAt(0)));
        boolean isGroupWord = true;
        for (int i = 1; i < word.length(); i++) {
            char prev = word.charAt(i - 1);
            char current = word.charAt(i);
            if (current != prev) {
                if (repository.contains(String.valueOf(current))) {
                    isGroupWord = false;
                } else {
                    repository.add(String.valueOf(current));
                }
            }
        }
        return isGroupWord;
    }
}