import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    static int N;
    static int M;
    static int[][] board;
    static int[][] prefixBoard;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = getInput();
//        solution_timeout();
        initPrefixBoard();
        solution(reader);
    }

    private static void solution(BufferedReader reader) throws IOException {
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        for (int i = 0; i < M; i++) {
            String[] input3 = reader.readLine().split(" ");
            int x1 = Integer.parseInt(input3[0]);
            int y1 = Integer.parseInt(input3[1]);

            int x2 = Integer.parseInt(input3[2]);
            int y2 = Integer.parseInt(input3[3]);

            int answer = prefixBoard[x2][y2] - prefixBoard[x1 - 1][y2] - prefixBoard[x2][y1 - 1] + prefixBoard[x1 - 1][y1 - 1];
            writer.write(answer + "\n");
        }
        // 출력된 내용을 즉시 출력
        writer.flush();
    }

    private static void initPrefixBoard() {
        prefixBoard = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (i == 1 && j == 1) {
                    prefixBoard[i][j] = board[i][j];
                    continue;
                }

                prefixBoard[i][j] = board[i][j] +
                        prefixBoard[i][j - 1] +
                        prefixBoard[i - 1][j] -
                        prefixBoard[i - 1][j - 1];
            }
        }
    }

    private static BufferedReader getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        getBoard(reader);
        return reader;
    }

    private static void getBoard(final BufferedReader reader) throws IOException {
        String[] input = reader.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        board = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            String[] input2 = reader.readLine().split(" ");
            for (int j = 1; j <= N; j++) {
                board[i][j] = Integer.parseInt(input2[j - 1]);
            }
        }
    }

    //    private static void solution_timeout() {
//        for (int i = 0; i < M; i++) {
//            List<Node> nodes1 = nodes.get(i);
//            Node fromNode = nodes1.get(0);
//            Node toNode = nodes1.get(1);
//
//            int answer = 0;
//            for (int j = fromNode.getX(); j <= toNode.getX(); j++) {
//                for (int k = fromNode.getY(); k <= toNode.getY(); k++) {
//                    answer += board[j][k];
//                }
//            }
//            System.out.println(answer);
//        }
//    }

//    private static void getNodes(final BufferedReader reader) throws IOException {
//        nodes = new ArrayList<>();
//        for (int i = 0; i < M; i++) {
//            String[] input3 = reader.readLine().split(" ");
//            Node fromNode = new Node(Integer.parseInt(input3[0]), Integer.parseInt(input3[1]));
//            Node toNode = new Node(Integer.parseInt(input3[2]), Integer.parseInt(input3[3]));
//            List<Node> nodesList = new ArrayList<>();
//            nodesList.add(fromNode);
//            nodesList.add(toNode);
//            nodes.add(nodesList);
//        }
//    }

//    static class Node {
//        private final int x;
//        private final int y;
//
//        public Node(final int x, final int y) {
//            this.x = x;
//            this.y = y;
//        }
//
//        public int getX() {
//            return x;
//        }
//
//        public int getY() {
//            return y;
//        }
//
//        @Override
//        public String toString() {
//            return "Node{" +
//                    "x=" + x +
//                    ", y=" + y +
//                    '}';
//        }
//    }
}
