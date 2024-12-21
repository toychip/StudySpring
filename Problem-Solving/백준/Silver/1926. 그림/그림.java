import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] input1 = reader.readLine().split(" ");
        int y = Integer.parseInt(input1[0]);
        int x = Integer.parseInt(input1[1]);

        int[][] board = new int[y][x];
        int[][] visited = new int[y][x];
        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};

        for (int i = 0; i < y; i++) {
            String[] input2 = reader.readLine().split(" ");
            for (int j = 0; j < x; j++) {
                board[i][j] = Integer.parseInt(input2[j]);
            }
        }

        int maxArea = 0;
        int pictures = 0;
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                if (board[i][j] == 1 && visited[i][j] == 0) {
                    pictures++;
                    Queue<Node> queue = new LinkedList<>();
                    visited[i][j] = 1;
                    queue.add(new Node(i, j));
                    int area = 0;
                    while (!queue.isEmpty()) {
                        Node currentNode = queue.poll();
                        area++;
                        for (int dir = 0; dir < 4; dir++) {
                            int nx = dx[dir] + currentNode.x;
                            int ny = dy[dir] + currentNode.y;

                            if (ny >= y || nx >= x || nx < 0 || ny < 0) {
                                continue;
                            }

                            if (board[ny][nx] != 1) {
                                continue;
                            }

                            if (visited[ny][nx] == 1) {
                                continue;
                            }

                            // 다음으로 나아갈 수 있고, // 방문한 적 없으면
                            visited[ny][nx] = 1;
                            queue.add(new Node(ny, nx));
                        }
                    }
                    maxArea = Math.max(maxArea, area);
                }
            }
        }
        System.out.println(pictures);
        System.out.print(maxArea);
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