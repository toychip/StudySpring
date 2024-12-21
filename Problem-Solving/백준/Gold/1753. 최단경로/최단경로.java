import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] input = reader.readLine().split(" ");
        int nodes = Integer.parseInt(input[0]);
        int lines = Integer.parseInt(input[1]);
        int start = Integer.parseInt(reader.readLine());

        List<List<Node>> links = new ArrayList<>();
        for (int i = 0; i <= nodes; i++) {
            links.add(new ArrayList<>());
        }

        int[] dist = new int[nodes + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        for (int i = 0; i < lines; i++) {
            String[] input2 = reader.readLine().split(" ");

            int A = Integer.parseInt(input2[0]);
            int B = Integer.parseInt(input2[1]);
            int C = Integer.parseInt(input2[2]);
            links.get(A).add(new Node(B, C));
        }

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));
        dist[start] = 0;

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            int curNode = current.getNode();

            for (Node next : links.get(curNode)) {
                int nextNode = next.getNode();
                int nextWeight = next.getWeight();

                if (dist[curNode] + nextWeight < dist[nextNode]) {
                    // 현재 가중치와 다음으로 가는데의 가중치를 합침
                    dist[nextNode] = dist[curNode] + nextWeight;
                    pq.add(new Node(nextNode, dist[nextNode]));
                }
            }
        }

        for (int i = 1; i <= nodes; i++) {
            if (dist[i] == Integer.MAX_VALUE) {
                System.out.println("INF");
            } else {
                System.out.println(dist[i]);
            }
        }
    }
}

class Node implements Comparable<Node> {
    private final int node;
    private final int weight;

    public Node(int node, int weight) {
        this.node = node;
        this.weight = weight;
    }

    @Override
    public int compareTo(Node o) {
        return Integer.compare(this.weight, o.weight);
    }

    public int getNode() {
        return node;
    }

    public int getWeight() {
        return weight;
    }
}