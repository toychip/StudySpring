import java.util.HashSet;
import java.util.Set;

public class Trie {

    private final Set<String> set = new HashSet<>();

    public Trie() {

    }

    public void insert(String word) {
        set.add(word);
    }

    public boolean search(String word) {
        return set.stream().anyMatch(
                s -> s.equals(word));
    }

    public boolean startsWith(String prefix) {
        return set.stream().anyMatch(
                s -> s.startsWith(prefix)
        );
    }
}