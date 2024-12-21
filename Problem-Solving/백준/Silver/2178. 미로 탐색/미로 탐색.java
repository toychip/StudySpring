import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        String[] input1 = reader.readLine().split(" ");
        int N = Integer.parseInt(input1[0]);
        int M = Integer.parseInt(input1[1]);
        int[][] board = new int[N][M];
        int[][] dist = new int[N][M];
        boolean[][] visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            String[] input2 = reader.readLine().split("");
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(input2[j]);
            }
        }

        Queue<Node> queue = new LinkedList<>();

        queue.add(new Node(0, 0));
        visited[0][0] = true;
        dist[0][0] = 1;

        while (!queue.isEmpty()) {
            Node currentNode = queue.poll();

            for (int dir = 0; dir < 4; dir++) {
                int y = currentNode.y;
                int x = currentNode.x;

                int ny = y + dy[dir];
                int nx = x + dx[dir];

                if (nx < 0 || ny < 0 || nx >= M || ny >= N) continue;

                if (board[ny][nx] == 0 || visited[ny][nx]) continue;

                visited[ny][nx] = true;
                dist[ny][nx] = dist[y][x] + 1;
                queue.add(new Node(ny, nx));
            }
        }
        int result = dist[N - 1][M - 1];
        System.out.print(result);

    }

    static class Node {
        int y;
        int x;

        public Node(final int y, final int x) {
            this.y = y;
            this.x = x;
        }
    }
}
