import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(reader.readLine());

        for (int i = 0; i < n; i++) {

            HashMap<Character, Integer> defaultMap = new HashMap<>();
            HashMap<Character, Integer> targetMap = new HashMap<>();

            String[] array = reader.readLine().split(" ");
            String first = array[0];
            String second = array[1];

            for (int j = 0; j < first.length(); j++) {
                defaultMap.put(first.charAt(j), defaultMap.getOrDefault(first.charAt(j), 0) + 1);
            }

            for (int k = 0; k < second.length(); k++) {
                targetMap.put(second.charAt(k), targetMap.getOrDefault(second.charAt(k), 0) + 1);
            }

            StringBuilder builder = new StringBuilder();

            builder.append(first);
            builder.append(" & ");
            builder.append(second);
            builder.append(" are ");

            if (defaultMap.equals(targetMap)) {
                builder.append("anagrams.");
            } else {
                builder.append("NOT anagrams.");
            }
            String answer = builder.toString();
            writer.write(answer);
            writer.write("\n");
        }
        writer.flush();
        writer.close();
    }
}
