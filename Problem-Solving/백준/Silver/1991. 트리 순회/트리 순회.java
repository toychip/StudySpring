import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static List<List<Integer>> tree;
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(reader.readLine());

        tree = new ArrayList<>();
        for (int i = 0; i < 200; i++) {
            tree.add(new ArrayList<>());
        }

        int firstAlphabet = 0;

        for (int i = 0; i < N; i++) {
            String[] input1 = reader.readLine().split(" ");
            int asciiRoot = input1[0].charAt(0);
            int asciiChild1 = input1[1].charAt(0);
            int asciiChild2 = input1[2].charAt(0);

            tree.get(asciiRoot).add(asciiChild1);
            tree.get(asciiRoot).add(asciiChild2);

            if (i == 0) {
                firstAlphabet = asciiRoot;
            }
        }

        recur1(firstAlphabet);
        System.out.println();
        recur2(firstAlphabet);
        System.out.println();
        recur3(firstAlphabet);
    }

    private static void recur1(final int root) {
        if (root == 46) {
            return;
        }

        List<Integer> childs = tree.get(root);

        System.out.print((char) root);
        for (Integer child : childs) {
            recur1(child);
        }
    }

    private static void recur2(final int root) {
        if (root == 46) {
            return;
        }

        List<Integer> childs = tree.get(root);
        Integer leftChild = childs.get(0);
        Integer rightChild = childs.get(1);

        recur2(leftChild);
        System.out.print((char) root);
        recur2(rightChild);
    }

    private static void recur3(final int root) {
        if (root == 46) {
            return;
        }

        List<Integer> childs = tree.get(root);
        for (Integer child : childs) {
            recur3(child);
        }

        System.out.print((char) root);
    }
}
