import java.util.LinkedList;
import java.util.Queue;

class Solution {

    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    public int numIslands(char[][] grid) {
        int y = grid.length;
        int x = grid[0].length;
        boolean[][] visited = new boolean[y][x];
        Queue<Node> queue = new LinkedList<>();
        int isLand = 0;

        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {

                if (grid[i][j] == '1' && !visited[i][j]) {
                    isLand++;
                    queue.add(new Node(i, j));
                    while (!queue.isEmpty()) {
                        Node currentNode = queue.poll();
                        for (int k = 0; k < 4; k++) {
                            int nextX = currentNode.x + dx[k];
                            int nextY = currentNode.y + dy[k];
                            if (nextY >= 0 && nextY < y && nextX >= 0 && nextX < x && grid[nextY][nextX] == '1' && !visited[nextY][nextX]) {
                                visited[nextY][nextX] = true;
                                queue.add(new Node(nextY, nextX));
                            }
                        }
                    }
                }
            }
        }

        return isLand;
    }
}

class Node {
    int y;
    int x;

    public Node(int y, int x) {
        this.y = y;
        this.x = x;
    }
}