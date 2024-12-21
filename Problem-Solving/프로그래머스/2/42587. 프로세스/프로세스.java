import java.util.ArrayList;
import java.util.List;

class Solution {
    public int solution(int[] priorities, int location) {

        List<Node> nodeList = new ArrayList<>();
        for (int i = 0; i < priorities.length; i++) {
            Node node = new Node(priorities[i], i);
            nodeList.add(node);
        }

        for (int i = 0; i < priorities.length; i++) {
            boolean moved = false;
            for (int j = i + 1; j < priorities.length; j++) {
                if (nodeList.get(i).getValue() < nodeList.get(j).getValue()) {

                    Node moveNode = nodeList.remove(i);
                    nodeList.add(moveNode);
                    moved = true;
                    break;
                }
            }
            if (moved) {
                i--;
            }
        }

        int answer = 0;
        for (Node node : nodeList) {
            answer++;
            if (node.getLocation() == location) {
                return answer;
            }
        }
        throw new RuntimeException("일어날 수 없는 오류");
    }
    static class Node{
        private int value;
        private int location;

        public Node(final int value, final int location) {
            this.value = value;
            this.location = location;
        }

        public int getValue() {
            return value;
        }

        public int getLocation() {
            return location;
        }
    }
}