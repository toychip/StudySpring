import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    static int maxY;
    static int maxX;
    static char[][] graph;
    static int[][] dir = {
            {0, 1}, {0, -1}, {1, 0}, {-1, 0}
    };

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] input = reader.readLine().split(" ");
        maxY = Integer.parseInt(input[0]);
        maxX = Integer.parseInt(input[1]);
        graph = new char[maxY][maxX];

        for (int i = 0; i < maxY; i++) {
            String line = reader.readLine();
            for (int j = 0; j < maxX; j++) {
                graph[i][j] = line.charAt(j);
            }
        }

        int answer = 0;

        for (int y = 0; y < maxY; y++) {
            for (int x = 0; x < maxX; x++) {
                if (graph[y][x] == 'L') {
                    answer = bfs(y, x, answer);
                }
            }
        }
        System.out.print(answer);
    }

    private static int bfs(int y, int x, int answer) {
        Queue<Position> queue = new LinkedList<>();
        boolean[][] visited = new boolean[maxY][maxX];
        int[][] dist = new int[maxY][maxX];

        queue.add(new Position(y, x));
        visited[y][x] = true;

        while (!queue.isEmpty()) {

            Position currentPosition = queue.poll();
            int currentX = currentPosition.getX();
            int currentY = currentPosition.getY();

            for (int k = 0; k < 4; k++) {
                int[] nextPosition = dir[k];
                int dy = nextPosition[0];
                int dx = nextPosition[1];

                int ny = currentY + dy;
                int nx = currentX + dx;

                if (maxX > nx && maxY > ny && nx >= 0 && ny >= 0 && !visited[ny][nx] && graph[ny][nx] == 'L') {
                    visited[ny][nx] = true;
                    dist[ny][nx] = dist[currentY][currentX] + 1;
                    answer = Math.max(answer, dist[ny][nx]);
                    queue.add(new Position(ny, nx));
                }
            }
        }
        return answer;
    }
}

class Position {
    private final int x;
    private final int y;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Position(final int y, final int x) {
        this.y = y;
        this.x = x;
    }
}