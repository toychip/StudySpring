import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
    static ArrayList<ArrayList<Integer>> graph;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int computer = Integer.parseInt(reader.readLine());
        int lines = Integer.parseInt(reader.readLine());

        graph = new ArrayList<>();
        for (int i = 0; i <= computer; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < lines; i++) {
            String[] input = reader.readLine().split(" ");
            int from = Integer.parseInt(input[0]);
            int to = Integer.parseInt(input[1]);

            graph.get(from).add(to);
            graph.get(to).add(from);
        }

        visited = new boolean[computer + 1];

        dfs(1);
        int answer = 0;
        for (int i = 2; i < visited.length; i++) {
            if (visited[i]) {
                answer++;
            }
        }
        System.out.print(answer);
    }

    private static void dfs(final int node) {
        visited[node] = true;
        for (Integer next : graph.get(node)) {
            if (visited[next] == true) {
                continue;
            }
            dfs(next);
        }
    }
}
