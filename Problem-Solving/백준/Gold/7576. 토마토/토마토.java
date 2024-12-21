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
        int x = Integer.parseInt(input1[0]);
        int y = Integer.parseInt(input1[1]);

        int[][] board = new int[y][x];
        Queue<Node> queue = new LinkedList<>();

        // 초기 상태 입력 및 익은 토마토 큐에 추가
        for (int i = 0; i < y; i++) {
            String[] input2 = reader.readLine().split(" ");
            for (int j = 0; j < x; j++) {
                board[i][j] = Integer.parseInt(input2[j]);
                if (board[i][j] == 1) {
                    queue.add(new Node(j, i));
                }
            }
        }

        // 모든 토마토가 있었는가?
        if (isNotZero(board)) {
            System.out.println(0);
            return;
        }

        boolean isChanged = true;
        int answer = 0;

        while (isChanged && !queue.isEmpty()) {
            isChanged = false;
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                Node currentNode = queue.poll();

                for (int dir = 0; dir < 4; dir++) {
                    int nx = currentNode.x + dx[dir];
                    int ny = currentNode.y + dy[dir];

                    if (ny >= y || nx >= x || ny < 0 || nx < 0) {
                        continue;
                    }

                    if (board[ny][nx] == 0) {
                        board[ny][nx] = 1;
                        isChanged = true;
                        queue.add(new Node(nx, ny));
                    }
                }
            }

            if (isChanged) {
                answer++;
            }
        }
        printResult(board, answer);
    }

    private static boolean isNotZero(final int[][] board) {
        boolean isNotZero = true;
        for (int[] ints : board) {
            for (int anInt : ints) {
                if (anInt == 0) {
                    isNotZero = false;
                    break;
                }
            }
        }
        return isNotZero;
    }

    private static void printResult(final int[][] board, final int answer) {
        for (int[] ints : board) {
            for (int anInt : ints) {
                if (anInt == 0) {
                    System.out.print(-1);
                    return;
                }
            }
        }

        System.out.print(answer);
    }

    static class Node {
        final int x;
        final int y;

        public Node(final int x, final int y) {
            this.x = x;
            this.y = y;
        }
    }
}
