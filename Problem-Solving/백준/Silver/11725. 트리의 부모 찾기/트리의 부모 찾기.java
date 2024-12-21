import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    static List<List<Integer>> graph;
    static int[] parents;
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(reader.readLine());

        graph = new ArrayList<>();
        parents = new int[N + 1];

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 1; i < N; i++) {
            String[] input1 = reader.readLine().split(" ");
            int from = Integer.parseInt(input1[0]);
            int to = Integer.parseInt(input1[1]);
            graph.get(from).add(to);
            graph.get(to).add(from);
        }

        recur(1, 0);

        for (int i = 2; i < parents.length; i++) {
            System.out.println(parents[i]);
        }
    }

    private static void recur(int node, int previous) {
        parents[node] = previous;
        List<Integer> children = graph.get(node);
        for (Integer next : children) {
            if (next != previous) {
                recur(next, node);
            }
        }
    }
}
