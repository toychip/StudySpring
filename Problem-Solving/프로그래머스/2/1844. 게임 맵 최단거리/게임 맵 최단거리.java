import java.util.*;

class Solution {
    static class Node {
        int y, x, distance;

        public Node(int y, int x, int distance) {
            this.y = y;
            this.x = x;
            this.distance = distance;
        }
    }
    
    public int solution(int[][] maps) {
        int n = maps.length;
        int m = maps[0].length;
        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};
        
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(0, 0, 1)); // 시작점에서 거리 1로 시작
        boolean[][] visited = new boolean[n][m];
        visited[0][0] = true;
        
        while (!queue.isEmpty()) {
            Node currentNode = queue.poll();
            
            if (currentNode.y == n - 1 && currentNode.x == m - 1) {
                return currentNode.distance;
            }

            for (int i = 0; i < 4; i++) {
                int ny = currentNode.y + dy[i];
                int nx = currentNode.x + dx[i];

                if (ny >= 0 && nx >= 0 && ny < n && nx < m && maps[ny][nx] == 1 && !visited[ny][nx]) {
                    visited[ny][nx] = true;
                    int newDistance = currentNode.distance + 1;
                    queue.add(new Node(ny, nx, newDistance));
                }
            }
        }
        return -1;
    }
}
